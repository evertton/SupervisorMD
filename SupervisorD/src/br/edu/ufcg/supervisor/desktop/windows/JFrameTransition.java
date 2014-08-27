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

import br.edu.ufcg.supervisor.desktop.util.Constants;
import br.edu.ufcg.supervisor.desktop.util.Internacionalizar;
import br.edu.ufcg.supervisor.model.Automaton;
import br.edu.ufcg.supervisor.model.State;
import br.edu.ufcg.supervisor.model.Transition;

/**
 * Tela criar/editar transição. 
 * @author Marcos José, Elthon Oliveira
 */
public class JFrameTransition extends javax.swing.JFrame {
	private static final long serialVersionUID = 2784649746799706506L;
	//atributos
	private int operacao;
	private int indexTransicao;
	private WindowsManager gerenciador;
	private int indexAtributoSelecionado;

	/** Creates new form JFrameCriarEditarTansicao */
	public JFrameTransition() {
		initComponents();
		setTitle("Adicionar Transição - Heart rate");
	}

	/**
	 * Construtor.
	 * @param listarTransicao	O JFrameTransicaoListar que instanciou a tela JFrameTransicao.
	 */
	public JFrameTransition(JFrameTransitionList listarTransicao) {
		initComponents();
	}
	
	public JFrameTransition(WindowsManager gerenciador) {
		initComponents();
		this.gerenciador = gerenciador;
	}
	/**
	 * Ativa o modo alterar transição.
	 * @param indexAtributo	Um int contendo a posição do atributo que deve ser alterado.
	 */
	
	public void setModoAlterarTransicao(int indexAtributo, int indexTransicao){
		this.indexTransicao = indexTransicao;
		this.indexAtributoSelecionado = indexAtributo;
		this.operacao = Constants.OP_ALTERAR;
		setTitle(Internacionalizar.TITULO_TL_ALTERAR_TRANSICAO+" - "+gerenciador.getAtributo(indexAtributo).getNome());//+"/"+transicao.getNome());
		Automaton automato = gerenciador.getAtributo(indexAtributo).getAutomato();
		Transition transicao = automato.getArrayTransicoes().get(indexTransicao);
		jTextFieldNome.setText(transicao.getRotulo());
		Vector<State> vectorEstado = automato.getVectorEstados();
		String nomeEstadoInicial = transicao.getEstadoOrigem().toString();
		String nomeEstadoFinal = transicao.getEstadoDestino().toString();
		int indexEstadoInicial = -1;
		int indexEstadoFinal = -1;
		for(int i = 0; i<vectorEstado.size(); i++){
			String nomeEstado = vectorEstado.get(i).toString();
			if(nomeEstado.equals(nomeEstadoInicial)) indexEstadoInicial = i;
			if(nomeEstado.equals(nomeEstadoFinal)) indexEstadoFinal = i;
		}
		jComboBoxEstadoDestino.setModel(
			new javax.swing.DefaultComboBoxModel<State>(vectorEstado));
			jComboBoxEstadoDestino.setSelectedIndex(indexEstadoFinal);
			jComboBoxEstadoOrigem.setModel(new javax.swing.DefaultComboBoxModel<State>(vectorEstado));
			jComboBoxEstadoOrigem.setSelectedIndex(indexEstadoInicial);
			jTextAreaMensagem.setText(transicao.getMensagem());
	}

	/**
	 * Ativa o modo criar transição.
	 */
	public void setModoCriarTransicao(int indexAtributo){//seta vazio para o usuário escolher
		setTitle(Internacionalizar.TITULO_TL_CRIAR_TRANSICAO+" - "+gerenciador.getAtributo(indexAtributo).getNome());
		this.indexAtributoSelecionado = indexAtributo;
		this.operacao = Constants.OP_CRIAR;
		jTextFieldNome.setText("");
		jTextAreaMensagem.setText("");
		Vector<State> vectorEstado = gerenciador.getAtributo(indexAtributo).getAutomato().getVectorEstados();
		jComboBoxEstadoDestino.setModel(new javax.swing.DefaultComboBoxModel<State>(vectorEstado));
		jComboBoxEstadoOrigem.setModel(	new javax.swing.DefaultComboBoxModel<State>(vectorEstado));
		jComboBoxEstadoDestino.setSelectedIndex(0);
		jComboBoxEstadoOrigem.setSelectedIndex(0);
	}

	/* Cria uma transição a partir das informações da tela */
	private void criarTransicao(){
		Transition transicao = new Transition();
		preencheTransicao(transicao);
		gerenciador.getAtributo(indexAtributoSelecionado).getAutomato().addTransicao(transicao);
	}

	/* Atualiza uma transição a partir das informações da tela */
	private void atualizarTransicao(){
		Transition transicao = gerenciador.getAtributo(indexAtributoSelecionado).getAutomato().getArrayTransicoes().get(indexTransicao);
		preencheTransicao(transicao);
	}

	/* Instancia uma transição a partir das informações da tela */
	private void preencheTransicao(Transition transicao){
		transicao.setMensagem(jTextAreaMensagem.getText().toString());
		transicao.setRotulo(jTextFieldNome.getText().toString());
		int indexEstadoInicial = jComboBoxEstadoOrigem.getSelectedIndex();
		Automaton automato = gerenciador.getAtributo(indexAtributoSelecionado).getAutomato();
		transicao.setEstadoOrigem(automato.getVectorEstados().get(indexEstadoInicial));
		int indexEstadoFinal = jComboBoxEstadoDestino.getSelectedIndex();
		transicao.setEstadoDestino(automato.getVectorEstados().get(indexEstadoFinal));
	}

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxEstadoOrigem = new javax.swing.JComboBox<State>();
        jTextFieldNome = new javax.swing.JTextField();
        jComboBoxEstadoDestino = new javax.swing.JComboBox<State>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaMensagem = new javax.swing.JTextArea();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText(Internacionalizar.CP_NOME_ACAO+":");
        jLabel2.setText(Internacionalizar.CP_ESTADO_DE_ORIGEM+":");
        jLabel3.setText(Internacionalizar.CP_ESTADO_DE_DESTINO+":");
        jLabel4.setText(Internacionalizar.CP_MENSAGEM_AO_USUARIO+":");
        jTextFieldNome.setText("");
        jTextAreaMensagem.setColumns(20);
        jTextAreaMensagem.setRows(5);
        jScrollPane1.setViewportView(jTextAreaMensagem);
        jButtonSalvar.setText(Internacionalizar.BT_SALVAR);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonSalvarActionPerformed(evt); }
        });
        jButtonCancelar.setText(Internacionalizar.BT_CANCELAR);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonCancelarActionPerformed(evt); }
        });
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4).addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2).addComponent(jLabel3)).addComponent(jLabel1))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBoxEstadoOrigem, 0, 258, Short.MAX_VALUE)
            .addComponent(jComboBoxEstadoDestino, 0, 258, Short.MAX_VALUE)
            .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel2)
            .addComponent(jComboBoxEstadoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel3)
            .addComponent(jComboBoxEstadoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30).addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jButtonSalvar).addComponent(jButtonCancelar)).addContainerGap(21, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
           .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
    }

	/* Volta para a tela lista transição */
	private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                                
		this.setVisible(false);                      
		gerenciador.initListaTransicao(indexAtributoSelecionado);
	}                                                 

	/* Salva ou altera a transição e volta para a tela lista transição */
	private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                                
		if (operacao == Constants.OP_CRIAR) criarTransicao();
		else if (operacao == Constants.OP_ALTERAR) atualizarTransicao();
		this.setVisible(false);
		gerenciador.initListaTransicao(indexAtributoSelecionado);
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
			java.util.logging.Logger.getLogger(JFrameTransition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JFrameTransition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JFrameTransition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JFrameTransition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {	new JFrameTransition().setVisible(true);	}
		});
	}

    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<State> jComboBoxEstadoDestino;
    private javax.swing.JComboBox<State> jComboBoxEstadoOrigem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaMensagem;
    private javax.swing.JTextField jTextFieldNome;
}