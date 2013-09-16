package br.com.orlandoburli.personalerp.facades.estoque.grupo;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class GrupoCadastroFacade extends BaseCadastroFlexFacade<GrupoVO, GrupoDAO> {

	public GrupoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NomeGrupo", "Nome do Grupo"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getQuantidadeMinimaMetroGrupo() != null && getVo().getQuantidadeMinimaUnitariaGrupo() != null && getVo().getQuantidadeMinimaMetroGrupo() > 0 && getVo().getQuantidadeMinimaUnitariaGrupo() > 0) {
			this.messages.add(new MessageVO("Informe somente quantidade unitária OU metragem.", "QuantidadeMinimaUnitariaGrupo"));
			return false;
		}
		return super.doBeforeSave();
	}

	private static final long	serialVersionUID	= 1L;

	@Override
	public Class<?> getDAOClass() {
		return GrupoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return GrupoVO.class;
	}
}