package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;

import Controller.ControllerAmministratore;
import Entities.Rider;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.Color;

public class GestioneRiderFrame extends JFrame{
	
	private ControllerAmministratore controllerAmministratore = null;
	private Point initialClick;
	private JFrame parent=this;


	public GestioneRiderFrame(ControllerAmministratore controllerAmministratore, int idSede) {
		this.controllerAmministratore = controllerAmministratore;
		setUndecorated(true);
		setResizable(false);
		setSize(new Dimension(430, 496));
		setPreferredSize(new Dimension(430, 496));
		setMinimumSize(new Dimension(430, 496));
		getContentPane().setLayout(null);
			
		JLabel lblIdRiderTxt = new JLabel("ID RIder ");
		lblIdRiderTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRiderTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRiderTxt.setBounds(37, 64, 116, 39);
		getContentPane().add(lblIdRiderTxt);
		
		JLabel lblIdRider = new JLabel("" + controllerAmministratore.getIdProssimoRider());
		lblIdRider.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRider.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRider.setBounds(179, 64, 116, 39);
		getContentPane().add(lblIdRider);
		
		JButton btnSalva = new JButton("AGGIUNGI");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(228, 411, 130, 44);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneRiderFrame();
				}
			}
		});
		btnChiudi.setBounds(67, 411, 130, 44);
		getContentPane().add(btnChiudi);
		
		JTextField txfNome = new JTextField();
		txfNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfNome.setBounds(179, 128, 210, 30);
		getContentPane().add(txfNome);
		txfNome.setColumns(10);
		
		JTextField txfCognome = new JTextField();
		txfCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfCognome.setColumns(10);
		txfCognome.setBounds(178, 194, 210, 30);
		getContentPane().add(txfCognome);
		
		JTextField txfTelefono = new JTextField();
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(178, 255, 210, 30);
		getContentPane().add(txfTelefono);
		
		JComboBox cbxVeicolo = new JComboBox();
		cbxVeicolo.setModel(new DefaultComboBoxModel(new String[] {"Nessun veicolo", "Auto", "Bici","Scooter", "Scooter Elettrico"}));
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
		lblTitolo.setBounds(153, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.CreaRider(Integer.parseInt(lblIdRider.getText()),txfNome.getText(),txfCognome.getText(),txfTelefono.getText(),cbxVeicolo.getSelectedItem().toString(),idSede);
				}
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public GestioneRiderFrame(ControllerAmministratore controllerAmministratore, Rider rider) {
		this.controllerAmministratore = controllerAmministratore;
		setUndecorated(true);
		setResizable(false);
		setSize(new Dimension(430, 496));
		setPreferredSize(new Dimension(430, 496));
		setMinimumSize(new Dimension(430, 496));
		getContentPane().setLayout(null);
			
		JLabel lblIdRiderTxt = new JLabel("ID RIder ");
		lblIdRiderTxt.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRiderTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRiderTxt.setBounds(37, 64, 116, 39);
		getContentPane().add(lblIdRiderTxt);
		
		JLabel lblIdRider = new JLabel("" + rider.getIdRider());
		lblIdRider.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdRider.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblIdRider.setBounds(179, 64, 116, 39);
		getContentPane().add(lblIdRider);
		
		JButton btnSalva = new JButton("SALVA");
		btnSalva.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnSalva.setBounds(228, 411, 130, 44);
		getContentPane().add(btnSalva);
		
		JButton btnChiudi = new JButton("CHIUDI");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 18));
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.ChiudiGestioneRiderFrame();
				}
			}
		});
		btnChiudi.setBounds(67, 411, 130, 44);
		getContentPane().add(btnChiudi);
		
		JLabel lblNome = new JLabel(rider.getNomeRider());
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNome.setBounds(179, 128, 210, 30);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel(rider.getCognomeRider());
		lblCognome.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCognome.setBounds(178, 194, 210, 30);
		getContentPane().add(lblCognome);
		
		JTextField txfTelefono = new JTextField(rider.getTelefonoRider());
		txfTelefono.setFont(new Font("Calibri", Font.PLAIN, 18));
		txfTelefono.setColumns(10);
		txfTelefono.setBounds(178, 255, 210, 30);
		getContentPane().add(txfTelefono);
		
		JComboBox cbxVeicolo = new JComboBox();
		cbxVeicolo.setModel(new DefaultComboBoxModel(new String[] {"Nessun veicolo", "Auto", "Bici","Scooter", "Scooter Elettrico"}));
		cbxVeicolo.setSelectedItem(rider.getVeicoloRider());
		cbxVeicolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		cbxVeicolo.setBounds(178, 313, 210, 30);
		getContentPane().add(cbxVeicolo);
		
		JLabel lblNomeTxt = new JLabel("Nome");
		lblNomeTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblNomeTxt.setBounds(37, 128, 130, 30);
		getContentPane().add(lblNomeTxt);
		
		JLabel lblCognomeTxt = new JLabel("Cognome");
		lblCognomeTxt.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblCognomeTxt.setBounds(38, 193, 130, 30);
		getContentPane().add(lblCognomeTxt);
		
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
		lblTitolo.setBounds(153, 0, 127, 35);
		pnlBarra.add(lblTitolo);
		
		btnSalva.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.BUTTON1 == MouseEvent.BUTTON1) {
					controllerAmministratore.AggiornaRider(rider.getIdRider(), txfTelefono.getText(), cbxVeicolo.getSelectedItem().toString());
				}
			}
		});
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}


}
