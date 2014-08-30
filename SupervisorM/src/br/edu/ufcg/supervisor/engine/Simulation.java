/**
 * Copyright 2013-2014 Marcos Ferreira and Elthon Oliveira
 * 
 * This file is part of Supervisor for Healthcare Professional software.
 * 
 *  Supervisor for Healthcare Professional is free software: you can 
 *  redistribute it and/or modify it under the terms of the GNU General 
 *  Public License as published by the Free Software Foundation, either 
 *  version 3 of the License, or (at your option) any later version.
 *  
 *  Supervisor for Healthcare Professional is distributed in the hope that
 *  it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 *  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See 
 *  the GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with Supervisor for Healthcare Professional. 
 *  Contact: el7hon at gmail dot com
 *  If not, see <http://www.gnu.org/licenses/>.
 *  
 *  Contact: el7hon at gmail dot com
*/
package br.edu.ufcg.supervisor.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import br.edu.ufcg.supervisor.desktop.util.AttributeList;
import br.edu.ufcg.supervisor.model.*;

/**
 * Tela na qual o usuário pode simular o funcionamento da aplicação com os sensores. 
 * @author Marcos José
 *
 */
public class Simulation {
	private Automaton automaton;
	private HashMap<Integer,Float> map;
	private int[] arrayIds;
	//private List<String> nomes;// = new ArrayList<String>();

	/** Chamado quando a activity é criada. */
	public void onCreate() {
		automaton = LoadedModel.getModelo();
		initMap();
	}

	private void imprimeMap(){
		StringBuffer sMap = new StringBuffer("");
		for (int i : arrayIds){
			sMap.append(AttributeList.getAtributoDoId("" + i)+ ": " + map.get(i) + "\n");
		}
	}	
	
	private void initMap(){
		map = new HashMap<Integer,Float>();
		State e = automaton.getArrayEstadosAceitos().get(0);
		for (Range i : e.getIntervalos()){
			map.put(i.getIdentificadorEvento(), getMediaDoIntervalo(i));
		}
		imprimeMap();
	}

	private float getMediaDoIntervalo(Range i){
		return ( i.getValorMaximo() + i.getValorMinimo() )/2;
	}

	private void executa(){
		String mensagem = "";
		ArrayList<String> arrayMensagens = new ArrayList<String>();
		State estado;
		try {
			estado = automaton.buscaEstadoCorrespondente(map);
			if (! (estado.getClassificacao() == State.INT_CL_ACEITACAO) ) {//verifica se E Qm, caso não chama o algoritmo
				Search alg = new Search(automaton);
				alg.execute(estado);
				for (State estadoAceito : automaton.getArrayEstadosAceitos()){
					LinkedList<State> caminho = alg.getPath(estadoAceito);
					if (caminho != null){
						for (int j=0; j<caminho.size()-1;j++) {
							mensagem += (j+1)+ ". " + automaton.getMensagemDasTransicoesEntreDoisEstadosQuaisquer(caminho.get(j),caminho.get(j+1) )+ " ";
						}
						arrayMensagens.add(mensagem);
					}
				}
			}
			getCaminhoMaisCurto(arrayMensagens);
			arrayMensagens.size();
			estado.getNome();
			estado.getClassificacaoString();
		} catch (Exception e) { e.printStackTrace(); }
	}

	private String getCaminhoMaisCurto(ArrayList<String> array){
		if (array.size() == 0){	return ""; }
		int minimo = array.get(0).split(".").length;
		int qtd;
		int indexMenorCaminho = 0;
		for(int i = 1; i < array.size(); i++){
			qtd = array.get(i).split(".").length;
			if (qtd < minimo){
				minimo = qtd;
				indexMenorCaminho = i;
			}
		}
		return array.get(indexMenorCaminho);
	}
}