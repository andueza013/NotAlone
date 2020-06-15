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
import java.awt.Font;
import java.awt.Color;

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
	public Profile() {
		setTitle("PERFIL");
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Profile.class.getResource("/Resources/logo.png")));
		setResizable(false);
		b.conexion();
		
		u=log_in.u;
		u=b.InfoPersonal(u);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		setBounds(50, 50, 490, 355);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(65, 109, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblTlf = new JLabel("Tlf");
		lblTlf.setBounds(65, 167, 45, 13);
		contentPane.add(lblTlf);
		
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(65, 220, 45, 13);
		contentPane.add(lblEmail);
		
		txtNombre = new JTextField();
		txtNombre.setEnabled(false);
		txtNombre.setEditable(false);
		txtNombre.setBounds(136, 102, 96, 28);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setText(u.getNombre());
		
		txtTlf = new JTextField();
		txtTlf.setEnabled(false);
		txtTlf.setEditable(false);
		txtTlf.setBounds(136, 157, 96, 34);
		contentPane.add(txtTlf);
		txtTlf.setColumns(10);
		txtTlf.setText(u.getTelefono());
		
		JLabel LblEmail = new JLabel("New label");
		LblEmail.setBounds(136, 212, 167, 28);
		contentPane.add(LblEmail);
		LblEmail.setText(u.getEmail());
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				u.setNombre(txtNombre.getText());
				u.setTelefono(txtTlf.getText());
				b.modify(u);
				JOptionPane.showMessageDialog(null, "Perfil modificado!");
			}
		});
		btnNewButton.setBounds(355, 270, 105, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNombre.setEditable(true);
				txtNombre.setEnabled(true);
				txtTlf.setEditable(true);
				txtTlf.setEnabled(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(364, 23, 96, 28);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Informaci\u00F3n Personal");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(41, 45, 191, 19);
		contentPane.add(lblNewLabel_1);
		
		
		
		
	}
}
