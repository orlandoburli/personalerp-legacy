package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.folha.FolhaPagamentoDAO;
import br.com.orlandoburli.core.dao.rh.folha.VerbaFolhaPagamentoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.rh.folha.ContratoTrabalhoFolhaPagamentoVO;
import br.com.orlandoburli.core.vo.rh.folha.VerbaFolhaPagamentoVO;
import br.com.orlandoburli.core.dao.rh.folha.ContratoTrabalhoFolhaPagamentoDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class FolhaPagamentoCadastroFacade extends BaseCadastroFlexFacade<FolhaPagamentoVO, FolhaPagamentoDAO> {

	private static final long serialVersionUID = 1L;

	public FolhaPagamentoCadastroFacade(HttpServletRequest request,	HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	public void processar() {
		try {
			// Zera o processamento antes de iniciar a thread...
			setVo(dao.get(getVo()));
			getVo().setStatusFolhaPagamento(FolhaPagamentoVO.STATUS_ABERTA);
			getVo().setPercentualProcessadoFolhaPagamento(BigDecimal.ZERO);
			dao.persist(getVo());
			
			ThreadFolhaPagamento processamento = new ThreadFolhaPagamento();
			processamento.setFolha(getVo());
			processamento.start();
			processamento.interrupt();
			
			write("ok");
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void edicaofolha() {
		try {
			setVo(dao.get(getVo()));
			// Instancia DAO's
			ContratoTrabalhoFolhaPagamentoDAO daoContratos = new ContratoTrabalhoFolhaPagamentoDAO();
			VerbaFolhaPagamentoDAO daoVerbaFolha = new VerbaFolhaPagamentoDAO();
			
			// Lista contratos de trabalho
			ContratoTrabalhoFolhaPagamentoVO _contratoFilter = new ContratoTrabalhoFolhaPagamentoVO();
			
			_contratoFilter.setCodigoFolhaPagamento(getVo().getCodigoFolhaPagamento());
			_contratoFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			_contratoFilter.setCodigoLoja(getVo().getCodigoLoja());
			
			List<ContratoTrabalhoFolhaPagamentoVO> listContratos = daoContratos.getList(_contratoFilter, "NomeFuncionario");
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>");
			
			// Lista Verbas dos contratos de trabalho
			for (ContratoTrabalhoFolhaPagamentoVO contrato : listContratos) {
				
				VerbaFolhaPagamentoVO _verbaFilter = new VerbaFolhaPagamentoVO();
				_verbaFilter.setCodigoFolhaPagamento(contrato.getCodigoFolhaPagamento());
				_verbaFilter.setCodigoEmpresa(contrato.getCodigoEmpresa());
				_verbaFilter.setCodigoLoja(contrato.getCodigoLoja());
				_verbaFilter.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresaContratoTrabalho());
				_verbaFilter.setCodigoLojaContratoTrabalho(contrato.getCodigoLojaContratoTrabalho());
				_verbaFilter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
				
				List<VerbaFolhaPagamentoVO> listVerbas = daoVerbaFolha.getList(_verbaFilter);
				
				contrato.setVerbas(listVerbas);
				
				for (VerbaFolhaPagamentoVO verba : listVerbas) {
					sb.append("<contratotrabalhofolhapagamentovo>");
					sb.append(Utils.fieldsToXml(contrato));
					sb.append(Utils.fieldsToXml(verba));
					sb.append("</contratotrabalhofolhapagamentovo>");
				}
			}
			sb.append("</list>");
			
			System.out.println(sb);
			
			write(sb.toString());
			
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void relatorio() {
		
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
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		zeraFolha(getVo());
		return super.doBeforeDelete();
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return FolhaPagamentoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FolhaPagamentoVO.class;
	}
	
	/**
	 * "Zera" um processamento de folha
	 * @param folha Folha a ser zerada
	 */
	private void zeraFolha(FolhaPagamentoVO folha) {
		try {
			VerbaFolhaPagamentoDAO verbaFolhaDao = new VerbaFolhaPagamentoDAO();
			VerbaFolhaPagamentoVO verbaFolhaFilter = new VerbaFolhaPagamentoVO();
			
			verbaFolhaFilter.setCodigoEmpresa(folha.getCodigoEmpresa());
			verbaFolhaFilter.setCodigoLoja(folha.getCodigoLoja());
			verbaFolhaFilter.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
			
			List<VerbaFolhaPagamentoVO> listVerbasFolhas = verbaFolhaDao.getList(verbaFolhaFilter);
			
			// Zera verbas da folha
			for (VerbaFolhaPagamentoVO verbafolha : listVerbasFolhas) {
				verbaFolhaDao.remove(verbafolha);
			}
			
			ContratoTrabalhoFolhaPagamentoDAO contratoFolhaDao = new ContratoTrabalhoFolhaPagamentoDAO();
			ContratoTrabalhoFolhaPagamentoVO contratoFolhaFilter = new ContratoTrabalhoFolhaPagamentoVO();
			
			contratoFolhaFilter.setCodigoEmpresa(folha.getCodigoEmpresa());
			contratoFolhaFilter.setCodigoLoja(folha.getCodigoLoja());
			contratoFolhaFilter.setCodigoFolhaPagamento(folha.getCodigoFolhaPagamento());
			
			List<ContratoTrabalhoFolhaPagamentoVO> listContratosFolha = contratoFolhaDao.getList(contratoFolhaFilter);
			
			// Zera contratos da folha
			for (ContratoTrabalhoFolhaPagamentoVO contratoFolha : listContratosFolha) {
				contratoFolhaDao.remove(contratoFolha);
			}
			
			// Zera percentual de processamento da folha
			folha.setPercentualProcessadoFolhaPagamento(BigDecimal.ZERO);
			folha.setStatusFolhaPagamento(FolhaPagamentoVO.STATUS_PROCESSANDO);
			dao.persist(folha);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}