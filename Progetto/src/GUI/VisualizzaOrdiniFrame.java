package GUI;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;
import javax.swing.DefaultComboBoxModel;

public class VisualizzaOrdiniFrame extends JFrame {

	private JPanel pnlPrincipale;
	private JTable tbl_Ordini;
	private JComboBox cbxTutteLeSedi;
	private String[] e;
	
	

	/**
	 * Create the frame.
	 */
	public VisualizzaOrdiniFrame(MainController mainController, String[] e) {
		setMinimumSize(new Dimension(1200, 700));
		setTitle("Visualizza Ordini");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		pnlPrincipale = new JPanel();
		pnlPrincipale.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipale);
		pnlPrincipale.setLayout(null);
		
		
		cbxTutteLeSedi = new JComboBox();
		cbxTutteLeSedi.setModel(new DefaultComboBoxModel(e));
		cbxTutteLeSedi.setBounds(96, 16, 354, 35);
		pnlPrincipale.add(cbxTutteLeSedi);
		
		
		JComboBox cbxMezzo = new JComboBox();
		cbxMezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbxMezzo.setBounds(521, 85, 148, 33);
		pnlPrincipale.add(cbxMezzo);

		
		JLabel lblNomeSede = new JLabel("Nome Sede");
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNomeSede.setBounds(22, 33, 64, 18);
		pnlPrincipale.add(lblNomeSede);
		
		
		JLabel lblMezzo = new JLabel("Mezzo di trasporto");
		lblMezzo.setBounds(404, 100, 107, 18);
		lblMezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblMezzo);
		
		JLabel lblProdotti = new JLabel("Prodotti");
		lblProdotti.setBounds(39, 100, 46, 18);
		lblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblProdotti);
		
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setBounds(720, 100, 22, 18);
		lblMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblMin);
		
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setBounds(877, 100, 25, 18);
		lblMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblMax);
		
		
		JTextField txfProdotti = new JTextField();
		txfProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfProdotti.setBounds(96, 86, 272, 32);
		pnlPrincipale.add(txfProdotti);
		txfProdotti.setColumns(10);
		
		
		JTextField txfMin = new JTextField();
		txfMin.setBounds(752, 87, 67, 32);
		txfMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(txfMin);
		txfMin.setColumns(10);
		
		
		JTextField txfMax = new JTextField();
		txfMax.setBounds(912, 87, 67, 32);
		txfMax.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfMax.setColumns(10);
		pnlPrincipale.add(txfMax);
		
		

		JButton Cerca_btn = new JButton("Cerca");
		Cerca_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1){
//					chiamata a funzione per query  
//					c.VisualizzaProdotti();								
//					risultati --> lista di object		
//					Risultati = new ArrayList<Object[]>();
//					Risultati.add(new String[] {"FHJ", "SDFS", "DS", "FSF", "FSF", "SF", "SFS", "FSF"});	
				}
			}
		});
		Cerca_btn.setBounds(1041, 86, 88, 35);
		Cerca_btn.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(Cerca_btn);
		
		
		JButton Chiudi_btn = new JButton("Chiudi");
		Chiudi_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) 
					mainController.ChiudiVisualizzaOrdiniFrame();	
			}
		});
		Chiudi_btn.setFont(new Font("Calibri", Font.PLAIN, 14));
		Chiudi_btn.setBounds(964, 603, 180, 35);
		pnlPrincipale.add(Chiudi_btn);
		
		
		JButton VisualizzaC_btn = new JButton("Visualizza Carrello");
		VisualizzaC_btn.setFont(new Font("Calibri", Font.PLAIN, 14));
		VisualizzaC_btn.setBounds(786, 603, 180, 35);
		pnlPrincipale.add(VisualizzaC_btn);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 170, 1030, 374);		
		pnlPrincipale.add(scrollPane);
		
		
		tbl_Ordini = new JTable();
		tbl_Ordini.setModel(new DefaultTableModel(
			new Object[][] {
			},
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
		tbl_Ordini.getColumnModel().getColumn(0).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(0).setPreferredWidth(20);
		tbl_Ordini.getColumnModel().getColumn(1).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(1).setPreferredWidth(20);
		tbl_Ordini.getColumnModel().getColumn(2).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(2).setPreferredWidth(20);
		tbl_Ordini.getColumnModel().getColumn(3).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(3).setPreferredWidth(150);
		tbl_Ordini.getColumnModel().getColumn(4).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(4).setPreferredWidth(250);
		tbl_Ordini.getColumnModel().getColumn(5).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(5).setPreferredWidth(20);
		tbl_Ordini.getColumnModel().getColumn(6).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(6).setPreferredWidth(150);
		tbl_Ordini.getColumnModel().getColumn(7).setResizable(false);
		tbl_Ordini.getColumnModel().getColumn(7).setPreferredWidth(20);
		tbl_Ordini.getColumnModel().getColumn(0).setMinWidth(17);
		tbl_Ordini.getTableHeader().setReorderingAllowed(false);
		tbl_Ordini.setAutoResizeMode(JTable.HEIGHT);
		tbl_Ordini.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbl_Ordini.setRowHeight(30);
		tbl_Ordini.setFont(new Font("Calibri", Font.PLAIN, 14));
		tbl_Ordini.setFillsViewportHeight(true);
		scrollPane.setViewportView(tbl_Ordini);

		
		
		
		
		
		
		
		
		
	}

	public JComboBox getCbxTutteLeSedi() {
		return cbxTutteLeSedi;
	}

	public String[] getE() {
		return e;
	}

	public void setCbxTutteLeSedi(JComboBox cbxTutteLeSedi) {
		this.cbxTutteLeSedi = cbxTutteLeSedi;
	}

	public void setE(String[] e) {
		this.e = e;
	}
}
