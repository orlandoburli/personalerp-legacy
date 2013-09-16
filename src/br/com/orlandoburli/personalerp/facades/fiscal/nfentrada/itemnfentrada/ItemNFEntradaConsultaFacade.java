package br.com.orlandoburli.personalerp.facades.fiscal.nfentrada.itemnfentrada;

import br.com.orlandoburli.core.dao.fiscal.nfentrada.ItemNFEntradaDAO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemNFEntradaConsultaFacade extends BaseConsultaFlexFacade<ItemNFEntradaVO, ItemNFEntradaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoItemNFEntrada(getFiltro() + "%");
		setOrderFields("SequencialItemNFEntrada");
	}

}