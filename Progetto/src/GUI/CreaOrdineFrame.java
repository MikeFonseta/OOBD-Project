package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.MainController;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreaOrdineFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;


	public CreaOrdineFrame(MainController mainController) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 181, 308, 351);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Prezzo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(404, 181, 308, 351);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.setRowHeight(30);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Quantit\u00E0", "Prezzo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		scrollPane_1.setViewportView(table_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(46, 61, 151, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Cerca");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(265, 61, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(309, 532, 45, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBounds(265, 532, 45, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_2.setBounds(489, 532, 45, 23);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_3.setBounds(534, 532, 45, 23);
		contentPane.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_4 = new JButton("");
		btnNewButton_1_4.setBounds(578, 532, 45, 23);
		contentPane.add(btnNewButton_1_4);
		
		JLabel lblNewLabel = new JLabel("NomeCliente");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setBounds(822, 188, 98, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomecliente = new JLabel("CognomeCliente");
		lblNomecliente.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNomecliente.setBounds(822, 247, 98, 14);
		contentPane.add(lblNomecliente);
		
		JLabel lblCognomecliente = new JLabel("TelefonoCliente");
		lblCognomecliente.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCognomecliente.setBounds(822, 308, 98, 14);
		contentPane.add(lblCognomecliente);
		
		JLabel lblCitt = new JLabel("Citt\u00E0");
		lblCitt.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCitt.setBounds(822, 368, 98, 14);
		contentPane.add(lblCitt);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblVia.setBounds(822, 428, 98, 14);
		contentPane.add(lblVia);
		
		JLabel lblPresentiProdottiCon = new JLabel("Presenti prodotti \r\ncon allergeni");
		lblPresentiProdottiCon.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPresentiProdottiCon.setBounds(822, 491, 169, 41);
		contentPane.add(lblPresentiProdottiCon);
		
		JLabel lblN = new JLabel("N.");
		lblN.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblN.setBounds(1034, 428, 11, 14);
		contentPane.add(lblN);
		
		textField = new JTextField();
		textField.setBounds(942, 185, 140, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(1055, 425, 27, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(942, 244, 140, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(942, 305, 140, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(942, 365, 140, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(873, 425, 140, 20);
		contentPane.add(textField_5);
		
		JButton btnNewButton_1_4_1 = new JButton("");
		btnNewButton_1_4_1.setBounds(624, 532, 88, 23);
		contentPane.add(btnNewButton_1_4_1);
		
		JLabel lblCodicecliente = new JLabel("CodiceCliente");
		lblCodicecliente.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCodicecliente.setBounds(822, 65, 98, 14);
		contentPane.add(lblCodicecliente);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(942, 65, 71, 20);
		contentPane.add(textField_6);
		
		JButton btnNewButton_2 = new JButton("Compila");
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(1084, 64, 71, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Nuovo");
		btnNewButton_2_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2_1.setBounds(1019, 64, 63, 23);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Conferma");
		btnNewButton_2_1_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2_1_1.setBounds(1075, 605, 80, 23);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_1_2 = new JButton("Annulla");
		btnNewButton_2_1_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2_1_2.setBounds(994, 605, 71, 23);
		contentPane.add(btnNewButton_2_1_2);
	}
}
