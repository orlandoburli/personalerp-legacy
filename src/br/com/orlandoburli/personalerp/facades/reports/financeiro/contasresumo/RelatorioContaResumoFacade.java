package br.com.orlandoburli.personalerp.facades.reports.financeiro.contasresumo;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.core.dao.financeiro.contaresumo.ContaResumoDAO;
import br.com.orlandoburli.core.dao.financeiro.contaresumo.TipoContaResumoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.contaresumo.ContaResumoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioContaResumoFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private String FlgTodasLojas;
	
	private Integer Mes;
	private Integer Ano;
	private Integer CodigoTipoContaResumo;
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioContasResumo.jasper";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void doParameter(Map parameters) {
		super.doParameter(parameters);
		
		String filtroDescricao = "";
		
		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		} else {
			parameters.put("CodigoEmpresa", null);
			parameters.put("CodigoLoja", null);
		}
		
		parameters.put("CodigoTipoContaResumo", CodigoTipoContaResumo);
		
		// Busca o elemento top-level
		ContaResumoDAO crDao = new ContaResumoDAO();
		crDao.setSpecialCondition(" CodigoContaResumoPai IS NULL");
		ContaResumoVO contaresumoFilter = new ContaResumoVO();
		contaresumoFilter.setCodigoTipoContaResumo(CodigoTipoContaResumo);
		
		try {
			List<ContaResumoVO> listContaResumo = crDao.getList(contaresumoFilter, "CodigoContaResumo");
			if (listContaResumo.size() > 0) {
				parameters.put("CodigoContaResumoNivel1", listContaResumo.get(0).getCodigoContaResumo().toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Mes - 6);
		cal.set(Calendar.YEAR, Ano);
		
		for (int i = 0; i < 6; i++) {
			Date data = Utils.calendarToDate(cal);
			
//			System.out.println(data);
			
			parameters.put("mes" + (i + 1), Integer.parseInt(new SimpleDateFormat("M").format(data)));
			parameters.put("mesDesc" + (i + 1), new SimpleDateFormat("MMMM").format(data));
			parameters.put("ano" + (i + 1), Integer.parseInt(new SimpleDateFormat("yyyy").format(data)));
			
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
			
		}
		
		parameters.put("filtroDescricao", filtroDescricao);
	}
	
	@IgnoreMethodAuthentication
	public void dados() {
		try {
			write(Utils.voToXml(new TipoContaResumoDAO().getList(null, "DescricaoTipoContaResumo")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getFlgTodasLojas() {
		return FlgTodasLojas;
	}

	public void setFlgTodasLojas(String flgTodasLojas) {
		FlgTodasLojas = flgTodasLojas;
	}

	public Integer getMes() {
		return Mes;
	}

	public void setMes(Integer mes) {
		Mes = mes;
	}

	public Integer getAno() {
		return Ano;
	}

	public void setAno(Integer ano) {
		Ano = ano;
	}

	public Integer getCodigoTipoContaResumo() {
		return CodigoTipoContaResumo;
	}

	public void setCodigoTipoContaResumo(Integer codigoTipoContaResumo) {
		CodigoTipoContaResumo = codigoTipoContaResumo;
	}
}
