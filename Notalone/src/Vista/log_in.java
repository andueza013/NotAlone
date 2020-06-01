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
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Modelo.Usuario;
import Controlador.BaseDeDatos;
import Controlador.Methods;
import java.awt.Color;

public class log_in extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEmail;
	private JPasswordField txtPass;
	static Usuario u=new Usuario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			log_in dialog = new log_in();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public log_in() {
		setResizable(false);
		try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e){
            System.out.println(e);
        }
		setTitle("LOG IN");
		BaseDeDatos b=new BaseDeDatos();
		b.conexion();
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\feryv\\Pictures\\Humpty_Dumpty.jpg"));
		setBounds(100, 100, 547, 333);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 61, 59, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 120, 109, 22);
		contentPanel.add(lblNewLabel_1);
		
		txtEmail = new JTextField("lokomoskito3@hotmail.com");
		txtEmail.setBounds(146, 58, 332, 28);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPass = new JPasswordField("fer 28121997");
		txtPass.setBounds(146, 125, 332, 28);
		contentPanel.add(txtPass);
		
		JButton btnNewButton = new JButton("Registro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register registro=new register();
				registro.main(null);
				
				
			}
		});
		btnNewButton.setBounds(10, 190, 85, 21);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Methods m=new Methods();
						
						char[] arrayC = txtPass.getPassword();
						String pass = new String(arrayC);
						u.setEmail(txtEmail.getText());
						u.setPassword_encripted(m.Encript(pass));
						u.setIdusuario(b.SacarID(u.getEmail()));
						System.out.println(u.getIdusuario());
						
						if(b.InicioSesion(u)) {
							JOptionPane.showMessageDialog(null,"Inicio de sesión correcto");
							home h=new home();
							h.main(null);
							setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null,"Inicio de sesión incorrecto");
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
