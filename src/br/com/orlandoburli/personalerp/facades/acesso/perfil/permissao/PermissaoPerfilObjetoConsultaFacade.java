package br.com.orlandoburli.personalerp.facades.acesso.perfil.permissao;

import java.sql.SQLException;

import br.com.orlandoburli.core.web.framework.facades.*;
import br.com.orlandoburli.core.dao.acesso.*;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.*;

public class PermissaoPerfilObjetoConsultaFacade extends BaseConsultaFlexFacade<PermissaoPerfilObjetoVO, PermissaoPerfilObjetoDAO> {

	private static final long serialVersionUID = 1L;
	
	private PerfilVO perfil;
	
	public PermissaoPerfilObjetoConsultaFacade() {
		perfil = new PerfilVO();
	}

	@Override
	public void execute() {
		try {
			setPageCount(this.dao.getListCountByPerfil(perfil, getPageSize(), "%" + getFiltro() + "%"));
			setListSource(this.dao.getListByPerfil(perfil, getPageSize(), getPageNumber(), "%" + getFiltro() + "%"));
		
			String baseXml = Utils.voToXml(getListSource(), false);
			String headerXml = "<?xml version='1.0' encoding='utf-8'?><list>";
			String xmlCount = "<count>" + getPageCount() + "</count>";
			
			write(headerXml + baseXml + xmlCount + "</list>");
			dispatch();
		} catch (SQLException e) {
			write(e.getMessage());
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return PermissaoPerfilObjetoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PermissaoPerfilObjetoVO.class;
	}

	@Override
	protected void doBeforeFilter() {}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}
}