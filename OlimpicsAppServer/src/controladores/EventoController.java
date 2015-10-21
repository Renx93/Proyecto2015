package controladores;


import javax.ejb.EJB;


import javax.ejb.Stateless;
import dominio.Evento;
import persistencia.IEventoDAO;

@Stateless
public class EventoController implements IEventoController {

	@EJB
	private IEventoDAO EventoDAO;
	
	public boolean guardarEvento(String nombreE, int idEvento) {
		
		try{							
			Evento ev = new Evento(nombreE,idEvento);			
			return EventoDAO.guardarEvento(ev);		
		
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public boolean existeEvento(String nombreE, int idEvento) {
		
		try{
			return EventoDAO.existeEvento(new Evento(nombreE,idEvento));
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	

	
	public Evento buscarEvento(int idEvento) {
		
		try{
		return EventoDAO.getEvento(idEvento);
		}catch(Exception e){
			e.printStackTrace();			
		}
		return null;
	}


	public void modificarEvento(Evento ev) {
		try{
	
			EventoDAO.modificarEvento(ev);
		}catch(Exception e){
			e.printStackTrace();			
		}		
	}
	

		
}