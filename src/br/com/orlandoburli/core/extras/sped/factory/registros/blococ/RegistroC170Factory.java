package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.fiscal.nfentrada.ItemNFEntradaDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0200Factory;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC100;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC170;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.NFEntradaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;

public class RegistroC170Factory {

	private static RegistroC170Factory factory;

	private RegistroC170Factory() {

	}

	public static RegistroC170Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC170Factory();
		}
		return factory;
	}

	public void buildRegistroC170(ArquivoSpedFiscal arquivo, GenericDAO dao, NFEntradaVO nfentrada, RegistroC100 regc100) throws SQLException {
		ItemNFEntradaDAO itemNFEntradaDao = new ItemNFEntradaDAO();
		itemNFEntradaDao.mergeDAO(dao);

		ItemNFEntradaVO itemNFEntradaFilter = new ItemNFEntradaVO();
		itemNFEntradaFilter.setCodigoEmpresa(nfentrada.getCodigoEmpresa());
		itemNFEntradaFilter.setCodigoLoja(nfentrada.getCodigoLoja());
		itemNFEntradaFilter.setCodigoNFEntrada(nfentrada.getCodigoNFEntrada());
		itemNFEntradaFilter.setSerieNFEntrada(nfentrada.getSerieNFEntrada());
		itemNFEntradaFilter.setCodigoEmpresaEmitenteNFEntrada(nfentrada.getCodigoEmpresaEmitenteNFEntrada());
		itemNFEntradaFilter.setCodigoLojaEmitenteNFEntrada(nfentrada.getCodigoLojaEmitenteNFEntrada());
		itemNFEntradaFilter.setCodigoPessoaEmitenteNFEntrada(nfentrada.getCodigoPessoaEmitenteNFEntrada());
		itemNFEntradaFilter.setCodigoEnderecoPessoaEmitenteNFEntrada(nfentrada.getCodigoEnderecoPessoaEmitenteNFEntrada());

		List<ItemNFEntradaVO> list = itemNFEntradaDao.getList(itemNFEntradaFilter);

		// Adiciona os itens da nota de entrada
		arquivo.getListVo().addAll(list);

		for (ItemNFEntradaVO item : list) {
			ProdutoVO produto = getProduto(arquivo, dao, item);
			
			String codigoItem = Registro0200Factory.getFactory().buildCodigoItem(produto);

			RegistroC170 regc170 = new RegistroC170();

			regc170.setNumeroItem(item.getSequencialItemNFEntrada());
			regc170.setCodigoItem(codigoItem);
			regc170.setDescricaoComplementar(item.getDescricaoItemNFEntrada());
			regc170.setQuantidade(item.getQuantidadeItemNFEntrada());
			regc170.setUnidade(produto.getSiglaUnidade());
			
			regc170.setValorTotalItem(item.getValorTotalBrutoItemNFEntrada());
			regc170.setValorDescontoItem(item.getValorDescontoItemNFEntrada());
			regc170.setIndicadorMovimentacaoItem("1"); // TODO - Como parametrizar isso?
			regc170.setCstIcms(item.getCstIcmsItemNFEntrada());
			regc170.setCfop(item.getCfopItemNFEntrada());
			regc170.setCodigoNaturezaOperacao(""); // TODO nao sei ainda o que é este item...
			
			regc170.setValorBaseIcms(item.getBaseCalculoIcmsItemNFEntrada());
			regc170.setAliquotaIcms(item.getAliquotaIcmsItemNFEntrada());
			regc170.setValorIcms(item.getValorIcmsItemNFEntrada());
			
			regc170.setValorBaseIcmsSt(item.getBaseIcmsStItemNFEntrada());
			regc170.setAliquotaIcmsSt(item.getAliquotaIcmsSTItemNFEntrada());
			regc170.setValorIcmsSt(item.getValorIcmsSTItemNFEntrada());
			
			regc170.setIndicadorApuracaoIpi(""); // TODO ???
			
			regc170.setCstIpi(item.getCstIpiItemNFEntrada());
			regc170.setCodigoEnquadramentoIpi(""); // TODO Codigo do Enquadramento IPI, nao tenho essa informacao.
			regc170.setValorBaseIpi(item.getBaseCalculoIcmsItemNFEntrada());
			regc170.setAliquotaIpi(item.getAliquotaIpiItemNFEntrada());
			regc170.setValorIpi(item.getValorIpiItemNFEntrada());
			
			regc170.setCstPis(item.getCstPisItemNFEntrada());
			regc170.setValorBasePis(item.getBaseCalculoIcmsItemNFEntrada());
			regc170.setAliquotaPercentualPis(item.getAliquotaPisItemNFEntrada());
			regc170.setValorPis(item.getValorPisItemNFEntrada());
			
			regc170.setCstCofins(item.getCstCofinsItemNFEntrada());
			regc170.setValorBaseCofins(item.getBaseCalculoIcmsItemNFEntrada());
			regc170.setAliquotaValorCofins(item.getAliquotaCofinsItemNFEntrada());
			regc170.setValorCofins(item.getValorCofinsItemNFEntrada());
			
			regc100.getItens().add(regc170);
			
//			arquivo.addRegistro(regc170);
		}
	}

	public void buildRegistroC170(ArquivoSpedFiscal arquivo, GenericDAO dao, NFSaidaVO nfsaida, RegistroC100 registroc100) throws SQLException {
		// TODO Build do registro com itens da nota de saida
	}

	private ProdutoVO getProduto(ArquivoSpedFiscal arquivo, GenericDAO dao, ItemNFEntradaVO item) throws SQLException {

		for (IValueObject i : arquivo.getListVo()) {
			if (i instanceof ProdutoVO) {
				ProdutoVO produto = (ProdutoVO) i;
				
				if (produto.getCodigoEmpresa().equals(item.getCodigoEmpresaProduto()) && produto.getCodigoLoja().equals(item.getCodigoLojaProduto()) && produto.getCodigoProduto().equals(item.getCodigoProduto())) {
					return produto;
				}
			}
		}
		
		// Se nao achou, coloca na lista
		ProdutoDAO prodDao = new ProdutoDAO();
		prodDao.mergeDAO(dao);
		
		ProdutoVO produto = new ProdutoVO();
		produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
		produto.setCodigoLoja(item.getCodigoLojaProduto());
		produto.setCodigoProduto(item.getCodigoProduto());

		produto = prodDao.get(produto);
		
		// Verifica se a sigla da unidade já existe na lista
		boolean found = false;
		for (IValueObject i : arquivo.getListVo()) {
			if (i instanceof UnidadeVO) {
				UnidadeVO unidade = (UnidadeVO) i;
				if (unidade.getSiglaUnidade().equals(produto.getSiglaUnidade())) {
					found = true;
				}
			}
		}
		
		// Se a sigla nao foi adicionada, adiciona
		if (!found) {
			UnidadeVO unidade = new UnidadeVO();
			unidade.setSiglaUnidade(produto.getSiglaUnidade());
			unidade = (UnidadeVO) dao.get(unidade);
			
			arquivo.getListVo().add(unidade);
		}
		
		arquivo.getListVo().add(produto);
		
		return produto;
	}
}