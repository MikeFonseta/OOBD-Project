
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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;
import Controller.MainController;
import javax.swing.DefaultComboBoxModel;

public class VisualizzaOrdiniFrame extends JFrame {

	private JPanel pnlPrincipale;
	private JTable tblOrdini;
	private JTable table;
	private JComboBox cbxIDSedi;
	private JComboBox cbxVeicolo;
	private JTextField txfProdotti;
	private JTextField txfMin;
	private JTextField txfMax;
	private MainController mainController;
	private VisualizzaOrdiniFrame v = this;
	
	/**
	 * Create the frame.
	 */
	public VisualizzaOrdiniFrame(MainController mainController) {
		this.mainController = mainController;
		setMinimumSize(new Dimension(1200, 700));
		setTitle("Visualizza Ordini");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		pnlPrincipale = new JPanel();
		pnlPrincipale.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipale);
		pnlPrincipale.setLayout(null);
		
		
		cbxIDSedi = new JComboBox();
		cbxIDSedi.setModel(new DefaultComboBoxModel(mainController.getIDSedi()));
		cbxIDSedi.setSelectedIndex(1);
		cbxIDSedi.setBounds(96, 16, 354, 35);
		pnlPrincipale.add(cbxIDSedi);
		
		
		cbxVeicolo = new JComboBox();
		cbxVeicolo.setModel(new DefaultComboBoxModel(new String[] {"", "Auto", "Bici", "Scooter ", "Scooter elettrico"}));
		cbxVeicolo.setSelectedIndex(1);
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 14));
		cbxVeicolo.setBounds(521, 85, 148, 33);
		pnlPrincipale.add(cbxVeicolo);

		
		JLabel lblNomeSede = new JLabel("Nome Sede");
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNomeSede.setBounds(22, 33, 64, 18);
		pnlPrincipale.add(lblNomeSede);
		
		
		JLabel lblVeicolo = new JLabel("Veicolo");
		lblVeicolo.setBounds(465, 100, 46, 18);
		lblVeicolo.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(lblVeicolo);
		
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
		
		
		txfProdotti = new JTextField();
		txfProdotti.setText("Ortolana");
		txfProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfProdotti.setBounds(96, 86, 272, 32);
		pnlPrincipale.add(txfProdotti);
		txfProdotti.setColumns(10);
		
		
		txfMin = new JTextField();
		txfMin.setText("3");
		txfMin.setBounds(752, 87, 67, 32);
		txfMin.setFont(new Font("Calibri", Font.PLAIN, 14));
		pnlPrincipale.add(txfMin);
		txfMin.setColumns(10);
		
		
		txfMax = new JTextField();
		txfMax.setText("18");
		txfMax.setBounds(912, 87, 67, 32);
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
		btnCerca.setBounds(1041, 86, 88, 35);
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
		btnChiudi.setBounds(964, 603, 180, 35);
		pnlPrincipale.add(btnChiudi);
		
		
		JButton btnVisualizzaC = new JButton("Visualizza Carrello");
		btnVisualizzaC.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnVisualizzaC.setBounds(786, 603, 180, 35);
		pnlPrincipale.add(btnVisualizzaC);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 170, 1030, 374);		
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

	scrollPane.setViewportView(tblOrdini);
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
		if(this.cbxVeicolo.getSelectedItem().toString().isBlank())
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

	public void SetMax(JTextField txfMax) {
		
	}

//	public void mouseClickedFun() {
//		Integer Min = this.getMinSelezionato();
//		Integer Max = this.getMaxSelezionato();
//
//
//		//mainController.getOrdini(this.getSedeSelezionata(), this.getProdottiSelezionati(),this.getVeicoloSelezionato(), );
//	}


}
