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
import br.com.devmedia.crud.exception.NegocioException;
import br.com.devmedia.crud.util.MensagemContantes;
import br.com.devmedia.crud.validator.CamposObrigatoriosValidator;

public class CadastroPessoaCommand implements Command {
	
	private String proximo;
	private PessoaBO pessoaBO;
	boolean valido = false;
	
	public String execute(HttpServletRequest request) {	
		pessoaBO = new PessoaBO();
		proximo = "consultas.jsp";
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String nasc = request.getParameter("nasc");
		String sexo = request.getParameter("sexo");
		String comentario = request.getParameter("comentario");
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
			
			PessoaDTO pessoaDTO = new PessoaDTO();
			pessoaDTO.setNome(nome);
			pessoaDTO.setCpf(cpf);
			pessoaDTO.setNasc(nasc);
			pessoaDTO.setComentario(comentario);
			pessoaDTO.setSexo(sexo != null ? sexo.charAt(0) : ' ');
			pessoaDTO.setPreferencias(listaPreferenciasMusicais);
			
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			enderecoDTO.setLogradouro(logradouro);
			
			CidadeDTO cidadeDTO = new CidadeDTO();
			cidadeDTO.setIdCidade(idCidade != null ? Integer.parseInt(idCidade) : null);
			
			EstadoDTO estadoDTO = new EstadoDTO();
			estadoDTO.setIdEstado(idEstado != null ? Integer.parseInt(idEstado) : null);
			
			cidadeDTO.setEstado(estadoDTO);
			enderecoDTO.setCidade(cidadeDTO);
			pessoaDTO.setEndereco(enderecoDTO);
			
			if(new CamposObrigatoriosValidator().camposObrigatoriosValidator(request)) {
				valido = true;
			}
			if(valido) {
				if (pessoaBO.validarPessoa(pessoaDTO)) {
					pessoaBO.cadastrarPessoa(pessoaDTO);
					proximo = "main?acao=consultasPessoa";
					request.setAttribute("msgSucessoCadastro", MensagemContantes.MSG_SUCESSO_CADASTRO_PESSOA);
					
				}else {
					request.setAttribute("msgErro", MensagemContantes.MSG_ERR_PESSOA_DADOS_INVALIDOS);
				}
			}
			
		} catch (NegocioException e) {
			request.setAttribute("msgErro", e.getMessage());
			proximo = "cadastroPessoa.jsp";
		}
		
		return proximo;
	}
	
}
