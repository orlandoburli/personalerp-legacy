package br.com.orlandoburli.core.vo.estoque.entradaestoque;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class ItemEntradaEstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoEntradaEstoque;
	@Key
	@AutoIncrement
	private Integer CodigoItemEntradaEstoque;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private BigDecimal QuantidadeItemEntradaEstoque;
	
	private Integer CodigoEmpresaEstoque;
	private Integer CodigoLojaEstoque;
	private Integer CodigoMovimentacaoEstoque;
	
	@Join(entityName="Produto", foreignColumnName="DescricaoProduto", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoProduto"}, 
			localKeys={"CodigoEmpresaProduto", "CodigoLojaProduto", "CodigoProduto"})
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

	public Integer getCodigoEntradaEstoque() {
		return CodigoEntradaEstoque;
	}

	public void setCodigoEntradaEstoque(Integer codigoEntradaEstoque) {
		CodigoEntradaEstoque = codigoEntradaEstoque;
	}

	public Integer getCodigoItemEntradaEstoque() {
		return CodigoItemEntradaEstoque;
	}

	public void setCodigoItemEntradaEstoque(Integer codigoItemEntradaEstoque) {
		CodigoItemEntradaEstoque = codigoItemEntradaEstoque;
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

	public BigDecimal getQuantidadeItemEntradaEstoque() {
		return QuantidadeItemEntradaEstoque;
	}

	public void setQuantidadeItemEntradaEstoque(BigDecimal quantidadeItemEntradaEstoque) {
		QuantidadeItemEntradaEstoque = quantidadeItemEntradaEstoque;
	}

	public Integer getCodigoEmpresaEstoque() {
		return CodigoEmpresaEstoque;
	}

	public void setCodigoEmpresaEstoque(Integer codigoEmpresaEstoque) {
		CodigoEmpresaEstoque = codigoEmpresaEstoque;
	}

	public Integer getCodigoLojaEstoque() {
		return CodigoLojaEstoque;
	}

	public void setCodigoLojaEstoque(Integer codigoLojaEstoque) {
		CodigoLojaEstoque = codigoLojaEstoque;
	}

	public Integer getCodigoMovimentacaoEstoque() {
		return CodigoMovimentacaoEstoque;
	}

	public void setCodigoMovimentacaoEstoque(Integer codigoMovimentacaoEstoque) {
		CodigoMovimentacaoEstoque = codigoMovimentacaoEstoque;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		DescricaoProduto = descricaoProduto;
	}

	public String getDescricaoProduto() {
		return DescricaoProduto;
	}
}