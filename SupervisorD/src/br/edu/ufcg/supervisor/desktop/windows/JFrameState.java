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
import br.edu.ufcg.supervisor.desktop.util.Internacionalizar;
import br.edu.ufcg.supervisor.model.State;

/**
 * Tela criar/editar estado. 
 * @author Marcos José, Elthon Oliveira
 */
public class JFrameState extends javax.swing.JFrame {
	private static final long serialVersionUID = -4978199252366781145L;
	// Variables declaration - do not modify//GEN-BEGIN:variables
	//private javax.swing.ButtonGroup buttonGroupOpcoes;
	private javax.swing.JButton jButtonCancelar;
	private javax.swing.JButton jButtonSalvar;
	private javax.swing.JComboBox<String> jComboBoxClassificacao;
	private javax.swing.JComboBox<Integer> jComboBoxMarcadorMaximo;
	private javax.swing.JComboBox<Integer> jComboBoxMarcadorMinimo;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabelAtributo;
	private javax.swing.JLabel jLabelNomeDoAtributo;
	private javax.swing.JLabel jLabelUnidadeDeMedida1;
	private javax.swing.JLabel jLabelUnidadeDeMedida2;
	private javax.swing.JLabel jLabelValorMedido;
	private javax.swing.JTextField jTextFieldNomeEstado;
	private javax.swing.JTextField jTextFieldValorMaximo;
	private javax.swing.JTextField jTextFieldValorMinimo;
	// End of variables declaration//GEN-END:variables
	//atributos
	private Integer operacao = null;
	private int indexEstado;
	private Vector<String> arrayClassificacaoEstado;
	private WindowsManager gerenciador;
	private int indexAtributoSelecionado;

    /**
     * Construtor.
     */
    public JFrameState() {
        initComponents();
        setTitle("Adicionar Estado - Heart hate");
        jLabelUnidadeDeMedida1.setText("(bpm)");
        jLabelUnidadeDeMedida2.setText("(bpm)");
        arrayClassificacaoEstado = AttributeList.getArrayClassificacaoEstado();
    }
    
    /**
     * Construtor.
     * @param jFrameListarEstados	O JFrameEstadoListar que criou o JFrameEstado.  
     */
    public JFrameState(JFrameStateList jFrameListarEstados) {
		arrayClassificacaoEstado = AttributeList.getArrayClassificacaoEstado();
		initComponents();
	}
    
    public JFrameState(WindowsManager gerenciador) {
    	this.gerenciador = gerenciador;
  		arrayClassificacaoEstado = AttributeList.getArrayClassificacaoEstado();
		initComponents();
  	}
    
    /**
     * Seta o modo edição, possibilitando editar um estado.
     * @param indexAtributo	Um int contendo a posição do atributo que terá um estado alterado.
     * @param indexEstado	Um int contendo a posição do estado que será alterado.
     */
    public void setAlterarEstado(int indexAtributo, int indexEstado){
    	this.indexAtributoSelecionado = indexAtributo;
    	jLabelNomeDoAtributo.setText( gerenciador.getAtributo(indexAtributo).getNome() );
    	State estado = gerenciador.getAtributo(indexAtributo).getAutomato().getVectorEstados().get(indexEstado);
		String unidade = gerenciador.getAtributo(indexAtributo).getUnidadeDeMedida();
		jTextFieldNomeEstado.setText(estado.getNome());
		jTextFieldValorMaximo.setText(""+estado.getValorMaximo());
		jTextFieldValorMinimo.setText(""+estado.getValorMinimo());
		jComboBoxMarcadorMaximo.setSelectedIndex(estado.getOperadorValorMaximo());
		jComboBoxMarcadorMinimo.setSelectedIndex(estado.getOperadorValorMinimo());
		jComboBoxClassificacao.setSelectedIndex(estado.getClassificacao());
		jLabelUnidadeDeMedida1.setText("("+unidade+")");
		jLabelUnidadeDeMedida2.setText("("+unidade+")");
		this.indexEstado = indexEstado;
		operacao = Constants.OP_ALTERAR;
		setTitle(Internacionalizar.TITULO_TL_ALTERAR_ESTADO+" - "+gerenciador.getAtributo(indexAtributoSelecionado).getNome());//+"/"+estado.getNome()
	}
    
/**
 * Seta o modo de criação, posibilitando criar um novo estado.
 */
	public void setCriarEstado(int indexAtributo){//seta vazio para o usuário escolher
		this.indexAtributoSelecionado = indexAtributo;
		jLabelNomeDoAtributo.setText( gerenciador.getAtributo(indexAtributoSelecionado).getNome() );
		jTextFieldNomeEstado.setText("");
		jTextFieldValorMaximo.setText("");
		jTextFieldValorMinimo.setText("");	
		String unidade = gerenciador.getAtributo(indexAtributoSelecionado).getUnidadeDeMedida();
		jLabelUnidadeDeMedida1.setText("("+unidade+")");
		jLabelUnidadeDeMedida2.setText("("+unidade+")");
		operacao = Constants.OP_CRIAR;
		setTitle(Internacionalizar.TITULO_TL_CRIAR_ESTADO+" - "+gerenciador.getAtributo(indexAtributoSelecionado).getNome());//+"/Novo Estado"
	}

	/* Preenche um estado com as informações da tela. */
	private State preencheEstado(State estado){
		estado.setNome(jTextFieldNomeEstado.getText());
		estado.setValorMaximo(Float.parseFloat(jTextFieldValorMaximo.getText()));
		estado.setValorMinimo(Float.parseFloat(jTextFieldValorMinimo.getText()));
		int indexClassificacao = jComboBoxClassificacao.getSelectedIndex();
		estado.setOperadorValorMaximo(jComboBoxMarcadorMaximo.getSelectedIndex());
		estado.setOperadorValorMinimo(jComboBoxMarcadorMinimo.getSelectedIndex());
		estado.setClassificacao(indexClassificacao);
		return estado;
	}

	/* Preenche um estado com as informações da tela e o adiciona na lista de estados. */
	private void preencheEstado_Criar(){
		State estado = new State();
		preencheEstado(estado);
		gerenciador.getAtributo(indexAtributoSelecionado).getAutomato().addEstado(estado);
	}

	/* Preenche um estado com as informações da tela para atualizá-lo . */
	private void preencheEstado_Atualizar(){
		State estado = gerenciador.getAtributo(indexAtributoSelecionado).getAutomato().getVectorEstados().get(indexEstado);//().get(linhaAtributo);//new Estado();
		preencheEstado(estado);
	}
       
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void initComponents() {
        jLabelAtributo = new javax.swing.JLabel();
        jLabelNomeDoAtributo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldValorMinimo = new javax.swing.JTextField();
        jComboBoxMarcadorMinimo = new javax.swing.JComboBox<Integer>();
        jTextFieldValorMaximo = new javax.swing.JTextField();
        jComboBoxMarcadorMaximo = new javax.swing.JComboBox<Integer>();
        jLabelUnidadeDeMedida1 = new javax.swing.JLabel();
        jLabelUnidadeDeMedida2 = new javax.swing.JLabel();
        jLabelValorMedido = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jComboBoxClassificacao = new javax.swing.JComboBox<String>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNomeEstado = new javax.swing.JTextField();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabelAtributo.setText(Internacionalizar.CP_ATRIBUTO+":");
        jLabelNomeDoAtributo.setText("--");
        jLabel1.setText(Internacionalizar.CP_VALOR_MINIMO+":");
        jLabel2.setText(Internacionalizar.CP_VALOR_MAXIMO+":");
        jTextFieldValorMinimo.setText("97");
        jComboBoxMarcadorMinimo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "[", "(" }));
        jTextFieldValorMaximo.setText("156");
        jComboBoxMarcadorMaximo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "]", ")" }));
        jLabelUnidadeDeMedida1.setText(Internacionalizar.CP_UNIDADE);
        jLabelUnidadeDeMedida2.setText(Internacionalizar.CP_UNIDADE);
        jLabelValorMedido.setText(Internacionalizar.CP_INTERVALO_DE_MEDICAO);
        jButtonSalvar.setText(Internacionalizar.BT_SALVAR);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });
        jButtonCancelar.setText(Internacionalizar.BT_CANCELAR);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jComboBoxClassificacao.setModel(new javax.swing.DefaultComboBoxModel<String>(arrayClassificacaoEstado));
        jLabel4.setText(Internacionalizar.CP_CLASSIFICACAO+":");
        jLabel5.setText(Internacionalizar.CP_NOME+":");
        jTextFieldNomeEstado.setText("---");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
	        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabelUnidadeDeMedida1)).addGroup(layout.createSequentialGroup()
            .addComponent(jComboBoxMarcadorMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextFieldValorMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(40, 40, 40).addComponent(jLabelValorMedido))).addGap(33, 33, 33)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
            .addComponent(jTextFieldValorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jComboBoxMarcadorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabelUnidadeDeMedida2)).addGroup(layout.createSequentialGroup().addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTextFieldNomeEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, Short.MAX_VALUE)).addGroup(layout.createSequentialGroup()
            .addComponent(jLabel4).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jComboBoxClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup().addComponent(jLabelAtributo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabelNomeDoAtributo))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18).addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabelAtributo).addComponent(jLabelNomeDoAtributo)).addGap(25, 25, 25)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTextFieldNomeEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel5)).addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel1).addComponent(jLabelUnidadeDeMedida1).addComponent(jLabel2)
            .addComponent(jLabelUnidadeDeMedida2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jComboBoxMarcadorMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jTextFieldValorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jComboBoxMarcadorMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabelValorMedido)
            .addComponent(jTextFieldValorMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(31, 31, 31).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel4)
            .addComponent(jComboBoxClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(22, 22, 22).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jButtonSalvar).addComponent(jButtonCancelar)).addContainerGap())
        );
        pack();
    }

    /* Volta para a tela listar estados. */
    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                                
    	gerenciador.initListaEstado(indexAtributoSelecionado);
    	this.setVisible(false);
    }

    /* Salva/atualiza e volta para a tela listar estados. */
    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	if (operacao == Constants.OP_CRIAR){
			preencheEstado_Criar();
		} else {
			preencheEstado_Atualizar();
		}
    	this.setVisible(false);
		gerenciador.initListaEstado(indexAtributoSelecionado);
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
            java.util.logging.Logger.getLogger(JFrameState.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameState.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameState.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameState.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { new JFrameState().setVisible(true); }
        });
    }
}