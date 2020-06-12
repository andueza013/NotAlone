package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		try {
			register dialog = new register();
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
		setLocationRelativeTo(null);
		setTitle("REGISTRO");
		b.conexion();
		setBounds(100, 100, 556, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setBounds(10, 71, 90, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 120, 90, 13);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Repetir Password");
		lblNewLabel_2.setBounds(10, 185, 115, 13);
		contentPanel.add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(152, 68, 244, 28);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(198, 309, 334, 28);
		lblNewLabel_3.setVisible(condicionDeAlerta);
		
		lblNewLabel_3.setText(mensajeDeAlerta);
		
		
		contentPanel.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 117, 244, 28);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(152, 182, 244, 28);
		contentPanel.add(passwordField_1);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 32, 90, 13);
		contentPanel.add(lblNombre);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(152, 20, 244, 28);
		contentPanel.add(txtName);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(10, 240, 115, 13);
		contentPanel.add(lblTelfono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(152, 237, 244, 28);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
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
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
