package br.com.orlandoburli.core.vo.financeiro.contasareceber;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ContaReceberVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	
	@Ignore
	public static final String SITUACAO_ABERTO = "A";
	@Ignore
	public static final String SITUACAO_QUITADO = "Q";
	@Ignore
	public static final String SITUACAO_CANCELADO = "C";
	@Ignore
	public static final String SITUACAO_PREVISTO = "P";
	
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoContaReceber;
	
	private Integer ParcelaContaReceber;
	private Integer NumeroParcelasContaReceber;
	private String DescricaoContaReceber;
	private Timestamp DataLancamentoContaReceber;
	private Date DataEmissaoContaReceber;
	private Date DataVencimentoContaReceber;
	
	private String NumeroTituloContaReceber;
	private String SituacaoContaReceber;
	
	@Precision(decimals=2)
	private BigDecimal ValorMultaContaReceber;
	@Precision(decimals=2)
	private BigDecimal PercentualMultaContaReceber;
	@Precision(decimals=2)
	private BigDecimal ValorJurosDiarioContaReceber;
	@Precision(decimals=2)
	private BigDecimal PercentualJurosDiarioContaReceber;
	@Precision(decimals=2)
	private BigDecimal ValorContaReceber;
	@Precision(decimals=2)
	private BigDecimal DescontoContaReceber;
	
	private Date DescontoAteContaReceber;
	
	private String ObservacaoContaReceber;
	private String ObservacaoCancelamentoContaReceber;
	
	private Integer CodigoPlanoConta;
	private Integer CodigoCentroCusto;
	private Integer CodigoTipoDocumento;
	private Integer CodigoPortador;
	
	private Integer CodigoEmpresaCliente; 
    private Integer CodigoLojaCliente;
    private Integer CodigoPessoaCliente;
    private Integer CodigoEnderecoPessoaCliente;
    
    @Formula(getFormula="(SELECT b.FantasiaApelidoEnderecoPessoa FROM [schema].EnderecoPessoa b " +
    		"			   WHERE b.CodigoPessoa         = a.CodigoPessoaCliente " +
    		"                AND b.CodigoEmpresa        = a.CodigoEmpresaCliente " +
    		"                AND b.CodigoLoja           = a.CodigoLojaCliente" +
    		"                AND b.CodigoEnderecoPessoa = a.CodigoEnderecoPessoaCliente)")
    private String NomeClienteContaReceber;
    
    @Formula(getFormula="(SELECT c.NomePortador FROM [schema].portador c WHERE c.CodigoPortador = a.CodigoPortador)")
    private String NomePortador;
    
    @Formula(getFormula="(SELECT d.NumeroPlanoConta from [schema].planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta)")
    private String NumeroPlanoConta;
    
    private String FlagDREContaReceber;
    
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    /** Campos calculado */
    @Ignore
    private String SituacaoTituloAReceber;
    @Ignore
    private BigDecimal ValorCalculado;
    @Ignore
    private BigDecimal ValorMultaCalculado;
    @Ignore
    private BigDecimal ValorJurosCalculado;
    @Ignore
	private BigDecimal ValorDescontoCalculado;
    @Ignore
	private BigDecimal ValorAbertoContaReceber;
    @Ignore
    private List<BaixaContaReceberVO> Baixas;

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
	 * @return the parcelaContaReceber
	 */
	public Integer getParcelaContaReceber() {
		return ParcelaContaReceber;
	}

	/**
	 * @param parcelaContaReceber the parcelaContaReceber to set
	 */
	public void setParcelaContaReceber(Integer parcelaContaReceber) {
		ParcelaContaReceber = parcelaContaReceber;
	}

	/**
	 * @return the numeroParcelasContaReceber
	 */
	public Integer getNumeroParcelasContaReceber() {
		return NumeroParcelasContaReceber;
	}

	/**
	 * @param numeroParcelasContaReceber the numeroParcelasContaReceber to set
	 */
	public void setNumeroParcelasContaReceber(Integer numeroParcelasContaReceber) {
		NumeroParcelasContaReceber = numeroParcelasContaReceber;
	}

	/**
	 * @return the dataLancamentoContaReceber
	 */
	public Timestamp getDataLancamentoContaReceber() {
		return DataLancamentoContaReceber;
	}

	/**
	 * @param dataLancamentoContaReceber the dataLancamentoContaReceber to set
	 */
	public void setDataLancamentoContaReceber(Timestamp dataLancamentoContaReceber) {
		DataLancamentoContaReceber = dataLancamentoContaReceber;
	}

	/**
	 * @return the dataEmissaoContaReceber
	 */
	public Date getDataEmissaoContaReceber() {
		return DataEmissaoContaReceber;
	}

	/**
	 * @param dataEmissaoContaReceber the dataEmissaoContaReceber to set
	 */
	public void setDataEmissaoContaReceber(Date dataEmissaoContaReceber) {
		DataEmissaoContaReceber = dataEmissaoContaReceber;
	}

	/**
	 * @return the dataVencimentoContaReceber
	 */
	public Date getDataVencimentoContaReceber() {
		return DataVencimentoContaReceber;
	}

	/**
	 * @param dataVencimentoContaReceber the dataVencimentoContaReceber to set
	 */
	public void setDataVencimentoContaReceber(Date dataVencimentoContaReceber) {
		DataVencimentoContaReceber = dataVencimentoContaReceber;
	}

	/**
	 * @return the numeroTituloContaReceber
	 */
	public String getNumeroTituloContaReceber() {
		return NumeroTituloContaReceber;
	}

	/**
	 * @param numeroTituloContaReceber the numeroTituloContaReceber to set
	 */
	public void setNumeroTituloContaReceber(String numeroTituloContaReceber) {
		NumeroTituloContaReceber = numeroTituloContaReceber;
	}

	/**
	 * @return the situacaoContaReceber
	 */
	public String getSituacaoContaReceber() {
		return SituacaoContaReceber;
	}

	/**
	 * @param situacaoContaReceber the situacaoContaReceber to set
	 */
	public void setSituacaoContaReceber(String situacaoContaReceber) {
		SituacaoContaReceber = situacaoContaReceber;
	}

	/**
	 * @return the valorMultaContaReceber
	 */
	public BigDecimal getValorMultaContaReceber() {
		return ValorMultaContaReceber;
	}

	/**
	 * @param valorMultaContaReceber the valorMultaContaReceber to set
	 */
	public void setValorMultaContaReceber(BigDecimal valorMultaContaReceber) {
		ValorMultaContaReceber = valorMultaContaReceber;
	}

	/**
	 * @return the percentualMultaContaReceber
	 */
	public BigDecimal getPercentualMultaContaReceber() {
		return PercentualMultaContaReceber;
	}

	/**
	 * @param percentualMultaContaReceber the percentualMultaContaReceber to set
	 */
	public void setPercentualMultaContaReceber(
			BigDecimal percentualMultaContaReceber) {
		PercentualMultaContaReceber = percentualMultaContaReceber;
	}

	/**
	 * @return the valorJurosDiarioContaReceber
	 */
	public BigDecimal getValorJurosDiarioContaReceber() {
		return ValorJurosDiarioContaReceber;
	}

	/**
	 * @param valorJurosDiarioContaReceber the valorJurosDiarioContaReceber to set
	 */
	public void setValorJurosDiarioContaReceber(
			BigDecimal valorJurosDiarioContaReceber) {
		ValorJurosDiarioContaReceber = valorJurosDiarioContaReceber;
	}

	/**
	 * @return the percentualJurosDiarioContaReceber
	 */
	public BigDecimal getPercentualJurosDiarioContaReceber() {
		return PercentualJurosDiarioContaReceber;
	}

	/**
	 * @param percentualJurosDiarioContaReceber the percentualJurosDiarioContaReceber to set
	 */
	public void setPercentualJurosDiarioContaReceber(
			BigDecimal percentualJurosDiarioContaReceber) {
		PercentualJurosDiarioContaReceber = percentualJurosDiarioContaReceber;
	}

	/**
	 * @return the valorContaReceber
	 */
	public BigDecimal getValorContaReceber() {
		return ValorContaReceber;
	}

	/**
	 * @param valorContaReceber the valorContaReceber to set
	 */
	public void setValorContaReceber(BigDecimal valorContaReceber) {
		ValorContaReceber = valorContaReceber;
	}

	/**
	 * @return the descontoContaReceber
	 */
	public BigDecimal getDescontoContaReceber() {
		return DescontoContaReceber;
	}

	/**
	 * @param descontoContaReceber the descontoContaReceber to set
	 */
	public void setDescontoContaReceber(BigDecimal descontoContaReceber) {
		DescontoContaReceber = descontoContaReceber;
	}

	/**
	 * @return the descontoAteContaReceber
	 */
	public Date getDescontoAteContaReceber() {
		return DescontoAteContaReceber;
	}

	/**
	 * @param descontoAteContaReceber the descontoAteContaReceber to set
	 */
	public void setDescontoAteContaReceber(Date descontoAteContaReceber) {
		DescontoAteContaReceber = descontoAteContaReceber;
	}

	/**
	 * @return the observacaoContaReceber
	 */
	public String getObservacaoContaReceber() {
		return ObservacaoContaReceber;
	}

	/**
	 * @param observacaoContaReceber the observacaoContaReceber to set
	 */
	public void setObservacaoContaReceber(String observacaoContaReceber) {
		ObservacaoContaReceber = observacaoContaReceber;
	}

	/**
	 * @return the observacaoCancelamentoContaReceber
	 */
	public String getObservacaoCancelamentoContaReceber() {
		return ObservacaoCancelamentoContaReceber;
	}

	/**
	 * @param observacaoCancelamentoContaReceber the observacaoCancelamentoContaReceber to set
	 */
	public void setObservacaoCancelamentoContaReceber(
			String observacaoCancelamentoContaReceber) {
		ObservacaoCancelamentoContaReceber = observacaoCancelamentoContaReceber;
	}

	/**
	 * @return the codigoPlanoConta
	 */
	public Integer getCodigoPlanoConta() {
		return CodigoPlanoConta;
	}

	/**
	 * @param codigoPlanoConta the codigoPlanoConta to set
	 */
	public void setCodigoPlanoConta(Integer codigoPlanoConta) {
		CodigoPlanoConta = codigoPlanoConta;
	}

	/**
	 * @return the codigoCentroCusto
	 */
	public Integer getCodigoCentroCusto() {
		return CodigoCentroCusto;
	}

	/**
	 * @param codigoCentroCusto the codigoCentroCusto to set
	 */
	public void setCodigoCentroCusto(Integer codigoCentroCusto) {
		CodigoCentroCusto = codigoCentroCusto;
	}

	/**
	 * @return the codigoTipoDocumento
	 */
	public Integer getCodigoTipoDocumento() {
		return CodigoTipoDocumento;
	}

	/**
	 * @param codigoTipoDocumento the codigoTipoDocumento to set
	 */
	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		CodigoTipoDocumento = codigoTipoDocumento;
	}

	/**
	 * @return the codigoPortador
	 */
	public Integer getCodigoPortador() {
		return CodigoPortador;
	}

	/**
	 * @param codigoPortador the codigoPortador to set
	 */
	public void setCodigoPortador(Integer codigoPortador) {
		CodigoPortador = codigoPortador;
	}

	/**
	 * @return the codigoEmpresaCliente
	 */
	public Integer getCodigoEmpresaCliente() {
		return CodigoEmpresaCliente;
	}

	/**
	 * @param codigoEmpresaCliente the codigoEmpresaCliente to set
	 */
	public void setCodigoEmpresaCliente(Integer codigoEmpresaCliente) {
		CodigoEmpresaCliente = codigoEmpresaCliente;
	}

	/**
	 * @return the codigoLojaCliente
	 */
	public Integer getCodigoLojaCliente() {
		return CodigoLojaCliente;
	}

	/**
	 * @param codigoLojaCliente the codigoLojaCliente to set
	 */
	public void setCodigoLojaCliente(Integer codigoLojaCliente) {
		CodigoLojaCliente = codigoLojaCliente;
	}

	/**
	 * @return the codigoPessoaCliente
	 */
	public Integer getCodigoPessoaCliente() {
		return CodigoPessoaCliente;
	}

	/**
	 * @param codigoPessoaCliente the codigoPessoaCliente to set
	 */
	public void setCodigoPessoaCliente(Integer codigoPessoaCliente) {
		CodigoPessoaCliente = codigoPessoaCliente;
	}

	/**
	 * @return the codigoEnderecoPessoaCliente
	 */
	public Integer getCodigoEnderecoPessoaCliente() {
		return CodigoEnderecoPessoaCliente;
	}

	/**
	 * @param codigoEnderecoPessoaCliente the codigoEnderecoPessoaCliente to set
	 */
	public void setCodigoEnderecoPessoaCliente(Integer codigoEnderecoPessoaCliente) {
		CodigoEnderecoPessoaCliente = codigoEnderecoPessoaCliente;
	}

	public void setNumeroPlanoConta(String numeroPlanoConta) {
		NumeroPlanoConta = numeroPlanoConta;
	}

	public String getNumeroPlanoConta() {
		return NumeroPlanoConta;
	}

	public void setNomePortador(String nomePortador) {
		NomePortador = nomePortador;
	}

	public String getNomePortador() {
		return NomePortador;
	}

	public void setValorCalculado(BigDecimal valorCalculado) {
		ValorCalculado = valorCalculado;
	}

	public BigDecimal getValorCalculado() {
		return ValorCalculado;
	}

	public void setValorMultaCalculado(BigDecimal valorMultaCalculado) {
		ValorMultaCalculado = valorMultaCalculado;
	}

	public BigDecimal getValorMultaCalculado() {
		return ValorMultaCalculado;
	}

	public void setValorJurosCalculado(BigDecimal valorJurosCalculado) {
		ValorJurosCalculado = valorJurosCalculado;
	}

	public BigDecimal getValorJurosCalculado() {
		return ValorJurosCalculado;
	}

	public void setValorDescontoCalculado(BigDecimal valorDescontoCalculado) {
		ValorDescontoCalculado = valorDescontoCalculado;
	}

	public BigDecimal getValorDescontoCalculado() {
		return ValorDescontoCalculado;
	}

	public void setBaixas(List<BaixaContaReceberVO> baixas) {
		Baixas = baixas;
	}

	public List<BaixaContaReceberVO> getBaixas() {
		return Baixas;
	}

	public void setNomeClienteContaReceber(String nomeClienteContaReceber) {
		NomeClienteContaReceber = nomeClienteContaReceber;
	}

	public String getNomeClienteContaReceber() {
		return NomeClienteContaReceber;
	}

	public void setSituacaoTituloAReceber(String situacaoTituloAReceber) {
		SituacaoTituloAReceber = situacaoTituloAReceber;
	}

	public String getSituacaoTituloAReceber() {
		return SituacaoTituloAReceber;
	}

	public void setValorAbertoContaReceber(BigDecimal valorAbertoContaReceber) {
		ValorAbertoContaReceber = valorAbertoContaReceber;
	}

	public BigDecimal getValorAbertoContaReceber() {
		return ValorAbertoContaReceber;
	}

	public void setDescricaoContaReceber(String descricaoContaReceber) {
		DescricaoContaReceber = descricaoContaReceber;
	}

	public String getDescricaoContaReceber() {
		return DescricaoContaReceber;
	}

	public String getFlagDREContaReceber() {
		return FlagDREContaReceber;
	}

	public void setFlagDREContaReceber(String flagDREContaReceber) {
		FlagDREContaReceber = flagDREContaReceber;
	}
}