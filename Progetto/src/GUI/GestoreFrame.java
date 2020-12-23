package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class GestoreFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestoreFrame frame = new GestoreFrame();
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
	public GestoreFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		scrollPane.setBounds(43, 225, 1006, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CodOrdine", "CodCliente", "NomeCliente", "indirizzo", "TelefonoCliente", "NomeRider", "TelefonoRider", "Totale", "Stato"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(74);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("NomeUtente");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setBounds(40, 27, 88, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNomesede = new JLabel("NomeSede");
		lblNomesede.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNomesede.setBounds(344, 27, 88, 14);
		contentPane.add(lblNomesede);
		
		JButton btnNewButton = new JButton("Esci");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(804, 23, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.setBounds(1023, 23, 89, 23);
		contentPane.add(btnChiudi);
		
		JButton btnNewButton_1 = new JButton("Crea Ordine");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_1.setBounds(43, 116, 339, 63);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Visualizza Ordini");
		btnNewButton_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(391, 116, 372, 63);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Visualizza Prodotti");
		btnNewButton_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton_1_1_1.setBounds(773, 116, 339, 63);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2 = new JButton("Immagine");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(1085, 260, 89, 63);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Immagine");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_1.setBounds(1085, 324, 89, 63);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Immagine");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_2.setBounds(1085, 385, 89, 63);
		contentPane.add(btnNewButton_2_2);
		
		JButton btnNewButton_2_3 = new JButton("Immagine");
		btnNewButton_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_3.setBounds(1085, 446, 89, 63);
		contentPane.add(btnNewButton_2_3);
		
		JButton btnNewButton_2_4 = new JButton("Immagine");
		btnNewButton_2_4.setBounds(1085, 509, 89, 63);
		contentPane.add(btnNewButton_2_4);
	}
}
