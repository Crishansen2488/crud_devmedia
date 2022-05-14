package br.com.devmedia.crud.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.devmedia.crud.bo.PessoaBO;
import br.com.devmedia.crud.dto.CidadeDTO;
import br.com.devmedia.crud.dto.EnderecoDTO;
import br.com.devmedia.crud.dto.EstadoDTO;
import br.com.devmedia.crud.dto.PessoaDTO;
import br.com.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.com.devmedia.crud.validator.CamposObrigatoriosValidator;

public class CadastroPessoaCommand implements Command {
	
	private String proximo;
	
	public String execute(HttpServletRequest request) {	
		proximo = "cadastroPessoa.jsp";
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String dtNasc = request.getParameter("dtNasc");
		String sexo = request.getParameter("sexo");
		String miniBio = request.getParameter("miniBio");
		String idEstado = request.getParameter("estado");
		String idCidade = request.getParameter("cidade");
		String logradouro = request.getParameter("logradouro");
		
		String[] preferenciasMusicais = request.getParameterValues("gostos");
		List<PreferenciaMusicalDTO> listaPreferenciasMusicais = new ArrayList<>();
		if (preferenciasMusicais != null) {
			for (String peferenciaMusical : preferenciasMusicais) {
				PreferenciaMusicalDTO preferenciaMusical = new PreferenciaMusicalDTO();
				preferenciaMusical.setIdPreferencia(Integer.parseInt(peferenciaMusical));
				
				listaPreferenciasMusicais.add(preferenciaMusical);
			}
		}
		try {
			boolean valido = true;
			PessoaDTO pessoaDTO = new PessoaDTO();
			pessoaDTO.setNome(nome);
			pessoaDTO.setCpf(cpf);
			pessoaDTO.setDtNasc(dtNasc);
			pessoaDTO.setMiniBio(miniBio);
			pessoaDTO.setSexo(sexo != null ? sexo.charAt(0) : ' ');
			pessoaDTO.setPreferencias(listaPreferenciasMusicais);
			
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			enderecoDTO.setLogradouro(logradouro);
			
			CidadeDTO cidadeDTO = new CidadeDTO();
			cidadeDTO.setIdCidade(idCidade != null ? Integer.parseInt(idCidade) : null);
			
			EstadoDTO ufDTO = new EstadoDTO();
			ufDTO.setIdUF(idEstado != null ? Integer.parseInt(idEstado) : null);
			
			cidadeDTO.setEstado(ufDTO);
			enderecoDTO.setCidade(cidadeDTO);
			pessoaDTO.setEndereco(enderecoDTO);
			
			valido = new CamposObrigatoriosValidator().camposObrigatoriosValidator(request);
			
			valido = new PessoaBO().validarPessoa(pessoaDTO);
	
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proximo;
	}
	
}
