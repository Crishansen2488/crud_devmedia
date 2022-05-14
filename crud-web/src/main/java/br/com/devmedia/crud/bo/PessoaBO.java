package br.com.devmedia.crud.bo;

import java.util.HashMap;
import java.util.Map;

import br.com.devmedia.crud.dto.PessoaDTO;
import br.com.devmedia.crud.exception.NegocioException;
import br.com.devmedia.crud.validator.CPFValidator;
import br.com.devmedia.crud.validator.DataValidator;

/**
 * Classe responsável por gerenciar os métodos de negócio da pessoa
 * 
 * @author Devmedia
 * 
 */
public class PessoaBO {

	/**
	 * Método resposável por validar a pessoa
	 * 
	 * @param request
	 * @return
	 * @throws NegocioException 
	 */
	public boolean validarPessoa(PessoaDTO pessoaDTO) throws NegocioException {
		boolean isValido = true;
		try {
			// Valida campos obg
			Map<String, Object> valores = new HashMap<>();
			valores.put("CPF", pessoaDTO.getCpf());
			if (new CPFValidator().validar(valores)) {
				isValido = true;
			}
			//comentando
			valores = new HashMap<>();
			valores.put("Data Nascimento", pessoaDTO.getDtNasc());
			if (new DataValidator().validar(valores)) {
				isValido = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e.getMessage());
		}

		return isValido;
	}

}
