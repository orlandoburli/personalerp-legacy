<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<aside>
	<label class="TituloTela">Consulta de Unidades</label>
	
	<div class="FormularioConsulta FormularioGeral">
		
		<div id="CamposFiltro">
			
			<fieldset>
				<legend>Pesquisar por</legend>
				
				<select id="ParametroPesquisa">
					<option value="Nome">Nome</option>
					<option value="Sigla">Sigla</option>
				</select> <input id="PesquisarPor" type="text" autofocus="autofocus" autocomplete="off"/>
				
				<button title="Clique para pesquisar [Atalho: ENTER]" class="BotaoPesquisar">Pesquisar</button>
				
			</fieldset>
		</div>
		
		<div class="DataGridConsulta" data-page="unidadeconsulta.grid.erp" data-page-size="10" data-detail-page="unidadecadastro.erp"></div>
		
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
	</div>
	
</aside>