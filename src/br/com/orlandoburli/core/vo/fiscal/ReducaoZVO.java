package br.com.orlandoburli.core.vo.fiscal;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ReducaoZVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;

	@Key
	@AutoIncrement
	private Integer CodigoReducaoZ;
	private String NumeroSerieEcf;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoPdv;

	private Date DataReducaoZ;

	private Integer CroReducaoZ;
	private Integer CrzReducaoZ;

	private Integer NumeroReducaoZ;

	@Precision(decimals = 2)
	private BigDecimal ValorGrandeTotalReducaoZ;
	@Precision(decimals = 2)
	private BigDecimal ValorVendaBrutaReducaoZ;
	@Precision(decimals = 2)
	private BigDecimal ValorTotalPisReducaoZ;
	@Precision(decimals = 2)
	private BigDecimal ValorTotalCofinsReducaoZ;

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

	public Integer getCodigoReducaoZ() {
		return CodigoReducaoZ;
	}

	public void setCodigoReducaoZ(Integer codigoReducaoZ) {
		CodigoReducaoZ = codigoReducaoZ;
	}

	public String getNumeroSerieEcf() {
		return NumeroSerieEcf;
	}

	public void setNumeroSerieEcf(String numeroSerieEcf) {
		NumeroSerieEcf = numeroSerieEcf;
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

	public Integer getCodigoPdv() {
		return CodigoPdv;
	}

	public void setCodigoPdv(Integer codigoPdv) {
		CodigoPdv = codigoPdv;
	}

	public Date getDataReducaoZ() {
		return DataReducaoZ;
	}

	public void setDataReducaoZ(Date dataReducaoZ) {
		DataReducaoZ = dataReducaoZ;
	}

	public Integer getCroReducaoZ() {
		return CroReducaoZ;
	}

	public void setCroReducaoZ(Integer croReducaoZ) {
		CroReducaoZ = croReducaoZ;
	}

	public Integer getCrzReducaoZ() {
		return CrzReducaoZ;
	}

	public void setCrzReducaoZ(Integer crzReducaoZ) {
		CrzReducaoZ = crzReducaoZ;
	}

	public Integer getNumeroReducaoZ() {
		return NumeroReducaoZ;
	}

	public void setNumeroReducaoZ(Integer numeroReducaoZ) {
		NumeroReducaoZ = numeroReducaoZ;
	}

	public BigDecimal getValorGrandeTotalReducaoZ() {
		return ValorGrandeTotalReducaoZ;
	}

	public void setValorGrandeTotalReducaoZ(BigDecimal valorGrandeTotalReducaoZ) {
		ValorGrandeTotalReducaoZ = valorGrandeTotalReducaoZ;
	}

	public BigDecimal getValorVendaBrutaReducaoZ() {
		return ValorVendaBrutaReducaoZ;
	}

	public void setValorVendaBrutaReducaoZ(BigDecimal valorVendaBrutaReducaoZ) {
		ValorVendaBrutaReducaoZ = valorVendaBrutaReducaoZ;
	}

	public BigDecimal getValorTotalPisReducaoZ() {
		return ValorTotalPisReducaoZ;
	}

	public void setValorTotalPisReducaoZ(BigDecimal valorTotalPisReducaoZ) {
		ValorTotalPisReducaoZ = valorTotalPisReducaoZ;
	}

	public BigDecimal getValorTotalCofinsReducaoZ() {
		return ValorTotalCofinsReducaoZ;
	}

	public void setValorTotalCofinsReducaoZ(BigDecimal valorTotalCofinsReducaoZ) {
		ValorTotalCofinsReducaoZ = valorTotalCofinsReducaoZ;
	}
}
