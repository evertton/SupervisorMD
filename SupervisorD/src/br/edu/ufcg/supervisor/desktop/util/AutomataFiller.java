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
		State safe = createEstado("safe",  60f, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, 100f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(safe);

		State tole = createEstado("tole",  100.0f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 120.0f, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(tole);
		State dang = createEstado("dang",  120.0f,  Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 140.0f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(dang);
	
		Transition safe_tole = createTransicao("ihr", safe, tole, "IncreaseHeartRate");
		aI.addTransicao(safe_tole);
		Transition safe_tole2 = createTransicao("hu", safe, tole, "HurryUp");
		aI.addTransicao(safe_tole2);
		Transition tole_safe = createTransicao("dhr", tole, safe,  "DecreaseHeartRate");
		aI.addTransicao(tole_safe);
		Transition tole_safe2 = createTransicao("sd", tole, safe,  "SlowDown");
		aI.addTransicao(tole_safe2);
		Transition tole_dang = createTransicao("ihr", tole, dang,  "IncreaseHeartRate");
		aI.addTransicao(tole_dang);
		Transition tole_dang2 = createTransicao("hu", tole, dang,  "HurryUp");
		aI.addTransicao(tole_dang2);
		Transition dang_tole = createTransicao("dhr", dang, tole,  "DecreaseHeartRate");
		aI.addTransicao(dang_tole);
		Transition dang_tole2 = createTransicao("sd", dang, tole,  "SlowDown");
		aI.addTransicao(dang_tole2);
		
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
		
		State slow = createEstado("slow",  2f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 5f, State.INT_CL_TOLERAVEL, identificador);
		aI.addEstado(slow);
		State mode = createEstado("mode",  5f,Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 9f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(mode);
		State fast = createEstado("fast",  9f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 12f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(fast);
		
		Transition slow_mode = createTransicao("hu", slow, mode, "HurryUp");
		aI.addTransicao(slow_mode);
		Transition mode_slow = createTransicao("sd", mode, slow,  "SlowDown");
		aI.addTransicao(mode_slow);
		Transition mode_dang = createTransicao("hu", mode, fast,  "HurryUp");
		aI.addTransicao(mode_dang);
		Transition dang_mode = createTransicao("sd", fast, mode,  "SlowDown");
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
		State good = createEstado("good",  19f, Range.MENOR_OU_IGUAL_A, Range.MENOR_OU_IGUAL_A, 22f, State.INT_CL_ACEITACAO, identificador);
		aI.addEstado(good);
		
		State high = createEstado("high",  28f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 30f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(high);
		State vhig = createEstado("vhig",  30f, Range.MENOR_QUE, Range.MENOR_OU_IGUAL_A, 40f, State.INT_CL_PERIGOSO, identificador);
		aI.addEstado(vhig);

		Transition good_high = createTransicao("it", good, high, "IncreaseTemperature");//(e.g.,hydrate)
		aI.addTransicao(good_high);
		Transition high_good = createTransicao("dt", high, good,  "DecreaseTemperature");//(e.g.,don't hydrate)
		aI.addTransicao(high_good);
		Transition high_vhig = createTransicao("it", high, vhig,  "IncreaseTemperature");//(e.g.,hydrate)
		aI.addTransicao(high_vhig);
		Transition vhig_high = createTransicao("dt", vhig, high,  "DecreaseTemperature");//(e.g.,don't hydrate)
		aI.addTransicao(vhig_high);
	
		atributo.setAutomato(aI);
		arrayAtributos.add(atributo);
	}
	
	private static State createEstado(String nome, Float minimo, int operadorMinimo, int operadorMaximo, Float maximo, int classificacao, int identificador ){
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

	private  static Transition createTransicao(String nome, State estadoOrigem, State estadoDestino, String mensagem){
		Transition transicao = new Transition();
		transicao.setRotulo(nome);
		transicao.setEstadoOrigem(estadoOrigem);
		transicao.setEstadoDestino(estadoDestino);
		transicao.setMensagem(mensagem);
		return transicao;
	}
}