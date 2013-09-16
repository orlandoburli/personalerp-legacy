package br.com.orlandoburli.personalerp.facades.financeiro.planopagamentovenda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import br.com.orlandoburli.core.dao.financeiro.ParcelaPlanoPagamentoVendaDAO;
import br.com.orlandoburli.core.dao.financeiro.PlanoPagamentoVendaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.financeiro.ParcelaPlanoPagamentoVendaVO;
import br.com.orlandoburli.core.vo.financeiro.PlanoPagamentoVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class PlanoPagamentoVendaCadastroFacade extends BaseCadastroFlexFacade<PlanoPagamentoVendaVO, PlanoPagamentoVendaDAO> {

	private static final long serialVersionUID = 1L;
	
	@OutjectSession
	@InstantiateObject
	private ArrayList<ParcelaPlanoPagamentoVendaVO> parcelas;
	@OutjectSession
	@InstantiateObject
	private ArrayList<ParcelaPlanoPagamentoVendaVO> parcelasexcluidas;
	
	private Integer Dias;
	
	private Integer Posicao;
	
	public PlanoPagamentoVendaCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("NomePlanoPagamento", "Nome"));
	}
	
	@Override
	public void visualizar() {
		super.visualizar();
		
		if (this.getVo() == null) {
			return;
		}
		
		// Busca as parcelas
		ParcelaPlanoPagamentoVendaVO filter = new ParcelaPlanoPagamentoVendaVO();
		filter.setCodigoPlanoPagamento(getVo().getCodigoPlanoPagamento());
		ParcelaPlanoPagamentoVendaDAO _dao = new ParcelaPlanoPagamentoVendaDAO();
		
		try {
			setParcelas((ArrayList<ParcelaPlanoPagamentoVendaVO>) _dao.getList(filter));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doAfterSave() throws SQLException {
		for (ParcelaPlanoPagamentoVendaVO p : parcelas) {
			
			p.setCodigoPlanoPagamento(getVo().getCodigoPlanoPagamento());
			
			p.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
			p.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
			p.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
			
			getGenericDao().persist(p);
		}
		
		for (ParcelaPlanoPagamentoVendaVO p : parcelasexcluidas) {
			if (!p.IsNew()) {
				getGenericDao().remove(p);
			}
		}
		super.doAfterSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		if (this.getVo() == null) {
			return false;
		}
		
		// Busca as parcelas
		ParcelaPlanoPagamentoVendaVO filter = new ParcelaPlanoPagamentoVendaVO();
		filter.setCodigoPlanoPagamento(getVo().getCodigoPlanoPagamento());
		
		for (IValueObject i : getGenericDao().getList(filter)) {
			getGenericDao().remove(i);
		}
		
		return super.doBeforeDelete();
	}
	
	@IgnoreMethodAuthentication
	public void limpar() {
		setParcelas(null);
		setParcelasexcluidas(null);
	}
	
	@IgnoreMethodAuthentication
	public void adicionar() {
		if (getDias() != null && getDias() > 0) {
			
			// Verifica se já existe esse valor
			for (ParcelaPlanoPagamentoVendaVO parcela : parcelas) {
				if (parcela.getDiasPagamentoParcela() == getDias()) {
					writeErrorMessage("Prazo de parcela já foi informado!", "Dias");
					return;
				}
			}
			
			ParcelaPlanoPagamentoVendaVO p = new ParcelaPlanoPagamentoVendaVO();
			p.setNewRecord(true);
			p.setDiasPagamentoParcela(getDias());
			parcelas.add(p);
			
			// Ordena as parcelas por numero de dias
			Collections.sort(parcelas, new Comparator<ParcelaPlanoPagamentoVendaVO>() {
				
				@Override
				public int compare(ParcelaPlanoPagamentoVendaVO parcela1, ParcelaPlanoPagamentoVendaVO parcela2) {
					return parcela1.getDiasPagamentoParcela()
						.compareTo(parcela2.getDiasPagamentoParcela());
				}
			});
			
			write("ok");
		} else {
			writeErrorMessage("Informe o número de dias!", "Dias");
		}
	}

	@IgnoreMethodAuthentication
	public void remover() {
		if (Posicao != null && Posicao >= 0 && Posicao < parcelas.size()) {
			parcelasexcluidas.add(parcelas.remove(Posicao.intValue()));
			write("ok");
		} else {
			writeErrorMessage("Parcela não encontrada!", "Dias");
		}
	}
	
	@IgnoreMethodAuthentication
	public void listar() {
		write(Utils.voToXml(parcelas));
	}
	
	public void setParcelas(ArrayList<ParcelaPlanoPagamentoVendaVO> parcelas) {
		this.parcelas = parcelas;
	}

	public ArrayList<ParcelaPlanoPagamentoVendaVO> getParcelas() {
		return parcelas;
	}

	public void setDias(Integer dias) {
		Dias = dias;
	}

	public Integer getDias() {
		return Dias;
	}


	public void setPosicao(Integer posicao) {
		Posicao = posicao;
	}


	public Integer getPosicao() {
		return Posicao;
	}

	public void setParcelasexcluidas(ArrayList<ParcelaPlanoPagamentoVendaVO> parcelasexcluidas) {
		this.parcelasexcluidas = parcelasexcluidas;
	}

	public ArrayList<ParcelaPlanoPagamentoVendaVO> getParcelasexcluidas() {
		return parcelasexcluidas;
	}
}