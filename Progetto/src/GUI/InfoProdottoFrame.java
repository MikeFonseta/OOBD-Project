package GUI;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color; 
import javax.swing.JButton; 
import Controller.ControllerGestore;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class InfoProdottoFrame extends JFrame {

	private JPanel pnlinfo;
	private ControllerGestore controllerGestore=null;
	private Point initialClick;
	private JFrame parent=this;
	private int ID;

	public InfoProdottoFrame(ControllerGestore ControllerGestore, int idProdotto) {
		
		this.controllerGestore=ControllerGestore;
		String[] dati=this.controllerGestore.getSingoloProdotto(idProdotto);
		this.ID=idProdotto;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		pnlinfo = new JPanel();
		pnlinfo.setBackground(UIManager.getColor("Panel.background"));
		pnlinfo.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(pnlinfo);
		pnlinfo.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setVerticalAlignment(SwingConstants.TOP);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(52, 87, 59, 14);
		pnlinfo.add(lblNome);
		
		JLabel lblDescrizione = new JLabel("Descrizione: ");
		lblDescrizione.setVerticalAlignment(SwingConstants.TOP);
		lblDescrizione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrizione.setBounds(52, 158, 77, 14);
		pnlinfo.add(lblDescrizione);
		
		JLabel lblAllergeni = new JLabel("Allergeni: ");
		lblAllergeni.setVerticalAlignment(SwingConstants.TOP);
		lblAllergeni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAllergeni.setBounds(52, 240, 59, 23);
		pnlinfo.add(lblAllergeni);
		
		JLabel lblPrezzo = new JLabel("Prezzo: ");
		lblPrezzo.setVerticalAlignment(SwingConstants.TOP);
		lblPrezzo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPrezzo.setBounds(52, 314, 59, 14);
		pnlinfo.add(lblPrezzo);
		
		JLabel lblDatiNome = new JLabel(dati[0]);
		lblDatiNome.setVerticalAlignment(SwingConstants.TOP);
		lblDatiNome.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDatiNome.setBounds(128, 87, 212, 60);
		pnlinfo.add(lblDatiNome);
		
		JLabel lblDatiDescrizione = new JLabel(dati[1]);
		lblDatiDescrizione.setVerticalAlignment(SwingConstants.TOP);
		lblDatiDescrizione.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDatiDescrizione.setBounds(128, 158, 212, 60);
		pnlinfo.add(lblDatiDescrizione);
		
		JLabel lblDatiAllergeni = new JLabel(dati[2]);
		lblDatiAllergeni.setVerticalAlignment(SwingConstants.TOP);
		lblDatiAllergeni.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDatiAllergeni.setBounds(128, 240, 212, 60);
		pnlinfo.add(lblDatiAllergeni);
		
		JLabel lblDatiPrezzo = new JLabel(dati[3]);
		lblDatiPrezzo.setVerticalAlignment(SwingConstants.TOP);
		lblDatiPrezzo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDatiPrezzo.setBounds(128, 315, 212, 60);
		pnlinfo.add(lblDatiPrezzo);
		
		JButton btnChiudi = new JButton("Chiudi");
		btnChiudi.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnChiudi.setBounds(145, 434, 89, 23);
		pnlinfo.add(btnChiudi);
		btnChiudi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					controllerGestore.ChiudiInfoProdottoFrame();	
			}
		});

		JPanel pnlBarra = new JPanel();
		pnlBarra.setBackground(Color.DARK_GRAY);
		pnlBarra.setBounds(0, 0, 1200, 35);
		getContentPane().add(pnlBarra);
		pnlBarra.setLayout(null);
		
		JLabel lblTitolo = new JLabel("Info Prodotto");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTitolo.setBounds(10, 0, 209, 35);
		pnlBarra.add(lblTitolo);
		
		
		pnlBarra.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	            initialClick = e.getPoint();
	            getComponentAt(initialClick);
	        }
	    });

	    pnlBarra.addMouseMotionListener(new MouseMotionAdapter() {
	        @Override
	        public void mouseDragged(MouseEvent e) {
 
	            int thisX = parent.getLocation().x;
	            int thisY = parent.getLocation().y;
 
	            int xMoved = e.getX() - initialClick.x;
	            int yMoved = e.getY() - initialClick.y;
 
	            int X = thisX + xMoved;
	            int Y = thisY + yMoved;
	            parent.setLocation(X, Y);
	        }
	    });
		
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
	}
	
	public int getID() {
		return this.ID;
	}
}
