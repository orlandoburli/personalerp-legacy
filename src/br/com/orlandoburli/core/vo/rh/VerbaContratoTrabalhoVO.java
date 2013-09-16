package br.com.orlandoburli.core.vo.rh;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class VerbaContratoTrabalhoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoContratoTrabalho;
	@Key
	private Integer CodigoEmpresaVerba;
	@Key
	private Integer CodigoLojaVerba;
	@Key
	private Integer CodigoVerba;
	
	private BigDecimal ValorVerba;
	
	@Formula(getFormula="(SELECT b.DescricaoVerba FROM [schema].Verba b WHERE a.CodigoEmpresaVerba = b.CodigoEmpresa AND a.CodigoLojaVerba = b.CodigoLoja AND a.CodigoVerba = b.CodigoVerba)")
	private String DescricaoVerba;
	
	@Formula(getFormula="(SELECT b.TipoVerba FROM [schema].Verba b WHERE a.CodigoEmpresaVerba = b.CodigoEmpresa AND a.CodigoLojaVerba = b.CodigoLoja AND a.CodigoVerba = b.CodigoVerba)")
	private String TipoVerba;
	
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

	public Integer getCodigoContratoTrabalho() {
		return CodigoContratoTrabalho;
	}

	public void setCodigoContratoTrabalho(Integer codigoContratoTrabalho) {
		CodigoContratoTrabalho = codigoContratoTrabalho;
	}

	public Integer getCodigoEmpresaVerba() {
		return CodigoEmpresaVerba;
	}

	public void setCodigoEmpresaVerba(Integer codigoEmpresaVerba) {
		CodigoEmpresaVerba = codigoEmpresaVerba;
	}

	public Integer getCodigoLojaVerba() {
		return CodigoLojaVerba;
	}

	public void setCodigoLojaVerba(Integer codigoLojaVerba) {
		CodigoLojaVerba = codigoLojaVerba;
	}

	public Integer getCodigoVerba() {
		return CodigoVerba;
	}

	public void setCodigoVerba(Integer codigoVerba) {
		CodigoVerba = codigoVerba;
	}

	public BigDecimal getValorVerba() {
		return ValorVerba;
	}

	public void setValorVerba(BigDecimal valorVerba) {
		ValorVerba = valorVerba;
	}

	public void setDescricaoVerba(String descricaoVerba) {
		DescricaoVerba = descricaoVerba;
	}

	public String getDescricaoVerba() {
		return DescricaoVerba;
	}

	public void setTipoVerba(String tipoVerba) {
		TipoVerba = tipoVerba;
	}

	public String getTipoVerba() {
		return TipoVerba;
	}
}