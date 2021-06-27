package Classes_de_conexao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Tela_cadastro<SQLException> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfUsuario;
	private JTextField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("rawtypes")
					Tela_cadastro frame = new Tela_cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_cadastro() {
		setTitle("Faz tudo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(22, 11, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(22, 46, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(22, 90, 46, 14);
		contentPane.add(lblSenha);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(93, 8, 131, 20);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(93, 43, 170, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(93, 87, 170, 20);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 234, 402, 94);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					Connection con = Conexao.faz_conexao();
					String sql = "insert into dados_senhas(usuario,senha) value (?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());

					stmt.setString(2,tfSenha.getText());
					
					

					

					stmt.execute();
					
					stmt.close();

					con.close();

					JOptionPane.showMessageDialog(null, "Salvo, DEUS é FIEL!!");

					tfUsuario.setText("");

					tfSenha.setText("");
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				

			}
			
		});
		btnSalvar.setBounds(10, 21, 89, 23);
		panel.add(btnSalvar);
		
	}
	
}


