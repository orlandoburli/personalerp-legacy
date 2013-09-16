package br.com.orlandoburli.core.vo.utils;

import br.com.orlandoburli.core.vo.*;
import br.com.orlandoburli.core.vo.acesso.*;
import br.com.orlandoburli.core.vo.sistema.*;

public class MonitorVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	private String Id;
	private UsuarioVO Usuario;
	private PerfilVO Perfil;
	private EmpresaVO Empresa;
	private LojaVO Loja;

	@Override
	public boolean IsNew() { return false; }

	@Override
	public void setNewRecord(boolean isNew) {}

	public void setId(String id) {
		Id = id;
	}

	public String getId() {
		return Id;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.Usuario = usuario;
	}

	public UsuarioVO getUsuario() {
		return Usuario;
	}

	public void setPerfil(PerfilVO perfil) {
		this.Perfil = perfil;
	}

	public PerfilVO getPerfil() {
		return Perfil;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.Empresa = empresa;
	}

	public EmpresaVO getEmpresa() {
		return Empresa;
	}

	public void setLoja(LojaVO loja) {
		this.Loja = loja;
	}

	public LojaVO getLoja() {
		return Loja;
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