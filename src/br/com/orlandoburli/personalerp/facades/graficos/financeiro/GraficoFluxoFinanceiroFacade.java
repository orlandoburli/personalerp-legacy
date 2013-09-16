package br.com.orlandoburli.personalerp.facades.graficos.financeiro;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.BaixaContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.BaixaContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class GraficoFluxoFinanceiroFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	private Date DataInicial;
	private Date DataFinal;

	public void execute() {
		PlanoContaDAO planoDao = new PlanoContaDAO();
		
		try {
			StringBuilder sb = new StringBuilder();
			
			sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><contas>");
			
			planoDao.setAutoCommit(false);
			planoDao.setSpecialCondition("CodigoPlanoContaPai IS NULL");
			List<PlanoContaVO> listRaiz = planoDao.getList(new PlanoContaVO(), "NumeroPlanoConta");
			planoDao.setSpecialCondition(null);
			
			for (PlanoContaVO item : listRaiz) {
				sb.append("<planoconta");
				sb.append(" NumeroPlanoConta=\"" + item.getNumeroPlanoConta() + "\"");
				sb.append(" NomePlanoConta=\"" + item.getNomePlanoConta() + "\"");
				sb.append(" ValorCreditoPlanoConta=\"" + getTotalCreditoPlanoConta(item) + "\"");
				sb.append(" ValorDebitoPlanoConta=\"" + getTotalDebitoPlanoConta(item) + "\"");
				sb.append(">");
				sb.append(addChild(item, planoDao));
				sb.append("</planoconta>");
			}
			
			sb.append("</contas>");
			
			planoDao.commit();
			
			writeIso88591(sb.toString());
			
		} catch (SQLException e) {
			planoDao.rollback();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String addChild(PlanoContaVO planoPai, PlanoContaDAO planoDao) throws SQLException {
		StringBuilder sb = new StringBuilder();
		// Busca os planos filhos
		PlanoContaVO filter = new PlanoContaVO();
		filter.setCodigoPlanoContaPai(planoPai.getCodigoPlanoConta());
		List<PlanoContaVO> list = planoDao.getList(filter, "NumeroPlanoConta");
		
		for (PlanoContaVO item : list) {
			
			sb.append("<planoconta");
			sb.append(" NumeroPlanoConta=\"" + item.getNumeroPlanoConta() + "\"");
			sb.append(" NomePlanoConta=\"" + item.getNomePlanoConta() + "\"");
			sb.append(" ValorCreditoPlanoConta=\"" + getTotalCreditoPlanoConta(item) + "\"");
			sb.append(" ValorDebitoPlanoConta=\"" + getTotalDebitoPlanoConta(item) + "\"");
			sb.append(">");
			sb.append(addChild(item, planoDao));
			sb.append("</planoconta>");
		}
		
		return sb.toString();
	}

	private BigDecimal getTotalDebitoPlanoConta(PlanoContaVO plano) throws SQLException {
		BigDecimal total = BigDecimal.ZERO;
		
		// Busca contas a pagar
		ContaPagarVO contaPagFilter = new ContaPagarVO();
		ContaPagarDAO contaPagDao = new ContaPagarDAO();
		contaPagDao.setSpecialCondition(" (SELECT d.NumeroPlanoConta from personalerp.planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta) = '" + plano.getNumeroPlanoConta() + "' OR (SELECT d.NumeroPlanoConta from personalerp.planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta) LIKE '" + plano.getNumeroPlanoConta() + ".%' ");
		List<ContaPagarVO> listContaPag = contaPagDao.getList(contaPagFilter);
		
		for (ContaPagarVO contaPag : listContaPag) {
			// Busca baixas (recebimentos)
			BaixaContaPagarVO baixaContaPagFilter = new BaixaContaPagarVO();
			BaixaContaPagarDAO baixaContaPagDao = new BaixaContaPagarDAO();
			
			baixaContaPagFilter.setCodigoEmpresaContaPagar(contaPag.getCodigoEmpresa());
			baixaContaPagFilter.setCodigoLojaContaPagar(contaPag.getCodigoLoja());
			baixaContaPagFilter.setCodigoContaPagar(contaPag.getCodigoContaPagar());
			
			List<BaixaContaPagarVO> listBaixaContaPag = baixaContaPagDao.getList(baixaContaPagFilter);
			
			for (BaixaContaPagarVO baixaContaPag : listBaixaContaPag) {
				total = total.add(baixaContaPag.getValorBaixaContaPagar());
			}
		}
		
		return total;
	}
	
	private BigDecimal getTotalCreditoPlanoConta(PlanoContaVO plano) throws SQLException {
		BigDecimal total = BigDecimal.ZERO;
		
		// Busca contas a pagar
		ContaReceberVO contaRecFilter = new ContaReceberVO();
		ContaReceberDAO contaRecDao = new ContaReceberDAO();
		contaRecDao.setSpecialCondition(" (SELECT d.NumeroPlanoConta from personalerp.planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta) = '" + plano.getNumeroPlanoConta() + "' OR (SELECT d.NumeroPlanoConta from personalerp.planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta) LIKE '" + plano.getNumeroPlanoConta() + ".%' ");
		List<ContaReceberVO> listContaRec = contaRecDao.getList(contaRecFilter);
		
		for (ContaReceberVO contaRec : listContaRec) {
			// Busca baixas (recebimentos)
			BaixaContaReceberVO baixaContaRecFilter = new BaixaContaReceberVO();
			BaixaContaReceberDAO baixaContaRecDao = new BaixaContaReceberDAO();
			
			baixaContaRecFilter.setCodigoEmpresaContaReceber(contaRec.getCodigoEmpresa());
			baixaContaRecFilter.setCodigoLojaContaReceber(contaRec.getCodigoLoja());
			baixaContaRecFilter.setCodigoContaReceber(contaRec.getCodigoContaReceber());
			
			List<BaixaContaReceberVO> listBaixaContaRec = baixaContaRecDao.getList(baixaContaRecFilter);
			
			for (BaixaContaReceberVO baixaContaRec : listBaixaContaRec) {
				total = total.add(baixaContaRec.getValorRecebimento());
			}
		}
		
		return total;
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
}