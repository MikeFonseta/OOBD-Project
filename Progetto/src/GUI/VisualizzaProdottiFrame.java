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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class VisualizzaProdottiFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaProdottiFrame frame = new VisualizzaProdottiFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VisualizzaProdottiFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 186, 1041, 348);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Conferma");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(1014, 605, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCerca = new JButton("Cerca");
		btnCerca.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCerca.setBounds(1014, 67, 89, 23);
		contentPane.add(btnCerca);
		
		JLabel lblNewLabel = new JLabel("Min");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setBounds(62, 71, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblMax = new JLabel("Max");
		lblMax.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblMax.setBounds(204, 71, 46, 14);
		contentPane.add(lblMax);
		
		textField = new JTextField();
		textField.setBounds(118, 68, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(260, 68, 46, 20);
		contentPane.add(textField_1);
		
		JLabel lblAllergeneallergeneallergene = new JLabel("Allergene1,Allergene2,Allergene3");
		lblAllergeneallergeneallergene.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAllergeneallergeneallergene.setBounds(380, 71, 168, 14);
		contentPane.add(lblAllergeneallergeneallergene);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(558, 68, 226, 20);
		contentPane.add(textField_2);
	}

}
