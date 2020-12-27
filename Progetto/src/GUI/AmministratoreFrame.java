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
import javax.swing.JOptionPane;

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
	private ControllerAmministratore controllerAmministratore = null;
	
	public AmministratoreFrame(ControllerAmministratore controllerAmministratore) {
		
		this.controllerAmministratore = controllerAmministratore;
		setMinimumSize(new Dimension(1200, 700));
		getContentPane().setLayout(null);
		JLabel lblNomeUtente = new JLabel("Nome Utente: " + controllerAmministratore.getAccount().getNomeUtente());
		lblNomeUtente.setBounds(22, 29, 321, 54);
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 30));
		getContentPane().add(lblNomeUtente);
		
		JButton btnGestioneProdotti = new JButton("GESTIONE PRODOTTI");
		btnGestioneProdotti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1){
				}
			}
		});
		btnGestioneProdotti.setBounds(343, 29, 242, 54);
		btnGestioneProdotti.setBorder(UIManager.getBorder("Button.border"));
		btnGestioneProdotti.setFont(new Font("Calibri", Font.PLAIN, 23));
		getContentPane().add(btnGestioneProdotti); 
		
		JButton btnVisualizzaOrdini = new JButton("VISUALIZZA ORDINI");
		btnVisualizzaOrdini.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					controllerAmministratore.ApriVisualizzaOrdini();
				}
					
			}
		});
		btnVisualizzaOrdini.setBounds(601, 29, 242, 54);
		btnVisualizzaOrdini.setBorder(UIManager.getBorder("Button.border"));
		btnVisualizzaOrdini.setFont(new Font("Calibri", Font.PLAIN, 23));
		getContentPane().add(btnVisualizzaOrdini);
		
		JButton btnEsci = new JButton("ESCI");
		btnEsci.setBounds(868, 29, 148, 54);
		btnEsci.setBorder(UIManager.getBorder("Button.border"));
		btnEsci.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnEsci.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.chiudiAmministratoreFrame(true);
				}
			}
		});
		getContentPane().add(btnEsci);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setBounds(1026, 29, 148, 54);
		btnChiudi.setBorder(UIManager.getBorder("Button.border"));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.chiudiAmministratoreFrame(false);
				}
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 23));
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChiudi.setActionCommand("closeBtn");
		getContentPane().add(btnChiudi);
		
		JButton btnElimina = new JButton("X");
		btnElimina.setBounds(1085, 576, 89, 74);
		btnElimina.setBorder(UIManager.getBorder("Button.border"));
		btnElimina.setFont(new Font("Calibri", Font.PLAIN, 48));
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblSedi.getSelectedColumnCount() != 0)
				{
					controllerAmministratore.EliminaSede((int) tblSedi.getValueAt(tblSedi.getSelectedRow(), 0));
				}else 
				{
					Errore();		
				}
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
		
		JScrollPane scpSedi = new JScrollPane();
		scpSedi.setBounds(32, 95, 1036, 555);
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
		scpSedi.setViewportView(tblSedi);
		
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
						Errore();		
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
						controllerAmministratore.ApriModificaSediFrame((int) (tblSedi.getValueAt(tblSedi.getSelectedRow(), 0)));
					}else 
					{
						Errore();	
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
			tblSedi.getColumnModel().getColumn(1).setPreferredWidth(150);
			tblSedi.getColumnModel().getColumn(1).setMinWidth(150);
			tblSedi.getColumnModel().getColumn(2).setResizable(false);
			tblSedi.getColumnModel().getColumn(2).setPreferredWidth(150);
			tblSedi.getColumnModel().getColumn(2).setMinWidth(150);
			tblSedi.getColumnModel().getColumn(3).setResizable(false);
			tblSedi.getColumnModel().getColumn(3).setPreferredWidth(150);
			tblSedi.getColumnModel().getColumn(3).setMinWidth(150);
	}
	
	private void Errore() {
		JOptionPane.showMessageDialog(this,"Nessuna sede selezionata","Errore",JOptionPane.ERROR_MESSAGE);
	}
	
}
