package br.com.orlandoburli.personalerp.facades.fiscal.nfentrada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.fiscal.nfentrada.ItemNFEntradaDAO;
import br.com.orlandoburli.core.dao.fiscal.nfentrada.NFEntradaDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.dao.vendas.tabelapreco.TabelaPrecoDAO;
import br.com.orlandoburli.core.utils.etiqueta.ItemImpressaoVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;
import br.com.orlandoburli.core.vo.vendas.tabelapreco.TabelaPrecoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.personalerp.facades.estoque.produto.ProdutoConsultaFacade;

public class NFEntradaConsultaFacade extends BaseConsultaFlexFacade<NFEntradaVO, NFEntradaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeEmitente(getFiltro() + "%");
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		setOrderFields("DataEmissaoNFEntrada DESC");
	}
	
	@IgnoreMethodAuthentication
	public void etiquetas() {
		ItemNFEntradaVO itemFilter = new ItemNFEntradaVO();
		
		itemFilter.setCodigoEmpresa(getFilter().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getFilter().getCodigoLoja());
		itemFilter.setCodigoNFEntrada(getFilter().getCodigoNFEntrada());

		itemFilter.setSerieNFEntrada(getFilter().getSerieNFEntrada());
		itemFilter.setCodigoEmpresaEmitenteNFEntrada(getFilter().getCodigoEmpresaEmitenteNFEntrada());
		itemFilter.setCodigoLojaEmitenteNFEntrada(getFilter().getCodigoLojaEmitenteNFEntrada());
		itemFilter.setCodigoPessoaEmitenteNFEntrada(getFilter().getCodigoPessoaEmitenteNFEntrada());
		itemFilter.setCodigoEnderecoPessoaEmitenteNFEntrada(getFilter().getCodigoEnderecoPessoaEmitenteNFEntrada());

		ItemNFEntradaDAO dao = new ItemNFEntradaDAO();
		ProdutoDAO prodDao = new ProdutoDAO();
		TabelaPrecoDAO tabDao = new TabelaPrecoDAO();
		GenericDAO _dao = new GenericDAO();
		
		try {
			Integer CodigoTabelaPreco = new ParametroLojaDAO().getIntegerParametro(Constants.Parameters.Estoque.TABELA_PRECO_PADRAO, getLojasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja());
			TabelaPrecoVO tabela = new TabelaPrecoVO();
			tabela.setCodigoTabelaPreco(CodigoTabelaPreco);
			tabela = tabDao.get(tabela);

			List<ItemNFEntradaVO> list = dao.getList(itemFilter);
			
			List<ItemImpressaoVO> listImpressao = new ArrayList<ItemImpressaoVO>();
			for (ItemNFEntradaVO item : list) {
				
				ProdutoVO produto = new ProdutoVO();
				
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				
				produto = prodDao.get(produto);

				ProdutoConsultaFacade.precoProdutoTabela(produto, getLojasessao(), tabela, _dao);
				
				listImpressao.add(new ItemImpressaoVO(produto, item.getQuantidadeItemNFEntrada().intValue()));
			}
			
			request.getSession().setAttribute("itensimpressao", listImpressao);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}