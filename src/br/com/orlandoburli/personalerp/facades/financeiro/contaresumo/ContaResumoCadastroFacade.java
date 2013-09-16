package br.com.orlandoburli.personalerp.facades.financeiro.contaresumo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.dao.financeiro.contaresumo.ContaResumoDAO;
import br.com.orlandoburli.core.dao.financeiro.contaresumo.ContaResumoPlanoContaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.contaresumo.ContaResumoPlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.contaresumo.ContaResumoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ContaResumoCadastroFacade extends BaseCadastroFlexFacade<ContaResumoVO, ContaResumoDAO>{

	private static final long serialVersionUID = 1L;
	
	@OutjectSession
	private List<ContaResumoPlanoContaVO> planocontas;
	@OutjectSession
	private List<ContaResumoPlanoContaVO> planocontasExclusao;
	
	private Integer CodigoPlanoConta;
	private String NomePlanoConta;
	
	private Integer CodigoContaResumoPesquisa;

	public ContaResumoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoContaResumo", "Descrição"));
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		for (ContaResumoPlanoContaVO i : planocontas) {
			getGenericDao().remove(i);
		}
		return super.doBeforeDelete();
	}
	
	@Override
	public void doAfterSave() throws SQLException {
		for (ContaResumoPlanoContaVO i : planocontasExclusao) {
			getGenericDao().remove(i);
		}
		for (ContaResumoPlanoContaVO i : planocontas) {
			i.setCodigoContaResumo(getVo().getCodigoContaResumo());
			getGenericDao().persist(i);
		}
		
		super.doAfterSave();
	}
	
	@IgnoreMethodAuthentication
	public void contaresumopai() {
		ContaResumoVO conta = new ContaResumoVO();
		conta.setCodigoContaResumo(CodigoContaResumoPesquisa);
		try {
			conta = dao.get(conta);
			write(Utils.voToXml(conta));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void data() {
		try {
			write(Utils.voToXml(new PlanoContaDAO().getList(null, "NomePlanoConta")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void limpar() {
		planocontas = new ArrayList<ContaResumoPlanoContaVO>();
		planocontasExclusao = new ArrayList<ContaResumoPlanoContaVO>();
	}
	
	@IgnoreMethodAuthentication
	public void load() {
		limpar();
		
		if (getVo().getCodigoContaResumo() != null) {
			ContaResumoPlanoContaVO filter = new ContaResumoPlanoContaVO();
			filter.setCodigoContaResumo(getVo().getCodigoContaResumo());
			
			try {
				planocontas = new ContaResumoPlanoContaDAO().getList(filter);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		listplanocontas();
	}
	
	@IgnoreMethodAuthentication
	public void listplanocontas() {
		// TODO Ordenação
		write(Utils.voToXml(planocontas));
	}
	
	@IgnoreMethodAuthentication
	public void adicionar() {
		// Verifica se a conta ja nao está adicionada
		boolean existe = false;
		for (ContaResumoPlanoContaVO i : planocontas) {
			if (i.getCodigoPlanoConta().equals(CodigoPlanoConta)) {
				existe = true;
			}
		}
		
		if (!existe) { 
			ContaResumoPlanoContaVO cp = new ContaResumoPlanoContaVO();
			cp.setNewRecord(true);
			cp.setCodigoPlanoConta(CodigoPlanoConta);
			cp.setNomePlanoConta(NomePlanoConta);
			planocontas.add(cp);
		}
		
		listplanocontas();
	}
	
	@IgnoreMethodAuthentication
	public void remover() {
		for (ContaResumoPlanoContaVO cp : planocontas) {
			if (cp.getCodigoPlanoConta().equals(CodigoPlanoConta)) {
				planocontas.remove(cp);
				if (!cp.IsNew()) {
					planocontasExclusao.add(cp);
				}
				break;
			}
		}
		listplanocontas();
	}
	

	public List<ContaResumoPlanoContaVO> getPlanocontas() {
		return planocontas;
	}

	public void setPlanocontas(List<ContaResumoPlanoContaVO> planocontas) {
		this.planocontas = planocontas;
	}

	public Integer getCodigoPlanoConta() {
		return CodigoPlanoConta;
	}

	public void setCodigoPlanoConta(Integer codigoPlanoConta) {
		CodigoPlanoConta = codigoPlanoConta;
	}

	public List<ContaResumoPlanoContaVO> getPlanocontasExclusao() {
		return planocontasExclusao;
	}

	public void setPlanocontasExclusao(List<ContaResumoPlanoContaVO> planocontasExclusao) {
		this.planocontasExclusao = planocontasExclusao;
	}

	public String getNomePlanoConta() {
		return NomePlanoConta;
	}

	public void setNomePlanoConta(String nomePlanoConta) {
		NomePlanoConta = nomePlanoConta;
	}

	public Integer getCodigoContaResumoPesquisa() {
		return CodigoContaResumoPesquisa;
	}

	public void setCodigoContaResumoPesquisa(Integer codigoContaResumoPesquisa) {
		CodigoContaResumoPesquisa = codigoContaResumoPesquisa;
	}
}
