package br.com.orlandoburli.core.vo.acesso;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class PermissaoEspecialVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private int CodigoPermissaoEspecial;
	private int DescricaoPermissaoEspecial;
	private String ValoresPermissaoEspecial;
	
	@Override
	public boolean IsNew() {
		return isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoPermissaoEspecial(int codigoPermissaoEspecial) {
		CodigoPermissaoEspecial = codigoPermissaoEspecial;
	}

	public int getCodigoPermissaoEspecial() {
		return CodigoPermissaoEspecial;
	}

	public void setDescricaoPermissaoEspecial(int descricaoPermissaoEspecial) {
		DescricaoPermissaoEspecial = descricaoPermissaoEspecial;
	}

	public int getDescricaoPermissaoEspecial() {
		return DescricaoPermissaoEspecial;
	}

	public void setValoresPermissaoEspecial(String valoresPermissaoEspecial) {
		ValoresPermissaoEspecial = valoresPermissaoEspecial;
	}

	public String getValoresPermissaoEspecial() {
		return ValoresPermissaoEspecial;
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