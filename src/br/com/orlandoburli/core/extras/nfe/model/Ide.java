package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class Ide implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * C�digo da UF. Usar tabela do IBGE.
	 */
	private String cUF;
	/**
	 * C�digo num�rico que comp�e a Chave de Acesso. 
	 * N�mero aleat�rio gerado pelo emitente para cada NF-e. 
	 * (tamanho reduzido para 8 d�gitos v2.0)
	 */
	private String cNF;
	/**
	 * Natureza da opera��o.
	 * de 1 a 60 caracteres.
	 */
	private String natOp;
	/** Indicador da forma de pagamento:
	 *   0 � pagamento � vista;
	 *   1 � pagamento � prazo;
	 *   2 � outros.
	 */
	private String indPag;
	/**
	 * C�digo do modelo do Documento Fiscal. 
	 * Utilizar 55 (unico valor v�lido) para identifica��o da NF-e, 
	 * emitida em substitui��o ao modelo 1 e 1A.
	 */
	private String mod;
	
	/**
	 * S�rie do Documento Fiscal 
	 * S�rie normal 0-889 
	 * Avulsa Fisco 890-899 
	 * SCAN 900-999
	 */
	private String serie;
	/**
	 * N�mero do Documento Fiscal
	 * Maximo 9 digitos.
	 */
	private String nNF;
	/**
	 * Data de emiss�o do Documento Fiscal (AAAA-MM-DD)
	 */
	private String dEmi;
	/**
	 * Data de sa�da ou de entrada da mercadoria / produto (AAAA-MM-DD)
	 */
	private String dSaiEnt;
	/**
	 * Hora de sa�da ou de entrada da mercadoria / produto (HH:MM:SS) (v2.0)
	 */
	private String hSaiEnt;
	/**
	 * Tipo do Documento Fiscal (0 - entrada; 1 - sa�da)
	 */
	private String tpNF;
	/**
	 * C�digo do Munic�pio de Ocorr�ncia do Fato Gerador (utilizar a tabela do IBGE)
	 */
	private String cMunFG;
	/**
	 * Grupo de informa��es da NF referenciada
	 * TODO O q � isso??
	 */
	//private String NFref;
	/**
	 * Formato de impress�o do DANFE (1 - Retrato; 2 - Paisagem)
	 */
	private String tpImp;
	/**
	 * Forma de emiss�o da NF-e 
	 * 1 - Normal; 
	 * 2 - Conting�ncia FS 
	 * 3 - Conting�ncia SCAN 
	 * 4 - Conting�ncia DPEC 
	 * 5 - Conting�ncia FSDA
	 */
	private String tpEmis;
	/**
	 * Digito Verificador da Chave de Acesso da NF-e.
	 * Um unico digito, de 0 a 9.
	 */
	private String cDV;
	/**
	 * Identifica��o do Ambiente: 
	 * 1 - Produ��o 
	 * 2 - Homologa��o
	 */
	private String tpAmb;
	/**
	 * Finalidade da emiss�o da NF-e:
	 * 1 - NFe normal
	 * 2 - NFe complementar
	 * 3 - NFe de ajuste
	 */
	private String finNFe;
	/**
	 * Processo de emiss�o utilizado com a seguinte codifica��o:
	 * 0 - emiss�o de NF-e com aplicativo do contribuinte;
	 * 1 - emiss�o de NF-e avulsa pelo Fisco;
	 * 2 - emiss�o de NF-e avulsa, pelo contribuinte com seu certificado digital, atrav�s do site do Fisco;
	 * 3 - emiss�o de NF-e pelo contribuinte com aplicativo fornecido pelo Fisco.
	 */
	private String procEmi;
	/**
	 * Vers�o do aplicativo utilizado no processo de emiss�o.
	 * De 1 a 20 Caracteres.
	 */
	private String verProc;
	
	public String getcUF() {
		return cUF;
	}
	/**
	 * C�digo da UF. Usar tabela do IBGE.
	 */
	public void setcUF(String cUF) {
		this.cUF = cUF;
	}
	public String getcNF() {
		return cNF;
	}
	/**
	 * C�digo num�rico que comp�e a Chave de Acesso. 
	 * N�mero aleat�rio gerado pelo emitente para cada NF-e. 
	 * (tamanho reduzido para 8 d�gitos v2.0)
	 */
	public void setcNF(String cNF) {
		Integer codigoNf = Integer.valueOf(cNF);
		this.cNF = codigoNf.toString(); //NfeUtils.preencheCom(codigoNf.toString(), "0", 8, 1);
	}
	public String getNatOp() {
		return natOp;
	}
	/**
	 * Natureza da opera��o.
	 * de 1 a 60 caracteres.
	 */
	public void setNatOp(String natOp) {
		this.natOp = natOp;
	}
	public String getIndPag() {
		return indPag;
	}
	/** Indicador da forma de pagamento:
	 * <ul>
	 * <li>0 � pagamento � vista;</li>
	 * <li>1 � pagamento � prazo;</li>
	 * <li>2 � outros.</li>
	 * </ul>
	 */
	public void setIndPag(String indPag) {
		this.indPag = indPag;
	}
	public String getMod() {
		return mod;
	}
	/**
	 * C�digo do modelo do Documento Fiscal. 
	 * Utilizar 55 (unico valor v�lido) para identifica��o da NF-e, 
	 * emitida em substitui��o ao modelo 1 e 1A.
	 */
	public void setMod(String mod) {
		this.mod = mod;
	}
	public String getSerie() {
		return serie;
	}
	/**
	 * S�rie do Documento Fiscal 
	 * S�rie normal 0-889 
	 * Avulsa Fisco 890-899 
	 * SCAN 900-999
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getnNF() {
		return nNF;
	}
	/**
	 * N�mero do Documento Fiscal<br/>
	 * Sequencial da nota.<br/>
	 * Maximo 9 digitos.<br/>
	 */
	public void setnNF(String nNF) {
		Integer numeroNf = Integer.valueOf(nNF);
		this.nNF = numeroNf.toString(); //NfeUtils.preencheCom(numeroNf.toString(), "0", 9, 1);
	}
	/**
	 * Data de emiss�o do Documento Fiscal (AAAA-MM-DD)
	 */
	public String getdEmi() {
		return dEmi;
	}
	/**
	 * Data de emiss�o do Documento Fiscal (AAAA-MM-DD)
	 */
	public void setdEmi(String dEmi) {
		this.dEmi = dEmi;
	}
	/**
	 * Data de sa�da ou de entrada da mercadoria / produto (AAAA-MM-DD)
	 */
	public String getdSaiEnt() {
		return dSaiEnt;
	}
	/**
	 * Data de sa�da ou de entrada da mercadoria / produto (AAAA-MM-DD)
	 */
	public void setdSaiEnt(String dSaiEnt) {
		this.dSaiEnt = dSaiEnt;
	}
	/**
	 * Hora de sa�da ou de entrada da mercadoria / produto (HH:MM:SS) (v2.0)
	 */
	public String gethSaiEnt() {
		return hSaiEnt;
	}
	/**
	 * Hora de sa�da ou de entrada da mercadoria / produto (HH:MM:SS) (v2.0)
	 */
	public void sethSaiEnt(String hSaiEnt) {
		this.hSaiEnt = hSaiEnt;
	}
	/**
	 * Tipo do Documento Fiscal 
	 * <ul>
	 * <li>0 - Entrada</li> 
	 * <li>1 - Sa�da</li>
	 * </ul>
	 */
	public String getTpNF() {
		return tpNF;
	}
	/**
	 * Tipo do Documento Fiscal 
	 * <ul>
	 * <li>0 - Entrada</li> 
	 * <li>1 - Sa�da</li>
	 * </ul>
	 */
	public void setTpNF(String tpNF) {
		this.tpNF = tpNF;
	}
	public String getcMunFG() {
		return cMunFG;
	}
	/**
	 * C�digo do Munic�pio de Ocorr�ncia do Fato Gerador (utilizar a tabela do IBGE)
	 */
	public void setcMunFG(String cMunFG) {
		this.cMunFG = cMunFG;
	}
	public String getTpImp() {
		return tpImp;
	}
	/**
	 * Formato de impress�o do DANFE 
	 * <ul>
	 * <li>1 - Retrato</li>
	 * <li>2 - Paisagem</li>
	 * </ul>
	 */
	public void setTpImp(String tpImp) {
		this.tpImp = tpImp;
	}
	public String getTpEmis() {
		return tpEmis;
	}
	/**
	 * Forma de emiss�o da NF-e
	 * <ul> 
	 * <li>1 - Normal;</li> 
	 * <li>2 - Conting�ncia FS</li> 
	 * <li>3 - Conting�ncia SCAN</li>
	 * <li>4 - Conting�ncia DPEC</li>
	 * <li>5 - Conting�ncia FSDA</li>
	 * </ul>
	 */
	public void setTpEmis(String tpEmis) {
		this.tpEmis = tpEmis;
	}
	public String getcDV() {
		return cDV;
	}
	/**
	 * Digito Verificador da Chave de Acesso da NF-e.
	 * Um unico digito, de 0 a 9.
	 */
	public void setcDV(String cDV) {
		this.cDV = cDV;
	}
	public String getTpAmb() {
		return tpAmb;
	}
	/**
	 * Identifica��o do Ambiente:
	 * <ul> 
	 * <li>1 - Produ��o</li> 
	 * <li>2 - Homologa��o</li>
	 * </ul>
	 */
	public void setTpAmb(String tpAmb) {
		this.tpAmb = tpAmb;
	}
	public String getFinNFe() {
		return finNFe;
	}
	/**
	 * Finalidade da emiss�o da NF-e:
	 * <ul>
	 * <li>1 - NFe normal</li>
	 * <li>2 - NFe complementar</li>
	 * <li>3 - NFe de ajuste</li>
	 * </ul>
	 */
	public void setFinNFe(String finNFe) {
		this.finNFe = finNFe;
	}
	public String getProcEmi() {
		return procEmi;
	}
	/**
	 * Processo de emiss�o utilizado com a seguinte codifica��o:
	 * <ul>
	 * <li>0 - emiss�o de NF-e com aplicativo do contribuinte;</li>
	 * <li>1 - emiss�o de NF-e avulsa pelo Fisco;</li>
	 * <li>2 - emiss�o de NF-e avulsa, pelo contribuinte com seu certificado digital, atrav�s do site do Fisco;</li>
	 * <li>3 - emiss�o de NF-e pelo contribuinte com aplicativo fornecido pelo Fisco.</li>
	 * </ul>
	 */
	public void setProcEmi(String procEmi) {
		this.procEmi = procEmi;
	}
	public String getVerProc() {
		return verProc;
	}
	/**
	 * Vers�o do aplicativo utilizado no processo de emiss�o.
	 * De 1 a 20 Caracteres.
	 */
	public void setVerProc(String verProc) {
		this.verProc = verProc;
	}
}
