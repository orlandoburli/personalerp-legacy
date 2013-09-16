package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class ItemRomaneioToraVO implements IValueObject {
	
	@Ignore
	/**
	 * Constante usada para calculo da volumetria da tora.
	 * Diametro (cm) X Comprimento (m) X 0.7854 / 10000 = Volume em m³ (metros cubicos)
	 * */
	public static final BigDecimal CONSTANTE_VOLUME_TORA = new BigDecimal(0.00007854).setScale(8, BigDecimal.ROUND_CEILING);
	
	private static final long serialVersionUID = 1L;
	
	private boolean isNew;
	
	@Key
	private Integer CodigoRomaneio;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer NumeroPlaquetaRomaneio;
	
	private Integer CodigoEssencia;
	private BigDecimal DiametroTora;
	private BigDecimal ComprimentoTora;
	private BigDecimal VolumeTora;
	private String FlagBaixaTora;
	private Timestamp DataHoraBaixaRomaneio;
	private Date DataBaixaItemRomaneio;
	
	private Integer CodigoEmpresaUsuarioBaixaRomaneio;
	private Integer CodigoLojaUsuarioBaixaRomaneio;
	private Integer CodigoUsuarioBaixaRomaneio;
	
	private Integer CodigoEmpresaGerente;
	private Integer CodigoLojaGerente;
	private Integer CodigoGerente;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.NomeEssencia FROM [schema].Essencia b WHERE a.CodigoEssencia = b.CodigoEssencia)")
    private String NomeEssencia;
	
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

	public Integer getNumeroPlaquetaRomaneio() {
		return NumeroPlaquetaRomaneio;
	}

	public void setNumeroPlaquetaRomaneio(Integer numeroPlaquetaRomaneio) {
		NumeroPlaquetaRomaneio = numeroPlaquetaRomaneio;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}

	public BigDecimal getDiametroTora() {
		return DiametroTora;
	}

	public void setDiametroTora(BigDecimal diametroTora) {
		DiametroTora = diametroTora;
	}

	public BigDecimal getComprimentoTora() {
		return ComprimentoTora;
	}

	public void setComprimentoTora(BigDecimal comprimentoTora) {
		ComprimentoTora = comprimentoTora;
	}

	public BigDecimal getVolumeTora() {
		return VolumeTora;
	}

	public void setVolumeTora(BigDecimal volumeTora) {
		VolumeTora = volumeTora;
	}

	public String getFlagBaixaTora() {
		return FlagBaixaTora;
	}

	public void setFlagBaixaTora(String flagBaixaTora) {
		FlagBaixaTora = flagBaixaTora;
	}

	public Timestamp getDataHoraBaixaRomaneio() {
		return DataHoraBaixaRomaneio;
	}

	public void setDataHoraBaixaRomaneio(Timestamp dataHoraBaixaRomaneio) {
		DataHoraBaixaRomaneio = dataHoraBaixaRomaneio;
	}

	public Integer getCodigoEmpresaUsuarioBaixaRomaneio() {
		return CodigoEmpresaUsuarioBaixaRomaneio;
	}

	public void setCodigoEmpresaUsuarioBaixaRomaneio(
			Integer codigoEmpresaUsuarioBaixaRomaneio) {
		CodigoEmpresaUsuarioBaixaRomaneio = codigoEmpresaUsuarioBaixaRomaneio;
	}

	public Integer getCodigoLojaUsuarioBaixaRomaneio() {
		return CodigoLojaUsuarioBaixaRomaneio;
	}

	public void setCodigoLojaUsuarioBaixaRomaneio(
			Integer codigoLojaUsuarioBaixaRomaneio) {
		CodigoLojaUsuarioBaixaRomaneio = codigoLojaUsuarioBaixaRomaneio;
	}

	public Integer getCodigoUsuarioBaixaRomaneio() {
		return CodigoUsuarioBaixaRomaneio;
	}

	public void setCodigoUsuarioBaixaRomaneio(Integer codigoUsuarioBaixaRomaneio) {
		CodigoUsuarioBaixaRomaneio = codigoUsuarioBaixaRomaneio;
	}

	public void setNomeEssencia(String nomeEssencia) {
		NomeEssencia = nomeEssencia;
	}

	public String getNomeEssencia() {
		return NomeEssencia;
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

	public void setDataBaixaItemRomaneio(Date dataBaixaItemRomaneio) {
		DataBaixaItemRomaneio = dataBaixaItemRomaneio;
	}

	public Date getDataBaixaItemRomaneio() {
		return DataBaixaItemRomaneio;
	}
}