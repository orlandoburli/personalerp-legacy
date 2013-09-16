package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiosaidatora;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiosaidatora.RomaneioSaidaToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiosaidatora.RomaneioSaidaToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class RomaneioSaidaToraCadastroFacade extends BaseCadastroFlexFacade<RomaneioSaidaToraVO, RomaneioSaidaToraDAO> {

	private static final long serialVersionUID = 1L;
	
	public RomaneioSaidaToraCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DataRomaneio", "Data do Romaneio"));
		addNewValidator(new NotEmptyValidator("CodigoPessoaCliente", "Cliente", "Cliente"));
		this.setWriteVoOnInsert(true);
		this.setWriteVoOnUpdate(true);
	}
	
	@IgnoreMethodAuthentication
	public void clientes() {
		EnderecoPessoaDAO _dao = new EnderecoPessoaDAO();
		
		try {
			List<EnderecoPessoaVO> list = _dao.getList(new EnderecoPessoaVO());
			int count = _dao.getListCount(new EnderecoPessoaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		getVo().setDataHoraLancamentoRomaneio(Utils.getNow());
		return super.doBeforeSave();
	}

	@Override
	protected Class<?> getDAOClass() {
		return RomaneioSaidaToraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return RomaneioSaidaToraVO.class;
	}
}