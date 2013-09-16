package br.com.orlandoburli.personalerp.facades.cadastros.pessoa;

import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PessoaConsultaFacade extends BaseConsultaFlexFacade<PessoaVO, PessoaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.filter.setNomeRazaoSocialPessoa(getFiltro() + "%");
	}
	
	@Override
	public String getOrderFields() {
		return "NomeRazaoSocialPessoa";
	}

	@Override
	protected Class<?> getDAOClass() {
		return PessoaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PessoaVO.class;
	}
}