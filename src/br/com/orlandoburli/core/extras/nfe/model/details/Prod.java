package br.com.orlandoburli.core.extras.nfe.model.details;

import java.io.Serializable;
import java.math.BigDecimal;

public class Prod implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * C�digo do produto ou servi�o. 
	 * Preencher com CFOP caso se trate de itens n�o relacionados 
	 * com mercadorias/produto e que o contribuinte n�o possua 
	 * codifica��o pr�pria. <br/>
	 * Formato "CFOP9999"<br/>
	 * Tamanho de 1 a 60 caracteres.
	 */
	private String cProd;
	/**
	 * GTIN (Global Trade Item Number) do produto,<br/> 
	 * antigo c�digo EAN ou c�digo de barras
	 */
	private String cEAN;
	/**
	 * Descri��o do produto ou servi�o
	 */
	private String xProd;
	/**
	 * C�digo NCM (8 posi��es), ser� permitida a informa��o do g�nero<br/> 
	 * (posi��o do cap�tulo do NCM) quando a opera��o n�o for de <br/>
	 * com�rcio exterior (importa��o/exporta��o) ou o produto n�o seja <br/>
	 * tributado pelo IPI. Em caso de item de servi�o ou item que n�o <br/>
	 * tenham produto (Ex. transfer�ncia de cr�dito, cr�dito do ativo<br/>
	 * imobilizado, etc.), informar o c�digo 00 (zeros) (v2.0)<br/>
	 */
	private String NCM;
	/**
	 * C�digo EX TIPI (3 posi��es)<br/>
	 * TODO <b>O qq � isso???</b>
	 */
	private String EXTIPI;
	/**
	 * C�digo Fiscal de Opera��es e Presta��es
	 */
	private String CFOP;
	/**
	 * Unidade comercial.<br/>
	 * 1 a 6 caracteres.
	 */
	private String uCom;
	/**
	 * Quantidade Comercial  do produto, alterado para aceitar de<br/> 
	 * 0 a 4 casas decimais e 11 inteiros. (v2.0)
	 */
	private BigDecimal qCom;
	/**
	 * Valor unit�rio de comercializa��o  - <br/>
	 * alterado para aceitar 0 a 10 casas decimais e 11 inteiros (v2.0)
	 */
	private BigDecimal vUnCom;
	/**
	 * Valor bruto do produto ou servi�o.<br/>
	 * 11 digitos e 2 casas decimais.
	 */
	private BigDecimal vProd;
	/**
	 * GTIN (Global Trade Item Number) da unidade tribut�vel,<br/>
	 * antigo c�digo EAN ou c�digo de barras
	 */
	private String cEANTrib;
	/**
	 * Unidade Tribut�vel
	 */
	private String uTrib;
	/**
	 * Quantidade Tribut�vel - alterado para aceitar<br/>
	 * de 0 a 4 casas decimais e 11 inteiros (v2.0)
	 */
	private BigDecimal qTrib;
	/**
	 * Valor unit�rio de tributa��o<br/> 
	 * alterado para aceitar 0 a 10 casas decimais e 11 inteiros (v2.0)
	 */
	private BigDecimal vUnTrib;
	/**
	 * Valor Total do Frete
	 */
	private BigDecimal vFrete;
	/**
	 * Valor Total do Seguro
	 */
	private BigDecimal vSeg;
	/**
	 * Valor do Desconto
	 */
	private BigDecimal vDesc;
	/**
	 * Outras despesas acess�rias - (v2.0)
	 */
	private BigDecimal vOutro;
	/**
	 * Este campo dever� ser preenchido com:<br/>
	 * <ul>
	 * <li>0 � o valor do item (vProd) n�o comp�e o valor total da NF-e (vProd)</li>
	 * <li>1 � o valor do item (vProd) comp�e o valor total da NF-e (vProd) (v2.0)</li>
	 * </ul>
	 */
	private String indTot;
	/**
	 * Pedido de compra - Informa��o de interesse do emissor para controle do B2B. (v2.0)
	 */
	private String xPed;
	/**
	 * N�mero do Item do Pedido de Compra<br/>
	 * Identifica��o do n�mero do item do pedido de Compra (v2.0)
	 */
	private String nItemPed;
	
	////////////////////////////////

	/**
	 * C�digo do produto ou servi�o. 
	 * Preencher com CFOP caso se trate de itens n�o relacionados 
	 * com mercadorias/produto e que o contribuinte n�o possua 
	 * codifica��o pr�pria. <br/>
	 * Formato "CFOP9999"<br/>
	 * Tamanho de 1 a 60 caracteres.
	 */
	public void setcProd(String cProd) {
		this.cProd = cProd;
	}
	public String getcProd() {
		return cProd;
	}
	/**
	 * GTIN (Global Trade Item Number) do produto,<br/> 
	 * antigo c�digo EAN ou c�digo de barras
	 */
	public void setcEAN(String cEAN) {
		this.cEAN = cEAN;
	}
	public String getcEAN() {
		return cEAN;
	}
	/**
	 * Descri��o do produto ou servi�o
	 */
	public void setxProd(String xProd) {
		this.xProd = xProd;
	}
	public String getxProd() {
		return xProd;
	}
	/**
	 * C�digo NCM (8 posi��es), ser� permitida a informa��o do g�nero<br/> 
	 * (posi��o do cap�tulo do NCM) quando a opera��o n�o for de <br/>
	 * com�rcio exterior (importa��o/exporta��o) ou o produto n�o seja <br/>
	 * tributado pelo IPI. Em caso de item de servi�o ou item que n�o <br/>
	 * tenham produto (Ex. transfer�ncia de cr�dito, cr�dito do ativo<br/>
	 *  imobilizado, etc.), informar o c�digo 00 (zeros) (v2.0)<br/>
	 */
	public void setNCM(String nCM) {
		NCM = nCM;
	}
	public String getNCM() {
		return NCM;
	}
	/**
	 * C�digo EX TIPI (3 posi��es)
	 */
	public void setEXTIPI(String eXTIPI) {
		EXTIPI = eXTIPI;
	}
	public String getEXTIPI() {
		return EXTIPI;
	}
	/**
	 * C�digo Fiscal de Opera��es e Presta��es
	 */
	public void setCFOP(String cFOP) {
		CFOP = cFOP;
	}
	public String getCFOP() {
		return CFOP;
	}
	/**
	 * Unidade comercial.<br/>
	 * 1 a 6 caracteres.
	 */
	public void setuCom(String uCom) {
		this.uCom = uCom;
	}
	public String getuCom() {
		return uCom;
	}
	/**
	 * Quantidade Comercial  do produto, alterado para aceitar de<br/> 
	 * 0 a 4 casas decimais e 11 inteiros. (v2.0)
	 */
	public void setqCom(BigDecimal qCom) {
		this.qCom = qCom;
		if (this.qCom != null) {
			this.qCom = this.qCom.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getqCom() {
		return qCom;
	}
	/**
	 * Valor unit�rio de comercializa��o  - <br/>
	 * alterado para aceitar 0 a 10 casas decimais e 11 inteiros (v2.0)
	 */
	public void setvUnCom(BigDecimal vUnCom) {
		this.vUnCom = vUnCom;
		if (this.vUnCom != null) {
			this.vUnCom = this.vUnCom.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvUnCom() {
		return vUnCom;
	}
	/**
	 * Valor bruto do produto ou servi�o.<br/>
	 * 11 digitos e 2 casas decimais.
	 */
	public void setvProd(BigDecimal vProd) {
		this.vProd = vProd;
		if (this.vProd != null) {
			this.vProd = this.vProd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvProd() {
		return vProd;
	}
	/**
	 * GTIN (Global Trade Item Number) da unidade tribut�vel,<br/>
	 * antigo c�digo EAN ou c�digo de barras
	 */
	public void setcEANTrib(String cEANTrib) {
		this.cEANTrib = cEANTrib;
	}
	public String getcEANTrib() {
		return cEANTrib;
	}
	/**
	 * Unidade Tribut�vel
	 */
	public void setuTrib(String uTrib) {
		this.uTrib = uTrib;
	}
	public String getuTrib() {
		return uTrib;
	}
	/**
	 * Quantidade Tribut�vel - alterado para aceitar<br/>
	 * de 0 a 4 casas decimais e 11 inteiros (v2.0)
	 */
	public void setqTrib(BigDecimal qTrib) {
		this.qTrib = qTrib;
		if (this.qTrib != null) {
			this.qTrib = this.qTrib.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getqTrib() {
		return qTrib;
	}
	/**
	 * Valor unit�rio de tributa��o<br/> 
	 * alterado para aceitar 0 a 10 casas decimais e 11 inteiros (v2.0)
	 */
	public void setvUnTrib(BigDecimal vUnTrib) {
		this.vUnTrib = vUnTrib;
		if (this.vUnTrib != null) {
			this.vUnTrib = this.vUnTrib.setScale(10, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvUnTrib() {
		return vUnTrib;
	}
	/**
	 * Valor Total do Frete
	 */
	public void setvFrete(BigDecimal vFrete) {
		this.vFrete = vFrete;
		if (this.vFrete != null) {
			this.vFrete = this.vFrete.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvFrete() {
		return vFrete;
	}
	/**
	 * Valor Total do Seguro
	 */
	public void setvSeg(BigDecimal vSeg) {
		this.vSeg = vSeg;
		if (this.vSeg != null) {
			this.vSeg = this.vSeg.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvSeg() {
		return vSeg;
	}
	/**
	 * Valor do Desconto
	 */
	public void setvDesc(BigDecimal vDesc) {
		this.vDesc = vDesc;
		if (this.vDesc != null) {
			this.vDesc = this.vDesc.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvDesc() {
		return vDesc;
	}
	/**
	 * Outras despesas acess�rias - (v2.0)
	 */
	public void setvOutro(BigDecimal vOutro) {
		this.vOutro = vOutro;
		if (this.vOutro != null) {
			this.vOutro = this.vOutro.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvOutro() {
		return vOutro;
	}
	/**
	 * Este campo dever� ser preenchido com:<br/>
	 * <ul>
	 * <li>0 � o valor do item (vProd) n�o comp�e o valor total da NF-e (vProd)</li>
	 * <li>1 � o valor do item (vProd) comp�e o valor total da NF-e (vProd) (v2.0)</li>
	 * </ul>
	 */
	public void setIndTot(String indTot) {
		this.indTot = indTot;
	}
	public String getIndTot() {
		return indTot;
	}
	/**
	 * Pedido de compra - Informa��o de interesse do emissor para controle do B2B. (v2.0)
	 */
	public void setxPed(String xPed) {
		this.xPed = xPed;
	}
	public String getxPed() {
		return xPed;
	}
	/**
	 * N�mero do Item do Pedido de Compra<br/>
	 * Identifica��o do n�mero do item do pedido de Compra (v2.0)
	 */
	public void setnItemPed(String nItemPed) {
		this.nItemPed = nItemPed;
	}
	public String getnItemPed() {
		return nItemPed;
	}
}