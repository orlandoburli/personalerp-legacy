package br.com.orlandoburli.personalerp.facades.rh.faltafuncionario;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.FaltaFuncionarioDAO;
import br.com.orlandoburli.core.dao.rh.MotivoFaltaFuncionarioDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.FaltaFuncionarioVO;
import br.com.orlandoburli.core.vo.rh.MotivoFaltaFuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class FaltaFuncionarioCadastroFacade extends BaseCadastroFlexFacade<FaltaFuncionarioVO, FaltaFuncionarioDAO> {

	private static final long serialVersionUID = 1L;
	
	private String ContratoTrabalhoQuery;
	private Integer CodigoContratoTrabalho;
	private Integer CodigoLojaContratoTrabalho;
	private Integer CodigoEmpresaContratoTrabalho;

	public FaltaFuncionarioCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoContratoTrabalho", "Funcionário", "ContratoTrabalho"));
		addNewValidator(new NotEmptyValidator("DataFaltaFuncionario", "Data"));
		addNewValidator(new NotEmptyValidator("HorasFaltaFuncionario", "Horas"));
		addNewValidator(new NotEmptyValidator("CodigoMotivoFaltaFuncionario", "Motivo", "MotivoFaltaFuncionario"));
	}
	
//	@IgnoreMethodAuthentication
//	public void funcionarios() {
//		ContratoTrabalhoVO _filter = new ContratoTrabalhoVO();
//		
//		// Para inclusao, somente de contratos ativos
//		if (getOperacao().equalsIgnoreCase(Constants.INSERIR)) {
//			_filter.setStatusContratoTrabalho(ContratoTrabalhoVO.STATUS_ATIVO);
//		}
//		
//		ContratoTrabalhoDAO _dao = new ContratoTrabalhoDAO();
//		try {
//			write(Utils.voToXml(_dao.getList(_filter), _dao.getListCount(_filter)));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	@IgnoreMethodAuthentication
	public void contratotrabalho() {
		ContratoTrabalhoVO filter = new ContratoTrabalhoVO();
		
		if (getContratoTrabalhoQuery() != null) {
			filter.setNomeFuncionario(getContratoTrabalhoQuery() + "%");
		}
		filter.setCodigoEmpresa(getCodigoEmpresaContratoTrabalho());
		filter.setCodigoLoja(getCodigoLojaContratoTrabalho());
		filter.setCodigoContratoTrabalho(getCodigoContratoTrabalho());
		
		ContratoTrabalhoDAO _dao = new ContratoTrabalhoDAO();
		
		try {
			List<ContratoTrabalhoVO> list = _dao.getList(filter);
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void motivofaltafuncionario() {
		MotivoFaltaFuncionarioDAO _dao = new MotivoFaltaFuncionarioDAO();
		try {
			write(Utils.voToXml(_dao.getList(new MotivoFaltaFuncionarioVO()), _dao.getListCount(new MotivoFaltaFuncionarioVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return FaltaFuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FaltaFuncionarioVO.class;
	}

	public void setContratoTrabalhoQuery(String contratoTrabalhoQuery) {
		ContratoTrabalhoQuery = contratoTrabalhoQuery;
	}

	public String getContratoTrabalhoQuery() {
		return ContratoTrabalhoQuery;
	}

	public void setCodigoContratoTrabalho(Integer codigoContratoTrabalho) {
		CodigoContratoTrabalho = codigoContratoTrabalho;
	}

	public Integer getCodigoContratoTrabalho() {
		return CodigoContratoTrabalho;
	}

	public void setCodigoLojaContratoTrabalho(Integer codigoLojaContratoTrabalho) {
		CodigoLojaContratoTrabalho = codigoLojaContratoTrabalho;
	}

	public Integer getCodigoLojaContratoTrabalho() {
		return CodigoLojaContratoTrabalho;
	}

	public void setCodigoEmpresaContratoTrabalho(
			Integer codigoEmpresaContratoTrabalho) {
		CodigoEmpresaContratoTrabalho = codigoEmpresaContratoTrabalho;
	}

	public Integer getCodigoEmpresaContratoTrabalho() {
		return CodigoEmpresaContratoTrabalho;
	}
}