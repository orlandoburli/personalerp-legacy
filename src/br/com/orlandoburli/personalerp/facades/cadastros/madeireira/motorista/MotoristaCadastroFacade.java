package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.motorista;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.cadastro.madeireira.MotoristaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.MotoristaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class MotoristaCadastroFacade extends BaseCadastroFlexFacade<MotoristaVO, MotoristaDAO> {

	private static final long serialVersionUID = 1L;
	private Integer CodigoCidadePesquisa;

	public MotoristaCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeMotorista", "Nome"));
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getVo().setCodigoLoja(getLojasessao().getCodigoLoja());
		return super.doBeforeInsert();
	}

	@Override
	protected Class<?> getDAOClass() {
		return MotoristaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MotoristaVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void cidade() {
		CidadeVO filter = new CidadeVO();
		filter.setCodigoCidade(getCodigoCidadePesquisa());
		try {
			write(Utils.voToXml(new CidadeDAO().get(filter)));
		} catch (SQLException e) {
			write(e.getMessage());
		}
	}

	public void setCodigoCidadePesquisa(Integer codigoCidadePesquisa) {
		CodigoCidadePesquisa = codigoCidadePesquisa;
	}

	public Integer getCodigoCidadePesquisa() {
		return CodigoCidadePesquisa;
	}
}