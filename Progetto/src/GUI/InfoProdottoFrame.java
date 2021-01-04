package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class InfoProdottoFrame extends JFrame {

	private JPanel pnlinfo;
	private Point initialClick;
	private JFrame parent=this;

	public InfoProdottoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		pnlinfo = new JPanel();
		pnlinfo.setBackground(Color.WHITE);
		pnlinfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlinfo);
		pnlinfo.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNome.setBounds(122, 66, 46, 14);
		pnlinfo.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblDescrizione.setBounds(122, 142, 61, 14);
		pnlinfo.add(lblDescrizione);
		
		JLabel lblAllergeni = new JLabel("Allergeni");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAllergeni.setBounds(123, 211, 46, 14);
		pnlinfo.add(lblAllergeni);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPrezzo.setBounds(123, 279, 46, 14);
		pnlinfo.add(lblPrezzo);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.setBounds(94, 335, 89, 23);
		pnlinfo.add(btnChiudi);
		

		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Info Prodotto");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblTitolo);
		
		
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
		setVisible(true);
	}
}
