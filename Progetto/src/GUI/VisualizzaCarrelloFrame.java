package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.Point;
import java.awt.Window;

import javax.swing.table.DefaultTableModel;


import Controller.MainController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class VisualizzaCarrelloFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private MainController maincontroller;
	private Point initialClick;
	private JFrame parent = this;
	
	
	public VisualizzaCarrelloFrame(MainController mainController, int idOrdine) {
		setAlwaysOnTop(true);
		this.maincontroller = mainController;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		
		
		table = new JTable();
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			mainController.getProdottiCarrello(idOrdine),
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(301);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setAutoResizeMode(JTable.HEIGHT);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		contentPane.setLayout(null);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
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
		
		JLabel lblTotale = new JLabel("Totale : "+calcolaTotale(table)+" ");
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
