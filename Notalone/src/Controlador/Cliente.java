package Controlador;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import Modelo.Post;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
 
public class Cliente
{
	static String ruta;
	static Post p=new Post();
    public static void main(String[] args)
    {
    	
        int port=2050;
        try
        {
            Socket clientSocket = new Socket("192.168.56.1",port);
            
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream perSal = new ObjectOutputStream( clientSocket.getOutputStream());
            
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            	
            	
            	BufferedImage image = ImageIO.read(new File(p.getImagen()));
                ImageIO.write(image, "jpg", byteArrayOutputStream);
     
                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                System.out.println(byteArrayOutputStream.size());
                outputStream.write(byteArrayOutputStream.toByteArray());
                perSal.writeObject(p);
                Thread.sleep(10);
     
                outputStream.close();
                clientSocket.close();    
            
            
        }
        catch (UnknownHostException e){
            System.out.println("pene");
        }
        catch (IOException e) {
            System.out.println("pene 2");
        } catch (InterruptedException e) {
          System.out.println("pene 3");
        }
    }
    public  void reciboPost(Post p) {
    	this.p=p;
    	
    }
}