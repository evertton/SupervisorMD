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

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//import br.edu.ufcg.supervisor.base.model.Atributo;
import br.edu.ufcg.supervisor.desktop.util.Internationalization;
import br.edu.ufcg.supervisor.model.Automaton;
import br.edu.ufcg.supervisor.model.State;
import br.edu.ufcg.supervisor.model.Transition;

/**
 * Tela para listar as transições criadas. 
 * @author Marcos José, Elthon Oliveira
 */
public class JFrameTransitionList extends javax.swing.JFrame {
	private static final long serialVersionUID = -6423879445769623094L;
	//Atributos
	private DefaultTableModel model;
	private Automaton automato;
	private WindowsManager gerenciador;
	
	//private String[] colunas = new String [] {
	//		Internationalization.CP_NOME_ACAO, Internationalization.CP_ESTADO_DE_ORIGEM, 
	//		Internationalization.CP_ESTADO_DE_DESTINO, Internationalization.CP_MENSAGEM_AO_USUARIO
	//};
	private int indexAtributoSelecionado;
    
    /**
     * Creates new form JFrameListarTransicao2
     */
    public JFrameTransitionList() {
        initComponents();
		this.model = (DefaultTableModel) jTable1.getModel();
		State e = new State();
		e.setNome("t");
		Transition t = new Transition();
		t.setRotulo("t");
		t.setEstadoOrigem(e);
		t.setEstadoDestino(e);
		addLinha(t);
		addLinha(t);
		addLinha(t);
		setTitle("Lista de Tansições - Heart rate");
    }

    /**
     * Construtor.
     * @param jFrameListarEstado	O JFrameEstadoListar que deseja criar o JFrameTransicaoListar.
     */
    public JFrameTransitionList(JFrameStateList jFrameListarEstado) {
		initComponents();
		this.model = (DefaultTableModel) jTable1.getModel();
	}
    
    public JFrameTransitionList(WindowsManager gerenciador) {
		initComponents();
		this.gerenciador = gerenciador;
		this.model = (DefaultTableModel) jTable1.getModel();
	}
  
    public void listarTransicoes(int indexAtributo){
		this.indexAtributoSelecionado = indexAtributo;
		int qtd = model.getRowCount() - 1;
		for (; qtd>=0; qtd--) model.removeRow(qtd);
		this.automato = gerenciador.getAtributo(indexAtributo).getAutomaton();
		for(Transition transicao : automato.getArrayTransicoes()) addLinha(transicao);
		setTitle(Internationalization.TITULO_TL_LISTA_TRANSICOES+" - "
			+gerenciador.getArrayAtributos().get(indexAtributoSelecionado).getName());
	}

	/**
	 * Adiciona uma nova transição.
	 * @param transicao	Uma transição que será adicionada.
	 */
	public void addLinha(Transition transicao) {
		Object[] linha = new Object[] {
			transicao.getRotulo(), transicao.getEstadoOrigem().getNome(), 
			transicao.getEstadoDestino().getNome(), transicao.getMensagem()};
		model.addRow(linha);
	}
	
	/* Remove uma transição. */
	private void removerLinha(){
    	int linha = jTable1.getSelectedRow();
    	model.removeRow(linha);
		automato.getArrayTransicoes().remove(linha);	
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jButtonAdicionar = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        jButtonRemoverTodas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonVoltar = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jButtonAdicionar.setText(Internationalization.BT_ADICIONAR);
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonAdicionarActionPerformed(evt); }
        });
        jButtonEditar.setText(Internationalization.BT_EDITAR);
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonEditarActionPerformed(evt); }
        });
        jButtonRemover.setText(Internationalization.BT_REMOVER);
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonRemoverActionPerformed(evt); }
        });
        jButtonRemoverTodas.setText(Internationalization.BT_REMOVER_TUDO);
        jButtonRemoverTodas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonRemoverTodasActionPerformed(evt); }
        });
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
       		new Object [][] {},
        		//            new String [] {
        		//                "Nome/Ação", "Estado de Origem", "Estado de Destino", "Mensagem"
        		//            }
        		//colunas
       		new String [] {
   				Internationalization.CP_NOME_ACAO, Internationalization.CP_ESTADO_DE_ORIGEM, 
   				Internationalization.CP_ESTADO_DE_DESTINO, Internationalization.CP_MENSAGEM_AO_USUARIO
       		}) {
				private static final long serialVersionUID = 7717284260760453763L;
				@SuppressWarnings("rawtypes")
				Class[] types = new Class [] {//TODO DIFERENÇA PARA jFrameTransicaoListar
        			java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
				};
        	@SuppressWarnings("rawtypes")
			public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
        });
        jScrollPane1.setViewportView(jTable1);
        jButtonVoltar.setText(Internationalization.BT_VOLTAR);
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { jButtonVoltarActionPerformed(evt); }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
            .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
            .addComponent(jButtonRemoverTodas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jButtonVoltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap()));
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jButtonAdicionar).addComponent(jButtonEditar)
            .addComponent(jButtonRemover).addComponent(jButtonRemoverTodas)).addGap(27, 27, 27)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jButtonVoltar).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        pack();
    }

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	gerenciador.initCriarTransicao(indexAtributoSelecionado);
		this.setVisible(false);
    }

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	int indexTransicaoSelecionada = jTable1.getSelectedRow();
    	if (indexTransicaoSelecionada>-1){
    		gerenciador.initEditarTransicao(indexAtributoSelecionado, indexTransicaoSelecionada);
    		this.setVisible(false);
		} else JOptionPane.showMessageDialog(null,Internationalization.MS_SELECIONE_UMA_TRANSICAO,"",JOptionPane.INFORMATION_MESSAGE);		
    }

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	int indexAtributoSelecionado = jTable1.getSelectedRow();
    	if (indexAtributoSelecionado>-1) removerLinha();
    	else JOptionPane.showMessageDialog(null,Internationalization.MS_SELECIONE_UMA_TRANSICAO,"",JOptionPane.INFORMATION_MESSAGE);
    }

    private void jButtonRemoverTodasActionPerformed(java.awt.event.ActionEvent evt) {                                                    
    	int qtd = model.getRowCount() - 1;
		for (; qtd >= 0; qtd--){
			model.removeRow(qtd);
			automato.getArrayTransicoes().remove(qtd);
		}
    }

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	gerenciador.initListaEstado(indexAtributoSelecionado);
		this.setVisible(false);
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
            java.util.logging.Logger.getLogger(JFrameTransitionList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameTransitionList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameTransitionList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameTransitionList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    	java.awt.EventQueue.invokeLater(new Runnable() {
    		public void run() { new JFrameTransitionList().setVisible(true); }
        });
    }
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonRemoverTodas;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}