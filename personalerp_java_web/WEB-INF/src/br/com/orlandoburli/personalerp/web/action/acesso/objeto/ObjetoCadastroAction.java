package br.com.orlandoburli.personalerp.web.action.acesso.objeto;

import br.com.orlandoburli.core.web.framework.action.BaseCadastroAction;
import br.com.orlandoburli.personalerp.model.acesso.objeto.be.ObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.dao.ObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.vo.ObjetoVO;

public class ObjetoCadastroAction extends BaseCadastroAction<ObjetoVO, ObjetoDAO, ObjetoBe>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspCadastro() {
		return "web/pages/acesso/objeto/objetocadastro.jsp";
	}
}
