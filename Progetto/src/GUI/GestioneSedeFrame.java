package GUI;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAmministratore;

import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestioneSedeFrame extends JDialog{
	private JTextField textFieldNomeSede;
	private JTextField textFieldProvincia;
	private JTextField textFieldCittà;
	private JTextField textFieldVia;
	private JLabel lblVia;
	private JTextField textFieldNumCivico;
	private JButton btnSalva;
	private JButton btnChiudi;
	private JPasswordField textFieldPassword;
	private JLabel lblPassword;
	private JLabel lblNomeUtente;
	private JLabel lblTelefono;
	private JTextField textFieldTelefono;
	private JTable tableProdotti;
	private JScrollPane scrollPaneRider;
	private JTable tableRider;
	private ControllerAmministratore controllerAmministratore;
	private String idSede;
	
	
	public GestioneSedeFrame(ControllerAmministratore controllerAmministratore,String idSede) {
		
		this.controllerAmministratore = controllerAmministratore;
		this.idSede = idSede;
		setResizable(false);
		setBounds(0,0,1200,700);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome sede");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(10, 11, 106, 39);
		getContentPane().add(lblNome);
		
		textFieldNomeSede = new JTextField();
		textFieldNomeSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldNomeSede.setBounds(108, 15, 183, 28);
		getContentPane().add(textFieldNomeSede);
		textFieldNomeSede.setColumns(10);
		
		textFieldProvincia = new JTextField();
		textFieldProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldProvincia.setColumns(10);
		textFieldProvincia.setBounds(376, 15, 42, 28);
		getContentPane().add(textFieldProvincia);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblProvincia.setBounds(296, 11, 96, 39);
		getContentPane().add(lblProvincia);
		
		textFieldCittà = new JTextField();
		textFieldCittà.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldCittà.setColumns(10);
		textFieldCittà.setBounds(480, 15, 106, 28);
		getContentPane().add(textFieldCittà);
		
		JLabel lblCittà = new JLabel("CittÃ ");
		lblCittà.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCittà.setBounds(428, 11, 42, 39);
		getContentPane().add(lblCittà);
		
		textFieldVia = new JTextField();
		textFieldVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldVia.setColumns(10);
		textFieldVia.setBounds(637, 15, 172, 28);
		getContentPane().add(textFieldVia);
		
		lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVia.setBounds(604, 11, 42, 39);
		getContentPane().add(lblVia);
		
		textFieldNumCivico = new JTextField();
		textFieldNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldNumCivico.setColumns(10);
		textFieldNumCivico.setBounds(847, 15, 76, 28);
		getContentPane().add(textFieldNumCivico);
		
		JLabel lblNumCivico = new JLabel("N.");
		lblNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNumCivico.setBounds(819, 11, 18, 39);
		getContentPane().add(lblNumCivico);
		
		btnSalva = new JButton("SALVA");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(1037, 11, 118, 39);
		getContentPane().add(btnSalva);
		
		btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneSedeFrame();
				}
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.setBounds(1037, 61, 118, 39);
		getContentPane().add(btnChiudi);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(376, 66, 106, 28);
		getContentPane().add(textFieldPassword);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(296, 62, 87, 39);
		getContentPane().add(lblPassword);
		
		lblNomeUtente = new JLabel("Nome utente      U001");
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeUtente.setBounds(10, 61, 236, 39);
		getContentPane().add(lblNomeUtente);
		
		JRadioButton rdbtnVisualizzaPassword = new JRadioButton("Visualizza");
		rdbtnVisualizzaPassword.setBounds(488, 69, 109, 23);
		getContentPane().add(rdbtnVisualizzaPassword);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(694, 61, 76, 39);
		getContentPane().add(lblTelefono);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		textFieldTelefono.setColumns(10);
		textFieldTelefono.setBounds(780, 66, 143, 28);
		getContentPane().add(textFieldTelefono);
		
		JScrollPane scrollPaneProdotti = new JScrollPane();
		scrollPaneProdotti.setBounds(10, 111, 578, 494);
		getContentPane().add(scrollPaneProdotti);
		
		tableProdotti = new JTable();
		tableProdotti.setRowHeight(30);
		tableProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tableProdotti.getTableHeader().setReorderingAllowed(false);
		tableProdotti.setDragEnabled(false);
		tableProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProdotti.setModel(new DefaultTableModel(
			controllerAmministratore.getMenùSede(idSede),
			new String[] {
				"ID", "Nome", "Descrizione", "Allergeni", "Prezzo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableProdotti.getColumnModel().getColumn(0).setResizable(false);
		tableProdotti.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tableProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tableProdotti.getColumnModel().getColumn(1).setResizable(false);
		tableProdotti.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableProdotti.getColumnModel().getColumn(1).setMinWidth(150);
		tableProdotti.getColumnModel().getColumn(2).setResizable(false);
		tableProdotti.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableProdotti.getColumnModel().getColumn(2).setMinWidth(100);
		tableProdotti.getColumnModel().getColumn(3).setResizable(false);
		tableProdotti.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableProdotti.getColumnModel().getColumn(3).setMinWidth(150);
		tableProdotti.getColumnModel().getColumn(4).setResizable(false);
		tableProdotti.getColumnModel().getColumn(4).setPreferredWidth(61);
		tableProdotti.getColumnModel().getColumn(4).setMinWidth(20);
		scrollPaneProdotti.setViewportView(tableProdotti);
		
		scrollPaneRider = new JScrollPane();
		scrollPaneRider.setBounds(604, 111, 570, 494);
		getContentPane().add(scrollPaneRider);
		
		tableRider = new JTable();
		tableRider.setRowHeight(30);
		tableRider.setFont(new Font("Calibri", Font.PLAIN, 14));
		tableRider.getTableHeader().setReorderingAllowed(false);
		tableRider.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Cognome", "Telefono", "Veicolo"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableRider.getColumnModel().getColumn(0).setResizable(false);
		tableRider.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableRider.getColumnModel().getColumn(0).setMinWidth(40);
		tableRider.getColumnModel().getColumn(0).setMaxWidth(40);
		tableRider.getColumnModel().getColumn(1).setResizable(false);
		tableRider.getColumnModel().getColumn(2).setResizable(false);
		tableRider.getColumnModel().getColumn(3).setResizable(false);
		tableRider.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableRider.getColumnModel().getColumn(3).setMinWidth(80);
		tableRider.getColumnModel().getColumn(4).setResizable(false);
		scrollPaneRider.setViewportView(tableRider);
		
		JButton btnEliminaProdotto = new JButton("ELIMINA");
		btnEliminaProdotto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tableProdotti.getSelectedRowCount() > 0) {
						controllerAmministratore.EliminaProdottoDaSede(idSede, (int)tableProdotti.getValueAt(tableProdotti.getSelectedRow(), 0));
					}else {
						Errore();
					}
				}
			}
		});
		btnEliminaProdotto.setBounds(483, 616, 105, 34);
		getContentPane().add(btnEliminaProdotto);
		
		JButton btnAggiungiProdotto = new JButton("AGGIUNGI");
		btnAggiungiProdotto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriAggiungiProdottoFrame(idSede);
				}
			}
		});
		btnAggiungiProdotto.setBounds(376, 616, 105, 34);
		getContentPane().add(btnAggiungiProdotto);
		
		JButton btnEliminaRider = new JButton("ELIMINA");
		btnEliminaRider.setBounds(1069, 616, 105, 34);
		getContentPane().add(btnEliminaRider);
		
		JButton btnAggiungiRider = new JButton("AGGIUNGI");
		btnAggiungiRider.setBounds(962, 616, 105, 34);
		getContentPane().add(btnAggiungiRider);
		
		JButton btnModificaRider = new JButton("MODIFICA");
		btnModificaRider.setBounds(854, 616, 105, 34);
		getContentPane().add(btnModificaRider);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}

	public void AggiornaProdotti() {
		tableProdotti.setModel(new DefaultTableModel(
				this.controllerAmministratore.getMenùSede(this.idSede),
				new String[] {
					"ID", "Nome", "Descrizione", "Allergeni", "Prezzo"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		tableProdotti.getColumnModel().getColumn(0).setResizable(false);
		tableProdotti.getColumnModel().getColumn(0).setPreferredWidth(40);
		tableProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tableProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tableProdotti.getColumnModel().getColumn(1).setResizable(false);
		tableProdotti.getColumnModel().getColumn(1).setPreferredWidth(150);
		tableProdotti.getColumnModel().getColumn(1).setMinWidth(150);
		tableProdotti.getColumnModel().getColumn(2).setResizable(false);
		tableProdotti.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableProdotti.getColumnModel().getColumn(2).setMinWidth(100);
		tableProdotti.getColumnModel().getColumn(3).setResizable(false);
		tableProdotti.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableProdotti.getColumnModel().getColumn(3).setMinWidth(150);
		tableProdotti.getColumnModel().getColumn(4).setResizable(false);
		tableProdotti.getColumnModel().getColumn(4).setPreferredWidth(61);
		tableProdotti.getColumnModel().getColumn(4).setMinWidth(20);
	}
	
	private void Errore() {
		JOptionPane.showMessageDialog(this,"Nessun prodotto selezionato","Errore",JOptionPane.ERROR_MESSAGE);
	}

}
