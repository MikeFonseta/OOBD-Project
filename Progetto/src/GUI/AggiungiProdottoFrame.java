package GUI;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.DropMode;

public class AggiungiProdottoFrame extends JFrame{
	private JTable table;
	
	public AggiungiProdottoFrame(ControllerAmministratore controllerAmministratore) {
		
		setResizable(false);
		setBounds(100,100,956,580);
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nessuna categoria", "Pizza", "Panini", "Bibite"}));
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 18));
		comboBox.setBounds(12, 13, 175, 30);
		getContentPane().add(comboBox);
		
		JButton btnCerca = new JButton("CERCA");
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCerca.setBounds(199, 11, 113, 35);
		getContentPane().add(btnCerca);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPane.setBounds(12, 67, 918, 405);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			controllerAmministratore.getProdotti(),
			new String[] {
				"Nome", "Descrizione", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(350);
		table.getColumnModel().getColumn(0).setMinWidth(350);
		table.getColumnModel().getColumn(0).setMaxWidth(350);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setMinWidth(70);
		table.getColumnModel().getColumn(2).setMaxWidth(70);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1)
				CloseFrame();
			}
		});
		btnChiudi.setBounds(636, 485, 141, 44);
		getContentPane().add(btnChiudi);
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAggiungi.setBounds(789, 483, 141, 44);
		getContentPane().add(btnAggiungi);
		
		setVisible(true);
	}
	
	private void CloseFrame() {
		this.dispose();
	}
}
