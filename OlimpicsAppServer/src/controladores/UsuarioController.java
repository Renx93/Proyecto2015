package controladores;


import javax.ejb.EJB;


import javax.ejb.Stateless;


import dominio.Roles;
import dominio.Usuario;
import persistencia.IUsuarioDAO;

@Stateless
public class UsuarioController implements IUsuarioController {

	@EJB
	private IUsuarioDAO UsuarioDAO;
	
	public boolean guardarUsuario(String nick, String Password) {
		try{
				
			
			Usuario u = new Usuario(nick,Password);
						
			if (u.getNick().equals("Admin")){
				System.out.println("Soy el admin");
				Roles r = new Roles(1,"Admin");
				u.setRoles(r);
				
			}else{
				
				System.out.println("Soy el user");
				Roles r = new Roles(2,"User");
				u.setRoles(r);
				
			}
			
			
			return UsuarioDAO.guardarUsuario(u);		
		
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean existeUsuario(String nick, String password) {
		
		try{
			return UsuarioDAO.existeUsuario(new Usuario(nick,password));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	

	public String darRol(String nick){
		try{
			
				return UsuarioDAO.darol(nick);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	



	public Usuario buscarUsuario(String nick) {
		
		try{
		return UsuarioDAO.getUsuario(nick);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}


	public void modificarUsuario(Usuario u) {
		try{
	
			UsuarioDAO.modificarUsuario(u);
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}
	

		
}
