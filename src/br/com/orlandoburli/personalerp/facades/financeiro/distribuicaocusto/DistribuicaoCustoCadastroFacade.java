package br.com.orlandoburli.personalerp.facades.financeiro.distribuicaocusto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.DistribuicaoCustoDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.financeiro.DistribuicaoCustoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class DistribuicaoCustoCadastroFacade extends BaseCadastroFlexFacade<DistribuicaoCustoVO, DistribuicaoCustoDAO>{

	private static final long serialVersionUID = 1L;

	public DistribuicaoCustoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoLoja", "Loja"));
		addNewValidator(new NotEmptyValidator("CodigoLojaReferencia", "Loja Referência"));
		addNewValidator(new NotEmptyValidator("PercentualCusto", "Percentual do Custo"));
		addNewValidator(new NotEmptyValidator("CodigoPlanoConta", "Plano de contas"));
		addNewValidator(new NotEmptyValidator("DataInicialDistribuicaoCusto", "Data Inicial"));
		addNewValidator(new NotEmptyValidator("DataFinalDistribuicaoCusto", "Data Final"));
	}
	
	@Override
	public void doBeforeWriteVo(DistribuicaoCustoVO vo) {
		super.doBeforeWriteVo(vo);
		if (vo != null) {
			vo.setNumeroPlanoConta("x" + vo.getNumeroPlanoConta() + "");
		}
	}
	
	@IgnoreMethodAuthentication
	public void dados() {
		List<IValueObject> list = new ArrayList<IValueObject>();
		try {
			list.addAll(new LojaDAO().getList(null, "NomeLoja"));
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
