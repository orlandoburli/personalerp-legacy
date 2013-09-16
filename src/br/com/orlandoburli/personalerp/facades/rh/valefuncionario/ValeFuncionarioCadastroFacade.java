package br.com.orlandoburli.personalerp.facades.rh.valefuncionario;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.ValeFuncionarioDAO;
import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.ValeFuncionarioVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ValeFuncionarioCadastroFacade extends BaseCadastroFlexFacade<ValeFuncionarioVO, ValeFuncionarioDAO> {

	private static final long serialVersionUID = 1L;

	public ValeFuncionarioCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoContratoTrabalho", "Funcionário", "ContratoTrabalho"));
		addNewValidator(new NotEmptyValidator("DataValeFuncionario", "Data"));
		addNewValidator(new NotEmptyValidator("ValorValeFuncionario", "Valor"));
		addNewValidator(new NotEmptyValidator("CodigoVerba", "Verba"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getValorValeFuncionario().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("Campo valor deve ser maior que ZERO!", "ValorValeFuncionario"));
			return false;
		}
		return super.doBeforeSave();
	}
	
	@IgnoreMethodAuthentication
	public void funcionarios() {
		ContratoTrabalhoVO _filter = new ContratoTrabalhoVO();
		
		// Para inclusao, somente de contratos ativos
		if (getOperacao().equalsIgnoreCase(Constants.INSERIR)) {
			_filter.setStatusContratoTrabalho(ContratoTrabalhoVO.STATUS_ATIVO);
		}
		
		ContratoTrabalhoDAO _dao = new ContratoTrabalhoDAO();
		try {
			write(Utils.voToXml(_dao.getList(_filter), _dao.getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void verbas() {
		VerbaVO _filter = new VerbaVO();
		VerbaDAO _dao = new VerbaDAO();
		try {
			write(Utils.voToXml(_dao.getList(_filter), _dao.getListCount(_filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	/**
	 * Este método cadastra automaticamente as demais parcelas de uma conta a pagar,
	 * baseado no numero de parcelas.
	 */
	public void autocadastro() {
		try {
			Date dataVencimentoBase = getVo().getDataValeFuncionario();
			dao.setAutoCommit(false);
			
			// Verifica se tem mais de uma parcela
			if (this.getVo().getQuantidadeParcelasValeFuncionario() != null && this.getVo().getQuantidadeParcelasValeFuncionario().compareTo(1) > 0) {
				for (int i = this.getVo().getNumeroParcelaValeFuncionario(); i < this.getVo().getQuantidadeParcelasValeFuncionario(); i++) {
					
					this.getVo().setNumeroParcelaValeFuncionario(i + 1);
					Calendar dataVencimento = Utils.dateToCalendar(dataVencimentoBase);
					dataVencimento.add(Calendar.MONTH, i);
					this.getVo().setDataValeFuncionario(Utils.calendarToDate(dataVencimento));
					this.getVo().setNewRecord(true);
					this.doBeforeInsert();
					dao.persist(getVo());
				}
			}
			
			dao.commit();
			write("ok");
		} catch (SQLException e) {
			dao.rollback();
			writeErrorMessage(e.getMessage());
		}
	}

	@Override
	protected Class<ValeFuncionarioDAO> getDAOClass() {
		return ValeFuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ValeFuncionarioVO.class;
	}
}