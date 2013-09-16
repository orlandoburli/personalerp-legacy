package br.com.orlandoburli.core.vo.financeiro.contasapagar;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class RateioContaPagarVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoContaPagar;
	@Key
	private Integer CodigoEmpresaRateio;
	@Key
	private Integer CodigoLojaRateio;
	
	@Precision(decimals=2)
	private BigDecimal ValorRateio;
	
	@Formula(getFormula="(SELECT b.NomeLoja FROM [schema].Loja b WHERE a.CodigoEmpresaRateio = b.CodigoEmpresa AND a.CodigoLojaRateio = b.CodigoLoja)")
	private String NomeLoja;
	
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

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
	}

	public Integer getCodigoEmpresaRateio() {
		return CodigoEmpresaRateio;
	}

	public void setCodigoEmpresaRateio(Integer codigoEmpresaRateio) {
		CodigoEmpresaRateio = codigoEmpresaRateio;
	}

	public Integer getCodigoLojaRateio() {
		return CodigoLojaRateio;
	}

	public void setCodigoLojaRateio(Integer codigoLojaRateio) {
		CodigoLojaRateio = codigoLojaRateio;
	}

	public BigDecimal getValorRateio() {
		return ValorRateio;
	}

	public void setValorRateio(BigDecimal valorRateio) {
		ValorRateio = valorRateio;
	}

	public String getNomeLoja() {
		return NomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}
}