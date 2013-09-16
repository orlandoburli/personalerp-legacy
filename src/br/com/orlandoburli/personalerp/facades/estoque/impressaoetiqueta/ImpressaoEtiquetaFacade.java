package br.com.orlandoburli.personalerp.facades.estoque.impressaoetiqueta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.utils.etiqueta.ItemImpressaoVO;
import br.com.orlandoburli.core.utils.etiqueta.impressoras.IModelImpressoraEtiqueta;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloEtiquetaVO;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloImpressoraEtiquetaVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.vendas.tabelapreco.TabelaPrecoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.personalerp.facades.estoque.produto.ProdutoConsultaFacade;

@IgnoreFacadeAuthentication
public class ImpressaoEtiquetaFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	@OutjectSession
	private List<ItemImpressaoVO> itensimpressao;

	private Integer CodigoModeloEtiqueta;

	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	private Integer QuantidadeEtiquetas;
	private Integer Posicao;

	private EmpresaVO empresasessao;
	private LojaVO lojasessao;

	public ImpressaoEtiquetaFacade() {
		super();
		this.setItensimpressao(new ArrayList<ItemImpressaoVO>());
	}

	public void execute() {
		GenericDAO dao = new GenericDAO();
		dao.setAutoCommit(false);
		try {

			ModeloEtiquetaVO modelo = new ModeloEtiquetaVO();
			modelo.setCodigoModeloEtiqueta(getCodigoModeloEtiqueta());

			modelo = (ModeloEtiquetaVO) dao.get(modelo);
			if (modelo == null) {
				writeErrorMessage("Modelo de etiqueta não encontrado!");
				return;
			}

			ModeloImpressoraEtiquetaVO impressora = new ModeloImpressoraEtiquetaVO();
			impressora.setCodigoModeloImpressoraEtiqueta(modelo.getCodigoModeloImpressoraEtiqueta());

			impressora = (ModeloImpressoraEtiquetaVO) dao.get(impressora);
			if (impressora == null) {
				writeErrorMessage("Tipo de impressora não encontrada!");
			}

			// Geracao dinamica da classe
			Class<?> klass = Class.forName(impressora.getClasseModeloImpressoraEtiqueta());
			Object o = klass.newInstance();

			if (!(o instanceof IModelImpressoraEtiqueta)) {
				writeErrorMessage("Ocorreu um erro ao iniciar impressora,\nentre em contato com o administrador do sistema!\n(Código 01)");
				return;
			}

			IModelImpressoraEtiqueta modelImpressora = (IModelImpressoraEtiqueta) o;

			// Envia para a impressora
			modelImpressora.imprimir(getItensimpressao(), modelo);

			write("ok");

		} catch (SQLException e) {
			writeErrorMessage("Ocorreu um erro no banco de dados,\nentre em contato com o administrado do sistema!\n(Código 02)");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			writeErrorMessage("Ocorreu um erro ao iniciar impressora,\nentre em contato com o administrado do sistema!\n(Código 03)");
			e.printStackTrace();
		} catch (InstantiationException e) {
			writeErrorMessage("Ocorreu um erro ao iniciar impressora,\nentre em contato com o administrado do sistema!\n(Código 04)");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			writeErrorMessage("Ocorreu um erro ao iniciar impressora,\nentre em contato com o administrado do sistema!\n(Código 05)");
			e.printStackTrace();
		} finally {
			try {
				dao.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@IgnoreMethodAuthentication
	public void dados() {
		try {
			List<IValueObject> list = new ArrayList<IValueObject>();
			GenericDAO dao = new GenericDAO();
			ProdutoVO produtoFilter = new ProdutoVO();
			produtoFilter.setSituacaoProduto("N");

			list.addAll(dao.getList(produtoFilter));
			
			list.addAll(dao.getList(new ModeloEtiquetaVO()));
			
			write(Utils.voToXml(list));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@IgnoreMethodAuthentication
	public void adicionaritem() {
		try {
			GenericDAO dao = new GenericDAO();

			// Tabela de preco padrao do sistema
			Integer CodigoTabelaPreco = new ParametroLojaDAO().getIntegerParametro(Constants.Parameters.Estoque.TABELA_PRECO_PADRAO, getLojasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja());
			TabelaPrecoVO tabela = new TabelaPrecoVO();
			tabela.setCodigoTabelaPreco(CodigoTabelaPreco);
			tabela = (TabelaPrecoVO) dao.get(tabela);

			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(getCodigoEmpresaProduto());
			produto.setCodigoLoja(getCodigoLojaProduto());
			produto.setCodigoProduto(getCodigoProduto());

			produto = (ProdutoVO) dao.get(produto);

			ProdutoConsultaFacade.precoProdutoTabela(produto, getLojasessao(), tabela, dao);

			if (produto == null) {
				return;
			}

			this.getItensimpressao().add(new ItemImpressaoVO(produto, getQuantidadeEtiquetas()));
			
			write("ok");
			
		} catch (SQLException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}

	@IgnoreMethodAuthentication
	public void listaritens() {
		write(Utils.voToXml(getItensimpressao()));
	}

	@IgnoreMethodAuthentication
	public void removeritem() {
		this.itensimpressao.remove(getPosicao().intValue());
		write("ok");
	}

	@IgnoreMethodAuthentication
	public void limparitens() {
		System.out.println("Limpando...");
		this.itensimpressao = new ArrayList<ItemImpressaoVO>();
		listaritens();
	}

	@IgnoreMethodAuthentication
	public void teste() {
		limparitens();

		setCodigoEmpresaProduto(3);
		setCodigoLojaProduto(1);
		setCodigoProduto(493);
		setQuantidadeEtiquetas(2);
		adicionaritem();

		setCodigoEmpresaProduto(3);
		setCodigoLojaProduto(1);
		setCodigoProduto(468);
		setQuantidadeEtiquetas(3);
		adicionaritem();

		setCodigoEmpresaProduto(3);
		setCodigoLojaProduto(1);
		setCodigoProduto(473);
		setQuantidadeEtiquetas(3);
		adicionaritem();

		setCodigoEmpresaProduto(3);
		setCodigoLojaProduto(1);
		setCodigoProduto(503);
		setQuantidadeEtiquetas(4);
		adicionaritem();

		setCodigoModeloEtiqueta(1);
		execute();
	}

	public Integer getCodigoModeloEtiqueta() {
		return CodigoModeloEtiqueta;
	}

	public void setCodigoModeloEtiqueta(Integer codigoModeloEtiqueta) {
		CodigoModeloEtiqueta = codigoModeloEtiqueta;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public Integer getQuantidadeEtiquetas() {
		return QuantidadeEtiquetas;
	}

	public void setQuantidadeEtiquetas(Integer quantidadeEtiquetas) {
		QuantidadeEtiquetas = quantidadeEtiquetas;
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

	public List<ItemImpressaoVO> getItensimpressao() {
		return itensimpressao;
	}

	public void setItensimpressao(List<ItemImpressaoVO> itensimpressao) {
		this.itensimpressao = itensimpressao;
	}

	public Integer getPosicao() {
		return Posicao;
	}

	public void setPosicao(Integer posicao) {
		Posicao = posicao;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}
}
