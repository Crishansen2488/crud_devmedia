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
	table{
		width: 100%;
	}
	table img{
		width: 25px;
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
				<table border="1" cellspacing="0" cellpadding="5">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>CPF</th>
							<th>Data de Nascimento</th>
							<th>Sexo</th>
							<th>Comentário</th>
							<th colspan="2" align="center">Ações</th>
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
						<td align="center">
							<a href="" title="Editar"><img src="img/edit.png" alt="Edição de Pessoa"/></a>
						</td>
						<td align="center" title="Remover">
							<a href=""><img src="img/delete.png" alt="Remoção de Pessoa"/></a>
						</td>
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