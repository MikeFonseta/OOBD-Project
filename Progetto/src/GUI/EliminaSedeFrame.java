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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EliminaSedeFrame extends JFrame{
	
	private JPanel pnlmain;
	private JPasswordField pxfPassword;
	private Point initialClick;
	private JFrame parent=this;
	private ControllerAmministratore controllerAmministratore = null;
	
	public EliminaSedeFrame(ControllerAmministratore ControllerAmministratore,int idSede) {
		this.controllerAmministratore = ControllerAmministratore;
		setUndecorated(true);
		setPreferredSize(new Dimension(475, 251));
		setMaximumSize(new Dimension(475, 251));
		setMinimumSize(new Dimension(475, 251));
		setSize(new Dimension(475, 251));
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setName("EliminaSede");
		
		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
		
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
		
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 475, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
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
		
		
		JLabel lblTitolo = new JLabel("Elimina sede");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
		setVisible(true);
		setResizable(false); 
		setLocationRelativeTo(null);
	}
}
