package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import Controller.MainController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Rectangle;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

	MainController controller;
	private JTextField txfNomeUtente;
	private JPasswordField psfPassword;
	private JLabel lblPassword;
	private JButton btnLogin;


	/**
	 * Create the frame.
	 */
	public LoginFrame(MainController c) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(5, 5, 5, 5));
		setTitle("Autentificazione");
		setPreferredSize(new Dimension(472, 363));
		setMinimumSize(new Dimension(472, 363));
		setAlwaysOnTop(true);
		setBackground(SystemColor.inactiveCaption);
		setBounds(100, 100, 471, 362);
		JPanel contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setPreferredSize(new Dimension(0, 0));
		contentPane.setBackground(Color.WHITE);
		contentPane.setAutoscrolls(false);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		setNomeUtente_tf(new JTextField());
		getNomeUtente_tf().setHorizontalAlignment(SwingConstants.LEFT);
		getNomeUtente_tf().setFont(new Font("Calibri", Font.PLAIN, 14));
		getNomeUtente_tf().setColumns(10);
		
		JLabel lblNomeUtente = new JLabel("Nome Utente");
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		setPassword_pf(new JPasswordField());
		getPassword_pf().setHorizontalAlignment(SwingConstants.LEFT);
		getPassword_pf().setFont(new Font("Calibri", Font.PLAIN, 14));
		
		lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		btnLogin = new JButton("Login");
		btnLogin.setOpaque(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.LoginTry(getNomeUtente_tf().getText(), getPassword_pf().getText());
				}
		});
		btnLogin.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setOpaque(false);
		btnChiudi.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					c.ChiudiLogin();
				}	
			}
		});
	
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(78)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNomeUtente, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(getNomeUtente_tf(), GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(getPassword_pf(), GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(217)
							.addComponent(btnChiudi)
							.addGap(49)
							.addComponent(btnLogin)))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getNomeUtente_tf(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNomeUtente))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getPassword_pf(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnChiudi, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
	}


	
	
	
	public JTextField getNomeUtente_tf() {
		return txfNomeUtente;
	}


	public void setNomeUtente_tf(JTextField nomeUtente_tf) {
		txfNomeUtente = nomeUtente_tf;
	}


	public JPasswordField getPassword_pf() {
		return psfPassword;
	}


	public void setPassword_pf(JPasswordField password_pf) {
		psfPassword = password_pf;
	}
}

