package br.com.orlandoburli.personalerp.facades.reports.vendas.comparativovendas;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.personalerp.facades.reports.vendas.pedidos.RelatorioMetaVendasFacade;

public class RelatorioComparativoVendasFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	private Integer Mes;
	private Integer Ano;

	private String FlagTodasLojas;

	private String Lojas;
	private String NomesLojas;

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioVendasComparativo.jasper";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void doParameter(Map parameters) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

//		if (getFlagTodasLojas() == null || getFlagTodasLojas().equals("N")) {
//			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
//			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
//		} else {
//			parameters.put("CodigoEmpresa", null);
//			parameters.put("CodigoLoja", null);
//		}

		String dataStr = "01/" + Utils.fillString(getMes(), "0", 2, 1) + "/" + Ano;

		Calendar dataInicial = null;
		Calendar dataFinal = null;

		try {
			dataInicial = Utils.dateToCalendar(sdf.parse(dataStr));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		String filtroDescricao = "";
		SimpleDateFormat mesFmt = new SimpleDateFormat("MMMM/yyyy");
		filtroDescricao += mesFmt.format(Utils.calendarToDate(dataInicial));

//		if (getFlagTodasLojas().equals("S")) {
//			filtroDescricao += "\nTodas as Lojas";
//		} else {
//			filtroDescricao += "\n" + getLojasessao().getNomeLoja();
//		}

		dataFinal = (Calendar) dataInicial.clone();
		dataFinal.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));

		Calendar dataInicial2 = (Calendar) dataInicial.clone();
		dataInicial2.add(Calendar.MONTH, -3);

		Calendar dataFinal2 = (Calendar) dataFinal.clone();
		dataFinal2.add(Calendar.MONTH, -1);
		
		Calendar dataInicialMesAnterior = (Calendar) dataInicial.clone();
		dataInicialMesAnterior.add(Calendar.MONTH, -1);
		
		Calendar dataFinalMesAnterior = (Calendar) dataInicialMesAnterior.clone();
		dataFinalMesAnterior.set(Calendar.DAY_OF_MONTH, dataFinalMesAnterior.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		Calendar dataInicialAnoAnterior = (Calendar) dataInicial.clone();
		dataInicialAnoAnterior.add(Calendar.YEAR, -1);
		
		Calendar dataFinalAnoAnterior = (Calendar) dataInicialAnoAnterior.clone();
		dataFinalAnoAnterior.set(Calendar.DAY_OF_MONTH, dataFinalAnoAnterior.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		parameters.put("DataInicial", Utils.calendarToDate(dataInicial));
		parameters.put("DataFinal", Utils.calendarToDate(dataFinal));
		parameters.put("DataInicial2", Utils.calendarToDate(dataInicial2));
		parameters.put("DataFinal2", Utils.calendarToDate(dataFinal2));
		
		parameters.put("DataInicialMesAnterior", Utils.calendarToDate(dataInicialMesAnterior));
		parameters.put("DataFinalMesAnterior", Utils.calendarToDate(dataFinalMesAnterior));
		parameters.put("DataInicialAnoAnterior", Utils.calendarToDate(dataInicialAnoAnterior));
		parameters.put("DataFinalAnoAnterior", Utils.calendarToDate(dataFinalAnoAnterior));
		
		parameters.put("imageUp", SystemManager.INITIAL_APP_DIRECTORY + "reports/up.png");
		parameters.put("imageDown", SystemManager.INITIAL_APP_DIRECTORY + "reports/down.png");
		

		parameters.put("Mes", getMes());
		parameters.put("Ano", getAno());

		RelatorioMetaVendasFacade relMetas = new RelatorioMetaVendasFacade();
		relMetas.setTipoRelatorio("S"); // Somente total
		parameters.put("TipoRelatorio", "S");
		relMetas.setDataInicial(Utils.calendarToDate(dataInicial));
		relMetas.setDataFinal(Utils.calendarToDate(dataFinal));
		relMetas.setTodasLojas(getFlagTodasLojas());
		relMetas.setEmpresasessao(getEmpresasessao());
		relMetas.setLojasessao(getLojasessao());

		List<?> list = relMetas.getListDS();

		parameters.put("listMetas", list);

		// Lojas Selecionadas
		List<LojaVO> listLojas = new ArrayList<LojaVO>();
		LojaDAO lojaDao = new LojaDAO();
		
		String strLojaSelecionada = "";
		for (String s : getLojas().split("\\;")) {
			Integer codigoEmpresa = Integer.parseInt(s.split("\\.")[0]);
			Integer codigoLoja = Integer.parseInt(s.split("\\.")[1]);

			parameters.put("CodigoEmpresa", codigoEmpresa);
			parameters.put("CodigoLoja", codigoLoja);
			
			LojaVO loja = new LojaVO();
			loja.setCodigoEmpresa(codigoEmpresa);
			loja.setCodigoLoja(codigoLoja);
			
			try {
				loja = lojaDao.get(loja);
				listLojas.add(loja);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (!strLojaSelecionada.equals("")) {
				strLojaSelecionada += " UNION ALL ";
				// Se tem mais de uma, vai nulo
				parameters.put("CodigoEmpresa", null);
				parameters.put("CodigoLoja", null);
			}
			
			strLojaSelecionada += "SELECT " + codigoEmpresa + ", " + codigoLoja;
		}
		
		if (getGrupoempresasessao().getCodigoGrupoEmpresa().equals(2)) {
			// FOM
			parameters.put("intervaloColuna1", "2 month");
		} else {
			// CHILLI
			parameters.put("intervaloColuna1", "1 year");
		}
		

		parameters.put("Lojas", strLojaSelecionada);

		if (getNomesLojas() != null && !getNomesLojas().trim().equals("")) {
			setNomesLojas(getNomesLojas().substring(0, getNomesLojas().length() - 2));
		}

		parameters.put("NomesLojasSelecionadas", getNomesLojas());
		filtroDescricao += " Lojas: " + getNomesLojas();
		
		parameters.put("filtroDescricao", filtroDescricao);
		
	
		super.doParameter(parameters);
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

	public String getFlagTodasLojas() {
		return FlagTodasLojas;
	}

	public void setFlagTodasLojas(String flagTodasLojas) {
		FlagTodasLojas = flagTodasLojas;
	}

	public String getLojas() {
		return Lojas;
	}

	public void setLojas(String lojas) {
		Lojas = lojas;
	}

	public String getNomesLojas() {
		return NomesLojas;
	}

	public void setNomesLojas(String nomesLojas) {
		NomesLojas = nomesLojas;
	}
}