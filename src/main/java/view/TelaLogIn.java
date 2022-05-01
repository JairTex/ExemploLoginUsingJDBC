package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaLogIn extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField pswSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogIn frame = new TelaLogIn();
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
	public TelaLogIn() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Dialog", Font.BOLD, 17));
		lblUsuario.setBounds(55, 64, 84, 36);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSenha.setBounds(55, 116, 84, 36);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setFont(new Font("DialogInput", Font.PLAIN, 16));
		tfUsuario.setBounds(149, 75, 214, 20);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		pswSenha = new JPasswordField();
		pswSenha.setFont(new Font("DialogInput", Font.PLAIN, 16));
		pswSenha.setBounds(149, 127, 214, 20);
		contentPane.add(pswSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.getConnection();
					
					String sql = "select * from dados_usuario where usuario=? and senha =?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfUsuario.getText());
					stmt.setString(2, new String(pswSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Usuário encontrado");
					}
					else {
						JOptionPane.showMessageDialog(null, "Usuário Inexistênte");
					}
					
					stmt.close();
					con.close();
					TelaCadastro telaCadastro = new TelaCadastro();
					telaCadastro.setVisible(true);

					setVisible(false);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnLogin.setBackground(new Color(192, 192, 192));
		btnLogin.setForeground(new Color(0, 0, 128));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 13));
		btnLogin.setBounds(273, 179, 89, 23);
		contentPane.add(btnLogin);
	}
}
