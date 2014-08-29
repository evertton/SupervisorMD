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

public class Internacionalizar {
	//public static String x = "x";
	//TITULOS
	public static String TITULO_TL_LISTA_ATRIBUTOS = "Attributes";
	public static String TITULO_TL_LISTA_ESTADOS = "States";
	public static String TITULO_TL_LISTA_TRANSICOES = "Transitions";
	public static String TITULO_TL_ALTERAR_ATRIBUTO = "Edit attribute";
	public static String TITULO_TL_SELECIONAR_ATRIBUTO = "Select attribute";
	public static String TITULO_TL_ALTERAR_ESTADO = "Edit state";
	public static String TITULO_TL_CRIAR_ESTADO = "Add state";
	public static String TITULO_TL_ALTERAR_TRANSICAO = "Edit transition";
	public static String TITULO_TL_CRIAR_TRANSICAO = "Add transition";

	//BOTÕES
	public static String BT_ADICIONAR = "Add";
	public static String BT_EDITAR = "Edit";
	public static String BT_REMOVER = "Remove";
	public static String BT_REMOVER_TUDO = "Remove All";
	public static String BT_VOLTAR = "Back";
	public static String BT_ESTADOS = "State";
	public static String BT_TRANSICOES = "Transitions";
	public static String BT_CANCELAR = "Cancel";
	public static String BT_SALVAR = "Save";
	public static String BT_FINALIZAR = "Done";

	//CAMPOS
	//TELA ADICIONAR ESTADO
	public static String CP_ATRIBUTO = "Attribute";
	public static String CP_INTEIRO = "Integer";
	public static String CP_REAL = "Real";
	public static String CP_NOME = "Name";		//presente em várias telas
	public static String CP_VALOR_MINIMO = "Minimum value";
	public static String CP_VALOR_MAXIMO = "Maximum value";
	public static String CP_INTERVALO_DE_MEDICAO = "Measurement range";
	public static String CP_VALOR_ENUMERADO = "Enumerated value";
	public static String CP_CLASSIFICACAO = "Classification";
	public static String CP_UNIDADE = "Unit";

	//TELA ADICIONAR TRANSICAO
	public static String CP_NOME_ACAO = "Name/Action";
	public static String CP_ESTADO_DE_ORIGEM = "Origin state";
	public static String CP_ESTADO_DE_DESTINO = "Target state";
	public static String CP_MENSAGEM_AO_USUARIO = "Message to user";

	//TELA ADICIONAR ATRIBUTO
	public static String CP_NOME_DO_ATRIBUTO = "Attribute's name";
	public static String CP_FREQUENCIA = "Frequency";
	public static String CP_UNIDADE_DE_MEDIDA = "Measurement unit";
	public static String CP_LEITURA_POR_MIN = "Readings/min";//APENAS O TEXTO, SEM PARENTESES

	//TABELA LISTA TRANSICAO
	public static String CP_MENSAGEM = "Message";

	//TABELA LISTA ESTADO
	public static String CP_NOME_DO_ESTADO = "State's name";
	
	//TABELA LISTA ATRIBUTOS		
	public static String CP_TIPO = "Type";
	
	//LISTA ATRIBUTOS
	public static String MS_SELECIONE_UMA_LINHA = "Selecione uma linha!";//TODO MODIFICAR
	public static String MS_SELECIONE_UM_ATRIBUTO = "Select an attribute!";
	public static String MS_SELECIONE_UMA_TRANSICAO = "Select a transition!";
	public static String MS_SELECIONE_UM_ESTADO = "Select a state!";
	public static String MS_DIGITE_NOME_DO_ARQUIVO = "Choose a name for the reference model file:";
	
	public static String MS_TODOS_OS_ATRIBUTOS_FORAM_ADICIONADOS = "All attributes were added to the list!";
	
	public static String MS_OPERACAO_REALIZADA_COM_SUCESSO = "Operation has been succesfully concluded";
	public static String MS_ARQUIVO_SALVO_EM = "File saved at";
	public static String MS_MODELO_NAO_SALVO = "Operation has not been concluded";
	public static String MS_MODELO_INVALIDO = "The generated model is invalid!";
	
	//LISTA_DE_ATRIBUTO
	public static String TIPO_ATRIBUTO_AMBIENTAL = "Environmental";
	public static String TIPO_ATRIBUTO_FISIOLOGICO = "Physiological";
	public static String TIPO_ATRIBUTO_COMPORTAMENTAL = "Behavioural";

	//NÃO USADOS AINDA
	//public static String CP_CADASTRAR = x;
	//public static String CP_ESTADO_INICIAL = x;
	//public static String BT_EXPRESSAO_REGULAR = x;
	//public static String CP_ENUMERACAO = "Enumeration"; //PODE SAIR DEVIDO A COMPLEXIDADE?
	
	//CLASSE ListaDeAtributos
	//public static String AT_FREQUENCIA_CARDIACA   	= "Heart_Rate";		//obs.: os nomes dos atributos não
	//public static String AT_TEMPERATURA_AMBIENTAL 	= "Environmental_Temperature";	//devem possuir espaços. ex.: FrequênciaCardíaca"
	//public static String AT_VELOCIDADE 				= "Speed";
	
	//LISTA DE ATRIBUTOS AUX
	public static String AT_HEART_RATE   				= "Heart-rate";//"FrequênciaCardíaca";		//obs.: os nomes dos atributos não
	public static String AT_RESPIRATORY_EXCHANGE_RATIO 	= "Respiratory exchange ratio";
	public static String AT_SYSTOLIC_BLOOD_PRESSURE 	= "Systolic blood pressure";
	public static String AT_DIASTOLIC_BLOOD_PRESSURE 	= "Diastolic blood pressure";
	public static String AT_BODY_TEMPERATURE			= "Body temperature";
	public static String AT_BLOOD_LACTATE 				= "Blood lactate";
	public static String AT_BLOOD_GLUCOSE 				= "Blood glucose";
	public static String AT_OXIGEN_CONSUMPTION 			= "Oxygen Consumption";
	public static String AT_AMBIENT_PRESSURE 			= "Atmospheric pressure";
	public static String AT_ENVIRONMENTAL_TEMPERATURE	= "Environmental temperature";	//devem possuir espaços. ex.: FrequênciaCardíaca"
	public static String AT_AIR_RELATIVE_HUMIDITY		= "Air relative humidity";
	public static String AT_SPEED 						= "Speed";

	public static String CL_TOLERAVEL 				= "Tolerable";
	public static String CL_NENHUM 				    = "None";
	public static String CL_PERIGOSO 				= "Dangerous";
	public static String CL_ACEITACAO 				= "Acceptable";
}