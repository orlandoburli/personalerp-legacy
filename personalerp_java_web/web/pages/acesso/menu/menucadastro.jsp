<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">
#CodigoMenu {
	direction: rtl;
}
#NomeMenu {
	width: 400px !important;
}

#NomeObjeto {
	width: 645px !important;
}

#NomeMenuPai {
	width: 645px !important;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Menus</label>

	<div class="FormularioCadastro FormularioGeral" data-page-consulta="menuconsulta.erp" data-page-operacao="${operacao}" data-page-cadastro="menucadastro.erp">

		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="CodigoMenu" type="text" disabled="disabled" value="${vo.codigoMenu }" />
		</fieldset>

		<fieldset>
			<legend>Nome</legend>
			<input id="NomeMenu" type="text" value="${vo.nomeMenu}"  autofocus="autofocus"/>
		</fieldset>
		
		<fieldset>
			<legend>Objeto</legend>
			<input id="CodigoObjeto" type="hidden" value="${vo.codigoObjeto}"/>
			<input id="NomeObjeto" type="text" value="${vo.descricaoObjeto}" />
		</fieldset>
		
		<fieldset>
			<legend>Menu Pai</legend>
			<input id="CodigoMenuPai" type="hidden" value="${vo.codigoMenuPai}"/>
			<input id="NomeMenuPai" type="text" value="${vo.nomeMenuPai}" />
		</fieldset>

		<fieldset>
			<legend>Ordem</legend>
			<input id="OrdemMenu" type="text" value="${vo.ordemMenu}" />
		</fieldset>
		
	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>

</aside>

<script type="text/javascript">

	// Objeto
	$( "#NomeObjeto" ).autocomplete({
		source : "menucadastro.objeto.erp",
		autoFocus : true,
		change : function () {			
			if ($("#NomeObjeto").val().length <= 0) {
				$("#CodigoObjeto").val("");
			}
		},
		select : function(event, ui) {
			$("#NomeObjeto").val(ui.item.DescricaoObjeto);
			$("#CodigoObjeto").val(ui.item.CodigoObjeto);
			return false;
		}
	}).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
      return $( "<li>" )
        .append( "<a>" + item.DescricaoObjeto + "<br/>" + item.NomeObjeto + "</a>" )
        .appendTo( ul );
    };
    
    // Menu Pai
    $("#NomeMenuPai").autocomplete({
    	source : "menucadastro.menupai.erp",
    	autoFocus : true,
    	change : function() {
    		if ($("#NomeMenuPai").val().length <= 0) {
				$("#CodigoMenuPai").val("");
			}
    	},
    	select : function(event, ui) {
    		$("#NomeMenuPai").val(ui.item.NomeMenu);
    		$("#CodigoMenuPai").val(ui.item.CodigoMenu);
    		return false;
    	}
    }).data("ui-autocomplete")._renderItem = function (ul, item) {
    	return $("<li>")
    		.append("<a>" + item.NomeMenu + "</a>")
    		.appendTo( ul );
    };
</script>