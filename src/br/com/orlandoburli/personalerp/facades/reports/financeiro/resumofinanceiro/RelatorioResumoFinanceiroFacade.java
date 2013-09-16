package br.com.orlandoburli.personalerp.facades.reports.financeiro.resumofinanceiro;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.utils.TipoSaidaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioResumoFinanceiroFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private Date DataInicialVencimento;
	private Date DataFinalVencimento;

	private String FlgTodasLojas;
	private String FlgPesoDespesa;
	private String FlgContasOcultas;
	
	private String Lojas;
	private String NomesLojas;

	public RelatorioResumoFinanceiroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	@IgnoreMethodAuthentication
	public void dados() {
		write(Utils.voToXml(TipoSaidaVO.getListSaida()));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		String quebralinha = "          ";
		String filtroDescricao = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (getFlgTodasLojas() != null && (getFlgTodasLojas().equals("G") || getFlgTodasLojas().equals("N"))) {
			filtroDescricao += "Grupo de Empresas: " + getGrupoempresasessao().getNomeGrupoEmpresa();
		} else {
			filtroDescricao += "Grupo de Empresas: TODOS" ;
		}
		
//		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
//			filtroDescricao += " Loja: " + getLojasessao().getNomeLoja();
//		} else {
//			filtroDescricao += " Loja: TODAS";
//		}
		
		if (DataInicialVencimento != null) {
			filtroDescricao += " Vencto. Inicial: " + sdf.format(DataInicialVencimento) + quebralinha;
		}
		
		if (DataFinalVencimento != null) {
			filtroDescricao += " Vencto. Final: " + sdf.format(DataFinalVencimento) + quebralinha;
		}
		
		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
			if (getFlgPesoDespesa() == null || getFlgPesoDespesa().equals("1")) {
				filtroDescricao += "\nPeso calculado sobre receita da propria loja";
			} else {
				filtroDescricao += "\nPeso calculado sobre receita global";
			}
		}
		
		parameters.put("DataInicial", getDataInicialVencimento());
		parameters.put("DataFinal", getDataFinalVencimento());
		
//		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
//			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
//			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
//			
//			if (getFlgPesoDespesa() == null || getFlgPesoDespesa().equals("1")) {
//				parameters.put("CodigoEmpresaGlobal", getEmpresasessao().getCodigoEmpresa());
//				parameters.put("CodigoLojaGlobal", getLojasessao().getCodigoLoja());
//			} else {
//				parameters.put("CodigoEmpresaGlobal", null);
//				parameters.put("CodigoLojaGlobal", null);
//			}
//		} else {
//			parameters.put("CodigoEmpresa", null);
//			parameters.put("CodigoLoja", null);
//			parameters.put("CodigoEmpresaGlobal", null);
//			parameters.put("CodigoLojaGlobal", null);
//		}
		
		if (getFlgTodasLojas() != null && (getFlgTodasLojas().equals("G") || getFlgTodasLojas().equals("N"))) {
			parameters.put("CodigoGrupoEmpresa", getGrupoempresasessao().getCodigoGrupoEmpresa());
		} else {
			parameters.put("CodigoGrupoEmpresa", null);
		}
		
		parameters.put("FlgContasOcultas", getFlgContasOcultas());
		
		// Lojas Selecionadas
		List<LojaVO> listLojas = new ArrayList<LojaVO>();
		LojaDAO lojaDao = new LojaDAO();
		
		String strLojaSelecionada = "";
		for (String s : getLojas().split("\\;")) {
			Integer codigoEmpresa = Integer.parseInt(s.split("\\.")[0]);
			Integer codigoLoja = Integer.parseInt(s.split("\\.")[1]);

			parameters.put("CodigoEmpresa", codigoEmpresa);
			parameters.put("CodigoLoja", codigoLoja);
			
			if (getFlgPesoDespesa() == null || getFlgPesoDespesa().equals("1")) {
				parameters.put("CodigoEmpresaGlobal", getEmpresasessao().getCodigoEmpresa());
				parameters.put("CodigoLojaGlobal", getLojasessao().getCodigoLoja());
			} else {
				parameters.put("CodigoEmpresaGlobal", null);
				parameters.put("CodigoLojaGlobal", null);
			}
			
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
				parameters.put("CodigoEmpresaGlobal", null);
				parameters.put("CodigoLojaGlobal", null);
			}
			
			strLojaSelecionada += "SELECT " + codigoEmpresa + ", " + codigoLoja;
			
			parameters.put("Lojas", strLojaSelecionada);

			if (getNomesLojas() != null && !getNomesLojas().trim().equals("")) {
				setNomesLojas(getNomesLojas().substring(0, getNomesLojas().length() - 2));
			}
		}
		
		parameters.put("NomesLojasSelecionadas", getNomesLojas());
		filtroDescricao += " Lojas: " + getNomesLojas();
		
		parameters.put("filtroDescricao", filtroDescricao);
		
		
		super.doParameter(parameters);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioResumoFinanceiro.jasper";
	}

	public Date getDataInicialVencimento() {
		return DataInicialVencimento;
	}

	public void setDataInicialVencimento(Date dataInicialVencimento) {
		DataInicialVencimento = dataInicialVencimento;
	}

	public Date getDataFinalVencimento() {
		return DataFinalVencimento;
	}

	public void setDataFinalVencimento(Date dataFinalVencimento) {
		DataFinalVencimento = dataFinalVencimento;
	}


	public String getFlgTodasLojas() {
		return FlgTodasLojas;
	}

	public void setFlgTodasLojas(String flgTodasLojas) {
		FlgTodasLojas = flgTodasLojas;
	}


	public String getFlgPesoDespesa() {
		return FlgPesoDespesa;
	}


	public void setFlgPesoDespesa(String flgPesoDespesa) {
		FlgPesoDespesa = flgPesoDespesa;
	}


	public String getFlgContasOcultas() {
		return FlgContasOcultas;
	}


	public void setFlgContasOcultas(String flgContasOcultas) {
		FlgContasOcultas = flgContasOcultas;
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