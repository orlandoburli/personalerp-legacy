package br.com.orlandoburli.personalerp.web.action.acesso.objeto.acaoobjeto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.core.web.framework.action.BaseConsultaAction;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.be.AcaoObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.dao.AcaoObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.vo.AcaoObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.be.ObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.vo.ObjetoVO;

public class AcaoObjetoConsultaAction extends BaseConsultaAction<AcaoObjetoVO, AcaoObjetoDAO, AcaoObjetoBe>{

	private static final long serialVersionUID = 1L;
	private Integer CodigoObjeto;
	
	@OutjectSession
	private ObjetoVO objeto;
	
	@Override
	public String getJspConsulta() {
		return "web/pages/acesso/objeto/acaoobjeto/acaoobjetoconsulta.jsp";
	}

	@Override
	public String getJspGridConsulta() {
		return "web/pages/acesso/objeto/acaoobjeto/acaoobjetoconsulta_grid.jsp";
	}

	@Override
	public String getOrderFields() {
		return "NomeAcaoObjeto";
	}
	
	@Override
	public void doBeforeExecute() {
		if (getObjeto() == null) {
			try {
				setObjeto(new ObjetoBe().getById(getCodigoObjeto()));
			} catch (ListException e) {
				e.printStackTrace();
			}
		}
		super.doBeforeExecute();
	}

	@Override
	public void doBeforeFilter(AcaoObjetoVO filter, AcaoObjetoBe be, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("FILTRANDO");
		filter.setCodigoObjeto(getObjeto().getCodigoObjeto());
	}

	public Integer getCodigoObjeto() {
		return CodigoObjeto;
	}

	public void setCodigoObjeto(Integer codigoObjeto) {
		CodigoObjeto = codigoObjeto;
	}

	public ObjetoVO getObjeto() {
		return objeto;
	}

	public void setObjeto(ObjetoVO objeto) {
		this.objeto = objeto;
	}
}
