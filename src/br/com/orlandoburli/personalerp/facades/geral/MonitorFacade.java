package br.com.orlandoburli.personalerp.facades.geral;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.utils.MonitorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;

public class MonitorFacade extends BaseFacade {

	private String KillId;
	
	private static final long serialVersionUID = 1L;
	private int PageNumber;
	
	public void execute() {
		forward("monitor.jsp");
	}
	
	public List<MonitorVO> getListSource() {
		
//		try {
////			request.getRequestDispatcher("/manager.session").include(request, response);
//		} catch (ServletException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		List<MonitorVO> list = new ArrayList<MonitorVO>();
		
		for (HttpSession session : SystemManager.listSessions) {
			MonitorVO monitor = new MonitorVO();
			monitor.setId(session.getId());
			monitor.setUsuario((UsuarioVO) session.getAttribute(Constants.Session.SESSION_USUARIO));
			monitor.setPerfil((PerfilVO) session.getAttribute(Constants.Session.SESSION_PERFIL));
			monitor.setEmpresa((EmpresaVO) session.getAttribute(Constants.Session.SESSION_EMPRESA));
			monitor.setLoja((LojaVO) session.getAttribute(Constants.Session.SESSION_LOJA));
			list.add(monitor);
		}
		
		return list;
	}
	
	public void kill() {
		for (HttpSession session : SystemManager.listSessions) {
			if (session.getId().equals(getKillId())) {
				session.invalidate(); // Finaliza a sessão
			}
		}
		redir(getFacadeName() + ".action");
	}

	public void setKillId(String killId) {
		KillId = killId;
	}
	
	public int getpagecount() {
		return SystemManager.listSessions.size();
	}

	public String getKillId() {
		return KillId;
	}

	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}

	public int getPageNumber() {
		return PageNumber;
	}
}