package br.com.orlandoburli.core.extras.nfe.model.totais;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de Valores Totais referentes ao ICMS 
 */
public class IcmsTot implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Base de Cálculo do ICMS (15, 2)
	 */
	private BigDecimal vBC;
	/**
	 * Valor Total do ICMS (15, 2)
	 */
	private BigDecimal vICMS;
	/**
	 * Base de Cálculo do ICMS ST (15, 2)
	 */
	private BigDecimal vBCST;
	/**
	 * Valor Total do ICMS ST (15, 2)
	 */
	private BigDecimal vST;
	/**
	 * Valor Total dos produtos e serviços (15, 2)
	 */
	private BigDecimal vProd;
	/**
	 * Valor Total do Frete (15, 2)
	 */
	private BigDecimal vFrete;
	/**
	 * Valor Total do Seguro (15, 2)
	 */
	private BigDecimal vSeg;
	/**
	 * Valor Total do Desconto (15, 2)
	 */
	private BigDecimal vDesc;
	/**
	 * Valor Total do II (15, 2)
	 * TODO O que é II???
	 */
	private BigDecimal vII;
	/**
	 * Valor Total do IPI (15, 2)
	 */
	private BigDecimal vIPI;
	/**
	 * Valor do PIS (15, 2)
	 */
	private BigDecimal vPIS;
	/**
	 * Valor do COFINS (15, 2)
	 */
	private BigDecimal vCOFINS;
	/**
	 * Outras Despesas acessórias (15, 2)
	 */
	private BigDecimal vOutro;
	/**
	 * Valor Total da NF-e (15, 2)
	 */
	private BigDecimal vNF;
	////////////////////////////

	/**
	 * Base de Cálculo do ICMS (15, 2)
	 */
	public void setvBC(BigDecimal vBC) {
		this.vBC = vBC;
		if (this.vBC != null) {
			this.vBC = this.vBC.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvBC() {
		return vBC;
	}
	/**
	 * Valor Total do ICMS (15, 2)
	 */
	public void setvICMS(BigDecimal vICMS) {
		this.vICMS = vICMS;
		if (this.vICMS != null) {
			this.vICMS = this.vICMS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvICMS() {
		return vICMS;
	}
	/**
	 * Base de Cálculo do ICMS ST (15, 2)
	 */
	public void setvBCST(BigDecimal vBCST) {
		this.vBCST = vBCST;
		if (this.vBCST != null) {
			this.vBCST = this.vBCST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvBCST() {
		return vBCST;
	}
	/**
	 * Valor Total do ICMS ST (15, 2)
	 */
	public void setvST(BigDecimal vST) {
		this.vST = vST;
		if (this.vST != null) {
			this.vST = this.vST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	/**
	 * Valor Total do ICMS ST (15, 2)
	 */
	public BigDecimal getvST() {
		return vST;
	}
	/**
	 * Valor Total dos produtos e serviços (15, 2)
	 */
	public void setvProd(BigDecimal vProd) {
		this.vProd = vProd;
		if (this.vProd != null) {
			this.vProd = this.vProd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	/**
	 * Valor Total dos produtos e serviços (15, 2)
	 */
	public BigDecimal getvProd() {
		return vProd;
	}
	/**
	 * Valor Total do Frete (15, 2)
	 */
	public void setvFrete(BigDecimal vFrete) {
		this.vFrete = vFrete;
		if (this.vFrete != null) {
			this.vFrete = this.vFrete.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	/**
	 * Valor Total do Frete (15, 2)
	 */
	public BigDecimal getvFrete() {
		return vFrete;
	}
	/**
	 * Valor Total do Seguro (15, 2)
	 */
	public void setvSeg(BigDecimal vSeg) {
		this.vSeg = vSeg;
		if (this.vSeg != null) {
			this.vSeg = this.vSeg.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	/**
	 * Valor Total do Seguro (15, 2)
	 */
	public BigDecimal getvSeg() {
		return vSeg;
	}
	/**
	 * Valor Total do Desconto (15, 2)
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
	 * Valor Total do II (15, 2)
	 * TODO O que é II???
	 */
	public void setvII(BigDecimal vII) {
		this.vII = vII;
		if (this.vII != null) {
			this.vII = this.vII.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvII() {
		return vII;
	}
	/**
	 * Valor Total do IPI (15, 2)
	 */
	public void setvIPI(BigDecimal vIPI) {
		this.vIPI = vIPI;
		if (this.vIPI != null) {
			this.vIPI = this.vIPI.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvIPI() {
		return vIPI;
	}
	/**
	 * Valor do PIS (15, 2)
	 */
	public void setvPIS(BigDecimal vPIS) {
		this.vPIS = vPIS;
		if (this.vPIS != null) {
			this.vPIS = this.vPIS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvPIS() {
		return vPIS;
	}
	/**
	 * Valor do COFINS (15, 2)
	 */
	public void setvCOFINS(BigDecimal vCOFINS) {
		this.vCOFINS = vCOFINS;
		if (this.vCOFINS != null) {
			this.vCOFINS = this.vCOFINS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvCOFINS() {
		return vCOFINS;
	}
	/**
	 * Outras Despesas acessórias (15, 2)
	 */
	public void setvOutro(BigDecimal vOutro) {
		this.vOutro = vOutro;
	}
	/**
	 * Outras Despesas acessórias (15, 2)
	 */
	public BigDecimal getvOutro() {
		return vOutro;
	}
	/**
	 * Valor Total da NF-e (15, 2)
	 */
	public void setvNF(BigDecimal vNF) {
		this.vNF = vNF;
		if (this.vNF != null) {
			this.vNF = this.vNF.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	/**
	 * Valor Total da NF-e (15, 2)
	 */
	public BigDecimal getvNF() {
		return vNF;
	}
}