package GUI;
 

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.ControllerAmministratore;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CreaCategoriaFrame extends JFrame {
	private Point initialClick;
	private JFrame parent = this;
	private ControllerAmministratore controllerAmministratore = null;
	private JTextField txfNomeCategoria;
	private boolean NomeInserito = false;
	private JButton btnCrea = null;

	public CreaCategoriaFrame(ControllerAmministratore ControllerAmministratore) {
		this.controllerAmministratore = ControllerAmministratore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 100);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 471, 34);
		getContentPane().add(pnlBarra);
		
		JLabel lblTitolo = new JLabel("Crea Categoria");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(10, 0, 144, 34);
		pnlBarra.add(lblTitolo);
		
		txfNomeCategoria = new JTextField();
		txfNomeCategoria.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfNomeCategoria.getText().isBlank())
					NomeInserito = false;
				else 
					NomeInserito = true;
				ControllaModifiche();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfNomeCategoria.getText().isBlank())
					NomeInserito = false;
				else 
					NomeInserito = true;
				ControllaModifiche();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(txfNomeCategoria.getText().isBlank())
					NomeInserito = false;
				else 
					NomeInserito = true;
				ControllaModifiche();
			}
		});
		txfNomeCategoria.setFont(new Font("Calibri", Font.PLAIN, 16));
		txfNomeCategoria.setBounds(10, 52, 240, 34);
		contentPane.add(txfNomeCategoria);
		txfNomeCategoria.setColumns(10);
		
		btnCrea = new JButton("CREA");
		btnCrea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnCrea.isEnabled()) {
					controllerAmministratore.CreaCategoria(txfNomeCategoria.getText());
				}
			}
		});
		btnCrea.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCrea.setBounds(260, 52, 78, 34);
		contentPane.add(btnCrea);
		
		JButton btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiCreaCategoriaFrame();
				}
			}
		});
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAnnulla.setBounds(348, 51, 118, 34);
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
	
	
	
	public void ControllaModifiche() {
		if(NomeInserito==true) {
			btnCrea.setEnabled(true);
		}else {
			btnCrea.setEnabled(false);
		}
	}
}
