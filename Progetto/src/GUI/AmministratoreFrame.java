package GUI;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Controller.ControllerAmministratore;
import Controller.ControllerGestore;
import Entities.Account;
import Entities.Sede;

import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.SystemColor;

public class AmministratoreFrame extends JFrame{
	
	private JTable tblSedi;
	
	public AmministratoreFrame(ControllerAmministratore controllerAmministratore) {
		
		
		setMinimumSize(new Dimension(1200, 700));
		getContentPane().setLayout(null);
		JLabel lbNomeUtente = new JLabel("Nome Utente: A001");
		lbNomeUtente.setBounds(22, 29, 321, 54);
		lbNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 30));
		getContentPane().add(lbNomeUtente);
		
		JButton btnGestioneProdotti = new JButton("GESTIONE PRODOTTI");
		btnGestioneProdotti.setBounds(343, 29, 242, 54);
		btnGestioneProdotti.setBorder(UIManager.getBorder("Button.border"));
		btnGestioneProdotti.setFont(new Font("Calibri", Font.PLAIN, 23));
		getContentPane().add(btnGestioneProdotti);
		
		JButton btnVisualizzaOrdini = new JButton("VISUALIZZA ORDINI");
		btnVisualizzaOrdini.setBounds(601, 29, 242, 54);
		btnVisualizzaOrdini.setBorder(UIManager.getBorder("Button.border"));
		btnVisualizzaOrdini.setFont(new Font("Calibri", Font.PLAIN, 23));
		getContentPane().add(btnVisualizzaOrdini);
		
		JButton btnEsci = new JButton("ESCI");
		btnEsci.setBounds(868, 29, 148, 54);
		btnEsci.setBorder(UIManager.getBorder("Button.border"));
		btnEsci.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnEsci);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setBounds(1026, 29, 148, 54);
		btnChiudi.setBorder(UIManager.getBorder("Button.border"));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1)
				ChiudiFrame();
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChiudi.setActionCommand("closeBtn");
		getContentPane().add(btnChiudi);
		
		JButton button = new JButton("New button");
		button.setBounds(1111, 652, 6, -44);
		getContentPane().add(button);
		
		JButton btnElimina = new JButton("X");
		btnElimina.setBounds(1085, 576, 89, 74);
		btnElimina.setBorder(UIManager.getBorder("Button.border"));
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 48));
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnElimina);
		
		JButton btnModifica = new JButton("M");
		btnModifica.setBounds(1085, 491, 89, 74);
		btnModifica.setBorder(UIManager.getBorder("Button.border"));
		btnModifica.setFont(new Font("Calibri", Font.PLAIN, 48));
		getContentPane().add(btnModifica);
		
		JButton btnAggiungi = new JButton("+");

		btnAggiungi.setBounds(1085, 403, 89, 74);
		btnAggiungi.setBorder(UIManager.getBorder("Button.border"));
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 48));
		getContentPane().add(btnAggiungi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 95, 1036, 555);
		getContentPane().add(scrollPane);
	
		
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
		tblSedi.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblSedi.getColumnModel().getColumn(1).setMinWidth(150);
		tblSedi.getColumnModel().getColumn(2).setResizable(false);
		tblSedi.getColumnModel().getColumn(2).setPreferredWidth(150);
		tblSedi.getColumnModel().getColumn(2).setMinWidth(150);
		tblSedi.getColumnModel().getColumn(3).setResizable(false);
		tblSedi.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblSedi.getColumnModel().getColumn(3).setMinWidth(150);
		tblSedi.setAutoResizeMode(JTable.HEIGHT);
		tblSedi.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tblSedi);
		
		btnAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1)
				{
					if(tblSedi.getSelectedColumnCount() != 0)
					{
						System.out.println("Selezionata riga n." + (tblSedi.getSelectedRow() + 1));
					}else 
					{
						System.out.println("Nessuna riga selezionata");		
					}
				}
			}
		});
		
		btnModifica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1)
				{
					if(tblSedi.getSelectedColumnCount() != 0)
					{
						controllerAmministratore.ApriGestioneSedi((tblSedi.getValueAt(tblSedi.getSelectedRow(), 0).toString()));
					}else 
					{
						System.out.println("Nessuna riga selezionata");		
					}
				}
			}
		});
		
		btnElimina.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1)
				{
					if(tblSedi.getSelectedColumnCount() != 0)
					{
						System.out.println("Selezionata riga n." + (tblSedi.getSelectedRow() + 1));
					}else 
					{
						System.out.println("Nessuna riga selezionata");		
					}
				}
			}
		});

		setResizable(false); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
	}
	

	private void ChiudiFrame() {
		this.setVisible(false);
	}
	
}
