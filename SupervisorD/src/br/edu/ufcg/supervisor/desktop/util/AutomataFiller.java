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

	public static void preencheFreqCar_Temp_Velo(ArrayList<Attribute> arrayAtributos){
		preencheFrequenciaCardiaca(arrayAtributos); 
		preencheVelocidade(arrayAtributos);
		preencheTemperatura(arrayAtributos);
	}

	private static void preencheFrequenciaCardiaca(ArrayList<Attribute> arrayAtributos){
		Attribute atributo = new Attribute();
		atributo.setNome(Internacionalizar.AT_HEART_RATE);
		atributo.setTipo(Attribute.FISIOLOGICO);
		atributo.setUnidadeDeMedida("bpm");
		int identificador = Attribute.ID_HEART_RATE;

		Automaton aI  = new Automaton();
		aI.setNome(Internacionalizar.AT_HEART_RATE);

		State safe = createEstado("safe",  50f, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, 100f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(safe);
		State tole = createEstado("tolerable",  100f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 130f, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(tole);
		State dang = createEstado("dangerous",  130f,  Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 160f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(dang);
	
		Transition safe_tole = createTransicao("ihr", safe, tole, "Increase_heart-rate.(by_going_uphill_or_accelerating)");
		aI.addTransicao(safe_tole);
		Transition tole_safe = createTransicao("dhr", tole, safe,  "Decrease_heart-rate.(by_slowing_down)");
		aI.addTransicao(tole_safe);
		Transition tole_dang = createTransicao("ihr", tole, dang,  "Increase_heart-rate.(by_going_uphill_or_accelerating)");
		aI.addTransicao(tole_dang);
		Transition dang_tole = createTransicao("dhr", dang, tole,  "Decrease_heart-rate.(by_slowing_down)");
		aI.addTransicao(dang_tole);
		
		atributo.setAutomato(aI);
		arrayAtributos.add(atributo);
	}
	
	private static void preencheVelocidade(ArrayList<Attribute> arrayAtributos){
		Attribute atributo = new Attribute();
		atributo.setNome(Internacionalizar.AT_SPEED);
		atributo.setTipo(Attribute.FISIOLOGICO);
		atributo.setUnidadeDeMedida("m/s");
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

		atributo.setAutomato(aI);
		arrayAtributos.add(atributo);
	}
	
	private static void preencheTemperatura(ArrayList<Attribute> arrayAtributos){
		Attribute atributo = new Attribute();
		atributo.setNome(Internacionalizar.AT_ENVIRONMENTAL_TEMPERATURE);
		atributo.setTipo(Attribute.AMBIENTAL);
		atributo.setUnidadeDeMedida("°C");
		int identificador = Attribute.ID_ENVIRONMENTAL_TEMPERATURE;

		Automaton aI  = new Automaton();
		aI.setNome(Internacionalizar.AT_ENVIRONMENTAL_TEMPERATURE);
		State good = createEstado("good",  19f, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, 29f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(good);
		
		State high = createEstado("high",  29f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 34f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(high);
		State vhig = createEstado("vhig",  34f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 50f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(vhig);

		Transition good_high = createTransicao("it", good, high, "Increase_temperature.");
		aI.addTransicao(good_high);
		Transition high_good = createTransicao("dt", high, good,  "Decrease_temperature.(or_hydrate!)");
		aI.addTransicao(high_good);
		Transition high_vhig = createTransicao("it", high, vhig,  "Increase_temperature.");
		aI.addTransicao(high_vhig);
		Transition vhig_high = createTransicao("dt", vhig, high,  "Decrease_temperature.(or_hydrate!)");
		aI.addTransicao(vhig_high);
	
		atributo.setAutomato(aI);
		arrayAtributos.add(atributo);
	}
	
	public static State createEstado(String nome, Float minimo, int operadorMinimo, int operadorMaximo, Float maximo, int classificacao, int identificador ){
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
		if (nome.equals(Internacionalizar.AT_HEART_RATE)) return Attribute.ID_HEART_RATE;
		if (nome.equals(Internacionalizar.AT_RESPIRATORY_EXCHANGE_RATIO)) return Attribute.ID_RESPIRATORY_EXCHANGE_RATIO;
		if (nome.equals(Internacionalizar.AT_SYSTOLIC_BLOOD_PRESSURE)) return Attribute.ID_SYSTOLIC_BLOOD_PRESSURE;
		if (nome.equals(Internacionalizar.AT_DIASTOLIC_BLOOD_PRESSURE)) return Attribute.ID_DIASTOLIC_BLOOD_PRESSURE; 
		if (nome.equals(Internacionalizar.AT_BODY_TEMPERATURE)) return Attribute.ID_BODY_TEMPERATURE;
		if (nome.equals(Internacionalizar.AT_BLOOD_LACTATE)) return Attribute.ID_BLOOD_LACTATE;
		if (nome.equals(Internacionalizar.AT_BLOOD_GLUCOSE)) return Attribute.ID_BLOOD_GLUCOSE;	
		if (nome.equals(Internacionalizar.AT_OXIGEN_CONSUMPTION)) return Attribute.ID_OXIGEN_CONSUMPTION; 
		if (nome.equals(Internacionalizar.AT_AMBIENT_PRESSURE)) return Attribute.ID_AMBIENT_PRESSURE;
		if (nome.equals(Internacionalizar.AT_AIR_RELATIVE_HUMIDITY)) return Attribute.ID_AIR_RELATIVE_HUMIDITY; 	
		if (nome.equals(Internacionalizar.AT_SPEED)) return Attribute.ID_SPEED;
		return 0;
	}

	private  static Transition createTransicao(String nome, State estadoOrigem, State estadoDestino, String mensagem){
		Transition transicao = new Transition();
		transicao.setRotulo(nome);
		transicao.setEstadoOrigem(estadoOrigem);
		transicao.setEstadoDestino(estadoDestino);
		transicao.setMensagem(mensagem);
		return transicao;
	}
}