package br.com.orlandoburli.personalerp.facades.rh.contratotrabalho;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.CargoDAO;
import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.HorarioTrabalhoDAO;
import br.com.orlandoburli.core.dao.rh.MotivoDesligamentoDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.CargoVO;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.HorarioTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.MotivoDesligamentoVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ContratoTrabalhoCadastroFacade extends BaseCadastroFlexFacade<ContratoTrabalhoVO, ContratoTrabalhoDAO> {

	private static final long serialVersionUID = 1L;

	public ContratoTrabalhoCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoCargo", "Cargo", "Cargo"));
		addNewValidator(new NotEmptyValidator("CodigoHorarioTrabalho", "Horário de Trabalho", "HorarioTrabalho"));
		addNewValidator(new NotEmptyValidator("DataInicioContratoTrabalho", "Data Inicial"));
		addNewValidator(new NotEmptyValidator("DiasExperiencia1ContratoTrabalho", "Dias de Experiência (inicial)"));
		addNewValidator(new NotEmptyValidator("DiasExperiencia2ContratoTrabalho", "Dias de Experiência (expansão)"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getStatusContratoTrabalho().equalsIgnoreCase(ContratoTrabalhoVO.STATUS_ATIVO)) {
			if (getVo().getCodigoMotivoDesligamento() != null) {
				this.messages.add(new MessageVO("O motivo do desligamento só pode ser informado caso o status seja INATIVO!", "MotivoDesligamento"));
				return false;
			}
			if (getVo().getDataFimContratoTrabalho() != null) {
				this.messages.add(new MessageVO("A data fim do contrato só pode ser informada caso o status seja INATIVO!", "DataFimContratoTrabalho"));
				return false;
			}
			
			// Verifica se já possui outro contrato ativo
			ContratoTrabalhoVO filter = new ContratoTrabalhoVO();
			filter.setCodigoEmpresaFuncionario(getVo().getCodigoEmpresaFuncionario());
			filter.setCodigoLojaFuncionario(getVo().getCodigoLojaFuncionario());
			filter.setCodigoFuncionario(getVo().getCodigoFuncionario());
			
			try {
				List<ContratoTrabalhoVO> list = dao.getList(filter);
				if (list.size() > 0) {
					boolean found = false;
					for (ContratoTrabalhoVO item : list) {
						if (!item.getCodigoContratoTrabalho().equals(getVo().getCodigoContratoTrabalho()) || !item.getCodigoEmpresa().equals(getVo().getCodigoEmpresa()) || !item.getCodigoLoja().equals(getVo().getCodigoLoja())) {
							if (item.getStatusContratoTrabalho().equalsIgnoreCase(ContratoTrabalhoVO.STATUS_ATIVO)) {
								found = true;
								break;
							}
						}
					}
					if (found) {
						this.messages.add(new MessageVO("Já existe um contrato ativo para este funcionário!\nDesative o antigo antes de criar um novo Contrato.", "StatusContratoTrabalho"));
						return false;
					}
				}
			} catch (SQLException e) {
				this.messages.add(new MessageVO(e.getMessage()));
				return false;
			}
			
		} else if (getVo().getStatusContratoTrabalho().equalsIgnoreCase(ContratoTrabalhoVO.STATUS_INATIVO)) {
			if (getVo().getCodigoMotivoDesligamento() == null) {
				this.messages.add(new MessageVO("O motivo do desligamento é obrigatório quando o status é INATIVO!", "MotivoDesligamento"));
				return false;
			}
			if (getVo().getDataFimContratoTrabalho() == null) {
				this.messages.add(new MessageVO("A data fim do contrato é obrigatória quando o status é INATIVO!", "DataFimContratoTrabalho"));
				return false;
			}
			if (getVo().getDataFimContratoTrabalho().compareTo(getVo().getDataInicioContratoTrabalho())  <= 0) {
				this.messages.add(new MessageVO("A data fim do contrato deve ser maior que a data de início!", "DataFimContratoTrabalho"));
				return false;
			}
		}
		return super.doBeforeSave();
	}

	@Override
	protected Class<?> getDAOClass() {
		return ContratoTrabalhoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ContratoTrabalhoVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void cargos() {
		CargoDAO _dao = new CargoDAO();
		try {
			List<CargoVO> list = _dao.getList(new CargoVO());
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void lojas() {
		LojaDAO _dao = new LojaDAO();
		try {
			List<LojaVO> list = _dao.getList(null, "NomeLoja");
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void motivosdesligamento() {
		MotivoDesligamentoDAO _dao = new MotivoDesligamentoDAO();
		try {
			write(Utils.voToXml(_dao.getList(new MotivoDesligamentoVO()), _dao.getListCount(new MotivoDesligamentoVO())));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void horariostrabalho() {
		HorarioTrabalhoDAO _dao = new HorarioTrabalhoDAO();
		try {
			write(Utils.voToXml(_dao.getList(new HorarioTrabalhoVO()), _dao.getListCount(new HorarioTrabalhoVO())));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}