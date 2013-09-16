package br.com.orlandoburli.personalerp.facades.reports.estoque.madeireira.romaneiomadeiraserrada;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioRomaneioMadeiraSerradaFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoRomaneio;
	private String TipoRomaneio;
	
	public RelatorioRomaneioMadeiraSerradaFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected String getJasperFileName() {
		if (getTipoRomaneio() != null && getTipoRomaneio().equalsIgnoreCase(RomaneioMadeiraSerradaVO.TIPOROMANEIO_SAIDA)) {
			return "reports/relatorioRomaneioMadeiraSerradaSaida.jasper";
		} else {
			return "reports/relatorioRomaneioMadeiraSerrada.jasper";
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("imagemLogo", SystemManager.INITIAL_APP_DIRECTORY + "reports/logo_orlando_grande.png");
		parameters.put("CodigoEmpresa", getCodigoEmpresa());
		parameters.put("CodigoLoja", getCodigoLoja());
		parameters.put("CodigoRomaneio", getCodigoRomaneio());
		super.doParameter(parameters);
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	public void setTipoRomaneio(String tipoRomaneio) {
		TipoRomaneio = tipoRomaneio;
	}

	public String getTipoRomaneio() {
		return TipoRomaneio;
	}

}
