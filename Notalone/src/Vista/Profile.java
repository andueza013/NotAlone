package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Controlador.BaseDeDatos;
import Modelo.Usuario;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Profile extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTlf;
	BaseDeDatos b=new BaseDeDatos();
	log_in l=new log_in();
	Usuario u=new Usuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile frame = new Profile();
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
	public Profile() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Profile.class.getResource("/Resources/home.png")));
		setResizable(false);
		b.conexion();
		
		u=log_in.u;
		u=b.InfoPersonal(u);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(50, 50, 500, 300);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 54, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTlf = new JLabel("Tlf");
		lblTlf.setBounds(10, 92, 45, 13);
		contentPane.add(lblTlf);
		
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(10, 149, 45, 13);
		contentPane.add(lblEmail);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(65, 42, 96, 28);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setText(u.getNombre());
		
		txtTlf = new JTextField();
		txtTlf.setBounds(65, 89, 96, 34);
		contentPane.add(txtTlf);
		txtTlf.setColumns(10);
		txtTlf.setText(u.getTelefono());
		
		JLabel LblEmail = new JLabel("New label");
		LblEmail.setBounds(65, 149, 167, 28);
		contentPane.add(LblEmail);
		LblEmail.setText(u.getEmail());
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.setNombre(txtNombre.getText());
				u.setTelefono(txtTlf.getText());
				b.modify(u);
				JOptionPane.showMessageDialog(null, "Perfil modificado!");
			}
		});
		btnNewButton.setBounds(369, 241, 85, 21);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
