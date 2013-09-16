package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.pessoafisica;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaFisicaDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaFisicaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class PessoaFisicaCadastroFacade extends BaseCadastroFlexFacade<PessoaFisicaVO, PessoaFisicaDAO> {

	private static final long	serialVersionUID	= 1L;
	
	public PessoaFisicaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("DataNascimentoPessoaFisica", "Data de Nascimento"));
		this.addNewValidator(new NotEmptyValidator("SexoPessoaFisica", "Sexo"));
	}
	
	public void execute() {
		if (getVo().IsNew()) {
			inserir();
		} else {
			alterar();
		}
	}
	
	@Override
	@IgnoreMethodAuthentication
	public void visualizar() {
		super.visualizar();
	}

	@Override
	public Class<?> getDAOClass() {
		return PessoaFisicaDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return PessoaFisicaVO.class;
	}
}