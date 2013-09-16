package br.com.orlandoburli.core.vo.estoque.troca;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class TrocaMercadoriaEntradaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoTroca;
	@Key @AutoIncrement
	private Integer CodigoTrocaEntrada;
	
	@Precision(decimals=2)
	private BigDecimal QuantidadeTrocaEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTrocaEntrada;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	@Join(entityName="Produto", foreignColumnName="DescricaoProduto", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoProduto"}, 
			localKeys={"CodigoEmpresaProduto", "CodigoLojaProduto", "CodigoProduto"})
	private String NomeProduto;
	
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

	public Integer getCodigoTroca() {
		return CodigoTroca;
	}

	public void setCodigoTroca(Integer codigoTroca) {
		CodigoTroca = codigoTroca;
	}

	public Integer getCodigoTrocaEntrada() {
		return CodigoTrocaEntrada;
	}

	public void setCodigoTrocaEntrada(Integer codigoTrocaEntrada) {
		CodigoTrocaEntrada = codigoTrocaEntrada;
	}

	public BigDecimal getQuantidadeTrocaEntrada() {
		return QuantidadeTrocaEntrada;
	}

	public void setQuantidadeTrocaEntrada(BigDecimal quantidadeTrocaEntrada) {
		QuantidadeTrocaEntrada = quantidadeTrocaEntrada;
	}

	public BigDecimal getValorTrocaEntrada() {
		return ValorTrocaEntrada;
	}

	public void setValorTrocaEntrada(BigDecimal valorTrocaEntrada) {
		ValorTrocaEntrada = valorTrocaEntrada;
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

	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}

	public String getNomeProduto() {
		return NomeProduto;
	}
}