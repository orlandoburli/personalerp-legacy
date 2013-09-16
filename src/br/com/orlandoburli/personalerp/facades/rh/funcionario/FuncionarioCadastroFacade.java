package br.com.orlandoburli.personalerp.facades.rh.funcionario;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.rh.FuncionarioDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.rh.FuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.CpfCnpjValidator;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class FuncionarioCadastroFacade extends BaseCadastroFlexFacade<FuncionarioVO, FuncionarioDAO> {
	
	private Integer CodigoCidadePesquisa;
	private static final long serialVersionUID = 1L;

	public FuncionarioCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NomeFuncionario", "Nome"));
		addNewValidator(new CpfCnpjValidator("CpfFuncionario", "Cpf"));
		addNewValidator(new NotEmptyValidator("DataNascimentoFuncionario", "Data de Nascimento"));
	}
	
	@IgnoreMethodAuthentication
	public void cidade() {
		CidadeVO filter = new CidadeVO();
		filter.setCodigoCidade(CodigoCidadePesquisa);
		try {
			write(Utils.voToXml(new CidadeDAO().get(filter)));
		} catch (SQLException e) {
			write(e.getMessage());
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return FuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FuncionarioVO.class;
	}

	public void setCodigoCidadePesquisa(Integer codigoCidadePesquisa) {
		CodigoCidadePesquisa = codigoCidadePesquisa;
	}

	public Integer getCodigoCidadePesquisa() {
		return CodigoCidadePesquisa;
	}
}