package br.com.devmedia.crud.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import br.com.devmedia.crud.exception.ValidationException;
import br.com.devmedia.crud.util.MensagemContantes;

public class DataValidator implements Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException {
		String msgErro = "";
		for (String key : valores.keySet()) {
			String data = (String) valores.get(key);
			if (!"".equals(data)) {
				try {
					if (data.length() < 10) {
						msgErro += MensagemContantes.MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO.replace("?", key).concat("<br/>");
					}
					if (data.length() > 10) {				
						msgErro += MensagemContantes.MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO.replace("?", key).concat("<br/>");
					}
					new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} catch (ParseException e) {
					msgErro += MensagemContantes.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
				}
			}
		}
		if (!"".equals(msgErro)) {
			throw new ValidationException(msgErro);
		}
		
		return true;
	}
	
}
