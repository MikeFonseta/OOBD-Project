
package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import Controller.MainController;
import Utility.FiltroInteri;
import javax.swing.DefaultComboBoxModel;
import java.util.List;
import javax.swing.UIManager;

public class VisualizzaOrdiniFrame extends JFrame  {

	private JPanel pnlPrincipale;
	private JTable tblOrdini;
	private JComboBox cbxIDSedi;
	private JComboBox cbxVeicolo;
	private JTextField txfProdotti;
	private JTextField txfMin;
	private JTextField txfMax;
	private MainController mainController = null;
	private JFrame parent = this;
	private Point initialClick;

	public VisualizzaOrdiniFrame(MainController MainController) {
		setResizable(false);
		this.mainController = MainController;
		setMinimumSize(new Dimension(1200, 700));
		setTitle("Visualizza Ordini");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		pnlPrincipale = new JPanel();
		pnlPrincipale.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlPrincipale);
		pnlPrincipale.setLayout(null);
		
		
		cbxIDSedi = new JComboBox();
		cbxIDSedi.setModel(new DefaultComboBoxModel(mainController.getIDSedi()));
		cbxIDSedi.setBounds(96, 59, 354, 35);
		pnlPrincipale.add(cbxIDSedi);
		
		
		cbxVeicolo = new JComboBox();
		cbxVeicolo.setModel(new DefaultComboBoxModel(mainController.getVeicoli()));
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbxVeicolo.setBounds(523, 129, 148, 33);
		pnlPrincipale.add(cbxVeicolo);

		
		JLabel lblNomeSede = new JLabel("Sede");
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNomeSede.setBounds(40, 69, 54, 18);
		pnlPrincipale.add(lblNomeSede);
		
		
		JLabel lblVeicolo = new JLabel("Veicolo");
		lblVeicolo.setBounds(466, 144, 46, 18);
		lblVeicolo.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblVeicolo);
		
		JLabel lblProdotti = new JLabel("Prodotti");
		lblProdotti.setBounds(40, 136, 46, 18);
		lblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblProdotti);
		
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setBounds(720, 143, 22, 18);
		lblMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblMin);
		
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setBounds(877, 143, 25, 18);
		lblMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblMax);
		
		
		txfProdotti = new JTextField();
		txfProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfProdotti.setBounds(96, 129, 272, 32);
		pnlPrincipale.add(txfProdotti);
		txfProdotti.setColumns(10);
		
	
		txfMin = new JTextField();
	      PlainDocument docMin = (PlainDocument) txfMin.getDocument();
	      docMin.setDocumentFilter(new FiltroInteri());
	      docMin.addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controllaValoriMin();
				
			}
			
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controllaValoriMin();
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controllaValoriMin();
			}
			
				
				
		});
		
		txfMin.setBounds(752, 130, 67, 32);
		txfMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(txfMin);
		txfMin.setColumns(10);


		txfMax = new JTextField();
			PlainDocument docMax = (PlainDocument) txfMax.getDocument();
			docMax.setDocumentFilter(new FiltroInteri());	
			docMax.addDocumentListener(new DocumentListener() {
				
				@Override
				public void removeUpdate(DocumentEvent e) {
					controllaValoriMax();
					
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					controllaValoriMax();
					
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					controllaValoriMax();
					
				}
			});

		txfMax.setBounds(912, 130, 67, 32);
		txfMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfMax.setColumns(10);
		pnlPrincipale.add(txfMax);

		

		JButton btnCerca = new JButton("Cerca");
		btnCerca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
					AggiornaRis();
				}
			}
		});
		btnCerca.setBounds(1041, 129, 88, 35);
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(btnCerca);
		
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) 
					mainController.ChiudiVisualizzaOrdiniFrame();	
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnChiudi.setBounds(961, 618, 180, 35);
		pnlPrincipale.add(btnChiudi);
		
		
		JButton btnVisualizzaCarrello = new JButton("Visualizza Carrello");
		btnVisualizzaCarrello.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
				int indice = tblOrdini.getSelectedRow();
				if(indice!= -1)
					mainController.ApriVisualizzaCarrelloFrame(getIdOrdineAllaRigaSelezionata(indice));
				else
					JOptionPane.showMessageDialog(parent, new String("Nessuna riga selezionata!"),"Error",JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		btnVisualizzaCarrello.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnVisualizzaCarrello.setBounds(783, 618, 180, 35);
		pnlPrincipale.add(btnVisualizzaCarrello);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(81, 204, 1048, 389);		
		pnlPrincipale.add(scrollPane);
		

		
		tblOrdini = new JTable();
		tblOrdini.setModel(new DefaultTableModel(
		mainController.getOrdini(getSedeSelezionata(), getProdottiSelezionati(),getVeicoloSelezionato(), getMinSelezionato(), getMaxSelezionato()),
		new String[] {
			"CodSede", "CodOrdine", "CodCliente", "Nome Cliente ", "Indirizzo", "CodRider", "Nome Rider ", "Totale"
		}
	) {
		Class[] columnTypes = new Class[] {
			String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
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
	tblOrdini.getColumnModel().getColumn(0).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(1).setResizable(false);
	tblOrdini.getColumnModel().getColumn(1).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(2).setResizable(false);
	tblOrdini.getColumnModel().getColumn(2).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(3).setResizable(false);
	tblOrdini.getColumnModel().getColumn(3).setPreferredWidth(150);
	tblOrdini.getColumnModel().getColumn(4).setResizable(false);
	tblOrdini.getColumnModel().getColumn(4).setPreferredWidth(250);
	tblOrdini.getColumnModel().getColumn(5).setResizable(false);
	tblOrdini.getColumnModel().getColumn(5).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(6).setResizable(false);
	tblOrdini.getColumnModel().getColumn(6).setPreferredWidth(150);
	tblOrdini.getColumnModel().getColumn(7).setResizable(false);
	tblOrdini.getColumnModel().getColumn(7).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(0).setMinWidth(17);
	tblOrdini.getTableHeader().setReorderingAllowed(false);
	tblOrdini.setAutoResizeMode(JTable.HEIGHT);
	tblOrdini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tblOrdini.setRowHeight(30);
	tblOrdini.setFont(new Font("Calibri", Font.PLAIN, 14));
	tblOrdini.setFillsViewportHeight(true);		
	
	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	tblOrdini.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);

	scrollPane.setViewportView(tblOrdini);
	
	
	
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
			
	public void AggiornaRis(){
		tblOrdini.setModel(new DefaultTableModel(
				mainController.getOrdini(getSedeSelezionata(), getProdottiSelezionati(),getVeicoloSelezionato(), getMinSelezionato(), getMaxSelezionato()),
		new String[] {
			"CodSede", "CodOrdine", "CodCliente", "Nome Cliente ", "Indirizzo", "CodRider", "Nome Rider ", "Totale"
		}
	) {
		Class[] columnTypes = new Class[] {
			String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
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
	tblOrdini.getColumnModel().getColumn(0).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(1).setResizable(false);
	tblOrdini.getColumnModel().getColumn(1).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(2).setResizable(false);
	tblOrdini.getColumnModel().getColumn(2).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(3).setResizable(false);
	tblOrdini.getColumnModel().getColumn(3).setPreferredWidth(150);
	tblOrdini.getColumnModel().getColumn(4).setResizable(false);
	tblOrdini.getColumnModel().getColumn(4).setPreferredWidth(250);
	tblOrdini.getColumnModel().getColumn(5).setResizable(false);
	tblOrdini.getColumnModel().getColumn(5).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(6).setResizable(false);
	tblOrdini.getColumnModel().getColumn(6).setPreferredWidth(150);
	tblOrdini.getColumnModel().getColumn(7).setResizable(false);
	tblOrdini.getColumnModel().getColumn(7).setPreferredWidth(20);
	tblOrdini.getColumnModel().getColumn(0).setMinWidth(17);
	tblOrdini.getTableHeader().setReorderingAllowed(false);
	tblOrdini.setAutoResizeMode(JTable.HEIGHT);
	tblOrdini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tblOrdini.setRowHeight(30);
	tblOrdini.setFont(new Font("Calibri", Font.PLAIN, 14));
	tblOrdini.setFillsViewportHeight(true);	
	
	DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	tblOrdini.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	tblOrdini.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);

	}
	
	private void controllaValoriMin() {
				if(!txfMin.getText().isBlank() && !txfMax.getText().isBlank()) {
					try {
					if(Integer.valueOf(this.txfMin.getText()) > Integer.valueOf(this.txfMax.getText()))
						txfMax.setText(txfMin.getText());

					}catch(NumberFormatException w) {
						txfMax.setText(txfMin.getText());

					}
				}
			}		
				
	private void controllaValoriMax() {
				if(!txfMin.getText().isBlank() && !txfMax.getText().isBlank()) {
					try {
						if(Integer.valueOf(this.txfMax.getText()) < Integer.valueOf(this.txfMin.getText())) 
							txfMin.setText(txfMax.getText());
						
					}catch(NumberFormatException w) {
						txfMin.setText(txfMax.getText());

					}
					
			}
		}		

	public Integer getSedeSelezionata() {
		if(this.cbxIDSedi.getSelectedItem().toString() == "Tutte Le Sedi")
			return null; 
		else 
			return Integer.valueOf(this.cbxIDSedi.getSelectedItem().toString());
	}
				
	public List<Integer> getProdottiSelezionati(){

		return mainController.getID_ProdottiPerNomeP(this.txfProdotti.getText());

	}

	public String getVeicoloSelezionato() {
		if(this.cbxVeicolo.getSelectedItem().toString().equals("Nessun Veicolo"))
			return null;
		else 
			return this.cbxVeicolo.getSelectedItem().toString();
	}
	
	public Integer getMinSelezionato() {
		if(this.txfMin.getText().isBlank())
			return null;
		else { 
			Integer min = Integer.valueOf(this.txfMin.getText());
			return min;
		}
	}
		
	public Integer getMaxSelezionato() {
		if(this.txfMax.getText().isBlank())
			return null;
		else { 
			Integer max = Integer.valueOf(this.txfMax.getText());
			return max;  
		}
	}

	public JTable getTblOrdini() {
		return tblOrdini;
	}

	public int getIdOrdineAllaRigaSelezionata(int indice){
		int idOrdine = Integer.parseInt(this.getTblOrdini().getValueAt(indice, 1).toString());
		return idOrdine;
	}

}