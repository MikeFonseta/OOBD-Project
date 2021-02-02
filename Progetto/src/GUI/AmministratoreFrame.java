package GUI;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;

import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class AmministratoreFrame extends JFrame{
	
	private JPanel pnlmain;
	private JTable tblSedi;
	private ControllerAmministratore controllerAmministratore = null;
	private Point initialClick;
	private JFrame parent=this;
	
	public AmministratoreFrame(ControllerAmministratore ControllerAmministratore) {
		
		this.controllerAmministratore = ControllerAmministratore;
		setMinimumSize(new Dimension(1200, 700));
		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
		JLabel lblNomeUtente = new JLabel("Nome Utente: " + controllerAmministratore.getAccount().getNomeUtente());
		lblNomeUtente.setBounds(22, 46, 317, 35);
		lblNomeUtente.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNomeUtente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 27));
		getContentPane().add(lblNomeUtente);
		
		JButton btnGestioneProdotti = new JButton("GESTIONE PRODOTTI");
		btnGestioneProdotti.setBounds(349, 46, 242, 54);
		btnGestioneProdotti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1){
					controllerAmministratore.ApriGestioneProdottiFrame();
				}
			}
		});
		btnGestioneProdotti.setBorder(UIManager.getBorder("Button.border"));
		btnGestioneProdotti.setFont(new Font("Calibri", Font.PLAIN, 23));
		getContentPane().add(btnGestioneProdotti); 
		
		JButton btnVisualizzaOrdini = new JButton("VISUALIZZA ORDINI");
		btnVisualizzaOrdini.setBounds(603, 46, 242, 54);
		btnVisualizzaOrdini.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerAmministratore.ApriVisualizzaOrdini();
				}
					
			}
		});
		btnVisualizzaOrdini.setBorder(UIManager.getBorder("Button.border"));
		btnVisualizzaOrdini.setFont(new Font("Calibri", Font.PLAIN, 23));
		getContentPane().add(btnVisualizzaOrdini);
		
		JButton btnEsci = new JButton("ESCI");
		btnEsci.setBounds(868, 46, 148, 54);
		btnEsci.setBorder(UIManager.getBorder("Button.border"));
		btnEsci.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerAmministratore.chiudiAmministratoreFrame(true);
				}
			}
		});
		getContentPane().add(btnEsci);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setBounds(1026, 46, 148, 54);
		btnChiudi.setBorder(UIManager.getBorder("Button.border"));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerAmministratore.chiudiAmministratoreFrame(false);
				}
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnChiudi.setActionCommand("closeBtn");
		getContentPane().add(btnChiudi);
		
		JButton btnElimina = new JButton("X");
		btnElimina.setBounds(1085, 603, 89, 74);
		btnElimina.setBorder(UIManager.getBorder("Button.border"));
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 48));
		btnElimina.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblSedi.getSelectedColumnCount() != 0)
					{
						controllerAmministratore.ApriEliminaSedeFrame((int) tblSedi.getValueAt(tblSedi.getSelectedRow(), 0));
					}else 
					{
					Errore();		
					}
				}
			}
		});
		getContentPane().add(btnElimina);
		
		JButton btnModifica = new JButton("M");
		btnModifica.setBounds(1085, 518, 89, 74);
		btnModifica.setBorder(UIManager.getBorder("Button.border"));
		btnModifica.setFont(new Font("Calibri", Font.PLAIN, 48));
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(tblSedi.getSelectedColumnCount() != 0)
					{
						controllerAmministratore.ApriModificaSediFrame((int) (tblSedi.getValueAt(tblSedi.getSelectedRow(), 0)));
					}else 
					{
						Errore();	
					}
				}
			}
		});
		getContentPane().add(btnModifica);
		
		JButton btnAggiungi = new JButton("+");
		btnAggiungi.setBounds(1085, 430, 89, 74);
		btnAggiungi.setBorder(UIManager.getBorder("Button.border"));
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 48));
		btnAggiungi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriCreazioneSedeFrame();
				}
			}
		});
		getContentPane().add(btnAggiungi);
		
		JScrollPane scpSedi = new JScrollPane();
		scpSedi.setBounds(22, 122, 1036, 555);
		getContentPane().add(scpSedi);
	
		
		tblSedi = new JTable();
		tblSedi.setSelectionForeground(SystemColor.text);
		tblSedi.setGridColor(Color.LIGHT_GRAY);
		tblSedi.setRowHeight(30);
		tblSedi.setFont(new Font("Calibri", Font.PLAIN, 18));
		tblSedi.setSelectionBackground(UIManager.getColor("Table.selectionBackground"));
		tblSedi.setFillsViewportHeight(true);
		tblSedi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSedi.setModel(new DefaultTableModel(
			controllerAmministratore.getDatiSedi(),
			new String[] {
				"ID", "Nome", "Indirizzo", "Telefono"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSedi.getColumnModel().getColumn(0).setResizable(false);
		tblSedi.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblSedi.getColumnModel().getColumn(0).setMinWidth(50);
		tblSedi.getColumnModel().getColumn(0).setMaxWidth(50);
		tblSedi.getColumnModel().getColumn(1).setResizable(false);
		tblSedi.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblSedi.getColumnModel().getColumn(1).setMinWidth(200);
		tblSedi.getColumnModel().getColumn(2).setResizable(false);
		tblSedi.getColumnModel().getColumn(2).setPreferredWidth(300);
		tblSedi.getColumnModel().getColumn(2).setMinWidth(300);
		tblSedi.getColumnModel().getColumn(3).setResizable(false);
		tblSedi.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblSedi.getColumnModel().getColumn(3).setMinWidth(80);
		tblSedi.setAutoResizeMode(JTable.HEIGHT);
		tblSedi.getTableHeader().setReorderingAllowed(false);
		scpSedi.setViewportView(tblSedi);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tblSedi.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBounds(0, 0, 1200, 35);
		pnlBarra.setBackground(Color.DARK_GRAY);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Client Amministratore");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblTitolo);
		
		JButton btnModificaPassword = new JButton("MODIFICA PASSWORD");
		btnModificaPassword.setBounds(22, 76, 233, 35);
		btnModificaPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerAmministratore.ApriModificaPasswordFrame();
				}
			}
		});
		btnModificaPassword.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnModificaPassword.setBorder(UIManager.getBorder("Button.border"));
		getContentPane().add(btnModificaPassword);
		
		
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
	}
	
	public void AggiornaSedi() {
		tblSedi.setModel(new DefaultTableModel(
				controllerAmministratore.getDatiSedi(),
				new String[] {
					"ID", "Nome", "Indirizzo", "Telefono"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblSedi.getColumnModel().getColumn(0).setResizable(false);
			tblSedi.getColumnModel().getColumn(0).setPreferredWidth(50);
			tblSedi.getColumnModel().getColumn(0).setMinWidth(50);
			tblSedi.getColumnModel().getColumn(0).setMaxWidth(50);
			tblSedi.getColumnModel().getColumn(1).setResizable(false);
			tblSedi.getColumnModel().getColumn(1).setPreferredWidth(200);
			tblSedi.getColumnModel().getColumn(1).setMinWidth(200);
			tblSedi.getColumnModel().getColumn(2).setResizable(false);
			tblSedi.getColumnModel().getColumn(2).setPreferredWidth(300);
			tblSedi.getColumnModel().getColumn(2).setMinWidth(300);
			tblSedi.getColumnModel().getColumn(3).setResizable(false);
			tblSedi.getColumnModel().getColumn(3).setPreferredWidth(80);
			tblSedi.getColumnModel().getColumn(3).setMinWidth(80);
	}
	
	private void Errore() {
		JOptionPane.showMessageDialog(this,"Nessuna sede selezionata","Errore",JOptionPane.ERROR_MESSAGE);
	}
}


