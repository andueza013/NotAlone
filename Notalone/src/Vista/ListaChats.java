package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.BaseDeDatos;
import Modelo.Mensaje;
import Modelo.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public  class ListaChats extends JFrame {

	/**
	 * 
	 */
	
	private final JPanel contentPanel = new JPanel();
	BaseDeDatos b=new BaseDeDatos();
	log_in l=new log_in();
	static JList list;
	static ArrayList<Usuario>chats=new ArrayList<Usuario>();
	static JButton okButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaChats dialog = new ListaChats();
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
	public ListaChats() {
		setResizable(false);
		b.conexion();
		setLocationRelativeTo(null);
		setBounds(100, 100, 775, 439);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 10, 741, 351);
			contentPanel.add(scrollPane);
			{
				chats=b.chats(log_in.u.getIdusuario());
				list = new JList();
				DefaultListModel listModel = new DefaultListModel();
				//Recorrer el contenido del ArrayList
				
				for(int i=0; i<chats.size();i++) {
				    //Añadir cada elemento del ArrayList en el modelo de la lista
				    listModel.add(i, chats.get(i).getNombre());
				}
				//Asociar el modelo de lista al JList
				list.setModel(listModel);
				
				scrollPane.setViewportView(list);
			}
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Chats c=new Chats();
						c.ini((String)list.getSelectedValue());
						okButton.setEnabled(false);
						
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
