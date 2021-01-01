package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class VisualizzaProdottiFrame extends JFrame {

	private JPanel pnlProdotti;
	private JTable tblProdotti;
	private JTextField txfMin;
	private JTextField txfMax;
	private JTextField txfAllergeni;
	private MainController mainController;


	public VisualizzaProdottiFrame(MainController mainController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2844, 913);
		pnlProdotti = new JPanel();
		pnlProdotti.setBackground(Color.WHITE);
		pnlProdotti.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlProdotti);
		pnlProdotti.setLayout(null);
		
		JScrollPane scpProdotti = new JScrollPane();
		scpProdotti.setBounds(62, 186, 1041, 348);
		pnlProdotti.add(scpProdotti);
		
		tblProdotti = new JTable();
		tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblProdotti.setRowHeight(30);
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		tblProdotti.setFillsViewportHeight(true);
		tblProdotti.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"NomeProdotto", "Descrizione", "Allergene", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Float.class
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
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		scpProdotti.setViewportView(tblProdotti);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChiudi.setBounds(1014, 605, 89, 23);
		pnlProdotti.add(btnChiudi);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCerca.setBounds(1014, 67, 89, 23);
		pnlProdotti.add(btnCerca);
		
		JLabel lblMin = new JLabel("Min");
		lblMin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblMin.setBounds(62, 71, 46, 14);
		pnlProdotti.add(lblMin);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblMax.setBounds(204, 71, 46, 14);
		pnlProdotti.add(lblMax);
		
		txfMin = new JTextField();
		txfMin.setBounds(118, 68, 46, 20);
		pnlProdotti.add(txfMin);
		txfMin.setColumns(10);
		
		txfMax = new JTextField();
		txfMax.setColumns(10);
		txfMax.setBounds(260, 68, 46, 20);
		pnlProdotti.add(txfMax);
		
		JLabel lblAllergeni = new JLabel("Allergene1,Allergene2,Allergene3");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAllergeni.setBounds(380, 71, 168, 14);
		pnlProdotti.add(lblAllergeni);
		
		txfAllergeni = new JTextField();
		txfAllergeni.setColumns(10);
		txfAllergeni.setBounds(558, 68, 226, 20);
		pnlProdotti.add(txfAllergeni);
		
		this.setVisible(true);

	}

}
