<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#CodigoGrupo {
	direction: rtl;
}
#NomeGrupo {
	width: 500px !important;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Grupos</label>

	<div class="FormularioCadastro FormularioGeral" data-page-consulta="grupoconsulta.erp" data-page-operacao="${operacao}" data-page-cadastro="grupocadastro.erp">

		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="CodigoGrupo" type="text" disabled="disabled" value="${vo.codigoGrupo }" />
		</fieldset>

		<fieldset>
			<legend>Descri&ccedil;&atilde;o</legend>
			<input id="NomeGrupo" type="text" value="${vo.nomeGrupo}"  autofocus="autofocus"/>
		</fieldset>
		
		<fieldset>
			<legend>Tipo</legend>
			<select id="TipoGrupo" data-field-type="autocomplete" data-field-value="${vo.tipoGrupo}" >
				<option value="M">Materiais</option>
				<option value="S">Serviços</option>
				<option value="O">Outros</option>
			</select>
		</fieldset>
		
	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>