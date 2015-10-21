package Wrappers;

import com.google.gson.annotations.SerializedName;

public class WrapperUsuario {
	
	@SerializedName("nick")
	private String nick;
	@SerializedName("password")
	private String password;
		
	public WrapperUsuario() {
	}
	
	public WrapperUsuario(String nick, String password) {
	
		this.nick = nick;
		this.password = password;		
	}
		
	public String imprimirBookData(){		
		return (this.nick+" "+this.password);
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

	
}
