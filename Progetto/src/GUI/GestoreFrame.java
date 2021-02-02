package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Controller.ControllerGestore;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class GestoreFrame extends JFrame {

	private JPanel pnlGestore;
	private JTable tblOrdini;
	private ControllerGestore controllerGestore=null;
	private Point initialClick;
	private JFrame parent=this;
	private JTable tblRider;
	private int filtroRider=0; 
	private JButton btnFiltro;

	public GestoreFrame(ControllerGestore ControllerGestore) {
		this.controllerGestore= ControllerGestore;
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		pnlGestore = new JPanel();
		pnlGestore.setBackground(UIManager.getColor("Panel.background"));
		pnlGestore.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlGestore);
		pnlGestore.setLayout(null);
		
		JScrollPane scpRider = new JScrollPane();
		scpRider.setFont(new Font("Calibri", Font.PLAIN, 11));
		scpRider.setBounds(10, 207, 371, 389);
		pnlGestore.add(scpRider);
		
		tblRider = new JTable();
		tblRider.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblRider.setRowHeight(30);
		tblRider.setFillsViewportHeight(true);
		tblRider.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblRider.getTableHeader().setReorderingAllowed(false); 
		tblRider.setModel(new DefaultTableModel(
			controllerGestore.getDatiRider(),
			new String[] {
				"Disponibilità", "Nome", "Telefono", "Codice", "Ordini"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblRider.getColumnModel().getColumn(0).setResizable(false);
		tblRider.getColumnModel().getColumn(1).setResizable(false);
		tblRider.getColumnModel().getColumn(2).setResizable(false);
		tblRider.getColumnModel().getColumn(3).setResizable(false);
		tblRider.getColumnModel().getColumn(4).setResizable(false);  
		tblRider.getColumnModel().getColumn(0).setMaxWidth(70);
		tblRider.getColumnModel().getColumn(3).setMaxWidth(50);
		tblRider.getColumnModel().getColumn(4).setMaxWidth(40);
		scpRider.setViewportView(tblRider);
		
		JScrollPane scpGestore = new JScrollPane();
		scpGestore.setFont(new Font("Calibri", Font.PLAIN, 11));
		scpGestore.setBounds(391, 207, 799, 389);
		pnlGestore.add(scpGestore);
		
		tblOrdini = new JTable();
		tblOrdini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblOrdini.setRowHeight(30);
		tblOrdini.setFillsViewportHeight(true);
		tblOrdini.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblOrdini.getTableHeader().setReorderingAllowed(false); 
		tblOrdini.setModel(new DefaultTableModel(
			controllerGestore.getDatiOrdini(0),
			new String[] {
				"CodOrdine", "CodRider", "CodCliente", "NomeCliente", "indirizzo", "TelefonoCliente", "Totale", "Stato"
			}
		) {
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class,String.class, String.class,String.class,String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false,false
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
		tblOrdini.getColumnModel().getColumn(7).setResizable(false);  
		tblOrdini.getColumnModel().getColumn(0).setMaxWidth(65); 
		tblOrdini.getColumnModel().getColumn(1).setMaxWidth(65);
		tblOrdini.getColumnModel().getColumn(2).setMaxWidth(65);
		tblOrdini.getColumnModel().getColumn(3).setMinWidth(120);
		tblOrdini.getColumnModel().getColumn(3).setMaxWidth(120);
		tblOrdini.getColumnModel().getColumn(5).setMinWidth(100);
		tblOrdini.getColumnModel().getColumn(5).setMaxWidth(100); 
		tblOrdini.getColumnModel().getColumn(6).setMaxWidth(60); 
		tblOrdini.getColumnModel().getColumn(7).setMaxWidth(40);
		scpGestore.setViewportView(tblOrdini);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tblOrdini.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);  
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tblOrdini.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		tblOrdini.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		tblOrdini.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		tblOrdini.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
		
		tblRider.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		tblRider.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		tblRider.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		
		
		JLabel lblNomeUtente = new JLabel("Utente: "+controllerGestore.getAccount().getNomeUtente());
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeUtente.setBounds(10, 47, 185, 18);
		pnlGestore.add(lblNomeUtente);
		
		JLabel lblNomeSede = new JLabel("Sede: "+controllerGestore.getAccount().getSede().getNomeSede());
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeSede.setBounds(391, 47, 407, 18);
		pnlGestore.add(lblNomeSede);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEsci.setBounds(1002, 47, 89, 23);
		pnlGestore.add(btnEsci);
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)   
					controllerGestore.ChiudiGestoreFrame(true); 
			}
		});
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnChiudi.setBounds(1101, 47, 89, 23);
		pnlGestore.add(btnChiudi);
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerGestore.ChiudiGestoreFrame(false);
			}
		});
		
		JButton btnCreaOrdine = new JButton("Crea Ordine");
		btnCreaOrdine.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCreaOrdine.setBounds(10, 98, 371, 63);
		pnlGestore.add(btnCreaOrdine);
		btnCreaOrdine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					if(e.getButton() == MouseEvent.BUTTON1) {
						controllerGestore.ApriCreaOrdineFrame(); 
					}	
			}
		});
		
		JButton btnVisualizzaOrdini = new JButton("Visualizza Ordini");
		btnVisualizzaOrdini.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnVisualizzaOrdini.setBounds(391, 98, 407, 63);
		pnlGestore.add(btnVisualizzaOrdini);
		btnVisualizzaOrdini.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerGestore.ApriVisualizzaOrdini(); 
			}
		});
		
		JButton btnVisualizzaProdotti = new JButton("Visualizza Prodotti");
		btnVisualizzaProdotti.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnVisualizzaProdotti.setBounds(808, 98, 382, 63);
		pnlGestore.add(btnVisualizzaProdotti);
		btnVisualizzaProdotti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerGestore.ApriVisualizzaProdotti(); 
			}
		});
				
		JButton btnRiassegna = new JButton("Riassegna");
		btnRiassegna.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnRiassegna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblOrdini.getSelectedColumnCount() != 0) 
				{
					if((int)(tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 1))==0) { 
						if(tblRider.getSelectedColumnCount() != 0) 
						{
							int idRider=((int)tblRider.getValueAt(tblRider.getSelectedRow(), 3));
							if(((char)tblRider.getValueAt(tblRider.getSelectedRow(), 0)=='L')){
								if(((int)tblRider.getValueAt(tblRider.getSelectedRow(), 4)<3)){
									controllerGestore.AssegnaOrdineAlRider((int)tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0),idRider,true);
									AggiornaRider();
									AggiornaOrdini(0);
								}else Errore("Il Rider ha raggiunto il limite massimo di ordini assegnabili");
							}else Errore("Il Rider deve essere disponibile")  ;
						}else Errore("Selezionare un rider per assegnare l'ordine");
					}else  
					{
						if((char)(tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 7))=='A') { 
							int numeroordini=controllerGestore.AssegnaOrdineAlRider((int)tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0),(int)tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 1),false);
							if(filtroRider==0) {
								AggiornaOrdini(0);
							}else { 
								if(numeroordini>0) {
									AggiornaOrdini(filtroRider);
								}else {
									AggiornaOrdini(0);
									filtroRider=0;
									btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
								}
							}
							AggiornaRider();
						}else Errore("L'ordine selezionato è stato già spedito");
					}				
				}else Errore("Selezionare un ordine");
			}
		});
		btnRiassegna.setBounds(923, 626, 89, 63);
		pnlGestore.add(btnRiassegna);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnModifica.setBounds(1012, 626, 89, 63);
		pnlGestore.add(btnModifica);
		btnModifica.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) { 
					if(tblOrdini.getSelectedColumnCount() != 0)  { 
						if((char)(tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 7))=='A') {
							controllerGestore.ModificaCreaOrdineFrame((int)(tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0)));  
						}else Errore("L'ordine selezionato è stato già spedito");
					}
					else Errore("Selezionare l'ordine da modificare");
				}
			}
		});
		
		JButton btnInfo = new JButton("Info");
		btnInfo.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnInfo.setBounds(1101, 626, 89, 63);
		pnlGestore.add(btnInfo);
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) { 
					if(tblOrdini.getSelectedColumnCount() != 0)  {
						int indice = getTblOrdini().getSelectedRow(); 
						if(indice!= -1)
							controllerGestore.ApriVisualizzaCarrello(getIdOrdineAllaRigaSelezionata(indice));
					}
					else Errore("Selezionare l'ordine per visualizzare il carrello"); 
				}
			}
		});
		
		JButton btnElimina = new JButton("Elimina");
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnElimina.setBounds(834, 626, 89, 63);
		pnlGestore.add(btnElimina);
		btnElimina.addMouseListener(new MouseAdapter() { 
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(tblOrdini.getSelectedColumnCount() != 0) 
					{ 
						if((char)(tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 7))=='A') {
							if(MessaggioElimina("Eliminare l'ordine?")) {
								int idRider=(int) (tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 1));
								int numeroordini=controllerGestore.EliminaOrdine((int) (tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0)),idRider); 
								if(filtroRider!=0) {
									if(numeroordini==0) {
										AggiornaOrdini(0);
										filtroRider=0;
										btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
									}else AggiornaOrdini(idRider); 
								}else AggiornaOrdini(0);
								AggiornaRider();
							}
						}
						else {
							Errore("Impossibile eliminare un ordine in consegna");
						}
					}else 
					{
						Errore("Selezionare un ordine per l'eliminazione");	
					}
				} 	
			} 
		});
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Client Gestore");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblTitolo);
		
		JButton btnDisponibile = new JButton("Disponibilit\u00E0");
		btnDisponibile.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnDisponibile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblRider.getSelectedColumnCount() != 0){
					int idRider=((int) tblRider.getValueAt(tblRider.getSelectedRow(),3)); 
					if((char)tblRider.getValueAt(tblRider.getSelectedRow(),0)=='L') {
						if((int) tblRider.getValueAt(tblRider.getSelectedRow(),4)>0) { 
							if(MessaggioElimina("Tutti gli ordini in attesa verranno dissociati da questo rider")) {
								controllerGestore.CancellaCodiciRider(idRider); 
							}
						}
						if(filtroRider==0) {
							AggiornaOrdini(0); 
						}
						else if(filtroRider==idRider) {
							AggiornaOrdini(0);
							filtroRider=0;
							btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
						} 
						controllerGestore.AggiornaDisposizioneRider(idRider ,false);
					}
					else if((char)tblRider.getValueAt(tblRider.getSelectedRow(),0)=='C') { 
						if(MessaggioElimina("Il rider è in consegna, continuare?")) {
							controllerGestore.AnnullaConsegna(idRider);
							controllerGestore.CancellaCodiciRider(idRider);
							if(filtroRider==0) {
								AggiornaOrdini(0); 
							}
							else if(filtroRider==idRider) {
								AggiornaOrdini(0);
								filtroRider=0;
								btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
							}
						}
					}
					else if((char)tblRider.getValueAt(tblRider.getSelectedRow(),0)=='X') {
						controllerGestore.AggiornaDisposizioneRider(idRider,true); 
					}
					AggiornaRider();
				}else 
				{
					Errore("Selezionare il Rider");	
				}
			}
		});
		btnDisponibile.setBounds(10, 626, 93, 63);
		pnlGestore.add(btnDisponibile);
		
		btnFiltro = new JButton("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
		btnFiltro.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		btnFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (filtroRider!=0) {
					AggiornaOrdini(0);
					filtroRider=0;
					btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
				}
				else {  
					if(tblRider.getSelectedColumnCount() != 0 ){
						if((char)tblRider.getValueAt(tblRider.getSelectedRow(),0)!='X') {
							if((int)tblRider.getValueAt(tblRider.getSelectedRow(),4)!=0) {
								AggiornaOrdini((int)tblRider.getValueAt(tblRider.getSelectedRow(),3));
								filtroRider=(int)tblRider.getValueAt(tblRider.getSelectedRow(),3);
								btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Attivato</p></html>");
							}else {
								Errore("Il rider non ha ordini assegnati");
							}
						}else {
							Errore("Per aggiornare il filtro il rider selezionato deve essere disponibile");
						}
					}
					else if(tblRider.getSelectedColumnCount() == 0 ) {
						Errore("Seleziona un rider per attivare il filtro");
					}	
				}
			}
		});
		btnFiltro.setBounds(102, 626, 93, 63);
		pnlGestore.add(btnFiltro);
		
		JButton btnPartenza = new JButton("Partenza");
		btnPartenza.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnPartenza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblRider.getSelectedColumnCount() != 0){
					int idRider=(int) (tblRider.getValueAt(tblRider.getSelectedRow(), 3)); 
						if( (char)tblRider.getValueAt(tblRider.getSelectedRow(), 0)=='X'){
							Errore("Il rider non è disponibile");
						}
						else if((int)tblRider.getValueAt(tblRider.getSelectedRow(), 4)==0) {
							Errore("Il Rider non ha ordini assegnati");
						}
						else if( (char)tblRider.getValueAt(tblRider.getSelectedRow(), 0)=='C'){ 
							controllerGestore.ImpostaInizioConsegna(idRider,true);
							AggiornaRider(); 
							if (filtroRider==0) AggiornaOrdini(0);
							else if(filtroRider==idRider) {
								AggiornaOrdini(filtroRider);
							}
						}
						else if((int)tblRider.getValueAt(tblRider.getSelectedRow(), 4)>0) {
							controllerGestore.ImpostaInizioConsegna(idRider,false);  
							AggiornaRider(); 
							if (filtroRider==0) AggiornaOrdini(0);
							else if(filtroRider==idRider) {
								AggiornaOrdini(filtroRider);
							}
						} 
				}else {
					Errore("Selezionare un Rider");
				}
			}
		});
		btnPartenza.setBounds(196, 626, 93, 63);
		pnlGestore.add(btnPartenza);
		
		JButton btnConsegnato = new JButton("Consegnato");
		btnConsegnato.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnConsegnato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblRider.getSelectedColumnCount() != 0){ 
					if( (char)tblRider.getValueAt(tblRider.getSelectedRow(), 0)=='C'){
						if(MessaggioElimina("Terminare la consegna?")) {
							int idRider=(int) (tblRider.getValueAt(tblRider.getSelectedRow(), 3));
							controllerGestore.ImpostaFineConsegna(idRider);
							AggiornaRider(); 
							if(filtroRider==0) {
								AggiornaOrdini(0);
							}else if(filtroRider==idRider) {
								AggiornaOrdini(0);
								filtroRider=0;
								btnFiltro.setText("<html><p style=\"text-align:center\">Filtro Disattivato</p></html>");
							}
						}
					}
					else {
						Errore("Il rider deve essere in consegna");
					} 
				}else {
					Errore("Selezionare un rider");
				}
			}
		}); 
		btnConsegnato.setBounds(288, 626, 93, 63);
		pnlGestore.add(btnConsegnato);
		 
		pnlBarra.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });

	    pnlBarra.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
	        	
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;

	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;

	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            parent.setLocation(X, Y);
	        }
	    });
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}
	
	public void AggiornaRider() {
		tblRider.setModel(new DefaultTableModel(
				controllerGestore.getDatiRider(),
				new String[] {
					"Disponibilità", "Nome", "Telefono", "Codice", "Ordini"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblRider.getColumnModel().getColumn(0).setResizable(false);
			tblRider.getColumnModel().getColumn(1).setResizable(false);
			tblRider.getColumnModel().getColumn(2).setResizable(false);
			tblRider.getColumnModel().getColumn(3).setResizable(false);
			tblRider.getColumnModel().getColumn(4).setResizable(false); 
			tblRider.getColumnModel().getColumn(0).setMaxWidth(70);
			tblRider.getColumnModel().getColumn(3).setMaxWidth(50);
			tblRider.getColumnModel().getColumn(4).setMaxWidth(40);
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tblRider.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			tblRider.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
			tblRider.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
	}
	
	public void AggiornaOrdini(int idRider) {
		tblOrdini.setModel(new DefaultTableModel(
				controllerGestore.getDatiOrdini(idRider),
				new String[] {
					"CodOrdine","CodRider", "CodCliente", "NomeCliente", "indirizzo", "TelefonoCliente", "Totale", "Stato"
				}
			) {
				Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class,String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
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
			tblOrdini.getColumnModel().getColumn(7).setResizable(false); 
			tblOrdini.getColumnModel().getColumn(0).setMaxWidth(65); 
			tblOrdini.getColumnModel().getColumn(1).setMaxWidth(65);
			tblOrdini.getColumnModel().getColumn(2).setMaxWidth(65);
			tblOrdini.getColumnModel().getColumn(3).setMinWidth(120);
			tblOrdini.getColumnModel().getColumn(3).setMaxWidth(120);
			tblOrdini.getColumnModel().getColumn(5).setMinWidth(100);
			tblOrdini.getColumnModel().getColumn(5).setMaxWidth(100); 
			tblOrdini.getColumnModel().getColumn(6).setMaxWidth(60); 
			tblOrdini.getColumnModel().getColumn(7).setMaxWidth(40); 
			
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
			tblOrdini.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);  
			
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			tblOrdini.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
			tblOrdini.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
			tblOrdini.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
			tblOrdini.getColumnModel().getColumn(7).setCellRenderer( centerRenderer );
			
	}
	
	private void Errore(String messaggio) {
		JOptionPane.showMessageDialog(this,messaggio,"Errore",JOptionPane.ERROR_MESSAGE);
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
	
	public JTable getTblOrdini() {
		return tblOrdini;
	}
	
	public int getIdOrdineAllaRigaSelezionata(int indice){
		int idOrdine = Integer.parseInt(this.getTblOrdini().getValueAt(indice,0).toString());
		return idOrdine;
	}

	public int getFiltroRider() {
		return filtroRider;
	}
	
}
