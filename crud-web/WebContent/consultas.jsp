<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.devmedia.crud.dto.PessoaDTO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	thead{
		text-align: left;	
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultas</title>
<link rel="stylesheet" href="css/global.css"/>
</head>
<body>

	<jsp:include page="cabecalho.jsp"/>
	
	<div class="main">
		<form action="">
			<jsp:include page="msg.jsp"></jsp:include>
			<fieldset>
				<legend>Consultas</legend> 
				<h1>Consultas</h1>
				<table>
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>CPF</th>
							<th>Data de Nascimento</th>
							<th>Sexo</th>
							<th>Comentário</th>
						</tr>
					</thead>
					<tbody>
				<%
					List<PessoaDTO> listaPessoas = (List<PessoaDTO>) request.getAttribute("listaPessoas");
					for(PessoaDTO pessoa : listaPessoas){
						
				%>
					<tr>
						<td><%=pessoa.getIdPessoa()%></td>
						<td><%=pessoa.getNome()%></td>
						<td><%=pessoa.getCpf()%></td>
						<td><%=pessoa.getNasc()%></td>
						<td><%=pessoa.getSexo()%></td>
						<td><%=pessoa.getComentario()%></td>
					</tr>
				<%
					}
				%>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	
	<jsp:include page="rodape.jsp"/>

</body>
</html>