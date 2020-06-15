package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.BaseDeDatos;
import Controlador.Cliente;
import Modelo.Post;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class NuevoPost extends JDialog {
	BaseDeDatos b=new BaseDeDatos();
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	static JLabel lblNewLabel;
	static NuevoPost dialog;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			
			dialog = new NuevoPost();
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
	public NuevoPost() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoPost.class.getResource("/Resources/logo.png")));
		setTitle("NUEVO POST");
		setLocationRelativeTo(null);
		b.conexion();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setForeground(new Color(0, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 414, 53);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Image");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
	            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	            int returnVal = chooser.showOpenDialog(null);
	            if(returnVal == JFileChooser.APPROVE_OPTION) {
	            	lblNewLabel.setText(chooser.getSelectedFile().getAbsolutePath()); 
			}
			}});
		btnNewButton.setBounds(10, 120, 89, 23);
		contentPanel.add(btnNewButton);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(123, 124, 301, 14);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 224, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Colgar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Cliente c=new Cliente();
						Post p=new Post();
						p.setIdusuario(log_in.u.getIdusuario());
						p.setImagen(lblNewLabel.getText());
						p.setContenido(textField.getText());
						p.setTitulo("");
						
						c.reciboPost(p);
						c.main(null);
					}
				});
				
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
