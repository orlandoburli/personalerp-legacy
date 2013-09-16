package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiosaidatora;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class RomaneioSaidaToraVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoRomaneio;
	
	private Timestamp DataHoraLancamentoRomaneio;
	private Date DataRomaneio;
	private String ObservacaoRomaneio;
	
	private Integer CodigoEmpresaCliente;
	private Integer CodigoLojaCliente;
	private Integer CodigoPessoaCliente;
	private Integer CodigoEnderecoPessoaCliente;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.NomeRazaoSocialPessoa FROM [schema].Pessoa b WHERE b.CodigoEmpresa = a.CodigoEmpresaCliente AND b.CodigoLoja = a.CodigoLojaCliente AND b.CodigoPessoa = a.CodigoPessoaCliente)")
    private String NomeCliente;
    
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

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

	@Override
	public void setCodigoUsuarioLog(Integer CodigoUsuarioLog) {
		this.CodigoUsuarioLog = CodigoUsuarioLog;
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

	public Timestamp getDataHoraLancamentoRomaneio() {
		return DataHoraLancamentoRomaneio;
	}

	public void setDataHoraLancamentoRomaneio(Timestamp dataHoraLancamentoRomaneio) {
		DataHoraLancamentoRomaneio = dataHoraLancamentoRomaneio;
	}

	public Date getDataRomaneio() {
		return DataRomaneio;
	}

	public void setDataRomaneio(Date dataRomaneio) {
		DataRomaneio = dataRomaneio;
	}

	public String getObservacaoRomaneio() {
		return ObservacaoRomaneio;
	}

	public void setObservacaoRomaneio(String observacaoRomaneio) {
		ObservacaoRomaneio = observacaoRomaneio;
	}

	public Integer getCodigoEmpresaCliente() {
		return CodigoEmpresaCliente;
	}

	public void setCodigoEmpresaCliente(Integer codigoEmpresaCliente) {
		CodigoEmpresaCliente = codigoEmpresaCliente;
	}

	public Integer getCodigoLojaCliente() {
		return CodigoLojaCliente;
	}

	public void setCodigoLojaCliente(Integer codigoLojaCliente) {
		CodigoLojaCliente = codigoLojaCliente;
	}

	public Integer getCodigoPessoaCliente() {
		return CodigoPessoaCliente;
	}

	public void setCodigoPessoaCliente(Integer codigoPessoaCliente) {
		CodigoPessoaCliente = codigoPessoaCliente;
	}

	public Integer getCodigoEnderecoPessoaCliente() {
		return CodigoEnderecoPessoaCliente;
	}

	public void setCodigoEnderecoPessoaCliente(Integer codigoEnderecoPessoaCliente) {
		CodigoEnderecoPessoaCliente = codigoEnderecoPessoaCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}
}