package br.com.orlandoburli.personalerp.web.action.estoque.grupo;

import br.com.orlandoburli.core.web.framework.action.BaseCadastroAction;
import br.com.orlandoburli.personalerp.model.estoque.grupo.be.GrupoBe;
import br.com.orlandoburli.personalerp.model.estoque.grupo.dao.GrupoDAO;
import br.com.orlandoburli.personalerp.model.estoque.grupo.vo.GrupoVO;

public class GrupoCadastroAction extends BaseCadastroAction<GrupoVO, GrupoDAO, GrupoBe>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspCadastro() {
		return "web/pages/estoque/grupo/grupocadastro.jsp";
	}

}
