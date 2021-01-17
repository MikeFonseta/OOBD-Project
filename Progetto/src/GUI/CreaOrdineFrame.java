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
import javax.swing.table.TableModel;

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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class CreaOrdineFrame extends JFrame {
	//aggiungere consistenze nei textfield (codice non puo contenere lettere ecc)
	private JPanel pnlCreaOrdine;
	private JTable tblProdotti;
	private JTable tblCarrello;
	private JTextField txfNome;
	private JTextField txfCivico;
	private JTextField txfCognome;
	private JTextField txfTelefono;
	private JTextField txfVia;
	private JTextField txfCodice;
	private JLabel lblAllergeni;
	private String idNuovo;
	private float Totale;
	private ControllerGestore controllerGestore;
	private Point initialClick;
	private JFrame parent=this;
	private JComboBox cbxProvincia;
	private JComboBox cbxCitta;
	private DefaultComboBoxModel CittaModel = new DefaultComboBoxModel();
	

	public CreaOrdineFrame(ControllerGestore controllerGestore) {
		setResizable(false);
		this.idNuovo=controllerGestore.getProssimoID();
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
				"Nome", "Prezzo", "ID", "Allergeni"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tblProdotti.getColumnModel().getColumn(0).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(0);
		tblProdotti.getColumnModel().getColumn(2).setMinWidth(0);
		tblProdotti.getColumnModel().getColumn(2).setMaxWidth(0);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(0);
		tblProdotti.getColumnModel().getColumn(3).setMinWidth(0);
		tblProdotti.getColumnModel().getColumn(3).setMaxWidth(0);
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
				"Nome", "Quantit\u00E0", "Prezzo", "ID", "Allergeni"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblCarrello.getColumnModel().getColumn(0).setResizable(false);
		tblCarrello.getColumnModel().getColumn(1).setResizable(false);
		tblCarrello.getColumnModel().getColumn(2).setResizable(false);
		tblCarrello.getColumnModel().getColumn(3).setResizable(false);
		tblCarrello.getColumnModel().getColumn(3).setPreferredWidth(0);
		tblCarrello.getColumnModel().getColumn(3).setMinWidth(0);
		tblCarrello.getColumnModel().getColumn(3).setMaxWidth(0);
		tblCarrello.getColumnModel().getColumn(4).setResizable(false);
		tblCarrello.getColumnModel().getColumn(4).setPreferredWidth(0);
		tblCarrello.getColumnModel().getColumn(4).setMinWidth(0);
		tblCarrello.getColumnModel().getColumn(4).setMaxWidth(0);
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
		
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCategoria.setBounds(46, 69, 88, 14);
		pnlCreaOrdine.add(lblCategoria);
		cbxCategorie.setBounds(203, 65, 151, 22);
		pnlCreaOrdine.add(cbxCategorie);
		
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
				if(tblCarrello.getSelectedColumnCount() != 0)
				{
					AggiornaQuantita(modelloCarrello,"AggiungiCarrello");
					modelloCarrello.addRow(new Object[]{"Totale", null ,"\u20AC "+String.valueOf(CalcolaTotale())});

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
				if(tblCarrello.getSelectedColumnCount() != 0)
				{
					AggiornaQuantita(modelloCarrello,"Rimuovi");
					lblAllergeni.setText(AggiornaAllergeni());
					if(modelloCarrello.getRowCount()!=0) {
						modelloCarrello.addRow(new Object[]{"Totale", null ,"\u20AC "+String.valueOf(CalcolaTotale())});
					}
				}else 
				{
					//Errore();		
				}
			}
		});
		btnRimuovi1.setBounds(534, 532, 45, 23);
		pnlCreaOrdine.add(btnRimuovi1);
		
		
		JButton btnElimina = new JButton("");
		btnElimina.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(tblCarrello.getSelectedColumnCount() != 0)
				{
					AggiornaQuantita(modelloCarrello,"Elimina");
					lblAllergeni.setText(AggiornaAllergeni());
					if(modelloCarrello.getRowCount()!=0) {
						modelloCarrello.addRow(new Object[]{"Totale", null ,"\u20AC "+String.valueOf(CalcolaTotale())});
					}
				}else 
				{
					//Errore();		
				}
			}
		});
		
		btnElimina.setBounds(578, 532, 45, 23);
		pnlCreaOrdine.add(btnElimina);
		
		
		
		JButton btnSvuota = new JButton("");
		btnSvuota.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MessaggioElimina("Svuotare il carrello?")) {
					int rowCount = modelloCarrello.getRowCount();
					//Rimuove le righe a partire dall ultima
					for (int i = rowCount - 1; i >= 0; i--) {
					    modelloCarrello.removeRow(i);
					}
					lblAllergeni.setText(AggiornaAllergeni());
				}
			}
		});
		btnSvuota.setBounds(623, 532, 89, 23);
		pnlCreaOrdine.add(btnSvuota);
		
		JLabel lblCodice = new JLabel("CodiceCliente");
		lblCodice.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCodice.setBounds(822, 65, 98, 14);
		pnlCreaOrdine.add(lblCodice);
		
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNome.setBounds(822, 188, 98, 14);
		pnlCreaOrdine.add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCognome.setBounds(822, 247, 98, 14);
		pnlCreaOrdine.add(lblCognome);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTelefono.setBounds(822, 308, 98, 14);
		pnlCreaOrdine.add(lblTelefono);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblProvincia.setBounds(822, 366, 46, 14);
		pnlCreaOrdine.add(lblProvincia);
		
		JLabel lblCitta = new JLabel("Citt\u00E0");
		lblCitta.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCitta.setBounds(942, 368, 44, 14);
		pnlCreaOrdine.add(lblCitta);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblVia.setBounds(822, 428, 98, 14);
		pnlCreaOrdine.add(lblVia);
		
		JLabel lblCivico = new JLabel("N.");
		lblCivico.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCivico.setBounds(1084, 428, 11, 14);
		pnlCreaOrdine.add(lblCivico);
		
		
		this.lblAllergeni = new JLabel("<html> Qui verranno inseriti gli allergeni </html>");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAllergeni.setBounds(822, 473, 333, 82);
		pnlCreaOrdine.add(lblAllergeni);
		
		
		txfNome = new JTextField();
		txfNome.setBounds(942, 185, 213, 20);
		txfNome.setColumns(10);
		pnlCreaOrdine.add(txfNome);
		
		txfCivico = new JTextField();
		txfCivico.setColumns(10);
		txfCivico.setBounds(1110, 423, 45, 20);
		pnlCreaOrdine.add(txfCivico);
		
		txfCognome = new JTextField();
		txfCognome.setColumns(10);
		txfCognome.setBounds(942, 244, 213, 20);
		pnlCreaOrdine.add(txfCognome);
		
		txfTelefono = new JTextField();
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(942, 305, 213, 20);
		pnlCreaOrdine.add(txfTelefono);
		
		txfVia = new JTextField();
		txfVia.setColumns(10);
		txfVia.setBounds(873, 425, 192, 20);
		pnlCreaOrdine.add(txfVia);
		
		txfCodice = new JTextField();
		txfCodice.setColumns(10);
		txfCodice.setBounds(942, 65, 71, 20);
		pnlCreaOrdine.add(txfCodice);
		
		JButton btnCompila = new JButton("Compila");
		btnCompila.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCompila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String [] datiUtente = controllerGestore.getDati(Integer.parseInt(txfCodice.getText())); //preleva i dati
				
				if(datiUtente[0]!=null) {//inserisci nei field i dati 
					txfNome.setText(datiUtente[0]);
					txfCognome.setText(datiUtente[1]);
					txfTelefono.setText(datiUtente[2]);
					cbxProvincia.setSelectedItem(datiUtente[3]);
					cbxCitta.setSelectedItem(datiUtente[4]);
					txfVia.setText(datiUtente[5]);
					txfCivico.setText(datiUtente[6]);
				}else {
					Errore("");
				}
				
			}
		});
		btnCompila.setBounds(1084, 64, 71, 23);
		pnlCreaOrdine.add(btnCompila);
		
		JButton btnNuovo = new JButton("Nuovo");
		btnNuovo.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNuovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txfCodice.setText(idNuovo);
			}
		});
		btnNuovo.setBounds(1019, 64, 63, 23);
		pnlCreaOrdine.add(btnNuovo);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblCarrello.getRowCount()!=0) {	
					if(txfCodice.getText().equals("") || txfNome.getText().equals("") || txfCognome.getText().equals("") || txfVia.getText().equals("")
							|| txfCivico.getText().equals("") || txfTelefono.getText().equals("") || cbxCitta.getSelectedItem().toString().equals("") 
							|| cbxProvincia.getSelectedItem().toString().equals("") ) 
					{
						//messaggio riempi tutti i campi per proseguire
					}
					else {	
						//creazione utente
						int idCliente=Integer.parseInt(txfCodice.getText());
						if(idCliente==Integer.parseInt(idNuovo)) {
							controllerGestore.CreaNuovoCliente(idNuovo,txfNome.getText(),txfCognome.getText());
						}else if(Integer.parseInt(idNuovo)<idCliente) {
							//messaggio per creare un nuovo utente utilizzare il tasto 'nuovo'
						}
						//creare l ordine inserendo il nome della sede, prelevandolo dall account salvato in controllerGestore (sede 1)
						//assegnando al primo rider della sede disponibile per id
							//fai partire il rider se e a 3 ordini impostando data di consegna e aggiornando la disponibilita a falso
						//inserire il totale dal carrello
						//usare returning per avere l ID dell ordine
						int idNuovoOrdine=controllerGestore.CreazioneOrdine(Totale);
						
						//in compordine inserire con l id dell ordine gli id dei prodotti 
						//il numero di ogni pezzo, prelevando il tutto dal carrello
						
						//funzione che preleva tutti gli id dei prodotti nel carrello e le quantita
						List<Integer[]> prodotti = new ArrayList<Integer[]>();
						for (int i = 0; i < (tblCarrello.getRowCount())-1; i++) {
							int idProdotto=((int) tblCarrello.getValueAt(i, 3));
							int quantita=((int)tblCarrello.getValueAt(i, 1));
							
							Integer[] object = new Integer[] {idProdotto,quantita};
							
							prodotti.add(object);
						}
						
						controllerGestore.CreazioneCompOrdine(prodotti,idNuovoOrdine);
						
						//in infoordineinserire l id dell ordine e del cliente,
						//il telefono e tutti gli altri dati relativi all indirizzo(citta via civico provincia)
						//infoordine:id_ordine,id_cliente,citta,via,numcivico,telefonoc,provincia
						controllerGestore.CreazioneInfoOrdine(idNuovoOrdine,idCliente,cbxCitta.getSelectedItem().toString(),txfVia.getText(),
								txfCivico.getText(),txfTelefono.getText(),cbxProvincia.getSelectedItem().toString());
					}	
				}else //messaggio il carrello e vuoto
					;
			}
		});
		
		
		btnConferma.setBounds(1075, 605, 80, 23);
		pnlCreaOrdine.add(btnConferma);
		
		JButton btnAnnulla = new JButton("Annulla");
		btnAnnulla.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAnnulla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerGestore.ChiudiCreaOrdineFrame();
			}
		});
		
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
		
		cbxProvincia = new JComboBox(controllerGestore.getProvince());
		cbxProvincia.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbxProvincia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	if(cbxProvincia.getSelectedItem() != null && !cbxProvincia.getSelectedItem().toString().equals("")) {	
            			
            		CittaModel.removeAllElements();
            		CittaModel.addAll(controllerGestore.getComuniProvincia(cbxProvincia.getSelectedItem().toString()));
            		
            	}else {
            		CittaModel.removeAllElements();
            		
            	}
            	
            
            }
		});
		cbxProvincia.setBounds(874, 362, 46, 22);
		pnlCreaOrdine.add(cbxProvincia);
		
		cbxCitta = new JComboBox(CittaModel);
		cbxCitta.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbxCitta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	          	
            	if(cbxCitta.getSelectedItem() != null && !cbxCitta.getSelectedItem().toString().equals("")) {
            		
            	}else {
            		
            	}
            	
            	
            }
		});
		cbxCitta.setBounds(994, 362, 161, 22);
		pnlCreaOrdine.add(cbxCitta);


			
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
		//
		tblProdotti.setModel(new DefaultTableModel(
				controllerGestore.getDatiProdotti(categoria),
				new String[] {
					"Nome", "Prezzo", "ID", "Allergeni"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tblProdotti.getColumnModel().getColumn(0).setResizable(false);
			tblProdotti.getColumnModel().getColumn(1).setResizable(false);
			tblProdotti.getColumnModel().getColumn(2).setResizable(false);
			tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(0);
			tblProdotti.getColumnModel().getColumn(2).setMinWidth(0);
			tblProdotti.getColumnModel().getColumn(2).setMaxWidth(0);
			tblProdotti.getColumnModel().getColumn(3).setResizable(false);
			tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(0);
			tblProdotti.getColumnModel().getColumn(3).setMinWidth(0);
			tblProdotti.getColumnModel().getColumn(3).setMaxWidth(0);
	
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			tblProdotti.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
	}
	
	
	
	
	public void AggiungiAlCarrello(DefaultTableModel modCarrello,String azione) {
		
		if(AggiornaQuantita(modCarrello,azione)) {
			modCarrello.addRow(new Object[]{tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 0), 
											1,
											tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 1),
											tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 2),
											tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 3)
											});
		}
		
	
		lblAllergeni.setText(AggiornaAllergeni());

		modCarrello.addRow(new Object[]{"Totale", null ,"\u20AC "+String.valueOf(CalcolaTotale()),null,null});	
		
	}
	
	public boolean AggiornaQuantita(DefaultTableModel modCarrello, String azione) {
		boolean nuovo=true;
		
		if(azione.equals("AggiungiCarrello")) {
			int quantita= (int) tblCarrello.getValueAt(tblCarrello.getSelectedRow(),1);
			if(quantita==99) {
				JOptionPane.showMessageDialog(this,"Impossibile aumentare la quantit\u00E0");
			}else {
				tblCarrello.setValueAt(++quantita,tblCarrello.getSelectedRow(),1);
			}
		}
		
		
		else if(azione.equals("Rimuovi")) {
			int quantita= (int) tblCarrello.getValueAt(tblCarrello.getSelectedRow(),1);
			if(quantita==1) {
				if(MessaggioElimina("Eliminare il prodotto?")) {
					modCarrello.removeRow(tblCarrello.getSelectedRow());
				}
			}else {
				tblCarrello.setValueAt(--quantita,tblCarrello.getSelectedRow(),1);
			}
		}
		
		else if(azione.equals("Elimina")) {
			if(MessaggioElimina("Eliminare il prodotto?")) {
				modCarrello.removeRow(tblCarrello.getSelectedRow());
			}
		}
		
		
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
		this.Totale=totale;
		return totale;
	}
	
	public String AggiornaAllergeni() {
		String allergeni="<html>";
		for (int i = 0; i < tblCarrello.getRowCount(); i++) {    
			if(tblCarrello.getValueAt(i, 4)!=null) {
				allergeni+= (tblCarrello.getValueAt(i, 0)+" contiene "+tblCarrello.getValueAt(i, 4)+"<br>" );
			}
		}
		if(tblCarrello.getRowCount()==0 || allergeni=="<html>") {
			allergeni+=" Nessun allergene presente ";
		}
		
		return allergeni+" </html>";
	}
	
	
	public void Errore(String stringa) {
		JOptionPane.showMessageDialog(this,stringa,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
	public boolean MessaggioElimina(String messaggio) {
		boolean elimina=true;
		Object[] opzioni = { "Annulla", "Conferma"};
		
		 int result = JOptionPane.showOptionDialog(this, messaggio,null,
	                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
	                null, opzioni, null);
	        if (result == JOptionPane.YES_OPTION){
	            elimina=false;
	        }
		
		return elimina;
	}
	
	
	
	
	
	
}