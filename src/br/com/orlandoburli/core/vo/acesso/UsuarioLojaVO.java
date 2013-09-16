package br.com.orlandoburli.core.vo.acesso;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class UsuarioLojaVO implements IValueObject {
    
	private static final long serialVersionUID = 1L;
    private boolean isNew;

    @Key
    private int CodigoUsuario;
    @Key
    private int CodigoEmpresa;
    @Key
    private int CodigoLoja;
    @Key
    private int CodigoEmpresaVinculada;
    @Key
    private int CodigoLojaVinculada;

    public boolean IsNew() {
        return this.isNew;
    }

    public void setNewRecord(boolean IsNew) {
        this.isNew = IsNew;
    }

    public int getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(int CodigoUsuario) {
        this.CodigoUsuario = CodigoUsuario;
    }

    public int getCodigoEmpresa() {
        return CodigoEmpresa;
    }

    public void setCodigoEmpresa(int CodigoEmpresa) {
        this.CodigoEmpresa = CodigoEmpresa;
    }

    public int getCodigoLoja() {
        return CodigoLoja;
    }

    public void setCodigoLoja(int CodigoLoja) {
        this.CodigoLoja = CodigoLoja;
    }

    public int getCodigoEmpresaVinculada() {
        return CodigoEmpresaVinculada;
    }

    public void setCodigoEmpresaVinculada(int CodigoEmpresaVinculada) {
        this.CodigoEmpresaVinculada = CodigoEmpresaVinculada;
    }

    public int getCodigoLojaVinculada() {
        return CodigoLojaVinculada;
    }

    public void setCodigoLojaVinculada(int CodigoLojaVinculada) {
        this.CodigoLojaVinculada = CodigoLojaVinculada;
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