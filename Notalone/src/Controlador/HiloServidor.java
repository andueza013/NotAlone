package Controlador;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;
import java.net.*;
import Modelo.Post;

import javax.imageio.ImageIO;

import Modelo.Post;

public class HiloServidor extends Thread {
	InputStream inputStream=null;
			Socket socket = null;
			BufferedImage image;
			static String publi="";
			BaseDeDatos b=new BaseDeDatos();
			ObjectInputStream inObjeto;
			Post dato;

	public HiloServidor(Socket s) throws IOException {// CONSTRUCTOR
		socket = s;
		s.setSoTimeout(0);
		inputStream=s.getInputStream();
		inObjeto= new ObjectInputStream(s.getInputStream());
		dato=null;
		// se crean flujos de entrada y salida
		
	}

	public void run() {// tarea a realizar con el cliente
		b.conexion();
		int n=b.NumPublicaciones()+1;
		publi="publi"+n;
		 byte[] imageAr = new byte[100000];
         try {
			inputStream.read(imageAr);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

             
			try {
				image = ImageIO.read(new ByteArrayInputStream(imageAr));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				
			}

         System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
         try {
        	  
			ImageIO.write(image, "png", new File("C:\\UsuariosNotAlone\\imagenes\\"+publi+".png"));
			 
			 
				 dato=(Post)(inObjeto.readObject());
				 dato.setImagen("\\\\LAPTOP-36SH2NU5\\imagenes\\"+publi+".png");
					b.InsertarPost(dato);
					System.out.println("INSERTADO");
			 
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         try {
			inputStream.close();
			inObjeto.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
         
		
		try {
			 socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}
	
	public void nombre(String s) {
		publi=s;
	}

}
// ..
