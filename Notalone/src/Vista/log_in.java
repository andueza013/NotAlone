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
import javax.swing.UIManager.LookAndFeelInfo;

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
			
			try {
				
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ( "Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
				
			}catch (Exception e) {
				
			}
			
			
			log_in dialog = new log_in();
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
	public log_in() {
		setResizable(false);
		/*try{
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e){
            System.out.println(e);
        }*/
		setTitle("LOG IN");
		setLocationRelativeTo(null);
		BaseDeDatos b=new BaseDeDatos();
		b.conexion();
		setIconImage(Toolkit.getDefaultToolkit().getImage(log_in.class.getResource("/Resources/logo.png")));
		setBounds(100, 100, 500, 343);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("E-mail");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(84, 53, 59, 13);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(84, 132, 109, 22);
		contentPanel.add(lblNewLabel_1);
		
		txtEmail = new JTextField("lokomoskito3@hotmail.com");
		txtEmail.setBounds(84, 82, 332, 28);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPass = new JPasswordField("fer 28121997");
		txtPass.setBounds(84, 164, 332, 28);
		contentPanel.add(txtPass);
		
		JButton btnNewButton = new JButton("Registro");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register registro=new register();
				registro.main(null);
				
				
			}
		});
		btnNewButton.setBounds(331, 220, 85, 21);
		contentPanel.add(btnNewButton);
		{
			JButton okButton = new JButton("Aceptar");
			okButton.setBounds(84, 220, 85, 21);
			contentPanel.add(okButton);
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
			getRootPane().setDefaultButton(okButton);
		}
		
		JLabel lblNewLabel_2 = new JLabel("\u00BFEres nuevo?");
		lblNewLabel_2.setBounds(254, 219, 77, 22);
		contentPanel.add(lblNewLabel_2);
	}
}
