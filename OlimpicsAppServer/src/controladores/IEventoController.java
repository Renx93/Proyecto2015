package controladores;

import javax.ejb.Local;
import dominio.Evento;

@Local
public interface IEventoController {
	
	public boolean guardarEvento(String nombreE, int idEvento);
	public boolean existeEvento(String nombreE, int idEvento);	
	public Evento buscarEvento(int idEvento);	
	public void modificarEvento(Evento ev);

}
