package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.referenciabancaria;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.pessoa.ReferenciaBancariaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.ReferenciaBancariaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ReferenciaBancariaCadastroFacade extends BaseCadastroFlexFacade<ReferenciaBancariaVO, ReferenciaBancariaDAO> {

	public ReferenciaBancariaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		
		this.addNewValidator(new NotEmptyValidator("CodigoBancoReferenciaBancaria", "Banco"));
		this.addNewValidator(new NotEmptyValidator("AgenciaReferenciaBancaria", "Agência"));
		this.addNewValidator(new NotEmptyValidator("ContaReferenciaBancaria", "Conta"));
		this.addNewValidator(new NotEmptyValidator("DataReferenciaBancaria", "Data Referência"));
	}
	
	private static final long	serialVersionUID	= 1L;

	@Override
	public Class<?> getDAOClass() {
		return ReferenciaBancariaDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return ReferenciaBancariaVO.class;
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setDataInclusaoReferenciaBancaria(Utils.getToday());
		return super.doBeforeInsert();
	}
}
