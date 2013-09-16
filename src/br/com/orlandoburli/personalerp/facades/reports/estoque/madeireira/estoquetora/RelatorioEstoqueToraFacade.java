package br.com.orlandoburli.personalerp.facades.reports.estoque.madeireira.estoquetora;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioEstoqueToraFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	private Integer CodigoEssencia;
	private String TipoRelatorio;
	
	public RelatorioEstoqueToraFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected String getJasperFileName() {
		if (getTipoRelatorio() == null || getTipoRelatorio().equalsIgnoreCase("1")) {
			return "reports/relatorioEstoqueTora.jasper";
		} else if (getTipoRelatorio().equalsIgnoreCase("2")) {
			return "reports/relatorioEstoqueToraDetalhe.jasper";
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_orlando_grande.png");
		parameters.put("CodigoEssencia", CodigoEssencia);
		super.doParameter(parameters);
	}
	
	@IgnoreMethodAuthentication
	public void essencias() {
		EssenciaDAO _dao = new EssenciaDAO();
		try {
			write(Utils.voToXml(_dao.getList(), _dao.getListCount()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}
}