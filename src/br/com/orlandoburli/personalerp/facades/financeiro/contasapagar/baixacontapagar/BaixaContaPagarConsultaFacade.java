package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.baixacontapagar;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.financeiro.contasapagar.BaixaContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.PagamentoDAO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class BaixaContaPagarConsultaFacade extends BaseConsultaFlexFacade<BaixaContaPagarVO, BaixaContaPagarDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}
	
	@Override
	public void doBeforeWrite() {
		try {
			PagamentoDAO _dao = new PagamentoDAO();
			for (BaixaContaPagarVO baixa : getListSource()) {
				PagamentoVO pagamento = new PagamentoVO();
				
				pagamento.setCodigoEmpresa(baixa.getCodigoEmpresa());
				pagamento.setCodigoLoja(baixa.getCodigoLoja());
				pagamento.setCodigoPagamento(baixa.getCodigoPagamento());
				
				baixa.setPagamento(_dao.get(pagamento));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.doBeforeWrite();
	}

	@Override
	protected Class<?> getDAOClass() {
		return BaixaContaPagarDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return BaixaContaPagarVO.class;
	}
}