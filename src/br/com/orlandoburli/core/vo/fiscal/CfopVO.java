package br.com.orlandoburli.core.vo.fiscal;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class CfopVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	@Key
	private Integer CodigoCfop;
	@Key
	private String ComplementoCfop;
	private String DescricaoCfop;
	private String TipoCfop;
	private Integer ClassificacaoCfop;
	private String ObservacoesCfop;
	
	
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

	public Integer getCodigoCfop() {
		return CodigoCfop;
	}

	public void setCodigoCfop(Integer codigoCfop) {
		CodigoCfop = codigoCfop;
	}

	public String getComplementoCfop() {
		return ComplementoCfop;
	}

	public void setComplementoCfop(String complementoCfop) {
		ComplementoCfop = complementoCfop;
	}

	public String getDescricaoCfop() {
		return DescricaoCfop;
	}

	public void setDescricaoCfop(String descricaoCfop) {
		DescricaoCfop = descricaoCfop;
	}

	public String getTipoCfop() {
		return TipoCfop;
	}

	public void setTipoCfop(String tipoCfop) {
		TipoCfop = tipoCfop;
	}

	public Integer getClassificacaoCfop() {
		return ClassificacaoCfop;
	}

	public void setClassificacaoCfop(Integer classificacaoCfop) {
		ClassificacaoCfop = classificacaoCfop;
	}

	public String getObservacoesCfop() {
		return ObservacoesCfop;
	}

	public void setObservacoesCfop(String observacoesCfop) {
		ObservacoesCfop = observacoesCfop;
	}
}
