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

public class InternacionalizarPt {

	public static String x = "x";
	//TITULOS
	public static String TITULO_TL_LISTA_ATRIBUTOS = "Lista de Atributos";
	public static String TITULO_TL_LISTA_ESTADOS = "Lista de Estados";
	public static String TITULO_TL_LISTA_TRANSICOES = "Lista de Transições";

	public static String TITULO_TL_ALTERAR_ATRIBUTO = "Editar Atributo";
	public static String TITULO_TL_SELECIONAR_ATRIBUTO = "Adicionar Atributo";
	public static String TITULO_TL_ALTERAR_ESTADO = "Editar Estado";
	public static String TITULO_TL_CRIAR_ESTADO = "Criar Estado";
	public static String TITULO_TL_ALTERAR_TRANSICAO = "Editar Transição";
	public static String TITULO_TL_CRIAR_TRANSICAO = "Criar Transição";

	//TELAS LISTAR
	public static String BT_ADICIONAR = "Adicionar";
	public static String BT_EDITAR = "Editar";
	public static String BT_REMOVER = "Remover";
	public static String BT_REMOVER_TUDO = "Remover Tudo";
	public static String BT_VOLTAR = "Voltar";

	public static String BT_ESTADOS = "Estados";
	public static String BT_TRANSICOES = "Transições";

	public static String BT_CANCELAR = "Cancelar";
	public static String BT_SALVAR = "Salvar";

	public static String BT_FINALIZAR = "Modelo";

	//TELA ADICIONAR ESTADO
	public static String CP_ATRIBUTO = "Atributo";
	public static String CP_INTEIRO = "Inteiro";
	public static String CP_REAL = "Real";
	public static String CP_ENUMERACAO = "Enumeração"; //PODE SAIR DEVIDO A COMPLEXIDADE?
	public static String CP_NOME = "Nome";		//COMUM
	public static String CP_VALOR_MINIMO = "Valor mínimo";
	public static String CP_VALOR_MAXIMO = "Valor máximo";
	public static String CP_INTERVALO_DE_MEDICAO = "Intervalo de medição";
	public static String CP_VALOR_ENUMERADO = "Valor enumerado";
	public static String CP_CLASSIFICACAO = "Classificação";
	public static String CP_UNIDADE = "Unidade";

	//TELA ADICIONAR TRANSICAO
	public static String CP_NOME_ACAO = "Nome/Ação";
	
	public static String CP_ESTADO_DE_ORIGEM = "Estado de origem";
	
	public static String CP_ESTADO_DE_DESTINO = "Estado de destino";
	
	public static String CP_MENSAGEM_AO_USUARIO = "Mensagem ao usuário";

	//TELA ADICIONAR ATRIBUTO
	public static String CP_NOME_DO_ATRIBUTO = "Nome do atributo";
	public static String CP_FREQUENCIA = "Frequência";
	public static String CP_UNIDADE_DE_MEDIDA = "Unidade de medida";
	public static String CP_LEITURA_POR_MIN = "Leitura/min";//APENAS O TEXTO, SEM PARENTESES

	//TABELA LISTA TRANSICAO
	public static String CP_MENSAGEM = "Mensagem";

	//TABELA LISTA ESTADO
	public static String CP_NOME_DO_ESTADO = "Nome do estado";
	
	//TABELA LISTA ATRIBUTOS		
	public static String CP_TIPO = "Tipo";
	
	//LISTA ATRIBUTOS
	public static String MS_SELECIONE_UMA_LINHA = "Selecione uma linha!";
	public static String MS_TODOS_OS_ATRIBUTOS_FORAM_ADICIONADOS = "Todos os atributos foram adicionados na lista!";

public static String MS_SELECIONE_UM_ATRIBUTO = "Selecione um atributo!";
public static String MS_SELECIONE_UMA_TRANSICAO = "Selecione uma transição!";
public static String MS_SELECIONE_UM_ESTADO = "Selecione um estado!";
public static String MS_DIGITE_NOME_DO_ARQUIVO = "Digite um nome para o arquivo com o modelo de referência:";

public static String MS_OPERACAO_REALIZADA_COM_SUCESSO = "Operação realizada com sucesso!";
public static String MS_ARQUIVO_SALVO_EM = "Arquivo salvo em";
public static String MS_MODELO_NAO_SALVO = "Não foi possível concluir a operação!";
public static String MS_MODELO_INVALIDO = "O modelo gerado é inválido!";
	
	
	//LISTA CLASSIFICAÇAO ATRIBUTO
	public static String TIPO_ATRIBUTO_AMBIENTAL = "Ambiental";
	public static String TIPO_ATRIBUTO_FISIOLOGICO = "Fisiológico";
	public static String TIPO_ATRIBUTO_COMPORTAMENTAL = "Comportamental";

	//NAO USADOS AINDA
	public static String CP_CADASTRAR = x;
	public static String CP_ESTADO_INICIAL = x;
	public static String BT_EXPRESSAO_REGULAR = x;
	
	
	//LISTA DE ATRIBUTOS AUX
	public static String AT_HEART_RATE   				= "Frequência cardíaca";//"Frequência Cardíaca";		//obs.: os nomes dos atributos não
	
	public static String AT_RESPIRATORY_EXCHANGE_RATIO 	= "Respiratory exchange ratio";
	public static String AT_SYSTOLIC_BLOOD_PRESSURE 	= "PA systólica";
	public static String AT_DIASTOLIC_BLOOD_PRESSURE 	= "PA diastólica";
	public static String AT_BODY_TEMPERATURE			= "Temperatura corporal";
	public static String AT_BLOOD_LACTATE 				= "Blood lactate";
	public static String AT_BLOOD_GLUCOSE 				= "Blood glucose";
	public static String AT_OXIGEN_CONSUMPTION 			= "Oxygen Consumption";
	public static String AT_AMBIENT_PRESSURE 			= "Pressão ambiental";
	public static String AT_ENVIRONMENTAL_TEMPERATURE	= "Temperatura ambiental";	//devem possuir espaços. ex.: FrequênciaCardíaca"
	public static String AT_AIR_RELATIVE_HUMIDITY		= "Humidade relativa do ar";
	public static String AT_SPEED 						= "Velocidade";
	
	public static String CL_TOLERAVEL 				= "Tolerável";
	public static String CL_NENHUM 				    = "Nenhum";
	public static String CL_PERIGOSO 				= "Perigoso";
	public static String CL_ACEITACAO 				= "Aceitação";
	
}
