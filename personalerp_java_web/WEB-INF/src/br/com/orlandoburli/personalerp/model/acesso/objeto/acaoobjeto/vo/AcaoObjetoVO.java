package br.com.orlandoburli.personalerp.model.acesso.objeto.acaoobjeto.vo;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class AcaoObjetoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	private boolean isNew;
	
	@Key
	private Integer CodigoObjeto;
	@Key @AutoIncrement
	private Integer CodigoAcaoObjeto;
	private String NomeAcaoObjeto;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoObjeto(Integer codigoObjeto) {
		CodigoObjeto = codigoObjeto;
	}

	public Integer getCodigoObjeto() {
		return CodigoObjeto;
	}

	public void setCodigoAcaoObjeto(Integer codigoAcaoObjeto) {
		CodigoAcaoObjeto = codigoAcaoObjeto;
	}

	public Integer getCodigoAcaoObjeto() {
		return CodigoAcaoObjeto;
	}

	public void setNomeAcaoObjeto(String nomeAcaoObjeto) {
		NomeAcaoObjeto = nomeAcaoObjeto;
	}

	public String getNomeAcaoObjeto() {
		return NomeAcaoObjeto;
	}
	
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
}
