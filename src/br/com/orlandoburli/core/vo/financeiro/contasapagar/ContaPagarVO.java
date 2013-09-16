package br.com.orlandoburli.core.vo.financeiro.contasapagar;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ContaPagarVO implements IValueObject {

	@Ignore
	public static final String SITUACAO_ABERTO = "A";
	@Ignore
	public static final String SITUACAO_QUITADO = "Q";
	@Ignore
	public static final String SITUACAO_CANCELADO = "C";
	@Ignore
	public static final String SITUACAO_PREVISTO = "P";
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoContaPagar;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	private Date DataLancamentoContaPagar;
	private String DescricaoContaPagar;
	private Integer CodigoPlanoConta;
	private Integer CodigoCentroCusto;
	
	private Integer CodigoPessoaContaPagar;
	private Integer CodigoEmpresaPessoaContaPagar;
	private Integer CodigoLojaPessoaContaPagar;
	
	private String NumeroTituloContaPagar;
	private Integer ParcelaContaPagar;
	private Integer NumeroParcelasContaPagar;
	private Integer CodigoTipoDocumento;
	private String SituacaoContaPagar;
	private Date DataVencimentoContaPagar;
	private Date DataEmissaoContaPagar;
	
	@Precision(decimals=2)
	private BigDecimal ValorMultaContaPagar;
	@Precision(decimals=2)
	private BigDecimal PercentualMultaContaPagar;
	@Precision(decimals=2)
	private BigDecimal ValorJurosDiarioContaPagar;
	@Precision(decimals=2)
	private BigDecimal PercentualJurosDiarioContaPagar;
	@Precision(decimals=2)
	private BigDecimal ValorContaPagar;
	@Precision(decimals=2)
	private BigDecimal DescontoContaPagar;
	@Precision(decimals=2)
	private Date DescontoAteContaPagar;
	
	private Integer CodigoPortador;
	private String ObservacaoContaPagar;
	private String ObservacaoCancelamentoContaPagar;
	
	private String FlagDREContaPagar;
	
	private String FlagResumoContaPagar;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.NomeRazaoSocialPessoa FROM [schema].pessoa b WHERE b.CodigoPessoa = a.CodigoPessoaContaPagar AND b.CodigoEmpresa = a.CodigoEmpresaPessoaContaPagar AND b.CodigoLoja = a.CodigoLojaPessoaContaPagar)")
    private String NomeFornecedorContaPagar;
    
    @Formula(getFormula="(SELECT c.NomePortador FROM [schema].portador c WHERE c.CodigoPortador = a.CodigoPortador)")
    private String NomePortador;
    
    @Formula(getFormula="(SELECT d.NumeroPlanoConta from [schema].planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta)")
    private String NumeroPlanoConta;
    
    /** Campos calculado */
    @Ignore
    private String SituacaoTituloAPagar;
    @Ignore
    private BigDecimal ValorCalculado;
    @Ignore
    private BigDecimal ValorMultaCalculado;
    @Ignore
    private BigDecimal ValorJurosCalculado;
    @Ignore
	private BigDecimal ValorDescontoCalculado;
    @Ignore
	private BigDecimal ValorAbertoContaPagar;
    @Ignore
    private List<BaixaContaPagarVO> Baixas;

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

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
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

	public Date getDataLancamentoContaPagar() {
		return DataLancamentoContaPagar;
	}

	public void setDataLancamentoContaPagar(Date dataLancamentoContaPagar) {
		DataLancamentoContaPagar = dataLancamentoContaPagar;
	}

	public String getDescricaoContaPagar() {
		return DescricaoContaPagar;
	}

	public void setDescricaoContaPagar(String descricaoContaPagar) {
		DescricaoContaPagar = descricaoContaPagar;
	}

	public Integer getCodigoPlanoConta() {
		return CodigoPlanoConta;
	}

	public void setCodigoPlanoConta(Integer codigoPlanoConta) {
		CodigoPlanoConta = codigoPlanoConta;
	}

	public Integer getCodigoCentroCusto() {
		return CodigoCentroCusto;
	}

	public void setCodigoCentroCusto(Integer codigoCentroCusto) {
		CodigoCentroCusto = codigoCentroCusto;
	}

	public Integer getCodigoPessoaContaPagar() {
		return CodigoPessoaContaPagar;
	}

	public void setCodigoPessoaContaPagar(Integer codigoPessoaContaPagar) {
		CodigoPessoaContaPagar = codigoPessoaContaPagar;
	}

	public Integer getCodigoEmpresaPessoaContaPagar() {
		return CodigoEmpresaPessoaContaPagar;
	}

	public void setCodigoEmpresaPessoaContaPagar(
			Integer codigoEmpresaPessoaContaPagar) {
		CodigoEmpresaPessoaContaPagar = codigoEmpresaPessoaContaPagar;
	}

	public Integer getCodigoLojaPessoaContaPagar() {
		return CodigoLojaPessoaContaPagar;
	}

	public void setCodigoLojaPessoaContaPagar(Integer codigoLojaPessoaContaPagar) {
		CodigoLojaPessoaContaPagar = codigoLojaPessoaContaPagar;
	}

	public String getNumeroTituloContaPagar() {
		return NumeroTituloContaPagar;
	}

	public void setNumeroTituloContaPagar(String numeroTituloContaPagar) {
		NumeroTituloContaPagar = numeroTituloContaPagar;
	}

	public Integer getCodigoTipoDocumento() {
		return CodigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		CodigoTipoDocumento = codigoTipoDocumento;
	}

	public String getSituacaoContaPagar() {
		return SituacaoContaPagar;
	}

	public void setSituacaoContaPagar(String situacaoContaPagar) {
		SituacaoContaPagar = situacaoContaPagar;
	}

	public Date getDataVencimentoContaPagar() {
		return DataVencimentoContaPagar;
	}

	public void setDataVencimentoContaPagar(Date dataVencimentoContaPagar) {
		DataVencimentoContaPagar = dataVencimentoContaPagar;
	}

	public BigDecimal getValorMultaContaPagar() {
		return ValorMultaContaPagar;
	}

	public void setValorMultaContaPagar(BigDecimal valorMultaContaPagar) {
		ValorMultaContaPagar = valorMultaContaPagar;
	}

	public BigDecimal getPercentualMultaContaPagar() {
		return PercentualMultaContaPagar;
	}

	public void setPercentualMultaContaPagar(BigDecimal percentualMultaContaPagar) {
		PercentualMultaContaPagar = percentualMultaContaPagar;
	}

	public BigDecimal getValorJurosDiarioContaPagar() {
		return ValorJurosDiarioContaPagar;
	}

	public void setValorJurosDiarioContaPagar(BigDecimal valorJurosDiarioContaPagar) {
		ValorJurosDiarioContaPagar = valorJurosDiarioContaPagar;
	}

	public BigDecimal getPercentualJurosDiarioContaPagar() {
		return PercentualJurosDiarioContaPagar;
	}

	public void setPercentualJurosDiarioContaPagar(
			BigDecimal percentualJurosDiarioContaPagar) {
		PercentualJurosDiarioContaPagar = percentualJurosDiarioContaPagar;
	}

	public BigDecimal getValorContaPagar() {
		return ValorContaPagar;
	}

	public void setValorContaPagar(BigDecimal valorContaPagar) {
		ValorContaPagar = valorContaPagar;
	}

	public BigDecimal getDescontoContaPagar() {
		return DescontoContaPagar;
	}

	public void setDescontoContaPagar(BigDecimal descontoContaPagar) {
		DescontoContaPagar = descontoContaPagar;
	}

	public Date getDescontoAteContaPagar() {
		return DescontoAteContaPagar;
	}

	public void setDescontoAteContaPagar(Date descontoAteContaPagar) {
		DescontoAteContaPagar = descontoAteContaPagar;
	}

	public Integer getCodigoPortador() {
		return CodigoPortador;
	}

	public void setCodigoPortador(Integer codigoPortador) {
		CodigoPortador = codigoPortador;
	}

	public String getObservacaoContaPagar() {
		return ObservacaoContaPagar;
	}

	public void setObservacaoContaPagar(String observacaoContaPagar) {
		ObservacaoContaPagar = observacaoContaPagar;
	}

	public void setNomeFornecedorContaPagar(String nomeFornecedorContaPagar) {
		NomeFornecedorContaPagar = nomeFornecedorContaPagar;
	}

	public String getNomeFornecedorContaPagar() {
		return NomeFornecedorContaPagar;
	}

	public void setNomePortador(String nomePortador) {
		NomePortador = nomePortador;
	}

	public String getNomePortador() {
		return NomePortador;
	}

	public void setSituacaoTituloAPagar(String situacaoTituloAPagar) {
		SituacaoTituloAPagar = situacaoTituloAPagar;
	}

	public String getSituacaoTituloAPagar() {
		return SituacaoTituloAPagar;
	}

	public void setValorCalculado(BigDecimal valorCalculado) {
		ValorCalculado = valorCalculado;
	}

	public BigDecimal getValorCalculado() {
		return ValorCalculado;
	}

	public void setNumeroPlanoConta(String numeroPlanoConta) {
		NumeroPlanoConta = numeroPlanoConta;
	}

	public String getNumeroPlanoConta() {
		return NumeroPlanoConta;
	}

	public void setParcelaContaPagar(Integer parcelaContaPagar) {
		ParcelaContaPagar = parcelaContaPagar;
	}

	public Integer getParcelaContaPagar() {
		return ParcelaContaPagar;
	}

	public void setObservacaoCancelamentoContaPagar(
			String observacaoCancelamentoContaPagar) {
		ObservacaoCancelamentoContaPagar = observacaoCancelamentoContaPagar;
	}

	public String getObservacaoCancelamentoContaPagar() {
		return ObservacaoCancelamentoContaPagar;
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

	public void setValorAbertoContaPagar(BigDecimal valorAbertoContaPagar) {
		ValorAbertoContaPagar = valorAbertoContaPagar;
	}

	public BigDecimal getValorAbertoContaPagar() {
		return ValorAbertoContaPagar;
	}

	public void setNumeroParcelasContaPagar(Integer numeroParcelasContaPagar) {
		NumeroParcelasContaPagar = numeroParcelasContaPagar;
	}

	public Integer getNumeroParcelasContaPagar() {
		return NumeroParcelasContaPagar;
	}

	public void setBaixas(List<BaixaContaPagarVO> baixas) {
		Baixas = baixas;
	}

	public List<BaixaContaPagarVO> getBaixas() {
		return Baixas;
	}

	public Date getDataEmissaoContaPagar() {
		return DataEmissaoContaPagar;
	}

	public void setDataEmissaoContaPagar(Date dataEmissaoContaPagar) {
		DataEmissaoContaPagar = dataEmissaoContaPagar;
	}

	public String getFlagDREContaPagar() {
		return FlagDREContaPagar;
	}

	public void setFlagDREContaPagar(String flagDREContaPagar) {
		FlagDREContaPagar = flagDREContaPagar;
	}

	public String getFlagResumoContaPagar() {
		return FlagResumoContaPagar;
	}

	public void setFlagResumoContaPagar(String flagResumoContaPagar) {
		FlagResumoContaPagar = flagResumoContaPagar;
	}
}