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
import javax.swing.JLabel;
import java.awt.Font;

public class InfoProdottoFrame extends JFrame {

	private JPanel pnlinfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InfoProdottoFrame frame = new InfoProdottoFrame();
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
	public InfoProdottoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 601);
		pnlinfo = new JPanel();
		pnlinfo.setBackground(Color.WHITE);
		pnlinfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlinfo);
		pnlinfo.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNome.setBounds(122, 66, 46, 14);
		pnlinfo.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblDescrizione.setBounds(122, 142, 61, 14);
		pnlinfo.add(lblDescrizione);
		
		JLabel lblAllergeni = new JLabel("Allergeni");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAllergeni.setBounds(123, 211, 46, 14);
		pnlinfo.add(lblAllergeni);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPrezzo.setBounds(123, 279, 46, 14);
		pnlinfo.add(lblPrezzo);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.setBounds(94, 335, 89, 23);
		pnlinfo.add(btnChiudi);
	}
}
