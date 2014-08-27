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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.edu.ufcg.supervisor.desktop.util.Internacionalizar;
import br.edu.ufcg.supervisor.model.Attribute;
import br.edu.ufcg.supervisor.model.Automaton;
import br.edu.ufcg.supervisor.model.State;
import br.edu.ufcg.supervisor.model.Transition;

/**
 * Tela lista estados. 
 * @author Marcos José, Elthon Oliveira
 */
public class JFrameStateList extends javax.swing.JFrame {
	private static final long serialVersionUID = 5095677686929910723L;
	// Atributos
	private DefaultTableModel model;
	private Automaton automato;
	private Attribute atributo;
	private WindowsManager gerenciador;
	private int indexAtributoSelecionado;

	/** Construtor */
	public JFrameStateList() {
		initComponents();
		this.model = (DefaultTableModel) jTable1.getModel();
		setTitle("Lista de Estados - Heart rate"); //TODO MODIFICAR
	}

	/**
	 * Construtor.
	 * @param jFrameListaAtributo	O JFrameAtributoListar que instanciou o JFrameEstadoListar. 
	 */
	public JFrameStateList(JFrameAttributeList jFrameListaAtributo) {
		initComponents();
		this.model = (DefaultTableModel) jTable1.getModel();
	}

	//TODO NOVO
	public JFrameStateList(WindowsManager gerenciador) {
		initComponents();
		this.gerenciador = gerenciador;
		this.model = (DefaultTableModel) jTable1.getModel();
	}

	/**
	 * Lista os estados do automato de um determinado atributo.
	 * @param linha		Um int contendo a posição do atributo no array de atributos.
	 */
	public void listarEstados(int linha){
		this.indexAtributoSelecionado = linha;
		int qtd = model.getRowCount() - 1;
		for (; qtd>=0; qtd--) model.removeRow(qtd);
		this.atributo = gerenciador.getArrayAtributos().get(linha);
		this.automato = this.atributo.getAutomato();//automato;
		for(State estado : automato.getVectorEstados()) addEstadoApenasNaTabela(estado);
		setTitle(Internacionalizar.TITULO_TL_LISTA_ESTADOS + " - " + atributo.getNome()); 
	}


	/* Adiciona os estados de um automato apenas na tabela  */
	private void addEstadoApenasNaTabela(State estado) {
		Object[] linha = new Object[] {
			estado.getNome(), estado.getOperadorValorMinimoString(),
			estado.getValorMinimo(), estado.getValorMaximo(),
			estado.getOperadorValorMaximoString(),	estado.getClassificacaoString()};
			model.addRow(linha);
	}

	/**
	 * Adiciona um estado na tabela e no automato.
	 * @param estado	O Estado que será adicionado.
	 */
	public void addEstado(State estado) {
		Object[] linha = new Object[] {
			estado.getNome(), estado.getOperadorValorMinimoString(),
			estado.getValorMinimo(), estado.getValorMaximo(),
			estado.getOperadorValorMaximoString(), estado.getClassificacaoString()
		};
		model.addRow(linha);
		automato.addEstado(estado);//Comentar para não dar erro nos testes
	}

	/**
	 * Remove um estado da tabela e do automato.
	 * @param fEstado	O Estado que será adicionado.
	 */
	public void removerEstado(){
		int linhaSelecionada = jTable1.getSelectedRow();
		removerEstado(linhaSelecionada);
	}

	@SuppressWarnings("unchecked")
	private void removerEstado(int index){
		model.removeRow(index);
		State e = automato.getVectorEstados().get(index);
		ArrayList<Transition> arrayTransicao = automato.getArrayTransicoes();
		for ( Transition t : ( (ArrayList<Transition>) automato.getArrayTransicoes().clone() ) ){
			if (t.getEstadoOrigem().equals(e) || t.getEstadoDestino().equals(e)){
				arrayTransicao.remove(t);
			}
		}
		automato.getVectorEstados().remove(index);	
	}

	/**
	 * Atualiza as informações de um estado na tablela.
	 * @param index		A posição do estado na tabela.
	 * @param estado	O Estado atualizado.
	 */
	public void atualizarEstadoNaTabela(int index, State estado){
		model.removeRow(index);
		Object[] linha = new Object[] {
			estado.getNome(), estado.getOperadorValorMinimoString(),
			estado.getValorMinimo(), estado.getValorMaximo(),
			estado.getOperadorValorMaximoString(), estado.getClassificacaoString()
		};
		model.addRow(linha);
	}

	/**
	 * Retorna o automato do atributo.
	 * @return	Um Automato. 
	 */
	public Automaton getAutomato(){
		return automato;
	}

	/**
	 * Retorna o atributo.
	 * @return	Um Atributo. 
	 */
	public Attribute getAtributo(){
		return this.atributo;
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jButtonAdicionar = new javax.swing.JButton();
		jButtonEditar = new javax.swing.JButton();
		jButtonRemover = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jButtonTransicoes = new javax.swing.JButton();
		jButtonRemoverTodos = new javax.swing.JButton();
		jButtonVoltar = new javax.swing.JButton();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jButtonAdicionar.setText(Internacionalizar.BT_ADICIONAR);
		jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAdicionarActionPerformed(evt);
			}
		});
		jButtonEditar.setText(Internacionalizar.BT_EDITAR);
		jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonEditarActionPerformed(evt);
			}
		});
		jButtonRemover.setText(Internacionalizar.BT_REMOVER);
		jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRemoverActionPerformed(evt);
			}
		});
		jTable1.setModel(new javax.swing.table.DefaultTableModel(
			new Object [][] {},
			new String [] {
				Internacionalizar.CP_NOME_DO_ESTADO, "", Internacionalizar.CP_VALOR_MINIMO, 
				Internacionalizar.CP_VALOR_MAXIMO, "", Internacionalizar.CP_CLASSIFICACAO
			}) {
				private static final long serialVersionUID = -1790264732478081846L;
				@SuppressWarnings("rawtypes")
				Class[] types = new Class [] {//TODO DIFERENÇA PARA jFrameTransicaoListar
						java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
				};
				@SuppressWarnings("rawtypes")
				public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
			}
		);
		jScrollPane1.setViewportView(jTable1);
		jTable1.getColumnModel().getColumn(1).setMinWidth(15);
		jTable1.getColumnModel().getColumn(1).setMaxWidth(15);
		jTable1.getColumnModel().getColumn(4).setMinWidth(15);
		jTable1.getColumnModel().getColumn(4).setMaxWidth(15);
		jButtonTransicoes.setText(Internacionalizar.BT_TRANSICOES);
		jButtonTransicoes.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonTransicoesActionPerformed(evt);
			}
		});
		jButtonRemoverTodos.setText(Internacionalizar.BT_REMOVER_TUDO);
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
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
			.addGroup(jPanel1Layout.createSequentialGroup()
			.addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addGap(10, 10, 10)
			.addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
			.addComponent(jButtonRemoverTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
			.addComponent(jButtonTransicoes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
			.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
			.addComponent(jButtonAdicionar).addComponent(jButtonRemover)
			.addComponent(jButtonEditar).addComponent(jButtonTransicoes)
			.addComponent(jButtonRemoverTodos)).addGap(18, 18, 18)
			.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
			.addContainerGap())
		);
		jButtonVoltar.setText(Internacionalizar.BT_VOLTAR);
		jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonVoltarActionPerformed(evt);
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
			.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addComponent(jButtonVoltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
			.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup().addContainerGap()
			.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jButtonVoltar)
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pack();
	}

	private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {
		int indexEstado = jTable1.getSelectedRow();
		if (indexEstado>-1){
			gerenciador.initEditarEstado(indexAtributoSelecionado, indexEstado);
			this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, Internacionalizar.MS_SELECIONE_UM_ESTADO,
					"",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/* Vai para a tela de criação de estados. */
	private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {                                                 
		gerenciador.initCriarEstado(indexAtributoSelecionado);
		this.setVisible(false);
	}

	/* Vai para a tela listar transições. */
	private void jButtonTransicoesActionPerformed(java.awt.event.ActionEvent evt) {   
		gerenciador.initListaTransicao(indexAtributoSelecionado);
		this.setVisible(false);
	}

	/* Remove um estado da lista de estados. */
	private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {
		int indexEstado = jTable1.getSelectedRow();
		if (indexEstado>-1){
			removerEstado();
		} else {
			JOptionPane.showMessageDialog(null, Internacionalizar.MS_SELECIONE_UM_ESTADO, 
					"",	JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/* Volta para a tela lista atributos. */
	private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {
		gerenciador.initListaAtributos();
		this.setVisible(false);
	}

	private void jButtonRemoverTodosActionPerformed(java.awt.event.ActionEvent evt) {                                                    
		int qtd = model.getRowCount() - 1;
		for (; qtd >= 0; qtd--)	removerEstado(qtd);
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
			java.util.logging.Logger.getLogger(JFrameStateList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JFrameStateList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JFrameStateList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JFrameStateList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {	new JFrameStateList().setVisible(true); }
		});
	}
	private javax.swing.JButton jButtonAdicionar;
	private javax.swing.JButton jButtonEditar;
	private javax.swing.JButton jButtonRemover;
	private javax.swing.JButton jButtonRemoverTodos;
	private javax.swing.JButton jButtonTransicoes;
	private javax.swing.JButton jButtonVoltar;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
}