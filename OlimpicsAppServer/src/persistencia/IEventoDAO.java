package persistencia;

import javax.ejb.Local;

import dominio.Evento;

@Local
public interface IEventoDAO {

public boolean guardarEvento(Evento evento);	
public boolean existeEvento(Evento evento);
public Evento getEvento(int idEvento);
public void modificarEvento(Evento ev);


}