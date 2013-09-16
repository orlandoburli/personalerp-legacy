package br.com.orlandoburli.core.vo.estoque.etiqueta;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ModeloImpressoraEtiquetaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoModeloImpressoraEtiqueta;
	
	private String NomeModeloImpressoraEtiqueta;
	private String ClasseModeloImpressoraEtiqueta;
	
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

	public Integer getCodigoModeloImpressoraEtiqueta() {
		return CodigoModeloImpressoraEtiqueta;
	}

	public void setCodigoModeloImpressoraEtiqueta(
			Integer codigoModeloImpressoraEtiqueta) {
		CodigoModeloImpressoraEtiqueta = codigoModeloImpressoraEtiqueta;
	}

	public String getNomeModeloImpressoraEtiqueta() {
		return NomeModeloImpressoraEtiqueta;
	}

	public void setNomeModeloImpressoraEtiqueta(
			String nomeModeloImpressoraEtiqueta) {
		NomeModeloImpressoraEtiqueta = nomeModeloImpressoraEtiqueta;
	}

	public String getClasseModeloImpressoraEtiqueta() {
		return ClasseModeloImpressoraEtiqueta;
	}

	public void setClasseModeloImpressoraEtiqueta(
			String classeModeloImpressoraEtiqueta) {
		ClasseModeloImpressoraEtiqueta = classeModeloImpressoraEtiqueta;
	}
}
