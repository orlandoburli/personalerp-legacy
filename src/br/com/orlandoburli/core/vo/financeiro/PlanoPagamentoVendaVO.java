package br.com.orlandoburli.core.vo.financeiro;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class PlanoPagamentoVendaVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoPlanoPagamento;
	private String NomePlanoPagamento;
	private BigDecimal PercentualJurosPlanoPagamento;
	private String FlagAtivoPlanoPagamento;
	
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

	public void setCodigoPlanoPagamento(Integer codigoPlanoPagamento) {
		CodigoPlanoPagamento = codigoPlanoPagamento;
	}

	public Integer getCodigoPlanoPagamento() {
		return CodigoPlanoPagamento;
	}

	public void setNomePlanoPagamento(String nomePlanoPagamento) {
		NomePlanoPagamento = nomePlanoPagamento;
	}

	public String getNomePlanoPagamento() {
		return NomePlanoPagamento;
	}

	public void setPercentualJurosPlanoPagamento(
			BigDecimal percentualJurosPlanoPagamento) {
		PercentualJurosPlanoPagamento = percentualJurosPlanoPagamento;
	}

	public BigDecimal getPercentualJurosPlanoPagamento() {
		return PercentualJurosPlanoPagamento;
	}

	public void setFlagAtivoPlanoPagamento(String flagAtivoPlanoPagamento) {
		FlagAtivoPlanoPagamento = flagAtivoPlanoPagamento;
	}

	public String getFlagAtivoPlanoPagamento() {
		return FlagAtivoPlanoPagamento;
	}
}