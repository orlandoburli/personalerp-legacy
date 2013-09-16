package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.gerentemadeireira;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.madeireira.GerenteMadeireiraDAO;
import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.madeireira.GerenteMadeireiraVO;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class GerenteMadeireiraCadastroFacade extends BaseCadastroFlexFacade<GerenteMadeireiraVO, GerenteMadeireiraDAO> {

	private static final long serialVersionUID = 1L;

	public GerenteMadeireiraCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) { 
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoContratoTrabalho", "Funcionário", "ContratoTrabalho"));
	}
	
	@IgnoreMethodAuthentication
	public void contratos() {
		ContratoTrabalhoDAO _dao = new ContratoTrabalhoDAO();
		ContratoTrabalhoVO _filter = new ContratoTrabalhoVO();
		try {
			write(Utils.voToXml(_dao.getList(_filter), _dao.getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return GerenteMadeireiraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return GerenteMadeireiraVO.class;
	}
}