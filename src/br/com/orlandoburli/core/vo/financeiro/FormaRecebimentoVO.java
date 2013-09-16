package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class FormaRecebimentoVO implements IValueObject {

	public static final int FORMA_PAGTO_DINHEIRO = 1;
	public static final int FORMA_PAGTO_CHEQUE = 2;
	public static final int FORMA_PAGTO_CREDITO_CONTA = 3;
	public static final int FORMA_PAGTO_CARTAO = 4;
	
	private static final long serialVersionUID = 1L;
	
	private boolean isNew;
	
	@Key
	private Integer CodigoFormaRecebimento;
	private String NomeFormaRecebimento;
	
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

	public void setCodigoFormaRecebimento(Integer codigoFormaRecebimento) {
		CodigoFormaRecebimento = codigoFormaRecebimento;
	}

	public Integer getCodigoFormaRecebimento() {
		return CodigoFormaRecebimento;
	}

	public void setNomeFormaRecebimento(String nomeFormaRecebimento) {
		NomeFormaRecebimento = nomeFormaRecebimento;
	}

	public String getNomeFormaRecebimento() {
		return NomeFormaRecebimento;
	}
}