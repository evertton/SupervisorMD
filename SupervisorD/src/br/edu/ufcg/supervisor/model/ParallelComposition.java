/**
 * Copyright 2013-2014 Marcos Ferreira and Elthon Oliveira
 * 
 * This file is part of SupervisorD for Healthcare Professional software.
 * 
 *  SupervisorD for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  SupervisorD for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with SupervisorD for Healthcare Professional. 
 *  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contact: el7hon at gmail dot com
 */

package br.edu.ufcg.supervisor.model;

import java.util.ArrayList;
//import java.util.Vector;

import br.edu.ufcg.supervisor.desktop.util.AttributeList;

/**
 * Classe responsável pela execução do algoritmo da composição paralela. 
 * @author Elthon Oliveira, Marcos José 
 */
public class ParallelComposition {
	/**
	 * Construtor padrão.
	 */
	public ParallelComposition(){ }
	/**
	 * Executa a composição paralela.
	 * @param array	Um ArrayList de Atributos.
	 * @return		Um Automato resultado da composição paralela.
	 */
	public Automaton executaComposicao(ArrayList<Attribute> array){
		Automaton automato_composicao = new Automaton(array.get(0).getAutomaton());
		int size = array.size();
		for (int i = 1; i < size; i++){
			Automaton automatoB = array.get(i).getAutomaton();
			automato_composicao = compoe2Automatos(automato_composicao, automatoB);
		}
		String nome = AttributeList.getIdDoAtributo( array.get(0).getAutomaton().getNome() ).toString();
		StringBuffer sbNome = new StringBuffer(nome+"_");
		for (int i = 1; i < size; i++){
			nome = array.get(i).getAutomaton().getNome();
			sbNome.append(AttributeList.getIdDoAtributo(nome)+"_"); 
		}
		automato_composicao.setNome(sbNome.toString());
		return automato_composicao;
	}

	/* Realiza a composição paralela de dois automatos */
	private Automaton compoe2Automatos(Automaton automatoA, Automaton automatoB){
		Automaton resultado = new Automaton();
		//faz o produto dos estados
		for(State eB : automatoB.getVectorEstados()){
			for(State eA : automatoA.getVectorEstados()){
				State eNovo = new State();
				eNovo.setNome(eA.getNome()+"."+eB.getNome());
				eNovo.setClassificacao(getClassificacao(eA,eB));
				eNovo.addListaIntervalos(eA.getListaIntervalos());
				eNovo.addListaIntervalos(eB.getListaIntervalos());
				resultado.addEstado(eNovo);
			}
		}
		//seta as transições
		for(State eB : automatoB.getVectorEstados()){
			for(State eA : automatoA.getVectorEstados())
				setTransicoesDaComposicao(eA, eB, automatoA, automatoB, resultado);
		}
		return resultado;
	}

	//escrever o algoritmo na mão
	@SuppressWarnings("unchecked")
	private void setTransicoesDaComposicao(State estadoA, State estadoB, 
			Automaton automatoA, Automaton automatoB, Automaton produto){
		ArrayList<String> listaDeRotulosDeA = automatoA.getListaDeRotulos();
		ArrayList<String> listaDeRotulosDeB = automatoB.getListaDeRotulos();
		ArrayList<Transition> arrayTransicoesA = (ArrayList<Transition>) automatoA.getArrayTransicoes().clone();
		ArrayList<Transition> arrayTransicoesB = (ArrayList<Transition>) automatoB.getArrayTransicoes().clone();
		ArrayList<Transition> arrayTransicoesEstadoA = new ArrayList<Transition>();//lista das transições do estado A
		ArrayList<Transition> arrayTransicoesEstadoB = new ArrayList<Transition>();//lista das transições do estado B
		ArrayList<Transition> arrayTransicoesEstadoBIguais = new ArrayList<Transition>();
		ArrayList<Transition> arrayTransicoesEstadoAIguais = new ArrayList<Transition>();
		//verifica se é uma transicao pertencente ao estado do automato A analisado
		for (Transition tA : arrayTransicoesA){
			if (tA.getEstadoOrigem().getNome().equals(estadoA.getNome())) arrayTransicoesEstadoA.add(tA);
		}
		//verifica se é uma transicao pertencente ao estado do automato B analisado
		for (Transition tB : arrayTransicoesB){
			if (tB.getEstadoOrigem().getNome().equals(estadoB.getNome())) arrayTransicoesEstadoB.add(tB);
		}
		//Verifica as transições dos estados A e B que tem o mesmo rótulo. 
		//Caso tenha, adiciona no ArrayList de transições iguais.
		for (Transition tA : arrayTransicoesEstadoA){//
			for (Transition tB : arrayTransicoesEstadoB){
				if (tB.getRotulo().equals(tA.getRotulo())){
					if (!arrayTransicoesEstadoAIguais.contains(tA)) arrayTransicoesEstadoAIguais.add(tA);
					if (!arrayTransicoesEstadoBIguais.contains(tB)) arrayTransicoesEstadoBIguais.add(tB);
				}
			}
		}
		//Remove as transições iguais do arrayTransicoesEstadoA.
		for (Transition tIgual : arrayTransicoesEstadoAIguais) arrayTransicoesEstadoA.remove(tIgual);
		//Remove as transições iguais do arrayTransicoesEstadoB.
		for (Transition tIgual : arrayTransicoesEstadoBIguais) arrayTransicoesEstadoB.remove(tIgual);
		//Adiciona as transições iguais no automato produto fazendo a relação entre os estados.
		for (Transition tAIgual : arrayTransicoesEstadoAIguais){
			for (Transition tBIgual : arrayTransicoesEstadoBIguais){
				if (tAIgual.getRotulo().equals(tBIgual.getRotulo()))
					produto.addTransicao(criarTransicaoRotulosIguais(tAIgual, tBIgual, produto));
			}
		}
		//Adiciona as transições diferentes no automato produto fazendo a relação entre os estados.
		for (Transition tA : arrayTransicoesEstadoA){
			if (!listaDeRotulosDeB.contains(tA.getRotulo()))
				produto.addTransicao(criarTransicaoRotuloA(tA, estadoB.getNome(), produto));//tB));
		}
		for (Transition tB : arrayTransicoesEstadoB){
			if (!listaDeRotulosDeA.contains(tB.getRotulo()))
				produto.addTransicao(criarTransicaoRotuloB(estadoA.getNome(), tB, produto));//tB));
		}
	}

	/* Cria uma transição a partir de outras duas com rótulos iguais */
	private Transition criarTransicaoRotulosIguais(Transition tA, Transition tB, Automaton produto){
		Transition transicao = new Transition(); 
		transicao.setRotulo(tA.getRotulo());
		transicao.setMensagem(tA.getMensagem());
		String nomeEstadoInicial = tA.getEstadoOrigem().getNome()+"."+tB.getEstadoOrigem().getNome();
		String nomeEstadoFinal = tA.getEstadoDestino().getNome()+"."+tB.getEstadoDestino().getNome();
		for (State e : produto.getVectorEstados()){
			if (e.getNome().equals(nomeEstadoFinal)) transicao.setEstadoDestino(e);
			if (e.getNome().equals(nomeEstadoInicial)) transicao.setEstadoOrigem(e);
		}
		return transicao;
	}

	/* Cria uma transição baseada numa transição do automato A */
	private Transition criarTransicaoRotuloA(Transition tA, String nomeEstadoB, Automaton produto){
		Transition transicao = new Transition();
		transicao.setRotulo(tA.getRotulo());
		transicao.setMensagem(tA.getMensagem());		
		String nomeEstadoFinal = tA.getEstadoDestino().getNome()+"."+nomeEstadoB;
		String nomeEstadoInicial = tA.getEstadoOrigem().getNome()+"."+nomeEstadoB;
		for (State e : produto.getVectorEstados()){
			if (e.getNome().equals(nomeEstadoFinal)) transicao.setEstadoDestino(e);
			if (e.getNome().equals(nomeEstadoInicial)) transicao.setEstadoOrigem(e);
		}
		return transicao;
	}

	/* Cria uma transição baseada numa transição do automato B */
	private Transition criarTransicaoRotuloB(String nomeEstadoA, Transition tB, Automaton produto ){
		Transition transicao = new Transition();
		transicao.setMensagem(tB.getMensagem());
		transicao.setRotulo(tB.getRotulo());
		String nomeEstadoInicial = nomeEstadoA +"."+ tB.getEstadoOrigem().getNome();
		String nomeEstadoFinal = nomeEstadoA +"."+ tB.getEstadoDestino().getNome();
		for (State e : produto.getVectorEstados()){
			if (e.getNome().equals(nomeEstadoFinal)) transicao.setEstadoDestino(e);
			if (e.getNome().equals(nomeEstadoInicial)) transicao.setEstadoOrigem(e);
		}
		return transicao;
	}

	/* Determina a classificação do estado após o produto de dois outros estados */
	private int getClassificacao(State eA, State eB){
		if (eA.getNome().contains("HhrR"))
			return getClassificacao3(eA,eB);
		else if (eB.getNome().contains("HhrR"))
			return getClassificacao3(eB,eA);
		else
			return getClassificacao1(eA,eB);
	}
	
	/* original - mais estrito*/
	private int getClassificacao1(State eA, State eB){
		if (eA.getClassificacao() == State.INT_CL_PERIGOSO || (eB.getClassificacao() == State.INT_CL_PERIGOSO))
			return State.INT_CL_PERIGOSO;
		else if ((eA.getClassificacao() == State.INT_CL_TOLERAVEL) || (eB.getClassificacao() == State.INT_CL_TOLERAVEL))
			return State.INT_CL_TOLERAVEL;
		return State.INT_CL_ACEITACAO;
	}

	/* versao menos estrita - developed based on tests on field*/
	private int getClassificacao2(State eA, State eB){
		if (eA.getClassificacao() == State.INT_CL_PERIGOSO || (eB.getClassificacao() == State.INT_CL_PERIGOSO))
			return State.INT_CL_PERIGOSO;
		else if ((eA.getClassificacao() == State.INT_CL_ACEITACAO) || (eB.getClassificacao() == State.INT_CL_ACEITACAO))
			return State.INT_CL_ACEITACAO;
		return State.INT_CL_TOLERAVEL;
	}

	/* developed based on tests on field - hear-rate is a pivot */
	private int getClassificacao3(State eA, State eB){
		if (eA.getClassificacao() == State.INT_CL_PERIGOSO || (eB.getClassificacao() == State.INT_CL_PERIGOSO))
			return State.INT_CL_PERIGOSO;
		else if ((eA.getClassificacao() == State.INT_CL_ACEITACAO))
			return State.INT_CL_ACEITACAO;
		return State.INT_CL_TOLERAVEL;
	}
	
}