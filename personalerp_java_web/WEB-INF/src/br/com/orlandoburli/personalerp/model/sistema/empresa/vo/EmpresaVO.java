package br.com.orlandoburli.personalerp.model.sistema.empresa.vo;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.IValueObject;

public class EmpresaVO implements IValueObject {

	private static final long serialVersionUID = 1L;

	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	private String RazaoSocialEmpresa;
	private String FantasiaEmpresa;

	public boolean IsNew() {
		return this.isNew;
	}

	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public String getRazaoSocialEmpresa() {
		return RazaoSocialEmpresa;
	}

	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		RazaoSocialEmpresa = razaoSocialEmpresa;
	}

	public String getFantasiaEmpresa() {
		return FantasiaEmpresa;
	}

	public void setFantasiaEmpresa(String fantasiaEmpresa) {
		FantasiaEmpresa = fantasiaEmpresa;
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
	
	@Override
	public String toString() {
		return Utils.voToXml(this);
	}
}