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

import java.util.ArrayList;
//import java.util.Vector;

import br.edu.ufcg.supervisor.model.Attribute;
import br.edu.ufcg.supervisor.model.Automaton;
import br.edu.ufcg.supervisor.model.Range;
import br.edu.ufcg.supervisor.model.State;
import br.edu.ufcg.supervisor.model.Transition;

/**
 * Classe auxiliar para preencher os autômatos com base na tese e com mais estados e transições.  
 * @author Marcos José, Elthon Oliveira
 */
public class AutomataFiller {

	public static void preencheSujeito(int i, ArrayList<Attribute> arrayAtributos){
		fillCommonAttributes(arrayAtributos);
		switch (i) {
		case 1:
			preenche1Ellen(arrayAtributos);
			break;
		case 2:
			preenche2Isadora(arrayAtributos);
			break;
		case 3:
			preenche3Clovis(arrayAtributos);
			break;
		case 4:
			preenche4Wanne(arrayAtributos);
			break;
		case 5:
			preenche5Carlos(arrayAtributos);
			break;
		case 6:
			preenche6Filipe(arrayAtributos);
			break;
		case 7:
			preenche7Matheus(arrayAtributos);
			break;
		case 8:
			preenche8Anderson(arrayAtributos);
			break;
		case 9:
			preenche9Jose(arrayAtributos);
			break;
		case 10:
			preenche10Denise(arrayAtributos);
			break;
		case 11:
			preenche11Tati(arrayAtributos);
			break;
		case 12:
			preenche12Ge(arrayAtributos);
			break;
		default:
			break;
		}
	}
	
	private static void preenche1Ellen(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,6,6,11,11,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,100,100,140,140,200);
	}
	
	private static void preenche2Isadora(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,5,5,7,7,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,100,100,130,130,200);
	}
	
	private static void preenche3Clovis(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,4,4,6.5f,6.5f,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,110,110,130,130,200);
	}
	
	private static void preenche4Wanne(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,4,4,6.5f,6.5f,20);
		fillDistance(arrayAtributos, 0,1.5f,1.5f,1.6f,1.6f,10);
		fillHeartRate(arrayAtributos, 60,110,110,130,130,200);
	}
	
	private static void preenche5Carlos(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,5.5f,5.5f,8,8,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,110,110,150,150,200);
	}
	
	private static void preenche6Filipe(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,5.5f,5.5f,8,8,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,100,100,145,145,200);
	}
	
	private static void preenche7Matheus(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,6,6,10,10,20);
		fillDistance(arrayAtributos, 0,2.5f,2.5f,2.6f,2.6f,10);
		fillHeartRate(arrayAtributos, 60,120,120,160,160,200);
	}
	
	private static void preenche8Anderson(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,6,6,10,10,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,115,115,145,145,200);
	}
	
	private static void preenche9Jose(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,6,6,10,10,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,120,120,160,160,200);
	}
	
	private static void preenche10Denise(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,7,7,12,12,20);
		fillDistance(arrayAtributos, 0,2.2f,2.2f,2.3f,2.3f,10);
		fillHeartRate(arrayAtributos, 60,135,135,170,170,200);
	}
	
	private static void preenche11Tati(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,7,7,12,12,20);
		fillDistance(arrayAtributos, 0,2,2,2.1f,2.1f,10);
		fillHeartRate(arrayAtributos, 60,135,135,165,165,200);
	}
	
	private static void preenche12Ge(ArrayList<Attribute> arrayAtributos){
		fillSpeed(arrayAtributos, 0,6,6,10,10,20);
		fillDistance(arrayAtributos, 0,1.8f,1.8f,2,2,10);
		fillHeartRate(arrayAtributos, 60,130,130,155,155,200);
	}
		
	/**
	 * Fill information common to all subjects 
	 * @param arrayAtributos
	 */
	private static void fillCommonAttributes(ArrayList<Attribute> arrayAtributos){
		fillEnvironmentalTemperature(arrayAtributos, 0, 19, 19, 29, 29, 40);
		fillAirRelativeHumidity(arrayAtributos, 0, 30, 30, 60, 60, 100);
		fillBodyTemperature(arrayAtributos, 30, 36.5f, 36.5f, 40, 40, 41.5f, 41.4f, 50);
	}

	/**
	 * Model used at tests on field
	 * @param arrayAtributos
	 */
	private static void fillSpeed(ArrayList<Attribute> arrayAtributos, 
			float i1,float i2,float j1,float j2,float k1,float k2){
		
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_SPEED"));
		atributo.setType(Attribute.BEHAVIORAL);
		atributo.setUnitOfMeasure("km/h");
		int identificador = Attribute.ID_SPEED;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_SPEED"));

		State lenta = createEstado("lenta", i1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, i2, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(lenta);
		
		State boa = createEstado("boa", j1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, j2, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(boa);

		State rapida = createEstado("rapida", k1, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, k2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(rapida);

		Transition lenta_boa = createTransicao("is", lenta, boa, "Increase_speed.");
		aI.addTransicao(lenta_boa);

		Transition boa_lenta = createTransicao("ds", boa, lenta, "Decrease_speed.");
		aI.addTransicao(boa_lenta);
		
		Transition boa_rapida = createTransicao("is", boa, rapida, "Increase_speed.");
		aI.addTransicao(boa_rapida);
		
		Transition rapida_boa = createTransicao("ds", rapida, boa, "Decrease_speed.");
		aI.addTransicao(rapida_boa);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);		
	}
	
	/**
	 * Model used at tests on field
	 * @param arrayAtributos
	 */
	private static void fillDistance(ArrayList<Attribute> arrayAtributos, 
			float i1,float i2,float j1,float j2,float k1,float k2){

		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_DISTANCE"));
		atributo.setType(Attribute.BEHAVIORAL);
		atributo.setUnitOfMeasure("km");
		int identificador = Attribute.ID_DISTANCE;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_DISTANCE"));

		State inicio = createEstado("inicio", i1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, i2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(inicio);
		
		State prox = createEstado("prox", j1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, j2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(prox);

		State fim = createEstado("fim", k1, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, k2, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(fim);

		Transition inicio_prox = createTransicao("id", inicio, prox, "Keep_jogging.");
		aI.addTransicao(inicio_prox);

		Transition prox_fim = createTransicao("id", prox, fim, "Keep_jogging.Almost_there.");
		aI.addTransicao(prox_fim);
		
		Transition rapida_rapida = createTransicao("stop", fim, fim, "Just_walk.");
		aI.addTransicao(rapida_rapida);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	/**
	 * Model used at tests on field
	 * @param arrayAtributos
	 */
	private static void fillHeartRate(ArrayList<Attribute> arrayAtributos, 
			float i1,float i2,float j1,float j2,float k1,float k2){
		
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_HEART_RATE"));
		atributo.setType(Attribute.PHYSIOLOGICAL);
		atributo.setUnitOfMeasure("bpm");
		int identificador = Attribute.ID_HEART_RATE;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_HEART_RATE"));

		State tole = createEstado("tole", i1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, i2, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(tole);
		
		State objet = createEstado("HhrRobjet", j1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, j2, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(objet);

		State perigo = createEstado("perigo", k1, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, k2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(perigo);

		//Transition tole_objet = createTransicao("iet", tole, objet, "Increase_temperature.");
		//aI.addTransicao(tole_objet);
		//Transition tole_objet2 = createTransicao("is", tole, objet, "Increase_speed.");
		//aI.addTransicao(tole_objet2);
		Transition tole_objet = createTransicao("ihr", tole, objet, "Increase_heart_rate.");
		aI.addTransicao(tole_objet);

		Transition objet_tole = createTransicao("dhr", objet, tole, "Decrease_heart_rate.");
		aI.addTransicao(objet_tole);
		//Transition objet_tole2 = createTransicao("ds", objet, tole, "Decrease_speed.");
		//aI.addTransicao(objet_tole2);
		
		//Transition objet_perigo = createTransicao("iet", objet, perigo, "Increase_temperature.");
		//aI.addTransicao(objet_perigo);
		//Transition objet_perigo2 = createTransicao("is", objet, perigo, "Increase_speed.");
		//aI.addTransicao(objet_perigo2);
		Transition objet_perigo = createTransicao("ihr", objet, perigo, "Increase_heart_rate.");
		aI.addTransicao(objet_perigo);
		
		Transition perigo_objet = createTransicao("dhr", perigo, objet, "Decrease_heart_rate.");
		aI.addTransicao(perigo_objet);
		//Transition perigo_objet2 = createTransicao("ds", perigo, objet, "Decrease_speed.");
		//aI.addTransicao(perigo_objet2);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	/**
	 * Model used at tests on field
	 * @param arrayAtributos
	 */
	private static void fillEnvironmentalTemperature(ArrayList<Attribute> arrayAtributos, 
			float i1,float i2,float j1,float j2,float k1,float k2){
		
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_ENVIRONMENTAL_TEMPERATURE"));
		atributo.setType(Attribute.ENVIRONMENTAL);
		atributo.setUnitOfMeasure("°C");
		int identificador = Attribute.ID_ENVIRONMENTAL_TEMPERATURE;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_ENVIRONMENTAL_TEMPERATURE"));

		State fria = createEstado("fria", i1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, i2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(fria);
		
		State amena = createEstado("amena", j1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, j2, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(amena);

		State quente = createEstado("quente", k1, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, k2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(quente);

		Transition fria_amena = createTransicao("iet", fria, amena, "Increase_temperature.");
		aI.addTransicao(fria_amena);

		Transition amena_fria = createTransicao("det", amena, fria, "Decrease_temperature.");
		aI.addTransicao(amena_fria);
		
		Transition amena_quente = createTransicao("iet", amena, quente, "Increase_temperature.");
		aI.addTransicao(amena_quente);
		
		Transition quente_amena = createTransicao("det", quente, amena, "Decrease_temperature.");
		aI.addTransicao(quente_amena);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	/**
	 * Model used at tests on field
	 * @param arrayAtributos
	 */
	private static void fillAirRelativeHumidity(ArrayList<Attribute> arrayAtributos, 
			float i1,float i2,float j1,float j2,float k1,float k2){
		
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_AIR_RELATIVE_HUMIDITY"));
		atributo.setType(Attribute.ENVIRONMENTAL);
		atributo.setUnitOfMeasure("%");
		int identificador = Attribute.ID_AIR_RELATIVE_HUMIDITY;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_AIR_RELATIVE_HUMIDITY"));

		State baixa = createEstado("baixa", i1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, i2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(baixa);
		
		State segura = createEstado("segura", j1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, j2, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(segura);

		State alta = createEstado("alta", k1, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, k2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(alta);

		Transition baixa_segura = createTransicao("hyd", baixa, segura, "Stop_exercising_and_hydrate!");
		aI.addTransicao(baixa_segura);

		Transition alta_segura = createTransicao("hyd", alta, segura, "Hydrate.");
		aI.addTransicao(alta_segura);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}

	/**
	 * Model used at tests on field
	 * @param arrayAtributos
	 */
	private static void fillBodyTemperature(ArrayList<Attribute> arrayAtributos, 
			float i1,float i2,float j1,float j2,float k1,float k2,float l1,float l2){
		
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_BODY_TEMPERATURE"));
		atributo.setType(Attribute.PHYSIOLOGICAL);
		atributo.setUnitOfMeasure("°C");
		int identificador = Attribute.ID_BODY_TEMPERATURE;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_BODY_TEMPERATURE"));

		State baixa = createEstado("baixa", i1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, i2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(baixa);
		
		State normal = createEstado("normal", j1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, j2, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(normal);

		State alerta = createEstado("alerta", k1, Range.MENOR_OU_IGUAL_A, Range.MENOR_QUE, k2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(alerta);

		State alta = createEstado("alta", l1, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, l2, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(alta);

		Transition baixa_normal = createTransicao("iet", baixa, normal, "Increase_temperature.");
		aI.addTransicao(baixa_normal);
		//Transition baixa_normal2 = createTransicao("is", baixa, normal, "Increase_speed.");
		//aI.addTransicao(baixa_normal2);
		//Transition baixa_normal3 = createTransicao("ihr", baixa, normal, "Increase_heart_rate.");
		//aI.addTransicao(baixa_normal3);

		Transition normal_baixa = createTransicao("det", normal, baixa, "Decrease_temperature.");
		aI.addTransicao(normal_baixa);
		//Transition normal_baixa2 = createTransicao("hyd", normal, baixa, "Hydrate.");
		//aI.addTransicao(normal_baixa2);
		//Transition normal_baixa3 = createTransicao("dhr", normal, baixa, "Decrease_heart_rate.");
		//aI.addTransicao(normal_baixa3);
		//Transition normal_baixa4 = createTransicao("ds", normal, baixa, "Decrease_speed.");
		//aI.addTransicao(normal_baixa4);
		
		Transition normal_alerta = createTransicao("iet", normal, alerta, "Increase_temperature.");
		aI.addTransicao(normal_alerta);
		//Transition normal_alerta2 = createTransicao("is", baixa, normal, "Increase_speed.");
		//aI.addTransicao(normal_alerta2);
		//Transition normal_alerta3 = createTransicao("ihr", baixa, normal, "Increase_heart_rate.");
		//aI.addTransicao(normal_alerta3);
		
		Transition alerta_normal = createTransicao("det", alerta, normal, "Decrease_temperature.");
		aI.addTransicao(alerta_normal);
		//Transition alerta_normal2 = createTransicao("hyd", normal, baixa, "Hydrate.");
		//aI.addTransicao(alerta_normal2);
		//Transition alerta_normal3 = createTransicao("dhr", normal, baixa, "Decrease_heart_rate.");
		//aI.addTransicao(alerta_normal3);
		//Transition alerta_normal4 = createTransicao("ds", normal, baixa, "Decrease_speed.");
		//aI.addTransicao(alerta_normal4);
	
		Transition alerta_alta = createTransicao("iet", alerta, alta, "Increase_temperature.");
		aI.addTransicao(alerta_alta);
		//Transition alerta_alta2 = createTransicao("is", baixa, normal, "Increase_speed.");
		//aI.addTransicao(alerta_alta2);
		//Transition alerta_alta3 = createTransicao("ihr", baixa, normal, "Increase_heart_rate.");
		//aI.addTransicao(alerta_alta3);
	
		Transition alta_alerta = createTransicao("det", alta, alerta, "Decrease_temperature.");
		aI.addTransicao(alta_alerta);
		//Transition alta_alerta2 = createTransicao("hyd", normal, baixa, "Hydrate.");
		//aI.addTransicao(alta_alerta2);
		//Transition alta_alerta3 = createTransicao("dhr", normal, baixa, "Decrease_heart_rate.");
		//aI.addTransicao(alta_alerta3);
		//Transition alta_alerta4 = createTransicao("ds", normal, baixa, "Decrease_speed.");
		//aI.addTransicao(alta_alerta4);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	/**
	 * First model presented
	 * @param arrayAtributos
	 */
	public static void preencheFreqCar_Temp_Velo2(ArrayList<Attribute> arrayAtributos){
		preencheFrequenciaCardiaca(arrayAtributos); 
		preencheVelocidade(arrayAtributos);
		preencheTemperatura(arrayAtributos);
	}

	/**
	 * First model presented
	 * @param arrayAtributos
	 */
	private static void preencheFrequenciaCardiaca(ArrayList<Attribute> arrayAtributos){
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_HEART_RATE"));
		atributo.setType(Attribute.PHYSIOLOGICAL);
		atributo.setUnitOfMeasure("bpm");
		int identificador = Attribute.ID_HEART_RATE;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_HEART_RATE"));

		State safe = createEstado("safe",  50f, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, 100f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(safe);
		State tole = createEstado("tolerable",  100f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 130f, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(tole);
		State dang = createEstado("dangerous",  130f,  Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 200f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(dang);
	
		Transition safe_tole = createTransicao("ihr", safe, tole, "Increase_heart-rate.(by_going_uphill_or_accelerating)");
		aI.addTransicao(safe_tole);
		Transition tole_safe = createTransicao("dhr", tole, safe,  "Decrease_heart-rate.(by_slowing_down)");
		aI.addTransicao(tole_safe);
		Transition tole_dang = createTransicao("ihr", tole, dang,  "Increase_heart-rate.(by_going_uphill_or_accelerating)");
		aI.addTransicao(tole_dang);
		Transition dang_tole = createTransicao("dhr", dang, tole,  "Decrease_heart-rate.(by_slowing_down)");
		aI.addTransicao(dang_tole);
		
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	/**
	 * First model presented
	 * @param arrayAtributos
	 */
	private static void preencheVelocidade(ArrayList<Attribute> arrayAtributos){
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_SPEED"));
		atributo.setType(Attribute.PHYSIOLOGICAL);
		atributo.setUnitOfMeasure("m/s");
		int identificador = Attribute.ID_SPEED;

		Automaton aI  = new Automaton();
		
		State slow = createEstado("slow",  0f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 6f, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(slow);
		State mode = createEstado("moderable",  6f,Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 13f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(mode);
		State fast = createEstado("fast",  13f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 20f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(fast);
		
		Transition slow_mode = createTransicao("hu", slow, mode, "Hurry-up.");
		aI.addTransicao(slow_mode);
		Transition mode_slow = createTransicao("sd", mode, slow,  "Slow_down.");
		aI.addTransicao(mode_slow);
		Transition mode_dang = createTransicao("hu", mode, fast,  "Hurry-up.");
		aI.addTransicao(mode_dang);
		Transition dang_mode = createTransicao("sd", fast, mode,  "Slow_down.");
		aI.addTransicao(dang_mode);

		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	/**
	 * First model presented
	 * @param arrayAtributos
	 */
	private static void preencheTemperatura(ArrayList<Attribute> arrayAtributos){
		Attribute atributo = new Attribute();
		atributo.setName(i16o.label("AT_ENVIRONMENTAL_TEMPERATURE"));
		atributo.setType(Attribute.ENVIRONMENTAL);
		atributo.setUnitOfMeasure("°C");
		int identificador = Attribute.ID_ENVIRONMENTAL_TEMPERATURE;

		Automaton aI  = new Automaton();
		aI.setNome(i16o.label("AT_ENVIRONMENTAL_TEMPERATURE"));
		State good = createEstado("good",  10f, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, 29f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(good);
		
		State high = createEstado("high",  29f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 35f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(high);
		State vhig = createEstado("vhig",  35f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 50f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(vhig);

		Transition good_high = createTransicao("it", good, high, "Increase_temperature.");
		aI.addTransicao(good_high);
		Transition high_good = createTransicao("dt", high, good,  "Decrease_temperature.(or_hydrate!)");
		aI.addTransicao(high_good);
		Transition high_vhig = createTransicao("it", high, vhig,  "Increase_temperature.");
		aI.addTransicao(high_vhig);
		Transition vhig_high = createTransicao("dt", vhig, high,  "Decrease_temperature.(or_hydrate!)");
		aI.addTransicao(vhig_high);
	
		atributo.setAutomaton(aI);
		arrayAtributos.add(atributo);
	}
	
	public static State createEstado(String nome, Float minimo, int operadorMinimo, 
			int operadorMaximo, Float maximo, int classificacao, int identificador){
		State estado = new State();
		estado.setNome(nome);
		estado.setValorMaximo(maximo);
		estado.setValorMinimo(minimo);
		estado.setClassificacao(classificacao);
		estado.setIdentificador(identificador);
		estado.setOperadorValorMinimo(operadorMinimo);
		estado.setOperadorValorMaximo(operadorMaximo);
		return estado;
	}
	
	public static int getAttIdFromName(String nome){
		if(nome.equals(i16o.label("AT_HEART_RATE"))) return Attribute.ID_HEART_RATE;
		if(nome.equals(i16o.label("AT_RESPIRATORY_EXCHANGE_RATIO"))) return Attribute.ID_RESPIRATORY_EXCHANGE_RATIO;
		if(nome.equals(i16o.label("AT_SYSTOLIC_BLOOD_PRESSURE"))) return Attribute.ID_SYSTOLIC_BLOOD_PRESSURE;
		if(nome.equals(i16o.label("AT_DIASTOLIC_BLOOD_PRESSURE"))) return Attribute.ID_DIASTOLIC_BLOOD_PRESSURE; 
		if(nome.equals(i16o.label("AT_BODY_TEMPERATURE"))) return Attribute.ID_BODY_TEMPERATURE;
		if(nome.equals(i16o.label("AT_DISTANCE"))) return Attribute.ID_DISTANCE;
		if(nome.equals(i16o.label("AT_BLOOD_GLUCOSE"))) return Attribute.ID_BLOOD_GLUCOSE;	
		if(nome.equals(i16o.label("AT_OXIGEN_CONSUMPTION"))) return Attribute.ID_OXIGEN_CONSUMPTION; 
		if(nome.equals(i16o.label("AT_AMBIENT_PRESSURE"))) return Attribute.ID_AMBIENT_PRESSURE;
		if(nome.equals(i16o.label("AT_AIR_RELATIVE_HUMIDITY"))) return Attribute.ID_AIR_RELATIVE_HUMIDITY; 	
		if(nome.equals(i16o.label("AT_SPEED"))) return Attribute.ID_SPEED;
		return 0;
	}

	private static Transition createTransicao(String nome, State estadoOrigem, State estadoDestino, String mensagem){
		Transition transicao = new Transition();
		transicao.setRotulo(nome);
		transicao.setEstadoOrigem(estadoOrigem);
		transicao.setEstadoDestino(estadoDestino);
		transicao.setMensagem(mensagem);
		return transicao;
	}
}