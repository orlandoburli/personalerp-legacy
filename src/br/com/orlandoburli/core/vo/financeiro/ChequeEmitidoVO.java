package br.com.orlandoburli.core.vo.financeiro;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ChequeEmitidoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoChequeEmitido;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	
	private Integer NumeroChequeEmitido;
	
	private Integer CodigoEmpresaContaBancaria;
	private Integer CodigoLojaContaBancaria;
	private Integer CodigoContaBancaria;
	
	private Integer SequencialMovimentacaoContaBancaria;
	
	private BigDecimal ValorChequeEmitido;
	private Date DataEmissaoChequeEmitido;
	private Date BomParaChequeEmitido;
	
	private Integer CodigoEmpresaDestinatario;
	private Integer CodigoLojaDestinatario;
	private Integer CodigoPessoaDestinatario;
	
	private Integer CodigoEmpresaPagamento;
	private Integer CodigoLojaPagamento;
	private Integer CodigoPagamento;
	
	private String StatusChequeEmitido;
	private Date DataCompensacaoChequeEmitido;

	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.DescricaoContaBancaria FROM [schema].ContaBancaria b " +
    		"	WHERE a.CodigoEmpresaContaBancaria = b.CodigoEmpresa" +
    		"     AND a.CodigoLojaContaBancaria = b.CodigoLoja" +
    		"     AND a.CodigoContaBancaria = b.CodigoContaBancaria)")
    private String DescricaoConta;
    
    @Formula(getFormula="( SELECT NomeRazaoSocialPessoa FROM [schema].Pessoa b " +
    		"  WHERE b.CodigoEmpresa = a.CodigoEmpresaDestinatario" +
    		"    AND b.CodigoLoja = a.CodigoLojaDestinatario" +
    		"    AND b.CodigoPessoa = a.CodigoPessoaDestinatario)")
    private String DestinatarioChequeEmitido;
	
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

	public Integer getCodigoChequeEmitido() {
		return CodigoChequeEmitido;
	}

	public void setCodigoChequeEmitido(Integer codigoChequeEmitido) {
		CodigoChequeEmitido = codigoChequeEmitido;
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

	public Integer getNumeroChequeEmitido() {
		return NumeroChequeEmitido;
	}

	public void setNumeroChequeEmitido(Integer numeroChequeEmitido) {
		NumeroChequeEmitido = numeroChequeEmitido;
	}

	public Integer getCodigoEmpresaContaBancaria() {
		return CodigoEmpresaContaBancaria;
	}

	public void setCodigoEmpresaContaBancaria(Integer codigoEmpresaContaBancaria) {
		CodigoEmpresaContaBancaria = codigoEmpresaContaBancaria;
	}

	public Integer getCodigoLojaContaBancaria() {
		return CodigoLojaContaBancaria;
	}

	public void setCodigoLojaContaBancaria(Integer codigoLojaContaBancaria) {
		CodigoLojaContaBancaria = codigoLojaContaBancaria;
	}

	public Integer getCodigoContaBancaria() {
		return CodigoContaBancaria;
	}

	public void setCodigoContaBancaria(Integer codigoContaBancaria) {
		CodigoContaBancaria = codigoContaBancaria;
	}

	public BigDecimal getValorChequeEmitido() {
		return ValorChequeEmitido;
	}

	public void setValorChequeEmitido(BigDecimal valorChequeEmitido) {
		ValorChequeEmitido = valorChequeEmitido;
	}

	public Date getDataEmissaoChequeEmitido() {
		return DataEmissaoChequeEmitido;
	}

	public void setDataEmissaoChequeEmitido(Date dataEmissaoChequeEmitido) {
		DataEmissaoChequeEmitido = dataEmissaoChequeEmitido;
	}

	public Date getBomParaChequeEmitido() {
		return BomParaChequeEmitido;
	}

	public void setBomParaChequeEmitido(Date bomParaChequeEmitido) {
		BomParaChequeEmitido = bomParaChequeEmitido;
	}

	public Integer getCodigoEmpresaDestinatario() {
		return CodigoEmpresaDestinatario;
	}

	public void setCodigoEmpresaDestinatario(Integer codigoEmpresaDestinatario) {
		CodigoEmpresaDestinatario = codigoEmpresaDestinatario;
	}

	public Integer getCodigoLojaDestinatario() {
		return CodigoLojaDestinatario;
	}

	public void setCodigoLojaDestinatario(Integer codigoLojaDestinatario) {
		CodigoLojaDestinatario = codigoLojaDestinatario;
	}

	public Integer getCodigoPessoaDestinatario() {
		return CodigoPessoaDestinatario;
	}

	public void setCodigoPessoaDestinatario(Integer codigoPessoaDestinatario) {
		CodigoPessoaDestinatario = codigoPessoaDestinatario;
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

	public Integer getCodigoPagamento() {
		return CodigoPagamento;
	}

	public void setCodigoPagamento(Integer codigoPagamento) {
		CodigoPagamento = codigoPagamento;
	}

	public void setDestinatarioChequeEmitido(String destinatarioChequeEmitido) {
		DestinatarioChequeEmitido = destinatarioChequeEmitido;
	}

	public String getDestinatarioChequeEmitido() {
		return DestinatarioChequeEmitido;
	}

	public void setStatusChequeEmitido(String statusChequeEmitido) {
		StatusChequeEmitido = statusChequeEmitido;
	}

	public String getStatusChequeEmitido() {
		return StatusChequeEmitido;
	}

	public void setDataCompensacaoChequeEmitido(
			Date dataCompensacaoChequeEmitido) {
		DataCompensacaoChequeEmitido = dataCompensacaoChequeEmitido;
	}

	public Date getDataCompensacaoChequeEmitido() {
		return DataCompensacaoChequeEmitido;
	}

	public void setDescricaoConta(String descricaoConta) {
		DescricaoConta = descricaoConta;
	}

	public String getDescricaoConta() {
		return DescricaoConta;
	}

	public void setSequencialMovimentacaoContaBancaria(
			Integer sequencialMovimentacaoContaBancaria) {
		SequencialMovimentacaoContaBancaria = sequencialMovimentacaoContaBancaria;
	}

	public Integer getSequencialMovimentacaoContaBancaria() {
		return SequencialMovimentacaoContaBancaria;
	}

}
