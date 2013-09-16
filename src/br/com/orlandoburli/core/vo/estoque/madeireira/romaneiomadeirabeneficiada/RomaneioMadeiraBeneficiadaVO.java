package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeirabeneficiada;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class RomaneioMadeiraBeneficiadaVO implements IValueObject {

	@Ignore
	public static final String STATUS_ROMANEIO_NAOPROCESSADO = "N";
	@Ignore
	public static final String STATUS_ROMANEIO_PROCESSADO  = "P";
	@Ignore
	public static final String STATUS_ROMANEIO_ESTORNADO = "E";
	
	@Ignore
	public static final String TIPOROMANEIO_ENTRADA = "+";
	@Ignore
	public static final String TIPOROMANEIO_SAIDA = "-";
	@Ignore
	public static final String TIPOROMANEIO_INVENTARIO = "I";
	
	private static final long serialVersionUID = 1L;
	
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoRomaneio;
	private Date DataRomaneio;
	private Timestamp DataHoraLancamentoRomaneio;
	private Timestamp DataHoraProcessamentoRomaneio;
	private String TipoRomaneio;
	private String ObservacaoRomaneio;
	
	private Integer CodigoEmpresaUsuarioProcessamentoRomaneio;
    private Integer CodigoLojaUsuarioProcessamentoRomaneio;
    private Integer CodigoUsuarioProcessamentoRomaneio;
    
    private Integer CodigoEmpresaGerente;
    private Integer CodigoLojaGerente;
    private Integer CodigoGerente;
    
    private Integer CodigoEmpresaEnderecoPessoaCliente;
    private Integer CodigoLojaEnderecoPessoaCliente;
    private Integer CodigoPessoaCliente;
    private Integer CodigoEnderecoPessoaCliente;
    
    private Integer CodigoEmpresaEnderecoPessoaTransportadora;
    private Integer CodigoLojaEnderecoPessoaTransportadora;
    private Integer CodigoPessoaTransportadora;
    private Integer CodigoEnderecoPessoaTransportadora;
    
    private Integer CodigoEmpresaMotorista;
    private Integer CodigoLojaMotorista;
    private Integer CodigoMotorista;
    
    private String PlacaPrincipalRomaneio;
    private String UFPlacaPrincipalRomaneio;
    private String PlacasReboqueRomaneio;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    private String StatusRomaneio;
    
    @Formula(getFormula="(SELECT b.NomeUsuario FROM [schema].Usuario b WHERE a.CodigoEmpresaUsuarioLog = b.CodigoEmpresa AND a.CodigoLojaUsuarioLog = b.CodigoLoja AND a.CodigoUsuarioLog = b.CodigoUsuario)")
    private String NomeUsuarioRomaneio;
    
    @Formula(getFormula="(SELECT c.NomeGerente FROM [schema].GerenteMadeireira c WHERE c.CodigoEmpresa = a.CodigoEmpresaGerente AND c.CodigoLoja = a.CodigoLojaGerente AND c.CodigoGerente = a.CodigoGerente)")
    private String NomeGerente;

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

	public String getTipoRomaneio() {
		return TipoRomaneio;
	}

	public void setTipoRomaneio(String tipoRomaneio) {
		TipoRomaneio = tipoRomaneio;
	}

	public void setDataRomaneio(Date dataRomaneio) {
		DataRomaneio = dataRomaneio;
	}

	public Date getDataRomaneio() {
		return DataRomaneio;
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

	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	public void setDataHoraProcessamentoRomaneio(
			Timestamp dataHoraProcessamentoRomaneio) {
		DataHoraProcessamentoRomaneio = dataHoraProcessamentoRomaneio;
	}

	public Timestamp getDataHoraProcessamentoRomaneio() {
		return DataHoraProcessamentoRomaneio;
	}

	public void setCodigoEmpresaUsuarioProcessamentoRomaneio(
			Integer codigoEmpresaUsuarioProcessamentoRomaneio) {
		CodigoEmpresaUsuarioProcessamentoRomaneio = codigoEmpresaUsuarioProcessamentoRomaneio;
	}

	public Integer getCodigoEmpresaUsuarioProcessamentoRomaneio() {
		return CodigoEmpresaUsuarioProcessamentoRomaneio;
	}

	public void setCodigoLojaUsuarioProcessamentoRomaneio(
			Integer codigoLojaUsuarioProcessamentoRomaneio) {
		CodigoLojaUsuarioProcessamentoRomaneio = codigoLojaUsuarioProcessamentoRomaneio;
	}

	public Integer getCodigoLojaUsuarioProcessamentoRomaneio() {
		return CodigoLojaUsuarioProcessamentoRomaneio;
	}

	public void setCodigoUsuarioProcessamentoRomaneio(
			Integer codigoUsuarioProcessamentoRomaneio) {
		CodigoUsuarioProcessamentoRomaneio = codigoUsuarioProcessamentoRomaneio;
	}

	public Integer getCodigoUsuarioProcessamentoRomaneio() {
		return CodigoUsuarioProcessamentoRomaneio;
	}

	public void setCodigoEmpresaGerente(Integer codigoEmpresaGerente) {
		CodigoEmpresaGerente = codigoEmpresaGerente;
	}

	public Integer getCodigoEmpresaGerente() {
		return CodigoEmpresaGerente;
	}

	public void setCodigoLojaGerente(Integer codigoLojaGerente) {
		CodigoLojaGerente = codigoLojaGerente;
	}

	public Integer getCodigoLojaGerente() {
		return CodigoLojaGerente;
	}

	public void setCodigoGerente(Integer codigoGerente) {
		CodigoGerente = codigoGerente;
	}

	public Integer getCodigoGerente() {
		return CodigoGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		NomeGerente = nomeGerente;
	}

	public String getNomeGerente() {
		return NomeGerente;
	}

	public Integer getCodigoEmpresaEnderecoPessoaCliente() {
		return CodigoEmpresaEnderecoPessoaCliente;
	}

	public void setCodigoEmpresaEnderecoPessoaCliente(
			Integer codigoEmpresaEnderecoPessoaCliente) {
		CodigoEmpresaEnderecoPessoaCliente = codigoEmpresaEnderecoPessoaCliente;
	}

	public Integer getCodigoLojaEnderecoPessoaCliente() {
		return CodigoLojaEnderecoPessoaCliente;
	}

	public void setCodigoLojaEnderecoPessoaCliente(
			Integer codigoLojaEnderecoPessoaCliente) {
		CodigoLojaEnderecoPessoaCliente = codigoLojaEnderecoPessoaCliente;
	}

	public Integer getCodigoEnderecoPessoaCliente() {
		return CodigoEnderecoPessoaCliente;
	}

	public void setCodigoEnderecoPessoaCliente(Integer codigoEnderecoPessoaCliente) {
		CodigoEnderecoPessoaCliente = codigoEnderecoPessoaCliente;
	}

	public Integer getCodigoEmpresaEnderecoPessoaTransportadora() {
		return CodigoEmpresaEnderecoPessoaTransportadora;
	}

	public void setCodigoEmpresaEnderecoPessoaTransportadora(
			Integer codigoEmpresaEnderecoPessoaTransportadora) {
		CodigoEmpresaEnderecoPessoaTransportadora = codigoEmpresaEnderecoPessoaTransportadora;
	}

	public Integer getCodigoLojaEnderecoPessoaTransportadora() {
		return CodigoLojaEnderecoPessoaTransportadora;
	}

	public void setCodigoLojaEnderecoPessoaTransportadora(
			Integer codigoLojaEnderecoPessoaTransportadora) {
		CodigoLojaEnderecoPessoaTransportadora = codigoLojaEnderecoPessoaTransportadora;
	}

	public Integer getCodigoEnderecoPessoaTransportadora() {
		return CodigoEnderecoPessoaTransportadora;
	}

	public void setCodigoEnderecoPessoaTransportadora(
			Integer codigoEnderecoPessoaTransportadora) {
		CodigoEnderecoPessoaTransportadora = codigoEnderecoPessoaTransportadora;
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

	public String getPlacaPrincipalRomaneio() {
		return PlacaPrincipalRomaneio;
	}

	public void setPlacaPrincipalRomaneio(String placaPrincipalRomaneio) {
		PlacaPrincipalRomaneio = placaPrincipalRomaneio;
	}

	public String getPlacasReboqueRomaneio() {
		return PlacasReboqueRomaneio;
	}

	public void setPlacasReboqueRomaneio(String placasReboqueRomaneio) {
		PlacasReboqueRomaneio = placasReboqueRomaneio;
	}

	public void setCodigoPessoaCliente(Integer codigoPessoaCliente) {
		CodigoPessoaCliente = codigoPessoaCliente;
	}

	public Integer getCodigoPessoaCliente() {
		return CodigoPessoaCliente;
	}

	public void setCodigoPessoaTransportadora(Integer codigoPessoaTransportadora) {
		CodigoPessoaTransportadora = codigoPessoaTransportadora;
	}

	public Integer getCodigoPessoaTransportadora() {
		return CodigoPessoaTransportadora;
	}

	public void setUFPlacaPrincipalRomaneio(String uFPlacaPrincipalRomaneio) {
		UFPlacaPrincipalRomaneio = uFPlacaPrincipalRomaneio;
	}

	public String getUFPlacaPrincipalRomaneio() {
		return UFPlacaPrincipalRomaneio;
	}
}
