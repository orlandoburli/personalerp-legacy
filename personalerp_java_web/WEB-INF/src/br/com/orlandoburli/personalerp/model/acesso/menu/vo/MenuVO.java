package br.com.orlandoburli.personalerp.model.acesso.menu.vo;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

import java.util.ArrayList;
import java.util.List;

public class MenuVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	
	private boolean isNew;
	
    @Key @AutoIncrement
    private Integer CodigoMenu;
    private String NomeMenu;
    private Integer CodigoObjeto;
    private Integer CodigoMenuPai;
    private Integer OrdemMenu;
    private String TipoMenu;
    
    @Formula("(SELECT COUNT(*) FROM [schema].Menu b WHERE b.CodigoMenuPai = a.CodigoMenu)")
    private Long CountChild;
    
    @Formula("(SELECT c.NomeObjeto FROM [schema].Objeto c WHERE a.CodigoObjeto = c.CodigoObjeto)")
    private String NomeObjeto;

    @Formula("(SELECT c.DescricaoObjeto FROM [schema].Objeto c WHERE a.CodigoObjeto = c.CodigoObjeto)")
    private String DescricaoObjeto;
    
    @Formula("(SELECT d.NomeMenu FROM [schema].Menu d WHERE a.CodigoMenuPai = d.CodigoMenu)")
    private String NomeMenuPai;
    
    @Ignore
    private List<MenuVO> SubMenus;

    public void setNewRecord(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean IsNew() {
        return this.isNew;
    }

    public Integer getCodigoMenu() {
        return CodigoMenu;
    }

    public void setCodigoMenu(Integer CodigoMenu) {
        this.CodigoMenu = CodigoMenu;
    }

    public String getNomeMenu() {
        return NomeMenu;
    }

    public void setNomeMenu(String NomeMenu) {
        this.NomeMenu = NomeMenu;
    }

    public Integer getCodigoObjeto() {
        return CodigoObjeto;
    }

    public void setCodigoObjeto(Integer CodigoObjeto) {
        this.CodigoObjeto = CodigoObjeto;
    }

    public Integer getCodigoMenuPai() {
        return CodigoMenuPai;
    }

    public void setCodigoMenuPai(Integer CodigoMenuPai) {
        this.CodigoMenuPai = CodigoMenuPai;
    }

    public List<MenuVO> getSubMenus() {
    	if (this.SubMenus == null) {
    		this.SubMenus = new ArrayList<MenuVO>();
    	}
        return SubMenus;
    }

    public void setSubMenus(List<MenuVO> SubMenus) {
        this.SubMenus = SubMenus;
    }

    public Long getCountChild() {
        return this.CountChild;
    }
    
    public void setCountChild(Long CountChild) {
    	this.CountChild = CountChild;
    }
    
    public Integer getOrdemMenu() {
        return this.OrdemMenu;
    }

    public void setOrdemMenu(Integer OrdemMenu) {
        this.OrdemMenu = OrdemMenu;
    }
    
    public String getNomeMenuPai() {
    	return this.NomeMenuPai;
    }
    
    public void setNomeMenuPai(String NomeMenuPai) {
    	this.NomeMenuPai = NomeMenuPai;
    }
    
    public String getNomeObjeto() {
    	return this.NomeObjeto;
    }
    
    public void setNomeObjeto(String NomeObjeto) {
    	this.NomeObjeto = NomeObjeto;
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

	public void setTipoMenu(String tipoMenu) {
		TipoMenu = tipoMenu;
	}

	public String getTipoMenu() {
		return TipoMenu;
	}

	public String getDescricaoObjeto() {
		return DescricaoObjeto;
	}

	public void setDescricaoObjeto(String descricaoObjeto) {
		DescricaoObjeto = descricaoObjeto;
	}
}