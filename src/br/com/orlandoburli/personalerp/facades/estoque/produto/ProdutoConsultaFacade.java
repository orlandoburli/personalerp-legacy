package br.com.orlandoburli.personalerp.facades.estoque.produto;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.fiscal.TributacaoDAO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.vendas.tabelapreco.TabelaPrecoProdutoVO;
import br.com.orlandoburli.core.vo.vendas.tabelapreco.TabelaPrecoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ProdutoConsultaFacade extends BaseConsultaFlexFacade<ProdutoVO, ProdutoDAO> {

	private static final long serialVersionUID	= 1L;
	private String Tipo;
	
	private EstoqueDAO estoquedao;
	private TributacaoDAO tributacaodao;
	
	@Override
	protected void doBeforeFilter() {
		if (getTipo().equalsIgnoreCase("D")) { // Pesquisa pela descrição
			getFilter().setDescricaoProduto(getFiltro() + "%");
		} else if (getTipo().equalsIgnoreCase("I")) { // Código Interno
			Integer codProduto = null;
			try {
				codProduto = Integer.parseInt(getFiltro().trim());
			} catch (Exception ex) {}
			getFilter().setCodigoProduto(codProduto);
		} else if (getTipo().equalsIgnoreCase("R")) { // Código de Referência
			getFilter().setCodigoReferenciaProduto(getFiltro() + "%");
		}
		if (getOrderFields() == null || getOrderFields().trim().equals("")) {
			setOrderFields("DescricaoProduto");
		}
		dao.setSpecialCondition(" TipoEstoqueProduto IN ('E', 'V')");
	}
	
	@Override
	public void setListSource(List<ProdutoVO> listSource) {
		super.setListSource(listSource);
//		List<ProdutoVO> list = super.getListSource();
//		for (ProdutoVO produto : list) {
//			try {
////				calcularPrecoProduto(produto);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
//	public static void calcularPrecoProduto(ProdutoVO produto) throws SQLException {
//		// Somente produtos para venda, não os de consumo interno
//		if (produto != null && produto.getTipoEstoqueProduto() != null && produto.getTipoEstoqueProduto().equalsIgnoreCase("V")) {
//			// Busca estoque e preço
//			EstoqueVO estoque = getEstoquedao().get(new Object[]{produto.getCodigoEmpresa(), produto.getCodigoLoja(), produto.getCodigoProduto(), getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()});
//			if (estoque != null) {
//				// Busca percentual ICMS
//				TributacaoVO tributacao = getTributacaodao().get(new Object[]{produto.getCodigoTipoTributacao(), getLojasessao().getRegiaoLoja()});
//				
//				if (tributacao != null) {
//					// Calcula preço de venda (atacado)
//					BigDecimal aliquotaIcms = null;
//					BigDecimal cem = new BigDecimal(100);
//					
////					if (tributacao.getAliquotaIcmsSaidaContribuinteTributacao() != null && tributacao.getAliquotaIcmsSaidaContribuinteTributacao().compareTo(BigDecimal.ZERO) > 0) {
////						aliquotaIcms = tributacao.getAliquotaIcmsSaidaContribuinteTributacao();
////					} else if (tributacao.getIcmsGarantidoSaidaContribuinteTributacao() != null && tributacao.getIcmsGarantidoSaidaContribuinteTributacao().compareTo(BigDecimal.ZERO) > 0) {
////						aliquotaIcms = tributacao.getIcmsGarantidoSaidaContribuinteTributacao();
////					}
////					
//					BigDecimal precoMedio = estoque.getPrecoMedioEstoque()==null?BigDecimal.ZERO:estoque.getPrecoMedioEstoque();
//					BigDecimal custo = aliquotaIcms.divide(cem).add(BigDecimal.ONE).multiply(precoMedio).setScale(2, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal margemLucro = produto.getMargemLucroAtacadoProduto()==null?BigDecimal.ZERO:produto.getMargemLucroAtacadoProduto();
//					BigDecimal precoVenda = custo.divide((BigDecimal.ONE.subtract(margemLucro.divide(cem)))).setScale(2, BigDecimal.ROUND_HALF_EVEN);//   custo / (1 - (margemLucro / 100));
//					
//					produto.setValorVendaAtacadoProduto(precoVenda);
//					
//					// Calcula preco de venda (varejo)
//					aliquotaIcms = null;
////					if (tributacao.getAliquotaIcmsSaidaNaoContribuinteTributacao() != null && tributacao.getAliquotaIcmsSaidaNaoContribuinteTributacao().compareTo(BigDecimal.ZERO) > 0) {
////						aliquotaIcms = tributacao.getAliquotaIcmsSaidaNaoContribuinteTributacao();
////					} else if (tributacao.getIcmsGarantidoSaidaNaoContribuinteTributacao() != null && tributacao.getIcmsGarantidoSaidaNaoContribuinteTributacao().compareTo(BigDecimal.ZERO) > 0) {
////						aliquotaIcms = tributacao.getIcmsGarantidoSaidaNaoContribuinteTributacao();
////					}
//					
//					custo = aliquotaIcms.divide(cem).add(BigDecimal.ONE).multiply(precoMedio).setScale(2, BigDecimal.ROUND_HALF_EVEN);
//					margemLucro = produto.getMargemLucroVarejoProduto()==null?BigDecimal.ZERO:produto.getMargemLucroVarejoProduto();
//					precoVenda = custo.divide((BigDecimal.ONE.subtract(margemLucro.divide(cem)))).setScale(2, BigDecimal.ROUND_HALF_EVEN);//   custo / (1 - (margemLucro / 100));
//					
//					produto.setValorVendaVarejoProduto(precoVenda);
//					
//					return;
//				}
//			}
//		}
//		produto.setValorVendaAtacadoProduto(BigDecimal.ZERO);
//		produto.setValorVendaVarejoProduto(BigDecimal.ZERO);
//	}

	/**
	 * Busca o preco do produto na tabela
	 * @param produto Produto a ser procurado
	 * @param loja
	 * @param tabela
	 * @param dao
	 */
	public static void precoProdutoTabela(ProdutoVO produto, LojaVO loja, TabelaPrecoVO tabela, GenericDAO dao) throws SQLException {
		TabelaPrecoProdutoVO tabProd = new TabelaPrecoProdutoVO();
		
		tabProd.setCodigoEmpresa(loja.getCodigoEmpresa());
		tabProd.setCodigoLoja(loja.getCodigoLoja());
		tabProd.setCodigoTabelaPreco(tabela.getCodigoTabelaPreco());
		tabProd.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
		tabProd.setCodigoLojaProduto(produto.getCodigoLoja());
		tabProd.setCodigoProduto(produto.getCodigoProduto());
		
		tabProd = (TabelaPrecoProdutoVO) dao.get(tabProd);
		
		if (tabProd != null) {
			produto.setValorVendaAtacadoProduto(tabProd.getPrecoVenda());
			produto.setValorVendaVarejoProduto(tabProd.getPrecoVenda());
		}
		
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return ProdutoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ProdutoVO.class;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getTipo() {
		return Tipo;
	}
	
	public void setEstoquedao(EstoqueDAO estoquedao) {
		this.estoquedao = estoquedao;
	}

	public EstoqueDAO getEstoquedao() {
		if (estoquedao == null) {
			this.estoquedao = new EstoqueDAO();
		}
		return estoquedao;
	}

	public void setTributacaodao(TributacaoDAO tributacaodao) {
		this.tributacaodao = tributacaodao;
	}

	public TributacaoDAO getTributacaodao() {
		if (tributacaodao == null) {
			this.tributacaodao = new TributacaoDAO();
		}
		return tributacaodao;
	}
}