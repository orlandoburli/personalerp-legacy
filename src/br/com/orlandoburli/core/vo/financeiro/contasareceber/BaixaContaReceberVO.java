package br.com.orlandoburli.core.vo.financeiro.contasareceber;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class BaixaContaReceberVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresaContaReceber;
	@Key
	private Integer CodigoLojaContaReceber;
	@Key
	private Integer CodigoContaReceber;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoRecebimento;
	
	@Precision(decimals=2)
	private BigDecimal ValorRecebimento;
	
	@Ignore
	private RecebimentoVO Recebimento;
	
	@Formula(getFormula="(SELECT b.NomeUsuario FROM [schema].Usuario b" +
	    		"  WHERE a.CodigoEmpresaUsuarioLog = b.CodigoEmpresa" +
	    		"    AND a.CodigoLojaUsuarioLog = b.CodigoLoja" +
	    		"    AND a.CodigoUsuarioLog = b.CodigoUsuario)")
	private String NomeUsuarioRecebimento;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}
	
	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	@Override
	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	@Override
	public void setCodigoUsuarioLog(Integer codigoUsuarioLog) {
		CodigoUsuarioLog = codigoUsuarioLog;
	}

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	/**
	 * @return the codigoEmpresaContaReceber
	 */
	public Integer getCodigoEmpresaContaReceber() {
		return CodigoEmpresaContaReceber;
	}

	/**
	 * @param codigoEmpresaContaReceber the codigoEmpresaContaReceber to set
	 */
	public void setCodigoEmpresaContaReceber(Integer codigoEmpresaContaReceber) {
		CodigoEmpresaContaReceber = codigoEmpresaContaReceber;
	}

	/**
	 * @return the codigoLojaContaReceber
	 */
	public Integer getCodigoLojaContaReceber() {
		return CodigoLojaContaReceber;
	}

	/**
	 * @param codigoLojaContaReceber the codigoLojaContaReceber to set
	 */
	public void setCodigoLojaContaReceber(Integer codigoLojaContaReceber) {
		CodigoLojaContaReceber = codigoLojaContaReceber;
	}

	/**
	 * @return the codigoContaReceber
	 */
	public Integer getCodigoContaReceber() {
		return CodigoContaReceber;
	}

	/**
	 * @param codigoContaReceber the codigoContaReceber to set
	 */
	public void setCodigoContaReceber(Integer codigoContaReceber) {
		CodigoContaReceber = codigoContaReceber;
	}

	/**
	 * @return the codigoEmpresa
	 */
	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the codigoLoja
	 */
	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	/**
	 * @param codigoLoja the codigoLoja to set
	 */
	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	/**
	 * @return the codigoRecebimento
	 */
	public Integer getCodigoRecebimento() {
		return CodigoRecebimento;
	}

	/**
	 * @param codigoRecebimento the codigoRecebimento to set
	 */
	public void setCodigoRecebimento(Integer codigoRecebimento) {
		CodigoRecebimento = codigoRecebimento;
	}

	/**
	 * @return the valorRecebimento
	 */
	public BigDecimal getValorRecebimento() {
		return ValorRecebimento;
	}

	/**
	 * @param valorRecebimento the valorRecebimento to set
	 */
	public void setValorRecebimento(BigDecimal valorRecebimento) {
		ValorRecebimento = valorRecebimento;
	}

	public RecebimentoVO getRecebimento() {
		return Recebimento;
	}

	public void setRecebimento(RecebimentoVO recebimento) {
		Recebimento = recebimento;
	}

	public String getNomeUsuarioRecebimento() {
		return NomeUsuarioRecebimento;
	}

	public void setNomeUsuarioRecebimento(String nomeUsuarioRecebimento) {
		NomeUsuarioRecebimento = nomeUsuarioRecebimento;
	}
}