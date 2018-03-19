<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="id" required="true" %>
<%@ attribute name="value" %>
<input id="${id}" name="${id}" value="${value}" />
<script type="text/javascript">
		$("#${id}").datepicker({dateFormat: 'dd/mm/yy'});				
</script>
