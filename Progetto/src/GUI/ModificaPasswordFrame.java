package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import Controller.ControllerAmministratore;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ModificaPasswordFrame extends JFrame{
	
	private JPanel pnlmain;
	private Point initialClick;
	private JFrame parent=this;
	private JPasswordField pxfPasswordAttuale;
	private JPasswordField pxfNuovaPassword;
	private JPasswordField pxfConfermaPassword;
	private ControllerAmministratore controllerAmministratore = null;
	public ModificaPasswordFrame(ControllerAmministratore ControllerAmministratore) {
		
		this.controllerAmministratore = ControllerAmministratore;
		setUndecorated(true);
		setPreferredSize(new Dimension(475, 251));
		setMaximumSize(new Dimension(475, 251));
		setMinimumSize(new Dimension(475, 251));
		setSize(new Dimension(475, 359));
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setName("EliminaSede");

		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
		
		pxfPasswordAttuale = new JPasswordField();
		pxfPasswordAttuale.setFont(new Font("Calibri", Font.PLAIN, 18));
		pxfPasswordAttuale.setBounds(203, 79, 205, 28);
		getContentPane().add(pxfPasswordAttuale);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiModificaPasswordFrame();
				}
			}
		});
		btnChiudi.setBounds(76, 269, 134, 39);
		getContentPane().add(btnChiudi);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnConferma.setBounds(274, 269, 134, 39);
		getContentPane().add(btnConferma);
		
		pxfNuovaPassword = new JPasswordField();
		pxfNuovaPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		pxfNuovaPassword.setBounds(203, 133, 205, 28);
		getContentPane().add(pxfNuovaPassword);
		
		pxfConfermaPassword = new JPasswordField();
		pxfConfermaPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		pxfConfermaPassword.setBounds(203, 184, 205, 28);
		getContentPane().add(pxfConfermaPassword);
		
		JLabel lblPasswordAttuale = new JLabel("Password");
		lblPasswordAttuale.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPasswordAttuale.setBounds(39, 79, 113, 28);
		getContentPane().add(lblPasswordAttuale);
		
		JLabel lblNuovaPassword = new JLabel("Nuova Password");
		lblNuovaPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNuovaPassword.setBounds(39, 133, 154, 28);
		getContentPane().add(lblNuovaPassword);
		
		JLabel lblConfermaPassword = new JLabel("Conferma Password");
		lblConfermaPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblConfermaPassword.setBounds(39, 184, 154, 28);
		getContentPane().add(lblConfermaPassword);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 475, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) 
				{
					controllerAmministratore.CambiaPassword(pxfPasswordAttuale.getText(),pxfNuovaPassword.getText(),pxfConfermaPassword.getText());
				}
			}
		});
		
		
		pnlBarra.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });

	    pnlBarra.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {

	            // Posizione Finestra
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;

	            // Determinazione Spostamento
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;

	            // Spostamento finestra
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            parent.setLocation(X, Y);
	        }
	    });
		
		
		JLabel lblTitolo = new JLabel("Modifica Password");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 174, 35);
		pnlBarra.add(lblTitolo);
		
		
		setVisible(true);
		setResizable(false); 
		setLocationRelativeTo(null);
	}
}