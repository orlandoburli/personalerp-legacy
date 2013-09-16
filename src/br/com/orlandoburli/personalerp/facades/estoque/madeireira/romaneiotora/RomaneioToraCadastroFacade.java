package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiotora;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.cadastro.madeireira.MotoristaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.ItemRomaneioToraDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.RomaneioToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.madeireira.MotoristaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.RomaneioToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class RomaneioToraCadastroFacade extends BaseCadastroFlexFacade<RomaneioToraVO, RomaneioToraDAO> {

	private static final long serialVersionUID = 1L;

	public RomaneioToraCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NumeroRomaneio", "Número do Romaneio"));
		addNewValidator(new NotEmptyValidator("DataRomaneio", "Data do Romaneio"));
		addNewValidator(new NotEmptyValidator("CodigoPessoaFornecedor", "Fornecedor", "Fornecedor"));
		this.setWriteVoOnInsert(true);
		this.setWriteVoOnUpdate(true);
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getVo().setCodigoLoja(getLojasessao().getCodigoLoja());
		this.getVo().setDataHoraLancamentoRomaneio(Utils.getNow());
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		// Exclusao dos itens
		ItemRomaneioToraVO itemfilter = new ItemRomaneioToraVO();
		itemfilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
		itemfilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemfilter.setCodigoLoja(getVo().getCodigoLoja());
		ItemRomaneioToraDAO itemdao = new ItemRomaneioToraDAO();
		try {
			List<ItemRomaneioToraVO> list = itemdao.getList(itemfilter);
			for (ItemRomaneioToraVO item : list) {
				itemdao.remove(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeDelete();
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return RomaneioToraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return RomaneioToraVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void fornecedores() {
		PessoaDAO _dao = new PessoaDAO();
		
		try {
			List<PessoaVO> list = _dao.getList(new PessoaVO(), "NomeRazaoSocialPessoa");
			int count = _dao.getListCount(new PessoaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void motoristas() {
		MotoristaDAO _dao = new MotoristaDAO();
		
		try {
			List<MotoristaVO> list = _dao.getList(new MotoristaVO(), "NomeMotorista");
			int count = _dao.getListCount(new MotoristaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}