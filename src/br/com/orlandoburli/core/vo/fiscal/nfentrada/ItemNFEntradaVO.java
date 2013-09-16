package br.com.orlandoburli.core.vo.fiscal.nfentrada;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemNFEntradaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoNFEntrada;
	@Key
	private String SerieNFEntrada;
	@Key
	private Integer CodigoEmpresaEmitenteNFEntrada;
	@Key
	private Integer CodigoLojaEmitenteNFEntrada;
	@Key
	private Integer CodigoPessoaEmitenteNFEntrada;
	@Key
	private Integer CodigoEnderecoPessoaEmitenteNFEntrada;
	@Key
	private Integer SequencialItemNFEntrada;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private String CfopItemNFEntrada;
	private String DescricaoItemNFEntrada;
	private String NCMItemNFEntrada;
	private String UnidadeItemNFEntrada;
	
	@Formula(getFormula="(SELECT b.DataEmissaoNFEntrada FROM [schema].NFEntrada b " +
			" WHERE a.CodigoEmpresa = b.CodigoEmpresa  " +
			"   AND a.CodigoLoja = b.CodigoLoja " +
			"   AND a.CodigoNFEntrada = b.CodigoNFEntrada " +
			"   AND a.SerieNFEntrada = b.SerieNFEntrada" +
			"   AND a.CodigoEmpresaEmitenteNFEntrada = b.CodigoEmpresaEmitenteNFEntrada" +
			"   AND a.CodigoLojaEmitenteNFEntrada = b.CodigoLojaEmitenteNFEntrada" +
			"   AND a.CodigoPessoaEmitenteNFEntrada = b.CodigoPessoaEmitenteNFEntrada" +
			"   AND a.CodigoEnderecoPessoaEmitenteNFEntrada = b.CodigoEnderecoPessoaEmitenteNFEntrada)")
	private Date DataEmissaoNFEntrada;
	
	@Precision(decimals=3)
	private BigDecimal QuantidadeItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorUnitarioItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorSeguroItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorDescontoItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorFreteItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorOutrasDespesasItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalBrutoItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalLiquidoItemNFEntrada;
	
	/* Grupo ICMS Normal ou nao tributado */
	private String CstIcmsItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal AliquotaIcmsItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal BaseCalculoIcmsItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal PercentualReducaoBCItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsItemNFEntrada;
	
	/* Grupo ICMS Substituicao Tributaria (ST) */
	@Precision(decimals=2)
	private BigDecimal AliquotaIcmsSTItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal PercentualMVAItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal BaseIcmsStItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsSTItemNFEntrada;
	
	/* Grupo PIS */
	private String CstPisItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal AliquotaPisItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorPisItemNFEntrada;
	
	/* Grupo COFINS */
	private String CstCofinsItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal AliquotaCofinsItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorCofinsItemNFEntrada;
	
	/* Grupo IPI */
	private String CstIpiItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal AliquotaIpiItemNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorIpiItemNFEntrada;
	
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
	 * @return the codigoNFEntrada
	 */
	public Integer getCodigoNFEntrada() {
		return CodigoNFEntrada;
	}

	/**
	 * @param codigoNFEntrada the codigoNFEntrada to set
	 */
	public void setCodigoNFEntrada(Integer codigoNFEntrada) {
		CodigoNFEntrada = codigoNFEntrada;
	}

	/**
	 * @return the serieNFEntrada
	 */
	public String getSerieNFEntrada() {
		return SerieNFEntrada;
	}

	/**
	 * @param serieNFEntrada the serieNFEntrada to set
	 */
	public void setSerieNFEntrada(String serieNFEntrada) {
		SerieNFEntrada = serieNFEntrada;
	}

	/**
	 * @return the sequencialItemNFEntrada
	 */
	public Integer getSequencialItemNFEntrada() {
		return SequencialItemNFEntrada;
	}

	/**
	 * @param sequencialItemNFEntrada the sequencialItemNFEntrada to set
	 */
	public void setSequencialItemNFEntrada(Integer sequencialItemNFEntrada) {
		SequencialItemNFEntrada = sequencialItemNFEntrada;
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
	 * @return the cfopItemNFEntrada
	 */
	public String getCfopItemNFEntrada() {
		return CfopItemNFEntrada;
	}

	/**
	 * @param cfopItemNFEntrada the cfopItemNFEntrada to set
	 */
	public void setCfopItemNFEntrada(String cfopItemNFEntrada) {
		CfopItemNFEntrada = cfopItemNFEntrada;
	}

	/**
	 * @return the descricaoItemNFEntrada
	 */
	public String getDescricaoItemNFEntrada() {
		return DescricaoItemNFEntrada;
	}

	/**
	 * @param descricaoItemNFEntrada the descricaoItemNFEntrada to set
	 */
	public void setDescricaoItemNFEntrada(String descricaoItemNFEntrada) {
		DescricaoItemNFEntrada = descricaoItemNFEntrada;
	}

	/**
	 * @return the nCMItemNFEntrada
	 */
	public String getNCMItemNFEntrada() {
		return NCMItemNFEntrada;
	}

	/**
	 * @param nCMItemNFEntrada the nCMItemNFEntrada to set
	 */
	public void setNCMItemNFEntrada(String nCMItemNFEntrada) {
		NCMItemNFEntrada = nCMItemNFEntrada;
	}

	/**
	 * @return the unidadeItemNFEntrada
	 */
	public String getUnidadeItemNFEntrada() {
		return UnidadeItemNFEntrada;
	}

	/**
	 * @param unidadeItemNFEntrada the unidadeItemNFEntrada to set
	 */
	public void setUnidadeItemNFEntrada(String unidadeItemNFEntrada) {
		UnidadeItemNFEntrada = unidadeItemNFEntrada;
	}

	/**
	 * @return the quantidadeItemNFEntrada
	 */
	public BigDecimal getQuantidadeItemNFEntrada() {
		return QuantidadeItemNFEntrada;
	}

	/**
	 * @param quantidadeItemNFEntrada the quantidadeItemNFEntrada to set
	 */
	public void setQuantidadeItemNFEntrada(BigDecimal quantidadeItemNFEntrada) {
		QuantidadeItemNFEntrada = quantidadeItemNFEntrada;
	}

	/**
	 * @return the valorUnitarioItemNFEntrada
	 */
	public BigDecimal getValorUnitarioItemNFEntrada() {
		return ValorUnitarioItemNFEntrada;
	}

	/**
	 * @param valorUnitarioItemNFEntrada the valorUnitarioItemNFEntrada to set
	 */
	public void setValorUnitarioItemNFEntrada(BigDecimal valorUnitarioItemNFEntrada) {
		ValorUnitarioItemNFEntrada = valorUnitarioItemNFEntrada;
	}

	/**
	 * @return the valorSeguroItemNFEntrada
	 */
	public BigDecimal getValorSeguroItemNFEntrada() {
		return ValorSeguroItemNFEntrada;
	}

	/**
	 * @param valorSeguroItemNFEntrada the valorSeguroItemNFEntrada to set
	 */
	public void setValorSeguroItemNFEntrada(BigDecimal valorSeguroItemNFEntrada) {
		ValorSeguroItemNFEntrada = valorSeguroItemNFEntrada;
	}

	/**
	 * @return the valorDescontoItemNFEntrada
	 */
	public BigDecimal getValorDescontoItemNFEntrada() {
		return ValorDescontoItemNFEntrada;
	}

	/**
	 * @param valorDescontoItemNFEntrada the valorDescontoItemNFEntrada to set
	 */
	public void setValorDescontoItemNFEntrada(BigDecimal valorDescontoItemNFEntrada) {
		ValorDescontoItemNFEntrada = valorDescontoItemNFEntrada;
	}

	/**
	 * @return the valorFreteItemNFEntrada
	 */
	public BigDecimal getValorFreteItemNFEntrada() {
		return ValorFreteItemNFEntrada;
	}

	/**
	 * @param valorFreteItemNFEntrada the valorFreteItemNFEntrada to set
	 */
	public void setValorFreteItemNFEntrada(BigDecimal valorFreteItemNFEntrada) {
		ValorFreteItemNFEntrada = valorFreteItemNFEntrada;
	}

	/**
	 * @return the valorOutrasDespesasItemNFEntrada
	 */
	public BigDecimal getValorOutrasDespesasItemNFEntrada() {
		return ValorOutrasDespesasItemNFEntrada;
	}

	/**
	 * @param valorOutrasDespesasItemNFEntrada the valorOutrasDespesasItemNFEntrada to set
	 */
	public void setValorOutrasDespesasItemNFEntrada(
			BigDecimal valorOutrasDespesasItemNFEntrada) {
		ValorOutrasDespesasItemNFEntrada = valorOutrasDespesasItemNFEntrada;
	}

	/**
	 * @return the valorTotalBrutoItemNFEntrada
	 */
	public BigDecimal getValorTotalBrutoItemNFEntrada() {
		return ValorTotalBrutoItemNFEntrada;
	}

	/**
	 * @param valorTotalBrutoItemNFEntrada the valorTotalBrutoItemNFEntrada to set
	 */
	public void setValorTotalBrutoItemNFEntrada(BigDecimal valorTotalBrutoItemNFEntrada) {
		ValorTotalBrutoItemNFEntrada = valorTotalBrutoItemNFEntrada;
	}

	/**
	 * @return the cstIcmsItemNFEntrada
	 */
	public String getCstIcmsItemNFEntrada() {
		return CstIcmsItemNFEntrada;
	}

	/**
	 * @param cstIcmsItemNFEntrada the cstIcmsItemNFEntrada to set
	 */
	public void setCstIcmsItemNFEntrada(String cstIcmsItemNFEntrada) {
		CstIcmsItemNFEntrada = cstIcmsItemNFEntrada;
	}

	/**
	 * @return the aliquotaIcmsItemNFEntrada
	 */
	public BigDecimal getAliquotaIcmsItemNFEntrada() {
		return AliquotaIcmsItemNFEntrada;
	}

	/**
	 * @param aliquotaIcmsItemNFEntrada the aliquotaIcmsItemNFEntrada to set
	 */
	public void setAliquotaIcmsItemNFEntrada(BigDecimal aliquotaIcmsItemNFEntrada) {
		AliquotaIcmsItemNFEntrada = aliquotaIcmsItemNFEntrada;
	}

	/**
	 * @return the baseCalculoIcmsItemNFEntrada
	 */
	public BigDecimal getBaseCalculoIcmsItemNFEntrada() {
		return BaseCalculoIcmsItemNFEntrada;
	}

	/**
	 * @param baseCalculoIcmsItemNFEntrada the baseCalculoIcmsItemNFEntrada to set
	 */
	public void setBaseCalculoIcmsItemNFEntrada(BigDecimal baseCalculoIcmsItemNFEntrada) {
		BaseCalculoIcmsItemNFEntrada = baseCalculoIcmsItemNFEntrada;
	}

	/**
	 * @return the percentualReducaoBCItemNFEntrada
	 */
	public BigDecimal getPercentualReducaoBCItemNFEntrada() {
		return PercentualReducaoBCItemNFEntrada;
	}

	/**
	 * @param percentualReducaoBCItemNFEntrada the percentualReducaoBCItemNFEntrada to set
	 */
	public void setPercentualReducaoBCItemNFEntrada(
			BigDecimal percentualReducaoBCItemNFEntrada) {
		PercentualReducaoBCItemNFEntrada = percentualReducaoBCItemNFEntrada;
	}

	/**
	 * @return the valorIcmsItemNFEntrada
	 */
	public BigDecimal getValorIcmsItemNFEntrada() {
		return ValorIcmsItemNFEntrada;
	}

	/**
	 * @param valorIcmsItemNFEntrada the valorIcmsItemNFEntrada to set
	 */
	public void setValorIcmsItemNFEntrada(BigDecimal valorIcmsItemNFEntrada) {
		ValorIcmsItemNFEntrada = valorIcmsItemNFEntrada;
	}

	/**
	 * @return the aliquotaIcmsSTItemNFEntrada
	 */
	public BigDecimal getAliquotaIcmsSTItemNFEntrada() {
		return AliquotaIcmsSTItemNFEntrada;
	}

	/**
	 * @param aliquotaIcmsSTItemNFEntrada the aliquotaIcmsSTItemNFEntrada to set
	 */
	public void setAliquotaIcmsSTItemNFEntrada(BigDecimal aliquotaIcmsSTItemNFEntrada) {
		AliquotaIcmsSTItemNFEntrada = aliquotaIcmsSTItemNFEntrada;
	}

	/**
	 * @return the percentualMVAItemNFEntrada
	 */
	public BigDecimal getPercentualMVAItemNFEntrada() {
		return PercentualMVAItemNFEntrada;
	}

	/**
	 * @param percentualMVAItemNFEntrada the percentualMVAItemNFEntrada to set
	 */
	public void setPercentualMVAItemNFEntrada(BigDecimal percentualMVAItemNFEntrada) {
		PercentualMVAItemNFEntrada = percentualMVAItemNFEntrada;
	}

	/**
	 * @return the baseIcmsStItemNFEntrada
	 */
	public BigDecimal getBaseIcmsStItemNFEntrada() {
		return BaseIcmsStItemNFEntrada;
	}

	/**
	 * @param baseIcmsStItemNFEntrada the baseIcmsStItemNFEntrada to set
	 */
	public void setBaseIcmsStItemNFEntrada(BigDecimal baseIcmsStItemNFEntrada) {
		BaseIcmsStItemNFEntrada = baseIcmsStItemNFEntrada;
	}

	/**
	 * @return the valorIcmsSTItemNFEntrada
	 */
	public BigDecimal getValorIcmsSTItemNFEntrada() {
		return ValorIcmsSTItemNFEntrada;
	}

	/**
	 * @param valorIcmsSTItemNFEntrada the valorIcmsSTItemNFEntrada to set
	 */
	public void setValorIcmsSTItemNFEntrada(BigDecimal valorIcmsSTItemNFEntrada) {
		ValorIcmsSTItemNFEntrada = valorIcmsSTItemNFEntrada;
	}

	/**
	 * @return the cstPisItemNFEntrada
	 */
	public String getCstPisItemNFEntrada() {
		return CstPisItemNFEntrada;
	}

	/**
	 * @param cstPisItemNFEntrada the cstPisItemNFEntrada to set
	 */
	public void setCstPisItemNFEntrada(String cstPisItemNFEntrada) {
		CstPisItemNFEntrada = cstPisItemNFEntrada;
	}

	/**
	 * @return the aliquotaPisItemNFEntrada
	 */
	public BigDecimal getAliquotaPisItemNFEntrada() {
		return AliquotaPisItemNFEntrada;
	}

	/**
	 * @param aliquotaPisItemNFEntrada the aliquotaPisItemNFEntrada to set
	 */
	public void setAliquotaPisItemNFEntrada(BigDecimal aliquotaPisItemNFEntrada) {
		AliquotaPisItemNFEntrada = aliquotaPisItemNFEntrada;
	}

	/**
	 * @return the valorPisItemNFEntrada
	 */
	public BigDecimal getValorPisItemNFEntrada() {
		return ValorPisItemNFEntrada;
	}

	/**
	 * @param valorPisItemNFEntrada the valorPisItemNFEntrada to set
	 */
	public void setValorPisItemNFEntrada(BigDecimal valorPisItemNFEntrada) {
		ValorPisItemNFEntrada = valorPisItemNFEntrada;
	}

	/**
	 * @return the cstCofinsItemNFEntrada
	 */
	public String getCstCofinsItemNFEntrada() {
		return CstCofinsItemNFEntrada;
	}

	/**
	 * @param cstCofinsItemNFEntrada the cstCofinsItemNFEntrada to set
	 */
	public void setCstCofinsItemNFEntrada(String cstCofinsItemNFEntrada) {
		CstCofinsItemNFEntrada = cstCofinsItemNFEntrada;
	}

	/**
	 * @return the aliquotaCofinsItemNFEntrada
	 */
	public BigDecimal getAliquotaCofinsItemNFEntrada() {
		return AliquotaCofinsItemNFEntrada;
	}

	/**
	 * @param aliquotaCofinsItemNFEntrada the aliquotaCofinsItemNFEntrada to set
	 */
	public void setAliquotaCofinsItemNFEntrada(BigDecimal aliquotaCofinsItemNFEntrada) {
		AliquotaCofinsItemNFEntrada = aliquotaCofinsItemNFEntrada;
	}

	/**
	 * @return the valorCofinsItemNFEntrada
	 */
	public BigDecimal getValorCofinsItemNFEntrada() {
		return ValorCofinsItemNFEntrada;
	}

	/**
	 * @param valorCofinsItemNFEntrada the valorCofinsItemNFEntrada to set
	 */
	public void setValorCofinsItemNFEntrada(BigDecimal valorCofinsItemNFEntrada) {
		ValorCofinsItemNFEntrada = valorCofinsItemNFEntrada;
	}

	/**
	 * @return the cstIpiItemNFEntrada
	 */
	public String getCstIpiItemNFEntrada() {
		return CstIpiItemNFEntrada;
	}

	/**
	 * @param cstIpiItemNFEntrada the cstIpiItemNFEntrada to set
	 */
	public void setCstIpiItemNFEntrada(String cstIpiItemNFEntrada) {
		CstIpiItemNFEntrada = cstIpiItemNFEntrada;
	}

	/**
	 * @return the aliquotaIpiItemNFEntrada
	 */
	public BigDecimal getAliquotaIpiItemNFEntrada() {
		return AliquotaIpiItemNFEntrada;
	}

	/**
	 * @param aliquotaIpiItemNFEntrada the aliquotaIpiItemNFEntrada to set
	 */
	public void setAliquotaIpiItemNFEntrada(BigDecimal aliquotaIpiItemNFEntrada) {
		AliquotaIpiItemNFEntrada = aliquotaIpiItemNFEntrada;
	}

	/**
	 * @return the valorIpiItemNFEntrada
	 */
	public BigDecimal getValorIpiItemNFEntrada() {
		return ValorIpiItemNFEntrada;
	}

	/**
	 * @param valorIpiItemNFEntrada the valorIpiItemNFEntrada to set
	 */
	public void setValorIpiItemNFEntrada(BigDecimal valorIpiItemNFEntrada) {
		ValorIpiItemNFEntrada = valorIpiItemNFEntrada;
	}

	public void setValorTotalLiquidoItemNFEntrada(
			BigDecimal valorTotalLiquidoItemNFEntrada) {
		ValorTotalLiquidoItemNFEntrada = valorTotalLiquidoItemNFEntrada;
	}

	public BigDecimal getValorTotalLiquidoItemNFEntrada() {
		return ValorTotalLiquidoItemNFEntrada;
	}

	public void setCodigoEmpresaEmitenteNFEntrada(
			Integer codigoEmpresaEmitenteNFEntrada) {
		CodigoEmpresaEmitenteNFEntrada = codigoEmpresaEmitenteNFEntrada;
	}

	public Integer getCodigoEmpresaEmitenteNFEntrada() {
		return CodigoEmpresaEmitenteNFEntrada;
	}

	public void setCodigoLojaEmitenteNFEntrada(
			Integer codigoLojaEmitenteNFEntrada) {
		CodigoLojaEmitenteNFEntrada = codigoLojaEmitenteNFEntrada;
	}

	public Integer getCodigoLojaEmitenteNFEntrada() {
		return CodigoLojaEmitenteNFEntrada;
	}

	public void setCodigoPessoaEmitenteNFEntrada(
			Integer codigoPessoaEmitenteNFEntrada) {
		CodigoPessoaEmitenteNFEntrada = codigoPessoaEmitenteNFEntrada;
	}

	public Integer getCodigoPessoaEmitenteNFEntrada() {
		return CodigoPessoaEmitenteNFEntrada;
	}

	public void setCodigoEnderecoPessoaEmitenteNFEntrada(
			Integer codigoEnderecoPessoaEmitenteNFEntrada) {
		CodigoEnderecoPessoaEmitenteNFEntrada = codigoEnderecoPessoaEmitenteNFEntrada;
	}

	public Integer getCodigoEnderecoPessoaEmitenteNFEntrada() {
		return CodigoEnderecoPessoaEmitenteNFEntrada;
	}

	public Date getDataEmissaoNFEntrada() {
		return DataEmissaoNFEntrada;
	}

	public void setDataEmissaoNFEntrada(Date dataEmissaoNFEntrada) {
		DataEmissaoNFEntrada = dataEmissaoNFEntrada;
	}
}
