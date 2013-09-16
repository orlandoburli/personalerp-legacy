package br.com.orlandoburli.core.extras.sped.registros.blococ;

import java.math.BigDecimal;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatNumberSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC410 extends RegistroSped {

	private String codigoTotalizador;
	private BigDecimal valorAcumuladoTotal;
	private Integer numeroTotalizador;
	private String descricaoNumeroTotalizador;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C410";
	}

	@CampoSped(name = "COD_TOT_PAR", order = 2)
	public String getCodigoTotalizador() {
		return codigoTotalizador;
	}

	@CampoSped(name = "VLR_ACUM_TOT", order = 3)
	@FormatNumberSped(2)
	public BigDecimal getValorAcumuladoTotal() {
		return valorAcumuladoTotal;
	}

	@CampoSped(name = "NR_TOT", order = 4)
	public Integer getNumeroTotalizador() {
		return numeroTotalizador;
	}

	@CampoSped(name = "DESCR_NR_TOT", order = 5)
	public String getDescricaoNumeroTotalizador() {
		return descricaoNumeroTotalizador;
	}

	public void setCodigoTotalizador(String codigoTotalizador) {
		this.codigoTotalizador = codigoTotalizador;
	}

	public void setValorAcumuladoTotal(BigDecimal valorAcumuladoTotal) {
		this.valorAcumuladoTotal = valorAcumuladoTotal;
	}

	public void setNumeroTotalizador(Integer numeroTotalizador) {
		this.numeroTotalizador = numeroTotalizador;
	}

	public void setDescricaoNumeroTotalizador(String descricaoNumeroTotalizador) {
		this.descricaoNumeroTotalizador = descricaoNumeroTotalizador;
	}

}
