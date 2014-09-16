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
package br.edu.ufcg.supervisor.desktop.util;


import java.util.HashMap;
import java.util.Vector;

import br.edu.ufcg.supervisor.model.Attribute;

/**
 * Classe auxiliar responsável pelos atributos pré-configurados/adicionados. 
 * @author Marcos José, Elthon Oliveira
 *
 */
public class AttributeList {
	private static Vector<String> arrayNomeAtributo = new Vector<String>();	//{"Batimentos Cardiácos", "Temperatura Corporal"};
	private static Vector<Integer> arrayTipoAtributo = new Vector<Integer>();
	private static Vector<String> arrayUnidadeAtributo = new Vector<String>();	//{"bpm", "ºC"};
	private static Vector<String> arrayClassificacaoEstado = new Vector<String>();

	private static HashMap<String, Integer> mapAtributoID  = new HashMap<String, Integer>(); 
	private static HashMap<String,String > mapIDAtributo  = new HashMap<String,String>(); 
	
	static {
		preencherArrays(i16o.label("AT_HEART_RATE"),Attribute.ID_HEART_RATE, Attribute.PHYSIOLOGICAL,"bpm");
		preencherArrays(i16o.label("AT_RESPIRATORY_EXCHANGE_RATIO"),Attribute.ID_RESPIRATORY_EXCHANGE_RATIO,Attribute.PHYSIOLOGICAL,"VCO2/VO2");
		preencherArrays(i16o.label("AT_SYSTOLIC_BLOOD_PRESSURE"),Attribute.ID_SYSTOLIC_BLOOD_PRESSURE,Attribute.PHYSIOLOGICAL,"mmHg");
		preencherArrays(i16o.label("AT_DIASTOLIC_BLOOD_PRESSURE"),Attribute.ID_DIASTOLIC_BLOOD_PRESSURE,Attribute.PHYSIOLOGICAL,"mmHg");
		preencherArrays(i16o.label("AT_BODY_TEMPERATURE"),Attribute.ID_BODY_TEMPERATURE,Attribute.PHYSIOLOGICAL, "ºC");
		preencherArrays(i16o.label("AT_BLOOD_LACTATE"),Attribute.ID_BLOOD_LACTATE,Attribute.PHYSIOLOGICAL,"[La-]b");
		preencherArrays(i16o.label("AT_BLOOD_GLUCOSE"),Attribute.ID_BLOOD_GLUCOSE,Attribute.PHYSIOLOGICAL,"md/dl");
		preencherArrays(i16o.label("AT_OXIGEN_CONSUMPTION"),Attribute.ID_OXIGEN_CONSUMPTION, Attribute.PHYSIOLOGICAL,"VO2");
		preencherArrays(i16o.label("AT_AMBIENT_PRESSURE"),Attribute.ID_AMBIENT_PRESSURE,Attribute.ENVIRONMENTAL,"atm");
		preencherArrays(i16o.label("AT_ENVIRONMENTAL_TEMPERATURE"),Attribute.ID_ENVIRONMENTAL_TEMPERATURE,Attribute.ENVIRONMENTAL,"ºC");
		preencherArrays(i16o.label("AT_AIR_RELATIVE_HUMIDITY"),Attribute.ID_AIR_RELATIVE_HUMIDITY,Attribute.ENVIRONMENTAL,"%");
		preencherArrays(i16o.label("AT_SPEED"),Attribute.ID_SPEED,Attribute.BEHAVIORAL,"m/s");
		arrayClassificacaoEstado.add(i16o.label("CL_TOLERAVEL"));//posição 1
		arrayClassificacaoEstado.add(i16o.label("CL_PERIGOSO"));//posição 2
		arrayClassificacaoEstado.add(i16o.label("CL_ACEITACAO"));//posição 3
	}
	
	public static String getAtributoDoId(String sId){ return mapIDAtributo.get(sId); }
	
	public static Integer getIdDoAtributo(String nomeAtributo){ return mapAtributoID.get(nomeAtributo);	}
	
	public static Vector<String> getArrayNomeAtributo(){ return arrayNomeAtributo; }
	
	public static Vector<Integer> getArrayTipoAtributo(){ return arrayTipoAtributo; }
	
	public static Vector<String> getArrayUnidadeAtributo(){	return arrayUnidadeAtributo; }
	
	public static Vector<String> getArrayClassificacaoEstado(){ return arrayClassificacaoEstado; }
	
	private static void preencherArrays(String nomeAtributo, int id, Integer tipoAtributo, String unidadeAtributo){
		arrayNomeAtributo.add(nomeAtributo);
		arrayTipoAtributo.add(tipoAtributo);
		arrayUnidadeAtributo.add(unidadeAtributo);
		mapAtributoID.put(nomeAtributo, id);
		mapIDAtributo.put("" + id, nomeAtributo);
	}
	
	/**
	 * Retorna o tipo do atributo.
	 * @return	Uma String contendo o tipo do atributo.
	 */
	public static String getTipoAtributoString(int tipoAtributo) {
		switch (tipoAtributo){
			case (Attribute.ENVIRONMENTAL):
				return i16o.label("TIPO_ATRIBUTO_AMBIENTAL");
			case (Attribute.BEHAVIORAL):
				return i16o.label("TIPO_ATRIBUTO_COMPORTAMENTAL");
			default:
				return i16o.label("TIPO_ATRIBUTO_FISIOLOGICO");
			}
	}
}