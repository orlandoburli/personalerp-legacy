package br.com.orlandoburli.core.view.vendas;

import java.math.BigDecimal;

import br.com.orlandoburli.core.view.IView;

public class VendasLojaGrupoView implements IView {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	
	private Integer CodigoGrupo;
	private String NomeGrupo;
	
	private BigDecimal ValorTotalPedido;
	private BigDecimal TotalQuantidadePedido;
	
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
