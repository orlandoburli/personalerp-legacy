package br.com.orlandoburli.personalerp.model.fiscal.tipotributacao.vo;

import br.com.orlandoburli.core.validators.NotEmpty;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Description;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TipoTributacaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	@Description("Código")
	private Integer CodigoTipoTributacao;
	
	@Description("Identificação")
	@NotEmpty
	private String IdentificacaoTipoTributacao;
	
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

	public void setIdentificacaoTipoTributacao(
			String identificacaoTipoTributacao) {
		IdentificacaoTipoTributacao = identificacaoTipoTributacao;
	}

	public String getIdentificacaoTipoTributacao() {
		return IdentificacaoTipoTributacao;
	}
}
