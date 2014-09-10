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
package br.edu.ufcg.supervisor.desktop.windows;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.edu.ufcg.supervisor.desktop.util.AttributeList;
import br.edu.ufcg.supervisor.desktop.util.Internationalization;
import br.edu.ufcg.supervisor.model.Attribute;
import br.edu.ufcg.supervisor.model.Automaton;
import br.edu.ufcg.supervisor.model.ParallelComposition;

import com.google.gson.Gson;
import com.google.gson.internal.StringMap;

/**
 * Tela para listar os atributos selecionados. 
 * @author Marcos José, Elthon Oliveira
 */
public class JFrameAttributeList extends javax.swing.JFrame {
	private static final long serialVersionUID = -5594345540620424402L;
	//Atributos
	private DefaultTableModel model;
	private WindowsManager gerenciador;

	/** Contrutor. */
/*	public JFrameAttributeList() {
		initComponents();
		this.model = (DefaultTableModel) jTable1.getModel();
		gerenciador = new WindowsManager(this);
		setTitle(Internationalization.TITULO_TL_LISTA_ATRIBUTOS);
		this.setVisible(false);
		gerenciador.initListaAtributos();
	}*/

	/** Contrutor. */
	public JFrameAttributeList(WindowsManager gerenciador) {
		initComponents();
		this.gerenciador = gerenciador;  
		this.model = (DefaultTableModel) jTable1.getModel();
		setTitle(Internationalization.TITULO_TL_LISTA_ATRIBUTOS);
	}

	public void listarAtributos(ArrayList<Attribute> arrayAtributos){
		int qtd = model.getRowCount() - 1;
		for (; qtd>=0; qtd--) model.removeRow(qtd);
		for (Attribute atributo : arrayAtributos) addLinha(atributo);
	}

	/**
	 * Adiciona um atributo cadastrado na lista de atributos selecionados. 
	 * @param atributo	Um Atributo cadastrado.
	 */
	public void addLinha(Attribute atributo) {
		Object[] linha = new Object[] {
				atributo.getName(), AttributeList.getTipoAtributoString(atributo.getType()), 
				atributo.getUnitOfMeasure(), atributo.getReadingRate()};
		model.addRow(linha);
	}

	/* Remove o atributo da linha selecionada da tabela e do array de atributos */
	private void removerLinha(){
		int linha = jTable1.getSelectedRow();
		model.removeRow(linha);
		gerenciador.removeAtributo(linha);
	}

	/**
	 * Atualiza uma linha da tabela.
	 * @param index		Um int contendo a posição do Atributo na lista de atributos selecionados.
	 * @param atributo	O Atributo que será atualizado.
	 */
	//	public void atualizarLinha(int index, Atributo atributo){
	//		model.removeRow(index);
	//		Object[] linha = new Object[] {
	//				atributo.getNome(), 
	//				ListaDeAtributos.getTipoAtributoString(atributo.getTipo()),
	//				atributo.getUnidadeDeMedida(),
	//				atributo.getFrequenciaDeLeitura()};
	//		model.insertRow(index, linha);
	//
	////		arrayAtributos.remove(index);
	//		arrayAtributos.add(index, atributo);
	//	}

	/**
	 * Retorna os atributos selecionados.
	 * @return	Um ArrayList<Atributo> contendo os atributos selecionados.
	 */
	//	public ArrayList<Atributo> getArrayAtributos(){
	//		return arrayAtributos;
	//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean automatoValido(String automatoJson){//TODO VER
		HashMap<String,Object> map = (HashMap<String,Object>) new Gson().fromJson(automatoJson, HashMap.class);
		int estados = ( (ArrayList<StringMap>) map.get(Automaton.ARRAY_ESTADOS)      ).size();
		int estadosAceitos = ( (ArrayList<Double>) map.get(Automaton.ARRAY_ESTADOS_ACEITOS) ).size();
		int transicoes = ( (ArrayList<StringMap>) map.get(Automaton.ARRAY_TRANSICOES)   ).size();
		if ( estados == 0 || estadosAceitos == 0 || transicoes == 0 )
			return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jButtonAdicionar = new javax.swing.JButton();
		jButtonEditar = new javax.swing.JButton();
		jButtonRemover = new javax.swing.JButton();
		jButtonEstados = new javax.swing.JButton();
		jButtonRemoverTodos = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jButtonFinalizar = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jButtonAdicionar.setText(Internationalization.BT_ADICIONAR);
		jButtonAdicionar.addActionListener(
			new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonAdicionarActionPerformed(evt);
				}
			}
		);
		jButtonEditar.setText(Internationalization.BT_EDITAR);
		jButtonEditar.addActionListener(
			new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonEditarActionPerformed(evt);
				}
			}
		);
		jButtonRemover.setText(Internationalization.BT_REMOVER);
		jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRemoverActionPerformed(evt);
			}
		});
		jButtonEstados.setText(Internationalization.BT_ESTADOS);
		jButtonEstados.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonEstadosActionPerformed(evt);
			}
		});
		jButtonRemoverTodos.setText(Internationalization.BT_REMOVER_TUDO);
		jButtonRemoverTodos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRemoverTodosActionPerformed(evt);
			}
		});
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
			.addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(jButtonRemoverTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(54, 54, 54)
			.addComponent(jButtonEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
			.addComponent(jButtonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(jButtonEstados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(jButtonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(jButtonRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(jButtonRemoverTodos))
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jTable1.setModel(new javax.swing.table.DefaultTableModel( new Object [][] { },
			new String [] {
				Internationalization.CP_NOME_DO_ATRIBUTO, Internationalization.CP_TIPO, Internationalization.CP_UNIDADE_DE_MEDIDA, Internationalization.CP_LEITURA_POR_MIN
			}
		) {
			private static final long serialVersionUID = -6767734492939939382L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
				java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
			};
			@SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}
		});
		jScrollPane1.setViewportView(jTable1);
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
			jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
			.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
			.addContainerGap())
		);
		jPanel2Layout.setVerticalGroup(
			jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel2Layout.createSequentialGroup()
			.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jButtonFinalizar.setText(Internationalization.BT_FINALIZAR);
		jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonFinalizarActionPerformed(evt);
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
			.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
			.addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addContainerGap()).addGroup(layout.createSequentialGroup().addGap(0, 554, Short.MAX_VALUE)
			.addComponent(jButtonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(22, 22, 22))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
			.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jButtonFinalizar).addContainerGap())
		);
		pack();
	}

	/* Remove um atributo selecionado. */
	private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {                                               
		int index = jTable1.getSelectedRow();
		if (index>-1) removerLinha();
		else {
			JOptionPane.showMessageDialog(null, 
				Internationalization.MS_SELECIONE_UM_ATRIBUTO, "", 
				JOptionPane.INFORMATION_MESSAGE);		
		}
	}

	/* Cria um arquivo de texto contendo a especificação e limpa as telas */
	private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {                                               
		ParallelComposition composicao = new ParallelComposition();
		ArrayList<Attribute> arrayAtributos = gerenciador.getArrayAtributos();
		if (arrayAtributos.size() == 0){
			JOptionPane.showMessageDialog(null, Internationalization.MS_MODELO_INVALIDO,"", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Automaton automato = composicao.executaComposicao(arrayAtributos);
		/*
		System.out.println("JFrameAtributoListar----------------------");
		for (Estado e : automato.getVectorEstados()){
			System.out.println(e.getNome() + " - " + e.getClassificacaoString());
		}
		System.out.println("----------------------");
		*/
		String automatoJson = automato.toJson();
		if (!automatoValido(automatoJson) ){
			JOptionPane.showMessageDialog(null, Internationalization.MS_MODELO_INVALIDO, "", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.setVisible(false);
		gerenciador.initTelaSalvar(automatoJson);
	}

	/* Chama a tela que lista estados. */
	private void jButtonEstadosActionPerformed(java.awt.event.ActionEvent evt) {                                               
		int index = jTable1.getSelectedRow();
		if (index>-1){
			this.setVisible(false);
			gerenciador.initListaEstado(index);
		} else {
			JOptionPane.showMessageDialog(null,
					Internationalization.MS_SELECIONE_UM_ATRIBUTO, "",	JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/* Chama a tela editar atributo. */
	private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {                                              
		int index = jTable1.getSelectedRow();
		if (index>-1){
			gerenciador.initEditarAtributo(index);
			this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, 
					Internationalization.MS_SELECIONE_UM_ATRIBUTO, "", 
					JOptionPane.INFORMATION_MESSAGE);					
		}
	}

	/* Chama a tela selecionar atributo. */
	private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {                                                 
		try {
			gerenciador.initCriarAtributo();
			this.setVisible(false);
		} catch (ArrayIndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(null, Internationalization.MS_TODOS_OS_ATRIBUTOS_FORAM_ADICIONADOS);
		}
	}

	/* Remove todos os atributos cadastrados. */
	private void jButtonRemoverTodosActionPerformed(java.awt.event.ActionEvent evt) {                                                 
		int qtd = model.getRowCount() - 1;
		for (; qtd >= 0; qtd--) model.removeRow(qtd);
		gerenciador.removeTodosAtributos();
	}
		
	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {	}
			//public void run() { new JFrameAttributeList().setVisible(true); } 00
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEstados;
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonRemoverTodos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}