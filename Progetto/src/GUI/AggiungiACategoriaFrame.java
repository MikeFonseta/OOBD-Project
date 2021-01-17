package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import Controller.ControllerAmministratore;
import javax.swing.JComboBox;

public class AggiungiACategoriaFrame extends JFrame {

	private JPanel contentPane;	
	private Point initialClick;
	private JFrame parent = this;
	private ControllerAmministratore controllerAmministratore = null;
	private JComboBox cbxNomeCategoria;
	private boolean NomeInserito = false;
	private JButton btnAggiungi = null;

	public AggiungiACategoriaFrame(ControllerAmministratore ControllerAmministratore,int[] idProdotti) {
		this.controllerAmministratore = ControllerAmministratore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 100);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 496, 34);
		getContentPane().add(pnlBarra);
		
		JLabel lblTitolo = new JLabel("Seleziona Categoria");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(0, 0, 144, 34);
		pnlBarra.add(lblTitolo);
		
		cbxNomeCategoria = new JComboBox(controllerAmministratore.getCategorie());
		cbxNomeCategoria.setFont(new Font("Calibri", Font.PLAIN, 16));
		cbxNomeCategoria.setBounds(10, 52, 240, 34);
		contentPane.add(cbxNomeCategoria);
		
		btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.AggiungiProdottiACategoria(idProdotti, cbxNomeCategoria.getSelectedItem().toString());
				}
			}
		});
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnAggiungi.setBounds(260, 52, 103, 34);
		contentPane.add(btnAggiungi);
		
		JButton btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiAggiungiACategoriaFrame();
				}
			}
		});
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 16));
		btnAnnulla.setBounds(383, 52, 103, 34);
		contentPane.add(btnAnnulla);
		
		
		
		
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
	    
	    setLocationRelativeTo(null);
		setUndecorated(true);
		this.setVisible(true);
	}

}
