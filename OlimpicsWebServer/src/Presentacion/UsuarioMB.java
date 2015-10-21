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

import Wrappers.WrapperUsuario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



@ManagedBean
@javax.faces.bean.SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nick;
	private String password;
	private String rol;	
	private String link;
	
////////////////////////////////////////////////////////////
	
@SuppressWarnings("deprecation")
public String Login(){
		
		ClientRequest request;
		  
		try {

			request = new ClientRequest("http://localhost:8080/OlimpicsAppServer/rest/UsuarioService/login");		
			
			WrapperUsuario u = new WrapperUsuario(nick, password);
			
			String userJson = toJSONString(u);			
			request.body("application/json", userJson);		
			
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			
			ClientResponse<String> response = request.post(String.class);
			
			JsonParser parser = new JsonParser();
		    JsonArray jArray = parser.parse(response.getEntity()).getAsJsonArray();
			
			ArrayList<Boolean> lista = new ArrayList<Boolean>();

			    for(JsonElement obj : jArray)
			    	
			    {
			    	Boolean booleano = gson.fromJson(obj , Boolean.class);
			    	lista.add(booleano);
			        
			    }
			
			//System.out.println(lista.get(0).toString() + lista.get(1).toString());
			
			if (lista.get(0) == false) {	
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Credenciales incorrectas o Perfil deshabilitado"));
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
			}
			
			else if((lista.get(0) == true) && (lista.get(1) == false)){				
				
				request.clear();				
				request = new ClientRequest("http://localhost:8080/OlimpicsAppServer/rest/UsuarioService/rol/"+this.nick);		
				ClientResponse<String> respuesta = request.get(String.class);
				this.rol = respuesta.getEntity(String.class);
				
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", this.rol);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.nick);
				
				if("Usuario".equals(this.rol)){					
				FacesContext.getCurrentInstance().getExternalContext().redirect("SoyUser.xhtml");
				}
				else
				{					
					FacesContext.getCurrentInstance().getExternalContext().redirect("SoyAdminx.xhtml");
				}				
			}	
			
			else{
						
				request.clear();				
				request = new ClientRequest("http://localhost:8080/OlimpicsAppServer/rest/UsuarioService/rol/"+this.nick);		
				ClientResponse<String> respuesta = request.get(String.class);
				
				this.rol = respuesta.getEntity(String.class);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.nick);	
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rol", this.rol);
				
				if("Admin".equals(this.rol)){					
					FacesContext.getCurrentInstance().getExternalContext().redirect("SoyAdminx.xhtml");
				}
				
				request.clear();								
				FacesContext.getCurrentInstance().getExternalContext().redirect("Index.xhtml");
			
				}
			
			} 
			catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	return null;

	}


//////////////////////////////////////////////////////////////////////////////////////

	public String logOut() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().redirect("Index.xhtml");
			// return "Index";

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		// return "Index.xhtml?faces-redirect=true";
		return null;
	}

	@SuppressWarnings("deprecation")
	public String registroUsuario() {

		ClientRequest request;

		try {
			request = new ClientRequest(
					"http://localhost:8080/OlimpicsAppServer/rest/UsuarioService/usuario");
			WrapperUsuario usuario = new WrapperUsuario(this.nick, this.password);

			// transformo el usuario a ingresar en Json string
			String usuarioJSON = toJSONString(usuario);

			// Seteo el objeto usuario al body del request
			request.body("application/json", usuarioJSON);

			// se obtiene una respuesta por parte del webService
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() != 201) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Error al ingresar un nuevo usuario"));
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			// System.out.println(response.getEntity());
			// System.out.println(response.getStatus());

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Alta de Usuario con exito"));
			FacesContext.getCurrentInstance().getExternalContext()
					.invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("Index.xhtml");
			// return "Index";

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return null;
	}
	
	
	public String getPassword() {
		return password;
	}

	
	public String getNick() {
		return nick;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	public String toJSONString(Object object) { // Funcion que convierte de
												// objeto java a JSON
		GsonBuilder gsonBuilder = new GsonBuilder();
		// gsonBuilder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // ISO8601
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}

}
