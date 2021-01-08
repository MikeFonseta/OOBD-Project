package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.*;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;
import Controller.ControllerGestore;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

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
	private ControllerGestore controllerGestore;
	private Point initialClick;
	private JFrame parent=this;

	public CreaOrdineFrame(ControllerGestore controllerGestore) {
		setResizable(false);

		this.controllerGestore = controllerGestore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		pnlCreaOrdine = new JPanel();
		pnlCreaOrdine.setBackground(Color.WHITE);
		pnlCreaOrdine.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlCreaOrdine);
		pnlCreaOrdine.setLayout(null);
		
		JScrollPane scpProdotti = new JScrollPane();
		scpProdotti.setBounds(46, 181, 308, 351);
		pnlCreaOrdine.add(scpProdotti);
		
		tblProdotti = new JTable();
		tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdotti.setRowHeight(30);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		tblProdotti.setFillsViewportHeight(true);
		//
		tblProdotti.setModel(new DefaultTableModel(
			controllerGestore.getDatiProdotti("Tutte"),
			new String[] {
				"Nome", "Prezzo", "ID"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblProdotti.getColumnModel().getColumn(0).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(0);
		tblProdotti.getColumnModel().getColumn(2).setMinWidth(0);
		tblProdotti.getColumnModel().getColumn(2).setMaxWidth(0);
		//
		scpProdotti.setViewportView(tblProdotti);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tblProdotti.getColumnModel().getColumn(1).setCellRenderer(rightRenderer); 
		
		JScrollPane scpCarrello = new JScrollPane();
		scpCarrello.setBounds(404, 181, 308, 351);
		pnlCreaOrdine.add(scpCarrello);
		
		tblCarrello = new JTable();
		tblCarrello.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
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
		DefaultTableModel modelloCarrello = (DefaultTableModel) tblCarrello.getModel();
		scpCarrello.setViewportView(tblCarrello);
		
		
       //rende la riga totale non selezionabile
       final ListSelectionModel sel = tblCarrello.getSelectionModel();
        sel.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
               
                if (sel.isSelectedIndex(tblCarrello.getRowCount()-1))
                	tblCarrello.clearSelection();
            }
        });
	   
		
		String[] categorie =controllerGestore.getCategorieBox();
		JComboBox cbxCategorie = new JComboBox(categorie);
		cbxCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Categoria = cbxCategorie.getSelectedItem().toString();
				FiltraPerCategorie(Categoria);
			}
		});
		cbxCategorie.setBounds(203, 65, 151, 22);
		pnlCreaOrdine.add(cbxCategorie);
		
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCategoria.setBounds(46, 69, 88, 14);
		pnlCreaOrdine.add(lblCategoria);
		
		JButton btnAggiungiAlCarrello = new JButton("");
		btnAggiungiAlCarrello.setBounds(309, 532, 45, 23);
		pnlCreaOrdine.add(btnAggiungiAlCarrello);
		btnAggiungiAlCarrello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)   {
					if(tblProdotti.getSelectedColumnCount() != 0)
					{
						 AggiungiAlCarrello(modelloCarrello,"AggiungiProdotto");
					}else 
					{
						//Errore();	
					}
				}
					
			}
		});
		
		
		JButton btnInfo = new JButton("");  // apre la schermata infoProdottoFrame con i dati del prodotto selezionato
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(tblProdotti.getSelectedColumnCount() != 0)
					{
						controllerGestore.ApriInfoProdottoFrame(Integer.parseInt((tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 2).toString()))); 
					}else 
					{
						//Errore();	
					}
				
				}
		});
		btnInfo.setBounds(265, 532, 45, 23);
		pnlCreaOrdine.add(btnInfo);
		
		JButton btnAggiungi1 = new JButton("");
		btnAggiungi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblProdotti.getSelectedColumnCount() != 0)
				{
					
					
				}else 
				{
					//Errore();	
				}
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
	
		
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Creazione Ordine");
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
		this.setVisible(true);

	}
	
	public void FiltraPerCategorie(String categoria) {
		tblProdotti.setModel(new DefaultTableModel(
				controllerGestore.getDatiProdotti(categoria),
				new String[] {
					"Nome", "Prezzo"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblProdotti.getColumnModel().getColumn(0).setResizable(false);
			tblProdotti.getColumnModel().getColumn(1).setResizable(false);
	}
	
	public void AggiungiAlCarrello(DefaultTableModel modCarrello,String azione) {
		
		if(AggiornaQuantita(modCarrello,azione)) {
			modCarrello.addRow(new Object[]{tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 0), 1,tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 1)});
		}
		
		modCarrello.addRow(new Object[]{"Totale", null ,"\u20AC "+String.valueOf(CalcolaTotale())});
		
	}
	
	public boolean AggiornaQuantita(DefaultTableModel modCarrello, String azione) {
		boolean nuovo=true;
		for (int i = 0; i < tblCarrello.getRowCount(); i++) {   
			if(azione.equals("AggiungiProdotto")) {
				if(tblProdotti.getValueAt(tblProdotti.getSelectedRow(),0).equals(tblCarrello.getValueAt(i, 0)) ){
					int quantita= (int) tblCarrello.getValueAt(i, 1);
					if(quantita==99) {
						JOptionPane.showMessageDialog(this,"Impossibile aumentare la quantit\u00E0");
					}else {
						tblCarrello.setValueAt(++quantita,i,1);
					}
					nuovo=false;
				}
			}else if(azione.equals("AggiungiCarrello")){
				
			}
			else {
				//rimuovicarrello
			}
			
			
			if(tblCarrello.getValueAt(i, 0).equals("Totale")) {
				modCarrello.removeRow(i);
			}
		}
		return nuovo;
	}
	
	public float CalcolaTotale() {
		
		float totale=0;
		for (int i = 0; i < tblCarrello.getRowCount(); i++) {    
			int quantita= (int) tblCarrello.getValueAt(i, 1);
			String costoEuro=(String) tblCarrello.getValueAt(i, 2);
			float costo= Float.valueOf(costoEuro.replace("\u20AC ", ""));
			totale+=(quantita*costo);
		}
		
		return totale;
	}
	
	
	public void Errore() {
		JOptionPane.showMessageDialog(this,"errore","Error",JOptionPane.ERROR_MESSAGE);
	}
	
}