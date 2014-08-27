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

/**
 * Classe responsável por representar uma variável que pode ser monitorada. 
 * @author Elthon Oliveira, Marcos José 
 *
 */
public class Attribute {
	
	//TODO ESSES VALORES SÃO DETERMINADOS PELA POSIÇÃO DESSES CAMPOS NA GUI
	public static final int AMBIENTAL = 0; 
	public static final int FISIOLOGICO = 1;
	public static final int COMPORTAMENTAL = 2;
	

	public static final int ID_HEART_RATE 					= 1;
	public static final int ID_RESPIRATORY_EXCHANGE_RATIO 	= 2;
	public static final int ID_SYSTOLIC_BLOOD_PRESSURE 		= 3;
	public static final int ID_DIASTOLIC_BLOOD_PRESSURE 	= 4;
	public static final int ID_BODY_TEMPERATURE 			= 5;
	public static final int ID_BLOOD_LACTATE 				= 6;
	public static final int ID_BLOOD_GLUCOSE 				= 7;
	public static final int ID_OXIGEN_CONSUMPTION 			= 8; 
	public static final int ID_AMBIENT_PRESSURE				= 9;
	public static final int ID_AIR_RELATIVE_HUMIDITY 		= 10;
	public static final int ID_SPEED 						= 11;
	
	public static final int ID_ENVIRONMENTAL_TEMPERATURE	= 12;

	public static final int ID_VELOCITY 					= 13;
	
	private Automaton automato;
	private String nome;
	private String unidadeDeMedida;
	private int tipo;
	private int frequenciaDeLeitura;
	
	/**
	 * Construtor padrão.
	 */
	public Attribute(){
		nome = new String();
		unidadeDeMedida = new String();
		frequenciaDeLeitura = 12;
		automato = new Automaton();
		this.tipo = FISIOLOGICO;
	}

	/**
	 * Retorna o automato.
	 * @return	Um Automato.
	 */
	public Automaton getAutomato() {
		this.automato.setNome(this.nome);
		return this.automato;
	}

	/**
	 * Seta o automato do atribito.
	 * @param automato	Um automato contendo a especificação.
	 */
	public void setAutomato(Automaton automat) {
		this.automato = automat;
	}

	/**
	 * Retorna o nome do atributo. 
	 * @return	Uma String contendo o nome.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Seta o nome do atributo.
	 * @param nome	Uma String contendo o nome do atributo. 
	 */
	public void setNome(String nome) {
		this.nome = nome;
		this.automato.setNome(nome);
	}

	/**
	 * Retorna a unidade de medida do atributo.
	 * @return	Uma String contendo a unidade de medida.
	 */
	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	/**
	 * Seta a unidade de medida.
	 * @param unidadeDeMedida	Uma String contendo a unidade de medida. 
	 */
	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	/**
	 * Retorna a frequência de leitura.
	 * @return	Um int contendo a frequência de leitura.
	 */
	public int getFrequenciaDeLeitura() {
		return frequenciaDeLeitura;
	}

	/**
	 * Seta a frequência de leitura.
	 * @param frequenciaDeLeitura	Um int contendo a frequência de leitura.
	 */
	public void setFrequenciaDeLeitura(int frequenciaDeLeitura) {
		this.frequenciaDeLeitura = frequenciaDeLeitura;
	}

	/**
	 * Retorna o tipo do atributo.
	 * @return	Um int contendo o tipo do atributo.
	 */
	public int getTipo() {
		return tipo;
	}
	
	/**
	 * Seta o tipo do atributo.
	 * @param tipo	Um int representando o tipo do atributo.
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}	
