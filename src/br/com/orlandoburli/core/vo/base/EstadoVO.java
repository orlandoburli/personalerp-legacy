package br.com.orlandoburli.core.vo.base;

import br.com.orlandoburli.core.vo.*;

public class EstadoVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private int CodigoEstado;
	private String NomeEstado;
	private String SiglaEstado;
	private String CodigoIbgeEstado;
	private int CodigoPais;
	
	public int getCodigoEstado() {
		return CodigoEstado;
	}

	public void setCodigoEstado(int codigoEstado) {
		CodigoEstado = codigoEstado;
	}

	public String getNomeEstado() {
		return NomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		NomeEstado = nomeEstado;
	}

	public String getCodigoIbgeEstado() {
		return CodigoIbgeEstado;
	}

	public void setCodigoIbgeEstado(String codigoIbgeEstado) {
		CodigoIbgeEstado = codigoIbgeEstado;
	}

	public int getCodigoPais() {
		return CodigoPais;
	}

	public void setCodigoPais(int codigoPais) {
		CodigoPais = codigoPais;
	}
	
	public boolean IsNew() {
		return this.isNew;
	}

	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
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

	public void setSiglaEstado(String siglaEstado) {
		SiglaEstado = siglaEstado;
	}

	public String getSiglaEstado() {
		return SiglaEstado;
	}
}