package br.com.devmedia.crud.command;

import java.util.List;

import br.com.devmedia.crud.dao.PessoaDAO;
import br.com.devmedia.crud.dto.CidadeDTO;
import br.com.devmedia.crud.dto.EstadoDTO;
import br.com.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.com.devmedia.crud.exception.PersistenciaException;
import jakarta.servlet.http.HttpServletRequest;

public class MontagemCadastroCommand implements Command {
	
	private String proximo;
	
	private PessoaDAO cadastroDAO;
	
	public String execute(HttpServletRequest request) {
		cadastroDAO = new PessoaDAO();
		proximo = "cadastroPessoa.jsp";
		String getCidades = request.getParameter("getCidades");
		
		try {
			if (getCidades != null && !"".equals(getCidades)) {
				String id = request.getParameter("idEstado");
				int idEstado = Integer.parseInt(id);
				
				List<CidadeDTO> listaCidades = cadastroDAO.consultarCidadesPorEstado(idEstado);
				request.getSession().setAttribute("listaCidades", listaCidades);
			} else {
				List<EstadoDTO> listaEstados = cadastroDAO.listarEstados();
				List<PreferenciaMusicalDTO> listaPreferencias = cadastroDAO.listarPreferencias();
				request.getSession().setAttribute("listaEstados", listaEstados);
				request.getSession().setAttribute("listaPreferencias", listaPreferencias);
			}
		} catch (PersistenciaException e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proximo;
	}
	
}
