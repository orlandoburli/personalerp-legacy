package br.com.orlandoburli.core.vo.rh;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class FeriadoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoFeriado;
	private String DescricaoFeriado;
	private Integer DiaFeriado;
	private Integer MesFeriado;
	private Integer AnoFeriado;
	
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

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoFeriado() {
		return CodigoFeriado;
	}

	public void setCodigoFeriado(Integer codigoFeriado) {
		CodigoFeriado = codigoFeriado;
	}

	public String getDescricaoFeriado() {
		return DescricaoFeriado;
	}

	public void setDescricaoFeriado(String descricaoFeriado) {
		DescricaoFeriado = descricaoFeriado;
	}

	public Integer getDiaFeriado() {
		return DiaFeriado;
	}

	public void setDiaFeriado(Integer diaFeriado) {
		DiaFeriado = diaFeriado;
	}

	public Integer getMesFeriado() {
		return MesFeriado;
	}

	public void setMesFeriado(Integer mesFeriado) {
		MesFeriado = mesFeriado;
	}

	public Integer getAnoFeriado() {
		return AnoFeriado;
	}

	public void setAnoFeriado(Integer anoFeriado) {
		AnoFeriado = anoFeriado;
	}
}