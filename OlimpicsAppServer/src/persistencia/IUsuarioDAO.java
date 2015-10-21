package persistencia;

import javax.ejb.Local;

import dominio.Usuario;

@Local
public interface IUsuarioDAO {

public boolean guardarUsuario(Usuario usuario);	
public boolean existeUsuario(Usuario usuario);
public Usuario getUsuario(String nick);
public String darol(String nick);
public void modificarUsuario(Usuario u);


}



 