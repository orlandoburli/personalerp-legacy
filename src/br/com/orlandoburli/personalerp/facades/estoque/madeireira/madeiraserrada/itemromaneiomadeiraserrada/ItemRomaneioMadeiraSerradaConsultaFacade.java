package br.com.orlandoburli.personalerp.facades.estoque.madeireira.madeiraserrada.itemromaneiomadeiraserrada;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomadeiraserrada.ItemRomaneioMadeiraSerradaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.ItemRomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemRomaneioMadeiraSerradaConsultaFacade extends BaseConsultaFlexFacade<ItemRomaneioMadeiraSerradaVO, ItemRomaneioMadeiraSerradaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeProdutoMadeiraSerrada(getFiltro() + "%");
		setOrderFields("NomeProdutoMadeiraSerrada");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ItemRomaneioMadeiraSerradaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ItemRomaneioMadeiraSerradaVO.class;
	}
	
	@Override
	public void doBeforeWrite() {
		for (ItemRomaneioMadeiraSerradaVO item : getListSource()) {
			ItemRomaneioMadeiraSerradaCadastroFacade.calculaVolumeItem(item);
		}
		super.doBeforeWrite();
	}
}