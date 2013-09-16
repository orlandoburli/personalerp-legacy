package br.com.orlandoburli.core.vo.rh;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TabelaSalarioMinimoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoSalarioMinimo;
	
	private Date DataInicialVigenciaSalarioMinimo;
	private Date DataFinalVigenciaSalarioMinimo;
	
	private BigDecimal ValorSalarioMinimo;
	
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

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Date getDataInicialVigenciaSalarioMinimo() {
		return DataInicialVigenciaSalarioMinimo;
	}

	public void setDataInicialVigenciaSalarioMinimo(
			Date dataInicialVigenciaSalarioMinimo) {
		DataInicialVigenciaSalarioMinimo = dataInicialVigenciaSalarioMinimo;
	}

	public Date getDataFinalVigenciaSalarioMinimo() {
		return DataFinalVigenciaSalarioMinimo;
	}

	public void setDataFinalVigenciaSalarioMinimo(
			Date dataFinalVigenciaSalarioMinimo) {
		DataFinalVigenciaSalarioMinimo = dataFinalVigenciaSalarioMinimo;
	}

	public BigDecimal getValorSalarioMinimo() {
		return ValorSalarioMinimo;
	}

	public void setValorSalarioMinimo(BigDecimal valorSalarioMinimo) {
		ValorSalarioMinimo = valorSalarioMinimo;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public void setCodigoSalarioMinimo(Integer codigoSalarioMinimo) {
		CodigoSalarioMinimo = codigoSalarioMinimo;
	}

	public Integer getCodigoSalarioMinimo() {
		return CodigoSalarioMinimo;
	}
}
