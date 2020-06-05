package Controlador;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
 
public class Cliente
{
    public static void main(String[] args)
    {
        int port=2050;
        try
        {
            Socket clientSocket = new Socket("192.168.1.160",port);
 
            OutputStream outputStream = clientSocket.getOutputStream();
 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
            	BufferedImage image = ImageIO.read(new File(chooser.getSelectedFile().getAbsolutePath()));
                ImageIO.write(image, "jpg", byteArrayOutputStream);
     
                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                System.out.println(byteArrayOutputStream.size());
                outputStream.write(byteArrayOutputStream.toByteArray());
                Thread.sleep(2000);
     
                outputStream.close();
                clientSocket.close();    
            }
            
        }
        catch (UnknownHostException e){
            System.out.println(e);
        }
        catch (IOException e) {
            System.out.println(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}