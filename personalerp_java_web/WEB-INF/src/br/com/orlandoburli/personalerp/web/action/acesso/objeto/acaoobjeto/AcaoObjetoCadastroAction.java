package br.com.orlandoburli.personalerp.web.action.acesso.objeto.acaoobjeto;

import br.com.orlandoburli.core.web.framework.action.BaseCadastroAction;
import br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.be.AcaoObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.dao.AcaoObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.vo.AcaoObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.vo.ObjetoVO;

public class AcaoObjetoCadastroAction extends BaseCadastroAction<AcaoObjetoVO, AcaoObjetoDAO, AcaoObjetoBe>{

	private static final long serialVersionUID = 1L;
	
	private ObjetoVO objeto;
	
	@Override
	public String getJspCadastro() {
		return "web/pages/acesso/objeto/acaoobjeto/acaoobjetocadastro.jsp";
	}
	
	@Override
	public boolean doBeforeInsert(AcaoObjetoVO vo) {
		vo.setCodigoObjeto(getObjeto().getCodigoObjeto());
		return super.doBeforeInsert(vo);
	}

	public ObjetoVO getObjeto() {
		return objeto;
	}

	public void setObjeto(ObjetoVO objeto) {
		this.objeto = objeto;
	}
}
