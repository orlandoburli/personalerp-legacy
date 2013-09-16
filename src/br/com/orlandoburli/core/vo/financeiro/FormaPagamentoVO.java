package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class FormaPagamentoVO implements IValueObject {
	
	public static final int FORMA_PAGTO_DINHEIRO = 1;
	public static final int FORMA_PAGTO_CHEQUE = 2;
	public static final int FORMA_PAGTO_DEBITO_CONTA = 3;

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoFormaPagamento;
	private String NomeFormaPagamento;
	
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

	public void setCodigoFormaPagamento(Integer codigoFormaPagamento) {
		CodigoFormaPagamento = codigoFormaPagamento;
	}

	public Integer getCodigoFormaPagamento() {
		return CodigoFormaPagamento;
	}

	public void setNomeFormaPagamento(String nomeFormaPagamento) {
		NomeFormaPagamento = nomeFormaPagamento;
	}

	public String getNomeFormaPagamento() {
		return NomeFormaPagamento;
	}
}