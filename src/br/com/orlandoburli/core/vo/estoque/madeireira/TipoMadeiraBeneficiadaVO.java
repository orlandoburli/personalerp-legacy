package br.com.orlandoburli.core.vo.estoque.madeireira;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TipoMadeiraBeneficiadaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoTipoMadeiraBeneficiada;
	private String NomeTipoMadeiraBeneficiada;
	
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
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoTipoMadeiraBeneficiada(
			Integer codigoTipoMadeiraBeneficiada) {
		CodigoTipoMadeiraBeneficiada = codigoTipoMadeiraBeneficiada;
	}

	public Integer getCodigoTipoMadeiraBeneficiada() {
		return CodigoTipoMadeiraBeneficiada;
	}

	public void setNomeTipoMadeiraBeneficiada(String nomeTipoMadeiraBeneficiada) {
		NomeTipoMadeiraBeneficiada = nomeTipoMadeiraBeneficiada;
	}

	public String getNomeTipoMadeiraBeneficiada() {
		return NomeTipoMadeiraBeneficiada;
	}
}