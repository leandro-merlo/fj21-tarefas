<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<html>
<head>
<title>Alterar tarefa</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value="/js/datepicker-pt-BR.js"/>" ></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/themes/base/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/themes/base/theme.min.css" rel="stylesheet" type="text/css"/>

<script>
	$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
</script>
</head>
<body>
	<h3>Alterar tarefa - ${tarefa.id}</h3>
	<form action="alteraTarefa" method="post">
		<input type="hidden" name="id" value="${tarefa.id}" /> Descrição:<br />
		<textarea name="descricao" cols="100" rows="5">${tarefa.descricao}</textarea>
		<br /> Finalizado? <input type="checkbox" name="finalizado" value="true" ${tarefa.finalizado? 'checked' : '' } /> 
		<br /> Data de finalização: <br /> <tags:campoData id="dataFinalizacao" value="${tarefa.dataFinalizacao.time}" />
		<br /> <input type="submit" value="Alterar" />
	</form>
</body>
</html>
