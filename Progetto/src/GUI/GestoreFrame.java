package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;
import Controller.ControllerGestore;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GestoreFrame extends JFrame {

	private JPanel pnlGestore;
	private JTable tblOrdini;

	public GestoreFrame(ControllerGestore controllerGestore) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		pnlGestore = new JPanel();
		pnlGestore.setBackground(Color.WHITE);
		pnlGestore.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlGestore);
		pnlGestore.setLayout(null);
		
		JScrollPane scpGestore = new JScrollPane();
		scpGestore.setFont(new Font("Calibri", Font.PLAIN, 11));
		scpGestore.setBounds(43, 225, 1006, 347);
		pnlGestore.add(scpGestore);
		
		tblOrdini = new JTable();
		tblOrdini.setRowHeight(30);
		tblOrdini.setFillsViewportHeight(true);
		tblOrdini.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblOrdini.getTableHeader().setReorderingAllowed(false);
		tblOrdini.setModel(new DefaultTableModel(
			controllerGestore.getDatiSedi(),
			new String[] {
				"CodOrdine", "CodCliente", "NomeCliente", "indirizzo", "TelefonoCliente", "NomeRider", "TelefonoRider", "Totale", "Stato"
			}
		) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class,String.class, String.class, String.class, String.class,String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblOrdini.getColumnModel().getColumn(0).setResizable(false);
		tblOrdini.getColumnModel().getColumn(1).setResizable(false);
		tblOrdini.getColumnModel().getColumn(2).setResizable(false);
		tblOrdini.getColumnModel().getColumn(3).setResizable(false);
		tblOrdini.getColumnModel().getColumn(4).setResizable(false);
		tblOrdini.getColumnModel().getColumn(5).setResizable(false);
		tblOrdini.getColumnModel().getColumn(6).setResizable(false);
		tblOrdini.getColumnModel().getColumn(6).setPreferredWidth(74);
		tblOrdini.getColumnModel().getColumn(7).setResizable(false);
		tblOrdini.getColumnModel().getColumn(8).setResizable(false);
		scpGestore.setViewportView(tblOrdini);
		
		JLabel lblNomeUtente = new JLabel("NomeUtente");
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNomeUtente.setBounds(40, 27, 88, 14);
		pnlGestore.add(lblNomeUtente);
		
		JLabel lblNomeSede = new JLabel("NomeSede");
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNomeSede.setBounds(344, 27, 88, 14);
		pnlGestore.add(lblNomeSede);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnEsci.setBounds(804, 23, 89, 23);
		pnlGestore.add(btnEsci);
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)   //controlla che si usi il tasto sinistro del mouse
					controllerGestore.chiudiGestoreFrame(true);
			}
		});
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.setBounds(1023, 23, 89, 23);
		pnlGestore.add(btnChiudi);
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				controllerGestore.chiudiGestoreFrame(false);
			}
		});
		
		JButton btnCreaOrdine = new JButton("Crea Ordine");
		btnCreaOrdine.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnCreaOrdine.setBounds(43, 116, 339, 63);
		pnlGestore.add(btnCreaOrdine);
		btnCreaOrdine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ApriCreaOrdineFrame(); 	//da scrivere
			}
		});
		
		JButton btnVisualizzaOrdini = new JButton("Visualizza Ordini");
		btnVisualizzaOrdini.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnVisualizzaOrdini.setBounds(391, 116, 372, 63);
		pnlGestore.add(btnVisualizzaOrdini);
		btnVisualizzaOrdini.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ApriVisualizzaOrdineFrame(); //da scrivere
			}
		});
		
		JButton btnVisualizzaProdotti = new JButton("Visualizza Prodotti");
		btnVisualizzaProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnVisualizzaProdotti.setBounds(773, 116, 339, 63);
		pnlGestore.add(btnVisualizzaProdotti);
		btnVisualizzaProdotti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ApriVisualizzaProdottiFrame(); //da scrivere
			}
		});
		
		JButton btnFineConsegna = new JButton("Immagine");
		btnFineConsegna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFineConsegna.setBounds(1085, 260, 89, 63);
		pnlGestore.add(btnFineConsegna);
		btnFineConsegna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ImpostaFineConsegna(); //da scrivere
				ControllerGestore.AggiornaTabella();
			}
		});
		
		JButton btnIniziaConsegna = new JButton("Immagine");
		btnIniziaConsegna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniziaConsegna.setBounds(1085, 324, 89, 63);
		pnlGestore.add(btnIniziaConsegna);
		btnIniziaConsegna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ImpostaInizioConsegna(); //da scrivere
				ControllerGestore.AggiornaTabella();
			}
		});
		
		JButton btnModifica = new JButton("Immagine");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModifica.setBounds(1085, 385, 89, 63);
		pnlGestore.add(btnModifica);
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ModificaCreaOrdineFrame(); //da scrivere
				ControllerGestore.AggiornaTabella();
			}
		});
		
		JButton btnInfo = new JButton("Immagine");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnInfo.setBounds(1085, 446, 89, 63);
		pnlGestore.add(btnInfo);
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					ControllerGestore.ApriCarrello(); //da scrivere
			}
		});
		
		JButton btnElimina = new JButton("Immagine");
		btnElimina.setBounds(1085, 509, 89, 63);
		pnlGestore.add(btnElimina);
		btnElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				ControllerGestore.EliminaOrdine(); //da scrivere
				ControllerGestore.AggiornaTabella();
			}
		});
	}
	
	}
