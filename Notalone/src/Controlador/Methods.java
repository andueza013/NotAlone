package Controlador;

import org.apache.commons.codec.digest.DigestUtils;

public class Methods {

	public  String Encript(String contraseña) {
		String  ContraseñaMD5;
		 ContraseñaMD5=DigestUtils.md5Hex(contraseña);
		System.out.println(ContraseñaMD5);
		return ContraseñaMD5;
	}
	
	
	
	
}
