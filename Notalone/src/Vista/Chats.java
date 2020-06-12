package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.BaseDeDatos;
import Modelo.Mensaje;
import Modelo.Usuario;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class Chats extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JList list;
	BaseDeDatos b=new BaseDeDatos();
	Usuario u2=new Usuario();
	static String correoUser2="";

	/**
	 * Launch the application.
	 */
	public static void ini(String s) {
		try {
			Chats dialog = new Chats();
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
			correoUser2=s;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Chats() {
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		b.conexion();
		setBounds(100, 100, 665, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 297, 515, 32);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("send");
		btnNewButton.setBounds(556, 302, 85, 21);
		contentPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.SendMessage(log_in.u, u2, textField.getText());
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 515, 261);
		contentPanel.add(scrollPane);
		{
			
			list = new JList();
			DefaultListModel listModel = new DefaultListModel();
			
			list.setModel(listModel);
			
			scrollPane.setViewportView(list);
		}
		
		
		scrollPane.setViewportView(list);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Exit");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListaChats.okButton.setEnabled(true);
						setVisible(false);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		Thread hilo = new Thread(runnable);
	    hilo.start();
	}
	Runnable runnable = new Runnable() {

        @Override
        public void run() {
            while (true) {                    
                try {
                    Thread.sleep(800);
                    DefaultListModel listModel = new DefaultListModel();
        			//Recorrer el contenido del ArrayList
        			for(int i=0;i<ListaChats.chats.size();i++) {
        				if(ListaChats.chats.get(i).getNombre().equals(correoUser2)) {
        					
        					u2.setIdusuario(ListaChats.chats.get(i).getIdusuario());
        				}
        			}
        			ArrayList<Mensaje>mensajes=b.Mensajes(log_in.u.getIdusuario(),u2.getIdusuario());
        			for(int i=0; i<mensajes.size();i++) {
        			    //Añadir cada elemento del ArrayList en el modelo de la lista
        			    listModel.add(i, mensajes.get(i).getContenido());
        			}
        			//Asociar el modelo de lista al JList
        			list.setModel(listModel);
        			
        			
                        
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
            
        }
        
    };
}
