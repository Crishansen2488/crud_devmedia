package br.com.devmedia.crud.exception;

/**
 * Classe respons�vel por gerenciar as exce��es da camada de neg�cio
 * 
 * @author Devmedia
 * 
 */
public class NegocioException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegocioException(Exception e) {
		super(e.getMessage());
	}
	
	public NegocioException(String msg) {
		super(msg);
	}

}
