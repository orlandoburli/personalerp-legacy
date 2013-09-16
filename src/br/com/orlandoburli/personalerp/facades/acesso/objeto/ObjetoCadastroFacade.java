package br.com.orlandoburli.personalerp.facades.acesso.objeto;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.base.AcaoObjetoDAO;
import br.com.orlandoburli.core.dao.base.ObjetoDAO;
import br.com.orlandoburli.core.vo.base.AcaoObjetoVO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ObjetoCadastroFacade extends BaseCadastroFlexFacade<ObjetoVO, ObjetoDAO> {

	private static final long serialVersionUID = 1L;

	public ObjetoCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeObjeto", "Nome"));
		this.addNewValidator(new NotEmptyValidator("DescricaoObjeto", "Descrição"));
	}
	
	@Override
	public Class<?> getDAOClass() {
		return ObjetoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return ObjetoVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void acoespadrao() {
		try {
			AcaoObjetoDAO acaoobjetodao = new AcaoObjetoDAO();
			AcaoObjetoVO filter = new AcaoObjetoVO();
			filter.setCodigoObjeto(getVo().getCodigoObjeto());
			List<AcaoObjetoVO> list = acaoobjetodao.getList(filter);
			if (getVo().getNomeObjeto().indexOf("cadastro") >= 0) {
				boolean found = false;
				// Ações padrão de cadastro
				
				// Procura pela ação inserir
				for (AcaoObjetoVO acaoobjeto : list) {
					if (acaoobjeto.getNomeAcaoObjeto().equals(Constants.INSERIR)) {
						found = true;
						break;
					}
				}
				if (!found) { // Cadastra a ação de inserir
					AcaoObjetoVO acaoobjeto = new AcaoObjetoVO();
					acaoobjeto.setCodigoObjeto(getVo().getCodigoObjeto());
					acaoobjeto.setNomeAcaoObjeto(Constants.INSERIR);
					acaoobjeto.setNewRecord(true);
					acaoobjetodao.persist(acaoobjeto);
				}
				
				// Procura pela ação editar
				found = false;
				for (AcaoObjetoVO acaoobjeto : list) {
					if (acaoobjeto.getNomeAcaoObjeto().equals(Constants.ALTERAR)) {
						found = true;
						break;
					}
				}
				if (!found) { // Cadastra a ação de editar
					AcaoObjetoVO acaoobjeto = new AcaoObjetoVO();
					acaoobjeto.setCodigoObjeto(getVo().getCodigoObjeto());
					acaoobjeto.setNomeAcaoObjeto(Constants.ALTERAR);
					acaoobjeto.setNewRecord(true);
					acaoobjetodao.persist(acaoobjeto);
				}
				
				// Procura pela ação excluir
				found = false;
				for (AcaoObjetoVO acaoobjeto : list) {
					if (acaoobjeto.getNomeAcaoObjeto().equals(Constants.EXCLUIR)) {
						found = true;
						break;
					}
				}
				if (!found) { // Cadastra a ação de excluir
					AcaoObjetoVO acaoobjeto = new AcaoObjetoVO();
					acaoobjeto.setCodigoObjeto(getVo().getCodigoObjeto());
					acaoobjeto.setNomeAcaoObjeto(Constants.EXCLUIR);
					acaoobjeto.setNewRecord(true);
					acaoobjetodao.persist(acaoobjeto);
				}
				
				// Procura pela ação visualizar
				found = false;
				for (AcaoObjetoVO acaoobjeto : list) {
					if (acaoobjeto.getNomeAcaoObjeto().equals(Constants.VISUALIZAR)) {
						found = true;
						break;
					}
				}
				if (!found) { // Cadastra a ação de inserir
					AcaoObjetoVO acaoobjeto = new AcaoObjetoVO();
					acaoobjeto.setCodigoObjeto(getVo().getCodigoObjeto());
					acaoobjeto.setNomeAcaoObjeto(Constants.VISUALIZAR);
					acaoobjeto.setNewRecord(true);
					acaoobjetodao.persist(acaoobjeto);
				}
				
			} else {
				// Ações padrão de consulta
				// Procura pela ação execute
				boolean found = false;
				for (AcaoObjetoVO acaoobjeto : list) {
					if (acaoobjeto.getNomeAcaoObjeto().equals(Constants.EXECUTE)) {
						found = true;
						break;
					}
				}
				if (!found) { // Cadastra a ação de execute
					AcaoObjetoVO acaoobjeto = new AcaoObjetoVO();
					acaoobjeto.setCodigoObjeto(getVo().getCodigoObjeto());
					acaoobjeto.setNomeAcaoObjeto(Constants.EXECUTE);
					acaoobjeto.setNewRecord(true);
					acaoobjetodao.persist(acaoobjeto);
				}
			}
			write("ok");
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage(), ""));
		}
	}
}