package dominio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L; // Mapping JPA

	@Id
	@Column(name = "Nick", nullable = false)
	private String nick;

	@Column(name = "Password", nullable = false)
	// @NotNull
	private String password;
	
	@ManyToOne
	@JoinColumn(name="Roles")
	private Roles roles;
	
	

	public Usuario() {}

	public Usuario(String nick, String password) {
		this.nick = nick;
		this.password = password;		
	}


	public Usuario(Usuario p) {
		this.nick = p.getNick();
		this.password = p.getPassword();		
	}	

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

		
}
