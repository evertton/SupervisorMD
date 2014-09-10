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

/**
 * Classe responsável por representar uma variável que pode ser monitorada. 
 * @author Elthon Oliveira, Marcos José 
 *
 */
public class Attribute {
	
	//TODO ESSES VALORES SÃO DETERMINADOS PELA POSIÇÃO DESSES CAMPOS NA GUI
	public static final int ENVIRONMENTAL = 0; 
	public static final int PHYSIOLOGICAL = 1;
	public static final int BEHAVIORAL = 2;
	
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
	public static final int ID_SPEED						= 11;
	public static final int ID_ENVIRONMENTAL_TEMPERATURE	= 12;
	public static final int ID_VELOCITY 					= 13;
	
	private Automaton automaton;
	private String name;
	private String unitOfMeasure;
	private int type;
	private int readingRate;
	
	/**
	 * Construtor padrão.
	 */
	public Attribute(){
		name = "";
		unitOfMeasure = "";
		readingRate = 12;
		automaton = new Automaton();
		this.type = PHYSIOLOGICAL;
	}

	/**
	 * Retorna o automaton.
	 * @return	Um Automato.
	 */
	public Automaton getAutomaton() {
		this.automaton.setNome(this.name);
		return this.automaton;
	}

	/**
	 * Seta o automaton do atribito.
	 * @param automaton	Um automaton contendo a especificação.
	 */
	public void setAutomaton(Automaton automat) { this.automaton = automat; }

	/**
	 * Retorna o name do atributo. 
	 * @return	Uma String contendo o name.
	 */
	public String getName() { return this.name; }

	/**
	 * Seta o name do atributo.
	 * @param name	Uma String contendo o name do atributo. 
	 */
	public void setName(String nome) {
		this.name = nome;
		this.automaton.setNome(nome);
	}

	/**
	 * Retorna a unidade de medida do atributo.
	 * @return	Uma String contendo a unidade de medida.
	 */
	public String getUnitOfMeasure() { return unitOfMeasure; }

	/**
	 * Seta a unidade de medida.
	 * @param unitOfMeasure	Uma String contendo a unidade de medida. 
	 */
	public void setUnitOfMeasure(String unidadeDeMedida) { this.unitOfMeasure = unidadeDeMedida; }

	/**
	 * Retorna a frequência de leitura.
	 * @return	Um int contendo a frequência de leitura.
	 */
	public int getReadingRate() { return readingRate; }

	/**
	 * Seta a frequência de leitura.
	 * @param readingRate	Um int contendo a frequência de leitura.
	 */
	public void setReadingRate(int frequenciaDeLeitura) {
		this.readingRate = frequenciaDeLeitura;
	}

	/**
	 * Retorna o type do atributo.
	 * @return	Um int contendo o type do atributo.
	 */
	public int getType() { return type; }
	
	/**
	 * Seta o type do atributo.
	 * @param type	Um int representando o type do atributo.
	 */
	public void setType(int tipo) { this.type = tipo; }
}	