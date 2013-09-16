package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.veiculo;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.cadastro.madeireira.TipoVeiculoDAO;
import br.com.orlandoburli.core.dao.cadastro.madeireira.VeiculoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.madeireira.VeiculoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class VeiculoCadastroFacade extends BaseCadastroFlexFacade<VeiculoVO, VeiculoDAO>{

	private static final long serialVersionUID = 1L;

	public VeiculoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoVeiculo", "Descrição"));
		addNewValidator(new NotEmptyValidator("CodigoTipoVeiculo", "Tipo de Veículo", "TipoVeiculo"));
	}
	
	@IgnoreMethodAuthentication
	public void tipoveiculo() {
		TipoVeiculoDAO _dao = new TipoVeiculoDAO();
		try {
			write(Utils.voToXml(_dao.getList(), _dao.getListCount()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
