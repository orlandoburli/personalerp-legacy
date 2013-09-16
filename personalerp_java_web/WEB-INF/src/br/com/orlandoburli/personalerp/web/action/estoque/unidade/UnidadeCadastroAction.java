package br.com.orlandoburli.personalerp.web.action.estoque.unidade;

import br.com.orlandoburli.core.web.framework.action.BaseCadastroAction;
import br.com.orlandoburli.personalerp.model.estoque.unidade.be.UnidadeBe;
import br.com.orlandoburli.personalerp.model.estoque.unidade.dao.UnidadeDAO;
import br.com.orlandoburli.personalerp.model.estoque.unidade.vo.UnidadeVO;

public class UnidadeCadastroAction extends BaseCadastroAction<UnidadeVO, UnidadeDAO, UnidadeBe> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspCadastro() {
		return "web/pages/estoque/unidade/unidadecadastro.jsp";
	}
}