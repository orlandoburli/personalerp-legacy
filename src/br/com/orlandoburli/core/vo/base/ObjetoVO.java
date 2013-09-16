package br.com.orlandoburli.core.vo.base;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ObjetoVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	
    private boolean isNew;

    @Key @AutoIncrement
    private Integer CodigoObjeto;
    private String NomeObjeto;
    private String DescricaoObjeto;
    
    public boolean IsNew() {
        return this.isNew;
    }

    public void setNewRecord(boolean isNew) {
        this.isNew = isNew;
    }

    public Integer getCodigoObjeto() {
        return CodigoObjeto;
    }

    public void setCodigoObjeto(Integer CodigoObjeto) {
        this.CodigoObjeto = CodigoObjeto;
    }

    public String getNomeObjeto() {
        return NomeObjeto;
    }

    public void setNomeObjeto(String NomeObjeto) {
        this.NomeObjeto = NomeObjeto;
    }

    public String getDescricaoObjeto() {
        return DescricaoObjeto;
    }

    public void setDescricaoObjeto(String DescricaoObjeto) {
        this.DescricaoObjeto = DescricaoObjeto;
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