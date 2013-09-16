package br.com.orlandoburli.core.vo.base;

import br.com.orlandoburli.core.vo.*;

public class PaisVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	
	private boolean isNew;

	@Key @AutoIncrement
	private int CodigoPais;
	private String NomePais;
	private String SiglaPais;
	private String CodigoIbgePais;
	
	public int getCodigoPais() {
		return CodigoPais;
	}

	public void setCodigoPais(int codigoPais) {
		CodigoPais = codigoPais;
	}

	public String getNomePais() {
		return NomePais;
	}

	public void setNomePais(String nomePais) {
		NomePais = nomePais;
	}

	public String getSiglaPais() {
		return SiglaPais;
	}

	public void setSiglaPais(String siglaPais) {
		SiglaPais = siglaPais;
	}

	public String getCodigoIbgePais() {
		return CodigoIbgePais;
	}

	public void setCodigoIbgePais(String codigoIbgePais) {
		CodigoIbgePais = codigoIbgePais;
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
}