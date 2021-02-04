package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.ControllerAmministratore;
import Entities.Rider;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;

public class GestioneRiderFrame extends JFrame{
	
	private JPanel pnlmain;
	private ControllerAmministratore controllerAmministratore = null;
	private Point initialClick;
	private JFrame parent=this;
	private JButton btnSalva;
	private boolean Nome=false,Cognome=false,Telefono=false,Veicolo=false;

	public GestioneRiderFrame(ControllerAmministratore ControllerAmministratore, int idSede, int idRider) {
		this.controllerAmministratore =  ControllerAmministratore;
		setUndecorated(true);
		setResizable(false);
		setSize(new Dimension(430, 496));
		setPreferredSize(new Dimension(430, 496));
		setMinimumSize(new Dimension(430, 496));

		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
		
		JLabel lblIdRiderTxt = new JLabel("ID RIder ");
		lblIdRiderTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRiderTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRiderTxt.setBounds(37, 64, 116, 39);
		getContentPane().add(lblIdRiderTxt);
		
		JLabel lblIdRider = new JLabel(""+idRider);
		lblIdRider.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRider.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRider.setBounds(179, 64, 116, 39);
		getContentPane().add(lblIdRider);
		
		btnSalva = new JButton("AGGIUNGI");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(228, 411, 130, 44);
		btnSalva.setEnabled(false);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneRiderFrame();
				}
			}
		});
		btnChiudi.setBounds(67, 411, 130, 44);
		getContentPane().add(btnChiudi);
		
		JTextField txfNome = new JTextField();
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfNome.getText().length()>0) {
					Nome=true;
				}else {
					Nome=false;
				}
				ControllaModifiche("Creazione");
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfNome.getText().length()>0) {
					Nome=true;
				}else {
					Nome=false;
				}
				ControllaModifiche("Creazione");
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
		}});
		txfNome.setBounds(179, 128, 210, 30);
		getContentPane().add(txfNome);
		txfNome.setColumns(10);
		
		JTextField txfCognome = new JTextField();
		txfCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCognome.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfCognome.getText().length()>0) {
					Cognome=true;
				}else {
					Cognome=false;
				}
				ControllaModifiche("Creazione");
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfCognome.getText().length()>0) {
					Cognome=true;
				}else {
					Cognome=false;
				}
				ControllaModifiche("Creazione");
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
		}});
		txfCognome.setColumns(10);
		txfCognome.setBounds(178, 194, 210, 30);
		getContentPane().add(txfCognome);
		
		JTextField txfTelefono = new JTextField();
		txfTelefono.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(txfTelefono.getText().length()>0) {
					Telefono=true;
				}else {
					Telefono=false;
				}
				ControllaModifiche("Creazione");
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(txfTelefono.getText().length()>0) {
					Telefono=true;
				}else {
					Telefono=false;
				}
				ControllaModifiche("Creazione");
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
		}});
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(178, 255, 210, 30);
		getContentPane().add(txfTelefono);
		
		JComboBox cbxVeicolo = new JComboBox(new DefaultComboBoxModel(controllerAmministratore.getVeicoli()));
		cbxVeicolo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
                if(!cbxVeicolo.getSelectedItem().toString().equals("Nessun veicolo")) {
                	Veicolo=true;
                }else {
                	Veicolo=false;
                }
                ControllaModifiche("Creazione");
            }
        });
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxVeicolo.setBounds(178, 313, 210, 30);
		getContentPane().add(cbxVeicolo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(37, 128, 130, 30);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCognome.setBounds(38, 193, 130, 30);
		getContentPane().add(lblCognome);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(38, 254, 130, 30);
		getContentPane().add(lblTelefono);
		
		JLabel lblVeicolo = new JLabel("Veicolo");
		lblVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVeicolo.setBounds(38, 313, 130, 30);
		getContentPane().add(lblVeicolo);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 430, 35);
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
		
		
		JLabel lblTitolo = new JLabel("Aggiungi Rider");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnSalva.isEnabled()) {
					controllerAmministratore.CreaRider(Integer.parseInt(lblIdRider.getText()),txfNome.getText().replaceAll("'", "''"),txfCognome.getText().replaceAll("'", "''"),txfTelefono.getText(),cbxVeicolo.getSelectedItem().toString().replaceAll("'", "''"),idSede);
				}
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public GestioneRiderFrame(ControllerAmministratore controllerAmministratore,Rider rider) {
		this.controllerAmministratore = controllerAmministratore;
		setUndecorated(true);
		setResizable(false);
		setSize(new Dimension(430, 496));
		setPreferredSize(new Dimension(430, 496));
		setMinimumSize(new Dimension(430, 496));
		
		pnlmain = new JPanel();
		pnlmain.setBackground(UIManager.getColor("Panel.background"));
		pnlmain.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlmain);
		pnlmain.setLayout(null);
			
		JLabel lblIdRiderTxt = new JLabel("ID RIder ");
		lblIdRiderTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRiderTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRiderTxt.setBounds(37, 64, 116, 39);
		getContentPane().add(lblIdRiderTxt);
		
		JLabel lblIdRider = new JLabel(""+rider.getIdRider());
		lblIdRider.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRider.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRider.setBounds(179, 64, 116, 39);
		getContentPane().add(lblIdRider);
		
		btnSalva = new JButton("SALVA");
		btnSalva.setEnabled(false);
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(228, 411, 130, 44);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneRiderFrame();
				}
			}
		});
		btnChiudi.setBounds(67, 411, 130, 44);
		getContentPane().add(btnChiudi);
		
		JLabel txfNome = new JLabel(rider.getNomeRider());
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setBounds(179, 128, 210, 30);
		getContentPane().add(txfNome);
		
		JLabel txfCognome = new JLabel(rider.getCognomeRider());
		txfCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCognome.setBounds(178, 194, 210, 30);
		getContentPane().add(txfCognome);
		
		JTextField txfTelefono = new JTextField(rider.getTelefonoRider());
		txfTelefono.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(!txfTelefono.getText().equals(rider.getTelefonoRider()) && txfTelefono.getText().length()==10) {
					Telefono=true;
				}else {
					Telefono=false;
				}
				ControllaModifiche("Modifica");
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(!txfTelefono.getText().equals(rider.getTelefonoRider()) && txfTelefono.getText().length()==10) {
					Telefono=true;
				}else {
					Telefono=false;
				}
				ControllaModifiche("Modifica");
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
		}});
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(178, 255, 210, 30);
		getContentPane().add(txfTelefono);
		
		JComboBox cbxVeicolo = new JComboBox(new DefaultComboBoxModel(controllerAmministratore.getVeicoli()));
		cbxVeicolo.setSelectedItem(rider.getVeicoloRider());
		cbxVeicolo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	
                if(!cbxVeicolo.getSelectedItem().toString().equals("Nessun veicolo")) {
                	Veicolo=true;
                }else {
                	Veicolo=false;
                }
                ControllaModifiche("Modifica");
            }
        });
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxVeicolo.setBounds(178, 313, 210, 30);
		getContentPane().add(cbxVeicolo);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(37, 128, 130, 30);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCognome.setBounds(38, 193, 130, 30);
		getContentPane().add(lblCognome);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTelefono.setBounds(38, 254, 130, 30);
		getContentPane().add(lblTelefono);
		
		JLabel lblVeicolo = new JLabel("Veicolo");
		lblVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblVeicolo.setBounds(38, 313, 130, 30);
		getContentPane().add(lblVeicolo);
		
		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 430, 35);
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
		
		
		JLabel lblTitolo = new JLabel("Modifica Rider");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setBounds(0, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1 && btnSalva.isEnabled()) {
					controllerAmministratore.AggiornaRider(rider.getIdRider(), txfTelefono.getText(), cbxVeicolo.getSelectedItem().toString().replaceAll("'", "''"));
				}
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void ControllaModifiche(String Operazione) {
		if(Operazione.equals("Modifica")){
			if(Telefono==true || Veicolo==true) {
				btnSalva.setEnabled(true);
			}else {
				btnSalva.setEnabled(false);
			}
		}else if(Operazione.equals("Creazione")) {
			if(Nome==true && Cognome==true && Telefono==true && Veicolo==true) {
				btnSalva.setEnabled(true);
			}else {
				btnSalva.setEnabled(false);
			}
		}
	}

}
