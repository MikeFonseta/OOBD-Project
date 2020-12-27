package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private JTable tblProdotti;
	private ControllerAmministratore controllerAmministratore;
	private int idSede;
	private JComboBox cbxCategoria;
	
	public AggiungiProdottoFrame(ControllerAmministratore controllerAmministratore, int idSede){
		
		setAlwaysOnTop(true);
		this.controllerAmministratore = controllerAmministratore;
		this.idSede = idSede;
		
		setResizable(false);
		setBounds(100,100,956,580);
		getContentPane().setLayout(null);
		
		cbxCategoria = new JComboBox();
		cbxCategoria.setModel(new DefaultComboBoxModel(new String[] {"Nessuna categoria", "Pizze", "Panini", "Bibite"}));
		cbxCategoria.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxCategoria.setBounds(12, 13, 175, 30);
		getContentPane().add(cbxCategoria);
		
		JButton btnCerca = new JButton("CERCA");
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controllerAmministratore.getProdottiSedeCategoria(idSede,cbxCategoria.getSelectedItem().toString());
			}
		});
		btnCerca.setBounds(199, 11, 113, 35);
		getContentPane().add(btnCerca);
		
		JScrollPane scpProdotti = new JScrollPane();
		scpProdotti.setFont(new Font("Calibri", Font.PLAIN, 18));
		scpProdotti.setBounds(12, 67, 918, 405);
		getContentPane().add(scpProdotti);
		
		tblProdotti = new JTable();
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		tblProdotti.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblProdotti.setRowHeight(30);
		tblProdotti.setFillsViewportHeight(true);
		tblProdotti.setModel(new DefaultTableModel(
			controllerAmministratore.getProdottiPerUnaSede(idSede),
			new String[] {
				"ID", "Nome", "Descrizione", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Double.class
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
		tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(350);
		tblProdotti.getColumnModel().getColumn(1).setMinWidth(350);
		tblProdotti.getColumnModel().getColumn(1).setMaxWidth(350);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblProdotti.getColumnModel().getColumn(3).setMinWidth(70);
		tblProdotti.getColumnModel().getColumn(3).setMaxWidth(70);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scpProdotti.setViewportView(tblProdotti);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
				controllerAmministratore.ChiudiAggiungiProdottoFrame();
			}
		});
		btnChiudi.setBounds(636, 485, 141, 44);
		getContentPane().add(btnChiudi);
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblProdotti.getSelectedRowCount() > 0) {
					controllerAmministratore.AggiungiProdottoASede(idSede, (int)tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 0));
					}else {
						Errore();
					}
				}
			}

		});
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAggiungi.setBounds(789, 483, 141, 44);
		getContentPane().add(btnAggiungi);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}

	private void Errore() {
		JOptionPane.showMessageDialog(this, "Nessun prodotto selezionato","Errore",JOptionPane.ERROR_MESSAGE);
	}
	
	public void AggiornaProdotti() {
		this.cbxCategoria.setSelectedIndex(0);
		tblProdotti.setModel(new DefaultTableModel(
				controllerAmministratore.getProdottiPerUnaSede(this.idSede),
				new String[] {
					"ID", "Nome", "Descrizione", "Prezzo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, Double.class
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
		tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(350);
		tblProdotti.getColumnModel().getColumn(1).setMinWidth(350);
		tblProdotti.getColumnModel().getColumn(1).setMaxWidth(350);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblProdotti.getColumnModel().getColumn(3).setMinWidth(70);
		tblProdotti.getColumnModel().getColumn(3).setMaxWidth(70);
	}

	public void AggiornaProdottiConCategoria(Object[][] result) {
		tblProdotti.setModel(new DefaultTableModel(
				result,
				new String[] {
					"ID", "Nome", "Descrizione", "Prezzo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, Double.class
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
		tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(50);
		tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(350);
		tblProdotti.getColumnModel().getColumn(1).setMinWidth(350);
		tblProdotti.getColumnModel().getColumn(1).setMaxWidth(350);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(70);
		tblProdotti.getColumnModel().getColumn(3).setMinWidth(70);
		tblProdotti.getColumnModel().getColumn(3).setMaxWidth(70);
	}
}
