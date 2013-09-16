package br.com.orlandoburli.personalerp.facades.rh.verbacontratotrabalho;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.VerbaContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class VerbaContratoTrabalhoCadastroFacade extends BaseCadastroFlexFacade<VerbaContratoTrabalhoVO, VerbaContratoTrabalhoDAO> {

	private static final long serialVersionUID = 1L;

	public VerbaContratoTrabalhoCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoVerba", "Verba"));
		addNewValidator(new NotEmptyValidator("ValorVerba", "Valor"));
	}
	
	@IgnoreMethodAuthentication
	public void verbas() {
		VerbaDAO _dao = new VerbaDAO();
		VerbaVO _filter = new VerbaVO();
		//_filter.setTipoVerba("C"); // Mostrar somente os créditos
		try {
			write(Utils.voToXml(_dao.getList(_filter), _dao.getListCount(_filter)));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		
		try {
			VerbaDAO verbaDao = new VerbaDAO();
			VerbaVO verba = verbaDao.get(new Object[]{getVo().getCodigoEmpresaVerba(), getVo().getCodigoLojaVerba(), getVo().getCodigoVerba()});
		
			if (verba.getTipoCalculoVerba().equalsIgnoreCase(VerbaVO.TIPO_CALCULO_SISTEMA)) {
				//vo.setValorVerba(BigDecimal.ZERO);
			} else if (getVo().getValorVerba().compareTo(BigDecimal.ZERO) <= 0) {
				this.messages.add(new MessageVO("O valor deve ser maior que zero!", "ValorVerba"));
				return false;
			}
			
			// Verifica se o contrato ainda está ativo
			ContratoTrabalhoVO contrato = new ContratoTrabalhoVO();
			contrato.setCodigoEmpresa(getVo().getCodigoEmpresa());
			contrato.setCodigoLoja(getVo().getCodigoLoja());
			contrato.setCodigoContratoTrabalho(getVo().getCodigoContratoTrabalho());
			ContratoTrabalhoDAO _dao = new ContratoTrabalhoDAO();
			
			contrato = _dao.get(contrato);
			if (contrato != null) {
				if (contrato.getStatusContratoTrabalho().equalsIgnoreCase(ContratoTrabalhoVO.STATUS_INATIVO)) {
					this.messages.add(new MessageVO("Este contrato está INATIVO e não aceita mais modificações!", "Verba"));
					return false;
				}
			} else {
				this.messages.add(new MessageVO("Contrato não encontrado!", "Verba"));
				return false;
			}
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
			return false;
		}
		
		return super.doBeforeSave();
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("verbacontratotrabalho_pk") >= 0) {
			this.messages.add(new MessageVO("Esta verba já foi lançada para este contrato!", "Verba"));
		}
		super.doBeforeWriteSqlErro(e);
	}

	@Override
	protected Class<?> getDAOClass() {
		return VerbaContratoTrabalhoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return VerbaContratoTrabalhoVO.class;
	}
}