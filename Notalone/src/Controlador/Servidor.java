package Controlador;
import javax.imageio.ImageIO;



import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Servidor
{
	
	static int n=0;
    public static void main(String[] args) throws IOException
    {
    	BaseDeDatos b=new BaseDeDatos();
    	ServerSocket servidor;		
		servidor = new ServerSocket(2050);
		
		System.out.println("Servidor iniciado...");
		InetAddress address = InetAddress.getLocalHost();
        System.out.println("IP Local :"+address.getHostAddress());
		b.conexion();
		while (true) {	
			Socket cliente = new Socket();
			
			cliente=servidor.accept();//esperando cliente	
			
			HiloServidor hilo = new HiloServidor(cliente);
			
			
			hilo.start();
			
		}
		}
    }
      
        
            

            

            

            

           
       
    
