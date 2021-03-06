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

import org.json.JSONObject;

import br.edu.ufcg.supervisor.desktop.util.AttributeList;
import br.edu.ufcg.supervisor.model.*;

/**
 * Tela na qual o usuário pode simular o funcionamento da aplicação com os sensores. 
 * @author Marcos José
 *
 */
public class Simulation {
	private static Automaton automaton;
	private static HashMap<Integer,Float> map;
	private static int[] arrayIds;

	public static void start() {
		automaton = LoadedModel.getModel();
		initMap();
	}
	
	private static void initMap(){
		map = new HashMap<Integer,Float>();
		State e = automaton.getArrayEstadosAceitos().get(0);
		for (Range i : e.getIntervalos()){
			map.put(i.getIdentificadorEvento(), getMediaDoIntervalo(i));
		}
		imprimeMap();
	}
	
	private static void imprimeMap(){
		StringBuffer sMap = new StringBuffer("");
		for (int i : arrayIds){
			sMap.append(AttributeList.getAtributoDoId("" + i)+ ": " + map.get(i) + "\n");
		}
	}
	
	private static float getMediaDoIntervalo(Range i){ return ( i.getValorMaximo() + i.getValorMinimo() )/2; }
	
	public static void executeModel(JSONObject r, Automaton model, HashMap<String, Float> map1, 
			HashMap<Integer, Float> map2, String currentState, String recommendation, String logString) throws Exception{
		
		ArrayList<String> names = LoadedModel.getNomesVariaveisMonitoradas();
		ArrayList<String> arrayMensagens = new ArrayList<String>();
		for (int i = 0; i < map1.size(); i++) { currentState = currentState+"- "+names.get(i)+": "+map1.get(names.get(i))+".<br>"; }

		logString = logString + currentState + " - ";
		r.put("cur",currentState);
		
		State estado = model.buscaEstadoCorrespondente(map2);
		if (!(estado.getClassificacao() == State.INT_CL_ACEITACAO)){
			Search alg = new Search(model);
			alg.execute(estado);
			for (State estadoAceito : model.getArrayEstadosAceitos()){
				LinkedList<State> caminho = alg.getPath(estadoAceito);
				if (caminho != null){
					for (int j=0; j<caminho.size()-1;j++)
						recommendation += "."+model.getMensagemDasTransicoesEntreDoisEstadosQuaisquer(caminho.get(j),caminho.get(j+1));//elthon
					arrayMensagens.add(recommendation);
				}
			}
			recommendation = getShortestPath(arrayMensagens);
			recommendation = eliminateReplicatedRecommendations(recommendation);
			if (recommendation.equals(".")) recommendation = "Some variable has not been measured!";
			logString = logString + recommendation +"\n";
		} else {
			recommendation = "Keep going!";
			logString = logString + "("+recommendation+")\n";
		}
		r.put("rec",recommendation);
	}	
	
	private static String getShortestPath(ArrayList<String> array){
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
	
	private static String eliminateReplicatedRecommendations(String rec){
		String result = "";
		rec = rec.replaceFirst(".", "");
		String[] temp = rec.split("\\.");
		for (int i = 0; i < temp.length; i++) {
			if (!result.contains(temp[i])) result = result + ", " + temp[i];
		}
		return result.replaceFirst(", ","") +".";		
	}
}