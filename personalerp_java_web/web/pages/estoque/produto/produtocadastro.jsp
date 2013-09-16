<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style type="text/css">

#CodigoEmpresa,#CodigoLoja,#CodigoProduto {
	width: 100px !important;
	direction: rtl !important;
}

#CodigoReferenciaProduto {
	width: 250px !important;
}

#DescricaoProduto {
	width: 690px !important;
}

#CodigoReferenciaProduto {
	direction: rtl;
}

#CodigoFabricante_cb {
	width: 408px !important;
}

#CodigoTipoTributacao_cb {
	width: 280px !important;
}

#SiglaUnidade_cb {
	width: 100px !important;
}

#PermiteVendaFracionadaProduto_cb {
	width: 80px !important;
}

#LarguraProduto, #AlturaProduto, #ComprimentoProduto, #VolumeProduto {
	direction: rtl;
	width: 140px !important;
}

#ObservacoesProduto {
	width: 700px !important;
	height: 100px;
}

</style>
<aside>
	<label class="TituloTela">Cadastro de Produtos</label>

	<div class="FormularioCadastro FormularioGeral" data-page-consulta="produtoconsulta.erp" data-page-operacao="${operacao}" data-page-cadastro="produtocadastro.erp">

		<fieldset>
			<legend>Empresa</legend>
			<input id="CodigoEmpresa" type="text" disabled="disabled" value="${vo.codigoEmpresa}" />
		</fieldset>

		<fieldset>
			<legend>Loja</legend>
			<input id="CodigoLoja" type="text" disabled="disabled" value="${vo.codigoLoja}" />
		</fieldset>

		<fieldset>
			<legend>C&oacute;digo</legend>
			<input id="CodigoProduto" type="text" disabled="disabled" value="${vo.codigoProduto }" />
		</fieldset>

		<fieldset>
			<legend>C&oacute;digo Referência</legend>
			<input id="CodigoReferenciaProduto" type="text" autofocus="autofocus" value="${vo.codigoReferenciaProduto}" />
		</fieldset>

		<fieldset>
			<legend>Descri&ccedil;&atilde;o</legend>
			<input id="DescricaoProduto" type="text" value="${vo.descricaoProduto}" />
		</fieldset>
		
		<fieldset>
			<legend>Código NCM</legend>
			<input id="CodigoNCMProduto" type="text" value="${vo.codigoNCMProduto }" />
		</fieldset>
		
		<fieldset>
			<legend>Fabricante</legend>
			<select id="CodigoFabricante" data-field-type="autocomplete" data-field-value="${vo.codigoFabricante}">
				<option></option>
				<c:forEach var="fab" items="${fabricantes}">
					<option value="${fab.codigofabricante}">${fab.nomeFabricante}</option>
				</c:forEach>
			</select>
		</fieldset>
		
		<fieldset>
			<legend>Grupo</legend>
			<select id="CodigoGrupo" data-field-type="autocomplete" data-field-value="${vo.codigoGrupo }">
				<option></option>
				<c:forEach var="grupo" items="${grupos}">
					<option value="${grupo.codigoGrupo}">${grupo.nomeGrupo}</option>
				</c:forEach>
			</select>
		</fieldset>
		
		<fieldset>
			<legend>Sub-Grupo</legend>
			<select id="CodigoSubGrupo" data-field-type="autocomplete" data-field-value="${vo.codigoSubGrupo }">
				<option></option>
				<c:forEach var="subgrupo" items="${subgrupos}">
					<option value="${subgrupo.codigoSubGrupo}">${subgrupo.nomeSubGrupo}</option>
				</c:forEach>
			</select>
		</fieldset>

		<fieldset>
			<legend>Situação</legend>
			<select id="SituacaoProduto" data-field-type="autocomplete" data-field-value="${vo.situacaoProduto }">
				<option value="N">Normal</option>
				<option value="I">Inativo</option>
			</select>
		</fieldset>

		<fieldset>
			<legend>Tipo de Tributação</legend>
			<select id="CodigoTipoTributacao" data-field-type="autocomplete" data-field-value="${vo.codigoTipoTributacao }">
				<option></option>
				<c:forEach var="tributacao" items="${tributacoes}">
					<option value="${tributacao.codigoTipoTributacao}">${tributacao.identificacaoTipoTributacao}</option>
				</c:forEach>
			</select>
		</fieldset>
	
		<fieldset>
			<legend>Permite venda fracionada</legend>
			<select id="PermiteVendaFracionadaProduto" data-field-type="autocomplete" data-field-value="${vo.permiteVendaFracionadaProduto}" >
				<option value="S">Sim</option>
				<option value="N">Não</option>
			</select>
		</fieldset>
		
		<fieldset>
			<legend>Unidade</legend>
			<select id="SiglaUnidade" data-field-type="autocomplete" data-field-value="${vo.siglaUnidade}">
				<option></option>
				<c:forEach var="unidade" items="${unidades}">
					<option value="${unidade.siglaUnidade}">${unidade.siglaUnidade}</option>
				</c:forEach>
			</select>
		</fieldset>
		
		<hr/>
		
		<fieldset>
			<legend>Largura</legend>
			<input id="LarguraProduto" type="text" value="${vo.larguraProduto }" data-field-type="number" data-field-precision="2" />
		</fieldset>
		
		<fmt:setLocale value="pt-BR" />
		
		<fieldset>
			<legend>Altura</legend>
			<input id="AlturaProduto" type="text"   value="${vo.alturaProduto}" data-field-type="number" data-field-precision="2"/>
		</fieldset>
		
		<fieldset>
			<legend>Comprimento</legend>
			<input id="ComprimentoProduto" type="text" value="${vo.comprimentoProduto }" data-field-type="number" data-field-precision="2" />
		</fieldset>
		
		<fieldset>
			<legend>Volume (m3)</legend>
			<input id="VolumeProduto" type="text" value="${vo.volumeProduto }" data-field-type="number" data-field-precision="2"/>
		</fieldset>
		
		<hr/>
		
		<fieldset>
			<legend>Margem Lucro Varejo (%)</legend>
			<input id="MargemLucroVarejoProduto" type="text" value="${vo.margemLucroVarejoProduto }" data-field-type="number" data-field-precision="2"/>
		</fieldset>
		
		<fieldset>
			<legend>Margem Lucro Atacado (%)</legend>
			<input id="MargemLucroAtacadoProduto" type="text" value="${vo.margemLucroAtacadoProduto }" data-field-type="number" data-field-precision="2"/>
		</fieldset>
		
		<fieldset>
			<legend>Estoque Mínimo</legend>
			<input id="EstoqueMinimoProduto" type="text" value="${vo.estoqueMinimoProduto }" data-field-type="number" data-field-precision="2"/>
		</fieldset>
		
		<fieldset>
			<legend>Observações</legend>
			<textarea id="ObservacoesProduto" name="ObservacoesProduto">${vo.observacoesProduto}</textarea>
		</fieldset>
		
	</div>

	<div class="FormularioBotoes">
		<button title="Salvar (Ctrl + S)" class="BotaoSalvar">Salvar</button>
		<button title="Excluir (Ctrl + Del)" class="BotaoExcluir">Excluir</button>
		<button title="Voltar (Esc)" class="BotaoVoltar">Voltar</button>
	</div>
</aside>