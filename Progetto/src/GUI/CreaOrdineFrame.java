package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreaOrdineFrame extends JFrame {
	//ultima riga contiene risultato di query con nome=totale descrizione=null prezzo=sommadeiprezzi

	private JPanel pnlCreaOrdine;
	private JTable tblProdotti;
	private JTable tblCarrello;
	private JTextField txfNome;
	private JTextField txfCivico;
	private JTextField txfCognome;
	private JTextField txfTelefono;
	private JTextField txfCitta;
	private JTextField txfVia;
	private JTextField txfCodice;


	public CreaOrdineFrame(MainController mainController) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2271, 916);
		pnlCreaOrdine = new JPanel();
		pnlCreaOrdine.setBackground(Color.WHITE);
		pnlCreaOrdine.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlCreaOrdine);
		pnlCreaOrdine.setLayout(null);
		
		JScrollPane scpProdotti = new JScrollPane();
		scpProdotti.setBounds(46, 181, 308, 351);
		pnlCreaOrdine.add(scpProdotti);
		
		tblProdotti = new JTable();
		tblProdotti.setRowHeight(30);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		tblProdotti.setFillsViewportHeight(true);
		tblProdotti.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Prezzo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblProdotti.getColumnModel().getColumn(0).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		scpProdotti.setViewportView(tblProdotti);
		
		JScrollPane scpCarrello = new JScrollPane();
		scpCarrello.setBounds(404, 181, 308, 351);
		pnlCreaOrdine.add(scpCarrello);
		
		tblCarrello = new JTable();
		tblCarrello.setFillsViewportHeight(true);
		tblCarrello.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblCarrello.getTableHeader().setReorderingAllowed(false);
		tblCarrello.setRowHeight(30);
		tblCarrello.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Quantit\u00E0", "Prezzo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCarrello.getColumnModel().getColumn(0).setResizable(false);
		tblCarrello.getColumnModel().getColumn(1).setResizable(false);
		tblCarrello.getColumnModel().getColumn(2).setResizable(false);
		scpCarrello.setViewportView(tblCarrello);
		
		JComboBox cbxCategorie = new JComboBox();
		cbxCategorie.setBounds(46, 61, 151, 22);
		pnlCreaOrdine.add(cbxCategorie);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCerca.setBounds(265, 61, 89, 23);
		pnlCreaOrdine.add(btnCerca);
		
		JButton btnAggiungiAlCarrello = new JButton("");
		btnAggiungiAlCarrello.setBounds(309, 532, 45, 23);
		pnlCreaOrdine.add(btnAggiungiAlCarrello);
		
		JButton btnInfo = new JButton("");
		btnInfo.setBounds(265, 532, 45, 23);
		pnlCreaOrdine.add(btnInfo);
		
		JButton btnAggiungi1 = new JButton("");
		btnAggiungi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAggiungi1.setBounds(489, 532, 45, 23);
		pnlCreaOrdine.add(btnAggiungi1);
		
		JButton btnRimuovi1 = new JButton("");
		btnRimuovi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRimuovi1.setBounds(534, 532, 45, 23);
		pnlCreaOrdine.add(btnRimuovi1);
		
		JButton btnElimina = new JButton("");
		btnElimina.setBounds(578, 532, 45, 23);
		pnlCreaOrdine.add(btnElimina);
		
		JLabel lblNome = new JLabel("NomeCliente");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNome.setBounds(822, 188, 98, 14);
		pnlCreaOrdine.add(lblNome);
		
		JLabel lblCognome = new JLabel("CognomeCliente");
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCognome.setBounds(822, 247, 98, 14);
		pnlCreaOrdine.add(lblCognome);
		
		JLabel lblTelefono = new JLabel("TelefonoCliente");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTelefono.setBounds(822, 308, 98, 14);
		pnlCreaOrdine.add(lblTelefono);
		
		JLabel lblCitta = new JLabel("Citt\u00E0");
		lblCitta.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCitta.setBounds(822, 368, 98, 14);
		pnlCreaOrdine.add(lblCitta);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblVia.setBounds(822, 428, 98, 14);
		pnlCreaOrdine.add(lblVia);
		
		JLabel lblPresentiProdottiCon = new JLabel("Presenti prodotti \r\ncon allergeni");
		lblPresentiProdottiCon.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPresentiProdottiCon.setBounds(822, 491, 169, 41);
		pnlCreaOrdine.add(lblPresentiProdottiCon);
		
		JLabel lblCivico = new JLabel("N.");
		lblCivico.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCivico.setBounds(1034, 428, 11, 14);
		pnlCreaOrdine.add(lblCivico);
		
		txfNome = new JTextField();
		txfNome.setBounds(942, 185, 140, 20);
		pnlCreaOrdine.add(txfNome);
		txfNome.setColumns(10);
		
		txfCivico = new JTextField();
		txfCivico.setColumns(10);
		txfCivico.setBounds(1055, 425, 27, 20);
		pnlCreaOrdine.add(txfCivico);
		
		txfCognome = new JTextField();
		txfCognome.setColumns(10);
		txfCognome.setBounds(942, 244, 140, 20);
		pnlCreaOrdine.add(txfCognome);
		
		txfTelefono = new JTextField();
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(942, 305, 140, 20);
		pnlCreaOrdine.add(txfTelefono);
		
		txfCitta = new JTextField();
		txfCitta.setColumns(10);
		txfCitta.setBounds(942, 365, 140, 20);
		pnlCreaOrdine.add(txfCitta);
		
		txfVia = new JTextField();
		txfVia.setColumns(10);
		txfVia.setBounds(873, 425, 140, 20);
		pnlCreaOrdine.add(txfVia);
		
		JButton btnSvuota = new JButton("");
		btnSvuota.setBounds(624, 532, 88, 23);
		pnlCreaOrdine.add(btnSvuota);
		
		JLabel lblCodice = new JLabel("CodiceCliente");
		lblCodice.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCodice.setBounds(822, 65, 98, 14);
		pnlCreaOrdine.add(lblCodice);
		
		txfCodice = new JTextField();
		txfCodice.setColumns(10);
		txfCodice.setBounds(942, 65, 71, 20);
		pnlCreaOrdine.add(txfCodice);
		
		JButton btnCompila = new JButton("Compila");
		btnCompila.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCompila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCompila.setBounds(1084, 64, 71, 23);
		pnlCreaOrdine.add(btnCompila);
		
		JButton btnNuovo = new JButton("Nuovo");
		btnNuovo.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNuovo.setBounds(1019, 64, 63, 23);
		pnlCreaOrdine.add(btnNuovo);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnConferma.setBounds(1075, 605, 80, 23);
		pnlCreaOrdine.add(btnConferma);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAnnulla.setBounds(994, 605, 71, 23);
		pnlCreaOrdine.add(btnAnnulla);
	}
}
