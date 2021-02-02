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
		
		JLabel lblNome = new JLabel(dati[0]);
		lblNome.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNome.setBounds(108, 66, 180, 60);
		pnlinfo.add(lblNome);
		
		JLabel lblDescrizione = new JLabel(dati[1]);
		lblDescrizione.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblDescrizione.setBounds(108, 137, 180, 60);
		pnlinfo.add(lblDescrizione);
		
		JLabel lblAllergeni = new JLabel(dati[2]);
		lblAllergeni.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAllergeni.setBounds(108, 219, 180, 60);
		pnlinfo.add(lblAllergeni);
		
		JLabel lblPrezzo = new JLabel(dati[3]);
		lblPrezzo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPrezzo.setBounds(108, 293, 180, 60);
		pnlinfo.add(lblPrezzo);
		
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
