package br.com.orlandoburli.core.extras.sped.registros.blococ;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatDateSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatNumberSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC405 extends RegistroSped {

	private Date dataDocumento;
	private Integer cro;
	private Integer crz;
	private Integer numeroCooFinal;
	private BigDecimal valorGrandeTotal;
	private BigDecimal valorVendaBruta;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C405";
	}

	@CampoSped(name = "DT_DOC", order = 2)
	@FormatDateSped("ddMMyyyy")
	public Date getDataDocumento() {
		return dataDocumento;
	}

	@CampoSped(name = "CRO", order = 3)
	public Integer getCro() {
		return cro;
	}

	@CampoSped(name = "CRZ", order = 4)
	public Integer getCrz() {
		return crz;
	}

	@CampoSped(name = "NUM_COO_FIN", order = 5)
	public Integer getNumeroCooFinal() {
		return numeroCooFinal;
	}

	@CampoSped(name = "GT_FIN", order = 6)
	@FormatNumberSped(2)
	public BigDecimal getValorGrandeTotal() {
		return valorGrandeTotal;
	}

	@CampoSped(name = "VL_BRT", order = 7)
	@FormatNumberSped(2)
	public BigDecimal getValorVendaBruta() {
		return valorVendaBruta;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public void setCro(Integer cro) {
		this.cro = cro;
	}

	public void setCrz(Integer crz) {
		this.crz = crz;
	}

	public void setNumeroCooFinal(Integer numeroCooFinal) {
		this.numeroCooFinal = numeroCooFinal;
	}

	public void setValorGrandeTotal(BigDecimal valorGrandeTotal) {
		this.valorGrandeTotal = valorGrandeTotal;
	}

	public void setValorVendaBruta(BigDecimal valorVendaBruta) {
		this.valorVendaBruta = valorVendaBruta;
	}

}
