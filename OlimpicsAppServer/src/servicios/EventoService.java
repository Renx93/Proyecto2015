package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import controladores.IEventoController;
import dominio.Evento;
import persistencia.EventoDAO;


@Path("/EventoService")

// para mapearlo en la url
public class EventoService extends Application {

	@EJB
	private IEventoController iec;

	// localhost:8080/OlimpicsAppServer/rest/EventoService/status/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/status")
	// para mapearlo en la url
	public Response getStatus() {
		// System.out.println("WENOOOOOOOOOOOOOOOOOOO");
		return Response
				.ok("{\"status\":\"El servicio de los eventos esta funcionando...\"}")
				.build();

	}
	
	
	
	
	// localhost:8080/OlimpicsAppServer/rest/EventoService/evento
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/evento")
	public Response guardarEvento(String datos) {
		System.out.println("payload - " + datos);
		boolean creado = false;
		// Create a new Gson object that could parse all passed in elements
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();

		// Obtengo el objeto book pareando los datos que me llegan (JSON string)
		Evento evento = gson.fromJson(datos, Evento.class);

		String returnCode = "200";

		// Guardo el libro
		try {

			// IUsuarioController iuc = Fabrica.getInstance();

			creado = this.iec.guardarEvento(evento.getNombreE(),evento.getIdEvento());

			returnCode = "{"
					+ "\"href\":\"http://localhost:8080/OlimpicsWebServer/rest/EventService/evento/"
					+ evento.getIdEvento() + "\","
					+ "\"message\":\"New book successfully created.\"" + "}";

		} catch (Exception err) {
			err.printStackTrace();
			returnCode = "{\"status\":\"500\","
					+ "\"message\":\"Resource not created.\""
					+ "\"developerMessage\":\"" + err.getMessage() + "\"" + "}";
			return Response.status(500).entity(returnCode).build();

		}
		return Response.status(201).entity(creado).build();
	}
	
	
	
	public String toJSONString(Object object) { // Funcion que convierte de
												// objeto java a JSON
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
	
	
}
