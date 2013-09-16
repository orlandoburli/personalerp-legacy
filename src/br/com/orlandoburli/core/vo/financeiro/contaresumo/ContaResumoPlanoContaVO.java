package br.com.orlandoburli.core.vo.financeiro.contaresumo;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ContaResumoPlanoContaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoContaResumo;
	@Key
	private Integer CodigoPlanoConta;
	
	@Formula(getFormula="(SELECT B.NomePlanoConta FROM [schema].PlanoConta b WHERE a.CodigoPlanoConta = b.CodigoPlanoConta )")
	private String NomePlanoConta;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;

    @Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}
	
	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	@Override
	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	@Override
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

	public Integer getCodigoContaResumo() {
		return CodigoContaResumo;
	}

	public void setCodigoContaResumo(Integer codigoContaResumo) {
		CodigoContaResumo = codigoContaResumo;
	}

	public Integer getCodigoPlanoConta() {
		return CodigoPlanoConta;
	}

	public void setCodigoPlanoConta(Integer codigoPlanoConta) {
		CodigoPlanoConta = codigoPlanoConta;
	}

	public String getNomePlanoConta() {
		return NomePlanoConta;
	}

	public void setNomePlanoConta(String nomePlanoConta) {
		NomePlanoConta = nomePlanoConta;
	}
}
