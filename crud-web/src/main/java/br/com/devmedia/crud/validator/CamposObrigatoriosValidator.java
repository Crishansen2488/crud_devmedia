package br.com.devmedia.crud.validator;

import javax.servlet.http.HttpServletRequest;

import br.com.devmedia.crud.exception.NegocioException;
import br.com.devmedia.crud.util.MensagemContantes;

public class CamposObrigatoriosValidator {

	public boolean camposObrigatoriosValidator(HttpServletRequest request) throws NegocioException {
		
		boolean valida = true;
		String msgErro = "";
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String nasc = request.getParameter("nasc");
		String sexo = request.getParameter("sexo");
		String idEstado = request.getParameter("estado");
		String idCidade = request.getParameter("cidade");
		String logradouro = request.getParameter("logradouro");
		
		if (nome==null || nome.equals("")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'Nome'").concat("<br/>");
		}
		if (cpf==null || cpf.equals("")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'CPF'").concat("<br/>");
		}
		if (nasc==null || nasc.equals("")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'Data de Nascimento'").concat("<br/>");
		}
		if (sexo==null || sexo.equals("")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'Sexo'").concat("<br/>");
		}
		if (idEstado==null || idEstado.equals("0")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'Estado'").concat("<br/>");
		}
		if (idCidade==null || idCidade.equals("0")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'Cidade'").concat("<br/>");
		}
		if (logradouro==null || logradouro.equals("")) {
			valida = false;
			msgErro += MensagemContantes.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "'Logradouro'").concat("<br/>");
		}
		if (!valida) {
			request.setAttribute("msgErro", msgErro);
			throw new NegocioException(msgErro);
		}
		
		return valida;
		
	}
}
