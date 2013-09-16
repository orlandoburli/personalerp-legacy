package br.com.orlandoburli.core.vo.bi.vendas;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ProdutoVendaDiariaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Date DataVenda;
	@Key
	private Integer CodigoEmpresaProduto;
	@Key
	private Integer CodigoLojaProduto;
	@Key
	private Integer CodigoProduto;
	
	@Precision(decimals=2)
	private BigDecimal QuantidadeProduto;
	@Precision(decimals=2)
	private BigDecimal ValorProduto;
	
	@Formula(getFormula="(SELECT b.DescricaoProduto FROM [schema].Produto b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresaProduto " +
			"                AND b.CodigoLoja    = a.CodigoLojaProduto" +
			"                AND b.CodigoProduto = a.CodigoProduto)")
	private String NomeProduto;
	
	@Precision(decimals=2)
	private BigDecimal QuantidadeGavetaProduto;
	@Precision(decimals=2)
	private BigDecimal QuantidadeDisplayProduto;
	
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

	public Date getDataVenda() {
		return DataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		DataVenda = dataVenda;
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

	public BigDecimal getQuantidadeProduto() {
		return QuantidadeProduto;
	}

	public void setQuantidadeProduto(BigDecimal quantidadeProduto) {
		QuantidadeProduto = quantidadeProduto;
	}

	public BigDecimal getValorProduto() {
		return ValorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		ValorProduto = valorProduto;
	}

	public String getNomeProduto() {
		return NomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}

	public BigDecimal getQuantidadeGavetaProduto() {
		return QuantidadeGavetaProduto;
	}

	public void setQuantidadeGavetaProduto(BigDecimal quantidadeGavetaProduto) {
		QuantidadeGavetaProduto = quantidadeGavetaProduto;
	}

	public BigDecimal getQuantidadeDisplayProduto() {
		return QuantidadeDisplayProduto;
	}

	public void setQuantidadeDisplayProduto(BigDecimal quantidadeDisplayProduto) {
		QuantidadeDisplayProduto = quantidadeDisplayProduto;
	}
}