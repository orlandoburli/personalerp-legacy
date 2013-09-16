package br.com.orlandoburli.core.vo.sistema;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ParametroVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private String ChaveParametro;
	private String DescricaoParametro;
	private String TipoDadoParametro;
	private Integer DecimaisParametro;
	private String ValoresParametro;
	
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

	public String getChaveParametro() {
		return ChaveParametro;
	}

	public void setChaveParametro(String chaveParametro) {
		ChaveParametro = chaveParametro;
	}

	public String getDescricaoParametro() {
		return DescricaoParametro;
	}

	public void setDescricaoParametro(String descricaoParametro) {
		DescricaoParametro = descricaoParametro;
	}

	public String getTipoDadoParametro() {
		return TipoDadoParametro;
	}

	public void setTipoDadoParametro(String tipoDadoParametro) {
		TipoDadoParametro = tipoDadoParametro;
	}

	public Integer getDecimaisParametro() {
		return DecimaisParametro;
	}

	public void setDecimaisParametro(Integer decimaisParametro) {
		DecimaisParametro = decimaisParametro;
	}

	public String getValoresParametro() {
		return ValoresParametro;
	}

	public void setValoresParametro(String valoresParametro) {
		ValoresParametro = valoresParametro;
	}
}