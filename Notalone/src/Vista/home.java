package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.BaseDeDatos;
import Modelo.Usuario;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class home extends JFrame {

	private JPanel contentPane;
	public static Usuario user=new Usuario();
	BaseDeDatos b=new BaseDeDatos();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					user=log_in.u;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/Resources/logo.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e){
            System.out.println(e);
        }
		setTitle("HOME");
		b.conexion();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 644);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 10, 760, 587);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		user=log_in.u;
		JLabel lblNewLabel_1 = new JLabel("BIENVENIDO/A"+" "+user.getNombre());
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(225, 45, 470, 77);
		panel_1.add(lblNewLabel_1);
		
		JButton btnProfile = new JButton("");
		btnProfile.setIcon(new ImageIcon(home.class.getResource("/Resources/IconoPerfil.png")));
		btnProfile.setBounds(161, 216, 146, 67);
		panel_1.add(btnProfile);
		
		
		JButton btnChats = new JButton("");
		btnChats.setIcon(new ImageIcon(home.class.getResource("/Resources/iconoPosts.png")));
		btnChats.setBounds(480, 216, 146, 67);
		panel_1.add(btnChats);
		
		
		JButton btnFriends = new JButton("");
		btnFriends.setIcon(new ImageIcon(home.class.getResource("/Resources/iconoAmigos.png")));
		btnFriends.setBounds(161, 404, 146, 58);
		panel_1.add(btnFriends);
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(home.class.getResource("/Resources/IconoChats.png")));
		btnNewButton.setBounds(480, 404, 146, 58);
		panel_1.add(btnNewButton);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(69, 34, 146, 111);
		Image img = new ImageIcon("\\\\LAPTOP-36SH2NU5\\imagenes\\logo.png").getImage();
		ImageIcon img2 = new ImageIcon(img.getScaledInstance(146, 111, Image.SCALE_SMOOTH));
		lblNewLabel.setIcon(img2);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("PERFIL");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(171, 293, 132, 20);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblChats = new JLabel("CHATS");
		lblChats.setHorizontalAlignment(SwingConstants.CENTER);
		lblChats.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChats.setBounds(480, 293, 142, 20);
		panel_1.add(lblChats);
		
		JLabel lblAddFriends = new JLabel("A\u00D1ADIR AMIGOS");
		lblAddFriends.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddFriends.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddFriends.setBounds(161, 472, 132, 20);
		panel_1.add(lblAddFriends);
		
		JLabel lblPosts = new JLabel("POSTS");
		lblPosts.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosts.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPosts.setBounds(480, 472, 146, 20);
		panel_1.add(lblPosts);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Postes p=new Postes();
				p.ini();
			}
		});
		btnFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_friend a=new add_friend();
				a.main(null);
			}
		});
		btnChats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaChats c=new ListaChats();
				c.main(null);
				
			}
		});
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile p=new Profile();
				p.main(null);
				
				
			}
		});
		System.out.println(b.Busqueda(log_in.u.getEmail()));
	}
}