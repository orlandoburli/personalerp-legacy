package br.com.orlandoburli.core.vo.acesso;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class PerfilVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	
    private boolean isNew;
    
    @Key
    private Integer CodigoEmpresa;
    @Key
    private Integer CodigoLoja;
    @Key @AutoIncrement
    private Integer CodigoPerfil;
    private String NomePerfil;
    private String DescricaoPerfil;
    private Integer CodigoMenu;
    
    private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Ignore
    private String Permitido;
    
    public void setNewRecord(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean IsNew() {
        return this.isNew;
    }

    public Integer getCodigoPerfil() {
        return CodigoPerfil;
    }

    public void setCodigoPerfil(Integer CodigoPerfil) {
        this.CodigoPerfil = CodigoPerfil;
    }

    public int getCodigoEmpresa() {
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

    public String getNomePerfil() {
        return NomePerfil;
    }

    public void setNomePerfil(String NomePerfil) {
        this.NomePerfil = NomePerfil;
    }

    public String getDescricaoPerfil() {
        return DescricaoPerfil;
    }

    public void setDescricaoPerfil(String DescricaoPerfil) {
        this.DescricaoPerfil = DescricaoPerfil;
    }

    public int getCodigoMenu() {
        return CodigoMenu;
    }

    public void setCodigoMenu(int CodigoMenu) {
        this.CodigoMenu = CodigoMenu;
    }
    
    @Override
	public String toString() {
		return Utils.voToXml(this);
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

	public void setPermitido(String permitido) {
		Permitido = permitido;
	}

	public String getPermitido() {
		return Permitido;
	}
}