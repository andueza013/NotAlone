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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setResizable(false);
		try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e){
            System.out.println(e);
        }
		setTitle("HOME");
		b.conexion();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1081, 644);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 10, 207, 587);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.setIcon(null);
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile p=new Profile();
				p.main(null);
				
				
			}
		});
		btnProfile.setBounds(41, 36, 116, 43);
		panel.add(btnProfile);
		
		JButton btnChats = new JButton("Chats");
		btnChats.setIcon(null);
		btnChats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaChats c=new ListaChats();
				c.main(null);
				
			}
		});
		btnChats.setBounds(41, 112, 116, 43);
		panel.add(btnChats);
		
		JButton btnFriends = new JButton("Add Friends");
		btnFriends.setIcon(null);
		btnFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_friend a=new add_friend();
				a.main(null);
			}
		});
		btnFriends.setBounds(41, 199, 116, 43);
		panel.add(btnFriends);
		
		JButton btnNewButton = new JButton("Post");
		btnNewButton.setIcon(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Postes p=new Postes();
				p.ini();
			}
		});
		btnNewButton.setBounds(41, 282, 116, 43);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(227, 10, 830, 587);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("BIENVENIDO/A "+b.Busqueda(log_in.u.getEmail()));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(10, 36, 810, 452);
		panel_1.add(lblNewLabel_1);
		System.out.println(b.Busqueda(log_in.u.getEmail()));
	}
}
