package br.com.orlandoburli.personalerp.facades.rh.tabelasalariofamilia;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.TabelaSalarioFamiliaDAO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioFamiliaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TabelaSalarioFamiliaCadastroFacade extends BaseCadastroFlexFacade<TabelaSalarioFamiliaVO, TabelaSalarioFamiliaDAO> {

	private static final long serialVersionUID = 1L;
	
	public TabelaSalarioFamiliaCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DataInicialVigenciaSalarioFamilia", "Data Inicial"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getValorInicialSalarioFamilia() == null) {
			this.messages.add(new MessageVO("Campo Valor Inicial é Obrigatório!", "ValorInicialSalarioFamilia"));
			return false;
		}
		if (getVo().getValorFinalSalarioFamilia() == null) {
			this.messages.add(new MessageVO("Campo Valor Final é Obrigatório!", "ValorFinalSalarioFamilia"));
			return false;
		}
		if (getVo().getValorSalarioFamilia().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("Salário Família deve ser maior do que zero!", "ValorSalarioFamilia"));
			return false;
		}
		
		return super.doBeforeSave();
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaSalarioFamiliaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaSalarioFamiliaVO.class;
	}
}
