package GUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import javax.swing.table.DefaultTableModel;
import Controller.MainController;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.UIManager;

public class VisualizzaCarrelloFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblProdotti;
	private MainController maincontroller = null;
	private Point initialClick;
	private JFrame parent = this;
	
	
	public VisualizzaCarrelloFrame(MainController MainController, int idOrdine) {
		setAlwaysOnTop(true);
		this.maincontroller = MainController;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 53, 422, 520);
		
		JButton Chiudi_btn = new JButton("CHIUDI");
		Chiudi_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				maincontroller.ChiudiVisualizzaCarrelloFrame();
			}
		});
		Chiudi_btn.setBounds(165, 649, 131, 40);
		Chiudi_btn.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(Chiudi_btn);
		
		
		tblProdotti = new JTable();
		tblProdotti.setRowHeight(30);
		tblProdotti.setFillsViewportHeight(true);
		tblProdotti.setModel(new DefaultTableModel(
			maincontroller.getProdottiCarrello(idOrdine),
			new String[] {
				"ID", "Nome", "Quantità", "Prezzo"
			}) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, Integer.class, String.class
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
		tblProdotti.getColumnModel().getColumn(0).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(301);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.setAutoResizeMode(JTable.HEIGHT);
		tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		contentPane.setLayout(null);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setViewportView(tblProdotti);
		contentPane.add(scrollPane);
		
		
		
		
		
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 500, 35);
		getContentPane().add(pnlBarra);
		
		JLabel lblVisualizzaCarrello = new JLabel("Carrello");
		lblVisualizzaCarrello.setForeground(Color.WHITE);
		lblVisualizzaCarrello.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVisualizzaCarrello.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblVisualizzaCarrello);
		
		JLabel lblTotale = new JLabel("Totale : "+calcolaTotale(tblProdotti)+" ");
		lblTotale.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTotale.setBounds(344, 594, 110, 26);
		contentPane.add(lblTotale);

		
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
	            parent .setLocation(X, Y);
	        }
	    });
		
		
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		this.setVisible(true);
		
	}
	
	
	
		
	public String calcolaTotale(JTable table) {
		float totale = 0, prezzo=0;
		int NumPezzi=0;
		for(int i=0;i<table.getRowCount();i++) {
			prezzo = Float.valueOf(table.getValueAt(i, 3).toString().substring(1));
			NumPezzi = Integer.parseInt(table.getValueAt(i, 2).toString());  
			totale += (prezzo*NumPezzi);
			
		}
		return "\u20ac " + Float.valueOf(totale).toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
