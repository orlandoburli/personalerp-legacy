package br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemRomaneioAbateVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key 
	private Integer CodigoRomaneio;
	@Key @AutoIncrement
	private Integer CodigoItemRomaneio;
	
	@Precision(decimals=4)
	private BigDecimal PesoBanda1ItemRomaneio;
	@Precision(decimals=4)
	private BigDecimal PesoBanda2ItemRomaneio;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private Integer CodigoEmpresaCurral;
	private Integer CodigoLojaCurral;
	private Integer CodigoCurral;
	
	@Precision(decimals=4)
	private BigDecimal ValorKgItemRomaneio;
	
	@Formula(getFormula="((a.ValorKgItemRomaneio * PesoBanda1ItemRomaneio / 15) + (a.ValorKgItemRomaneio * PesoBanda2ItemRomaneio / 15))")
	private BigDecimal ValorTotalItemRomaneio;
	
	@Formula(getFormula="(SELECT b.DescricaoProduto FROM [schema].Produto b " +
						"  WHERE a.CodigoEmpresaProduto = b.CodigoEmpresa" +
						"    AND a.CodigoLojaProduto = b.CodigoLoja" +
						"    AND a.CodigoProduto = b.CodigoProduto)")
	private String NomeProduto;
	
	@Formula(getFormula="(SELECT b.DescricaoCurral FROM [schema].Curral b" +
						"  WHERE b.CodigoEmpresa = a.CodigoEmpresaCurral" +
						"    AND b.CodigoLoja = a.CodigoLojaCurral" +
						"    AND b.CodigoCurral = a.CodigoCurral)")
	private String DescricaoCurral;
	
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
	 * @return the codigoRomaneio
	 */
	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	/**
	 * @param codigoRomaneio the codigoRomaneio to set
	 */
	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	/**
	 * @return the codigoItemRomaneio
	 */
	public Integer getCodigoItemRomaneio() {
		return CodigoItemRomaneio;
	}

	/**
	 * @param codigoItemRomaneio the codigoItemRomaneio to set
	 */
	public void setCodigoItemRomaneio(Integer codigoItemRomaneio) {
		CodigoItemRomaneio = codigoItemRomaneio;
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
	 * @return the valorKgItemRomaneio
	 */
	public BigDecimal getValorKgItemRomaneio() {
		return ValorKgItemRomaneio;
	}

	/**
	 * @param valorKgItemRomaneio the valorKgItemRomaneio to set
	 */
	public void setValorKgItemRomaneio(BigDecimal valorKgItemRomaneio) {
		ValorKgItemRomaneio = valorKgItemRomaneio;
	}

	public void setValorTotalItemRomaneio(BigDecimal valorTotalItemRomaneio) {
		ValorTotalItemRomaneio = valorTotalItemRomaneio;
	}

	public BigDecimal getValorTotalItemRomaneio() {
		return ValorTotalItemRomaneio;
	}

	public void setPesoBanda1ItemRomaneio(BigDecimal pesoBanda1ItemRomaneio) {
		PesoBanda1ItemRomaneio = pesoBanda1ItemRomaneio;
	}

	public BigDecimal getPesoBanda1ItemRomaneio() {
		return PesoBanda1ItemRomaneio;
	}

	public void setPesoBanda2ItemRomaneio(BigDecimal pesoBanda2ItemRomaneio) {
		PesoBanda2ItemRomaneio = pesoBanda2ItemRomaneio;
	}

	public BigDecimal getPesoBanda2ItemRomaneio() {
		return PesoBanda2ItemRomaneio;
	}

	/**
	 * @return the codigoEmpresaCurral
	 */
	public Integer getCodigoEmpresaCurral() {
		return CodigoEmpresaCurral;
	}

	/**
	 * @param codigoEmpresaCurral the codigoEmpresaCurral to set
	 */
	public void setCodigoEmpresaCurral(Integer codigoEmpresaCurral) {
		CodigoEmpresaCurral = codigoEmpresaCurral;
	}

	/**
	 * @return the codigoLojaCurral
	 */
	public Integer getCodigoLojaCurral() {
		return CodigoLojaCurral;
	}

	/**
	 * @param codigoLojaCurral the codigoLojaCurral to set
	 */
	public void setCodigoLojaCurral(Integer codigoLojaCurral) {
		CodigoLojaCurral = codigoLojaCurral;
	}

	/**
	 * @return the codigoCurral
	 */
	public Integer getCodigoCurral() {
		return CodigoCurral;
	}

	/**
	 * @param codigoCurral the codigoCurral to set
	 */
	public void setCodigoCurral(Integer codigoCurral) {
		CodigoCurral = codigoCurral;
	}

	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}

	public String getNomeProduto() {
		return NomeProduto;
	}

	public void setDescricaoCurral(String descricaoCurral) {
		DescricaoCurral = descricaoCurral;
	}

	public String getDescricaoCurral() {
		return DescricaoCurral;
	}
}