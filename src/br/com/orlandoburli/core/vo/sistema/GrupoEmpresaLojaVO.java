package br.com.orlandoburli.core.vo.sistema;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;


public class GrupoEmpresaLojaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoGrupoEmpresa;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	
	@Formula(getFormula="(SELECT b.NomeLoja FROM [schema].Loja b WHERE a.CodigoEmpresa = b.CodigoEmpresa AND a.CodigoLoja = b.CodigoLoja)")
	private String NomeLoja;
	
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

	public Integer getCodigoGrupoEmpresa() {
		return CodigoGrupoEmpresa;
	}

	public void setCodigoGrupoEmpresa(Integer codigoGrupoEmpresa) {
		CodigoGrupoEmpresa = codigoGrupoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public String getNomeLoja() {
		return NomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}
}
