package br.com.devmedia.crud.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.devmedia.crud.dao.PessoaDAO;
import br.com.devmedia.crud.dto.PessoaDTO;
import br.com.devmedia.crud.exception.NegocioException;
import br.com.devmedia.crud.exception.PersistenciaException;
import br.com.devmedia.crud.validator.CPFValidator;
import br.com.devmedia.crud.validator.DataValidator;

public class PessoaBO {
	
	private PessoaDAO pessoaDAO;
	
	public PessoaBO() {
		pessoaDAO = new PessoaDAO();
	}

	public boolean validarPessoa(PessoaDTO pessoaDTO) throws NegocioException {
		boolean isValido = true;
		try {
			// Valida campos obg
			Map<String, Object> valores = new HashMap<>();
			valores.put("'CPF'", pessoaDTO.getCpf());
			if (new CPFValidator().validar(valores)) {
				isValido = true;
			}
			valores = new HashMap<>();
			valores.put("'Data de Nascimento'", pessoaDTO.getNasc());
			if (new DataValidator().validar(valores)) {
				isValido = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}

		return isValido;
	}
	
	public void cadastrarPessoa(PessoaDTO pessoaDTO) throws NegocioException {
		try {
			pessoaDAO.cadastrarPessoa(pessoaDTO);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}
	}
	
	public List<PessoaDTO> listarPessoas() throws NegocioException{
		List<PessoaDTO> lista = null;
		try {
			lista = pessoaDAO.listarPessoas();
		} catch (PersistenciaException e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}
		return lista;
	}

}
