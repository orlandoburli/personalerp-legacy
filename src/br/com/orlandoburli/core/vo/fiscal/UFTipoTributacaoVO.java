package br.com.orlandoburli.core.vo.fiscal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class UFTipoTributacaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoTipoTributacao;
	@Key
	private String UFOrigemTributacao;
	
	@Formula(getFormula="(SELECT b.NomeEstado FROM [schema].Estado b WHERE b.SiglaEstado = a.UFOrigemTributacao)")
	private String NomeUFTipoTributacao;

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

	public void setCodigoTipoTributacao(Integer codigoTipoTributacao) {
		CodigoTipoTributacao = codigoTipoTributacao;
	}

	public Integer getCodigoTipoTributacao() {
		return CodigoTipoTributacao;
	}

	public void setUFOrigemTributacao(String uFOrigemTributacao) {
		UFOrigemTributacao = uFOrigemTributacao;
	}

	public String getUFOrigemTributacao() {
		return UFOrigemTributacao;
	}

	public void setNomeUFTipoTributacao(String nomeUFTipoTributacao) {
		NomeUFTipoTributacao = nomeUFTipoTributacao;
	}

	public String getNomeUFTipoTributacao() {
		return NomeUFTipoTributacao;
	}
}