package br.com.orlandoburli.core.web.framework.filters;

import java.lang.reflect.Method;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.BaseFilter;
import br.com.orlandoburli.core.web.framework.pages.BasePage;

public class AutorizathionFilter extends BaseFilter {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean doFilter(BaseFacade facade) throws IllegalArgumentException, IllegalAccessException {
		
		if (facade.getClass().getAnnotation(IgnoreFacadeAuthentication.class) != null) {
			return true;
		}
		
		// Verifica se o usuario esta logado
		if (this.getRequest().getSession().getAttribute(Constants.Session.SESSION_USUARIO) == null) {
			if (facade.getFacadeName().equalsIgnoreCase("login") || facade.getFacadeName().equalsIgnoreCase("loginflex")) {
				return true;
			} else {
				return false;
			}
		}
		
		try {
			Method method = facade.getClass().getMethod(facade.getMethodName(), new Class<?>[]{});
			if (method.getAnnotation(IgnoreMethodAuthentication.class) != null) {
				return true;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if (facade.getFacadeName().equalsIgnoreCase("login") || facade.getFacadeName().equalsIgnoreCase("loginflex")) {
			return true;
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
			if (facade.getFacadeName().equals(nomeObjeto)) { // Achou o objeto na lista de objetos permitidos
				acessoObjeto = true;
				List<PermissaoPerfilAcaoObjetoVO> listAcoesObjeto = (List<PermissaoPerfilAcaoObjetoVO>) this.getRequest().getSession().getAttribute(Constants.Session.SESSION_PERMISSAO_ACAO_OBJETOS);
				for (PermissaoPerfilAcaoObjetoVO acaoobjeto : listAcoesObjeto) {
					nomeObjeto = acaoobjeto.getNomeObjeto().substring(0, acaoobjeto.getNomeObjeto().indexOf("."));
					if (acaoobjeto.getNomeAcaoObjeto() != null && acaoobjeto.getNomeAcaoObjeto().equals(facade.getMethodName()) && facade.getFacadeName().equals(nomeObjeto)) {
						acessoAcaoObjeto = true;
						break;
					}
				}
				
				break;
			}
		}
		String mensagem = null;
		if (!acessoObjeto) {
			mensagem = "Acesso negado a este objeto [" + facade.getFacadeName() + "]!";
		} else if (!acessoAcaoObjeto) {
			mensagem = "Acesso negado a esta ação [" + facade.getMethodName() + "] em [" + facade.getFacadeName() + "]!";
		}
		if (mensagem != null) {
			facade.writeErrorMessage(mensagem);
			System.out.println(mensagem);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean doFilter(BasePage page) throws IllegalArgumentException,
			IllegalAccessException {
		if (page.getClass().getAnnotation(IgnoreFacadeAuthentication.class) != null) {
			return true;
		}
		
		// Verifica se o usuario esta logado
		if (this.getRequest().getSession().getAttribute(Constants.Session.SESSION_USUARIO) == null) {
			if (page.getFacadeName().equalsIgnoreCase("login") || page.getFacadeName().equalsIgnoreCase("loginflex")) {
				return true;
			} else {
				return false;
			}
		}
		
		try {
			Method method = page.getClass().getMethod(page.getMethodName(), new Class<?>[]{});
			if (method.getAnnotation(IgnoreMethodAuthentication.class) != null) {
				return true;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
		if (page.getFacadeName().equalsIgnoreCase("login") || page.getFacadeName().equalsIgnoreCase("loginflex")) {
			return true;
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
			if (page.getFacadeName().equals(nomeObjeto)) { // Achou o objeto na lista de objetos permitidos
				acessoObjeto = true;
				List<PermissaoPerfilAcaoObjetoVO> listAcoesObjeto = (List<PermissaoPerfilAcaoObjetoVO>) this.getRequest().getSession().getAttribute(Constants.Session.SESSION_PERMISSAO_ACAO_OBJETOS);
				for (PermissaoPerfilAcaoObjetoVO acaoobjeto : listAcoesObjeto) {
					nomeObjeto = acaoobjeto.getNomeObjeto().substring(0, acaoobjeto.getNomeObjeto().indexOf("."));
					if (acaoobjeto.getNomeAcaoObjeto() != null && acaoobjeto.getNomeAcaoObjeto().equals(page.getMethodName()) && page.getFacadeName().equals(nomeObjeto)) {
						acessoAcaoObjeto = true;
						break;
					}
				}
				
				break;
			}
		}
		String mensagem = null;
		if (!acessoObjeto) {
			mensagem = "Acesso negado a este objeto [" + page.getFacadeName() + "]!";
		} else if (!acessoAcaoObjeto) {
			mensagem = "Acesso negado a esta ação [" + page.getMethodName() + "] em [" + page.getFacadeName() + "]!";
		}
		if (mensagem != null) {
			page.writeErrorMessage(mensagem);
			System.out.println(mensagem);
			return false;
		}
		return true;
	}
}