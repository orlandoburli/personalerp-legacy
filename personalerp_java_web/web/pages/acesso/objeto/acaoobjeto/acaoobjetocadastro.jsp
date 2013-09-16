<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#CodigoAcaoObjeto {
	direction: rtl;
}
#NomeAcaoObjeto {
	width: 500px !important;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Ações do Objeto [${objeto.descricaoObjeto}]</label>

	<div class="FormularioCadastro FormularioGeral" data-page-consulta="acaoobjetoconsulta.erp" data-page-operacao="${operacao}" data-page-cadastro="acaoobjetocadastro.erp">
		
		<input id="CodigoObjeto" type="hidden" value="${vo.codigoObjeto }" />
		
		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="CodigoAcaoObjeto" type="text" disabled="disabled" value="${vo.codigoAcaoObjeto }" />
		</fieldset>

		<fieldset>
			<legend>Descri&ccedil;&atilde;o</legend>
			<input id="NomeAcaoObjeto" type="text" value="${vo.nomeAcaoObjeto}"  autofocus="autofocus"/>
		</fieldset>
	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>
