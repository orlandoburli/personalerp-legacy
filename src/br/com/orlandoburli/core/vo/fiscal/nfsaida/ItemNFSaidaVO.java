package br.com.orlandoburli.core.vo.fiscal.nfsaida;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemNFSaidaVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoNFSaida;
	@Key
	private String SerieNFSaida;
	@Key
	private Integer SequencialItemNFSaida;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private String CfopItemNFSaida;
	private String DescricaoItemNFSaida;
	private String NCMItemNFSaida;
	private String UnidadeItemNFSaida;
	
	@Precision(decimals=3)
	private BigDecimal QuantidadeItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorUnitarioItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorSeguroItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorDescontoItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorFreteItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorOutrasDespesasItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalBrutoItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalLiquidoItemNFSaida;
	
	/* Grupo ICMS Normal ou nao tributado */
	private String CstIcmsItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal AliquotaIcmsItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal BaseCalculoIcmsItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal PercentualReducaoBCItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsItemNFSaida;
	
	/* Grupo ICMS Substituicao Tributaria (ST) */
	@Precision(decimals=2)
	private BigDecimal AliquotaIcmsSTItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal PercentualMVAItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal BaseIcmsStItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsSTItemNFSaida;
	
	/* Grupo PIS */
	private String CstPisItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal AliquotaPisItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorPisItemNFSaida;
	
	/* Grupo COFINS */
	private String CstCofinsItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal AliquotaCofinsItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorCofinsItemNFSaida;
	
	/* Grupo IPI */
	private String CstIpiItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal AliquotaIpiItemNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorIpiItemNFSaida;
	
	
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

	/**
	 * @return the codigoEmpresa
	 */
	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the codigoLoja
	 */
	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	/**
	 * @param codigoLoja the codigoLoja to set
	 */
	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	/**
	 * @return the codigoNFSaida
	 */
	public Integer getCodigoNFSaida() {
		return CodigoNFSaida;
	}

	/**
	 * @param codigoNFSaida the codigoNFSaida to set
	 */
	public void setCodigoNFSaida(Integer codigoNFSaida) {
		CodigoNFSaida = codigoNFSaida;
	}

	/**
	 * @return the serieNFSaida
	 */
	public String getSerieNFSaida() {
		return SerieNFSaida;
	}

	/**
	 * @param serieNFSaida the serieNFSaida to set
	 */
	public void setSerieNFSaida(String serieNFSaida) {
		SerieNFSaida = serieNFSaida;
	}

	/**
	 * @return the sequencialItemNFSaida
	 */
	public Integer getSequencialItemNFSaida() {
		return SequencialItemNFSaida;
	}

	/**
	 * @param sequencialItemNFSaida the sequencialItemNFSaida to set
	 */
	public void setSequencialItemNFSaida(Integer sequencialItemNFSaida) {
		SequencialItemNFSaida = sequencialItemNFSaida;
	}

	/**
	 * @return the codigoEmpresaProduto
	 */
	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	/**
	 * @param codigoEmpresaProduto the codigoEmpresaProduto to set
	 */
	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	/**
	 * @return the codigoLojaProduto
	 */
	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	/**
	 * @param codigoLojaProduto the codigoLojaProduto to set
	 */
	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	/**
	 * @return the codigoProduto
	 */
	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	/**
	 * @param codigoProduto the codigoProduto to set
	 */
	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	/**
	 * @return the cfopItemNFSaida
	 */
	public String getCfopItemNFSaida() {
		return CfopItemNFSaida;
	}

	/**
	 * @param cfopItemNFSaida the cfopItemNFSaida to set
	 */
	public void setCfopItemNFSaida(String cfopItemNFSaida) {
		CfopItemNFSaida = cfopItemNFSaida;
	}

	/**
	 * @return the descricaoItemNFSaida
	 */
	public String getDescricaoItemNFSaida() {
		return DescricaoItemNFSaida;
	}

	/**
	 * @param descricaoItemNFSaida the descricaoItemNFSaida to set
	 */
	public void setDescricaoItemNFSaida(String descricaoItemNFSaida) {
		DescricaoItemNFSaida = descricaoItemNFSaida;
	}

	/**
	 * @return the nCMItemNFSaida
	 */
	public String getNCMItemNFSaida() {
		return NCMItemNFSaida;
	}

	/**
	 * @param nCMItemNFSaida the nCMItemNFSaida to set
	 */
	public void setNCMItemNFSaida(String nCMItemNFSaida) {
		NCMItemNFSaida = nCMItemNFSaida;
	}

	/**
	 * @return the unidadeItemNFSaida
	 */
	public String getUnidadeItemNFSaida() {
		return UnidadeItemNFSaida;
	}

	/**
	 * @param unidadeItemNFSaida the unidadeItemNFSaida to set
	 */
	public void setUnidadeItemNFSaida(String unidadeItemNFSaida) {
		UnidadeItemNFSaida = unidadeItemNFSaida;
	}

	/**
	 * @return the quantidadeItemNFSaida
	 */
	public BigDecimal getQuantidadeItemNFSaida() {
		return QuantidadeItemNFSaida;
	}

	/**
	 * @param quantidadeItemNFSaida the quantidadeItemNFSaida to set
	 */
	public void setQuantidadeItemNFSaida(BigDecimal quantidadeItemNFSaida) {
		QuantidadeItemNFSaida = quantidadeItemNFSaida;
	}

	/**
	 * @return the valorUnitarioItemNFSaida
	 */
	public BigDecimal getValorUnitarioItemNFSaida() {
		return ValorUnitarioItemNFSaida;
	}

	/**
	 * @param valorUnitarioItemNFSaida the valorUnitarioItemNFSaida to set
	 */
	public void setValorUnitarioItemNFSaida(BigDecimal valorUnitarioItemNFSaida) {
		ValorUnitarioItemNFSaida = valorUnitarioItemNFSaida;
	}

	/**
	 * @return the valorSeguroItemNFSaida
	 */
	public BigDecimal getValorSeguroItemNFSaida() {
		return ValorSeguroItemNFSaida;
	}

	/**
	 * @param valorSeguroItemNFSaida the valorSeguroItemNFSaida to set
	 */
	public void setValorSeguroItemNFSaida(BigDecimal valorSeguroItemNFSaida) {
		ValorSeguroItemNFSaida = valorSeguroItemNFSaida;
	}

	/**
	 * @return the valorDescontoItemNFSaida
	 */
	public BigDecimal getValorDescontoItemNFSaida() {
		return ValorDescontoItemNFSaida;
	}

	/**
	 * @param valorDescontoItemNFSaida the valorDescontoItemNFSaida to set
	 */
	public void setValorDescontoItemNFSaida(BigDecimal valorDescontoItemNFSaida) {
		ValorDescontoItemNFSaida = valorDescontoItemNFSaida;
	}

	/**
	 * @return the valorFreteItemNFSaida
	 */
	public BigDecimal getValorFreteItemNFSaida() {
		return ValorFreteItemNFSaida;
	}

	/**
	 * @param valorFreteItemNFSaida the valorFreteItemNFSaida to set
	 */
	public void setValorFreteItemNFSaida(BigDecimal valorFreteItemNFSaida) {
		ValorFreteItemNFSaida = valorFreteItemNFSaida;
	}

	/**
	 * @return the valorOutrasDespesasItemNFSaida
	 */
	public BigDecimal getValorOutrasDespesasItemNFSaida() {
		return ValorOutrasDespesasItemNFSaida;
	}

	/**
	 * @param valorOutrasDespesasItemNFSaida the valorOutrasDespesasItemNFSaida to set
	 */
	public void setValorOutrasDespesasItemNFSaida(
			BigDecimal valorOutrasDespesasItemNFSaida) {
		ValorOutrasDespesasItemNFSaida = valorOutrasDespesasItemNFSaida;
	}

	/**
	 * @return the valorTotalBrutoItemNFSaida
	 */
	public BigDecimal getValorTotalBrutoItemNFSaida() {
		return ValorTotalBrutoItemNFSaida;
	}

	/**
	 * @param valorTotalBrutoItemNFSaida the valorTotalBrutoItemNFSaida to set
	 */
	public void setValorTotalBrutoItemNFSaida(BigDecimal valorTotalBrutoItemNFSaida) {
		ValorTotalBrutoItemNFSaida = valorTotalBrutoItemNFSaida;
	}

	/**
	 * @return the cstIcmsItemNFSaida
	 */
	public String getCstIcmsItemNFSaida() {
		return CstIcmsItemNFSaida;
	}

	/**
	 * @param cstIcmsItemNFSaida the cstIcmsItemNFSaida to set
	 */
	public void setCstIcmsItemNFSaida(String cstIcmsItemNFSaida) {
		CstIcmsItemNFSaida = cstIcmsItemNFSaida;
	}

	/**
	 * @return the aliquotaIcmsItemNFSaida
	 */
	public BigDecimal getAliquotaIcmsItemNFSaida() {
		return AliquotaIcmsItemNFSaida;
	}

	/**
	 * @param aliquotaIcmsItemNFSaida the aliquotaIcmsItemNFSaida to set
	 */
	public void setAliquotaIcmsItemNFSaida(BigDecimal aliquotaIcmsItemNFSaida) {
		AliquotaIcmsItemNFSaida = aliquotaIcmsItemNFSaida;
	}

	/**
	 * @return the baseCalculoIcmsItemNFSaida
	 */
	public BigDecimal getBaseCalculoIcmsItemNFSaida() {
		return BaseCalculoIcmsItemNFSaida;
	}

	/**
	 * @param baseCalculoIcmsItemNFSaida the baseCalculoIcmsItemNFSaida to set
	 */
	public void setBaseCalculoIcmsItemNFSaida(BigDecimal baseCalculoIcmsItemNFSaida) {
		BaseCalculoIcmsItemNFSaida = baseCalculoIcmsItemNFSaida;
	}

	/**
	 * @return the percentualReducaoBCItemNFSaida
	 */
	public BigDecimal getPercentualReducaoBCItemNFSaida() {
		return PercentualReducaoBCItemNFSaida;
	}

	/**
	 * @param percentualReducaoBCItemNFSaida the percentualReducaoBCItemNFSaida to set
	 */
	public void setPercentualReducaoBCItemNFSaida(
			BigDecimal percentualReducaoBCItemNFSaida) {
		PercentualReducaoBCItemNFSaida = percentualReducaoBCItemNFSaida;
	}

	/**
	 * @return the valorIcmsItemNFSaida
	 */
	public BigDecimal getValorIcmsItemNFSaida() {
		return ValorIcmsItemNFSaida;
	}

	/**
	 * @param valorIcmsItemNFSaida the valorIcmsItemNFSaida to set
	 */
	public void setValorIcmsItemNFSaida(BigDecimal valorIcmsItemNFSaida) {
		ValorIcmsItemNFSaida = valorIcmsItemNFSaida;
	}

	/**
	 * @return the aliquotaIcmsSTItemNFSaida
	 */
	public BigDecimal getAliquotaIcmsSTItemNFSaida() {
		return AliquotaIcmsSTItemNFSaida;
	}

	/**
	 * @param aliquotaIcmsSTItemNFSaida the aliquotaIcmsSTItemNFSaida to set
	 */
	public void setAliquotaIcmsSTItemNFSaida(BigDecimal aliquotaIcmsSTItemNFSaida) {
		AliquotaIcmsSTItemNFSaida = aliquotaIcmsSTItemNFSaida;
	}

	/**
	 * @return the percentualMVAItemNFSaida
	 */
	public BigDecimal getPercentualMVAItemNFSaida() {
		return PercentualMVAItemNFSaida;
	}

	/**
	 * @param percentualMVAItemNFSaida the percentualMVAItemNFSaida to set
	 */
	public void setPercentualMVAItemNFSaida(BigDecimal percentualMVAItemNFSaida) {
		PercentualMVAItemNFSaida = percentualMVAItemNFSaida;
	}

	/**
	 * @return the baseIcmsStItemNFSaida
	 */
	public BigDecimal getBaseIcmsStItemNFSaida() {
		return BaseIcmsStItemNFSaida;
	}

	/**
	 * @param baseIcmsStItemNFSaida the baseIcmsStItemNFSaida to set
	 */
	public void setBaseIcmsStItemNFSaida(BigDecimal baseIcmsStItemNFSaida) {
		BaseIcmsStItemNFSaida = baseIcmsStItemNFSaida;
	}

	/**
	 * @return the valorIcmsSTItemNFSaida
	 */
	public BigDecimal getValorIcmsSTItemNFSaida() {
		return ValorIcmsSTItemNFSaida;
	}

	/**
	 * @param valorIcmsSTItemNFSaida the valorIcmsSTItemNFSaida to set
	 */
	public void setValorIcmsSTItemNFSaida(BigDecimal valorIcmsSTItemNFSaida) {
		ValorIcmsSTItemNFSaida = valorIcmsSTItemNFSaida;
	}

	/**
	 * @return the cstPisItemNFSaida
	 */
	public String getCstPisItemNFSaida() {
		return CstPisItemNFSaida;
	}

	/**
	 * @param cstPisItemNFSaida the cstPisItemNFSaida to set
	 */
	public void setCstPisItemNFSaida(String cstPisItemNFSaida) {
		CstPisItemNFSaida = cstPisItemNFSaida;
	}

	/**
	 * @return the aliquotaPisItemNFSaida
	 */
	public BigDecimal getAliquotaPisItemNFSaida() {
		return AliquotaPisItemNFSaida;
	}

	/**
	 * @param aliquotaPisItemNFSaida the aliquotaPisItemNFSaida to set
	 */
	public void setAliquotaPisItemNFSaida(BigDecimal aliquotaPisItemNFSaida) {
		AliquotaPisItemNFSaida = aliquotaPisItemNFSaida;
	}

	/**
	 * @return the valorPisItemNFSaida
	 */
	public BigDecimal getValorPisItemNFSaida() {
		return ValorPisItemNFSaida;
	}

	/**
	 * @param valorPisItemNFSaida the valorPisItemNFSaida to set
	 */
	public void setValorPisItemNFSaida(BigDecimal valorPisItemNFSaida) {
		ValorPisItemNFSaida = valorPisItemNFSaida;
	}

	/**
	 * @return the cstCofinsItemNFSaida
	 */
	public String getCstCofinsItemNFSaida() {
		return CstCofinsItemNFSaida;
	}

	/**
	 * @param cstCofinsItemNFSaida the cstCofinsItemNFSaida to set
	 */
	public void setCstCofinsItemNFSaida(String cstCofinsItemNFSaida) {
		CstCofinsItemNFSaida = cstCofinsItemNFSaida;
	}

	/**
	 * @return the aliquotaCofinsItemNFSaida
	 */
	public BigDecimal getAliquotaCofinsItemNFSaida() {
		return AliquotaCofinsItemNFSaida;
	}

	/**
	 * @param aliquotaCofinsItemNFSaida the aliquotaCofinsItemNFSaida to set
	 */
	public void setAliquotaCofinsItemNFSaida(BigDecimal aliquotaCofinsItemNFSaida) {
		AliquotaCofinsItemNFSaida = aliquotaCofinsItemNFSaida;
	}

	/**
	 * @return the valorCofinsItemNFSaida
	 */
	public BigDecimal getValorCofinsItemNFSaida() {
		return ValorCofinsItemNFSaida;
	}

	/**
	 * @param valorCofinsItemNFSaida the valorCofinsItemNFSaida to set
	 */
	public void setValorCofinsItemNFSaida(BigDecimal valorCofinsItemNFSaida) {
		ValorCofinsItemNFSaida = valorCofinsItemNFSaida;
	}

	/**
	 * @return the cstIpiItemNFSaida
	 */
	public String getCstIpiItemNFSaida() {
		return CstIpiItemNFSaida;
	}

	/**
	 * @param cstIpiItemNFSaida the cstIpiItemNFSaida to set
	 */
	public void setCstIpiItemNFSaida(String cstIpiItemNFSaida) {
		CstIpiItemNFSaida = cstIpiItemNFSaida;
	}

	/**
	 * @return the aliquotaIpiItemNFSaida
	 */
	public BigDecimal getAliquotaIpiItemNFSaida() {
		return AliquotaIpiItemNFSaida;
	}

	/**
	 * @param aliquotaIpiItemNFSaida the aliquotaIpiItemNFSaida to set
	 */
	public void setAliquotaIpiItemNFSaida(BigDecimal aliquotaIpiItemNFSaida) {
		AliquotaIpiItemNFSaida = aliquotaIpiItemNFSaida;
	}

	/**
	 * @return the valorIpiItemNFSaida
	 */
	public BigDecimal getValorIpiItemNFSaida() {
		return ValorIpiItemNFSaida;
	}

	/**
	 * @param valorIpiItemNFSaida the valorIpiItemNFSaida to set
	 */
	public void setValorIpiItemNFSaida(BigDecimal valorIpiItemNFSaida) {
		ValorIpiItemNFSaida = valorIpiItemNFSaida;
	}

	public void setValorTotalLiquidoItemNFSaida(
			BigDecimal valorTotalLiquidoItemNFSaida) {
		ValorTotalLiquidoItemNFSaida = valorTotalLiquidoItemNFSaida;
	}

	public BigDecimal getValorTotalLiquidoItemNFSaida() {
		return ValorTotalLiquidoItemNFSaida;
	}
}