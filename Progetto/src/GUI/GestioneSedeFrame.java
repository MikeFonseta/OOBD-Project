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
import Entities.Account;
import Entities.Sede;

import javax.swing.JPasswordField;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GestioneSedeFrame extends JDialog{
	
	private JTextField txfNomeSede;
	private JTextField txfProvincia;
	private JTextField txfCitta;
	private JTextField txfVia;
	private JLabel lblVia;
	private JTextField txfNumCivico;
	private JButton btnSalva;
	private JButton btnChiudi;
	private JPasswordField psfPassword;
	private JLabel lblPassword;
	private JLabel lblNomeUtente;
	private JLabel lblTelefono;
	private JTextField txfTelefono;
	private JTable tblProdotti;
	private JScrollPane scpRider;
	private JTable tblRider;
	private ControllerAmministratore controllerAmministratore;
	private Account gestoreSede;
	
	
	public GestioneSedeFrame(ControllerAmministratore controllerAmministratore,Account gestoreSede) {
		
		this.controllerAmministratore = controllerAmministratore;
		this.gestoreSede = gestoreSede;
		setResizable(false);
		setBounds(0,0,1200,700);
		getContentPane().setLayout(null);
		
		JLabel lblNomeSede = new JLabel("Nome sede");
		lblNomeSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeSede.setBounds(10, 11, 106, 39);
		getContentPane().add(lblNomeSede);
		
		txfNomeSede = new JTextField(gestoreSede.getSede().getNomeSede());
		txfNomeSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNomeSede.setBounds(108, 15, 183, 28);
		getContentPane().add(txfNomeSede);
		txfNomeSede.setColumns(10);
		
		txfProvincia = new JTextField(gestoreSede.getSede().getProvincia());
		txfProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfProvincia.setColumns(10);
		txfProvincia.setBounds(376, 15, 42, 28);
		getContentPane().add(txfProvincia);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblProvincia.setBounds(296, 11, 96, 39);
		getContentPane().add(lblProvincia);
		
		txfCitta = new JTextField(gestoreSede.getSede().getCitta());
		txfCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCitta.setColumns(10);
		txfCitta.setBounds(480, 15, 106, 28);
		getContentPane().add(txfCitta);
		
		JLabel lblCitta = new JLabel("Città");
		lblCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCitta.setBounds(428, 11, 42, 39);
		getContentPane().add(lblCitta);
		
		txfVia = new JTextField(gestoreSede.getSede().getVia());
		txfVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfVia.setColumns(10);
		txfVia.setBounds(637, 15, 172, 28);
		getContentPane().add(txfVia);
		
		lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVia.setBounds(604, 11, 42, 39);
		getContentPane().add(lblVia);
		
		txfNumCivico = new JTextField(gestoreSede.getSede().getNumCivico());
		txfNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNumCivico.setColumns(10);
		txfNumCivico.setBounds(847, 15, 76, 28);
		getContentPane().add(txfNumCivico);
		
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
		
		psfPassword = new JPasswordField(gestoreSede.getPassword());
		psfPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		psfPassword.setColumns(10);
		psfPassword.setBounds(376, 66, 106, 28);
		getContentPane().add(psfPassword);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(296, 62, 87, 39);
		getContentPane().add(lblPassword);
		
		lblNomeUtente = new JLabel("Gestore: " + gestoreSede.getNomeUtente());
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
		
		txfTelefono = new JTextField(gestoreSede.getSede().getTelefonoSede());
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(780, 66, 143, 28);
		getContentPane().add(txfTelefono);
		
		JScrollPane scpProdotti = new JScrollPane();
		scpProdotti.setBounds(10, 111, 578, 494);
		getContentPane().add(scpProdotti);
		
		tblProdotti = new JTable();
		tblProdotti.setRowHeight(30);
		tblProdotti.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblProdotti.getTableHeader().setReorderingAllowed(false);
		tblProdotti.setDragEnabled(false);
		tblProdotti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblProdotti.setModel(new DefaultTableModel(
			controllerAmministratore.getMenuSede(gestoreSede.getSede().getIdSede()),
			new String[] {
				"ID", "Nome", "Categoria", "Prezzo"
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
		tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
		tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
		tblProdotti.getColumnModel().getColumn(1).setResizable(false);
		tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblProdotti.getColumnModel().getColumn(1).setMinWidth(150);
		tblProdotti.getColumnModel().getColumn(2).setResizable(false);
		tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(100);
		tblProdotti.getColumnModel().getColumn(2).setMinWidth(100);
		tblProdotti.getColumnModel().getColumn(2).setMaxWidth(100);
		tblProdotti.getColumnModel().getColumn(3).setResizable(false);
		tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(61);
		tblProdotti.getColumnModel().getColumn(3).setMinWidth(60);
		tblProdotti.getColumnModel().getColumn(3).setMaxWidth(60);
		scpProdotti.setViewportView(tblProdotti);
		
		scpRider = new JScrollPane();
		scpRider.setBounds(604, 111, 570, 494);
		getContentPane().add(scpRider);
		
		tblRider = new JTable();
		tblRider.setRowHeight(30);
		tblRider.setFont(new Font("Calibri", Font.PLAIN, 14));
		tblRider.getTableHeader().setReorderingAllowed(false);
		tblRider.setModel(new DefaultTableModel(
			controllerAmministratore.getRiderDaSede(gestoreSede.getSede().getIdSede()),
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
		tblRider.getColumnModel().getColumn(0).setResizable(false);
		tblRider.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblRider.getColumnModel().getColumn(0).setMinWidth(40);
		tblRider.getColumnModel().getColumn(0).setMaxWidth(40);
		tblRider.getColumnModel().getColumn(1).setResizable(false);
		tblRider.getColumnModel().getColumn(2).setResizable(false);
		tblRider.getColumnModel().getColumn(3).setResizable(false);
		tblRider.getColumnModel().getColumn(3).setPreferredWidth(80);
		tblRider.getColumnModel().getColumn(3).setMinWidth(80);
		tblRider.getColumnModel().getColumn(4).setResizable(false);
		scpRider.setViewportView(tblRider);
		
		JButton btnEliminaProdotto = new JButton("ELIMINA");
		btnEliminaProdotto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblProdotti.getSelectedRowCount() > 0) {
						controllerAmministratore.EliminaProdottoDaSede(gestoreSede.getSede().getIdSede(), (int)tblProdotti.getValueAt(tblProdotti.getSelectedRow(), 0));
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
					controllerAmministratore.ApriAggiungiProdottoFrame(gestoreSede.getSede().getIdSede());
				}
			}
		});
		btnAggiungiProdotto.setBounds(376, 616, 105, 34);
		getContentPane().add(btnAggiungiProdotto);
		
		JButton btnEliminaRider = new JButton("ELIMINA");
		btnEliminaRider.setBounds(1069, 616, 105, 34);
		getContentPane().add(btnEliminaRider);
		
		JButton btnAggiungiRider = new JButton("AGGIUNGI");
		btnAggiungiRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriNuovoRiderFrame();
				}
			}
		});
		btnAggiungiRider.setBounds(962, 616, 105, 34);
		getContentPane().add(btnAggiungiRider);
		
		JButton btnModificaRider = new JButton("MODIFICA");
		btnModificaRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriModificaRiderFrame(tblRider.getValueAt(tblRider.getSelectedRow(), 0).toString(), gestoreSede.getSede().getIdSede());
				}
			}
		});
		btnModificaRider.setBounds(854, 616, 105, 34);
		getContentPane().add(btnModificaRider);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}

	public void AggiornaProdotti() {
		tblProdotti.setModel(new DefaultTableModel(
				controllerAmministratore.getMenuSede(gestoreSede.getSede().getIdSede()),
				new String[] {
					"ID", "Nome", "Categoria", "Prezzo"
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
			tblProdotti.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblProdotti.getColumnModel().getColumn(0).setMinWidth(40);
			tblProdotti.getColumnModel().getColumn(0).setMaxWidth(40);
			tblProdotti.getColumnModel().getColumn(1).setResizable(false);
			tblProdotti.getColumnModel().getColumn(1).setPreferredWidth(150);
			tblProdotti.getColumnModel().getColumn(1).setMinWidth(150);
			tblProdotti.getColumnModel().getColumn(2).setResizable(false);
			tblProdotti.getColumnModel().getColumn(2).setPreferredWidth(100);
			tblProdotti.getColumnModel().getColumn(2).setMinWidth(100);
			tblProdotti.getColumnModel().getColumn(2).setMaxWidth(100);
			tblProdotti.getColumnModel().getColumn(3).setResizable(false);
			tblProdotti.getColumnModel().getColumn(3).setPreferredWidth(61);
			tblProdotti.getColumnModel().getColumn(3).setMinWidth(60);
			tblProdotti.getColumnModel().getColumn(3).setMaxWidth(60);
	}
	
	public void AggiornaRider() {
		tblRider.setModel(new DefaultTableModel(
				controllerAmministratore.getRiderDaSede(gestoreSede.getSede().getIdSede()),
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
			tblRider.getColumnModel().getColumn(0).setResizable(false);
			tblRider.getColumnModel().getColumn(0).setPreferredWidth(40);
			tblRider.getColumnModel().getColumn(0).setMinWidth(40);
			tblRider.getColumnModel().getColumn(0).setMaxWidth(40);
			tblRider.getColumnModel().getColumn(1).setResizable(false);
			tblRider.getColumnModel().getColumn(2).setResizable(false);
			tblRider.getColumnModel().getColumn(3).setResizable(false);
			tblRider.getColumnModel().getColumn(3).setPreferredWidth(80);
			tblRider.getColumnModel().getColumn(3).setMinWidth(80);
			tblRider.getColumnModel().getColumn(4).setResizable(false);
	}
	
	private void Errore() {
		JOptionPane.showMessageDialog(this,"Nessun prodotto selezionato","Errore",JOptionPane.ERROR_MESSAGE);
	}

}
