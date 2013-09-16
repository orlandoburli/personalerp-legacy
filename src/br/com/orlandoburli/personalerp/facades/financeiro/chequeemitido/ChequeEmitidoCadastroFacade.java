package br.com.orlandoburli.personalerp.facades.financeiro.chequeemitido;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.financeiro.ChequeEmitidoDAO;
import br.com.orlandoburli.core.dao.financeiro.ContaBancariaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.vo.financeiro.ContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ChequeEmitidoCadastroFacade extends BaseCadastroFlexFacade<ChequeEmitidoVO, ChequeEmitidoDAO> {

	private static final long serialVersionUID = 1L;
	
	private String DestinatarioQuery;
	private Integer CodigoPessoaDestinatario;
	private Integer CodigoLojaDestinatario;
	private Integer CodigoEmpresaDestinatario;
	
	public ChequeEmitidoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("ValorChequeEmitido", "Valor"));
		addNewValidator(new NotEmptyValidator("DataEmissaoChequeEmitido", "Data Emissão"));
		addNewValidator(new NotEmptyValidator("CodigoContaBancaria", "Conta Bancária", "ContaBancaria"));
		addNewValidator(new NotEmptyValidator("CodigoPessoaDestinatario", "Destinatário", "Destinatario"));
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		try {
			setVo(dao.get(getVo()));
		
			if (getVo().getCodigoPagamento() != null) {
				this.messages.add(new MessageVO("Esse cheque está vinculado a um pagamento, e não pode ser excluído!"));
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeDelete();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (getVo().getValorChequeEmitido().compareTo(BigDecimal.ZERO) <= 0) {
			this.messages.add(new MessageVO("Valor do Cheque deve ser maior que zero!", "ValorChequeEmitido"));
			return false;
		}
		if (getVo().getStatusChequeEmitido() != null && getVo().getStatusChequeEmitido().equals("C")) {
			this.messages.add(new MessageVO("Cheque já foi compensado, não pode ser alterado!"));
			return false;
		}
		return super.doBeforeSave();
	}
	
	@IgnoreMethodAuthentication
	public void contasbancarias() {
		ContaBancariaDAO _dao = new ContaBancariaDAO();
		try {
			write(Utils.voToXml(_dao.getList(new ContaBancariaVO()), _dao.getListCount(new ContaBancariaVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void destinatario() {
		PessoaVO filter = new PessoaVO();
		
		if (getDestinatarioQuery() != null) {
			filter.setNomeRazaoSocialPessoa(getDestinatarioQuery() + "%");
		}
		filter.setCodigoEmpresa(getCodigoEmpresaDestinatario());
		filter.setCodigoLoja(getCodigoLojaDestinatario());
		filter.setCodigoPessoa(getCodigoPessoaDestinatario());
		
		PessoaDAO _dao = new PessoaDAO();
		
		try {
			List<PessoaVO> list = _dao.getList(filter);
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void compensar() {
		try {
			if (getVo().getDataCompensacaoChequeEmitido() == null) {
				writeErrorMessage("Informe a data de compensação!");
				return;
			}
			
			ChequeEmitidoVO cheque = dao.get(getVo());
			
			if (cheque.getStatusChequeEmitido() != null && cheque.getStatusChequeEmitido().equals("C")) {
				writeErrorMessage("Cheque já foi compensado!");
				return;
			}
			
			cheque.setDataCompensacaoChequeEmitido(getVo().getDataCompensacaoChequeEmitido());
			cheque.setStatusChequeEmitido("C");
			cheque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
			cheque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
			cheque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
			
			Calendar dataEmissao = Utils.dateToCalendar(cheque.getDataEmissaoChequeEmitido());
			Calendar dataCompensacao = Utils.dateToCalendar(cheque.getDataCompensacaoChequeEmitido());
			
			if (dataEmissao.compareTo(dataCompensacao) > 0) {
				writeErrorMessage("Data de compensação não pode ser maior que a data de emissão!");
				return;
			}
			
			// Gera a movimentação bancária na conta
			getGenericDao().setAutoCommit(false);
			MovimentacaoContaBancariaVO mov = new MovimentacaoContaBancariaVO();
			
			mov.setNewRecord(true);
			mov.setCodigoEmpresa(cheque.getCodigoEmpresaContaBancaria());
			mov.setCodigoLoja(cheque.getCodigoLojaContaBancaria());
			mov.setCodigoContaBancaria(cheque.getCodigoContaBancaria());
			mov.setValorMovimentacaoContaBancaria(cheque.getValorChequeEmitido());
			mov.setOperacaoMovimentacaoContaBancaria(MovimentacaoContaBancariaVO.OPERACAO_DEBITO);
			mov.setDataMovimentacaoContaBancaria(cheque.getDataCompensacaoChequeEmitido());
			mov.setDescricaoMovimentacaoContaBancaria("Compensação do cheque numero " + cheque.getNumeroChequeEmitido());
			
			getGenericDao().persist(mov);
			
			// Finaliza
			cheque.setSequencialMovimentacaoContaBancaria(mov.getSequencialMovimentacaoContaBancaria());
			getGenericDao().persist(cheque);
			getGenericDao().commit();
			
			write("ok");
		} catch (SQLException e) {
			getGenericDao().rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void estornar() {
		try {
			getGenericDao().setAutoCommit(false);
			
			ChequeEmitidoVO cheque = (ChequeEmitidoVO) getGenericDao().get(getVo());
			
			if (cheque.getStatusChequeEmitido() != null && !cheque.getStatusChequeEmitido().equals("C")) {
				writeErrorMessage("Cheque não foi compensado!");
				return;
			}
			
			MovimentacaoContaBancariaVO mov = new MovimentacaoContaBancariaVO();
			mov.setCodigoEmpresa(cheque.getCodigoEmpresaContaBancaria());
			mov.setCodigoLoja(cheque.getCodigoLojaContaBancaria());
			mov.setCodigoContaBancaria(cheque.getCodigoContaBancaria());
			mov.setSequencialMovimentacaoContaBancaria(cheque.getSequencialMovimentacaoContaBancaria());
			mov.setDescricaoMovimentacaoContaBancaria("Compensação do cheque número " + cheque.getNumeroChequeEmitido());
			
			mov = (MovimentacaoContaBancariaVO) getGenericDao().get(mov);
			
			cheque.setSequencialMovimentacaoContaBancaria(null);
			cheque.setDataCompensacaoChequeEmitido(null);
			cheque.setStatusChequeEmitido("N");
			
			getGenericDao().persist(cheque);
			
			if (mov != null) {
				getGenericDao().remove(mov);
			}
			
			getGenericDao().commit();
			
			write("ok");
			
		} catch (SQLException e) {
			getGenericDao().rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return ChequeEmitidoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ChequeEmitidoVO.class;
	}

	public void setDestinatarioQuery(String destinatarioQuery) {
		DestinatarioQuery = destinatarioQuery;
	}

	public String getDestinatarioQuery() {
		return DestinatarioQuery;
	}

	public void setCodigoPessoaDestinatario(Integer codigoPessoaDestinatario) {
		CodigoPessoaDestinatario = codigoPessoaDestinatario;
	}

	public Integer getCodigoPessoaDestinatario() {
		return CodigoPessoaDestinatario;
	}

	public void setCodigoLojaDestinatario(Integer codigoLojaDestinatario) {
		CodigoLojaDestinatario = codigoLojaDestinatario;
	}

	public Integer getCodigoLojaDestinatario() {
		return CodigoLojaDestinatario;
	}

	public void setCodigoEmpresaDestinatario(Integer codigoEmpresaDestinatario) {
		CodigoEmpresaDestinatario = codigoEmpresaDestinatario;
	}

	public Integer getCodigoEmpresaDestinatario() {
		return CodigoEmpresaDestinatario;
	}
}