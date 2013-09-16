package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.IValueObject;

public class BandeiraCartaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	private Integer CodigoBandeira;
	private String NomeBandeira;
	
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

	public void setCodigoBandeira(Integer codigoBandeira) {
		CodigoBandeira = codigoBandeira;
	}

	public Integer getCodigoBandeira() {
		return CodigoBandeira;
	}

	public void setNomeBandeira(String nomeBandeira) {
		NomeBandeira = nomeBandeira;
	}

	public String getNomeBandeira() {
		return NomeBandeira;
	}
}