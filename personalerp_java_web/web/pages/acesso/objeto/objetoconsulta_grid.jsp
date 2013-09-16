<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
table thead td:nth-child(1) {
	width: 60px;
}

table thead td:nth-child(1) {
	text-align: right;
}

table tbody tr td:nth-child(1) {
	text-align: right;
}

table thead td:nth-child(2) {
	width: 100px;
}

</style>

<table data-page-count="${pageCount}">
	<thead>
		<tr>
			<td>C&oacute;digo</td>
			<td>Nome</td>
			<td>Descrição</td>
		</tr>
	</thead>

	<tfoot>
		<tr>
			<td colspan="4">P&aacute;gina ${pageNumber} de ${pageCount}</td>
		</tr>
	</tfoot>

	<tbody>
		<c:forEach items="${listSource}" var="objeto">
			<tr
				data-id="CodigoObjeto=${objeto.codigoObjeto}">
				<td>${objeto.codigoObjeto }</td>
				<td>${objeto.nomeObjeto }</td>
				<td>${objeto.descricaoObjeto }</td>
			</tr>
		</c:forEach>
	</tbody>

</table>


