package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class RomaneioToraVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoRomaneio;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	
	private Integer NumeroRomaneio;
	private Timestamp DataHoraLancamentoRomaneio;
	private Date DataRomaneio;
	
	private Integer CodigoEmpresaFornecedor;
	private Integer CodigoLojaFornecedor;
	private Integer CodigoPessoaFornecedor;
	
	private String ObservacaoRomaneio;
	
	private Integer CodigoEmpresaMotorista;
	private Integer CodigoLojaMotorista;
	private Integer CodigoMotorista;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.NomeRazaoSocialPessoa FROM [schema].Pessoa b WHERE b.CodigoEmpresa = a.CodigoEmpresaFornecedor AND b.CodigoLoja = a.CodigoLojaFornecedor AND b.CodigoPessoa = a.CodigoPessoaFornecedor)")
    private String NomeFornecedor;
	
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

	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
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

	public Integer getNumeroRomaneio() {
		return NumeroRomaneio;
	}

	public void setNumeroRomaneio(Integer numeroRomaneio) {
		NumeroRomaneio = numeroRomaneio;
	}

	public Timestamp getDataHoraLancamentoRomaneio() {
		return DataHoraLancamentoRomaneio;
	}

	public void setDataHoraLancamentoRomaneio(Timestamp dataHoraLancamentoRomaneio) {
		DataHoraLancamentoRomaneio = dataHoraLancamentoRomaneio;
	}

	public Integer getCodigoEmpresaFornecedor() {
		return CodigoEmpresaFornecedor;
	}

	public void setCodigoEmpresaFornecedor(Integer codigoEmpresaFornecedor) {
		CodigoEmpresaFornecedor = codigoEmpresaFornecedor;
	}

	public Integer getCodigoLojaFornecedor() {
		return CodigoLojaFornecedor;
	}

	public void setCodigoLojaFornecedor(Integer codigoLojaFornecedor) {
		CodigoLojaFornecedor = codigoLojaFornecedor;
	}

	public Integer getCodigoPessoaFornecedor() {
		return CodigoPessoaFornecedor;
	}

	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaFornecedor = codigoPessoaFornecedor;
	}

	public String getObservacaoRomaneio() {
		return ObservacaoRomaneio;
	}

	public void setObservacaoRomaneio(String observacaoRomaneio) {
		ObservacaoRomaneio = observacaoRomaneio;
	}

	public Integer getCodigoEmpresaMotorista() {
		return CodigoEmpresaMotorista;
	}

	public void setCodigoEmpresaMotorista(Integer codigoEmpresaMotorista) {
		CodigoEmpresaMotorista = codigoEmpresaMotorista;
	}

	public Integer getCodigoLojaMotorista() {
		return CodigoLojaMotorista;
	}

	public void setCodigoLojaMotorista(Integer codigoLojaMotorista) {
		CodigoLojaMotorista = codigoLojaMotorista;
	}

	public Integer getCodigoMotorista() {
		return CodigoMotorista;
	}

	public void setCodigoMotorista(Integer codigoMotorista) {
		CodigoMotorista = codigoMotorista;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		NomeFornecedor = nomeFornecedor;
	}

	public String getNomeFornecedor() {
		return NomeFornecedor;
	}

	public void setDataRomaneio(Date dataRomaneio) {
		DataRomaneio = dataRomaneio;
	}

	public Date getDataRomaneio() {
		return DataRomaneio;
	}

}
