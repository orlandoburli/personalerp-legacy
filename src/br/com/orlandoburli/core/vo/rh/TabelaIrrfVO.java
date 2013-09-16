package br.com.orlandoburli.core.vo.rh;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TabelaIrrfVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;

	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoTabelaIrrf;
	
	private Date DataInicialVigenciaTabelaIrrf;
	private Date DataFinalVigenciaTabelaIrrf;
	private BigDecimal ValorInicialTabelaIrrf;
	private BigDecimal ValorFinalTabelaIrrf;
	private BigDecimal AliquotaTabelaIrrf;
	private BigDecimal ValorParcelaDeducaoTabelaIrrf;
	
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

	public Integer getCodigoTabelaIrrf() {
		return CodigoTabelaIrrf;
	}

	public void setCodigoTabelaIrrf(Integer codigoTabelaIrrf) {
		CodigoTabelaIrrf = codigoTabelaIrrf;
	}

	public Date getDataInicialVigenciaTabelaIrrf() {
		return DataInicialVigenciaTabelaIrrf;
	}

	public void setDataInicialVigenciaTabelaIrrf(Date dataInicialVigenciaTabelaIrrf) {
		DataInicialVigenciaTabelaIrrf = dataInicialVigenciaTabelaIrrf;
	}

	public Date getDataFinalVigenciaTabelaIrrf() {
		return DataFinalVigenciaTabelaIrrf;
	}

	public void setDataFinalVigenciaTabelaIrrf(Date dataFinalVigenciaTabelaIrrf) {
		DataFinalVigenciaTabelaIrrf = dataFinalVigenciaTabelaIrrf;
	}

	public BigDecimal getValorInicialTabelaIrrf() {
		return ValorInicialTabelaIrrf;
	}

	public void setValorInicialTabelaIrrf(BigDecimal valorInicialTabelaIrrf) {
		ValorInicialTabelaIrrf = valorInicialTabelaIrrf;
	}

	public BigDecimal getValorFinalTabelaIrrf() {
		return ValorFinalTabelaIrrf;
	}

	public void setValorFinalTabelaIrrf(BigDecimal valorFinalTabelaIrrf) {
		ValorFinalTabelaIrrf = valorFinalTabelaIrrf;
	}

	public BigDecimal getAliquotaTabelaIrrf() {
		return AliquotaTabelaIrrf;
	}

	public void setAliquotaTabelaIrrf(BigDecimal aliquotaTabelaIrrf) {
		AliquotaTabelaIrrf = aliquotaTabelaIrrf;
	}

	public void setValorParcelaDeducaoTabelaIrrf(
			BigDecimal valorParcelaDeducaoTabelaIrrf) {
		ValorParcelaDeducaoTabelaIrrf = valorParcelaDeducaoTabelaIrrf;
	}

	public BigDecimal getValorParcelaDeducaoTabelaIrrf() {
		return ValorParcelaDeducaoTabelaIrrf;
	}
}