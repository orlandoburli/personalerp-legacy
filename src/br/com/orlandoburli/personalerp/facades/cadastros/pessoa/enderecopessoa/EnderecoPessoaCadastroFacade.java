package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.enderecopessoa;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.CpfCnpjValidator;
import br.com.orlandoburli.core.web.framework.validators.EmailValidator;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class EnderecoPessoaCadastroFacade extends BaseCadastroFlexFacade<EnderecoPessoaVO, EnderecoPessoaDAO> {
	
	private static final long serialVersionUID = 1L;
	private int CodigoCidadePesquisa;
	
	public EnderecoPessoaCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("CodigoCidadeEnderecoPessoa", "Cidade"));
		this.addNewValidator(new NotEmptyValidator("CpfCnpjEnderecoPessoa", "Cpf / Cnpj"));
		this.addNewValidator(new CpfCnpjValidator("CpfCnpjEnderecoPessoa", "Cpf / Cnpj"));
		//this.addNewValidator(new EmailValidator("EmailEnderecoPessoa", "Email"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (this.getVo().getEmailEnderecoPessoa() != null && !this.getVo().getEmailEnderecoPessoa().trim().equalsIgnoreCase("")) {
			EmailValidator email = new EmailValidator("EmailEnderecoPessoa", "Email");
			email.setVo(getVo());
			if (!email.validate()) {
				this.messages.add(new MessageVO(email.getMessage(), email.getFieldFocus()));
				return false;
			}
		}
		return super.doBeforeSave();
	}

	@IgnoreMethodAuthentication
	public void cidade() {
		CidadeVO filter = new CidadeVO();
		filter.setCodigoCidade(CodigoCidadePesquisa);
		try {
			write(Utils.voToXml(new CidadeDAO().get(filter)));
		} catch (SQLException e) {
			write(e.getMessage());
		}
	}
	
	@Override
	public Class<?> getDAOClass() {
		return EnderecoPessoaDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return EnderecoPessoaVO.class;
	}

	public void setCodigoCidadePesquisa(int codigoCidadePesquisa) {
		CodigoCidadePesquisa = codigoCidadePesquisa;
	}

	public int getCodigoCidadePesquisa() {
		return CodigoCidadePesquisa;
	}
}