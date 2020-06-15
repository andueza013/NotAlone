package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;

import Controlador.BaseDeDatos;
import Controlador.Methods;
import Modelo.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
public class register extends JDialog {

	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private boolean condicionDeAlerta=true;
	public  String mensajeDeAlerta;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	Methods m=new Methods();
	Usuario u=new Usuario();
	BaseDeDatos b=new BaseDeDatos();
	private JTextField txtName;
	private JTextField txtTelefono;
	public static register dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		try {
			dialog = new register();
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(register.class.getResource("/Resources/logo.png")));
		setLocationRelativeTo(null);
		setTitle("REGISTRO");
		b.conexion();
		setBounds(100, 100, 595, 742);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(152, 305, 90, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(152, 380, 90, 13);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Repetir Password");
		lblNewLabel_2.setBounds(152, 450, 115, 13);
		contentPanel.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(152, 327, 244, 28);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 401, 244, 28);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(152, 471, 244, 28);
		contentPanel.add(passwordField_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(152, 228, 90, 13);
		contentPanel.add(lblNombre);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(152, 244, 244, 28);
		contentPanel.add(txtName);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(152, 522, 115, 13);
		contentPanel.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(152, 546, 244, 28);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		
		JLabel lblimagen = new JLabel("");
		lblimagen.setIcon(new ImageIcon(register.class.getResource("/Resources/logo.png")));
		lblimagen.setBounds(152, 30, 244, 152);
		contentPanel.add(lblimagen);
		
		
		Image img = new ImageIcon("\\\\LAPTOP-36SH2NU5\\imagenes\\logo.png").getImage();
		ImageIcon img2 = new ImageIcon(img.getScaledInstance(244, 152, Image.SCALE_SMOOTH));
		lblimagen.setIcon(img2);
		
		{
			JButton okButton = new JButton("Aceptar");
			okButton.setBounds(368, 643, 90, 21);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char[] arrayC = passwordField.getPassword();
					char[] arrayC2 = passwordField_1.getPassword();

					String pass = new String(arrayC);
					String pass2 = new String(arrayC2);
					if(pass.equals(pass2) && pass.length()>0) {

					
					Usuario u=new Usuario();
					u.setEmail(txtEmail.getText());
					u.setNombre(txtName.getText());
					u.setTelefono(txtTelefono.getText());
					u.setPassword_encripted(m.Encript(pass));
					b.insertarUser(u);
					setVisible(false);
					}else {
						JOptionPane.showConfirmDialog(null, "Contraseñas no coincidentes");
					}
					
					
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Salir");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				dialog.dispose();
					
				}
			});
			cancelButton.setBounds(468, 643, 99, 21);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
}
