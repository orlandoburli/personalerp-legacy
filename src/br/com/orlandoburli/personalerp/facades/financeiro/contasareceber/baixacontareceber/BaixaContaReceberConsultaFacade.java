package br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.baixacontareceber;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.financeiro.contareceber.BaixaContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.RecebimentoDAO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.RecebimentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class BaixaContaReceberConsultaFacade extends BaseConsultaFlexFacade<BaixaContaReceberVO, BaixaContaReceberDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}
	
	@Override
	public void doBeforeWrite() {
		try {
			RecebimentoDAO _dao = new RecebimentoDAO();
			for (BaixaContaReceberVO baixa : getListSource()) {
				RecebimentoVO recebimento = new RecebimentoVO();
				
				recebimento.setCodigoEmpresa(baixa.getCodigoEmpresa());
				recebimento.setCodigoLoja(baixa.getCodigoLoja());
				recebimento.setCodigoRecebimento(baixa.getCodigoRecebimento());
				
				baixa.setRecebimento(_dao.get(recebimento));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.doBeforeWrite();
	}
}