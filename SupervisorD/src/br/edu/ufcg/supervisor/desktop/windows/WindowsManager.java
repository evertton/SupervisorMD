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

package br.edu.ufcg.supervisor.desktop.windows;

import java.util.ArrayList;

//import br.edu.ufcg.supervisor.desktop.util.AttributesFiller; 00
import br.edu.ufcg.supervisor.desktop.util.AutomataFiller;
import br.edu.ufcg.supervisor.model.Attribute;

/**
 * Classe responsável por gerenciar as telas da aplicação. 
 * @author Marcos José, Elthon Oliveira
 *
 */
public class WindowsManager {
	private JFrameAttributeList listaAtributos;
	private JFrameStateList listaEstado;
	private JFrameTransitionList listaTransicao;
	private JFrameAttributeSelector telaAtributo;
	private JFrameState telaEstado;
	private JFrameTransition telaTransicao;
	private JFrameModelGenerator telaSalvar;
	private ArrayList<Attribute> arrayAtributos;

	public WindowsManager(){
		listaAtributos 	= new JFrameAttributeList(this);
		listaEstado 	= new JFrameStateList(this);
		listaTransicao 	= new JFrameTransitionList(this);
		telaAtributo	= new JFrameAttributeSelector(this);
		telaEstado		= new JFrameState(this);
		telaTransicao 	= new JFrameTransition(this);
		telaSalvar 		= new JFrameModelGenerator(this);
		arrayAtributos	= new ArrayList<Attribute>();
		AutomataFiller.preencheFreqCar_Temp_Velo(arrayAtributos);
		telaAtributo.removerAtributoUtilizado(11);
		telaAtributo.removerAtributoUtilizado(9);
		telaAtributo.removerAtributoUtilizado(0);
	}

	public void initListaAtributos() {
		listaAtributos.listarAtributos(arrayAtributos);
		listaAtributos.setVisible(true);
	}

	public void initListaEstado(int index) {
		listaEstado.listarEstados(index);
		listaEstado.setVisible(true);
	}

	public void initListaTransicao(int indexAtributoSelecionado) {
		listaTransicao.listarTransicoes(indexAtributoSelecionado);
		listaTransicao.setVisible(true);
	}

	public void initCriarAtributo() {
		telaAtributo.setCriarAtributo();
		telaAtributo.setVisible(true);
	}

	public void initEditarAtributo(int index) {
		Attribute atributo = arrayAtributos.get(index);
		arrayAtributos.remove(index);
		telaAtributo.setAlterarAtributo(index, atributo);
		telaAtributo.setVisible(true);
	}

	//LISTA ESTADO
	public void initCriarEstado(int indexAtributo) {
		telaEstado.setCriarEstado(indexAtributo);
		telaEstado.setVisible(true);
	}
	public void initEditarEstado(int indexAtributo, int indexEstado) {
		telaEstado.setAlterarEstado(indexAtributo, indexEstado);
		telaEstado.setVisible(true);
	}

	//LISTA TRANSICAO
	public void initCriarTransicao(int indexAtributo) {
		telaTransicao.setModoCriarTransicao(indexAtributo);
		telaTransicao.setVisible(true);
	}

	public void initEditarTransicao(int indexAtributo, int indexTransicao) {
		telaTransicao.setModoAlterarTransicao(indexAtributo, indexTransicao);
		telaTransicao.setVisible(true);
	}

	public void initTelaSalvar(String automatoJson){
		telaSalvar.setModeloJson(automatoJson);
		telaSalvar.setVisible(true);
	}

	//LISTA ATRIBUTOS
	public void removeTodosAtributos(){
		arrayAtributos.clear();
		telaAtributo.initCampos();
	}

	public void removeAtributo(int index){
		Attribute atributo = arrayAtributos.get(index);
		arrayAtributos.remove(index);
		telaAtributo.addAtributoNaListaDoComboBox(atributo);
	}

	public ArrayList<Attribute> getArrayAtributos(){
		//SUBSTITUIR ESSE METODO PELA FUNÇÃO GERAR COMPOSICAO SIMPLIFICAR INTERFACE
		return arrayAtributos;
	}

	//SELECIONAR ATRIBUTO
	public void adicionaAtributo(Attribute atributo){
		arrayAtributos.add(atributo);
	}

	public void atualizaAtributo(int index, Attribute atributo){
		arrayAtributos.add(index, atributo);
	}

	//LISTA ESTADOS
	public void removeTodosEstados(int indexAtributo){
		arrayAtributos.get(indexAtributo).getAutomaton().getVectorEstados().clear();
	}

	public void removeTodasTransicoes(int indexAtributo){
		arrayAtributos.get(indexAtributo).getAutomaton().getArrayTransicoes().clear();
	}

	public Attribute getAtributo(int indexAtributo) {
		return arrayAtributos.get(indexAtributo);
	}
}
