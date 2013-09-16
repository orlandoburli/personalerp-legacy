<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css">
table thead td:nth-child(1) {
	width: 60px;
}

table thead td:nth-child(2) {
	width: 80px;
}

table thead td:nth-child(1),table thead td:nth-child(2) {
	text-align: right;
}

table tbody tr td:nth-child(1),table tbody tr td:nth-child(2) {
	text-align: right;
}
</style>

<table data-page-count="${pageCount}">
	<thead>
		<tr>
			<td>C&oacute;digo</td>
			<td>Refer&ecirc;ncia</td>
			<td>Nome</td>
		</tr>
	</thead>

	<tfoot>
		<tr>
			<td colspan="4">P&aacute;gina ${pageNumber} de ${pageCount}</td>
		</tr>
	</tfoot>

	<tbody>
		<c:forEach items="${listSource}" var="produto">
			<tr
				data-id="CodigoEmpresa=${produto.codigoEmpresa}&CodigoLoja=${produto.codigoLoja}&CodigoProduto=${produto.codigoProduto}">
				<td>${produto.codigoProduto }</td>
				<td>${produto.codigoReferenciaProduto }</td>
				<td>${produto.descricaoProduto }</td>
			</tr>
		</c:forEach>
	</tbody>

</table>


