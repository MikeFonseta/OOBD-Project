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
		setResizable(false); 
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel contentPane = new JPanel();
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setPreferredSize(new Dimension(0, 0));
		contentPane.setBackground(Color.WHITE);
		contentPane.setAutoscrolls(false);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
	    txfNomeUtente = new JTextField();
		txfNomeUtente.setBounds(173, 100, 163, 24);
		txfNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfNomeUtente.setColumns(10);
		contentPane.add(txfNomeUtente);
		
		
	    psfPassword = new JPasswordField();
		psfPassword.setBounds(173, 178, 163, 24);
		psfPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		psfPassword.setColumns(10);
		contentPane.add(psfPassword);
		
		
		JLabel lblNomeUtente = new JLabel("Nome Utente");
		lblNomeUtente.setBounds(78, 103, 77, 18);
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNomeUtente);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(78, 181, 77, 18);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblPassword);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(333, 285, 63, 27);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.LoginTry(getTxfNomeUtente().getText(), getPsfPassword().getText());
				}
		});
		btnLogin.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnLogin);
		
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()== MouseEvent.BUTTON1)
					c.ChiudiLogin();
			}
		});
		btnChiudi.setBounds(217, 285, 67, 27);
		btnChiudi.setOpaque(false);
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnChiudi);
}

		

	//Getter e Setter
	public JTextField getTxfNomeUtente() {
		return txfNomeUtente;
	}

	public JPasswordField getPsfPassword() {
		return psfPassword;
	}

	public void setTxfNomeUtente(JTextField txfNomeUtente) {
		this.txfNomeUtente = txfNomeUtente;
	}

	public void setPsfPassword(JPasswordField psfPassword) {
		this.psfPassword = psfPassword;
	}
}