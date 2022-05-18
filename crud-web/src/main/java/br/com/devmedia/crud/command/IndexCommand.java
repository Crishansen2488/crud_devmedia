package br.com.devmedia.crud.command;

import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {	
		proximo = "index.jsp";
		
		return proximo;
		
	}
	
}
