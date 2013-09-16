package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class PlanoContaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key 
	@AutoIncrement
	private Integer CodigoPlanoConta;
	private String NumeroPlanoConta;
	private String NomePlanoConta;
	private Integer CodigoTipoPlanoConta;
	private Integer CodigoPlanoContaPai;
	
	@Formula(getFormula="(SELECT b.NomePlanoConta FROM [schema].planoconta b WHERE a.CodigoPlanoContaPai = b.CodigoPlanoConta)")
	private String NomePlanoContaPai;
	
	@Formula(getFormula="(SELECT b.NumeroPlanoConta FROM [schema].planoconta b WHERE a.CodigoPlanoContaPai = b.CodigoPlanoConta)")
	private String NumeroPlanoContaPai;
	
	@Formula(getFormula="(SELECT COUNT(*) FROM [schema].planoconta b WHERE b.CodigoPlanoContaPai = a.CodigoPlanoConta)")
    private Long CountChild;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
	
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}

	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	public void setCodigoUsuarioLog(Integer codigoUsuarioLog) {
		CodigoUsuarioLog = codigoUsuarioLog;
	}

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoPlanoConta(Integer codigoPlanoConta) {
		CodigoPlanoConta = codigoPlanoConta;
	}

	public Integer getCodigoPlanoConta() {
		return CodigoPlanoConta;
	}

	public void setNomePlanoConta(String nomePlanoConta) {
		NomePlanoConta = nomePlanoConta;
	}

	public String getNomePlanoConta() {
		return NomePlanoConta;
	}

	public void setCodigoPlanoContaPai(Integer codigoPlanoContaPai) {
		CodigoPlanoContaPai = codigoPlanoContaPai;
	}

	public Integer getCodigoPlanoContaPai() {
		return CodigoPlanoContaPai;
	}

	public void setCodigoTipoPlanoConta(Integer codigoTipoPlanoConta) {
		CodigoTipoPlanoConta = codigoTipoPlanoConta;
	}

	public Integer getCodigoTipoPlanoConta() {
		return CodigoTipoPlanoConta;
	}

	public void setNumeroPlanoConta(String numeroPlanoConta) {
		NumeroPlanoConta = numeroPlanoConta;
	}

	public String getNumeroPlanoConta() {
		return NumeroPlanoConta;
	}

	public void setNomePlanoContaPai(String nomePlanoContaPai) {
		NomePlanoContaPai = nomePlanoContaPai;
	}

	public String getNomePlanoContaPai() {
		return NomePlanoContaPai;
	}

	public void setNumeroPlanoContaPai(String numeroPlanoContaPai) {
		NumeroPlanoContaPai = numeroPlanoContaPai;
	}

	public String getNumeroPlanoContaPai() {
		return NumeroPlanoContaPai;
	}

	public void setCountChild(Long countChild) {
		CountChild = countChild;
	}

	public Long getCountChild() {
		return CountChild;
	}
}