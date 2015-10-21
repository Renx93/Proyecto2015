package Presentacion;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import Wrappers.WrapperEvento;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



@ManagedBean
@javax.faces.bean.SessionScoped
public class EventoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombreE;
	private int idEvento;
	
////////////////////////////////////////////////////////////


	@SuppressWarnings("deprecation")
	public String altaEvento() {

		ClientRequest request;

		try {
			request = new ClientRequest(
					"http://localhost:8080/OlimpicsAppServer/rest/EventoService/evento");
			WrapperEvento evento = new WrapperEvento(this.nombreE, this.idEvento);

			// transformo el evento a ingresar en Json string
			String eventoJSON = toJSONString(evento);

			// Seteo el objeto evento al body del request
			request.body("application/json", eventoJSON);

			// se obtiene una respuesta por parte del webService
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() != 201) {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error al ingresar un nuevo evento"));
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			// System.out.println(response.getEntity());
			// System.out.println(response.getStatus());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alta de evento con exito"));
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().redirect("Index.xhtml");
			// return "Index";

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getNombreE() {
		return nombreE;
	}

	
	public int getIdEvento() {
		return idEvento;
	}

	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}

		
	public String toJSONString(Object object) { // Funcion que convierte de objeto java a JSON
		GsonBuilder gsonBuilder = new GsonBuilder();
		// gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // ISO8601
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}

}