package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Controller.ControllerAmministratore;

import java.awt.ComponentOrientation;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EliminaSedeFrame extends JFrame{
	
	private JPasswordField pxfPassword;
	
	public EliminaSedeFrame(ControllerAmministratore controllerAmministratore,int idSede) {
		setUndecorated(true);
		
		setPreferredSize(new Dimension(475, 251));
		setMaximumSize(new Dimension(475, 251));
		setMinimumSize(new Dimension(475, 251));
		setSize(new Dimension(475, 251));
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setName("EliminaSede");
		getContentPane().setLayout(null);
		
		JLabel lblText = new JLabel("<html>Per eliminare la sede '" + idSede + "' inserire la propria password: ");
		lblText.setHorizontalTextPosition(SwingConstants.CENTER);
		lblText.setHorizontalAlignment(SwingConstants.CENTER);
		lblText.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblText.setBounds(12, 13, 443, 96);
		getContentPane().add(lblText);
		
		pxfPassword = new JPasswordField();
		pxfPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		pxfPassword.setBounds(144, 104, 177, 28);
		getContentPane().add(pxfPassword);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiEliminaSedeFrame();
				}
			}
		});
		btnChiudi.setBounds(73, 158, 134, 39);
		getContentPane().add(btnChiudi);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) 
				{
					controllerAmministratore.ConfermaEliminazioneSede(pxfPassword.getText(), idSede);
				}
			}
		});
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConferma.setBounds(271, 158, 134, 39);
		getContentPane().add(btnConferma);
		
		setVisible(true);
		setResizable(false); 
		setLocationRelativeTo(null);
	}
}
