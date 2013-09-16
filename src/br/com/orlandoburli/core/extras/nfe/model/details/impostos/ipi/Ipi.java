package br.com.orlandoburli.core.extras.nfe.model.details.impostos.ipi;

import java.io.Serializable;

public class Ipi implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Classe de enquadramento do IPI para Cigarros e Bebidas
	 */
	private String clEnq;
	/**
	 * CNPJ do produtor da mercadoria, quando diferente do emitente. Somente para os casos de exportação direta ou indireta.
	 */
	private String CNPJProd;
	/**
	 * Código do selo de controle IPI
	 */
	private String cSelo;
	/**
	 * Quantidade de selo de controle
	 */
	private Integer qSelo;
	/**
	 * Código de Enquadramento Legal do IPI
	 */
	private String cEnq;
	
	private IpiTrib IPITrib;
	
	private IpiNT IPINT;
	
	public static Ipi getIPITribInstance() {
		Ipi ipi = new Ipi();
		ipi.setIPITrib(new IpiTrib());
		return ipi;
	}
	
	public static Ipi getIPINTInstance() {
		Ipi ipi = new Ipi();
		ipi.setIPINT(new IpiNT());
		return ipi;
	}
	
	private Ipi() {
		
	}
	
	public String getClEnq() {
		return clEnq;
	}
	public void setClEnq(String clEnq) {
		this.clEnq = clEnq;
	}
	public String getCNPJProd() {
		return CNPJProd;
	}
	public void setCNPJProd(String cNPJProd) {
		CNPJProd = cNPJProd;
	}
	public String getcSelo() {
		return cSelo;
	}
	public void setcSelo(String cSelo) {
		this.cSelo = cSelo;
	}
	public Integer getqSelo() {
		return qSelo;
	}
	public void setqSelo(Integer qSelo) {
		this.qSelo = qSelo;
	}
	public String getcEnq() {
		return cEnq;
	}
	public void setcEnq(String cEnq) {
		this.cEnq = cEnq;
	}
	public IpiTrib getIPITrib() {
		return IPITrib;
	}
	public void setIPITrib(IpiTrib iPITrib) {
		IPITrib = iPITrib;
	}
	public IpiNT getIPINT() {
		return IPINT;
	}
	public void setIPINT(IpiNT iPINT) {
		IPINT = iPINT;
	}
}
