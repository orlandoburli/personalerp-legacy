package br.com.orlandoburli.personalerp.facades.reports.estoque.movimentacaoestoque;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

@IgnoreFacadeAuthentication
public class RelatorioMovimentacaoEstoqueFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private String TodasEmpresas;
	private String Estoque;
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	private Date DataInicial;
	private Date DataFinal;

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioMovimentacaoEstoque.jasper";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("Estoque", getEstoque());
		parameters.put("CodigoEmpresaProduto", getCodigoEmpresaProduto());
		parameters.put("CodigoLojaProduto", getCodigoLojaProduto());
		parameters.put("CodigoProduto", getCodigoProduto());
		
		if (getTodasEmpresas() != null && getTodasEmpresas().equals("S")) {
			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		} else {
			parameters.put("CodigoEmpresa", null);
			parameters.put("CodigoLoja", null);
		}
		
		if (getDataInicial() != null) {
			parameters.put("DataInicial", getDataInicial());
		} else {
			parameters.put("DataInicial", Date.valueOf("0001-01-01"));
		}
		if (getDataFinal() != null) {
			parameters.put("DataFinal", getDataFinal());
		} else {
			parameters.put("DataFinal", Date.valueOf("2999-12-31"));
		}
	}
	
	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			List<ProdutoVO> list = new ProdutoDAO().getList(new ProdutoVO());
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
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

	public void setTodasEmpresas(String todasEmpresas) {
		TodasEmpresas = todasEmpresas;
	}

	public String getTodasEmpresas() {
		return TodasEmpresas;
	}

	public void setEstoque(String estoque) {
		Estoque = estoque;
	}

	public String getEstoque() {
		return Estoque;
	}
}