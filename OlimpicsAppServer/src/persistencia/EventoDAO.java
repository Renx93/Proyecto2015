package persistencia;

import javax.ejb.Stateless;

import dominio.Evento;


@Stateless
public class EventoDAO implements IEventoDAO {

	@javax.persistence.PersistenceContext(unitName = "OlimpicsAppServer")
	private javax.persistence.EntityManager em;

	public boolean guardarEvento(Evento evento) {

		try {
			em.persist(evento);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public void modificarEvento(Evento ev) {
		
		try {
			em.merge(ev);		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}


	public boolean existeEvento(Evento evento) {

		Evento ev = em.find(Evento.class, evento.getNombreE()); 
		if ((ev != null) && (ev.getNombreE().equals(evento.getNombreE())))
			return true;
		else
			return false;
	}

	
	public Evento getEvento(int idEvento) {		
		return em.find(Evento.class, idEvento);
	}
	
}
