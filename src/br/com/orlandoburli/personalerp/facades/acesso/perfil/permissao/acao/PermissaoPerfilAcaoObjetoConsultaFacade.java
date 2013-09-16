package br.com.orlandoburli.personalerp.facades.acesso.perfil.permissao.acao;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.dao.acesso.PermissaoPerfilAcaoObjetoDAO;

public class PermissaoPerfilAcaoObjetoConsultaFacade extends BaseConsultaFlexFacade<PermissaoPerfilAcaoObjetoVO, PermissaoPerfilAcaoObjetoDAO> {

	private static final long serialVersionUID = 1L;
	private PerfilVO perfil;
	private ObjetoVO objeto;
	
	public PermissaoPerfilAcaoObjetoConsultaFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		perfil = new PerfilVO();
		objeto = new ObjetoVO();
	}
	
	@Override
	public void execute() {
		//setPageCount(this.dao.getListCount(getFilter(), getPageSize()));
		perfil.setCodigoEmpresa(getFilter().getCodigoEmpresa());
		perfil.setCodigoLoja(getFilter().getCodigoLoja());
		perfil.setCodigoPerfil(getFilter().getCodigoPerfil());
		objeto.setCodigoObjeto(filter.getCodigoObjeto());
		
		setListSource(this.dao.getListByPerfil(getPerfil(), getObjeto()));
		try {
			write(Utils.voToXml(this.getListSource()));
		} catch (Exception e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	protected Class<?> getDAOClass() {
		return PermissaoPerfilAcaoObjetoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PermissaoPerfilAcaoObjetoVO.class;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setObjeto(ObjetoVO objeto) {
		this.objeto = objeto;
	}

	public ObjetoVO getObjeto() {
		return objeto;
	}
	
	public String getInfoMessage() {
		return "Clique sobre a ação para Permitir ou Negar.";
	}

	@Override
	protected void doBeforeFilter() {
	}
}