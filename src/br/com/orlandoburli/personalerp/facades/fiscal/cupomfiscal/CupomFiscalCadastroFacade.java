package br.com.orlandoburli.personalerp.facades.fiscal.cupomfiscal;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.fiscal.cupomfiscal.CupomFiscalDAO;
import br.com.orlandoburli.core.dao.fiscal.cupomfiscal.ItemCupomFiscalDAO;
import br.com.orlandoburli.core.extras.cupom.exceptions.CupomException;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.fiscal.OperacaoTributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.TributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.cupomfiscal.CupomFiscalVO;
import br.com.orlandoburli.core.vo.fiscal.cupomfiscal.ItemCupomFiscalVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class CupomFiscalCadastroFacade extends BaseCadastroFlexFacade<CupomFiscalVO, CupomFiscalDAO>{

	private static final String OPERACAO_SAIDA_CUPOM_FISCAL = "SCF";
	private static final long serialVersionUID = 1L;

	public static void validarCupomFiscal(GenericDAO _dao, CupomFiscalVO vo, LojaVO loja) throws SQLException, CupomException {
		try {
			_dao.setAutoCommit(false);
			
			vo = (CupomFiscalVO) _dao.get(vo);
			
			BigDecimal cem = new BigDecimal(100);
			
			ItemCupomFiscalVO filter = new ItemCupomFiscalVO();
			filter.setCodigoEmpresa(vo.getCodigoEmpresa());
			filter.setCodigoLoja(vo.getCodigoLoja());
			filter.setCodigoCupomFiscal(vo.getCodigoCupomFiscal());
			filter.setSerieCupomFiscal(vo.getSerieCupomFiscal());
			filter.setCodigoPdv(vo.getCodigoPdv());
			
			ItemCupomFiscalDAO itemDao = new ItemCupomFiscalDAO();
			
			List<ItemCupomFiscalVO> list = itemDao.getList(filter);
			
			// Zera os totais
			BigDecimal zero = BigDecimal.ZERO;
			vo.setValorTotalItensCupomFiscal(zero);
			vo.setValorDescontoCupomFiscal(zero);
			
			vo.setValorTotalIpiCupomFiscal(zero);
			vo.setValorTotalPisCupomFiscal(zero);
			vo.setValorTotalCofinsCupomFiscal(zero);
			vo.setValorTotalIcmsCupomFiscal(zero);
			vo.setValorTotalCupomFiscal(zero);
			
			OperacaoTributacaoVO operacao = new OperacaoTributacaoVO();
			operacao.setOperacaoTributacao(OPERACAO_SAIDA_CUPOM_FISCAL);
			operacao = (OperacaoTributacaoVO) _dao.get(operacao);
			
			if (operacao == null) {
				throw new CupomException("Operação " + OPERACAO_SAIDA_CUPOM_FISCAL + " não encontrada!");
			}
			
			for (ItemCupomFiscalVO item : list) {
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				
				produto = (ProdutoVO) _dao.get(produto);
				
				if (produto.getCodigoTipoTributacao() == null) {
					throw new CupomException("O produto \n[" + produto.getDescricaoProduto() + "] \n código " + produto.getCodigoProduto() + " não está configurado quanto ao tipo de tributação!");
				}
				
				TributacaoVO tributacao = new TributacaoVO();
				tributacao.setCodigoTipoTributacao(produto.getCodigoTipoTributacao());
				tributacao.setUFOrigemTributacao(loja.getUFLoja());
				tributacao.setUFDestinoTributacao("MT");
				tributacao.setOperacaoTributacao(OPERACAO_SAIDA_CUPOM_FISCAL);
				
				tributacao = (TributacaoVO) _dao.get(tributacao);
				
				// Se nao achou especifico para este estado, tenta para todos os estados
				if (tributacao == null) {
					tributacao = new TributacaoVO();
					tributacao.setCodigoTipoTributacao(produto.getCodigoTipoTributacao());
					tributacao.setUFOrigemTributacao(loja.getUFLoja());
					tributacao.setUFDestinoTributacao("XX");
					tributacao.setOperacaoTributacao(OPERACAO_SAIDA_CUPOM_FISCAL);
					
					tributacao = (TributacaoVO) _dao.get(tributacao);
				}
				
				if (tributacao == null) {
					_dao.rollback();
					throw new CupomException("Configuração de tributação não encontrada\npara a operação [" + operacao.getDescricaoOperacaoTributacao() + "]\npara emitente " + loja.getUFLoja() + "\ne destinatário MT ou Todos!");
				}
				
				item.setCstIcmsItemCupomFiscal(tributacao.getClassificacaoTributariaIcmsTributacao());
				item.setCstIpiItemCupomFiscal(tributacao.getCstIpiTributacao());
				item.setCstCofinsItemCupomFiscal(tributacao.getCstCofinsTributacao());
				item.setCstPisItemCupomFiscal(tributacao.getCstPisTributacao());
				
				item.setCfopItemCupomFiscal(tributacao.getCfopTributacao());
				
				item.setAliquotaIcmsItemCupomFiscal(tributacao.getAliquotaIcmsTributacao());
				item.setAliquotaPisItemCupomFiscal(tributacao.getAliquotaPisTributacao());
				item.setAliquotaCofinsItemCupomFiscal(tributacao.getAliquotaCofinsTributacao());
				item.setAliquotaIpiItemCupomFiscal(tributacao.getAliquotaIpiTributacao());
				
				if (item.getValorDescontoItemCupomFiscal() == null) {
					item.setValorDescontoItemCupomFiscal(zero);
				}
				if (item.getValorAcrescimoItemCupomFiscal() == null) {
					item.setValorAcrescimoItemCupomFiscal(zero);
				}
				
				// COFINS
				item.setValorCofinsItemCupomFiscal(item.getValorLiquidoItemCupomFiscal()
						.multiply(item.getAliquotaCofinsItemCupomFiscal())
						.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN));
				
				// PIS
				item.setValorPisItemCupomFiscal(item.getValorLiquidoItemCupomFiscal()
						.multiply(item.getAliquotaPisItemCupomFiscal())
						.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN));
				
				// IPI
				item.setValorIpiItemCupomFiscal(item.getValorLiquidoItemCupomFiscal()
						.multiply(item.getAliquotaIpiItemCupomFiscal())
						.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN));
				
				// ICMS
				if (tributacao.getFormaCalculoIcmsTributacao().equals("NT")) {
					// Não tributado
					item.setValorBaseIcmsItemCupomFiscal(zero);
					item.setValorIcmsItemCupomFiscal(zero);
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("ST")) {
					
					// Substituicao Tributaria
//					item.setBaseCalculoIcmsItemCupomFiscal(item.getValorTotalLiquidoItemCupomFiscal());
//					item.setBaseIcmsStItemCupomFiscal(item.getValorTotalLiquidoItemCupomFiscal());
//					
					// Verifica se o IPI faz parte da base de cálculo (antes da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("A")) {
						item.setValorBaseIcmsItemCupomFiscal(item.getValorBaseIcmsItemCupomFiscal().add(item.getValorIpiItemCupomFiscal()));
					}
					
					// Aliquotas de ICMS
					item.setAliquotaIcmsItemCupomFiscal(tributacao.getAliquotaIcmsTributacao());
					
					if (tributacao.getReducaoBaseCalculoIcmsTributacao() != null && tributacao.getReducaoBaseCalculoIcmsTributacao().compareTo(zero) > 0) {
						item.setValorBaseIcmsItemCupomFiscal(
								item.getValorBaseIcmsItemCupomFiscal()
								.subtract(item.getValorBaseIcmsItemCupomFiscal()
								.multiply(tributacao.getReducaoBaseCalculoIcmsTributacao())
								.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN)));
					}
					
					// Verifica se o IPI faz parte da base de cálculo (depois da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("D")) {
						item.setValorBaseIcmsItemCupomFiscal(item.getValorBaseIcmsItemCupomFiscal().add(item.getValorIpiItemCupomFiscal()));
					}
					
					// Verifica se Outras Despesas fazem parte da base de cálculo (depois da reducao)...
					
					// Valor do ICMS
					item.setValorIcmsItemCupomFiscal(item.getAliquotaIcmsItemCupomFiscal()
							.multiply(item.getValorBaseIcmsItemCupomFiscal())
							.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN));
					
					// Valor do ICMS ST
					// A base de calculo é aumentada pelo MVA (Margem do Valor Agregado)
					
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("GN")) {
					// TODO Garantido Normal 
//					item.setBaseCalculoIcmsItemCupomFiscal(item.getValorTotalLiquidoItemCupomFiscal());
//					item.setValorIcmsItemCupomFiscal(item.getAliquotaIcmsItemCupomFiscal().multiply(item.getBaseCalculoIcmsItemCupomFiscal()));
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("GI")) {
					// TODO Garantido Integral
//					item.setBaseCalculoIcmsItemCupomFiscal(item.getValorTotalLiquidoItemCupomFiscal());
//					item.setValorIcmsItemCupomFiscal(item.getAliquotaIcmsItemCupomFiscal().multiply(item.getBaseCalculoIcmsItemCupomFiscal()));
				} else if (tributacao.getFormaCalculoIcmsTributacao().equals("TI")) {
					// Tributacao Integral
					
					item.setValorBaseIcmsItemCupomFiscal(item.getValorLiquidoItemCupomFiscal());
					
					// Verifica se o IPI faz parte da base de cálculo (antes da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("A")) {
						item.setValorBaseIcmsItemCupomFiscal(item.getValorBaseIcmsItemCupomFiscal()
								.add(item.getValorIpiItemCupomFiscal()));
					}
					
					// Verifica se tem redução na base de calculo
					if (tributacao.getReducaoBaseCalculoIcmsTributacao() != null && 
						tributacao.getReducaoBaseCalculoIcmsTributacao().compareTo(zero) > 0) {
						item.setValorBaseIcmsItemCupomFiscal(
								item.getValorBaseIcmsItemCupomFiscal()
								.subtract(item.getValorBaseIcmsItemCupomFiscal()
								.multiply(tributacao.getReducaoBaseCalculoIcmsTributacao())
								.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN)));
					}
					
					// Verifica se o IPI faz parte da base de cálculo (antes da reducao)...
					if (tributacao.getIpiBaseIcmsTributacao().equals("D")) {
						item.setValorBaseIcmsItemCupomFiscal(item.getValorBaseIcmsItemCupomFiscal()
								.add(item.getValorIpiItemCupomFiscal()));
					}
					
					item.setValorIcmsItemCupomFiscal(item.getAliquotaIcmsItemCupomFiscal()
							.multiply(item.getValorBaseIcmsItemCupomFiscal())
							.divide(cem, 2, BigDecimal.ROUND_HALF_EVEN));
				}
				
				if (item.getValorIcmsItemCupomFiscal() != null) {
					vo.setValorTotalIcmsCupomFiscal(vo.getValorTotalIcmsCupomFiscal().add(item.getValorIcmsItemCupomFiscal()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				
				if (item.getValorIpiItemCupomFiscal() != null) {
					vo.setValorTotalIpiCupomFiscal(vo.getValorTotalIpiCupomFiscal().add(item.getValorIpiItemCupomFiscal()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				
				if (item.getValorPisItemCupomFiscal() != null) {
					vo.setValorTotalPisCupomFiscal(vo.getValorTotalPisCupomFiscal().add(item.getValorPisItemCupomFiscal()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				
				if (item.getValorCofinsItemCupomFiscal() != null) {
					vo.setValorTotalCofinsCupomFiscal(vo.getValorTotalCofinsCupomFiscal().add(item.getValorCofinsItemCupomFiscal()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				}
				
				vo.setValorTotalItensCupomFiscal(vo.getValorTotalItensCupomFiscal().add(item.getValorTotalItemCupomFiscal()));
				vo.setValorDescontoCupomFiscal(vo.getValorDescontoCupomFiscal().add(item.getValorDescontoItemCupomFiscal()));
				vo.setValorAcrescimoCupomFiscal(vo.getValorAcrescimoCupomFiscal().add(item.getValorAcrescimoItemCupomFiscal()));
				vo.setValorTotalCupomFiscal(vo.getValorTotalCupomFiscal().add(item.getValorLiquidoItemCupomFiscal()));
				
				//vo.setValorTotalCupomFiscal();
				_dao.persist(item);
			}
			
			_dao.persist(vo);
			
			_dao.commit();
			
		} catch (SQLException e) {
			_dao.rollback();
			throw e;
		}
	}
}
