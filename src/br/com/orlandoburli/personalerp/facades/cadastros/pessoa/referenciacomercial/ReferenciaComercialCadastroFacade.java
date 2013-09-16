package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.referenciacomercial;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.pessoa.ReferenciaComercialDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.ReferenciaComercialVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ReferenciaComercialCadastroFacade extends BaseCadastroFlexFacade<ReferenciaComercialVO, ReferenciaComercialDAO> {

	private static final long	serialVersionUID	= 1L;
	
	public ReferenciaComercialCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("LocalReferenciaComercial", "Local"));
	}

	@Override
	public Class<?> getDAOClass() {
		return ReferenciaComercialDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return ReferenciaComercialVO.class;
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setDataCadastroReferenciaComercial(Utils.getToday());
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (!getVo().IsNew() && !getVo().getDataCadastroReferenciaComercial().equals(Utils.getToday())) {
			this.messages.add(new MessageVO("Modificação não permitida! Data diferente de hoje!", ""));
		}
		return super.doBeforeSave();
	}

}
