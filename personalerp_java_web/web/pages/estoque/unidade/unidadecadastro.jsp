<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#CodigoUnidade {
	direction: rtl;
}
#NomeUnidadeSingular, #NomeUnidadePlural {
	width: 400px !important;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Unidades</label>

	<div class="FormularioCadastro FormularioGeral" data-page-consulta="unidadeconsulta.erp" data-page-operacao="${operacao}" data-page-cadastro="unidadecadastro.erp">
		
		<c:if test="${operacao != 'inserir' }">
			<c:set var="disabled" value="disabled='disable'"/>
		</c:if>
		
		<fieldset>
			<legend>Sigla</legend>
			<input id="SiglaUnidade" type="text" ${disabled} value="${vo.siglaUnidade }" />
		</fieldset>
		
		<hr/>
		
		<fieldset>
			<legend>Nome Unidade Singular</legend>
			<input id="NomeUnidadeSingular" type="text" value="${vo.nomeUnidadeSingular}"  autofocus="autofocus"/>
		</fieldset>
		
		<fieldset>
			<legend>Nome Unidade Plural</legend>
			<input id="NomeUnidadePlural" type="text" value="${vo.nomeUnidadePlural}"/>
		</fieldset>
			
	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>