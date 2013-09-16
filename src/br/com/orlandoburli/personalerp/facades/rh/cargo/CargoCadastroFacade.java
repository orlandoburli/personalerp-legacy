package br.com.orlandoburli.personalerp.facades.rh.cargo;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.CargoDAO;
import br.com.orlandoburli.core.dao.rh.CboDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.CargoVO;
import br.com.orlandoburli.core.vo.rh.CboVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class CargoCadastroFacade extends BaseCadastroFlexFacade<CargoVO, CargoDAO> {

	private static final long serialVersionUID = 1L;
	
	private String CodigoCboPesquisa;

	public CargoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NomeCargo", "Nome do Cargo"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getCodigoCbo() != null && getVo().getCodigoCbo().trim().equals("")) {
			getVo().setCodigoCbo(null);
		}
		return super.doBeforeSave();
	}

	@Override
	protected Class<?> getDAOClass() {
		return CargoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return CargoVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void cbo() {
		CboDAO _dao = new CboDAO();
		CboVO _vo = new CboVO();
		_vo.setCodigoCbo(getCodigoCboPesquisa());
		try {
			_vo = _dao.get(_vo);
			if (_vo != null) {
				write(Utils.voToXml(_vo));
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	public void setCodigoCboPesquisa(String codigoCboPesquisa) {
		CodigoCboPesquisa = codigoCboPesquisa;
	}

	public String getCodigoCboPesquisa() {
		return CodigoCboPesquisa;
	}
}