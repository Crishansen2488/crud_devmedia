package br.com.devmedia.crud.bo;

import java.util.HashMap;
import java.util.Map;

import br.com.devmedia.crud.dao.UsuarioDAO;
import br.com.devmedia.crud.dto.UsuarioDTO;
import br.com.devmedia.crud.exception.NegocioException;
import br.com.devmedia.crud.util.MensagemContantes;
import br.com.devmedia.crud.validator.LoginValidator;

/**
 * Classe responsável por gerenciar os métodos de negócio do usuário
 * 
 * @author Devmedia
 * 
 */
public class UsuarioBO {

	/**
	 * Método resposável por validar o usuário
	 * 
	 * @param request
	 * @return
	 * @throws NegocioException 
	 */
	public boolean validarUsuario(UsuarioDTO usuarioDTO) throws NegocioException {
		boolean isValido = true;
		try {
			// Valida campos obg
			Map<String, Object> valores = new HashMap<>();
			valores.put("Usuário", usuarioDTO.getUsuario());
			valores.put("Senha", usuarioDTO.getSenha());
			if (new LoginValidator().validar(valores)) {
				isValido = true;
			}
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			isValido = usuarioDAO.validarUsuario(usuarioDTO);
			if (!isValido) {
				throw new NegocioException(MensagemContantes.MSG_ERR_USUARIO_SENHA_INVALIDOS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;
	}

}
