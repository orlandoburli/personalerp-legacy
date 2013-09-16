package br.com.orlandoburli.core.vo.rh;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TabelaInssVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;

	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoTabelaInss;
	
	private Date DataInicialVigenciaTabelaInss;
	private Date DataFinalVigenciaTabelaInss;
	private BigDecimal ValorInicialTabelaInss;
	private BigDecimal ValorFinalTabelaInss;
	private BigDecimal AliquotaTabelaInss;
	private BigDecimal AliquotaPatronalTabelaInss;
	
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

	public Integer getCodigoTabelaInss() {
		return CodigoTabelaInss;
	}

	public void setCodigoTabelaInss(Integer codigoTabelaInss) {
		CodigoTabelaInss = codigoTabelaInss;
	}

	public Date getDataInicialVigenciaTabelaInss() {
		return DataInicialVigenciaTabelaInss;
	}

	public void setDataInicialVigenciaTabelaInss(Date dataInicialVigenciaTabelaInss) {
		DataInicialVigenciaTabelaInss = dataInicialVigenciaTabelaInss;
	}

	public Date getDataFinalVigenciaTabelaInss() {
		return DataFinalVigenciaTabelaInss;
	}

	public void setDataFinalVigenciaTabelaInss(Date dataFinalVigenciaTabelaInss) {
		DataFinalVigenciaTabelaInss = dataFinalVigenciaTabelaInss;
	}

	public BigDecimal getValorInicialTabelaInss() {
		return ValorInicialTabelaInss;
	}

	public void setValorInicialTabelaInss(BigDecimal valorInicialTabelaInss) {
		ValorInicialTabelaInss = valorInicialTabelaInss;
	}

	public BigDecimal getValorFinalTabelaInss() {
		return ValorFinalTabelaInss;
	}

	public void setValorFinalTabelaInss(BigDecimal valorFinalTabelaInss) {
		ValorFinalTabelaInss = valorFinalTabelaInss;
	}

	public BigDecimal getAliquotaTabelaInss() {
		return AliquotaTabelaInss;
	}

	public void setAliquotaTabelaInss(BigDecimal aliquotaTabelaInss) {
		AliquotaTabelaInss = aliquotaTabelaInss;
	}

	public void setAliquotaPatronalTabelaInss(BigDecimal aliquotaPatronalTabelaInss) {
		AliquotaPatronalTabelaInss = aliquotaPatronalTabelaInss;
	}

	public BigDecimal getAliquotaPatronalTabelaInss() {
		return AliquotaPatronalTabelaInss;
	}
}