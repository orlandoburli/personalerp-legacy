package br.com.orlandoburli.core.vo.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class ReciboAvulsoVO implements Serializable, Cloneable {
	
	private static final long serialVersionUID = 1L;
	
	private EmpresaVO Empresa;
	private LojaVO Loja;
	private CidadeVO Cidade;
	
	private String NomeDestinatario;
	private Date DataRecibo;
	@Precision(decimals=2)
	private BigDecimal ValorRecibo;
	private String ValorReciboExtenso;
	private String ReferenteA;
	private String Emitente;
	private String TextoRecibo;
	
	public void setEmpresa(EmpresaVO empresa) {
		Empresa = empresa;
	}
	public EmpresaVO getEmpresa() {
		return Empresa;
	}
	public void setLoja(LojaVO loja) {
		Loja = loja;
	}
	public LojaVO getLoja() {
		return Loja;
	}
	public void setTextoRecibo(String textoRecibo) {
		TextoRecibo = textoRecibo;
	}
	public String getTextoRecibo() {
		return TextoRecibo;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setCidade(CidadeVO cidade) {
		Cidade = cidade;
	}
	public CidadeVO getCidade() {
		return Cidade;
	}
	public void setNomeDestinatario(String nomeDestinatario) {
		NomeDestinatario = nomeDestinatario;
	}
	public String getNomeDestinatario() {
		return NomeDestinatario;
	}
	public void setDataRecibo(Date dataRecibo) {
		DataRecibo = dataRecibo;
	}
	public Date getDataRecibo() {
		return DataRecibo;
	}
	public void setValorRecibo(BigDecimal valorRecibo) {
		ValorRecibo = valorRecibo;
	}
	public BigDecimal getValorRecibo() {
		return ValorRecibo;
	}
	public void setValorReciboExtenso(String valorReciboExtenso) {
		ValorReciboExtenso = valorReciboExtenso;
	}
	public String getValorReciboExtenso() {
		return ValorReciboExtenso;
	}
	public void setReferenteA(String referenteA) {
		ReferenteA = referenteA;
	}
	public String getReferenteA() {
		return ReferenteA;
	}
	public void setEmitente(String emitente) {
		Emitente = emitente;
	}
	public String getEmitente() {
		return Emitente;
	}
}