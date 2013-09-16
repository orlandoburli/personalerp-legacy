package br.com.orlandoburli.core.view.vendas;

import java.math.BigDecimal;

import br.com.orlandoburli.core.view.IView;

public class VendasVendedorGrupoView implements IView {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private Integer CodigoGrupo;
	private String NomeGrupo;
	
	private BigDecimal ValorTotalPedido;
	private BigDecimal TotalQuantidadePedido;	
	
	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}
	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}
	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}
	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}
	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}
	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}
	public Integer getCodigoGrupo() {
		return CodigoGrupo;
	}
	public void setCodigoGrupo(Integer codigoGrupo) {
		CodigoGrupo = codigoGrupo;
	}
	public String getNomeGrupo() {
		return NomeGrupo;
	}
	public void setNomeGrupo(String nomeGrupo) {
		NomeGrupo = nomeGrupo;
	}
	public BigDecimal getValorTotalPedido() {
		return ValorTotalPedido;
	}
	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		ValorTotalPedido = valorTotalPedido;
	}
	public BigDecimal getTotalQuantidadePedido() {
		return TotalQuantidadePedido;
	}
	public void setTotalQuantidadePedido(BigDecimal totalQuantidadePedido) {
		TotalQuantidadePedido = totalQuantidadePedido;
	}
}