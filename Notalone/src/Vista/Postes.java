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
import java.awt.Toolkit;
import java.awt.Font;

public class Postes extends JDialog {

	private JPanel contentPane;
	BaseDeDatos b =new BaseDeDatos();
	ArrayList<Post>posts=new ArrayList<Post>();
	int posicion=0;
	JLabel lblNewLabel;
	JTextArea textArea;
	public static Postes frame;

	/**
	 * Launch the application.
	 */
	public static void ini() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new Postes();
					frame.setLocationRelativeTo(null);
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
		setTitle("POSTS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Postes.class.getResource("/Resources/logo.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		b.conexion();
		posts=b.posts2(log_in.u.getIdusuario());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 665, 493);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(114, 62, 402, 221);
		contentPane.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.BOLD, 11));
		textArea.setBackground(new Color(176, 224, 230));
		textArea.setEditable(false);
		textArea.setBounds(114, 307, 402, 87);
		textArea.setBorder(null);
		contentPane.add(textArea);
		
		
		JButton btnNewButton = new JButton("<<");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(posicion>0) {
				posicion--;
				actualizar();}else {}
			}
		});
		btnNewButton.setBounds(24, 197, 43, 79);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton(">>");
		button.setFont(new Font("Tahoma", Font.PLAIN, 8));
		button.setIcon(null);
		actualizar();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(posicion<posts.size()-1) {
				posicion++;
				actualizar();}else {}
				
			}
		});
		button.setBounds(587, 198, 43, 78);
		contentPane.add(button);
		
		JButton btnNewButton_1 = new JButton("New Post");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevoPost p=new NuevoPost();
				p.main(null);
			}
		});
		btnNewButton_1.setBounds(444, 416, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnNewButton_2.setBounds(543, 416, 99, 22);
		contentPane.add(btnNewButton_2);
	}
	public void actualizar() {
		Image img=new ImageIcon(posts.get(posicion).getImagen()).getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(402, 221, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(img2);
		textArea.setText(posts.get(posicion).getContenido());
	}
	Runnable runnable = new Runnable() {

        @Override
        public void run() {
            while (true) {                    
                try {
                    Thread.sleep(2000);
                   
                    posts=b.posts2(log_in.u.getIdusuario());
                        
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
            
        }
        
    };
}
