package Controlador;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Mensaje;
import Modelo.Post;
import Modelo.Usuario;

public class BaseDeDatos {

	java.sql.Connection conexion = null;

	public void conexion() {

		try {
			Class.forName("org.postgresql.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/NotAlone", "postgres", "postgres");
		} catch (ClassNotFoundException cnfe) {
			cnfe.fillInStackTrace();
		} catch (SQLException sqle) {
			sqle.fillInStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertarRol() {

		String sentenciaSql = "INSERT INTO rol  VALUES (?, ?,?,?)";
		PreparedStatement sentencia = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, 3);
			sentencia.setString(2, "Prueba");
			sentencia.setString(3, "Descrip prueba");
			sentencia.setInt(4, 1);
			sentencia.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
	}

	public boolean UserExist(String email) {
		boolean userexist = false;
		String sentenciaSql = "SELECT email FROM usuario";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				if (resultado.getString(1).equals(email)) {
					userexist = true;
				}

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
		return userexist;
	}

	public int SacarID(String email) {
		int idusuario = 0;
		String sentenciaSql = "SELECT idusuario FROM usuario where email=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, email);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				idusuario = resultado.getInt(1);

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
		return idusuario;
	}

	public void insertarUser(Usuario u) {
		if (UserExist(u.getEmail())) {
		} else {
			String sentenciaSql = "INSERT INTO usuario (idrol,nombre,telefono,email,password_hash,condicion)  VALUES (?,?,?,?,?,?)";
			PreparedStatement sentencia = null;

			try {
				sentencia = conexion.prepareStatement(sentenciaSql);
				sentencia.setInt(1, 2);
				sentencia.setString(2, u.getNombre());
				sentencia.setString(3, u.getTelefono());
				sentencia.setString(4, u.getEmail());
				sentencia.setString(5, u.getPassword_encripted());
				sentencia.setString(6, "true");

				sentencia.executeUpdate();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				if (sentencia != null)
					try {
						sentencia.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
			}
		}
	}

	public boolean InicioSesion(Usuario u) {
		boolean inicioCorrecto = false;
		String sentenciaSql = "SELECT password_hash FROM usuario WHERE email=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {

			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, u.getEmail());
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				if (resultado.getString(1).equals(u.getPassword_encripted())) {
					inicioCorrecto = true;
				}

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

		return inicioCorrecto;
	}

	public String Busqueda(String s) {

		String sentenciaSql = "SELECT nombre FROM usuario WHERE email=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		String persona = "";

		try {

			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, s);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				Usuario u = new Usuario();
				u.setNombre(resultado.getString(1));
				persona = u.getNombre();
				System.out.println(u.getNombre());

			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

		return persona;
	}

	public void AgregarAmigo(Usuario u, Usuario u2) {
		String sentenciaSql = "INSERT INTO amistad  VALUES (?, ?)";
		PreparedStatement sentencia = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, u.getIdusuario());
			sentencia.setInt(2, u2.getIdusuario());
			sentencia.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
	}

	public void AgregarConversacion(Usuario u, Usuario u2) {
		String sentenciaSql = "INSERT INTO conversacion  VALUES (?, ?)";
		PreparedStatement sentencia = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, u.getIdusuario());
			sentencia.setInt(2, u2.getIdusuario());
			sentencia.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
	}

	public boolean SonAmigos(Usuario u, Usuario u2) {
		boolean IsFriend = false;

		String sentenciaSql = "SELECT * FROM amistad where idusuarioa=? and idusuariob=? or idusuariob=? and idusuarioa=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, u.getIdusuario());
			sentencia.setInt(2, u2.getIdusuario());
			sentencia.setInt(3, u.getIdusuario());
			sentencia.setInt(4, u2.getIdusuario());
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				IsFriend = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}

		return IsFriend;
	}

	@SuppressWarnings("null")
	public ArrayList<Usuario> chats(int id) {
		ArrayList<Usuario> chats = new ArrayList<Usuario>();
		String sentenciaSql = "select nombre,idusuario from usuario inner join conversacion on idusuario=idusuariob or idusuario=idusuarioa where idusuarioa=? or idusuariob=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, id);
			sentencia.setInt(2, id);
			resultado = sentencia.executeQuery();
			int puntero = 0;
			while (resultado.next()) {
				Usuario u = new Usuario();
				if (resultado.getInt(2) == id) {

					

				} else {

					u.setIdusuario(resultado.getInt(2));
					u.setNombre(resultado.getString(1));
					chats.add(u);
				}
				
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
		for(int i =0;i<chats.size();i++) {
		System.out.println(chats.get(i).getIdusuario());}
		return chats;

	}
	public Usuario  InfoPersonal(Usuario u) {
		Usuario u2=new Usuario();
		u2=u;
	String sentenciaSql = "SELECT * FROM usuario where idusuario=?";
	PreparedStatement sentencia = null;
	ResultSet resultado = null;
	 
	try {
	  sentencia = conexion.prepareStatement(sentenciaSql);
	  sentencia.setInt(1, u.getIdusuario());
	  resultado = sentencia.executeQuery();
	  while (resultado.next()) {
	    u2.setNombre(resultado.getString(3));
	    u2.setTelefono(resultado.getString(4));
	    u2.setEmail(resultado.getString(5));
	  }
	} catch (SQLException sqle) {
	  sqle.printStackTrace();
	} finally {
	  if (sentencia != null)
	    try {
	      sentencia.close();
	      resultado.close();
	    } catch (SQLException sqle) {
	      sqle.printStackTrace();
	    }
	}
	return u2;
	}
	
	public ArrayList<Mensaje> Mensajes(int id,int id2) {
		ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
		String sentenciaSql = "select * from mensaje where idusuario_emisor=? and idusuario_receptor=? or idusuario_emisor=? and idusuario_receptor=?";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, id);
			sentencia.setInt(2, id2);
			sentencia.setInt(3, id2);
			sentencia.setInt(4, id);
			resultado = sentencia.executeQuery();
			int puntero = 0;
			while (resultado.next()) {
				Mensaje m = new Mensaje();
				
				m.setidmensaje(resultado.getInt(1));
				m.setIdusuarioemisor(resultado.getInt(2));
				m.setIdusuarioreceptor(resultado.getInt(3));
				m.setContenido(resultado.getString(4));
				mensajes.add(m);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
		for(int i =0;i<mensajes.size();i++) {
		System.out.println(mensajes.get(i).getContenido());}
		return mensajes;

	}
	public void SendMessage(Usuario u,Usuario u2,String contenido) {
	
			String sentenciaSql = "INSERT INTO mensaje (idusuario_emisor,idusuario_receptor,contenido)  VALUES (?,?,?)";
			PreparedStatement sentencia = null;

			try {
				sentencia = conexion.prepareStatement(sentenciaSql);
				sentencia.setInt(1,u.getIdusuario());
				sentencia.setInt(2, u2.getIdusuario());
				sentencia.setString(3,contenido);
				

				sentencia.executeUpdate();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				if (sentencia != null)
					try {
						sentencia.close();
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
			}
		}
	public ArrayList<Post> posts(int id) {
		ArrayList<Post> posts = new ArrayList<Post>();
		String sentenciaSql = "select usuario.nombre, titulo, contenido,imagen from post inner join usuario on usuario.idusuario=post.idusuario"
				+ "  where post.idusuario in (select idusuarioa  from amistad where idusuarioa=? or idusuariob=?)";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, id);
			sentencia.setInt(2, id);
			resultado = sentencia.executeQuery();
			int puntero = 0;
			while (resultado.next()) {
				Post p=new Post();
				

					p.setTitulo(resultado.getString(2));
					p.setContenido(resultado.getString(3));
					p.setImagen(resultado.getString(4));

				
				posts.add(p);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
					resultado.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
	
		return posts;

	}
	public void modify(Usuario u) {
		String sentenciaSql = "UPDATE usuario SET nombre = ?, telefono = ? " + "WHERE idusuario = ?";
		PreparedStatement sentencia = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1,u.getNombre());
			sentencia.setString(2, u.getTelefono());
			sentencia.setInt(3, u.getIdusuario());
			sentencia.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
}
}
	}
	public int NumPublicaciones() {
		int publicaciones=0;
		String sentenciaSql = "SELECT count(*) FROM post";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		 
		try {
		  sentencia = conexion.prepareStatement(sentenciaSql);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
		   publicaciones=resultado.getInt(1);
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		      resultado.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		return publicaciones;
	}
	public void InsertarPost( Post p) {
		String sentenciaSql = "INSERT INTO post  VALUES (?, ?,?,?)";
		PreparedStatement sentencia = null;

		try {
			sentencia = conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, p.getIdusuario());
			sentencia.setString(2, p.getTitulo());
			sentencia.setString(3, p.getContenido());
			sentencia.setString(4, p.getImagen());
			sentencia.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (sentencia != null)
				try {
					sentencia.close();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
		}
	}
	
	
	
}
