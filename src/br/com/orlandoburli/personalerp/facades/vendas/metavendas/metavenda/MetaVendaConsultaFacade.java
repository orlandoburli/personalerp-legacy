package br.com.orlandoburli.personalerp.facades.vendas.metavendas.metavenda;

import br.com.orlandoburli.core.dao.vendas.metavendas.MetaVendaDAO;
import br.com.orlandoburli.core.vo.vendas.metavendas.MetaVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MetaVendaConsultaFacade extends BaseConsultaFlexFacade<MetaVendaVO, MetaVendaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataInicialMetaVenda DESC, NomeVendedor");
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		getFilter().setNomeVendedor(getFiltro() + "%");
	}
}