package br.com.orlandoburli.core.dao.financeiro;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;

public class ChequeEmitidoDAO extends BaseCadastroDAO<ChequeEmitidoVO> {

	public ChequeEmitidoVO getChequeByPagamento(PagamentoVO pagamento) throws SQLException {
		ChequeEmitidoVO chequeFilter = new ChequeEmitidoVO();
		chequeFilter.setCodigoEmpresaPagamento(pagamento.getCodigoEmpresa());
		chequeFilter.setCodigoLojaPagamento(pagamento.getCodigoLoja());
		chequeFilter.setCodigoPagamento(pagamento.getCodigoPagamento());
		
		List<ChequeEmitidoVO> listAux = getList(chequeFilter);
		
		if (listAux.size() > 0) {
			return listAux.get(0);
		}
		return null;
	}
}
