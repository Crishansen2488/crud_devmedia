package br.com.devmedia.crud.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.devmedia.crud.command.CadastroPessoaCommand;
import br.com.devmedia.crud.command.Command;
import br.com.devmedia.crud.command.LoginCommand;
import br.com.devmedia.crud.command.MontagemCadastroCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Command> comandos = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		comandos.put("login", new LoginCommand());
		comandos.put("montagemCadastro", new MontagemCadastroCommand());
		comandos.put("cadastroPessoa", new CadastroPessoaCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxima = "";
		try {
			Command comando = verificarComand(acao);
			proxima = comando.execute(request);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getRequestDispatcher(proxima).forward(request, response);
	}
	
	private Command verificarComand(String acao) {
		Command comando = null;
		for (String key : comandos.keySet()) {
			if (key.equalsIgnoreCase(acao)) {
				comando = comandos.get(key);
			}
		}
		return comando;
	}

}
