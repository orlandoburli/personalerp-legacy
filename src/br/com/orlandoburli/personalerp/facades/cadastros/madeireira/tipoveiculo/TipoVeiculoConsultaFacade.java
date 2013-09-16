package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.tipoveiculo;

import br.com.orlandoburli.core.dao.cadastro.madeireira.TipoVeiculoDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.TipoVeiculoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoVeiculoConsultaFacade extends BaseConsultaFlexFacade<TipoVeiculoVO, TipoVeiculoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoTipoVeiculo(getFiltro() + "%");
	}
}