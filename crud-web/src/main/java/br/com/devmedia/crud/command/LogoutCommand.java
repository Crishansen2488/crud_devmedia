package br.com.devmedia.crud.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {	
		proximo = "login.jsp";
		
		return proximo;
		
	}
	
}
