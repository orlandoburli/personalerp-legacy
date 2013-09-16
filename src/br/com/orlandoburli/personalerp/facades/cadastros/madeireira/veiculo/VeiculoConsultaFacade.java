package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.veiculo;

import br.com.orlandoburli.core.dao.cadastro.madeireira.VeiculoDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.VeiculoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class VeiculoConsultaFacade extends BaseConsultaFlexFacade<VeiculoVO, VeiculoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoVeiculo(getFiltro() + "%");
	}
}
