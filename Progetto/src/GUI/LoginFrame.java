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
	private JTextField NomeUtente_tf;
	private JPasswordField Password_pf;
	private JLabel Password_lb;


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
		
		JLabel NomeUtente_lb = new JLabel("Nome Utente");
		NomeUtente_lb.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		setPassword_pf(new JPasswordField());
		getPassword_pf().setHorizontalAlignment(SwingConstants.LEFT);
		getPassword_pf().setFont(new Font("Calibri", Font.PLAIN, 14));
		
		Password_lb = new JLabel("Password");
		Password_lb.setHorizontalAlignment(SwingConstants.LEFT);
		Password_lb.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton Login_btn = new JButton("Login");
		Login_btn.setOpaque(false);
		Login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.LoginTry(getNomeUtente_tf().getText(), getPassword_pf().getText());
				}
		});
		Login_btn.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton Chiudi_btn = new JButton("Chiudi");
		Chiudi_btn.setOpaque(false);
		Chiudi_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Chiudi_btn.setFont(new Font("Calibri", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(78)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(NomeUtente_lb, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(Password_lb, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(getNomeUtente_tf(), GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(getPassword_pf(), GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(217)
							.addComponent(Chiudi_btn)
							.addGap(49)
							.addComponent(Login_btn)))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(100)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getNomeUtente_tf(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(NomeUtente_lb))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(getPassword_pf(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Password_lb))
					.addGap(83)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Login_btn)
						.addComponent(Chiudi_btn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}


	
	
	
	public JTextField getNomeUtente_tf() {
		return NomeUtente_tf;
	}


	public void setNomeUtente_tf(JTextField nomeUtente_tf) {
		NomeUtente_tf = nomeUtente_tf;
	}


	public JPasswordField getPassword_pf() {
		return Password_pf;
	}


	public void setPassword_pf(JPasswordField password_pf) {
		Password_pf = password_pf;
	}
}

