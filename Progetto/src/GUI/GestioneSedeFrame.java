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
	
	
	
	private JTable tblProdotti;
	private JTable tblRider;
	private ControllerAmministratore controllerAmministratore;
	private Account gestoreSede;
	
	
	public GestioneSedeFrame(ControllerAmministratore controllerAmministratore,Account gestoreSede) {
		
		this.controllerAmministratore = controllerAmministratore;
		this.gestoreSede = gestoreSede;
		setResizable(false);
		setBounds(0,0,1200,700);
		getContentPane().setLayout(null);
		
		JLabel lblIdSedeTxt = new JLabel("ID Sede:");
		lblIdSedeTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdSedeTxt.setBounds(10, 11, 67, 39);
		getContentPane().add(lblIdSedeTxt);
		
		JLabel lblIdSede = new JLabel(gestoreSede.getSede().getIdSede());
		lblIdSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdSede.setBounds(82, 11, 88, 39);
		getContentPane().add(lblIdSede);
		
		JLabel lblNome = new JLabel("Nome ");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(180, 11, 49, 39);
		getContentPane().add(lblNome);
		
		JTextField txfNomeSede = new JTextField(gestoreSede.getSede().getNomeSede());
		txfNomeSede.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNomeSede.setBounds(231, 15, 183, 28);
		getContentPane().add(txfNomeSede);
		txfNomeSede.setColumns(10);
		
		JTextField txfProvincia = new JTextField(gestoreSede.getSede().getProvincia());
		txfProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfProvincia.setColumns(10);
		txfProvincia.setBounds(499, 15, 49, 28);
		getContentPane().add(txfProvincia);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblProvincia.setBounds(424, 11, 76, 39);
		getContentPane().add(lblProvincia);
		
		JTextField txfCitta = new JTextField(gestoreSede.getSede().getCitta());
		txfCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCitta.setColumns(10);
		txfCitta.setBounds(603, 15, 106, 28);
		getContentPane().add(txfCitta);
		
		JLabel lblCitta = new JLabel("Città");
		lblCitta.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCitta.setBounds(558, 11, 42, 39);
		getContentPane().add(lblCitta);
		
		JTextField txfVia = new JTextField(gestoreSede.getSede().getVia());
		txfVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfVia.setColumns(10);
		txfVia.setBounds(760, 15, 172, 28);
		getContentPane().add(txfVia);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVia.setBounds(727, 11, 42, 39);
		getContentPane().add(lblVia);
		
		JTextField txfNumCivico = new JTextField(gestoreSede.getSede().getNumCivico());
		txfNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNumCivico.setColumns(10);
		txfNumCivico.setBounds(970, 15, 76, 28);
		getContentPane().add(txfNumCivico);
		
		JLabel lblNumCivico = new JLabel("N.");
		lblNumCivico.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNumCivico.setBounds(942, 11, 18, 39);
		getContentPane().add(lblNumCivico);
		
		JButton btnSalva = new JButton("SALVA");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(1056, 11, 118, 39);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneSedeFrame();
				}
			}
		});
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.setBounds(1056, 61, 118, 39);
		getContentPane().add(btnChiudi);
		
		JPasswordField psfPassword = new JPasswordField(gestoreSede.getPassword());
		psfPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		psfPassword.setColumns(10);
		psfPassword.setBounds(289, 66, 164, 28);
		getContentPane().add(psfPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblPassword.setBounds(212, 62, 87, 39);
		getContentPane().add(lblPassword);
		
		JLabel lblNomeUtente = new JLabel("Gestore: " + gestoreSede.getNomeUtente());
		lblNomeUtente.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeUtente.setBounds(10, 61, 236, 39);
		getContentPane().add(lblNomeUtente);
		
		JRadioButton rdbtnVisualizzaPassword = new JRadioButton("Visualizza");
		rdbtnVisualizzaPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(rdbtnVisualizzaPassword.isSelected()) {
						psfPassword.setEchoChar((char)0);
					}else {
						psfPassword.setEchoChar((char)'●');
					}
				}
			}
		});
		rdbtnVisualizzaPassword.setBounds(459, 68, 109, 23);
		getContentPane().add(rdbtnVisualizzaPassword);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(687, 61, 76, 39);
		getContentPane().add(lblTelefono);
		
		JTextField txfTelefono = new JTextField(gestoreSede.getSede().getTelefonoSede());
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(760, 66, 143, 28);
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
		
		JScrollPane scpRider = new JScrollPane();
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
		btnEliminaRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblRider.getSelectedRowCount() > 0) {
						controllerAmministratore.EliminaRider(gestoreSede.getSede().getIdSede(), (int) tblRider.getValueAt(tblRider.getSelectedRow(), 0));
					}else {
						Errore();
					}
				}
			}
		});
		btnEliminaRider.setBounds(1069, 616, 105, 34);
		getContentPane().add(btnEliminaRider);
		
		JButton btnAggiungiRider = new JButton("AGGIUNGI");
		btnAggiungiRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ApriNuovoRiderFrame(lblIdSede.getText());
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
		JOptionPane.showMessageDialog(this,"Nessuna riga selezionata","Errore",JOptionPane.ERROR_MESSAGE);
	}
}
