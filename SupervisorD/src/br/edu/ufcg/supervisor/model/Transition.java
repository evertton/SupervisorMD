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
 */
package br.edu.ufcg.supervisor.model;

import java.util.HashMap;
import java.util.Vector;

import com.google.gson.internal.StringMap;
/**
 * Classe que representa a trasição do autômato. 
 * @author Elthon Oliveira, Marcos José 
 *
 */
public class Transition {
	public static final String MENSAGEM = "0";//"mensagem";
	public static final String ROTULO = "1";//"rotulo";
	public static final String POSICAO_ESTADO_INICIAL = "2";// "posicaoDoEstadoInicial";
	public static final String POSICAO_ESTADO_FINAL = "3";//"posicaoDoEstadoFinal";

	private String rotulo;
	private String mensagem;
	private State estadoOrigem = null;
	private State estadoDestino = null;
		
	/**
	 * Contrutor padrão.
	 */
	public Transition(){ init(); }

	/*
	public Transicao(Vector<Estado> listaEstado, String transicaoJson){
		initAtributos();
		System.out.println("Transicao1 String");
		
		HashMap<String,Object> map = (HashMap<String,Object>) ControladorGson.getGson()
				.fromJson(transicaoJson, HashMap.class);

		this.rotulo = (String) map.get(NOME);
		this.mensagem = (String) map.get(MENSAGEM);
		int posicaoEstadoFinal = ((Double) map.get(POSICAO_ESTADO_FINAL)).intValue();
		int posicaoEstadoInicial = ((Double) map.get(POSICAO_ESTADO_INICIAL)).intValue();

		//TODO OBS
		this.estadoDestino = listaEstado.get(posicaoEstadoFinal);
		this.estadoOrigem = listaEstado.get(posicaoEstadoInicial);
	}
	*/

	/**
	 * Construtor.
	 * @param listaEstado	Um Vector<Estado> com a lista de estados de um automato.
	 * @param stringMap		Uma StringMap que contém uma transição no formato JSON.
	 */
	public Transition(Vector<State> listaEstado, StringMap<Object> stringMap){
		init();
		this.mensagem = (String) stringMap.get(MENSAGEM);
		//this.rotulo = (String) stringMap.get(ROTULO);// TODO COMPLICAR
		int posicaoEstadoFinal = ((Double) stringMap.get(POSICAO_ESTADO_FINAL)).intValue();
		int posicaoEstadoInicial = ((Double) stringMap.get(POSICAO_ESTADO_INICIAL)).intValue();
		this.estadoDestino = listaEstado.get(posicaoEstadoFinal);
		this.estadoOrigem = listaEstado.get(posicaoEstadoInicial);
	}

	/**
	 * Converte a transição numa String no formato JSON.
	 * @param vector 	Vetor de estado para setar a posição.
	 * @return 			Uma String contendo os atributos da transição.
	 */
	public String toJson(Vector<State> vector){
		//Nao usar Gson pois nem todos os atributos serão passados
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put(ROTULO,rotulo); // TODO COMPLICAR
		map.put(MENSAGEM, mensagem);
		map.put(POSICAO_ESTADO_INICIAL, vector.indexOf(estadoOrigem));
		map.put(POSICAO_ESTADO_FINAL, vector.indexOf(estadoDestino));
		return map.toString();
	}

	/**
	 * Retorna o rôtulo da transição.
	 * @return 		Uma String contendo o rôtulo. 
	 */
	public String getRotulo() { return rotulo; }

	/**
	 * Seta o rôtulo da transição.
	 * @param rotulo	Uma String contendo o rôtulo da transição.
	 */
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	/**
	 * Retorna a mensagem da transição.
	 * @return		Uma String contendo a mensagem da transição.
	 */
	public String getMensagem() { return mensagem; }

	/**
	 * Seta a mensagem da transição.
	 * @param mensagem	Uma String contendo a mensagem da transição.
	 */
	public void setMensagem(String mensagem){
		this.mensagem = mensagem;
	}

	/**
	 * Retorna o estado de origem da transição.	 
	 * @return		Um Estado que é a origem da transição. 
	 */
	public State getEstadoOrigem() { return estadoOrigem; }

	/**
	 * Seta o estado de origem da transição.	
	 * @param estadoOrigem		O Estado origem da transição. 
	 */
	public void setEstadoOrigem(State estadoOrigem) {
		this.estadoOrigem = estadoOrigem;
	}

	/**
	 * Retorna o estado destino da transição.
	 * @return		O Estado destino da transição.
	 */
	public State getEstadoDestino() { return estadoDestino; }

	/**
	 * Seta o estado final da transição.
	 * @param estadoDestino	O estado final da transição.
	 */
	public void setEstadoDestino(State estadoDestino) {
		this.estadoDestino = estadoDestino;
	}

	/**
	 * Utilizado para representar a transição.
	 */
	public String toString(){
		return rotulo + " " + estadoOrigem.getNome() + " " + estadoDestino.getNome(); 
	}
	
	/**
	 * Vincula a transição com o estado de origem.
	 */
	public void addTransicaoNoEstadoOrigem(){
		estadoOrigem.addTransicao(this);
	}

	/*
	 * Utilizado para iniciar os atributos da transição.
	 */
	private void init(){
		this.estadoDestino = null;
		this.estadoOrigem = null;
		this.rotulo = null;
		this.mensagem = null;
	}

	/**
	 * Utilizado para comparar transições.
	 */
	@Override
	public boolean equals(Object t){
		if (rotulo.equals(((Transition)t).getRotulo()) && 
				estadoOrigem.getNome().equals(((Transition)t).getEstadoOrigem().getNome()) &&
				estadoDestino.getNome().equals(((Transition)t).getEstadoDestino().getNome())){
			return true;
		}
		return false;
	}

}

