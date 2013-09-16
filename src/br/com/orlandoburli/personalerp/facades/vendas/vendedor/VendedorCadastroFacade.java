package br.com.orlandoburli.personalerp.facades.vendas.vendedor;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.FuncionarioVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class VendedorCadastroFacade extends BaseCadastroFlexFacade<VendedorVO, VendedorDAO> {

	private static final long	serialVersionUID	= 1L;

	public VendedorCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NomeVendedor", "Nome"));
	}
	
	@IgnoreMethodAuthentication
	public void funcionarios() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new FuncionarioVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}