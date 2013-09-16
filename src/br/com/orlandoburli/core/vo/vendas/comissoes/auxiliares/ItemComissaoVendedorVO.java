package br.com.orlandoburli.core.vo.vendas.comissoes.auxiliares;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.filters.InjectionFilter;

public class ItemComissaoVendedorVO implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private String NomeLoja;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private String NomeVendedor;
	private Date DataVenda;
	
	private Integer CodigoGrupo01;
	private String NomeGrupo01;
	private BigDecimal MetaGrupo01;
	private BigDecimal PremioGrupo01;
	private BigDecimal QuantidadeGrupo01;
	
	private Integer CodigoGrupo02;
	private String NomeGrupo02;
	private BigDecimal MetaGrupo02;
	private BigDecimal PremioGrupo02;
	private BigDecimal QuantidadeGrupo02;
	
	private Integer CodigoGrupo03;
	private String NomeGrupo03;
	private BigDecimal MetaGrupo03;
	private BigDecimal PremioGrupo03;
	private BigDecimal QuantidadeGrupo03;
	
	private Integer CodigoGrupo04;
	private String NomeGrupo04;
	private BigDecimal MetaGrupo04;
	private BigDecimal PremioGrupo04;
	private BigDecimal QuantidadeGrupo04;
	
	private Integer CodigoGrupo05;
	private String NomeGrupo05;
	private BigDecimal MetaGrupo05;
	private BigDecimal PremioGrupo05;
	private BigDecimal QuantidadeGrupo05;
	
	private Integer CodigoGrupo06;
	private String NomeGrupo06;
	private BigDecimal MetaGrupo06;
	private BigDecimal PremioGrupo06;
	private BigDecimal QuantidadeGrupo06;
	
	private Integer CodigoGrupo07;
	private String NomeGrupo07;
	private BigDecimal MetaGrupo07;
	private BigDecimal PremioGrupo07;
	private BigDecimal QuantidadeGrupo07;
	
	private Integer CodigoGrupo08;
	private String NomeGrupo08;
	private BigDecimal MetaGrupo08;
	private BigDecimal PremioGrupo08;
	private BigDecimal QuantidadeGrupo08;
	
	private BigDecimal PercentualComissaoVendas;
	private BigDecimal ValorTotalVendas;
	private BigDecimal ValorPremioVendas;
	
	public void setMetaGrupo(BigDecimal metaGrupo, int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		InjectionFilter.setproperty(this, "MetaGrupo" + sPosicao, metaGrupo);
	}
	
	public void setPremioGrupo(BigDecimal premioGrupo, int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		InjectionFilter.setproperty(this, "PremioGrupo" + sPosicao, premioGrupo);
	}
	
	public void setGrupoVenda(Integer CodigoGrupo, String NomeGrupo, BigDecimal Quantidade, int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		InjectionFilter.setproperty(this, "CodigoGrupo" + sPosicao, CodigoGrupo);
		InjectionFilter.setproperty(this, "NomeGrupo" + sPosicao, NomeGrupo);
		InjectionFilter.setproperty(this, "QuantidadeGrupo" + sPosicao, Quantidade);
	}
	
	public void setGrupoVenda(Integer CodigoGrupo, String NomeGrupo, int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		InjectionFilter.setproperty(this, "CodigoGrupo" + sPosicao, CodigoGrupo);
		InjectionFilter.setproperty(this, "NomeGrupo" + sPosicao, NomeGrupo);
	}
	
	public Integer getCodigoGrupo(int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		return (Integer) DaoUtils.getproperty(this, "CodigoGrupo" + sPosicao);
	}
	
	public String getNomeGrupo(int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		return (String) DaoUtils.getproperty(this, "NomeGrupo" + sPosicao);
	}
	
	public BigDecimal getQuantidadeGrupo(int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		return (BigDecimal) DaoUtils.getproperty(this, "QuantidadeGrupo" + sPosicao);
	}
	
	public void setQuantidadeGrupo(BigDecimal Quantidade, int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		InjectionFilter.setproperty(this, "QuantidadeGrupo" + sPosicao, Quantidade);
	}
	
	public BigDecimal getMetaGrupo(int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		return (BigDecimal) DaoUtils.getproperty(this, "MetaGrupo" + sPosicao);
	}
	
	public BigDecimal getPremioGrupo(int posicao) {
		String sPosicao = Utils.fillString(posicao, "0", 2, 1);
		return (BigDecimal) DaoUtils.getproperty(this, "PremioGrupo" + sPosicao);
	}
	
	public void setAllZeros() {
		for (int i = 0; i < 10; i++) {
			String sPosicao = Utils.fillString(i, "0", 2, 1);
			InjectionFilter.setproperty(this, "QuantidadeGrupo" + sPosicao, BigDecimal.ZERO);
		}
	}

	public void setNomeVendedor(String nomeVendedor) {
		NomeVendedor = nomeVendedor;
	}

	public String getNomeVendedor() {
		return NomeVendedor;
	}

	public void setDataVenda(Date dataVenda) {
		DataVenda = dataVenda;
	}

	public Date getDataVenda() {
		return DataVenda;
	}

	public void setValorTotalVendas(BigDecimal valorTotalVendas) {
		ValorTotalVendas = valorTotalVendas;
	}

	public BigDecimal getValorTotalVendas() {
		return ValorTotalVendas;
	}

	public void setValorPremioVendas(BigDecimal valorPremioVendas) {
		ValorPremioVendas = valorPremioVendas;
	}

	public BigDecimal getValorPremioVendas() {
		return ValorPremioVendas;
	}

	public String getNomeGrupo01() {
		return NomeGrupo01;
	}

	public void setNomeGrupo01(String nomeGrupo01) {
		NomeGrupo01 = nomeGrupo01;
	}

	public BigDecimal getMetaGrupo01() {
		return MetaGrupo01;
	}

	public void setMetaGrupo01(BigDecimal metaGrupo01) {
		MetaGrupo01 = metaGrupo01;
	}

	public BigDecimal getPremioGrupo01() {
		return PremioGrupo01;
	}

	public void setPremioGrupo01(BigDecimal premioGrupo01) {
		PremioGrupo01 = premioGrupo01;
	}

	public String getNomeGrupo02() {
		return NomeGrupo02;
	}

	public void setNomeGrupo02(String nomeGrupo02) {
		NomeGrupo02 = nomeGrupo02;
	}

	public BigDecimal getMetaGrupo02() {
		return MetaGrupo02;
	}

	public void setMetaGrupo02(BigDecimal metaGrupo02) {
		MetaGrupo02 = metaGrupo02;
	}

	public BigDecimal getPremioGrupo02() {
		return PremioGrupo02;
	}

	public void setPremioGrupo02(BigDecimal premioGrupo02) {
		PremioGrupo02 = premioGrupo02;
	}

	public String getNomeGrupo03() {
		return NomeGrupo03;
	}

	public void setNomeGrupo03(String nomeGrupo03) {
		NomeGrupo03 = nomeGrupo03;
	}

	public BigDecimal getMetaGrupo03() {
		return MetaGrupo03;
	}

	public void setMetaGrupo03(BigDecimal metaGrupo03) {
		MetaGrupo03 = metaGrupo03;
	}

	public BigDecimal getPremioGrupo03() {
		return PremioGrupo03;
	}

	public void setPremioGrupo03(BigDecimal premioGrupo03) {
		PremioGrupo03 = premioGrupo03;
	}

	public String getNomeGrupo04() {
		return NomeGrupo04;
	}

	public void setNomeGrupo04(String nomeGrupo04) {
		NomeGrupo04 = nomeGrupo04;
	}

	public BigDecimal getMetaGrupo04() {
		return MetaGrupo04;
	}

	public void setMetaGrupo04(BigDecimal metaGrupo04) {
		MetaGrupo04 = metaGrupo04;
	}

	public BigDecimal getPremioGrupo04() {
		return PremioGrupo04;
	}

	public void setPremioGrupo04(BigDecimal premioGrupo04) {
		PremioGrupo04 = premioGrupo04;
	}

	public String getNomeGrupo05() {
		return NomeGrupo05;
	}

	public void setNomeGrupo05(String nomeGrupo05) {
		NomeGrupo05 = nomeGrupo05;
	}

	public BigDecimal getMetaGrupo05() {
		return MetaGrupo05;
	}

	public void setMetaGrupo05(BigDecimal metaGrupo05) {
		MetaGrupo05 = metaGrupo05;
	}

	public BigDecimal getPremioGrupo05() {
		return PremioGrupo05;
	}

	public void setPremioGrupo05(BigDecimal premioGrupo05) {
		PremioGrupo05 = premioGrupo05;
	}

	public String getNomeGrupo06() {
		return NomeGrupo06;
	}

	public void setNomeGrupo06(String nomeGrupo06) {
		NomeGrupo06 = nomeGrupo06;
	}

	public BigDecimal getMetaGrupo06() {
		return MetaGrupo06;
	}

	public void setMetaGrupo06(BigDecimal metaGrupo06) {
		MetaGrupo06 = metaGrupo06;
	}

	public BigDecimal getPremioGrupo06() {
		return PremioGrupo06;
	}

	public void setPremioGrupo06(BigDecimal premioGrupo06) {
		PremioGrupo06 = premioGrupo06;
	}

	public String getNomeGrupo07() {
		return NomeGrupo07;
	}

	public void setNomeGrupo07(String nomeGrupo07) {
		NomeGrupo07 = nomeGrupo07;
	}

	public BigDecimal getMetaGrupo07() {
		return MetaGrupo07;
	}

	public void setMetaGrupo07(BigDecimal metaGrupo07) {
		MetaGrupo07 = metaGrupo07;
	}

	public BigDecimal getPremioGrupo07() {
		return PremioGrupo07;
	}

	public void setPremioGrupo07(BigDecimal premioGrupo07) {
		PremioGrupo07 = premioGrupo07;
	}

	public String getNomeGrupo08() {
		return NomeGrupo08;
	}

	public void setNomeGrupo08(String nomeGrupo08) {
		NomeGrupo08 = nomeGrupo08;
	}

	public BigDecimal getMetaGrupo08() {
		return MetaGrupo08;
	}

	public void setMetaGrupo08(BigDecimal metaGrupo08) {
		MetaGrupo08 = metaGrupo08;
	}

	public BigDecimal getPremioGrupo08() {
		return PremioGrupo08;
	}

	public void setPremioGrupo08(BigDecimal premioGrupo08) {
		PremioGrupo08 = premioGrupo08;
	}

	public BigDecimal getQuantidadeGrupo01() {
		return QuantidadeGrupo01;
	}

	public void setQuantidadeGrupo01(BigDecimal quantidadeGrupo01) {
		QuantidadeGrupo01 = quantidadeGrupo01;
	}

	public BigDecimal getQuantidadeGrupo02() {
		return QuantidadeGrupo02;
	}

	public void setQuantidadeGrupo02(BigDecimal quantidadeGrupo02) {
		QuantidadeGrupo02 = quantidadeGrupo02;
	}

	public BigDecimal getQuantidadeGrupo03() {
		return QuantidadeGrupo03;
	}

	public void setQuantidadeGrupo03(BigDecimal quantidadeGrupo03) {
		QuantidadeGrupo03 = quantidadeGrupo03;
	}

	public BigDecimal getQuantidadeGrupo04() {
		return QuantidadeGrupo04;
	}

	public void setQuantidadeGrupo04(BigDecimal quantidadeGrupo04) {
		QuantidadeGrupo04 = quantidadeGrupo04;
	}

	public BigDecimal getQuantidadeGrupo05() {
		return QuantidadeGrupo05;
	}

	public void setQuantidadeGrupo05(BigDecimal quantidadeGrupo05) {
		QuantidadeGrupo05 = quantidadeGrupo05;
	}

	public BigDecimal getQuantidadeGrupo06() {
		return QuantidadeGrupo06;
	}

	public void setQuantidadeGrupo06(BigDecimal quantidadeGrupo06) {
		QuantidadeGrupo06 = quantidadeGrupo06;
	}

	public BigDecimal getQuantidadeGrupo07() {
		return QuantidadeGrupo07;
	}

	public void setQuantidadeGrupo07(BigDecimal quantidadeGrupo07) {
		QuantidadeGrupo07 = quantidadeGrupo07;
	}

	public BigDecimal getQuantidadeGrupo08() {
		return QuantidadeGrupo08;
	}

	public void setQuantidadeGrupo08(BigDecimal quantidadeGrupo08) {
		QuantidadeGrupo08 = quantidadeGrupo08;
	}

	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public Integer getCodigoGrupo01() {
		return CodigoGrupo01;
	}

	public void setCodigoGrupo01(Integer codigoGrupo01) {
		CodigoGrupo01 = codigoGrupo01;
	}

	public Integer getCodigoGrupo02() {
		return CodigoGrupo02;
	}

	public void setCodigoGrupo02(Integer codigoGrupo02) {
		CodigoGrupo02 = codigoGrupo02;
	}

	public Integer getCodigoGrupo03() {
		return CodigoGrupo03;
	}

	public void setCodigoGrupo03(Integer codigoGrupo03) {
		CodigoGrupo03 = codigoGrupo03;
	}

	public Integer getCodigoGrupo04() {
		return CodigoGrupo04;
	}

	public void setCodigoGrupo04(Integer codigoGrupo04) {
		CodigoGrupo04 = codigoGrupo04;
	}

	public Integer getCodigoGrupo05() {
		return CodigoGrupo05;
	}

	public void setCodigoGrupo05(Integer codigoGrupo05) {
		CodigoGrupo05 = codigoGrupo05;
	}

	public Integer getCodigoGrupo06() {
		return CodigoGrupo06;
	}

	public void setCodigoGrupo06(Integer codigoGrupo06) {
		CodigoGrupo06 = codigoGrupo06;
	}

	public Integer getCodigoGrupo07() {
		return CodigoGrupo07;
	}

	public void setCodigoGrupo07(Integer codigoGrupo07) {
		CodigoGrupo07 = codigoGrupo07;
	}

	public Integer getCodigoGrupo08() {
		return CodigoGrupo08;
	}

	public void setCodigoGrupo08(Integer codigoGrupo08) {
		CodigoGrupo08 = codigoGrupo08;
	}

	public void setPercentualComissaoVendas(BigDecimal percentualComissaoVendas) {
		PercentualComissaoVendas = percentualComissaoVendas;
	}

	public BigDecimal getPercentualComissaoVendas() {
		return PercentualComissaoVendas;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}

	public String getNomeLoja() {
		return NomeLoja;
	}
}