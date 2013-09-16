package br.com.orlandoburli.personalerp.facades.reports.estoque.madeireira.entradatora;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioEntradaToraFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	private Integer CodigoEssencia;
	private Date DataInicial;
	private Date DataFinal;
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioEntradaTora.jasper";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_orlando_grande.png");
		parameters.put("CodigoEssencia", getCodigoEssencia());
		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());
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

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
	}
}
