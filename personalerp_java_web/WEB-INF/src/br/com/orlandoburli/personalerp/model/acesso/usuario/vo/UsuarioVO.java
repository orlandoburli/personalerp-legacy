package br.com.orlandoburli.personalerp.model.acesso.usuario.vo;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class UsuarioVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
    private Integer CodigoUsuario;
    @Key
    private Integer CodigoEmpresa;
    @Key
    private Integer CodigoLoja;
    private String NomeUsuario;
    private String LoginUsuario;
    private String SenhaUsuario;
    @Ignore
    private String ConfSenhaUsuario;
    private Boolean Ativo;
    private Boolean SuperUsuario;
    
	@Override
	public boolean IsNew() {
		return isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

    public Integer getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(Integer CodigoUsuario) {
        this.CodigoUsuario = CodigoUsuario;
    }

    public Integer getCodigoEmpresa() {
        return CodigoEmpresa;
    }

    public void setCodigoEmpresa(Integer CodigoEmpresa) {
        this.CodigoEmpresa = CodigoEmpresa;
    }

    public Integer getCodigoLoja() {
        return CodigoLoja;
    }

    public void setCodigoLoja(Integer CodigoLoja) {
        this.CodigoLoja = CodigoLoja;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String NomeUsuario) {
        this.NomeUsuario = NomeUsuario;
    }

    public String getLoginUsuario() {
        return LoginUsuario;
    }

    public void setLoginUsuario(String LoginUsuario) {
        this.LoginUsuario = LoginUsuario;
    }

    public String getSenhaUsuario() {
        return SenhaUsuario;
    }

    public void setSenhaUsuario(String SenhaUsuario) {
        this.SenhaUsuario = SenhaUsuario;
    }

    public Boolean isAtivo() {
        return Ativo;
    }

    public void setAtivo(Boolean Ativo) {
        this.Ativo = Ativo;
    }

	public void setConfSenhaUsuario(String confSenhaUsuario) {
		ConfSenhaUsuario = confSenhaUsuario;
	}

	public String getConfSenhaUsuario() {
		return ConfSenhaUsuario;
	}

	public void setSuperUsuario(Boolean superUsuario) {
		SuperUsuario = superUsuario;
	}

	public Boolean getSuperUsuario() {
		return SuperUsuario;
	}
	
	@Override
	public String toString() {
		return Utils.voToXml(this);
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