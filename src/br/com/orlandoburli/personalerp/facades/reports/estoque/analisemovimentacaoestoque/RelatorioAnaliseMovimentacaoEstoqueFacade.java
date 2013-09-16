package br.com.orlandoburli.personalerp.facades.reports.estoque.analisemovimentacaoestoque;

import java.sql.Date;
import java.util.Map;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioAnaliseMovimentacaoEstoqueFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	private String TodasEmpresas = "S";
	private Date DataInicial;
	private Date DataFinal;

	private Integer CodigoGrupo;
	private Integer CodigoSubGrupo;
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;

	private String Lojas;
	private String NomesLojas;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void doParameter(Map parameters) {

		super.doParameter(parameters);

		if (getTodasEmpresas() != null && getTodasEmpresas().equals("S")) {
			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
			parameters.put("NomeEmpresa", getEmpresasessao().getRazaoSocialEmpresa());
			parameters.put("NomeLoja", getLojasessao().getNomeLoja());
		} else {
			parameters.put("CodigoEmpresa", null);
			parameters.put("CodigoLoja", null);
			parameters.put("NomeEmpresa", "TODAS");
			parameters.put("NomeLoja", "TODAS");
		}

		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());
		parameters.put("CodigoGrupo", getCodigoGrupo());
		parameters.put("CodigoSubGrupo", getCodigoSubGrupo());

		parameters.put("CodigoEmpresaProduto", getCodigoEmpresaProduto());
		parameters.put("CodigoLojaProduto", getCodigoLojaProduto());
		parameters.put("CodigoProduto", getCodigoProduto());

		// Lojas Selecionadas

		String strLojaSelecionada = "";
		for (String s : getLojas().split("\\;")) {
			Integer codigoEmpresa = Integer.parseInt(s.split("\\.")[0]);
			Integer codigoLoja = Integer.parseInt(s.split("\\.")[1]);
			
			if (!strLojaSelecionada.equals("")) {
				strLojaSelecionada += " UNION ALL ";
			}
			strLojaSelecionada += "SELECT " + codigoEmpresa + ", " + codigoLoja;
		}
		
		parameters.put("LojasSelecionadas", strLojaSelecionada);
		
		if (getNomesLojas() != null && !getNomesLojas().trim().equals("")) {
			setNomesLojas(getNomesLojas().substring(0, getNomesLojas().length() - 2));
		}
		
		parameters.put("NomesLojasSelecionadas", getNomesLojas());
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioAnaliseMovimentacaoEstoque.jasper";
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public String getTodasEmpresas() {
		return TodasEmpresas;
	}

	public void setTodasEmpresas(String todasEmpresas) {
		TodasEmpresas = todasEmpresas;
	}

	public Integer getCodigoGrupo() {
		return CodigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		CodigoGrupo = codigoGrupo;
	}

	public Integer getCodigoSubGrupo() {
		return CodigoSubGrupo;
	}

	public void setCodigoSubGrupo(Integer codigoSubGrupo) {
		CodigoSubGrupo = codigoSubGrupo;
	}

	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
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
