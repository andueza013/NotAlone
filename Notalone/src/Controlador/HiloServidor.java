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

import javax.imageio.ImageIO;

public class HiloServidor extends Thread {
	InputStream inputStream=null;
			Socket socket = null;
			BufferedImage image;
			static String publi="";
			

	public HiloServidor(Socket s) throws IOException {// CONSTRUCTOR
		socket = s;
		inputStream=s.getInputStream();
		// se crean flujos de entrada y salida
		
	}

	public void run() {// tarea a realizar con el cliente
		
		 byte[] imageAr = new byte[62100];
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
				e1.printStackTrace();
			}

         System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
         try {
			ImageIO.write(image, "jpg", new File("C:\\UsuariosNotAlone\\imagenes\\"+publi+".png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

         try {
			inputStream.close();
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
