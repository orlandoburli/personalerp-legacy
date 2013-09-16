<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside>
	<label class="TituloTela">Consulta de Objetos</label>
	
	<div class="FormularioConsulta FormularioGeral">
		
		<div id="CamposFiltro">
			
			<fieldset>
				<legend>Pesquisar por</legend>
				
				<select id="ParametroPesquisa">
					<option value="Nome">Nome</option>
					<option value="Codigo">Código</option>
				</select> <input id="PesquisarPor" type="text" autofocus="autofocus" autocomplete="off"/>
				
				<button title="Clique para pesquisar [Atalho: ENTER]" class="BotaoPesquisar">Pesquisar</button>
				
			</fieldset>
		</div>
		
		<div class="DataGridConsulta" data-page="objetoconsulta.grid.erp" data-page-size="10" data-detail-page="objetocadastro.erp"></div>
		
		<div class="FormularioBotoesNavegacaoGrid">
			<button class="BotaoNavegacaoPrimeiro" title="Primeiro [Home]"></button>
			<button class="BotaoNavegacaoAnterior" title="Anterior [&larr;/PgUp]"></button>
			<button class="BotaoNavegacaoProximo" title="Próximo [&rarr;/PgDown]"></button>
			<button class="BotaoNavegacaoUltimo" title="Último [End]"></button>
		</div>
		
	</div>

	<div class="FormularioBotoes">
		<button type="button" class="BotaoNovo" title="Novo Registro (Ctrl + N)">Novo</button>
		<button type="button" class="BotaoEditar" title="Editar Registro Selecionado (Ctrl + E)">Editar</button>
		<button type="button" class="BotaoExcluir" title="Excluir Registro Selecionado (Ctrl + Del)">Excluir</button>
		<button type="button" class="BotaoAcoes" title="Ações">Ações</button>
	</div>
	
</aside>

<script type="text/javascript">
	$(".BotaoAcoes").click(function() {
		var paginaAcoes = "acaoobjetoconsulta.erp?" + getSelectedDataId();
		loadPage(paginaAcoes);
		loadJs("web/assets/js/consulta.js");
	});
</script>