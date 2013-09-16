package br.com.orlandoburli.core.vo.vendas.pedido;

import java.math.BigDecimal;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemPedidoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoPdv;
	@Key
	private Integer CodigoPedido;
	@Key @AutoIncrement
	private Integer CodigoItemPedido;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	@Precision(decimals=4)
	private BigDecimal QuantidadeItemPedido;
	
	@Precision(decimals=4)
	private BigDecimal QuantidadeEstoqueItemPedido;
	
	@Precision(decimals=2)
	private BigDecimal ValorUnitarioItemPedido;
	@Precision(decimals=2)
	private BigDecimal ValorDescontoItemPedido;
	@Precision(decimals=2)
	private BigDecimal ValorTotalItemPedido;
	
	@Formula(getFormula="(SELECT b.DescricaoProduto FROM [schema].Produto b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresaProduto " +
			"                AND b.CodigoLoja    = a.CodigoLojaProduto" +
			"                AND b.CodigoProduto = a.CodigoProduto)")
	private String NomeProduto;
	
	private String FlagComissaoVendedorPedido;
	
	private Integer CodigoEmpresaLinhaProduto;
	private Integer CodigoLojaLinhaProduto;
	private Integer CodigoLinhaProduto;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Override
    public String toString() {
    	return Utils.voToXml(this);
    }

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
	 * @return the codigoPedido
	 */
	public Integer getCodigoPedido() {
		return CodigoPedido;
	}

	/**
	 * @param codigoPedido the codigoPedido to set
	 */
	public void setCodigoPedido(Integer codigoPedido) {
		CodigoPedido = codigoPedido;
	}

	/**
	 * @return the codigoItemPedido
	 */
	public Integer getCodigoItemPedido() {
		return CodigoItemPedido;
	}

	/**
	 * @param codigoItemPedido the codigoItemPedido to set
	 */
	public void setCodigoItemPedido(Integer codigoItemPedido) {
		CodigoItemPedido = codigoItemPedido;
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
	 * @return the quantidadeItemPedido
	 */
	public BigDecimal getQuantidadeItemPedido() {
		return QuantidadeItemPedido;
	}

	/**
	 * @param quantidadeItemPedido the quantidadeItemPedido to set
	 */
	public void setQuantidadeItemPedido(BigDecimal quantidadeItemPedido) {
		QuantidadeItemPedido = quantidadeItemPedido;
	}

	/**
	 * @return the valorUnitarioItemPedido
	 */
	public BigDecimal getValorUnitarioItemPedido() {
		return ValorUnitarioItemPedido;
	}

	/**
	 * @param valorUnitarioItemPedido the valorUnitarioItemPedido to set
	 */
	public void setValorUnitarioItemPedido(BigDecimal valorUnitarioItemPedido) {
		ValorUnitarioItemPedido = valorUnitarioItemPedido;
	}

	/**
	 * @return the valorDescontoItemPedido
	 */
	public BigDecimal getValorDescontoItemPedido() {
		return ValorDescontoItemPedido;
	}

	/**
	 * @param valorDescontoItemPedido the valorDescontoItemPedido to set
	 */
	public void setValorDescontoItemPedido(BigDecimal valorDescontoItemPedido) {
		ValorDescontoItemPedido = valorDescontoItemPedido;
	}

	/**
	 * @return the valorTotalItemPedido
	 */
	public BigDecimal getValorTotalItemPedido() {
		return ValorTotalItemPedido;
	}

	/**
	 * @param valorTotalItemPedido the valorTotalItemPedido to set
	 */
	public void setValorTotalItemPedido(BigDecimal valorTotalItemPedido) {
		ValorTotalItemPedido = valorTotalItemPedido;
	}

	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}

	public String getNomeProduto() {
		return NomeProduto;
	}

	public void setQuantidadeEstoqueItemPedido(
			BigDecimal quantidadeEstoqueItemPedido) {
		QuantidadeEstoqueItemPedido = quantidadeEstoqueItemPedido;
	}

	public BigDecimal getQuantidadeEstoqueItemPedido() {
		return QuantidadeEstoqueItemPedido;
	}

	public void setFlagComissaoVendedorPedido(String flagComissaoVendedorPedido) {
		FlagComissaoVendedorPedido = flagComissaoVendedorPedido;
	}

	public String getFlagComissaoVendedorPedido() {
		return FlagComissaoVendedorPedido;
	}

	public Integer getCodigoEmpresaLinhaProduto() {
		return CodigoEmpresaLinhaProduto;
	}

	public void setCodigoEmpresaLinhaProduto(Integer codigoEmpresaLinhaProduto) {
		CodigoEmpresaLinhaProduto = codigoEmpresaLinhaProduto;
	}

	public Integer getCodigoLojaLinhaProduto() {
		return CodigoLojaLinhaProduto;
	}

	public void setCodigoLojaLinhaProduto(Integer codigoLojaLinhaProduto) {
		CodigoLojaLinhaProduto = codigoLojaLinhaProduto;
	}

	public Integer getCodigoLinhaProduto() {
		return CodigoLinhaProduto;
	}

	public void setCodigoLinhaProduto(Integer codigoLinhaProduto) {
		CodigoLinhaProduto = codigoLinhaProduto;
	}

	public Integer getCodigoPdv() {
		return CodigoPdv;
	}

	public void setCodigoPdv(Integer codigoPdv) {
		CodigoPdv = codigoPdv;
	}
}