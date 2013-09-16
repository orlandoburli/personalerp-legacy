package br.com.orlandoburli.core.vo.financeiro;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class MovimentacaoContaBancariaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Ignore
	public static final String OPERACAO_CREDITO = "C";
	@Ignore
	public static final String OPERACAO_DEBITO = "D";
	
	
	@Key
	private Integer CodigoContaBancaria;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer SequencialMovimentacaoContaBancaria;
	
	private Integer CodigoPagamento;
	private Integer CodigoEmpresaPagamento;
	private Integer CodigoLojaPagamento;
	
	private Integer CodigoEmpresaRecebimento;
	private Integer CodigoLojaRecebimento;
	private Integer CodigoRecebimento;
	
	private String OperacaoMovimentacaoContaBancaria;
	private BigDecimal ValorMovimentacaoContaBancaria;
	private String DescricaoMovimentacaoContaBancaria;
	
	private Date DataMovimentacaoContaBancaria;

	 @Formula(getFormula="(SELECT b.DescricaoContaBancaria FROM [schema].ContaBancaria b " +
	    		"	WHERE a.CodigoEmpresa = b.CodigoEmpresa" +
	    		"     AND a.CodigoLoja = b.CodigoLoja" +
	    		"     AND a.CodigoContaBancaria = b.CodigoContaBancaria)")
	    private String DescricaoConta;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}

	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

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

	public Integer getCodigoContaBancaria() {
		return CodigoContaBancaria;
	}

	public void setCodigoContaBancaria(Integer codigoContaBancaria) {
		CodigoContaBancaria = codigoContaBancaria;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getSequencialMovimentacaoContaBancaria() {
		return SequencialMovimentacaoContaBancaria;
	}

	public void setSequencialMovimentacaoContaBancaria(
			Integer sequencialMovimentacaoContaBancaria) {
		SequencialMovimentacaoContaBancaria = sequencialMovimentacaoContaBancaria;
	}

	public Integer getCodigoPagamento() {
		return CodigoPagamento;
	}

	public void setCodigoPagamento(Integer codigoPagamento) {
		CodigoPagamento = codigoPagamento;
	}

	public Integer getCodigoEmpresaPagamento() {
		return CodigoEmpresaPagamento;
	}

	public void setCodigoEmpresaPagamento(Integer codigoEmpresaPagamento) {
		CodigoEmpresaPagamento = codigoEmpresaPagamento;
	}

	public Integer getCodigoLojaPagamento() {
		return CodigoLojaPagamento;
	}

	public void setCodigoLojaPagamento(Integer codigoLojaPagamento) {
		CodigoLojaPagamento = codigoLojaPagamento;
	}

	public BigDecimal getValorMovimentacaoContaBancaria() {
		return ValorMovimentacaoContaBancaria;
	}

	public void setValorMovimentacaoContaBancaria(
			BigDecimal valorMovimentacaoContaBancaria) {
		ValorMovimentacaoContaBancaria = valorMovimentacaoContaBancaria;
	}

	public void setOperacaoMovimentacaoContaBancaria(
			String operacaoMovimentacaoContaBancaria) {
		OperacaoMovimentacaoContaBancaria = operacaoMovimentacaoContaBancaria;
	}

	public String getOperacaoMovimentacaoContaBancaria() {
		return OperacaoMovimentacaoContaBancaria;
	}

	public void setCodigoEmpresaRecebimento(Integer codigoEmpresaRecebimento) {
		CodigoEmpresaRecebimento = codigoEmpresaRecebimento;
	}

	public Integer getCodigoEmpresaRecebimento() {
		return CodigoEmpresaRecebimento;
	}

	public void setCodigoLojaRecebimento(Integer codigoLojaRecebimento) {
		CodigoLojaRecebimento = codigoLojaRecebimento;
	}

	public Integer getCodigoLojaRecebimento() {
		return CodigoLojaRecebimento;
	}

	public void setCodigoRecebimento(Integer codigoRecebimento) {
		CodigoRecebimento = codigoRecebimento;
	}

	public Integer getCodigoRecebimento() {
		return CodigoRecebimento;
	}

	public void setDescricaoMovimentacaoContaBancaria(
			String descricaoMovimentacaoContaBancaria) {
		DescricaoMovimentacaoContaBancaria = descricaoMovimentacaoContaBancaria;
	}

	public String getDescricaoMovimentacaoContaBancaria() {
		return DescricaoMovimentacaoContaBancaria;
	}

	public void setDataMovimentacaoContaBancaria(
			Date dataMovimentacaoContaBancaria) {
		DataMovimentacaoContaBancaria = dataMovimentacaoContaBancaria;
	}

	public Date getDataMovimentacaoContaBancaria() {
		return DataMovimentacaoContaBancaria;
	}

	public String getDescricaoConta() {
		return DescricaoConta;
	}

	public void setDescricaoConta(String descricaoConta) {
		DescricaoConta = descricaoConta;
	}
}
