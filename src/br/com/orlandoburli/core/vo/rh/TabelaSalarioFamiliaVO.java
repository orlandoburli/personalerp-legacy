package br.com.orlandoburli.core.vo.rh;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TabelaSalarioFamiliaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoTabelaSalarioFamilia;
	
	private Date DataInicialVigenciaSalarioFamilia;
	private Date DataFinalVigenciaSalarioFamilia;
	
	private BigDecimal ValorInicialSalarioFamilia;
	private BigDecimal ValorFinalSalarioFamilia;
	private BigDecimal ValorSalarioFamilia;
	
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

	public Integer getCodigoTabelaSalarioFamilia() {
		return CodigoTabelaSalarioFamilia;
	}

	public void setCodigoTabelaSalarioFamilia(Integer codigoTabelaSalarioFamilia) {
		CodigoTabelaSalarioFamilia = codigoTabelaSalarioFamilia;
	}

	public Date getDataInicialVigenciaSalarioFamilia() {
		return DataInicialVigenciaSalarioFamilia;
	}

	public void setDataInicialVigenciaSalarioFamilia(
			Date dataInicialVigenciaSalarioFamilia) {
		DataInicialVigenciaSalarioFamilia = dataInicialVigenciaSalarioFamilia;
	}

	public Date getDataFinalVigenciaSalarioFamilia() {
		return DataFinalVigenciaSalarioFamilia;
	}

	public void setDataFinalVigenciaSalarioFamilia(
			Date dataFinalVigenciaSalarioFamilia) {
		DataFinalVigenciaSalarioFamilia = dataFinalVigenciaSalarioFamilia;
	}

	public BigDecimal getValorInicialSalarioFamilia() {
		return ValorInicialSalarioFamilia;
	}

	public void setValorInicialSalarioFamilia(BigDecimal valorInicialSalarioFamilia) {
		ValorInicialSalarioFamilia = valorInicialSalarioFamilia;
	}

	public BigDecimal getValorFinalSalarioFamilia() {
		return ValorFinalSalarioFamilia;
	}

	public void setValorFinalSalarioFamilia(BigDecimal valorFinalSalarioFamilia) {
		ValorFinalSalarioFamilia = valorFinalSalarioFamilia;
	}

	public BigDecimal getValorSalarioFamilia() {
		return ValorSalarioFamilia;
	}

	public void setValorSalarioFamilia(BigDecimal valorSalarioFamilia) {
		ValorSalarioFamilia = valorSalarioFamilia;
	}
}
