package Controlador;

import org.apache.commons.codec.digest.DigestUtils;

public class Methods {

	public  String Encript(String contrase�a) {
		String  Contrase�aMD5;
		 Contrase�aMD5=DigestUtils.md5Hex(contrase�a);
		System.out.println(Contrase�aMD5);
		return Contrase�aMD5;
	}
	
	
	
	
}
