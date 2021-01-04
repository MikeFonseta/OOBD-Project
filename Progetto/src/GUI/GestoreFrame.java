package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;
import Controller.ControllerGestore;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

public class GestoreFrame extends JFrame {

	private JPanel pnlGestore;
	private JTable tblOrdini;
	private ControllerGestore controllerGestore=null;
	private Point initialClick;
	private JFrame parent=this;

	public GestoreFrame(ControllerGestore controllerGestore) {
		setResizable(false);
		
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
		//
		tblOrdini.setModel(new DefaultTableModel(
			controllerGestore.getDatiOrdini(),
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
		//
		scpGestore.setViewportView(tblOrdini);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tblOrdini.getColumnModel().getColumn(7).setCellRenderer(rightRenderer); //allinea  a destra gli elementi della colonna
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		tblOrdini.getColumnModel().getColumn(8).setCellRenderer( centerRenderer );
		
		
		JLabel lblNomeUtente = new JLabel(controllerGestore.getAccount().getNomeUtente());
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNomeUtente.setBounds(43, 46, 88, 14);
		pnlGestore.add(lblNomeUtente);
		
		JLabel lblNomeSede = new JLabel(controllerGestore.getAccount().getSede().getNomeSede());
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNomeSede.setBounds(347, 46, 88, 14);
		pnlGestore.add(lblNomeSede);
		
		JButton btnEsci = new JButton("Esci");
		btnEsci.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnEsci.setBounds(806, 42, 89, 23);
		pnlGestore.add(btnEsci);
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)   //controlla che si usi il tasto sinistro del mouse
					controllerGestore.chiudiGestoreFrame(true); //dopo aver chiuso il frame, apre login
			}
		});
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.setBounds(1023, 42, 89, 23);
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
					controllerGestore.ApriCreaOrdineFrame(); 	
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
					controllerGestore.ApriVisualizzaOrdini(); 
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
					controllerGestore.ApriVisualizzaProdottiFrame(); 
			}
		});
		
		JButton btnFineConsegna = new JButton("Immagine");			//da testare
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
				{
					if(tblOrdini.getSelectedColumnCount() != 0)
					{
						controllerGestore.ImpostaFineConsegna((int) (tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0)));
					}else 
					{
						Errore();	
					}
				}
			}
		});
		
		JButton btnIniziaConsegna = new JButton("Immagine");		//da testare
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
				{
					if(tblOrdini.getSelectedColumnCount() != 0)
					{
						controllerGestore.ImpostaInizioConsegna((int) (tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0)));
					}else 
					{
						Errore();	
					}
				}
					
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
					controllerGestore.ModificaCreaOrdineFrame(); //da scrivere dopo aver caricato CreaOrdineFrame
					
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
				if(e.getButton() == MouseEvent.BUTTON1) { //codice da rivedere
					int indice = getTblOrdini().getSelectedRow();
					if(indice!= -1)
						controllerGestore.ApriVisualizzaCarrello(getIdOrdineAllaRigaSelezionata(indice));
				}
			}
		});
		
		JButton btnElimina = new JButton("Immagine");	//da testare
		btnElimina.setBounds(1085, 509, 89, 63);
		pnlGestore.add(btnElimina);
		btnElimina.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(tblOrdini.getSelectedColumnCount() != 0)
					{
						if((char)(tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 8))=='A') {
							controllerGestore.EliminaOrdine((int) (tblOrdini.getValueAt(tblOrdini.getSelectedRow(), 0)));
						}
						else {
							OrdineSpedito();
						}
					}else 
					{
						Errore();	
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
	
	
	public void AggiornaOrdini() {
		tblOrdini.setModel(new DefaultTableModel(
				controllerGestore.getDatiOrdini(),
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
	}
	
	private void Errore() {
		JOptionPane.showMessageDialog(this,"Nessun ordine selezionato","Errore",JOptionPane.ERROR_MESSAGE);
	}
	
	private void OrdineSpedito() {
		JOptionPane.showMessageDialog(this,"Impossibile eliminare un ordine in consegna","Errore",JOptionPane.ERROR_MESSAGE);
	}
	
	public JTable getTblOrdini() {
		return tblOrdini;
	}
	
	public int getIdOrdineAllaRigaSelezionata(int indice){
		int idOrdine = Integer.parseInt(this.getTblOrdini().getValueAt(indice,0).toString());
		return idOrdine;
	}
}
