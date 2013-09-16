package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomadeirabeneficiada.itemromaneiobeneficiada;

import br.com.orlandoburli.core.dao.estoque.madeireira.madeirabeneficiada.ItemRomaneioBeneficiadaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeirabeneficiada.ItemRomaneioBeneficiadaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemRomaneioBeneficiadaConsultaFacade extends BaseConsultaFlexFacade<ItemRomaneioBeneficiadaVO, ItemRomaneioBeneficiadaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeProdutoMadeiraBeneficiada(getFiltro() + "%");
		setOrderFields("NomeProdutoMadeiraBeneficiada");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ItemRomaneioBeneficiadaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ItemRomaneioBeneficiadaVO.class;
	}
	
	@Override
	public void doBeforeWrite() {
		for (ItemRomaneioBeneficiadaVO item : getListSource()) {
			ItemRomaneioBeneficiadaCadastroFacade.calculaVolumeItem(item);
			ItemRomaneioBeneficiadaCadastroFacade.calculaAreaItem(item);
		}
		super.doBeforeWrite();
	}
}