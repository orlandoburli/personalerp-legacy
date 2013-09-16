package br.com.orlandoburli.personalerp.facades.financeiro.movimentacaocontabancaria;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.ChequeEmitidoDAO;
import br.com.orlandoburli.core.dao.financeiro.MovimentacaoContaBancariaDAO;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class MovimentacaoContaBancariaCadastroFacade extends BaseCadastroFlexFacade<MovimentacaoContaBancariaVO, MovimentacaoContaBancariaDAO>{

	private static final long serialVersionUID = 1L;

	public MovimentacaoContaBancariaCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("OperacaoMovimentacaoContaBancaria", "Operação"));
		addNewValidator(new NotEmptyValidator("ValorMovimentacaoContaBancaria", "Valor"));
		addNewValidator(new NotEmptyValidator("DescricaoMovimentacaoContaBancaria", "Descrição"));
		addNewValidator(new NotEmptyValidator("DataMovimentacaoContaBancaria", "Data"));
	}
	
	@Override
	public boolean doBeforeUpdate() throws SQLException {
		try {
			// Verifica a movimentacao
			MovimentacaoContaBancariaVO mov = dao.get(getVo());
			
			if (mov.getCodigoPagamento() != null) {
				this.messages.add(new MessageVO("Movimentação não pode ser alterada, está vinculada a pagamentos!"));
				return false;
			} else if (mov.getCodigoRecebimento() != null) {
				this.messages.add(new MessageVO("Movimentação não pode ser alterada, está vinculada a recebimentos!"));
				return false;
			}
			
			// Verifica se tem cheques vinculados
			ChequeEmitidoVO chequeFilter = new ChequeEmitidoVO();
			chequeFilter.setCodigoEmpresaContaBancaria(mov.getCodigoEmpresa());
			chequeFilter.setCodigoLojaContaBancaria(mov.getCodigoLoja());
			chequeFilter.setCodigoContaBancaria(mov.getCodigoContaBancaria());
			chequeFilter.setSequencialMovimentacaoContaBancaria(mov.getSequencialMovimentacaoContaBancaria());
			
			List<ChequeEmitidoVO> listChequesEmitidos = new ChequeEmitidoDAO().getList(chequeFilter);
			if (listChequesEmitidos.size() > 0) {
				this.messages.add(new MessageVO("Movimentação não pode ser alterada, está vinculada a compensação de cheques!"));
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeUpdate();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		if (!doBeforeUpdate()) {
			return false;
		}
		return super.doBeforeDelete();
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("chequeemitido") >= 0) {
			writeErrorMessage("Movimentação não pode ser alterada / excluída, possui cheques vinculados!");
			return;
		}
		super.doBeforeWriteSqlErro(e);
	}
}