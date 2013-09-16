package br.com.orlandoburli.personalerp.model.acesso.usuario.perfilusuario.vo;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class PerfilUsuarioVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	
    private boolean isNew;
    
    @Key
    private int CodigoEmpresa;
    @Key
    private int CodigoLoja;
    @Key
    private int CodigoPerfil;

    @Key
    private int UsuarioCodigoUsuario;
    @Key
    private int UsuarioCodigoEmpresa;
    @Key
    private int UsuarioCodigoLoja;
    
    private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;

    public void setNewRecord(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean IsNew() {
        return this.isNew;
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

    public int getCodigoPerfil() {
        return CodigoPerfil;
    }

    public void setCodigoPerfil(int CodigoPerfil) {
        this.CodigoPerfil = CodigoPerfil;
    }

    public int getUsuarioCodigoUsuario() {
        return UsuarioCodigoUsuario;
    }

    public void setUsuarioCodigoUsuario(int UsuarioCodigoUsuario) {
        this.UsuarioCodigoUsuario = UsuarioCodigoUsuario;
    }

    public int getUsuarioCodigoEmpresa() {
        return UsuarioCodigoEmpresa;
    }

    public void setUsuarioCodigoEmpresa(int UsuarioCodigoEmpresa) {
        this.UsuarioCodigoEmpresa = UsuarioCodigoEmpresa;
    }

    public int getUsuarioCodigoLoja() {
        return UsuarioCodigoLoja;
    }

    public void setUsuarioCodigoLoja(int UsuarioCodigoLoja) {
        this.UsuarioCodigoLoja = UsuarioCodigoLoja;
    }
    
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