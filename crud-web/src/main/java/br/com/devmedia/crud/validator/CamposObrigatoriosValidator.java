package br.com.devmedia.crud.validator;

import br.com.devmedia.crud.util.MensagemContantes;
import jakarta.servlet.http.HttpServletRequest;

public class CamposObrigatoriosValidator {

	public boolean camposObrigatoriosValidator(HttpServletRequest request) {
		
		boolean valida = true;
		String msgErro = "";
		
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String dtNasc = request.getParameter("dtNasc");
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
		if (dtNasc==null || dtNasc.equals("")) {
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
		request.setAttribute("msgErro", msgErro);
		return valida;
		
	}
}
