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

import java.util.Vector;

import br.edu.ufcg.supervisor.desktop.util.AttributeList;
import br.edu.ufcg.supervisor.desktop.util.Constants;
import br.edu.ufcg.supervisor.desktop.util.Internationalization;
import br.edu.ufcg.supervisor.model.Attribute;

/**
 * Tela para selecionar um atributo cadastrado. 
 * @author Marcos José, Elthon Oliveira
 */
public class JFrameAttributeSelector extends javax.swing.JFrame {
	private static final long serialVersionUID = -6337851717392582944L;
	public static final int OP_CRIAR = 1; //Opções para setar o modo da tela
	public static final int OP_ALTERAR = 2; 

	private Vector<String> arrayNomeAtributo;
	private Vector<Integer> arrayTipoAtributo;
	private Vector<String> arrayUnidadeAtributo;

	private Integer operacao = null;
	private int indexAtributo;
	private WindowsManager gerenciador;
	private Attribute atributoSelecionado;

	/** Construtor. */
	@SuppressWarnings("unchecked")
	public JFrameAttributeSelector() {
		setTitle("Adicionar Atributo");
		arrayNomeAtributo = (Vector<String>) AttributeList.getArrayNomeAtributo().clone();
		arrayTipoAtributo = (Vector<Integer>) AttributeList.getArrayTipoAtributo().clone();
		arrayUnidadeAtributo = (Vector<String>) AttributeList.getArrayUnidadeAtributo().clone();
		initComponents();
	}

	/**
	 * Construtor.
	 * @param jFrameListaAtributos	O JFrameAtributoListar que instanciou o JFrameAtributoSelecionar. 
	 */
	@SuppressWarnings("unchecked")
	public JFrameAttributeSelector(JFrameAttributeList jFrameListaAtributos) {
		arrayNomeAtributo = (Vector<String>) AttributeList.getArrayNomeAtributo().clone();
		arrayTipoAtributo = (Vector<Integer>) AttributeList.getArrayTipoAtributo().clone();
		arrayUnidadeAtributo = (Vector<String>) AttributeList.getArrayUnidadeAtributo().clone();
		initComponents();
	}

	@SuppressWarnings("unchecked")
	public JFrameAttributeSelector(WindowsManager gerenciador) {
		this.gerenciador = gerenciador;  
		arrayNomeAtributo = (Vector<String>) AttributeList.getArrayNomeAtributo().clone();
		arrayTipoAtributo = (Vector<Integer>) AttributeList.getArrayTipoAtributo().clone();
		arrayUnidadeAtributo = (Vector<String>) AttributeList.getArrayUnidadeAtributo().clone();
		initComponents();
	}

	/**
	 * Chama o JFrameAtributoSelecionar para editar um atributo.
	 * @param linhaAtributo		Um int contendo a posição do atributo que será modificado no array de atributos. 
	 */
	public void setAlterarAtributo(int linhaAtributo, Attribute atributo){
		this.atributoSelecionado = atributo;
		addAtributoNaListaDoComboBox(atributo);
		jLabelTipoAtributo.setText(AttributeList.getTipoAtributoString(atributo.getType()));
		jLabelUnidadeAtributo.setText(atributo.getUnitOfMeasure());
		jTextFieldFrequencia.setText(""+atributo.getReadingRate());
		setTitle(Internationalization.TITULO_TL_ALTERAR_ATRIBUTO);
		this.indexAtributo = linhaAtributo;
		operacao = Constants.OP_ALTERAR;
	}

	/**
	 * Chama o JFrameAtributoSelecionar para selecionar um atributo.
	 */
	public void setCriarAtributo() {//seta vazio para o usuário escolher
		jLabelTipoAtributo.setText( getStringTipoAtributo( arrayTipoAtributo.get(0) ) );
		jLabelUnidadeAtributo.setText(arrayUnidadeAtributo.get(0));
		jTextFieldFrequencia.setText(""+12);
		setTitle(Internationalization.TITULO_TL_SELECIONAR_ATRIBUTO);
		operacao = Constants.OP_CRIAR;
	}

	/**
	 * Adiciona um atributo excluido da lista de atributos selecionados na lista de atributos disponíveis.
	 * @param atributo	Um Atributo excluido da lista de atributos selecionados.
	 */
	public void addAtributoNaListaDoComboBox(Attribute atributo){
		arrayNomeAtributo.add(0, atributo.getName());//0 é a posição em que o atributo deve ser colocado para edição
		arrayTipoAtributo.add(0, atributo.getType());
		arrayUnidadeAtributo.add(0, atributo.getUnitOfMeasure());
		jComboBoxOpcoesAtributos.setModel(new javax.swing.DefaultComboBoxModel<String>(arrayNomeAtributo));
	}

	/**
	 * Reseta a lista de atributos disponíveis.
	 * Deve ser chamado ao instanciar o JFrameAtributoSelecionar ou ao clicar no botão "Finalizar" do JFrameAtributoListar.
	 */
	@SuppressWarnings("unchecked")
	public void initCampos(){
		arrayNomeAtributo = (Vector<String>) AttributeList.getArrayNomeAtributo().clone();
		arrayTipoAtributo = (Vector<Integer>) AttributeList.getArrayTipoAtributo().clone();
		arrayUnidadeAtributo = (Vector<String>) AttributeList.getArrayUnidadeAtributo().clone();
		jComboBoxOpcoesAtributos.setModel(new javax.swing.DefaultComboBoxModel<String>(arrayNomeAtributo));
	}


	/* Seleciona um atributo cadastrado e o adiciona no ArrayList de atributos e na tabela de JFrameAtributoListar.  */
	private void selecionarAtributo(){
		gerenciador.adicionaAtributo(getAtributoSelecionado());
	}

	/* Altera as informações de um atributo selecionado.  */
	private void atualizarAtributo(){
		int index = jComboBoxOpcoesAtributos.getSelectedIndex();
		if (index == 0){ //0 corresponde ao atributo que foi passado para edição
			atributoSelecionado.setReadingRate(Integer.parseInt(jTextFieldFrequencia.getText().toString()));
			gerenciador.atualizaAtributo(indexAtributo, atributoSelecionado);
			removerAtributoDaListaDoComboBox(index);
		} else {
			gerenciador.atualizaAtributo(indexAtributo, getAtributoSelecionado());
		}
	}

	private Attribute getAtributoSelecionado(){
		int index = jComboBoxOpcoesAtributos.getSelectedIndex();
		Attribute atributo = new Attribute();
		preencheAtributo(atributo, index);
		removerAtributoDaListaDoComboBox(index);
		return atributo;
	}

	/* Preenche um atributo a partir das informações dos campos da tela. */
	private void preencheAtributo(Attribute atributo, int index){
		atributo.setName(arrayNomeAtributo.get(index));//TODO MODIFICADO
		atributo.setType(arrayTipoAtributo.get(index));
		atributo.setUnitOfMeasure(arrayUnidadeAtributo.get(index));
		atributo.setReadingRate(Integer.parseInt(jTextFieldFrequencia.getText().toString()));
	}

	/* Remove um atributo já selecionado da lista de atributos disponíveis. */
	private void removerAtributoDaListaDoComboBox(int index){
		arrayNomeAtributo.remove(index);
		arrayTipoAtributo.remove(index);
		arrayUnidadeAtributo.remove(index);
		jComboBoxOpcoesAtributos.setModel(new javax.swing.DefaultComboBoxModel<String>(arrayNomeAtributo));
	}

	/* Retorna a String contendo o atributo. */
	private String getStringTipoAtributo(int tipo){
		switch (tipo){
		case Attribute.ENVIRONMENTAL: 
			return Internationalization.TIPO_ATRIBUTO_AMBIENTAL;
		case Attribute.BEHAVIORAL: 
			return Internationalization.TIPO_ATRIBUTO_COMPORTAMENTAL;
		default: 
			return Internationalization.TIPO_ATRIBUTO_FISIOLOGICO;
		}
	}

	public void removerAtributoUtilizado(int index) {
		arrayNomeAtributo.remove(index);
		arrayTipoAtributo.remove(index);
		arrayUnidadeAtributo.remove(index);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jComboBoxOpcoesAtributos = new javax.swing.JComboBox<String>();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jTextFieldFrequencia = new javax.swing.JTextField();
		jLabelTipoAtributo = new javax.swing.JLabel();
		jButtonSalvar = new javax.swing.JButton();
		jLabelUnidadeAtributo = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jButtonCancelar = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jLabel1.setText(Internationalization.CP_NOME_DO_ATRIBUTO+":");
		jComboBoxOpcoesAtributos.setModel(new javax.swing.DefaultComboBoxModel<String>(arrayNomeAtributo));
		jComboBoxOpcoesAtributos.setSelectedIndex(1);
		jComboBoxOpcoesAtributos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBoxOpcoesAtributosActionPerformed(evt);
			}
		});
		jLabel2.setText(Internationalization.CP_FREQUENCIA+":");
		jLabel3.setText(Internationalization.CP_UNIDADE_DE_MEDIDA+":");
		jTextFieldFrequencia.setText("12");
		jLabelTipoAtributo.setText("--------");
		jButtonSalvar.setText(Internationalization.BT_SALVAR);
		jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonSalvarActionPerformed(evt); }
		});
		jLabelUnidadeAtributo.setText("bpm");
		jLabel7.setText("("+Internationalization.CP_LEITURA_POR_MIN+")");
		jButtonCancelar.setText(Internationalization.BT_CANCELAR);
		jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonCancelarActionPerformed(evt); }
		});
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup().addGap(244, 244, 244)
			.addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(18, 18, 18)
			.addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
			.addGroup(jPanel1Layout.createSequentialGroup()
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
			.addComponent(jLabel1).addComponent(jLabel2)).addGap(4, 4, 4)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup()
			.addComponent(jComboBoxOpcoesAtributos, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jLabelTipoAtributo)).addGroup(jPanel1Layout.createSequentialGroup()
			.addComponent(jTextFieldFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jLabel7)))))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabelUnidadeAtributo)))
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(26, Short.MAX_VALUE)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addComponent(jLabel1)
			.addComponent(jComboBoxOpcoesAtributos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
			.addComponent(jLabelTipoAtributo, javax.swing.GroupLayout.Alignment.TRAILING))
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addComponent(jLabel7)
			.addComponent(jTextFieldFrequencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addComponent(jLabel3)
			.addComponent(jLabelUnidadeAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addComponent(jButtonCancelar).addComponent(jButtonSalvar)).addContainerGap())
		);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
			.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
			.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pack();
	}
	
	/* Volta para a tela lista atributos. */
	private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		if (operacao == OP_ALTERAR){
			gerenciador.atualizaAtributo(indexAtributo, atributoSelecionado);//getAtributo(0)
			removerAtributoDaListaDoComboBox(0);//0 é a posição em que o atributo foi colocado para ser editado
		}
		gerenciador.initListaAtributos();
	}

	private void jComboBoxOpcoesAtributosActionPerformed(java.awt.event.ActionEvent evt) {
		int index = jComboBoxOpcoesAtributos.getSelectedIndex();
		jLabelUnidadeAtributo.setText(arrayUnidadeAtributo.get(index));
		jLabelTipoAtributo.setText( getStringTipoAtributo( arrayTipoAtributo.get(index) ) );
	}

	/* Adiciona o atributo na lista e volta para a tela lista atributos. */
	private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {
		this.setVisible(false);
		if (this.operacao == Constants.OP_ALTERAR ) {
			atualizarAtributo();
		} else { //this.operacao == Constantes.OP_CRIAR
			selecionarAtributo();
		}
		gerenciador.initListaAtributos();
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
			java.util.logging.Logger.getLogger(JFrameAttributeSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JFrameAttributeSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {	new JFrameAttributeSelector().setVisible(true); }
		});
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButtonCancelar;
	private javax.swing.JButton jButtonSalvar;
	private javax.swing.JComboBox<String> jComboBoxOpcoesAtributos;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabelTipoAtributo;
	private javax.swing.JLabel jLabelUnidadeAtributo;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JTextField jTextFieldFrequencia;
	// End of variables declaration//GEN-END:variables
}
