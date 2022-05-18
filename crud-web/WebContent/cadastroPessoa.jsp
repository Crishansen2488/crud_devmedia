<%@page import="br.com.devmedia.crud.dto.PreferenciaMusicalDTO"%>
<%@page import="br.com.devmedia.crud.dto.EstadoDTO"%>
<%@page import="br.com.devmedia.crud.dto.CidadeDTO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastros</title>
<link rel="stylesheet" href="css/global.css"/>
<!-- criacao da pagina de formulario -->
<script type="text/javascript">
	function popularComboCidades(comboEstados) {
		var idEstado = comboEstados.options[comboEstados.selectedIndex].value;
		var formCadastro = document.forms[0];
		formCadastro.action = 'main?acao=montagemCadastro&getCidades=true&idEstado=' + idEstado;
		formCadastro.submit();
	}
	
	function cadastrar() {
		var formCadastro = document.forms[0];
		formCadastro.action='main?acao=cadastroPessoa';
		formCadastro.submit();
	}

</script>
</head>
<body>

	<jsp:include page="cabecalho.jsp"/>
		<h1>Cadastro</h1>
	
		<div class="main">
			<form action="main?acao=cadastroPessoa" method="post" name="form">
				<fieldset id="fieldset">
				<jsp:include page="msg.jsp"></jsp:include>
					<legend>Cadastro de Pessoa</legend>
					 
					<table cellpadding="5">
						<tr>
							<td>Nome*:</td>
							<td><input type="text" name="nome" maxlength="45" value="${param.nome}"/></td>
						</tr>
						<tr>
							<td>CPF*:</td>
							<td><input type="text" name="cpf" maxlength="11" value="${param.cpf}"/></td>
						</tr>
						<tr>
							<td>Data de Nascimento*:</td>
							<td><input type="text" name="nasc" maxlength="10" value="${param.nasc}"/></td>
						</tr>
						<tr>
							<td>Sexo*:</td>
							<td>
								<input type="radio" name="sexo" value="M" <%="M".equals(request.getParameter("sexo")) ? "checked" : ""%>/> Masculino
								<input type="radio" name="sexo" value="F" <%="F".equals(request.getParameter("sexo")) ? "checked" : ""%>/> Feminino
							</td>
						</tr>
						<tr>
							<td>Preferências:</td>
							<td>
								<%
									List<PreferenciaMusicalDTO> preferencias = (List<PreferenciaMusicalDTO>) session.getAttribute("listaPreferencias");
									String[] paramPrefs = request.getParameterValues("gostos");
									if (preferencias != null) {
										for (PreferenciaMusicalDTO preferencia : preferencias) {
								%>
											<input type="checkbox" name="gostos" value="<%=preferencia.getIdPreferencia()%>" 
											<%=paramPrefs != null && Arrays.asList(paramPrefs).contains(String.valueOf(preferencia.getIdPreferencia())) ? "checked" : ""%>/>
											<%=preferencia.getDescricao()%>
								<%
										}
									}
								%>
							</td>
						</tr>
						<tr>
							<td>Comentário:</td>
							<td>
								<textarea rows="5" cols="40" name="comentario">${param.comentario}</textarea>
							</td>
						</tr>
					</table>
					
					<fieldset>
						<legend>Endereço</legend>
						
						<table cellpadding="5">
							<tr>
								<td style="width:138px">Estado*:</td>
								<td>
									<select name="estado" id="estado" onchange="popularComboCidades(this)">
										<option value="0">Selecione...</option>
									<%
										List<EstadoDTO> listaEstados = (List<EstadoDTO>) session.getAttribute("listaEstados");
											for (EstadoDTO estado : listaEstados) {
									%>
												<option value="<%=estado.getIdEstado()%>" 
												<%= request.getParameter("estado") != null && String.valueOf(estado.getIdEstado()).equals(request.getParameter("estado")) ? "selected='selected'" : "" %>>
												<%=estado.getDescricao()%></option>
									<%
											}
									%>
									</select>
								</td>
							</tr>
							<tr>
								<td>Cidade*:</td>
								<td>
									<select name="cidade">
										<option value="0">Selecione...</option>
									<%
										List<CidadeDTO> listaCidades = (List<CidadeDTO>) session.getAttribute("listaCidades");
										if (listaCidades != null) {
											for (CidadeDTO cidade : listaCidades) {
									%>
										<option value="<%= cidade.getIdCidade() %>"
											<%= request.getParameter("cidade") != null && String.valueOf(cidade.getIdCidade()).equals(request.getParameter("cidade")) ? "selected='selected'" : "" %>>
											<%= cidade.getDescricao() %>
										</option>
									<%
											}
										}
									%>
									</select>
								</td>
							</tr>
							<tr>
								<td>Logradouro*:</td>
								<td>
									<input style="width:302px" type="text" name="logradouro" value="${param.logradouro}"/>
								</td>
							</tr>
						</table>
					</fieldset>
					<div class="campo_obg"><span>* Campos obrigatórios</span></div>
				<button type="reset" ><strong>Limpar</strong></button>
				<button type="submit" onclick="cadastrar()"><strong>Cadastrar</strong></button>
				</fieldset>
			</form>
		</div>
	
	<jsp:include page="rodape.jsp"/>

</body>
</html>