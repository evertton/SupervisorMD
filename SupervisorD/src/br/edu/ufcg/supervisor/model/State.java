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
import java.util.HashMap;

import br.edu.ufcg.supervisor.desktop.util.Internacionalizar;

import com.google.gson.internal.StringMap;

/**
 * Classe que representa o estado de um autômato. 
 * @author Elthon Oliveira, Marcos José 
 *
 */
public class State {
	public static final int INT_CL_ACEITACAO = 3;
	public static final int INT_CL_PERIGOSO = 2;
	public static final int INT_CL_TOLERAVEL = 1;
	//public static final int INT_CL_NENHUM = 0;

	public static final String NOME = "1";
	public static final String VALOR_MAXIMO = "2";
	public static final String VALOR_MINIMO = "3";
	public static final String CLASSIFICACAO = "4";
	public static final String LISTA_INTERVALOS = "5";

	private String nome;
	private int classificacao;
	private ArrayList<Transition> arrayTransicoes;
	private ArrayList<Range> listaDeIntervalos;

	/**
	 * Construtor padrão.
	 */
	public State(){ initAtributos(); }

	private void initAtributos(){
		this.nome = null;
		this.listaDeIntervalos = new ArrayList<Range>();
		this.arrayTransicoes = new ArrayList<Transition>();
		this.classificacao = -1;
	}
	
	/**
	 * Construtor que recebe uma StringMap
	 * @param map	Uma StringMap que contém um estado no formato JSON.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public State(StringMap<?> map){
		initAtributos();
		this.nome = (String) map.get(NOME);
		ArrayList<StringMap> arrayIntervalos = (ArrayList<StringMap>) map.get(LISTA_INTERVALOS);
		for (StringMap<?> sm : arrayIntervalos){
			Range e = new Range(sm);
			listaDeIntervalos.add(e);
		}
		this.classificacao = ((Double) map.get(CLASSIFICACAO)).intValue() ;
	}

	/**
	 * Retorna uma String que representa o estado.
	 * @return	Retorna uma String que representa o estado no formato JSON.
	 */
	public String toJson(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(NOME, nome);
		map.put(CLASSIFICACAO, classificacao);
		ArrayList<String> arrayStringIntervalo = new ArrayList<String>();
		for(Range i : listaDeIntervalos){
			//i.setIdentificadorEvento(this.getClassificacao());//elthon
			arrayStringIntervalo.add(i.toJson());
		}
		map.put(LISTA_INTERVALOS, arrayStringIntervalo.toString());
		return map.toString();
	}

	/**
	 * Retorna o nome do estado.
	 * @return	Uma String contendo o nome do estado.
	 */
	public String getNome() { return nome; }

	/**
	 * Seta o nome do estado.
	 * @param nome	Uma String contendo o nome do estado.
	 */
	public void setNome(String nome) { this.nome = nome; }

	/**
	 * Retorna o valor mínimo do intervalo do estado.
	 * @return	Um float com o valor mínimo do estado.
	 */
	public float getValorMinimo() { 
		if (listaDeIntervalos.size() == 0){
			Range i = new Range();
			listaDeIntervalos.add(i);
		}
		return listaDeIntervalos.get(0).getValorMinimo();
	}

	/**
	 * Seta o valor mínimo do intervalo do estado.
	 * @param valorMinimo	Um float contendo o valor mínimo do intervalo do estado.
	 */
	public void setValorMinimo(float valorMinimo) {
		if (listaDeIntervalos.size() == 0){
			Range i = new Range();
			listaDeIntervalos.add(i);
		}
		listaDeIntervalos.get(0).setValorMinimo(valorMinimo);
	}


	/**
	 * Retorna o valor máximo do intervalo do estado.
	 * @return	Um float com o valor máximo do estado.
	 */
	public float getValorMaximo() { 
		if (listaDeIntervalos.size() == 0){
			Range i = new Range();
			listaDeIntervalos.add(i);
		}
		return listaDeIntervalos.get(0).getValorMaximo();	
	}

	/**
	 * Seta o valor máximo do intervalo do estado.
	 * @param valorMaximo	Um float contendo o valor máximo do intervalo do estado.
	 */
	public void setValorMaximo(float valorMaximo) {
		if (listaDeIntervalos.size() == 0){
			Range i = new Range();
			listaDeIntervalos.add(i);
		}
		listaDeIntervalos.get(0).setValorMaximo(valorMaximo);
	}

	/**
	 * Retorna a classificação do estado.
	 * @return	Um int contendo a classificação do estado.
	 */
	public int getClassificacao() { return classificacao; }

	/**
	 * Retorna a classificação do estado.
	 * @return	Uma String contendo a classificação do estado.
	 */
	public String getClassificacaoString() { 
		switch (classificacao){
		case INT_CL_PERIGOSO: return Internacionalizar.CL_PERIGOSO;
		case INT_CL_ACEITACAO: return Internacionalizar.CL_ACEITACAO;
		case INT_CL_TOLERAVEL: return Internacionalizar.CL_TOLERAVEL;
		default: return Internacionalizar.CL_NENHUM;
		}
	}
	
	/**
	 * Seta a classificação do estado.
	 * @param classificacao
	 */
	public void setClassificacao(int classificacao) { this.classificacao = classificacao; }

	
	/**
	 * Retorna uma lista de intervalos do estado.
	 * @return	Um ArrayList<Intervalo> contendo os intervalos pertencentes ao estado.
	 */
	public ArrayList<Range> getListaIntervalos(){ return listaDeIntervalos; }

	/**
	 * Adiciona uma lista de intervalos no estado.
	 * @param array	ArrayList<Intervalo> contendo os intervalos que serão adicionados.
	 */
	public void addListaIntervalos(ArrayList<Range> array){ listaDeIntervalos.addAll(array); }

	/**
	 * Determina a representação do estado.
	 */
	public String toString(){ return nome; }

	/**
	 * Adiciona uma transição ao estado.
	 * @param transicao		Uma Transicao que tem origem no estado.
	 */
	public void addTransicao(Transition transicao){ arrayTransicoes.add(transicao); }

	/**
	 * Retorna a lista de transições que tem origem no estado.
	 * @return	Um ArrayList<Transicao> com as transições pertencentes ao estado.
	 */
	public ArrayList<Transition> getTransicoes(){ return arrayTransicoes; }

	/**
	 * Retorna a lista de Intervalo do estado.
	 * @return	Um ArrayList<Intervalo> com os intervalos do estado.
	 */
	public ArrayList<Range> getIntervalos(){ return listaDeIntervalos; }


	/**
	 * Verifica se os valores das variáveis estão dentro 
	 * do limite dos intervalos de um estado.
	 * @param map	Um HashMap<Integer, Float> contendo o identificador da 
	 * 					variável e o seu valor, respectivamente.
	 * @return 		Um boolean true se o valor da variável está dentro do 
	 * 					intervalo determinado ou false se não estiver.
	 */
	public boolean verificaIntervalos(HashMap<Integer, Float> map){
		boolean result = true;
		for (Range intervalo : listaDeIntervalos)
			result = result && (intervalo.verificaIntervalo(map)); //elthon .. improved!
		return result;
	}

	/**
	 * Seta o identificador do evento para o processamento do intervalo correspondente.
	 * @param identificador		Um int que representa um evento. 
	 */
	public void setIdentificador(int identificador){
		this.listaDeIntervalos.get(0).setIdentificadorEvento(identificador);
	}

	
	public void addIdentificador(int identificador){
		this.listaDeIntervalos.get(0).setIdentificadorEvento(identificador);
	}

	/**
	 * Retorna o valor do operador relacionado ao valor mínimo.
	 * @return	Um int que representa o operador do valor mínimo
	 */
	public int getOperadorValorMinimo(){
		return this.listaDeIntervalos.get(0).getOperadorValorMinimo();
	}

	/**
	 * Retorna o operador relacionado ao valor mínimo.
	 * @return Uma String contendo o operador do valor mínimo.
	 */
	public String getOperadorValorMinimoString(){
		if (this.listaDeIntervalos.get(0).getOperadorValorMinimo() == Range.MENOR_QUE) return "(";
		else return "[";
	}

	/**
	 * Retorna o operador relacionado ao valor máximo.
	 * @return Uma String contendo o operador do valor máximo.
	 */
	public String getOperadorValorMaximoString(){
		if (this.listaDeIntervalos.get(0).getOperadorValorMaximo() == Range.MENOR_QUE) return ")";
		else return "]";
	}

	/**
	 * Seta o operador do valor mínimo.
	 * @param operador		Um int que representa o operador do valor mínimo.
	 */
	public void setOperadorValorMinimo(int operador){
		this.listaDeIntervalos.get(0).setOperadorValorMinimo(operador);
	}

	/**
	 * Retorna o operador relacionado ao valor máximo.
	 * @return	Um int que representa o operador do valor máximo.
	 */
	public int getOperadorValorMaximo(){
		return this.listaDeIntervalos.get(0).getOperadorValorMaximo();
	}

	/**
	 * Seta o operador do valor máximo.
	 * @param operador		Um int que representa o operador do valor máximo.
	 */
	public void setOperadorValorMaximo(int operador){
		this.listaDeIntervalos.get(0).setOperadorValorMaximo(operador);
	}
	
	public void addRange(Range r){ this.listaDeIntervalos.add(r); }
}