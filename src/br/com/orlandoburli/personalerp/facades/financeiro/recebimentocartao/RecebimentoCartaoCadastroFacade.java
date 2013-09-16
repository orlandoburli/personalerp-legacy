package br.com.orlandoburli.personalerp.facades.financeiro.recebimentocartao;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.financeiro.RecebimentoCartaoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.OperadoraCartaoVO;
import br.com.orlandoburli.core.vo.financeiro.RecebimentoCartaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class RecebimentoCartaoCadastroFacade extends BaseCadastroFlexFacade<RecebimentoCartaoVO, RecebimentoCartaoDAO>{

	private static final long serialVersionUID = 1L;

	public void compensar() {
		try {
			GenericDAO _dao = new GenericDAO();
			_dao.setAutoCommit(false);
			
			setVo((RecebimentoCartaoVO) _dao.get(getVo()));
			
			if (getVo() == null) {
				writeErrorMessage("Recebimento não encontrado!");
				return;
			}
			
			if (!getVo().getStatusRecebimentoCartao().equals("A")) {
				writeErrorMessage("Recebimento de cartão não está mais em aberto!");
				return;
			}
			
			OperadoraCartaoVO operadora = new OperadoraCartaoVO();
			operadora.setCodigoOperadoraCartao(getVo().getCodigoOperadoraCartao());
			operadora =	(OperadoraCartaoVO) _dao.get(operadora);
			
			if (operadora == null) {
				writeErrorMessage("Operadora de cartão não encontrada!");
				return;
			}
			
			MovimentacaoContaBancariaVO mov = new MovimentacaoContaBancariaVO();
			
			mov.setNewRecord(true);
			mov.setCodigoEmpresa(operadora.getCodigoEmpresa());
			mov.setCodigoLoja(operadora.getCodigoLoja());
			mov.setCodigoContaBancaria(operadora.getCodigoContaBancaria());
			
			mov.setOperacaoMovimentacaoContaBancaria(MovimentacaoContaBancariaVO.OPERACAO_CREDITO);
			mov.setValorMovimentacaoContaBancaria(getVo().getValorRecebimentoCartao());
			mov.setDescricaoMovimentacaoContaBancaria("Crédito em conta da operadora de cartão " + getVo().getNomeOperadora() + " da bandeira " + getVo().getNomeBandeira() + " recibo n." + getVo().getReciboRecebimentoCartao());
			mov.setDataMovimentacaoContaBancaria(Utils.getToday());
			
			Utils.setUsuarioLog(mov, getUsuariosessao());
			
			_dao.persist(mov);
			
			getVo().setStatusRecebimentoCartao("P");
			getVo().setCodigoEmpresaContaBancaria(mov.getCodigoEmpresa());
			getVo().setCodigoLojaContaBancaria(mov.getCodigoLoja());
			getVo().setCodigoContaBancaria(mov.getCodigoContaBancaria());
			getVo().setSequencialMovimentacaoContaBancaria(mov.getSequencialMovimentacaoContaBancaria());
			
			Utils.setUsuarioLog(getVo(), getUsuariosessao());
			
			_dao.persist(getVo());
			
			_dao.commit();
			
			write("ok");
		} catch (SQLException ex) {
			ex.printStackTrace();
			writeErrorMessage(ex.getMessage());
		}
	}
	
	public void estornar() {
		try {
			GenericDAO _dao = new GenericDAO();
			_dao.setAutoCommit(false);
			
			setVo((RecebimentoCartaoVO) _dao.get(getVo()));
			
			if (getVo() == null) {
				writeErrorMessage("Recebimento não encontrado!");
				return;
			}
			
			if (getVo().getStatusRecebimentoCartao().equals("A")) {
				writeErrorMessage("Recebimento de cartão está em aberto!");
				return;
			}
			
			MovimentacaoContaBancariaVO mov = new MovimentacaoContaBancariaVO();
			
			mov.setCodigoEmpresa(getVo().getCodigoEmpresaContaBancaria());
			mov.setCodigoLoja(getVo().getCodigoLojaContaBancaria());
			mov.setCodigoContaBancaria(getVo().getCodigoContaBancaria());
			mov.setSequencialMovimentacaoContaBancaria(getVo().getSequencialMovimentacaoContaBancaria());
			
			mov = (MovimentacaoContaBancariaVO) _dao.get(mov);
			
			if (mov == null) {
				writeErrorMessage("Movimentação não encontrada!");
				return;
			}
			
			Utils.setUsuarioLog(mov, getUsuariosessao());
			
			getVo().setStatusRecebimentoCartao("A");
			getVo().setSequencialMovimentacaoContaBancaria(null);
			getVo().setCodigoEmpresaContaBancaria(null);
			getVo().setCodigoLojaContaBancaria(null);
			getVo().setCodigoContaBancaria(null);
			
			Utils.setUsuarioLog(getVo(), getUsuariosessao());
			
			_dao.persist(getVo());
			_dao.remove(mov);
			
			_dao.commit();
			
			write("ok");
		} catch (SQLException ex) {
			ex.printStackTrace();
			writeErrorMessage(ex.getMessage());
		}
	}
}