package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;
import Controller.ControllerAmministratore;
import Entities.Prodotto;
import Utility.FiltroDecimali;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

public class ModificaProdottoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txfNomeP;
	private JTextArea txpDescrizione;
	private JTextField txfPrezzo;
	private JTable tblSedi;
	private JTable tblAllergeni;
	private ControllerAmministratore controllerAmministratore = null;
	private JFrame parent = this;
	private Point initialClick;
	private Prodotto prodotto = null;
	private boolean NomeInserito, DescrizioneInserita, PrezzoInserito, CategoriaInserita;
	private JButton btnAggiorna;

	public ModificaProdottoFrame(ControllerAmministratore ControllerAmministratore, Prodotto prodotto) {
		this.controllerAmministratore = ControllerAmministratore;
		this.prodotto = prodotto;
		setTitle("Modifica Prodotto");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txfNomeP = new JTextField(prodotto.getNomeProdotto());
		txfNomeP.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfNomeP.getText().isBlank() && !txfNomeP.getText().equals(prodotto.getNomeProdotto())) 
					NomeInserito = true;
				else 
					NomeInserito = false;
				ControllaModifiche();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfNomeP.getText().isBlank() && !txfNomeP.getText().equals(prodotto.getNomeProdotto())) 
					NomeInserito = true;
				else 
					NomeInserito = false;
				ControllaModifiche();	
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txfNomeP.getText().isBlank() && !txfNomeP.getText().equals(prodotto.getNomeProdotto())) 
					NomeInserito = true;
				else 
					NomeInserito = false;
				ControllaModifiche();
				
			}
		});
		txfNomeP.setBounds(145, 104, 197, 24);
		txfNomeP.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfNomeP.setColumns(10);
		contentPane.add(txfNomeP);

		  txfPrezzo = new JTextField(String.format(Locale.US, "%.2f", prodotto.getPrezzo()));
	      PlainDocument docMin = (PlainDocument) txfPrezzo.getDocument();
	      docMin.setDocumentFilter(new FiltroDecimali());
		txfPrezzo.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfPrezzo.getText().isBlank() && Float.parseFloat(txfPrezzo.getText()) > 0 && Float.parseFloat(txfPrezzo.getText())!= prodotto.getPrezzo() ) {
					
						PrezzoInserito = true;
				}
				else 
					PrezzoInserito = false;
					
				ControllaModifiche();
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfPrezzo.getText().isBlank() && Float.parseFloat(txfPrezzo.getText()) > 0 && Float.parseFloat(txfPrezzo.getText())!= prodotto.getPrezzo() ) {
					
						PrezzoInserito = true;
				}
				else 
					PrezzoInserito = false;
					
				ControllaModifiche();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				if(!txfPrezzo.getText().isBlank() && Float.parseFloat(txfPrezzo.getText()) > 0 && Float.parseFloat(txfPrezzo.getText())!= prodotto.getPrezzo() ) {
					
						PrezzoInserito = true;
				}
				else 
					PrezzoInserito = false;
					
				ControllaModifiche();
			}		
		});
		txfPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		txfPrezzo.setBounds(145, 634, 86, 23);
		txfPrezzo.setColumns(10);
		contentPane.add(txfPrezzo);
		
		txpDescrizione = new JTextArea(prodotto.getDescrizione());
		txpDescrizione.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txpDescrizione!= null && !txpDescrizione.getText().toString().equals(prodotto.getDescrizione()))
					DescrizioneInserita = true;
				else
					DescrizioneInserita = false;
				ControllaModifiche();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txpDescrizione!= null && !txpDescrizione.getText().toString().equals(prodotto.getDescrizione()))
					DescrizioneInserita = true;
				else
					DescrizioneInserita = false;
				ControllaModifiche();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				if(txpDescrizione!= null && !txpDescrizione.getText().toString().equals(prodotto.getDescrizione()))
					DescrizioneInserita = true;
				else
					DescrizioneInserita = false;
				ControllaModifiche();	
			}
		});
		txpDescrizione.setLineWrap(true);
		txpDescrizione.setSize(288, 136);
		txpDescrizione.setLocation(new Point(145, 226));
		txpDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(txpDescrizione);

		JLabel lblAllergeni = new JLabel("Allergeni presenti");
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblAllergeni.setBounds(10, 383, 103, 20);
		contentPane.add(lblAllergeni);
		
		JLabel lblPrezzo = new JLabel("Prezzo");
		lblPrezzo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPrezzo.setBounds(63, 643, 50, 14);
		contentPane.add(lblPrezzo);
		
		JLabel lblEuro = new JLabel("\u20AC");
		lblEuro.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEuro.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEuro.setBounds(239, 639, 25, 18);
		contentPane.add(lblEuro);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategoria.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCategoria.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblCategoria.setBounds(46, 163, 67, 22);
		contentPane.add(lblCategoria);
		
		JLabel lblIDProdotto = new JLabel("ID Prodotto :  "+prodotto.getIdProdotto()+"");
		lblIDProdotto.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblIDProdotto.setBounds(46, 60, 185, 18);
		contentPane.add(lblIDProdotto);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(79, 107, 34, 18);
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione");
		lblDescrizione.setBounds(46, 226, 65, 18);
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblDescrizione);
			
		JLabel lblSedi = new JLabel("Sedi in cui e presente questo prodotto :");
		lblSedi.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblSedi.setBounds(489, 100, 261, 32);
		contentPane.add(lblSedi);
						
		JComboBox cbxCategorie = new JComboBox();
		cbxCategorie.setModel(new DefaultComboBoxModel(controllerAmministratore.getCategorie()));
		cbxCategorie.getModel().setSelectedItem(prodotto.getCategoria());
		cbxCategorie.setBounds(145, 163, 197, 22);
		cbxCategorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxCategorie.getSelectedItem() != null  && !cbxCategorie.getSelectedItem().toString().equals(prodotto.getCategoria())) 
					CategoriaInserita = true;
				else
					CategoriaInserita = false;
				ControllaModifiche();
			}
		});
		contentPane.add(cbxCategorie);

		JScrollPane spnlSedi = new JScrollPane();
		spnlSedi.setBounds(489, 133, 630, 407);
		contentPane.add(spnlSedi);
		
		tblSedi = new JTable();
		tblSedi.setRowHeight(20);
		tblSedi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblSedi.setModel(new DefaultTableModel(
			controllerAmministratore.getSediPerProdotto(prodotto.getIdProdotto()),
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
		spnlSedi.setViewportView(tblSedi);
		
	
		
		JScrollPane spnlAllergeni = new JScrollPane();
		spnlAllergeni.setBounds(142, 383, 291, 196);
		contentPane.add(spnlAllergeni);
		
		tblAllergeni = new JTable();
		tblAllergeni.setModel(new DefaultTableModel(
			controllerAmministratore.getAllergeniProdotto(prodotto.getIdProdotto()),
			new String[] {
				"Allergeni"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblAllergeni.getColumnModel().getColumn(0).setResizable(false);
		tblAllergeni.setFillsViewportHeight(true);
		tblAllergeni.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		spnlAllergeni.setViewportView(tblAllergeni);
		
		
		JButton btnAggiungiSede = new JButton("Aggiungi prodotto a sede");
		btnAggiungiSede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					if(controllerAmministratore.getSediMancanti(prodotto.getIdProdotto()).length > 0)
						controllerAmministratore.ApriAggiungiSedeFrame(prodotto.getIdProdotto());
					else
						JOptionPane.showMessageDialog(null,  "Nessuna sede disponibile, il prodotto è già presente in tutte", "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnAggiungiSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiSede.setBounds(714, 547, 197, 32);
		contentPane.add(btnAggiungiSede);

		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerAmministratore.ChiudiModificaProdotto();
			}
		});
		btnChiudi.setBounds(764, 651, 163, 38);
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(btnChiudi);
		
		
		btnAggiorna = new JButton("AGGIORNA");
		btnAggiorna.setEnabled(false);
		btnAggiorna.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnAggiorna.isEnabled()) {
					controllerAmministratore.AggiornaProdotto(prodotto.getIdProdotto(), txfNomeP.getText().replaceAll("'", "''"), txpDescrizione.getText().replaceAll("'", "''"), Float.parseFloat(txfPrezzo.getText()), cbxCategorie.getSelectedItem().toString().replaceAll("'", "''"));
				}
			}
		});
		btnAggiorna.setBounds(958, 651, 163, 38);
		btnAggiorna.setFont(new Font("Calibri", Font.PLAIN, 14));
		
		contentPane.add(btnAggiorna);
		
		JButton btnEliminaAllergeni = new JButton("Elimina");
		btnEliminaAllergeni.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblAllergeni.getSelectedRowCount() > 0) {
						
						String[] Allergeni = new String[tblAllergeni.getSelectedRowCount()];
						int righe[] = tblAllergeni.getSelectedRows();
							for(int i = 0; i<tblAllergeni.getSelectedRowCount(); i++)
								Allergeni[i] = (String) tblAllergeni.getValueAt(righe[i], 0);
				
								controllerAmministratore.EliminaAllergeniDaProdotto(prodotto.getIdProdotto(), Allergeni);
					}
					else JOptionPane.showMessageDialog(null, "Nessun allergene selezionato! ", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminaAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEliminaAllergeni.setBounds(344, 580, 89, 23);
		contentPane.add(btnEliminaAllergeni);
		
		JButton btnAggiungiAllergene = new JButton("Aggiungi");
		btnAggiungiAllergene.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(controllerAmministratore.getAllergeniMancanti(prodotto.getIdProdotto()).length > 0) 
					controllerAmministratore.ApriAggiungiAllergeniFrame(prodotto.getIdProdotto());
				else
				JOptionPane.showInternalMessageDialog(null, "Nessun allergene disponibile! ", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAggiungiAllergene.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnAggiungiAllergene.setBounds(253, 580, 89, 23);
		contentPane.add(btnAggiungiAllergene);
		
		JButton btnEliminaSede = new JButton("Elimina prodotto da sede");
		btnEliminaSede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(tblSedi.getSelectedRowCount() > 0) {
						int[] Sedi = new int[tblSedi.getSelectedRowCount()];
						int righe[] = tblSedi.getSelectedRows();
							for(int i = 0; i<tblSedi.getSelectedRowCount(); i++)
								Sedi[i] = (int) tblSedi.getValueAt(righe[i], 0);
							controllerAmministratore.EliminaProdottoDaSedi(prodotto.getIdProdotto(), Sedi);
					}
					else
						JOptionPane.showMessageDialog(null, "Nessuna sede selezionata! ", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEliminaSede.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnEliminaSede.setBounds(924, 547, 197, 32);
		contentPane.add(btnEliminaSede);
		

		JPanel pnlBarra = new JPanel();
		pnlBarra.setLayout(null);
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		
		JLabel ModificaProdotto = new JLabel("Modifica Prodotto");
		ModificaProdotto.setForeground(Color.WHITE);
		ModificaProdotto.setFont(new Font("Calibri", Font.PLAIN, 18));
		ModificaProdotto.setBounds(10, 0, 209, 35);
		pnlBarra.add(ModificaProdotto);

	
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
		
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		this.setVisible(true);

		

	}

	private void ControllaModifiche() {
		if(NomeInserito == true || DescrizioneInserita == true || CategoriaInserita == true || PrezzoInserito == true)
			btnAggiorna.setEnabled(true);
		else 
			btnAggiorna.setEnabled(false);
	}			

	public void AggiornaTabellaAllergeni() {
		tblAllergeni.setModel(new DefaultTableModel(
				controllerAmministratore.getAllergeniProdotto(prodotto.getIdProdotto()),
				new String[] {
					"Allergeni"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tblAllergeni.getColumnModel().getColumn(0).setResizable(false);
			tblAllergeni.setFillsViewportHeight(true);
			tblAllergeni.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));	
		
	}

	public void AggiornaTabellaSedi() {
		tblSedi.setRowHeight(20);
		tblSedi.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblSedi.setModel(new DefaultTableModel(
			controllerAmministratore.getSediPerProdotto(prodotto.getIdProdotto()),
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

	}
}
