package br.com.orlandoburli.core.vo.estoque.levantamento;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class LevantamentoEstoqueItemVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoLevantamentoEstoque;
	@Key
	@AutoIncrement
	private Integer CodigoItemLevantamentoEstoque;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private BigDecimal QuantidadeDigitadaItemLevantamentoEstoque;
	private BigDecimal QuantidadeEncontradaItemLevantamentoEstoque;
	
	private Integer CodigoEmpresaEstoque;
	private Integer CodigoLojaEstoque;
	private Integer CodigoMovimentacaoEstoque;
	
	private String AcaoItemLevantamentoEstoque;
	
	@Join(entityName="Produto", foreignColumnName="DescricaoProduto", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoProduto"}, 
			localKeys={"CodigoEmpresaProduto", "CodigoLojaProduto", "CodigoProduto"})
	private String DescricaoProduto;
	
	private BigDecimal QuantidadeDisplayItemLevantamentoEstoque;
	private BigDecimal QuantidadeGavetaItemLevantamentoEstoque;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    public Object clone() {
    	return this.clone();
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

	public Integer getCodigoLevantamentoEstoque() {
		return CodigoLevantamentoEstoque;
	}

	public void setCodigoLevantamentoEstoque(Integer codigoLevantamentoEstoque) {
		CodigoLevantamentoEstoque = codigoLevantamentoEstoque;
	}

	public Integer getCodigoItemLevantamentoEstoque() {
		return CodigoItemLevantamentoEstoque;
	}

	public void setCodigoItemLevantamentoEstoque(
			Integer codigoItemLevantamentoEstoque) {
		CodigoItemLevantamentoEstoque = codigoItemLevantamentoEstoque;
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

	public BigDecimal getQuantidadeDigitadaItemLevantamentoEstoque() {
		return QuantidadeDigitadaItemLevantamentoEstoque;
	}

	public void setQuantidadeDigitadaItemLevantamentoEstoque(
			BigDecimal quantidadeDigitadaItemLevantamentoEstoque) {
		QuantidadeDigitadaItemLevantamentoEstoque = quantidadeDigitadaItemLevantamentoEstoque;
	}

	public BigDecimal getQuantidadeEncontradaItemLevantamentoEstoque() {
		return QuantidadeEncontradaItemLevantamentoEstoque;
	}

	public void setQuantidadeEncontradaItemLevantamentoEstoque(
			BigDecimal quantidadeEncontradaItemLevantamentoEstoque) {
		QuantidadeEncontradaItemLevantamentoEstoque = quantidadeEncontradaItemLevantamentoEstoque;
	}

	public String getAcaoItemLevantamentoEstoque() {
		return AcaoItemLevantamentoEstoque;
	}

	public void setAcaoItemLevantamentoEstoque(String acaoItemLevantamentoEstoque) {
		AcaoItemLevantamentoEstoque = acaoItemLevantamentoEstoque;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		DescricaoProduto = descricaoProduto;
	}

	public String getDescricaoProduto() {
		return DescricaoProduto;
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

	public void setCodigoMovimentacaoEstoque(Integer codigoMovimentacaoEstoque) {
		CodigoMovimentacaoEstoque = codigoMovimentacaoEstoque;
	}

	public Integer getCodigoMovimentacaoEstoque() {
		return CodigoMovimentacaoEstoque;
	}

	public void setQuantidadeDisplayItemLevantamentoEstoque(BigDecimal quantidadeDisplayItemLevantamentoEstoque) {
		QuantidadeDisplayItemLevantamentoEstoque = quantidadeDisplayItemLevantamentoEstoque;
	}

	public BigDecimal getQuantidadeDisplayItemLevantamentoEstoque() {
		return QuantidadeDisplayItemLevantamentoEstoque;
	}

	public void setQuantidadeGavetaItemLevantamentoEstoque(BigDecimal quantidadeGavetaItemLevantamentoEstoque) {
		QuantidadeGavetaItemLevantamentoEstoque = quantidadeGavetaItemLevantamentoEstoque;
	}

	public BigDecimal getQuantidadeGavetaItemLevantamentoEstoque() {
		return QuantidadeGavetaItemLevantamentoEstoque;
	}
}