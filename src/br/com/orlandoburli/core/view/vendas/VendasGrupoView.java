package br.com.orlandoburli.core.view.vendas;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.view.IView;

public class VendasGrupoView implements IView {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private String NomeLoja;
	private String RazaoSocialEmpresa;
	private Date DataVenda;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	private String NomeVendedor;
	
	private Integer CodigoGrupo;
	private String NomeGrupo;
	
	private BigDecimal TotalPecas;
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

	public Date getDataVenda() {
		return DataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		DataVenda = dataVenda;
	}

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

	public String getNomeVendedor() {
		return NomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		NomeVendedor = nomeVendedor;
	}

	public String getNomeGrupo() {
		return NomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		NomeGrupo = nomeGrupo;
	}

	public BigDecimal getTotalPecas() {
		return TotalPecas;
	}

	public void setTotalPecas(BigDecimal totalPecas) {
		TotalPecas = totalPecas;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		CodigoGrupo = codigoGrupo;
	}

	public Integer getCodigoGrupo() {
		return CodigoGrupo;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		ValorTotal = valorTotal;
	}

	public BigDecimal getValorTotal() {
		return ValorTotal;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}

	public String getNomeLoja() {
		return NomeLoja;
	}

	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		RazaoSocialEmpresa = razaoSocialEmpresa;
	}

	public String getRazaoSocialEmpresa() {
		return RazaoSocialEmpresa;
	}
}
