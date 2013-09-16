package br.com.orlandoburli.personalerp.facades.cadastros.pessoa;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class PessoaCadastroFacade extends BaseCadastroFlexFacade<PessoaVO, PessoaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	private List<EnderecoPessoaVO> listEndereco;
	
	public PessoaCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeRazaoSocialPessoa", "Nome / Razão Social"));
	}
	
	@IgnoreMethodAuthentication
	public void lists() {
		EnderecoPessoaVO filter = new EnderecoPessoaVO();
		filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filter.setCodigoLoja(getVo().getCodigoLoja());
		filter.setCodigoPessoa(getVo().getCodigoPessoa());
		
		try {
			this.listEndereco = new EnderecoPessoaDAO().getList(filter);
		} catch (SQLException e) {
			write(e.getMessage());
			return;
		}
		
		write(Utils.voToXml(listEndereco));
	}
	
	public void setListEndereco(List<EnderecoPessoaVO> listEndereco) {
		this.listEndereco = listEndereco;
	}

	public List<EnderecoPessoaVO> getListEndereco() {
		return listEndereco;
	}
}