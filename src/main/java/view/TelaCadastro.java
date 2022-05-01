package view;

import java.awt.BorderLayout;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfCadUsuario;
	private JPasswordField pswCadSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() {
		setResizable(false);
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadUsuario = new JLabel("Usuario:");
		lblCadUsuario.setFont(new Font("Dialog", Font.BOLD, 17));
		lblCadUsuario.setBounds(58, 56, 84, 36);
		contentPane.add(lblCadUsuario);
		
		tfCadUsuario = new JTextField();
		tfCadUsuario.setFont(new Font("DialogInput", Font.PLAIN, 16));
		tfCadUsuario.setColumns(10);
		tfCadUsuario.setBounds(152, 67, 214, 20);
		contentPane.add(tfCadUsuario);
		
		JLabel lblCadSenha = new JLabel("Senha:");
		lblCadSenha.setFont(new Font("Dialog", Font.BOLD, 17));
		lblCadSenha.setBounds(58, 108, 84, 36);
		contentPane.add(lblCadSenha);
		
		pswCadSenha = new JPasswordField();
		pswCadSenha.setFont(new Font("DialogInput", Font.PLAIN, 16));
		pswCadSenha.setBounds(152, 119, 214, 20);
		contentPane.add(pswCadSenha);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.getConnection();
					
					String sql = "INSERT INTO dados_usuario (usuario, senha) values ( ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfCadUsuario.getText());
					stmt.setString(2, new String(pswCadSenha.getPassword()));
					
					stmt.execute();
					
					stmt.close();
					
					JOptionPane.showMessageDialog(null, "Usuário Cadastrado!!");
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCadastrar.setForeground(new Color(0, 0, 128));
		btnCadastrar.setFont(new Font("Dialog", Font.BOLD, 13));
		btnCadastrar.setBackground(Color.LIGHT_GRAY);
		btnCadastrar.setBounds(266, 179, 100, 20);
		contentPane.add(btnCadastrar);
	}

}
