package br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class RomaneioAbateVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoRomaneio;
	
	private Integer CodigoEmpresaPessoaFornecedor;
	private Integer CodigoLojaPessoaFornecedor;
	private Integer CodigoPessoaFornecedor;
	private Integer CodigoEnderecoPessoaFornecedor;
	
	private Date DataRomaneio;
	private Timestamp DataHoraLancamentoRomaneio;
	private String StatusRomaneio;
	private String StatusNotaRomaneio;
	private String StatusPagamentoRomaneio;
	
	private String ObservacaoRomaneio;
	
	private Integer CodigoEmpresaContaPagar;
	private Integer CodigoLojaContaPagar;
	private Integer CodigoContaPagar;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.NomeUsuario FROM [schema].Usuario b WHERE a.CodigoEmpresaUsuarioLog = b.CodigoEmpresa AND a.CodigoLojaUsuarioLog = b.CodigoLoja AND a.CodigoUsuarioLog = b.CodigoUsuario)")
    private String NomeUsuarioRomaneio;
    
    @Formula(getFormula="(SELECT SUM(" +
    		"			  (b.ValorKgItemRomaneio * b.PesoBanda1ItemRomaneio) + " +
    		"			  (b.ValorKgItemRomaneio * b.PesoBanda2ItemRomaneio)" +
    		"			) FROM [schema].ItemRomaneioAbate b " +
    		"		       WHERE a.CodigoEmpresa = b.CodigoEmpresa" +
    		"                AND a.CodigoLoja = b.CodigoLoja" +
    		"                AND a.CodigoRomaneio = b.CodigoRomaneio" +
    		")")
	private BigDecimal ValorTotalRomaneio;
    
    @Formula(getFormula="(SELECT NomeRazaoSocialPessoa FROM [schema].Pessoa b" +
    		"			   WHERE b.CodigoEmpresa = CodigoEmpresaPessoaFornecedor" +
    		"                AND b.CodigoLoja = CodigoLojaPessoaFornecedor" +
    		"                AND b.CodigoPessoa = a.CodigoPessoaFornecedor" +
    		")")
    private String NomeFornecedor;

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

	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	public Integer getCodigoEmpresaPessoaFornecedor() {
		return CodigoEmpresaPessoaFornecedor;
	}

	public void setCodigoEmpresaPessoaFornecedor(
			Integer codigoEmpresaPessoaFornecedor) {
		CodigoEmpresaPessoaFornecedor = codigoEmpresaPessoaFornecedor;
	}

	public Integer getCodigoLojaPessoaFornecedor() {
		return CodigoLojaPessoaFornecedor;
	}

	public void setCodigoLojaPessoaFornecedor(Integer codigoLojaPessoaFornecedor) {
		CodigoLojaPessoaFornecedor = codigoLojaPessoaFornecedor;
	}

	public Integer getCodigoPessoaFornecedor() {
		return CodigoPessoaFornecedor;
	}

	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaFornecedor = codigoPessoaFornecedor;
	}

	public Integer getCodigoEnderecoPessoaFornecedor() {
		return CodigoEnderecoPessoaFornecedor;
	}

	public void setCodigoEnderecoPessoaFornecedor(
			Integer codigoEnderecoPessoaFornecedor) {
		CodigoEnderecoPessoaFornecedor = codigoEnderecoPessoaFornecedor;
	}

	public Date getDataRomaneio() {
		return DataRomaneio;
	}

	public void setDataRomaneio(Date dataRomaneio) {
		DataRomaneio = dataRomaneio;
	}

	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public String getStatusNotaRomaneio() {
		return StatusNotaRomaneio;
	}

	public void setStatusNotaRomaneio(String statusNotaRomaneio) {
		StatusNotaRomaneio = statusNotaRomaneio;
	}

	public String getStatusPagamentoRomaneio() {
		return StatusPagamentoRomaneio;
	}

	public void setStatusPagamentoRomaneio(String statusPagamentoRomaneio) {
		StatusPagamentoRomaneio = statusPagamentoRomaneio;
	}

	public void setDataHoraLancamentoRomaneio(Timestamp dataHoraLancamentoRomaneio) {
		DataHoraLancamentoRomaneio = dataHoraLancamentoRomaneio;
	}

	public Timestamp getDataHoraLancamentoRomaneio() {
		return DataHoraLancamentoRomaneio;
	}

	public void setObservacaoRomaneio(String observacaoRomaneio) {
		ObservacaoRomaneio = observacaoRomaneio;
	}

	public String getObservacaoRomaneio() {
		return ObservacaoRomaneio;
	}

	public void setNomeUsuarioRomaneio(String nomeUsuarioRomaneio) {
		NomeUsuarioRomaneio = nomeUsuarioRomaneio;
	}

	public String getNomeUsuarioRomaneio() {
		return NomeUsuarioRomaneio;
	}

	public void setCodigoEmpresaContaPagar(Integer codigoEmpresaContaPagar) {
		CodigoEmpresaContaPagar = codigoEmpresaContaPagar;
	}

	public Integer getCodigoEmpresaContaPagar() {
		return CodigoEmpresaContaPagar;
	}

	public void setCodigoLojaContaPagar(Integer codigoLojaContaPagar) {
		CodigoLojaContaPagar = codigoLojaContaPagar;
	}

	public Integer getCodigoLojaContaPagar() {
		return CodigoLojaContaPagar;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
	}

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setValorTotalRomaneio(BigDecimal valorTotalRomaneio) {
		ValorTotalRomaneio = valorTotalRomaneio;
	}

	public BigDecimal getValorTotalRomaneio() {
		return ValorTotalRomaneio;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		NomeFornecedor = nomeFornecedor;
	}

	public String getNomeFornecedor() {
		return NomeFornecedor;
	}
}