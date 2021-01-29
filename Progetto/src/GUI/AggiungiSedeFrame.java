package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class AggiungiSedeFrame extends JFrame {

	private JPanel contentPane;
	private ControllerAmministratore controllerAmministratore;
	private JTable tblSedi;
	private Point initialClick;
	private JFrame parent=this;  
	
	public AggiungiSedeFrame(ControllerAmministratore controllerAmministratore, int idProdotto) {
		this.controllerAmministratore = controllerAmministratore;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100,100,889,553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 75, 809, 402);
		contentPane.add(scrollPane);
		
		tblSedi = new JTable();
		tblSedi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblSedi.setModel(new DefaultTableModel(
			controllerAmministratore.getSediMancanti(idProdotto),
			new String[] {
				"ID_Sede", "Nome Sede", "Indirizzo", "Telefono"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblSedi.getColumnModel().getColumn(0).setResizable(false);
		tblSedi.getColumnModel().getColumn(1).setResizable(false);
		tblSedi.getColumnModel().getColumn(2).setResizable(false);
		tblSedi.getColumnModel().getColumn(3).setResizable(false);
		tblSedi.setFillsViewportHeight(true);
		tblSedi.setFont(new Font("Calibri", Font.PLAIN, 14));
		scrollPane.setViewportView(tblSedi);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 956, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
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
	            parent.setLocation(X, Y);
	        }
	    });
	    
		JLabel lblTitolo = new JLabel("Aggiungi Prodotto A Sede");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 185, 35);
		pnlBarra.add(lblTitolo);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerAmministratore.ChiudiAggiungiSedeFrame();
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.setBounds(546, 490, 141, 44);
		contentPane.add(btnChiudi);
		
		JButton btnAggiungi = new JButton("AGGIUNGI");
		btnAggiungi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblSedi.getSelectedRowCount() > 0) {
						int[] Sedi = new int[tblSedi.getSelectedRowCount()];
						int righe[] = tblSedi.getSelectedRows();
							for(int i = 0; i<tblSedi.getSelectedRowCount(); i++)
								Sedi[i] = (int) tblSedi.getValueAt(righe[i], 0);
							
							controllerAmministratore.AggiungiProdottoASedi(idProdotto, Sedi);
					}
				}
			}
		});
		btnAggiungi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnAggiungi.setBounds(699, 488, 141, 44);
		contentPane.add(btnAggiungi);
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}
}

