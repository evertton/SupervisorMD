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

import java.util.HashMap;

import com.google.gson.internal.StringMap;

/**
 * Classe que representa o intervalo de valores de um estado de uma variável 
 * @author Elthon Oliveira, Marcos José 
 */
public class Range {
	public static final String IDENTIFICADOR = "0";//"identificador";
	public static final String VALOR_MAXIMO = "1";//"valorMaximo";
	public static final String VALOR_MINIMO = "2";//"valorMinimo";
	public static final String OPERADOR_VALOR_MAXIMO = "3";//"operadorValorMaximo";
	public static final String OPERADOR_VALOR_MINIMO = "4";//"operadorValorMinimo";
	public static final int MENOR_QUE = 1;//Operador.MenorQue.getNumero();
	public static final int MENOR_OU_IGUAL_A = 0;//Operador.MenorOuIgual.getNumero();

	private float valorMinimo;
	private float valorMaximo;
	private int identificadorEvento;
	private int operadorValorMinimo;
	private int operadorValorMaximo;
	/**
	 * Construtor padrão.
	 */
	public Range(){
		this.identificadorEvento = -1;
		this.valorMinimo = -1;
		this.valorMaximo = -1;
		this.operadorValorMinimo = MENOR_QUE;
		this.operadorValorMaximo = MENOR_QUE;
	}

	/**
	 * Construtor.
	 * @param valorMinimo	Um float contendo o valor mínimo.
	 * @param valorMaximo	Um float contendo o valor máximo.
	 * @param identificador	Um int contendo um identificador 
	 * para relacionar o intervalo com um evento.
	 */
	public Range(float valorMinimo, float valorMaximo, int identificador, 
			int operadorValorMinimo, int operadorValorMaximo){ 
		this.identificadorEvento = identificador;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.operadorValorMinimo = operadorValorMinimo;
		this.operadorValorMaximo = operadorValorMaximo;
	}

	/**
	 * Construtor.
	 * @param map	Uma StringMap contendo um intervalo no formato JSON.
	 */
	 //TODO COMPLETAR
	public Range(StringMap<?> map){
		this.identificadorEvento = ((Double) map.get(IDENTIFICADOR)).intValue();
		this.valorMinimo = ((Double) map.get(VALOR_MINIMO)).floatValue();
		this.valorMaximo = ((Double) map.get(VALOR_MAXIMO)).floatValue();
		this.operadorValorMinimo = ((Double) map.get(OPERADOR_VALOR_MINIMO)).intValue();
		this.operadorValorMaximo = ((Double) map.get(OPERADOR_VALOR_MAXIMO)).intValue();
	}

	/**
	 * Retorna o valor mínimo do intervalo. 
	 * @return	Um float contendo o valor mínimo do intervalo. 
	 */
	public float getValorMinimo() {
		return valorMinimo;
	}

	/**
	 * Seta o valor mínimo do intervalo.
	 * @param valorMinimo	Um float contendo o valor mínimo do intervalo.
	 */
	public void setValorMinimo(float valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	/**
	 * Retorna o valor máximo do intervalo.
	 * @return	Um float contendo o valor máximo do intervalo.
	 */
	public float getValorMaximo() {
		return valorMaximo;
	}

	/**
	 * Seta o valor máximo do intervalo.
	 * @param valorMaximo	Um float contendo o valor máximo do intervalo.
	 */
	public void setValorMaximo(float valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	/**
	 * Retorna um int que identifica o evento que o intervalo está relacionado.
	 * @return Um int contendo o identificador do evento que o intervalo está relacionado.
	 */
	public int getIdentificadorEvento() {
		return identificadorEvento;
	}

	/**
	 * Retorna o operador mínimo.
	 * @return	Um int reprensentando o operador do valor mínimo. 
	 */
	public int getOperadorValorMinimo(){
		return this.operadorValorMinimo;
	}
	
	/**
	 * Seta o identificador do evento que o intervalo está relacionaldo.
	 * @param identificadorEvento	Um int que identifica a qual evento o intervalo está relacionado.
	 */
	public void setIdentificadorEvento(int identificadorEvento) {
		this.identificadorEvento = identificadorEvento;
	}
	
	
	/**
	 * Retorna o operador máximo.
	 * @return	Um int reprensentando o operador do valor máximo. 
	 */
	public int getOperadorValorMaximo(){
		return this.operadorValorMaximo;
	}
	
	/**
	 * Seta o operador do valor mínimo.
	 * @param operador	Um int contendo a posição do operador.
	 */
	public void setOperadorValorMinimo(int operador){
		this.operadorValorMinimo = operador;//getOperador(operador); 
	}
	
	/**
	 * Seta o operador do valor máximo.
	 * @param operador	Um int contendo a posição do operador.
	 */
	public void setOperadorValorMaximo(int operador){
		this.operadorValorMaximo = operador;//getOperador(operador); 
	}
	

	/**
	 * Retorna uma String contendo o intervalo.
	 * @return Uma String contendo o intervalo no formato JSON.
	 */
	public String toJson(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(IDENTIFICADOR, identificadorEvento);
		map.put(VALOR_MAXIMO, valorMaximo);
		map.put(VALOR_MINIMO, valorMinimo);
		map.put(OPERADOR_VALOR_MINIMO, operadorValorMinimo);
		map.put(OPERADOR_VALOR_MAXIMO, operadorValorMaximo);
		return map.toString();
	}

	/**
	 * Retorna uma String que identifica o intervalo.
	 */
	public String toString(){
		return identificadorEvento + " " + valorMinimo + " " + valorMaximo;
	}
	
	/**
	 * Verifica se o valor de determinada variável está dentro 
	 * do limite determinado no intervalo de um estado.
	 * @param map	Um HashMap<Integer, Float> contendo o identificador da 
	 * variável e o seu valor, respectivamente.
	 * @return Um boolean true se o valor da variável está dentro do 
	 * intervalo determinado ou false se não estiver.
	 */
	public boolean verificaIntervalo(HashMap<Integer, Float> map){
		boolean ret = false;
		boolean proposition1 = false;
		boolean proposition2 = false;
		Float f = map.get(identificadorEvento);
		if (f!= null){
			float val = f.floatValue();
			if (operadorValorMinimo == MENOR_QUE) proposition1 = valorMinimo < val;
			else proposition1 = valorMinimo <= val;
			if (operadorValorMaximo == MENOR_QUE) proposition2 = val < valorMaximo;
			else proposition2 = val <= valorMaximo;
			ret = (proposition1 && proposition2);
			return ret;
		} else return true;
	}
}