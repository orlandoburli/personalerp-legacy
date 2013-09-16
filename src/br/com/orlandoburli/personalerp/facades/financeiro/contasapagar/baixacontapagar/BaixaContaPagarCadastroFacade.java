package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.baixacontapagar;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.financeiro.ChequeEmitidoDAO;
import br.com.orlandoburli.core.dao.financeiro.MovimentacaoContaBancariaDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.BaixaContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.PagamentoDAO;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class BaixaContaPagarCadastroFacade extends BaseCadastroFlexFacade<BaixaContaPagarVO, BaixaContaPagarDAO> {

	private static final long serialVersionUID = 1L;
	
	private String ConfirmacaoExclusao;

	public void estornar() {
		excluir();
	}
	
	@Override
	public boolean doBeforeSave() {
		// Previne modificações
		return false;
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		
		// Verifica se o pagamento está sendo usado em conjunto com outras baixas
		GenericDAO _dao = new GenericDAO();
		try {
			_dao.setAutoCommit(false);
			
			BaixaContaPagarVO _filter = new BaixaContaPagarVO();
			_filter.setCodigoPagamento(getVo().getCodigoPagamento());
			_filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			_filter.setCodigoLoja(getVo().getCodigoLoja());
			
			boolean flagFound = false;
			
			List<BaixaContaPagarVO> baixas = dao.getList(_filter);
			
			for (BaixaContaPagarVO baixa : baixas) {
				if (!(baixa.getCodigoContaPagar().equals(getVo().getCodigoContaPagar()) && baixa.getCodigoEmpresaContaPagar().equals(getVo().getCodigoEmpresaContaPagar()) && baixa.getCodigoLojaContaPagar().equals(getVo().getCodigoLojaContaPagar()))) {
					// Se ja for a confirmação... excluir
					if (!getConfirmacaoExclusao().equalsIgnoreCase("S")) {
						flagFound = true;
					}
				}
			}
			
			if (flagFound) {
				MessageVO message = new MessageVO("Existem contas vinculadas a este mesmo pagamento. " +
						"\nSe você excluir esta baixa, irá excluir também as outras baixas. " +
						"\nDeseja prosseguir?", "CONTA_VINCULADA");
				this.messages.add(message);
				return false;
			}
			
			// Retorna o status como aberto de cada conta
			for (BaixaContaPagarVO baixa : baixas) {
				ContaPagarVO conta = new ContaPagarVO();
				conta.setCodigoEmpresa(baixa.getCodigoEmpresaContaPagar());
				conta.setCodigoLoja(baixa.getCodigoLojaContaPagar());
				conta.setCodigoContaPagar(baixa.getCodigoContaPagar());
				ContaPagarDAO contaDao = new ContaPagarDAO();
				conta = contaDao.get(conta);
				
				if (conta != null) {
					conta.setSituacaoContaPagar(ContaPagarVO.SITUACAO_ABERTO);
					//contaDao.persist(conta);
					_dao.persist(conta);
				}
			}
			
			PagamentoDAO pagamentoDao = new PagamentoDAO();
			
			// Exclui movimentacao de conta
			for (BaixaContaPagarVO baixa : baixas) {
				MovimentacaoContaBancariaVO _filterMovimentacao = new MovimentacaoContaBancariaVO();
				_filterMovimentacao.setCodigoEmpresaPagamento(baixa.getCodigoEmpresa());
				_filterMovimentacao.setCodigoLojaPagamento(baixa.getCodigoLoja());
				_filterMovimentacao.setCodigoPagamento(baixa.getCodigoPagamento());
				MovimentacaoContaBancariaDAO movDao = new MovimentacaoContaBancariaDAO();
 				List<MovimentacaoContaBancariaVO> movimentacoes = movDao.getList(_filterMovimentacao);
 				
 				for (MovimentacaoContaBancariaVO movimentacao : movimentacoes) {
 					//movDao.remove(movimentacao);
 					_dao.remove(movimentacao);
 				}
			}
			
			// Exclui cheques
			for (BaixaContaPagarVO baixa : baixas) {
				ChequeEmitidoVO _filterCheque = new ChequeEmitidoVO();
				_filterCheque.setCodigoEmpresaPagamento(baixa.getCodigoEmpresa());
				_filterCheque.setCodigoLojaPagamento(baixa.getCodigoLoja());
				_filterCheque.setCodigoPagamento(baixa.getCodigoPagamento());
				ChequeEmitidoDAO movCheque = new ChequeEmitidoDAO();
 				List<ChequeEmitidoVO> cheques = movCheque.getList(_filterCheque);
 				
 				for (ChequeEmitidoVO cheque : cheques) {
 					//movCheque.remove(cheque);
 					_dao.remove(cheque);
 				}
			}
			
			// Exclui as baixas
			for (BaixaContaPagarVO baixa : baixas) {
				//dao.remove(baixa);
				_dao.remove(baixa);
			}
			
			// Exclui os pagamentos
			for (BaixaContaPagarVO baixa : baixas) {
				PagamentoVO _pagamento = new PagamentoVO();
				
				_pagamento.setCodigoEmpresa(baixa.getCodigoEmpresa());
				_pagamento.setCodigoLoja(baixa.getCodigoLoja());
				_pagamento.setCodigoPagamento(baixa.getCodigoPagamento());
				
				_pagamento = pagamentoDao.get(_pagamento);
				if (_pagamento != null) {
					//pagamentoDao.remove(_pagamento);
					_dao.remove(_pagamento);
				}
			}
			_dao.commit();
		} catch (SQLException e) {
			_dao.rollback();
			e.printStackTrace();
		}
		return super.doBeforeDelete();
	}

	@Override
	protected Class<?> getDAOClass() {
		return BaixaContaPagarDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return BaixaContaPagarVO.class;
	}

	public void setConfirmacaoExclusao(String confirmacaoExclusao) {
		ConfirmacaoExclusao = confirmacaoExclusao;
	}

	public String getConfirmacaoExclusao() {
		return ConfirmacaoExclusao;
	}
}