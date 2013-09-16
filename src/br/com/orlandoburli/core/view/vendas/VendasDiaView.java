package br.com.orlandoburli.core.view.vendas;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.view.IView;

public class VendasDiaView implements IView {

	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Date Data;
	private BigDecimal ValorTotal;
	
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
	public Date getData() {
		return Data;
	}
	public void setData(Date data) {
		Data = data;
	}
	public BigDecimal getValorTotal() {
		return ValorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		ValorTotal = valorTotal;
	}
}
