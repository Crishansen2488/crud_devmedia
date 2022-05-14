package br.com.devmedia.crud.command;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Interface para o padrão Command
 * 
 * @author Devmedia
 * 
 */
public interface Command {

	/**
	 * Método de execução do comando
	 * 
	 * @param request
	 * @return
	 */
	public String execute(HttpServletRequest request);
}
