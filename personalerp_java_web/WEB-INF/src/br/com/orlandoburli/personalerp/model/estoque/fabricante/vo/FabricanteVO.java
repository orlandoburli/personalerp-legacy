package br.com.orlandoburli.personalerp.model.estoque.fabricante.vo;

import br.com.orlandoburli.core.validators.NotEmpty;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Description;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class FabricanteVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	@Description("Código do Fabricante")
	private Integer CodigoFabricante;
	
	@NotEmpty
	@Description("Nome do Fabricante")
	private String NomeFabricante;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
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

	public void setCodigoFabricante(Integer codigoFabricante) {
		CodigoFabricante = codigoFabricante;
	}

	public Integer getCodigoFabricante() {
		return CodigoFabricante;
	}

	public void setNomeFabricante(String nomeFabricante) {
		NomeFabricante = nomeFabricante;
	}

	public String getNomeFabricante() {
		return NomeFabricante;
	}
}
