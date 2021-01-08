package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;
import Entities.Prodotto;

import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;

public class ModificaProdottoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txfNomeP;
	private JTextArea txpDescrizione;
	private JFormattedTextField txfPrezzo;
	private JTable tblSedi;
	private JTable tblAllergeni;
	private ControllerAmministratore controllerAmministratore = null;
	private JFrame parent;
	private Point initialClick;


	public ModificaProdottoFrame(ControllerAmministratore controllerAmministratore, Prodotto prodotto) {
		this.controllerAmministratore = controllerAmministratore;
		setTitle("Modifica Prodotto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txfNomeP = new JTextField(prodotto.getNomeProdotto());
		txfNomeP.setBounds(145, 128, 197, 24);
		txfNomeP.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfNomeP.setColumns(10);
		contentPane.add(txfNomeP);
		
		txfPrezzo = new JFormattedTextField(new DecimalFormat("#.##"));
		txfPrezzo.setValue(prodotto.getPrezzo());
		txfPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfPrezzo.setBounds(145, 627, 86, 23);
		txfPrezzo.setColumns(10);
		contentPane.add(txfPrezzo);
		
		txpDescrizione = new JTextArea(prodotto.getDescrizione());
		txpDescrizione.setLineWrap(true);
		txpDescrizione.setSize(288, 136);
		txpDescrizione.setLocation(new Point(145, 182));
		txpDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txpDescrizione);

		JLabel lblIDProdotto = new JLabel("ID Prodotto :  "+prodotto.getIdProdotto()+"");
		lblIDProdotto.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblIDProdotto.setBounds(46, 75, 185, 18);
		contentPane.add(lblIDProdotto);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(79, 131, 34, 18);
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(46, 182, 65, 18);
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblDescrizione);
		
		JButton btnAggiungiSede = new JButton("Aggiungi Prodotto");
		btnAggiungiSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiSede.setBounds(834, 540, 145, 32);
		contentPane.add(btnAggiungiSede);
		
		JLabel lblAllergeni = new JLabel("Allergeni");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAllergeni.setBounds(63, 339, 50, 20);
		contentPane.add(lblAllergeni);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPrezzo.setBounds(63, 595, 50, 14);
		contentPane.add(lblPrezzo);
		
		JLabel lblEuro = new JLabel("\u20AC");
		lblEuro.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEuro.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEuro.setBounds(241, 632, 25, 18);
		contentPane.add(lblEuro);
		
		JButton btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBounds(764, 651, 163, 38);
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnAnnulla);
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.setBounds(958, 651, 163, 38);
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnConferma);
		
		JButton btnEliminaAllergeni = new JButton("Elimina");
		btnEliminaAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEliminaAllergeni.setBounds(344, 536, 89, 23);
		contentPane.add(btnEliminaAllergeni);
		
		JButton btnAggiungiAllergene = new JButton("Aggiungi");
		btnAggiungiAllergene.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiAllergene.setBounds(253, 536, 89, 23);
		contentPane.add(btnAggiungiAllergene);
		
		JButton btnElimina = new JButton("Elimina Prodotto");
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnElimina.setBounds(974, 540, 145, 32);
		contentPane.add(btnElimina);
		
		
		JScrollPane spnlSedi = new JScrollPane();
		spnlSedi.setBounds(489, 133, 630, 407);
		contentPane.add(spnlSedi);
		
		tblSedi = new JTable();
		tblSedi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblSedi.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID_Sede", "Nome Sede", "Indirizzo", "Telefono"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSedi.getColumnModel().getColumn(0).setResizable(false);
		tblSedi.getColumnModel().getColumn(1).setResizable(false);
		tblSedi.getColumnModel().getColumn(2).setResizable(false);
		tblSedi.getColumnModel().getColumn(3).setResizable(false);
		tblSedi.setFillsViewportHeight(true);
		tblSedi.setFont(new Font("Calibri", Font.PLAIN, 14));
		spnlSedi.setViewportView(tblSedi);
		
	
		
		JScrollPane spnlAllergeni = new JScrollPane();
		spnlAllergeni.setBounds(142, 339, 291, 196);
		contentPane.add(spnlAllergeni);
		
		tblAllergeni = new JTable();
		tblAllergeni.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Allergeni"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblAllergeni.getColumnModel().getColumn(0).setResizable(false);
		tblAllergeni.setFillsViewportHeight(true);
		tblAllergeni.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		spnlAllergeni.setViewportView(tblAllergeni);
		

		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		
		JLabel lblVisualizzaOrdini = new JLabel("Visualizza Ordini");
		lblVisualizzaOrdini.setForeground(Color.WHITE);
		lblVisualizzaOrdini.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVisualizzaOrdini.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblVisualizzaOrdini);

		
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
