package br.com.orlandoburli.core.vo;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Esta classe serve simplesmente de modelo para futuros VO's
 * @author Orlando Burli Jr.
 *
 */
public final class ModeloVO implements IValueObject {
	
	private ModeloVO() {
		// Garante que ninguem constroi esse trem...
		throw new NotImplementedException();
	}
	
	// Copiar daqui pra baixo
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
    private Integer CodigoUsuarioLog;
    private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;

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

	@Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return this.CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		this.CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}

	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		this.CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}
}