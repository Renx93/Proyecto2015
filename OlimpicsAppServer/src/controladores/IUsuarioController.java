package controladores;

import javax.ejb.Local;
import dominio.Usuario;

@Local
public interface IUsuarioController {
	public boolean guardarUsuario(String nick, String Password);
	public boolean existeUsuario(String nick, String password);	
	public String darRol(String nick);	
	public Usuario buscarUsuario(String nick);	
	public void modificarUsuario(Usuario u);

}
