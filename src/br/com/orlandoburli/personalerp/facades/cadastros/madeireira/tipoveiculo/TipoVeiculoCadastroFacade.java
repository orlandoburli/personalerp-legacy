package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.tipoveiculo;

import br.com.orlandoburli.core.dao.cadastro.madeireira.TipoVeiculoDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.TipoVeiculoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoVeiculoCadastroFacade extends BaseCadastroFlexFacade<TipoVeiculoVO, TipoVeiculoDAO>{

	private static final long serialVersionUID = 1L;

	public TipoVeiculoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoTipoVeiculo", "Descrição"));
	}
}
