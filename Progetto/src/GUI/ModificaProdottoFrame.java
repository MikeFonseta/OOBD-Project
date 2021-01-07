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
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;

public class ModificaProdottoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txfNomeP;
	private JTextArea txpDescrizione;
	private JTextField txfPrezzo;
	private JTable tblSedi;
	private JTable tblAllergeni;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaProdottoFrame frame = new ModificaProdottoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModificaProdottoFrame() {
		setTitle("Modifica Prodotto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txfNomeP = new JTextField();
		txfNomeP.setBounds(145, 87, 197, 24);
		txfNomeP.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfNomeP.setColumns(10);
		
		JScrollPane spnlSedi = new JScrollPane();
		spnlSedi.setBounds(489, 83, 630, 407);
		
		txpDescrizione = new JTextArea();
		txpDescrizione.setLineWrap(true);
		txpDescrizione.setSize(288, 136);
		txpDescrizione.setLocation(new Point(145, 141));
		txpDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(79, 90, 34, 18);
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(46, 141, 65, 18);
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnAggiungiSede = new JButton("Aggiungi Prodotto");
		btnAggiungiSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiSede.setBounds(834, 490, 145, 32);
		
		JLabel lblAllergeni = new JLabel("Allergeni");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAllergeni.setBounds(63, 298, 50, 20);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPrezzo.setBounds(79, 590, 32, 14);
		
		txfPrezzo = new JTextField();
		txfPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfPrezzo.setBounds(145, 586, 86, 23);
		txfPrezzo.setColumns(10);
		
		JButton btnAnnulla = new JButton("ANNULLA");
		btnAnnulla.setBounds(762, 612, 163, 38);
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		JButton btnConferma = new JButton("CONFERMA");
		btnConferma.setBounds(956, 612, 163, 38);
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 14));
		
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
		contentPane.setLayout(null);
		contentPane.add(lblNome);
		contentPane.add(lblDescrizione);
		contentPane.add(lblAllergeni);
		contentPane.add(lblPrezzo);
		contentPane.add(txfPrezzo);
		contentPane.add(txfNomeP);
		contentPane.add(txpDescrizione);
		contentPane.add(btnAggiungiSede);
		contentPane.add(spnlSedi);
		contentPane.add(btnAnnulla);
		contentPane.add(btnConferma);
		
		JScrollPane spnlAllergeni = new JScrollPane();
		spnlAllergeni.setBounds(142, 298, 291, 196);
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
		
		JButton btnEliminaAllergeni = new JButton("Elimina");
		btnEliminaAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEliminaAllergeni.setBounds(344, 495, 89, 23);
		contentPane.add(btnEliminaAllergeni);
		
		JButton btnAggiungiAllergene = new JButton("Aggiungi");
		btnAggiungiAllergene.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiAllergene.setBounds(253, 495, 89, 23);
		contentPane.add(btnAggiungiAllergene);
		
		JButton btnElimina = new JButton("Elimina Prodotto");
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnElimina.setBounds(974, 490, 145, 32);
		contentPane.add(btnElimina);
		
		JLabel lblIDProdotto = new JLabel("ID Prodotto :");
		lblIDProdotto.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblIDProdotto.setBounds(46, 28, 185, 18);
		contentPane.add(lblIDProdotto);
	}
}
