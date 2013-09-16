package br.com.orlandoburli.personalerp.facades.geral;

import java.util.List;

import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;

public class PermissaoFacade extends BaseFacade {

	private static final long	serialVersionUID	= 1L;

	private List<PermissaoPerfilObjetoVO> permissaoobjetos;
	private List<PermissaoPerfilAcaoObjetoVO> permissaoacaoobjetos;
	
	private String NomeObjeto;
	private String NomeAcao;
	
	public void execute() {
		boolean foundObjeto = false;
		boolean foundAcao = false;
		
		if (NomeObjeto != null) {
			for (PermissaoPerfilObjetoVO objeto : permissaoobjetos) {
				if (objeto.getNomeObjeto().equalsIgnoreCase(NomeObjeto)) {
					foundObjeto = true;
					if (NomeAcao != null) {
						for (PermissaoPerfilAcaoObjetoVO acao : permissaoacaoobjetos) {
							if (acao.getNomeObjeto().equalsIgnoreCase(NomeObjeto) && acao.getNomeAcaoObjeto().equalsIgnoreCase(NomeAcao)) {
								foundAcao = true;
							}
						}
					}
					break;
				}
			}
		}
		
		if (foundObjeto && foundAcao) {
			write("ok");
		} else {
			write("Você não tem permissão de acesso a esta ação!");
		}
	}

	public List<PermissaoPerfilObjetoVO> getPermissaoobjetos() {
		return permissaoobjetos;
	}

	public void setPermissaoobjetos(List<PermissaoPerfilObjetoVO> permissaoobjetos) {
		this.permissaoobjetos = permissaoobjetos;
	}

	public List<PermissaoPerfilAcaoObjetoVO> getPermissaoacaoobjetos() {
		return permissaoacaoobjetos;
	}

	public void setPermissaoacaoobjetos(
			List<PermissaoPerfilAcaoObjetoVO> permissaoacaoobjetos) {
		this.permissaoacaoobjetos = permissaoacaoobjetos;
	}
}