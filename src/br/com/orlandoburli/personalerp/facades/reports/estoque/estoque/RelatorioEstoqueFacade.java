package br.com.orlandoburli.personalerp.facades.reports.estoque.estoque;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.SubGrupoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.SubGrupoVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

public class RelatorioEstoqueFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private String TodasEmpresas;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private Integer CodigoGrupo;
	private Integer CodigoSubGrupo;
	
	private Integer CodigoGrupoPesquisa;
	private Integer CodigoSubGrupoPesquisa;
	
	private String TipoRelatorio;
	private String SomenteGaveta;
	
	@OutjectSession
	private List<LojaVO> LojasRelatorioEstoque;
	
	private String Lojas;
	
	@Override
	protected String getJasperFileName() {
		if (getTipoRelatorio() != null)  {
			if (getTipoRelatorio().equals("produto")) {
				return "reports/relatorioEstoquePorProduto.jasper";
			} else if (getTipoRelatorio().equals("produto2")) {
				return "reports/relatorioEstoqueProduto2.jasper";
			} else if (getTipoRelatorio().equals("grupo")) {
				return "reports/relatorioEstoquePorGrupo.jasper";
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		System.out.println(Lojas);
		
		parameters.put("CodigoEmpresaProduto", getCodigoEmpresaProduto());
		parameters.put("CodigoLojaProduto", getCodigoLojaProduto());
		parameters.put("CodigoProduto", getCodigoProduto());
		
		parameters.put("CodigoGrupo", getCodigoGrupo());
		parameters.put("CodigoSubGrupo", getCodigoSubGrupo());
		
		parameters.put("SomenteGaveta", getSomenteGaveta());
		
		if (getTodasEmpresas() != null && getTodasEmpresas().equals("S")) {
			parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
			parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		} else {
			parameters.put("CodigoEmpresa", null);
			parameters.put("CodigoLoja", null);
		}
	}
	
	public void limparlojas() {
		this.setLojasRelatorioEstoque(new ArrayList<LojaVO>());
	}
	
	@IgnoreMethodAuthentication
	public void produtos() {
		limparlojas();
		try {
			List<ProdutoVO> list = new ProdutoDAO().getList(new ProdutoVO());
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void grupo() {
		if (CodigoGrupoPesquisa != null) {
			GrupoDAO grupodao = new GrupoDAO();
			GrupoVO grupo = null;
			try {
				grupo = grupodao.get(CodigoGrupoPesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (grupo != null) {
				write(Utils.voToXml(grupo));
			} else {
				write("");
			}
		}
	}
	
	@IgnoreMethodAuthentication
	public void subgrupo() {
		if (CodigoSubGrupoPesquisa != null) {
			SubGrupoDAO subgrupodao = new SubGrupoDAO();
			SubGrupoVO subgrupo = null;
			try {
				subgrupo = subgrupodao.get(CodigoSubGrupoPesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (subgrupo != null) {
				write(Utils.voToXml(subgrupo));
			} else {
				write("");
			}
		}
	}

	public void setTodasEmpresas(String todasEmpresas) {
		TodasEmpresas = todasEmpresas;
	}

	public String getTodasEmpresas() {
		return TodasEmpresas;
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

	public Integer getCodigoGrupoPesquisa() {
		return CodigoGrupoPesquisa;
	}

	public void setCodigoGrupoPesquisa(Integer codigoGrupoPesquisa) {
		CodigoGrupoPesquisa = codigoGrupoPesquisa;
	}

	public Integer getCodigoSubGrupoPesquisa() {
		return CodigoSubGrupoPesquisa;
	}

	public void setCodigoSubGrupoPesquisa(Integer codigoSubGrupoPesquisa) {
		CodigoSubGrupoPesquisa = codigoSubGrupoPesquisa;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}

	public void setSomenteGaveta(String somenteGaveta) {
		SomenteGaveta = somenteGaveta;
	}

	public String getSomenteGaveta() {
		return SomenteGaveta;
	}

	public List<LojaVO> getLojasRelatorioEstoque() {
		return LojasRelatorioEstoque;
	}

	public void setLojasRelatorioEstoque(List<LojaVO> lojasRelatorioEstoque) {
		LojasRelatorioEstoque = lojasRelatorioEstoque;
	}

	public String getLojas() {
		return Lojas;
	}

	public void setLojas(String lojas) {
		Lojas = lojas;
	}
}