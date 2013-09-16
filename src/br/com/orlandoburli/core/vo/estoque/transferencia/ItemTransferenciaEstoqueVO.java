package br.com.orlandoburli.core.vo.estoque.transferencia;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ItemTransferenciaEstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoTransferenciaEstoque;
	@Key @AutoIncrement
	private Integer CodigoItemTransferenciaEstoque;
	
	private BigDecimal QuantidadeItemTransferenciaEstoque;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	@Formula(getFormula="(SELECT b.DescricaoProduto FROM [schema].Produto b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresaProduto " +
			"                AND b.CodigoLoja    = a.CodigoLojaProduto" +
			"                AND b.CodigoProduto = a.CodigoProduto)")
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

	public Integer getCodigoTransferenciaEstoque() {
		return CodigoTransferenciaEstoque;
	}

	public void setCodigoTransferenciaEstoque(Integer codigoTransferenciaEstoque) {
		CodigoTransferenciaEstoque = codigoTransferenciaEstoque;
	}

	public Integer getCodigoItemTransferenciaEstoque() {
		return CodigoItemTransferenciaEstoque;
	}

	public void setCodigoItemTransferenciaEstoque(Integer codigoItemTransferenciaEstoque) {
		CodigoItemTransferenciaEstoque = codigoItemTransferenciaEstoque;
	}

	public BigDecimal getQuantidadeItemTransferenciaEstoque() {
		return QuantidadeItemTransferenciaEstoque;
	}

	public void setQuantidadeItemTransferenciaEstoque(BigDecimal quantidadeItemTransferenciaEstoque) {
		QuantidadeItemTransferenciaEstoque = quantidadeItemTransferenciaEstoque;
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