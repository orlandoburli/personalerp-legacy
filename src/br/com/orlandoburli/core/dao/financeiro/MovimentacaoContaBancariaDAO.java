package br.com.orlandoburli.core.dao.financeiro;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;

public class MovimentacaoContaBancariaDAO extends BaseCadastroDAO<MovimentacaoContaBancariaVO> {

	public MovimentacaoContaBancariaVO getMovimentacaoByPagamento(PagamentoVO pagamento) throws SQLException {
		MovimentacaoContaBancariaVO movFilter = new MovimentacaoContaBancariaVO();
		movFilter.setCodigoEmpresaPagamento(pagamento.getCodigoEmpresa());
		movFilter.setCodigoLojaPagamento(pagamento.getCodigoLoja());
		movFilter.setCodigoPagamento(pagamento.getCodigoPagamento());
		
		List<MovimentacaoContaBancariaVO> listAux = getList(movFilter);
		
		if (listAux.size() > 0) {
			return listAux.get(0);
		}
		
		return null;
	}
}
