package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuario;
import Controlador.BaseDeDatos;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;


public class add_friend extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtmail;
	BaseDeDatos b=new BaseDeDatos();
	ArrayList<Usuario>users=new ArrayList<Usuario>();
	static add_friend dialog;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new add_friend();
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
	public add_friend() {
		setTitle("A\u00D1ADIR AMIGOS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(add_friend.class.getResource("/Resources/logo.png")));
		setLocationRelativeTo(null);
		setResizable(false);
		b.conexion();
		setBounds(100, 100, 578, 489);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(176, 224, 230));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 63, 370, 348);
		contentPanel.add(scrollPane);
		
		JList<Object> list = new JList<Object>();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		
		txtmail = new JTextField();
		txtmail.setBounds(127, 10, 370, 31);
		contentPanel.add(txtmail);
		txtmail.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultListModel<Object> modelo = new DefaultListModel();
				
				users=b.Busqueda(txtmail.getText());
				for(int i=0;i<users.size();i++) {
					
				modelo.addElement(users.get(i).getNombre());}
				list.setModel(modelo);
				
			}
		});
		btnNewButton.setBounds(10, 9, 107, 32);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(176, 224, 230));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Usuario amigo=new Usuario();
						
						amigo.setIdusuario(b.SacarID(users.get(list.getSelectedIndex()).getEmail()));
						System.out.println(amigo.getIdusuario());
						if(b.SonAmigos(log_in.u, amigo)) {
							JOptionPane.showMessageDialog(null, "Ya sois amigos");
						}else {
						b.AgregarAmigo(log_in.u,amigo);
						b.AgregarConversacion(log_in.u,amigo);
						JOptionPane.showMessageDialog(null, "Agregado!");
					}}
				});
				okButton.setActionCommand("OK");
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
