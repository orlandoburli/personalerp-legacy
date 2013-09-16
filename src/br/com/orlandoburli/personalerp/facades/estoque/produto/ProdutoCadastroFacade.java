package br.com.orlandoburli.personalerp.facades.estoque.produto;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.estoque.FabricanteDAO;
import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.SubGrupoDAO;
import br.com.orlandoburli.core.dao.estoque.UnidadeDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.FabricanteVO;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.SubGrupoVO;
import br.com.orlandoburli.core.vo.fiscal.TipoTributacaoVO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ProdutoCadastroFacade extends BaseCadastroFlexFacade<ProdutoVO, ProdutoDAO> {

	private static final long	serialVersionUID	= 1L;
	private Integer CodigoGrupoPesquisa;
	private Integer CodigoSubGrupoPesquisa;
	private Integer CodigoFabricantePesquisa;
	private String SiglaUnidadePesquisa;
	private Integer CodigoTipoTributacaoPesquisa;

	public ProdutoCadastroFacade() {
		super();
		this.addNewValidator(new NotEmptyValidator("DescricaoProduto", "Descrição"));
		this.addNewValidator(new NotEmptyValidator("CodigoTipoTributacao", "Tipo de Tributação"));
		this.addNewValidator(new NotEmptyValidator("SiglaUnidade", "Unidade"));
	}

	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getVo().setCodigoLoja(getLojasessao().getCodigoLoja());
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (this.getVo().getSiglaUnidade() != null && this.getVo().getSiglaUnidade().trim().equals("")) {
			this.getVo().setSiglaUnidade(null);
		}
		return super.doBeforeSave();
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
	
	@IgnoreMethodAuthentication
	public void fabricante() {
		if (CodigoFabricantePesquisa != null) {
			FabricanteDAO fabricantedao = new FabricanteDAO();
			FabricanteVO fabricante = null;
			try {
				fabricante = fabricantedao.get(CodigoFabricantePesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (fabricante != null) {
				write(Utils.voToXml(fabricante));
			} else {
				write("");
			}
		}
	}
	
	@IgnoreMethodAuthentication
	public void unidade() {
		if (SiglaUnidadePesquisa != null) {
			UnidadeDAO unidadedao = new UnidadeDAO();
			UnidadeVO unidade = null;
			try {
				unidade = unidadedao.get(SiglaUnidadePesquisa);
			} catch (SQLException e) {
				write(e.getMessage());
			}
			if (unidade != null) {
				write(Utils.voToXml(unidade));
			} else {
				write("");
			}
		}
	}
	
	@IgnoreMethodAuthentication
	public void tipotributacao() {
		if (getCodigoTipoTributacaoPesquisa() != null) {
			TipoTributacaoVO _filter = new TipoTributacaoVO();
			_filter.setCodigoTipoTributacao(getCodigoTipoTributacaoPesquisa());
			try {
				write(Utils.voToXml(getGenericDao().get(_filter)));
			} catch (SQLException e) {
				writeErrorMessage(e.getMessage());
			}
		}
	}

	public void setCodigoGrupoPesquisa(Integer codigoGrupoPesquisa) {
		CodigoGrupoPesquisa = codigoGrupoPesquisa;
	}

	public Integer getCodigoGrupoPesquisa() {
		return CodigoGrupoPesquisa;
	}

	public void setCodigoSubGrupoPesquisa(Integer codigoSubGrupoPesquisa) {
		CodigoSubGrupoPesquisa = codigoSubGrupoPesquisa;
	}

	public Integer getCodigoSubGrupoPesquisa() {
		return CodigoSubGrupoPesquisa;
	}

	public void setCodigoFabricantePesquisa(Integer codigoFabricantePesquisa) {
		CodigoFabricantePesquisa = codigoFabricantePesquisa;
	}

	public Integer getCodigoFabricantePesquisa() {
		return CodigoFabricantePesquisa;
	}

	public void setSiglaUnidadePesquisa(String siglaUnidadePesquisa) {
		SiglaUnidadePesquisa = siglaUnidadePesquisa;
	}

	public String getSiglaUnidadePesquisa() {
		return SiglaUnidadePesquisa;
	}

	public void setCodigoTipoTributacaoPesquisa(
			Integer codigoTipoTributacaoPesquisa) {
		CodigoTipoTributacaoPesquisa = codigoTipoTributacaoPesquisa;
	}

	public Integer getCodigoTipoTributacaoPesquisa() {
		return CodigoTipoTributacaoPesquisa;
	}
}