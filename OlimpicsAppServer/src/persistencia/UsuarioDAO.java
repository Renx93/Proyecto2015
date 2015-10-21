package persistencia;


import javax.ejb.Stateless;

import dominio.Usuario;


@Stateless
public class UsuarioDAO implements IUsuarioDAO {

	@javax.persistence.PersistenceContext(unitName = "OlimpicsAppServer")
	private javax.persistence.EntityManager em;

	public boolean guardarUsuario(Usuario usuario) {

		try {
			em.persist(usuario);
			return true;

		} catch (Exception e) {

			e.printStackTrace();

		}

		return false;

	}
	
	
	public void modificarUsuario(Usuario u) {
		
		try {
			em.merge(u);			

		} catch (Exception e) {

			e.printStackTrace();

		}
		
	}


	public boolean existeUsuario(Usuario usuario) {

		Usuario u = em.find(Usuario.class, usuario.getNick()); // Si no se
																// encuentra a
																// la persona,
																// se retorna
																// NULL

		if ((u != null) && (u.getPassword().equals(usuario.getPassword())))
			return true;
		else
			return false;
	}

	
	public Usuario getUsuario(String nick) {

		
		
		return em.find(Usuario.class, nick);
	}


	
	public String darol(String nick) {
		Usuario u = em.find(Usuario.class, nick);

		if (u.getRoles().getId() == 1) {
			return "Admin";
		} else {
			return "Usuario";
		}

	}
	
	
}
