<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Listar tarefa</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/lista.css' />">
</head>
<body>
	<a href="novaTarefa">Criar nova tarefa</a>
	<br />
	<br />
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Descrição</th>
				<th>Finalizado?</th>
				<th>Data de finalização</th>
				<th>Ações</th>
			</tr>
		</thead>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr>
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<c:if test="${tarefa.finalizado eq false}">
					<td id="tarefa_${ tarefa.id }">Não finalizado</td>
				</c:if>
				<c:if test="${tarefa.finalizado eq true}">
					<td id="tarefa_${ tarefa.id }">Finalizado</td>
				</c:if>
				<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
						pattern="dd/MM/yyyy" /></td>
				<td>
					<a href="mostraTarefa?id=${ tarefa.id }" >Alterar</a>
					<a href="removeTarefa?id=${ tarefa.id }" >Remover</a>
				<c:if test="${tarefa.finalizado eq false}">
					<a href="#" onclick="finalizaAgora(${ tarefa.id })">Finalizar agora!</a>
				</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script type="text/javascript">
	function finalizaAgora(id) {
		$.post("finalizaTarefa", {'id': id}, function(){
			$("#tarefa_" + id).html("Finalizado");
		});
	}
</script>
</html>
