package br.com.orlandoburli.core.vo.financeiro.contaresumo;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TipoContaResumoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoTipoContaResumo;
	private String DescricaoTipoContaResumo;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;

    @Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}
	
	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	@Override
	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	@Override
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

	public Integer getCodigoTipoContaResumo() {
		return CodigoTipoContaResumo;
	}

	public void setCodigoTipoContaResumo(Integer codigoTipoContaResumo) {
		CodigoTipoContaResumo = codigoTipoContaResumo;
	}

	public String getDescricaoTipoContaResumo() {
		return DescricaoTipoContaResumo;
	}

	public void setDescricaoTipoContaResumo(String descricaoTipoContaResumo) {
		DescricaoTipoContaResumo = descricaoTipoContaResumo;
	}
}
