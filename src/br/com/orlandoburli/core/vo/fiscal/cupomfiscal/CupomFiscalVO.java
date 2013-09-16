package br.com.orlandoburli.core.vo.fiscal.cupomfiscal;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class CupomFiscalVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoPdv;
	@Key
	private String SerieCupomFiscal;
	@Key
	private Integer CodigoCupomFiscal;
	
	private Timestamp DataHoraCupomFiscal;
	private String StatusCupomFiscal;
	private Timestamp DataHoraCancelamentoCupomFiscal;
	
	private String NomeClienteCupomFiscal;
	private String CpfCnpjClienteCupomFiscal;
	
	@Precision(decimals=2)
	private BigDecimal ValorTotalItensCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorDescontoCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorAcrescimoCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorTotalCupomFiscal;
	
	@Precision(decimals=2)
	private BigDecimal ValorTotalIcmsCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorTotalIpiCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorTotalPisCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorTotalCofinsCupomFiscal;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private String NumeroSerieEcf;
	private String NumeroCupomFiscal;
	
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

	public Integer getCodigoPdv() {
		return CodigoPdv;
	}

	public void setCodigoPdv(Integer codigoPdv) {
		CodigoPdv = codigoPdv;
	}

	public String getSerieCupomFiscal() {
		return SerieCupomFiscal;
	}

	public void setSerieCupomFiscal(String serieCupomFiscal) {
		SerieCupomFiscal = serieCupomFiscal;
	}

	public Integer getCodigoCupomFiscal() {
		return CodigoCupomFiscal;
	}

	public void setCodigoCupomFiscal(Integer codigoCupomFiscal) {
		CodigoCupomFiscal = codigoCupomFiscal;
	}

	public Timestamp getDataHoraCupomFiscal() {
		return DataHoraCupomFiscal;
	}

	public void setDataHoraCupomFiscal(Timestamp dataHoraCupomFiscal) {
		DataHoraCupomFiscal = dataHoraCupomFiscal;
	}

	public String getStatusCupomFiscal() {
		return StatusCupomFiscal;
	}

	public void setStatusCupomFiscal(String statusCupomFiscal) {
		StatusCupomFiscal = statusCupomFiscal;
	}

	public Timestamp getDataHoraCancelamentoCupomFiscal() {
		return DataHoraCancelamentoCupomFiscal;
	}

	public void setDataHoraCancelamentoCupomFiscal(Timestamp dataHoraCancelamentoCupomFiscal) {
		DataHoraCancelamentoCupomFiscal = dataHoraCancelamentoCupomFiscal;
	}

	public BigDecimal getValorTotalItensCupomFiscal() {
		return ValorTotalItensCupomFiscal;
	}

	public void setValorTotalItensCupomFiscal(BigDecimal valorTotalItensCupomFiscal) {
		ValorTotalItensCupomFiscal = valorTotalItensCupomFiscal;
	}

	public BigDecimal getValorDescontoCupomFiscal() {
		return ValorDescontoCupomFiscal;
	}

	public void setValorDescontoCupomFiscal(BigDecimal valorDescontoCupomFiscal) {
		ValorDescontoCupomFiscal = valorDescontoCupomFiscal;
	}

	public BigDecimal getValorAcrescimoCupomFiscal() {
		return ValorAcrescimoCupomFiscal;
	}

	public void setValorAcrescimoCupomFiscal(BigDecimal valorAcrescimoCupomFiscal) {
		ValorAcrescimoCupomFiscal = valorAcrescimoCupomFiscal;
	}

	public BigDecimal getValorTotalIcmsCupomFiscal() {
		return ValorTotalIcmsCupomFiscal;
	}

	public void setValorTotalIcmsCupomFiscal(BigDecimal valorTotalIcmsCupomFiscal) {
		ValorTotalIcmsCupomFiscal = valorTotalIcmsCupomFiscal;
	}

	public BigDecimal getValorTotalIpiCupomFiscal() {
		return ValorTotalIpiCupomFiscal;
	}

	public void setValorTotalIpiCupomFiscal(BigDecimal valorTotalIpiCupomFiscal) {
		ValorTotalIpiCupomFiscal = valorTotalIpiCupomFiscal;
	}

	public BigDecimal getValorTotalPisCupomFiscal() {
		return ValorTotalPisCupomFiscal;
	}

	public void setValorTotalPisCupomFiscal(BigDecimal valorTotalPisCupomFiscal) {
		ValorTotalPisCupomFiscal = valorTotalPisCupomFiscal;
	}

	public BigDecimal getValorTotalCofinsCupomFiscal() {
		return ValorTotalCofinsCupomFiscal;
	}

	public void setValorTotalCofinsCupomFiscal(BigDecimal valorTotalCofinsCupomFiscal) {
		ValorTotalCofinsCupomFiscal = valorTotalCofinsCupomFiscal;
	}

	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public String getNumeroSerieEcf() {
		return NumeroSerieEcf;
	}

	public void setNumeroSerieEcf(String numeroSerieEcf) {
		NumeroSerieEcf = numeroSerieEcf;
	}

	public String getNumeroCupomFiscal() {
		return NumeroCupomFiscal;
	}

	public void setNumeroCupomFiscal(String numeroCupomFiscal) {
		NumeroCupomFiscal = numeroCupomFiscal;
	}

	public BigDecimal getValorTotalCupomFiscal() {
		return ValorTotalCupomFiscal;
	}

	public void setValorTotalCupomFiscal(BigDecimal valorTotalCupomFiscal) {
		ValorTotalCupomFiscal = valorTotalCupomFiscal;
	}

	public String getNomeClienteCupomFiscal() {
		return NomeClienteCupomFiscal;
	}

	public void setNomeClienteCupomFiscal(String nomeClienteCupomFiscal) {
		NomeClienteCupomFiscal = nomeClienteCupomFiscal;
	}

	public String getCpfCnpjClienteCupomFiscal() {
		return CpfCnpjClienteCupomFiscal;
	}

	public void setCpfCnpjClienteCupomFiscal(String cpfCnpjClienteCupomFiscal) {
		CpfCnpjClienteCupomFiscal = cpfCnpjClienteCupomFiscal;
	}
}
