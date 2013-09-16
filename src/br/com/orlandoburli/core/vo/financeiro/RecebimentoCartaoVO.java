package br.com.orlandoburli.core.vo.financeiro;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class RecebimentoCartaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoRecebimentoCartao;
	
	private Timestamp DataHoraRecebimentoCartao;
	private String ReciboRecebimentoCartao;
	private BigDecimal ValorRecebimentoCartao;
	private String TipoRecebimentoCartao;
	
	private Integer CodigoBandeira;
	private Integer CodigoOperadoraCartao;
	
	private Integer ParcelasRecebimentoCartao;
	
	private Integer CodigoEmpresaRecebimento;
	private Integer CodigoLojaRecebimento;
	private Integer CodigoRecebimento;
	
	private Integer CodigoEmpresaEmitente;
	private Integer CodigoLojaEmitente;
	private Integer CodigoPessoaEmitente;
	private Integer CodigoEnderecoPessoaEmitente;
	
	private String StatusRecebimentoCartao;
	private String MensagemRecebimentoCartao;
	
	private Integer CodigoContaBancaria;
	private Integer CodigoEmpresaContaBancaria;
	private Integer CodigoLojaContaBancaria;
	private Integer SequencialMovimentacaoContaBancaria;
	
	@Formula(getFormula="(SELECT b.NomeOperadoraCartao FROM [schema].OperadoraCartao b WHERE b.CodigoOperadoraCartao = a.CodigoOperadoraCartao)")
	private String NomeOperadora;
	@Formula(getFormula="(SELECT b.NomeBandeira FROM [schema].BandeiraCartao b WHERE b.CodigoBandeira = a.CodigoBandeira)")
	private String NomeBandeira;
	
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

	public Integer getCodigoRecebimentoCartao() {
		return CodigoRecebimentoCartao;
	}

	public void setCodigoRecebimentoCartao(Integer codigoRecebimentoCartao) {
		CodigoRecebimentoCartao = codigoRecebimentoCartao;
	}

	public Timestamp getDataHoraRecebimentoCartao() {
		return DataHoraRecebimentoCartao;
	}

	public void setDataHoraRecebimentoCartao(Timestamp dataHoraRecebimentoCartao) {
		DataHoraRecebimentoCartao = dataHoraRecebimentoCartao;
	}

	public String getReciboRecebimentoCartao() {
		return ReciboRecebimentoCartao;
	}

	public void setReciboRecebimentoCartao(String reciboRecebimentoCartao) {
		ReciboRecebimentoCartao = reciboRecebimentoCartao;
	}

	public BigDecimal getValorRecebimentoCartao() {
		return ValorRecebimentoCartao;
	}

	public void setValorRecebimentoCartao(BigDecimal valorRecebimentoCartao) {
		ValorRecebimentoCartao = valorRecebimentoCartao;
	}

	public String getTipoRecebimentoCartao() {
		return TipoRecebimentoCartao;
	}

	public void setTipoRecebimentoCartao(String tipoRecebimentoCartao) {
		TipoRecebimentoCartao = tipoRecebimentoCartao;
	}

	public Integer getCodigoBandeira() {
		return CodigoBandeira;
	}

	public void setCodigoBandeira(Integer codigoBandeira) {
		CodigoBandeira = codigoBandeira;
	}

	public Integer getCodigoOperadoraCartao() {
		return CodigoOperadoraCartao;
	}

	public void setCodigoOperadoraCartao(Integer codigoOperadoraCartao) {
		CodigoOperadoraCartao = codigoOperadoraCartao;
	}

	public Integer getParcelasRecebimentoCartao() {
		return ParcelasRecebimentoCartao;
	}

	public void setParcelasRecebimentoCartao(Integer parcelasRecebimentoCartao) {
		ParcelasRecebimentoCartao = parcelasRecebimentoCartao;
	}

	public Integer getCodigoEmpresaRecebimento() {
		return CodigoEmpresaRecebimento;
	}

	public void setCodigoEmpresaRecebimento(Integer codigoEmpresaRecebimento) {
		CodigoEmpresaRecebimento = codigoEmpresaRecebimento;
	}

	public Integer getCodigoLojaRecebimento() {
		return CodigoLojaRecebimento;
	}

	public void setCodigoLojaRecebimento(Integer codigoLojaRecebimento) {
		CodigoLojaRecebimento = codigoLojaRecebimento;
	}

	public Integer getCodigoRecebimento() {
		return CodigoRecebimento;
	}

	public void setCodigoRecebimento(Integer codigoRecebimento) {
		CodigoRecebimento = codigoRecebimento;
	}

	public Integer getCodigoEmpresaEmitente() {
		return CodigoEmpresaEmitente;
	}

	public void setCodigoEmpresaEmitente(Integer codigoEmpresaEmitente) {
		CodigoEmpresaEmitente = codigoEmpresaEmitente;
	}

	public Integer getCodigoLojaEmitente() {
		return CodigoLojaEmitente;
	}

	public void setCodigoLojaEmitente(Integer codigoLojaEmitente) {
		CodigoLojaEmitente = codigoLojaEmitente;
	}

	public Integer getCodigoPessoaEmitente() {
		return CodigoPessoaEmitente;
	}

	public void setCodigoPessoaEmitente(Integer codigoPessoaEmitente) {
		CodigoPessoaEmitente = codigoPessoaEmitente;
	}

	public Integer getCodigoEnderecoPessoaEmitente() {
		return CodigoEnderecoPessoaEmitente;
	}

	public void setCodigoEnderecoPessoaEmitente(Integer codigoEnderecoPessoaEmitente) {
		CodigoEnderecoPessoaEmitente = codigoEnderecoPessoaEmitente;
	}

	public String getStatusRecebimentoCartao() {
		return StatusRecebimentoCartao;
	}

	public void setStatusRecebimentoCartao(String statusRecebimentoCartao) {
		StatusRecebimentoCartao = statusRecebimentoCartao;
	}

	public String getMensagemRecebimentoCartao() {
		return MensagemRecebimentoCartao;
	}

	public void setMensagemRecebimentoCartao(String mensagemRecebimentoCartao) {
		MensagemRecebimentoCartao = mensagemRecebimentoCartao;
	}

	public Integer getCodigoContaBancaria() {
		return CodigoContaBancaria;
	}

	public void setCodigoContaBancaria(Integer codigoContaBancaria) {
		CodigoContaBancaria = codigoContaBancaria;
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

	public Integer getSequencialMovimentacaoContaBancaria() {
		return SequencialMovimentacaoContaBancaria;
	}

	public void setSequencialMovimentacaoContaBancaria(
			Integer sequencialMovimentacaoContaBancaria) {
		SequencialMovimentacaoContaBancaria = sequencialMovimentacaoContaBancaria;
	}

	public void setNomeOperadora(String nomeOperadora) {
		NomeOperadora = nomeOperadora;
	}

	public String getNomeOperadora() {
		return NomeOperadora;
	}

	public void setNomeBandeira(String nomeBandeira) {
		NomeBandeira = nomeBandeira;
	}

	public String getNomeBandeira() {
		return NomeBandeira;
	}
}