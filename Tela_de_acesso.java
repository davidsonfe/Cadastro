package Classes_de_conexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_acesso extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pfSenhas;
	protected Object tfSenhas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_acesso frame = new Tela_de_acesso();
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
	public Tela_de_acesso() {
		setResizable(false);
		setTitle("Tela de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 272);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setForeground(Color.GREEN);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(25, 24, 93, 46);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setForeground(Color.GREEN);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSenha.setBounds(25, 83, 93, 46);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfUsuario.setBounds(132, 39, 213, 31);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pfSenhas = new JPasswordField();
		pfSenhas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pfSenhas.setBounds(132, 98, 213, 31);
		contentPane.add(pfSenhas);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(Color.BLACK);
		btnEntrar.addActionListener(new ActionListener() {
			

			 public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select *from dados_senhas where usuario=? and senha=?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String(pfSenhas.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						
						//JOptionPane.showMessageDialog(null, "Esse usuario existe");
						
						Tela_cadastro exibir = new Tela_cadastro();
						exibir.setVisible(true);
						
						setVisible(false);
						
						
						
					}else {
						
						JOptionPane.showMessageDialog(null, "usuário/senha incorreto");
						
						
					}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setForeground(Color.GREEN);
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnEntrar.setBounds(166, 168, 136, 37);
		contentPane.add(btnEntrar);
	}
}
