package br.com.orlandoburli.core.extras.nfe.model.details;

import java.io.Serializable;
import java.math.BigDecimal;

public class Prod implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Código do produto ou serviço. 
	 * Preencher com CFOP caso se trate de itens não relacionados 
	 * com mercadorias/produto e que o contribuinte não possua 
	 * codificação própria. <br/>
	 * Formato "CFOP9999"<br/>
	 * Tamanho de 1 a 60 caracteres.
	 */
	private String cProd;
	/**
	 * GTIN (Global Trade Item Number) do produto,<br/> 
	 * antigo código EAN ou código de barras
	 */
	private String cEAN;
	/**
	 * Descrição do produto ou serviço
	 */
	private String xProd;
	/**
	 * Código NCM (8 posições), será permitida a informação do gênero<br/> 
	 * (posição do capítulo do NCM) quando a operação não for de <br/>
	 * comércio exterior (importação/exportação) ou o produto não seja <br/>
	 * tributado pelo IPI. Em caso de item de serviço ou item que não <br/>
	 * tenham produto (Ex. transferência de crédito, crédito do ativo<br/>
	 * imobilizado, etc.), informar o código 00 (zeros) (v2.0)<br/>
	 */
	private String NCM;
	/**
	 * Código EX TIPI (3 posições)<br/>
	 * TODO <b>O qq é isso???</b>
	 */
	private String EXTIPI;
	/**
	 * Código Fiscal de Operações e Prestações
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
	 * Valor unitário de comercialização  - <br/>
	 * alterado para aceitar 0 a 10 casas decimais e 11 inteiros (v2.0)
	 */
	private BigDecimal vUnCom;
	/**
	 * Valor bruto do produto ou serviço.<br/>
	 * 11 digitos e 2 casas decimais.
	 */
	private BigDecimal vProd;
	/**
	 * GTIN (Global Trade Item Number) da unidade tributável,<br/>
	 * antigo código EAN ou código de barras
	 */
	private String cEANTrib;
	/**
	 * Unidade Tributável
	 */
	private String uTrib;
	/**
	 * Quantidade Tributável - alterado para aceitar<br/>
	 * de 0 a 4 casas decimais e 11 inteiros (v2.0)
	 */
	private BigDecimal qTrib;
	/**
	 * Valor unitário de tributação<br/> 
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
	 * Outras despesas acessórias - (v2.0)
	 */
	private BigDecimal vOutro;
	/**
	 * Este campo deverá ser preenchido com:<br/>
	 * <ul>
	 * <li>0 – o valor do item (vProd) não compõe o valor total da NF-e (vProd)</li>
	 * <li>1 – o valor do item (vProd) compõe o valor total da NF-e (vProd) (v2.0)</li>
	 * </ul>
	 */
	private String indTot;
	/**
	 * Pedido de compra - Informação de interesse do emissor para controle do B2B. (v2.0)
	 */
	private String xPed;
	/**
	 * Número do Item do Pedido de Compra<br/>
	 * Identificação do número do item do pedido de Compra (v2.0)
	 */
	private String nItemPed;
	
	////////////////////////////////

	/**
	 * Código do produto ou serviço. 
	 * Preencher com CFOP caso se trate de itens não relacionados 
	 * com mercadorias/produto e que o contribuinte não possua 
	 * codificação própria. <br/>
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
	 * antigo código EAN ou código de barras
	 */
	public void setcEAN(String cEAN) {
		this.cEAN = cEAN;
	}
	public String getcEAN() {
		return cEAN;
	}
	/**
	 * Descrição do produto ou serviço
	 */
	public void setxProd(String xProd) {
		this.xProd = xProd;
	}
	public String getxProd() {
		return xProd;
	}
	/**
	 * Código NCM (8 posições), será permitida a informação do gênero<br/> 
	 * (posição do capítulo do NCM) quando a operação não for de <br/>
	 * comércio exterior (importação/exportação) ou o produto não seja <br/>
	 * tributado pelo IPI. Em caso de item de serviço ou item que não <br/>
	 * tenham produto (Ex. transferência de crédito, crédito do ativo<br/>
	 *  imobilizado, etc.), informar o código 00 (zeros) (v2.0)<br/>
	 */
	public void setNCM(String nCM) {
		NCM = nCM;
	}
	public String getNCM() {
		return NCM;
	}
	/**
	 * Código EX TIPI (3 posições)
	 */
	public void setEXTIPI(String eXTIPI) {
		EXTIPI = eXTIPI;
	}
	public String getEXTIPI() {
		return EXTIPI;
	}
	/**
	 * Código Fiscal de Operações e Prestações
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
	 * Valor unitário de comercialização  - <br/>
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
	 * Valor bruto do produto ou serviço.<br/>
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
	 * GTIN (Global Trade Item Number) da unidade tributável,<br/>
	 * antigo código EAN ou código de barras
	 */
	public void setcEANTrib(String cEANTrib) {
		this.cEANTrib = cEANTrib;
	}
	public String getcEANTrib() {
		return cEANTrib;
	}
	/**
	 * Unidade Tributável
	 */
	public void setuTrib(String uTrib) {
		this.uTrib = uTrib;
	}
	public String getuTrib() {
		return uTrib;
	}
	/**
	 * Quantidade Tributável - alterado para aceitar<br/>
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
	 * Valor unitário de tributação<br/> 
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
	 * Outras despesas acessórias - (v2.0)
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
	 * Este campo deverá ser preenchido com:<br/>
	 * <ul>
	 * <li>0 – o valor do item (vProd) não compõe o valor total da NF-e (vProd)</li>
	 * <li>1 – o valor do item (vProd) compõe o valor total da NF-e (vProd) (v2.0)</li>
	 * </ul>
	 */
	public void setIndTot(String indTot) {
		this.indTot = indTot;
	}
	public String getIndTot() {
		return indTot;
	}
	/**
	 * Pedido de compra - Informação de interesse do emissor para controle do B2B. (v2.0)
	 */
	public void setxPed(String xPed) {
		this.xPed = xPed;
	}
	public String getxPed() {
		return xPed;
	}
	/**
	 * Número do Item do Pedido de Compra<br/>
	 * Identificação do número do item do pedido de Compra (v2.0)
	 */
	public void setnItemPed(String nItemPed) {
		this.nItemPed = nItemPed;
	}
	public String getnItemPed() {
		return nItemPed;
	}
}