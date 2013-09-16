<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#CodigoObjeto {
	direction: rtl;
}
#NomeObjeto {
	width: 700px !important;
}

#DescricaoObjeto {
	width: 700px !important;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Objetos</label>
	
	<div class="FormularioCadastro FormularioGeral" data-page-consulta="objetoconsulta.erp" data-page-operacao="${operacao}" data-page-cadastro="objetocadastro.erp">

		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="CodigoObjeto" type="text" disabled="disabled" value="${vo.codigoObjeto }" />
		</fieldset>
		
		<fieldset>
			<legend>Nome</legend>
			<input id="NomeObjeto" type="text" value="${vo.nomeObjeto}"  autofocus="autofocus"/>
		</fieldset>

		<fieldset>
			<legend>Descri&ccedil;&atilde;o</legend>
			<input id="DescricaoObjeto" type="text" value="${vo.descricaoObjeto}" />
		</fieldset>
		
	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>