package br.com.orlandoburli.core.vo.fiscal.cupomfiscal;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemCupomFiscalVO implements IValueObject {

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
	@Key
	private Integer SequencialItemCupomFiscal;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	@Precision(decimals=2)
	private BigDecimal QuantidadeItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorUnitarioItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorTotalItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorDescontoItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorAcrescimoItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorLiquidoItemCupomFiscal;
	
	@Precision(decimals=2)
	private BigDecimal ValorBaseIcmsItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorBaseIpiItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorBasePisItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorBaseCofinsItemCupomFiscal;
	
	@Precision(decimals=2)
	private BigDecimal AliquotaIcmsItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal AliquotaIpiItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal AliquotaPisItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal AliquotaCofinsItemCupomFiscal;
	
	@Precision(decimals=2)
	private BigDecimal ValorIcmsItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorIpiItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorPisItemCupomFiscal;
	@Precision(decimals=2)
	private BigDecimal ValorCofinsItemCupomFiscal;
	
	private String CstIcmsItemCupomFiscal;
	private String CstIpiItemCupomFiscal;
	private String CstPisItemCupomFiscal;
	private String CstCofinsItemCupomFiscal;
	
	private String CfopItemCupomFiscal;
	
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

	public Integer getSequencialItemCupomFiscal() {
		return SequencialItemCupomFiscal;
	}

	public void setSequencialItemCupomFiscal(Integer sequencialItemCupomFiscal) {
		SequencialItemCupomFiscal = sequencialItemCupomFiscal;
	}

	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public BigDecimal getQuantidadeItemCupomFiscal() {
		return QuantidadeItemCupomFiscal;
	}

	public void setQuantidadeItemCupomFiscal(BigDecimal quantidadeItemCupomFiscal) {
		QuantidadeItemCupomFiscal = quantidadeItemCupomFiscal;
	}

	public BigDecimal getValorUnitarioItemCupomFiscal() {
		return ValorUnitarioItemCupomFiscal;
	}

	public void setValorUnitarioItemCupomFiscal(BigDecimal valorUnitarioItemCupomFiscal) {
		ValorUnitarioItemCupomFiscal = valorUnitarioItemCupomFiscal;
	}

	public BigDecimal getValorTotalItemCupomFiscal() {
		return ValorTotalItemCupomFiscal;
	}

	public void setValorTotalItemCupomFiscal(BigDecimal valorTotalItemCupomFiscal) {
		ValorTotalItemCupomFiscal = valorTotalItemCupomFiscal;
	}

	public BigDecimal getValorAcrescimoItemCupomFiscal() {
		return ValorAcrescimoItemCupomFiscal;
	}

	public void setValorAcrescimoItemCupomFiscal(BigDecimal valorAcrescimoItemCupomFiscal) {
		ValorAcrescimoItemCupomFiscal = valorAcrescimoItemCupomFiscal;
	}

	public BigDecimal getValorLiquidoItemCupomFiscal() {
		return ValorLiquidoItemCupomFiscal;
	}

	public void setValorLiquidoItemCupomFiscal(BigDecimal valorLiquidoItemCupomFiscal) {
		ValorLiquidoItemCupomFiscal = valorLiquidoItemCupomFiscal;
	}

	public BigDecimal getValorBaseIcmsItemCupomFiscal() {
		return ValorBaseIcmsItemCupomFiscal;
	}

	public void setValorBaseIcmsItemCupomFiscal(BigDecimal valorBaseIcmsItemCupomFiscal) {
		ValorBaseIcmsItemCupomFiscal = valorBaseIcmsItemCupomFiscal;
	}

	public BigDecimal getValorBaseIpiItemCupomFiscal() {
		return ValorBaseIpiItemCupomFiscal;
	}

	public void setValorBaseIpiItemCupomFiscal(BigDecimal valorBaseIpiItemCupomFiscal) {
		ValorBaseIpiItemCupomFiscal = valorBaseIpiItemCupomFiscal;
	}

	public BigDecimal getValorBasePisItemCupomFiscal() {
		return ValorBasePisItemCupomFiscal;
	}

	public void setValorBasePisItemCupomFiscal(BigDecimal valorBasePisItemCupomFiscal) {
		ValorBasePisItemCupomFiscal = valorBasePisItemCupomFiscal;
	}

	public BigDecimal getValorBaseCofinsItemCupomFiscal() {
		return ValorBaseCofinsItemCupomFiscal;
	}

	public void setValorBaseCofinsItemCupomFiscal(BigDecimal valorBaseCofinsItemCupomFiscal) {
		ValorBaseCofinsItemCupomFiscal = valorBaseCofinsItemCupomFiscal;
	}

	public BigDecimal getAliquotaIcmsItemCupomFiscal() {
		return AliquotaIcmsItemCupomFiscal;
	}

	public void setAliquotaIcmsItemCupomFiscal(BigDecimal aliquotaIcmsItemCupomFiscal) {
		AliquotaIcmsItemCupomFiscal = aliquotaIcmsItemCupomFiscal;
	}

	public BigDecimal getAliquotaIpiItemCupomFiscal() {
		return AliquotaIpiItemCupomFiscal;
	}

	public void setAliquotaIpiItemCupomFiscal(BigDecimal aliquotaIpiItemCupomFiscal) {
		AliquotaIpiItemCupomFiscal = aliquotaIpiItemCupomFiscal;
	}

	public BigDecimal getAliquotaPisItemCupomFiscal() {
		return AliquotaPisItemCupomFiscal;
	}

	public void setAliquotaPisItemCupomFiscal(BigDecimal aliquotaPisItemCupomFiscal) {
		AliquotaPisItemCupomFiscal = aliquotaPisItemCupomFiscal;
	}

	public BigDecimal getAliquotaCofinsItemCupomFiscal() {
		return AliquotaCofinsItemCupomFiscal;
	}

	public void setAliquotaCofinsItemCupomFiscal(BigDecimal aliquotaCofinsItemCupomFiscal) {
		AliquotaCofinsItemCupomFiscal = aliquotaCofinsItemCupomFiscal;
	}

	public BigDecimal getValorIcmsItemCupomFiscal() {
		return ValorIcmsItemCupomFiscal;
	}

	public void setValorIcmsItemCupomFiscal(BigDecimal valorIcmsItemCupomFiscal) {
		ValorIcmsItemCupomFiscal = valorIcmsItemCupomFiscal;
	}

	public BigDecimal getValorIpiItemCupomFiscal() {
		return ValorIpiItemCupomFiscal;
	}

	public void setValorIpiItemCupomFiscal(BigDecimal valorIpiItemCupomFiscal) {
		ValorIpiItemCupomFiscal = valorIpiItemCupomFiscal;
	}

	public BigDecimal getValorPisItemCupomFiscal() {
		return ValorPisItemCupomFiscal;
	}

	public void setValorPisItemCupomFiscal(BigDecimal valorPisItemCupomFiscal) {
		ValorPisItemCupomFiscal = valorPisItemCupomFiscal;
	}

	public BigDecimal getValorCofinsItemCupomFiscal() {
		return ValorCofinsItemCupomFiscal;
	}

	public void setValorCofinsItemCupomFiscal(BigDecimal valorCofinsItemCupomFiscal) {
		ValorCofinsItemCupomFiscal = valorCofinsItemCupomFiscal;
	}

	public String getCstIcmsItemCupomFiscal() {
		return CstIcmsItemCupomFiscal;
	}

	public void setCstIcmsItemCupomFiscal(String cstIcmsItemCupomFiscal) {
		CstIcmsItemCupomFiscal = cstIcmsItemCupomFiscal;
	}

	public String getCstIpiItemCupomFiscal() {
		return CstIpiItemCupomFiscal;
	}

	public void setCstIpiItemCupomFiscal(String cstIpiItemCupomFiscal) {
		CstIpiItemCupomFiscal = cstIpiItemCupomFiscal;
	}

	public String getCstPisItemCupomFiscal() {
		return CstPisItemCupomFiscal;
	}

	public void setCstPisItemCupomFiscal(String cstPisItemCupomFiscal) {
		CstPisItemCupomFiscal = cstPisItemCupomFiscal;
	}

	public String getCstCofinsItemCupomFiscal() {
		return CstCofinsItemCupomFiscal;
	}

	public void setCstCofinsItemCupomFiscal(String cstCofinsItemCupomFiscal) {
		CstCofinsItemCupomFiscal = cstCofinsItemCupomFiscal;
	}

	public BigDecimal getValorDescontoItemCupomFiscal() {
		return ValorDescontoItemCupomFiscal;
	}

	public void setValorDescontoItemCupomFiscal(BigDecimal valorDescontoItemCupomFiscal) {
		ValorDescontoItemCupomFiscal = valorDescontoItemCupomFiscal;
	}

	public String getCfopItemCupomFiscal() {
		return CfopItemCupomFiscal;
	}

	public void setCfopItemCupomFiscal(String cfopItemCupomFiscal) {
		CfopItemCupomFiscal = cfopItemCupomFiscal;
	}
}
