package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.ControllerAmministratore;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;

public class EliminaProdottoFrame extends JFrame {

	private Point initialClick;
	private JFrame parent=this;
	private JPanel contentPane;
	private JLabel lblAvviso;
	private JPasswordField psfPassword;
	private ControllerAmministratore controllerAmministratore = null;

	public EliminaProdottoFrame(ControllerAmministratore ControllerAmministratore, String NomeProdottoDaEliminare, int idProdottoDaEliminare) {
		
		
		
		this.controllerAmministratore = ControllerAmministratore;
		setUndecorated(true);
		setBounds(100, 100, 450, 288);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 251);

		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(475, 251));
		contentPane.setMaximumSize(new Dimension(475, 271));
		contentPane.setMinimumSize(new Dimension(475, 251));
		contentPane.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		lblAvviso = new JLabel("<html>Per eliminare il prodotto '"+NomeProdottoDaEliminare+"' da tutte le sedi inserire la propria password:</html>");
		lblAvviso.setBounds(69, 41, 321, 72);
		lblAvviso.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPane.add(lblAvviso);
		
		
		psfPassword = new JPasswordField();
		psfPassword.setBounds(95, 124, 258, 24);
		psfPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(psfPassword);
		
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setBounds(105, 197, 96, 27);
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerAmministratore.ChiudiEliminaProdottoFrame();
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnChiudi);
		
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.setBounds(254, 197, 99, 27);
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ConfermaEliminaProdotto(psfPassword.getText(), idProdottoDaEliminare, NomeProdottoDaEliminare);
				}
			}
		});
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnConferma);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		setContentPane(contentPane);
		
		
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
		
		
		JLabel lblTitolo = new JLabel("Elimina prodotto");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(10, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
			
	}

	
	
}
