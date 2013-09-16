package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ParcelaPlanoPagamentoVendaVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoPlanoPagamento;
	@Key
	@AutoIncrement
	private Integer CodigoParcelaPlanoPagamento;
	private Integer DiasPagamentoParcela;
	
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

	public void setCodigoParcelaPlanoPagamento(
			Integer codigoParcelaPlanoPagamento) {
		CodigoParcelaPlanoPagamento = codigoParcelaPlanoPagamento;
	}

	public Integer getCodigoParcelaPlanoPagamento() {
		return CodigoParcelaPlanoPagamento;
	}

	public void setDiasPagamentoParcela(Integer diasPagamentoParcela) {
		DiasPagamentoParcela = diasPagamentoParcela;
	}

	public Integer getDiasPagamentoParcela() {
		return DiasPagamentoParcela;
	}
}