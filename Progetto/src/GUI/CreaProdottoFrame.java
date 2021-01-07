 package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JComboBox;

public class CreaProdottoFrame extends JFrame {

	private JPanel contentPane;
	private Point initialClick;
	private JFrame parent = this;
	private JTextField txfNome;
	private JTextField txfPrezzo;



	/**
	 * Create the frame.
	 */
	public CreaProdottoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 450, 41);
		getContentPane().add(pnlBarra);
		
		JLabel lblCreaProdotto = new JLabel("Crea Prodotto");
		lblCreaProdotto.setForeground(Color.WHITE);
		lblCreaProdotto.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCreaProdotto.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblCreaProdotto);
		
		txfNome = new JTextField();
		txfNome.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setColumns(10);
		txfNome.setBounds(131, 145, 288, 29);
		contentPane.add(txfNome);
		
		JTextArea txpDescrizione = new JTextArea();
		txpDescrizione.setLocation(new Point(145, 141));
		txpDescrizione.setLineWrap(true);
		txpDescrizione.setFont(new Font("Calibri", Font.PLAIN, 18));
		txpDescrizione.setBounds(131, 277, 288, 169);
		contentPane.add(txpDescrizione);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDescrizione.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblDescrizione.setBounds(0, 276, 99, 26);
		contentPane.add(lblDescrizione);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNome.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(39, 146, 62, 26);
		contentPane.add(lblNome);
		
		JLabel lblIDProdotto = new JLabel("ID Prodotto :");
		lblIDProdotto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblIDProdotto.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIDProdotto.setBounds(10, 89, 228, 23);
		contentPane.add(lblIDProdotto);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPrezzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPrezzo.setBounds(39, 480, 62, 24);
		contentPane.add(lblPrezzo);
		
		txfPrezzo = new JTextField();
		txfPrezzo.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfPrezzo.setColumns(10);
		txfPrezzo.setBounds(131, 475, 89, 29);
		contentPane.add(txfPrezzo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboBox.setBounds(131, 207, 288, 29);
		contentPane.add(comboBox);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategoria.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCategoria.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCategoria.setBounds(24, 207, 77, 29);
		contentPane.add(lblCategoria);
		
		JButton btnNewButton = new JButton("Annulla");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnNewButton.setBounds(91, 556, 99, 41);
		contentPane.add(btnNewButton);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnConferma.setBounds(253, 556, 105, 41);
		contentPane.add(btnConferma);

		
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
