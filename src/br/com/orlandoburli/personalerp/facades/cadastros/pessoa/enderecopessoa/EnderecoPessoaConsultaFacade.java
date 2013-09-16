package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.enderecopessoa;

import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class EnderecoPessoaConsultaFacade extends BaseConsultaFlexFacade<EnderecoPessoaVO, EnderecoPessoaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setLogradouroEnderecoPessoa(getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return EnderecoPessoaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return EnderecoPessoaVO.class;
	}
}