package br.com.orlandoburli.core.vo.utils;

import br.com.orlandoburli.core.vo.IValueObject;

public class AuxiliarVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private String Descricao;
	private String Valor;
	
	public AuxiliarVO(String Descricao, String Valor) {
		this.Descricao = Descricao;
		this.Valor = Valor;
	}
	
	public AuxiliarVO(String Descricao) {
		this.Descricao = Descricao;
		this.Valor = Descricao;
	}
	
	@Override
	public boolean IsNew() { return false; }

	@Override
	public void setNewRecord(boolean isNew) {}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setValor(String valor) {
		Valor = valor;
	}

	public String getValor() {
		return Valor;
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