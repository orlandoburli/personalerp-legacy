package br.com.orlandoburli.core.web.framework.filters;

import java.lang.reflect.Method;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.web.framework.action.BaseAction;
import br.com.orlandoburli.core.web.framework.filters.BaseFilter;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.vo.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.vo.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;

public class AutorizathionFilter extends BaseFilter {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public boolean doFilter(Object obj) throws IllegalArgumentException, IllegalAccessException {
		if (!(obj instanceof BaseAction)) {
			return true;
		}

		BaseAction action = (BaseAction) obj;

		if (action.getClass().getAnnotation(IgnoreFacadeAuthentication.class) != null) {
			return true;
		}

		if (action.getActionName().equalsIgnoreCase("login") || action.getActionName().equalsIgnoreCase("loginflex")) {
			return true;
		}

		// Verifica se o usuario esta logado
		if (this.getRequest().getSession().getAttribute(Constants.Session.SESSION_USUARIO) == null) {
			return false;
		}
		
		try {
			Method method = action.getClass().getMethod(action.getMethodName(), new Class<?>[] {});
			if (method.getAnnotation(IgnoreMethodAuthentication.class) != null) {
				return true;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		UsuarioVO usuario = (UsuarioVO) this.getRequest().getSession().getAttribute(Constants.Session.SESSION_USUARIO);

		boolean acessoObjeto = false;
		boolean acessoAcaoObjeto = false;

		if (usuario.getSuperUsuario()) { // Superusuario pode tudo!!!
			acessoObjeto = true;
			acessoAcaoObjeto = true;
		}

		List<PermissaoPerfilObjetoVO> listObjetos = (List<PermissaoPerfilObjetoVO>) this.getRequest().getSession().getAttribute(Constants.Session.SESSION_PERMISSAO_OBJETOS);
		for (PermissaoPerfilObjetoVO objeto : listObjetos) {
			// So interessa o nome do objeto ate o ponto "."
			String nomeObjeto = objeto.getNomeObjeto().substring(0, objeto.getNomeObjeto().indexOf("."));

			if (action.getActionName().equals(nomeObjeto)) { // Achou o objeto
																// na lista de
																// objetos
																// permitidos
				acessoObjeto = true;

				List<PermissaoPerfilAcaoObjetoVO> listAcoesObjeto = (List<PermissaoPerfilAcaoObjetoVO>) this.getRequest().getSession().getAttribute(Constants.Session.SESSION_PERMISSAO_ACAO_OBJETOS);

				for (PermissaoPerfilAcaoObjetoVO acaoobjeto : listAcoesObjeto) {
					nomeObjeto = acaoobjeto.getNomeObjeto().substring(0, acaoobjeto.getNomeObjeto().indexOf("."));
					if (acaoobjeto.getNomeAcaoObjeto() != null && acaoobjeto.getNomeAcaoObjeto().equals(action.getMethodName()) && action.getActionName().equals(nomeObjeto)) {
						acessoAcaoObjeto = true;
						break;
					}
				}

				break;
			}
		}

		String mensagem = null;

		if (!acessoObjeto) {
			mensagem = "Acesso negado a este objeto [" + action.getActionName() + "]!";
		} else if (!acessoAcaoObjeto) {
			mensagem = "Acesso negado a esta ação [" + action.getMethodName() + "] em [" + action.getActionName() + "]!";
		}
		if (mensagem != null) {
			action.writeErrorMessage(mensagem);
			System.out.println(mensagem);
			return false;
		}
		return true;
	}
}