package br.com.orlandoburli.core.vo.base;

import br.com.orlandoburli.core.vo.*;

public class CidadeVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private int CodigoCidade;
	private String NomeCidade;
	private int CodigoEstado;
	private String CodigoIbgeCidade;
	
	@Formula(getFormula="(SELECT b.siglaestado FROM [schema].estado b WHERE a.codigoestado = b.codigoestado)")
	private String SiglaUFCidade;
	
	@Formula(getFormula="(SELECT c.nomepais FROM [schema].pais c JOIN [schema].estado d ON c.codigopais = d.codigopais AND d.codigoestado = a.codigoestado)")
	private String NomePais;
	
	@Formula(getFormula="(SELECT b.CodigoIbgeEstado FROM [schema].estado b WHERE a.codigoestado = b.codigoestado)")
	private String CodigoIbgeEstado;

	public boolean IsNew() {
		return this.isNew;
	}

	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public int getCodigoCidade() {
		return CodigoCidade;
	}

	public void setCodigoCidade(int codigoCidade) {
		CodigoCidade = codigoCidade;
	}

	public String getNomeCidade() {
		return NomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		NomeCidade = nomeCidade;
	}

	public int getCodigoEstado() {
		return CodigoEstado;
	}

	public void setCodigoEstado(int codigoEstado) {
		CodigoEstado = codigoEstado;
	}

	public String getCodigoIbgeCidade() {
		return CodigoIbgeCidade;
	}

	public void setCodigoIbgeCidade(String codigoIbgeCidade) {
		CodigoIbgeCidade = codigoIbgeCidade;
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

	public void setSiglaUFCidade(String siglaUFCidade) {
		SiglaUFCidade = siglaUFCidade;
	}

	public String getSiglaUFCidade() {
		return SiglaUFCidade;
	}

	public void setNomePais(String nomePais) {
		NomePais = nomePais;
	}

	public String getNomePais() {
		return NomePais;
	}

	public String getCodigoIbgeEstado() {
		return CodigoIbgeEstado;
	}

	public void setCodigoIbgeEstado(String codigoIbgeEstado) {
		CodigoIbgeEstado = codigoIbgeEstado;
	}
}