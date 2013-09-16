package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeirabeneficiada;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class ItemRomaneioBeneficiadaSerradaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	
	@Ignore
	public static final String STATUS_NAOPROCESSADO = "N";
	@Ignore
	public static final String STATUS_PROCESSADO = "P";
	@Ignore
	public static final String STATUS_ESTORNADO = "E";
	
	private boolean isNew;

	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoRomaneio;
	@Key @AutoIncrement
	private Integer CodigoItemRomaneio;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private Integer QuantidadeItemRomaneio;
	private Timestamp DataHoraLancamentoItemRomaneio;
	private String StatusItemRomaneio;
	
	/// Os campos abaixo mais os campos do produto (CodigoEmpresaProduto, CodigoLojaProduto e CodigoProduto)
	/// fazem referência a Movimentacao do Estoque
	private Integer CodigoMovimentacaoEstoque;
	private Integer CodigoEmpresaEstoque;
	private Integer CodigoLojaEstoque;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.DescricaoProduto FROM [schema].Produto b WHERE a.CodigoEmpresaProduto = b.CodigoEmpresa AND a.CodigoLojaProduto = b.CodigoLoja AND a.CodigoProduto = b.CodigoProduto)")
    private String NomeProdutoMadeiraSerrada;
    
    @Formula(getFormula="(SELECT b.CodigoEssencia FROM [schema].Produto b WHERE a.CodigoEmpresaProduto = b.CodigoEmpresa AND a.CodigoLojaProduto = b.CodigoLoja AND a.CodigoProduto = b.CodigoProduto)")
    private Integer CodigoEssencia;
    
    @Formula(getFormula="(SELECT ComprimentoProduto FROM [schema].Produto b WHERE a.CodigoEmpresaProduto = b.CodigoEmpresa AND a.CodigoLojaProduto = b.CodigoLoja AND a.CodigoProduto = b.CodigoProduto)")
    private BigDecimal ComprimentoProduto;
    @Formula(getFormula="(SELECT LarguraProduto FROM [schema].Produto b WHERE a.CodigoEmpresaProduto = b.CodigoEmpresa AND a.CodigoLojaProduto = b.CodigoLoja AND a.CodigoProduto = b.CodigoProduto)")
	private BigDecimal LarguraProduto;
    @Formula(getFormula="(SELECT AlturaProduto FROM [schema].Produto b WHERE a.CodigoEmpresaProduto = b.CodigoEmpresa AND a.CodigoLojaProduto = b.CodigoLoja AND a.CodigoProduto = b.CodigoProduto)")
	private BigDecimal AlturaProduto;
    
    @Ignore
    private BigDecimal VolumeTotalItemRomaneio;

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

	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	public Integer getCodigoItemRomaneio() {
		return CodigoItemRomaneio;
	}

	public void setCodigoItemRomaneio(Integer codigoItemRomaneio) {
		CodigoItemRomaneio = codigoItemRomaneio;
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

	public Integer getQuantidadeItemRomaneio() {
		return QuantidadeItemRomaneio;
	}

	public void setQuantidadeItemRomaneio(Integer quantidadeItemRomaneio) {
		QuantidadeItemRomaneio = quantidadeItemRomaneio;
	}

	public Timestamp getDataHoraLancamentoItemRomaneio() {
		return DataHoraLancamentoItemRomaneio;
	}

	public void setDataHoraLancamentoItemRomaneio(
			Timestamp dataHoraLancamentoItemRomaneio) {
		DataHoraLancamentoItemRomaneio = dataHoraLancamentoItemRomaneio;
	}

	public String getStatusItemRomaneio() {
		return StatusItemRomaneio;
	}

	public void setStatusItemRomaneio(String statusItemRomaneio) {
		StatusItemRomaneio = statusItemRomaneio;
	}

	public void setNomeProdutoMadeiraSerrada(String nomeProdutoMadeiraSerrada) {
		NomeProdutoMadeiraSerrada = nomeProdutoMadeiraSerrada;
	}

	public String getNomeProdutoMadeiraSerrada() {
		return NomeProdutoMadeiraSerrada;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setComprimentoProduto(BigDecimal comprimentoProduto) {
		ComprimentoProduto = comprimentoProduto;
	}

	public BigDecimal getComprimentoProduto() {
		return ComprimentoProduto;
	}

	public void setLarguraProduto(BigDecimal larguraProduto) {
		LarguraProduto = larguraProduto;
	}

	public BigDecimal getLarguraProduto() {
		return LarguraProduto;
	}

	public void setAlturaProduto(BigDecimal alturaProduto) {
		AlturaProduto = alturaProduto;
	}

	public BigDecimal getAlturaProduto() {
		return AlturaProduto;
	}

	public void setVolumeTotalItemRomaneio(BigDecimal volumeTotalItemRomaneio) {
		VolumeTotalItemRomaneio = volumeTotalItemRomaneio;
	}

	public BigDecimal getVolumeTotalItemRomaneio() {
		return VolumeTotalItemRomaneio;
	}

	public void setCodigoMovimentacaoEstoque(Integer codigoMovimentacaoEstoque) {
		CodigoMovimentacaoEstoque = codigoMovimentacaoEstoque;
	}

	public Integer getCodigoMovimentacaoEstoque() {
		return CodigoMovimentacaoEstoque;
	}

	public void setCodigoEmpresaEstoque(Integer codigoEmpresaEstoque) {
		CodigoEmpresaEstoque = codigoEmpresaEstoque;
	}

	public Integer getCodigoEmpresaEstoque() {
		return CodigoEmpresaEstoque;
	}

	public void setCodigoLojaEstoque(Integer codigoLojaEstoque) {
		CodigoLojaEstoque = codigoLojaEstoque;
	}

	public Integer getCodigoLojaEstoque() {
		return CodigoLojaEstoque;
	}
}
