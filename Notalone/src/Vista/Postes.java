package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.BaseDeDatos;
import Modelo.Mensaje;
import Modelo.Post;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Postes extends JDialog {

	private JPanel contentPane;
	BaseDeDatos b =new BaseDeDatos();
	ArrayList<Post>posts=new ArrayList<Post>();
	int posicion=0;
	JLabel lblNewLabel;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void ini() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Postes frame = new Postes();
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
	public Postes() {
		setResizable(false);
		b.conexion();
		posts=b.posts(log_in.u.getIdusuario());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 646, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(69, 107, 468, 221);
		contentPane.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(69, 10, 468, 87);
		textArea.setBorder(null);
		contentPane.add(textArea);
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Postes.class.getResource("/de/javasoft/plaf/synthetica/whitevision/images/arrow8x8Up_disabled.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(posicion>0) {
				posicion--;
				actualizar();}else {}
			}
		});
		btnNewButton.setBounds(597, 30, 35, 79);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(Postes.class.getResource("/de/javasoft/plaf/synthetica/whitevision/images/arrowDown_disabled.png")));
		actualizar();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(posicion<posts.size()-1) {
				posicion++;
				actualizar();}else {}
				
			}
		});
		button.setBounds(597, 293, 35, 68);
		contentPane.add(button);
	}
	public void actualizar() {
		Image img=new ImageIcon(posts.get(posicion).getImagen()).getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(468, 221, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(img2);
		textArea.setText(posts.get(posicion).getContenido());
	}
	Runnable runnable = new Runnable() {

        @Override
        public void run() {
            while (true) {                    
                try {
                    Thread.sleep(2000);
                   
                    posts=b.posts(log_in.u.getIdusuario());
                        
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
            
        }
        
    };
}
