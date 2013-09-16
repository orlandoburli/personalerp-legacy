package br.com.orlandoburli.core.vo.estoque;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class SubProdutoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoProduto;
	
	@Key
	private Integer CodigoEmpresaSubProduto;
	@Key
	private Integer CodigoLojaSubProduto;
	@Key
	private Integer CodigoSubProduto;
	
	@Precision(decimals=4)
	private BigDecimal QuantidadeSubProduto;
	
	@Formula(getFormula="(SELECT b.DescricaoProduto " +
			"				FROM [schema].Produto b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresaSubProduto" +
			"                AND b.CodigoLoja = a.CodigoLojaSubProduto" +
			"                AND b.CodigoProduto = a.CodigoSubProduto)")
	private String DescricaoSubProduto;
	
	@Formula(getFormula="(SELECT b.DescricaoProduto " +
			"				FROM [schema].Produto b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresa" +
			"                AND b.CodigoLoja = a.CodigoLoja" +
			"                AND b.CodigoProduto = a.CodigoProduto)")
	private String DescricaoProduto;
	
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
	 * @return the codigoEmpresaSubProduto
	 */
	public Integer getCodigoEmpresaSubProduto() {
		return CodigoEmpresaSubProduto;
	}

	/**
	 * @param codigoEmpresaSubProduto the codigoEmpresaSubProduto to set
	 */
	public void setCodigoEmpresaSubProduto(Integer codigoEmpresaSubProduto) {
		CodigoEmpresaSubProduto = codigoEmpresaSubProduto;
	}

	/**
	 * @return the codigoLojaSubProduto
	 */
	public Integer getCodigoLojaSubProduto() {
		return CodigoLojaSubProduto;
	}

	/**
	 * @param codigoLojaSubProduto the codigoLojaSubProduto to set
	 */
	public void setCodigoLojaSubProduto(Integer codigoLojaSubProduto) {
		CodigoLojaSubProduto = codigoLojaSubProduto;
	}

	/**
	 * @return the codigoSubProduto
	 */
	public Integer getCodigoSubProduto() {
		return CodigoSubProduto;
	}

	/**
	 * @param codigoSubProduto the codigoSubProduto to set
	 */
	public void setCodigoSubProduto(Integer codigoSubProduto) {
		CodigoSubProduto = codigoSubProduto;
	}

	/**
	 * @return the quantidadeSubProduto
	 */
	public BigDecimal getQuantidadeSubProduto() {
		return QuantidadeSubProduto;
	}

	/**
	 * @param quantidadeSubProduto the quantidadeSubProduto to set
	 */
	public void setQuantidadeSubProduto(BigDecimal quantidadeSubProduto) {
		QuantidadeSubProduto = quantidadeSubProduto;
	}

	public void setDescricaoSubProduto(String descricaoSubProduto) {
		DescricaoSubProduto = descricaoSubProduto;
	}

	public String getDescricaoSubProduto() {
		return DescricaoSubProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		DescricaoProduto = descricaoProduto;
	}

	public String getDescricaoProduto() {
		return DescricaoProduto;
	}
}