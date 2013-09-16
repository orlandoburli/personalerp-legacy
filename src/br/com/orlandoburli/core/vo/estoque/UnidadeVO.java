package br.com.orlandoburli.core.vo.estoque;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class UnidadeVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private String SiglaUnidade;
	private String NomeUnidadeSingular;
	private String NomeUnidadePlural;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
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

	public void setSiglaUnidade(String siglaUnidade) {
		SiglaUnidade = siglaUnidade;
	}

	public String getSiglaUnidade() {
		return SiglaUnidade;
	}

	public void setNomeUnidadeSingular(String nomeUnidadeSingular) {
		NomeUnidadeSingular = nomeUnidadeSingular;
	}

	public String getNomeUnidadeSingular() {
		return NomeUnidadeSingular;
	}

	public void setNomeUnidadePlural(String nomeUnidadePlural) {
		NomeUnidadePlural = nomeUnidadePlural;
	}

	public String getNomeUnidadePlural() {
		return NomeUnidadePlural;
	}
}
