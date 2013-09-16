package br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.baixacontareceber;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.financeiro.ChequeRecebidoDAO;
import br.com.orlandoburli.core.dao.financeiro.MovimentacaoContaBancariaDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.BaixaContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.RecebimentoDAO;
import br.com.orlandoburli.core.vo.financeiro.ChequeRecebidoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.RecebimentoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class BaixaContaReceberCadastroFacade extends BaseCadastroFlexFacade<BaixaContaReceberVO, BaixaContaReceberDAO>{

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
			
			BaixaContaReceberVO _filter = new BaixaContaReceberVO();
			_filter.setCodigoRecebimento(getVo().getCodigoRecebimento());
			_filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			_filter.setCodigoLoja(getVo().getCodigoLoja());
			
			boolean flagFound = false;
			
			List<BaixaContaReceberVO> baixas = dao.getList(_filter);
			
			for (BaixaContaReceberVO baixa : baixas) {
				if (!(baixa.getCodigoContaReceber().equals(getVo().getCodigoContaReceber()) && baixa.getCodigoEmpresaContaReceber().equals(getVo().getCodigoEmpresaContaReceber()) && baixa.getCodigoLojaContaReceber().equals(getVo().getCodigoLojaContaReceber()))) {
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
			for (BaixaContaReceberVO baixa : baixas) {
				ContaReceberVO conta = new ContaReceberVO();
				conta.setCodigoEmpresa(baixa.getCodigoEmpresaContaReceber());
				conta.setCodigoLoja(baixa.getCodigoLojaContaReceber());
				conta.setCodigoContaReceber(baixa.getCodigoContaReceber());
				ContaReceberDAO contaDao = new ContaReceberDAO();
				conta = contaDao.get(conta);
				
				if (conta != null) {
					conta.setSituacaoContaReceber(ContaReceberVO.SITUACAO_ABERTO);
					//contaDao.persist(conta);
					_dao.persist(conta);
				}
			}
			
			RecebimentoDAO recebimentoDao = new RecebimentoDAO();
			
			// Exclui movimentacao de conta
			for (BaixaContaReceberVO baixa : baixas) {
				MovimentacaoContaBancariaVO _filterMovimentacao = new MovimentacaoContaBancariaVO();
				_filterMovimentacao.setCodigoEmpresaRecebimento(baixa.getCodigoEmpresa());
				_filterMovimentacao.setCodigoLojaRecebimento(baixa.getCodigoLoja());
				_filterMovimentacao.setCodigoRecebimento(baixa.getCodigoRecebimento());
				MovimentacaoContaBancariaDAO movDao = new MovimentacaoContaBancariaDAO();
 				List<MovimentacaoContaBancariaVO> movimentacoes = movDao.getList(_filterMovimentacao);
 				
 				for (MovimentacaoContaBancariaVO movimentacao : movimentacoes) {
 					//movDao.remove(movimentacao);
 					_dao.remove(movimentacao);
 				}
			}
			
			// Exclui cheques
			for (BaixaContaReceberVO baixa : baixas) {
				ChequeRecebidoVO _filterCheque = new ChequeRecebidoVO();
				_filterCheque.setCodigoEmpresaRecebimento(baixa.getCodigoEmpresa());
				_filterCheque.setCodigoLojaRecebimento(baixa.getCodigoLoja());
				_filterCheque.setCodigoRecebimento(baixa.getCodigoRecebimento());
				ChequeRecebidoDAO movCheque = new ChequeRecebidoDAO();
 				List<ChequeRecebidoVO> cheques = movCheque.getList(_filterCheque);
 				
 				for (ChequeRecebidoVO cheque : cheques) {
 					//movCheque.remove(cheque);
 					_dao.remove(cheque);
 				}
			}
			
			// Exclui as baixas
			for (BaixaContaReceberVO baixa : baixas) {
				//dao.remove(baixa);
				_dao.remove(baixa);
			}
			
			// Exclui os pagamentos
			for (BaixaContaReceberVO baixa : baixas) {
				RecebimentoVO _recebimento = new RecebimentoVO();
				
				_recebimento.setCodigoEmpresa(baixa.getCodigoEmpresa());
				_recebimento.setCodigoLoja(baixa.getCodigoLoja());
				_recebimento.setCodigoRecebimento(baixa.getCodigoRecebimento());
				
				_recebimento = recebimentoDao.get(_recebimento);
				if (_recebimento != null) {
					//pagamentoDao.remove(_pagamento);
					_dao.remove(_recebimento);
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
		return BaixaContaReceberDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return BaixaContaReceberVO.class;
	}

	public void setConfirmacaoExclusao(String confirmacaoExclusao) {
		ConfirmacaoExclusao = confirmacaoExclusao;
	}

	public String getConfirmacaoExclusao() {
		return ConfirmacaoExclusao;
	}
}