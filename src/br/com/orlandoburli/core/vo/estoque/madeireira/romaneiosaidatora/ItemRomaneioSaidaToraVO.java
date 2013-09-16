package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiosaidatora;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ItemRomaneioSaidaToraVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoRomaneio;
	@Key
	private Integer NumeroPlaquetaRomaneio;
	@Key
	private Integer CodigoEmpresaItemRomaneio;
	@Key
	private Integer CodigoLojaItemRomaneio;
	@Key
	private Integer CodigoItemRomaneio;
	
	@Formula(getFormula="(SELECT b.DiametroTora FROM [schema].ItemRomaneioTora b WHERE b.CodigoEmpresa = a.CodigoEmpresaItemRomaneio AND b.CodigoLoja = a.CodigoLojaItemRomaneio AND b.CodigoRomaneio = a.CodigoItemRomaneio AND b.NumeroPlaquetaRomaneio = a.NumeroPlaquetaRomaneio)")
	private BigDecimal DiametroTora;
	@Formula(getFormula="(SELECT ComprimentoTora FROM [schema].ItemRomaneioTora b WHERE b.CodigoEmpresa = a.CodigoEmpresaItemRomaneio AND b.CodigoLoja = a.CodigoLojaItemRomaneio AND b.CodigoRomaneio = a.CodigoItemRomaneio AND b.NumeroPlaquetaRomaneio = a.NumeroPlaquetaRomaneio)")
	private BigDecimal ComprimentoTora;
	@Formula(getFormula="(SELECT VolumeTora FROM [schema].ItemRomaneioTora b WHERE b.CodigoEmpresa = a.CodigoEmpresaItemRomaneio AND b.CodigoLoja = a.CodigoLojaItemRomaneio AND b.CodigoRomaneio = a.CodigoItemRomaneio AND b.NumeroPlaquetaRomaneio = a.NumeroPlaquetaRomaneio)")
	private BigDecimal VolumeTora;
	
	@Formula(getFormula="(SELECT c.NomeEssencia FROM [schema].ItemRomaneioTora b JOIN [schema].Essencia c ON b.CodigoEssencia = c.CodigoEssencia WHERE b.CodigoEmpresa = a.CodigoEmpresaItemRomaneio AND b.CodigoLoja = a.CodigoLojaItemRomaneio AND b.CodigoRomaneio = a.CodigoItemRomaneio AND b.NumeroPlaquetaRomaneio = a.NumeroPlaquetaRomaneio)")
    private String NomeEssencia;
	
	@Formula(getFormula="(SELECT b.CodigoEssencia FROM [schema].ItemRomaneioTora b WHERE b.CodigoEmpresa = a.CodigoEmpresaItemRomaneio AND b.CodigoLoja = a.CodigoLojaItemRomaneio AND b.CodigoRomaneio = a.CodigoItemRomaneio AND b.NumeroPlaquetaRomaneio = a.NumeroPlaquetaRomaneio)")
	private Integer CodigoEssencia;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
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

	public Integer getNumeroPlaquetaRomaneio() {
		return NumeroPlaquetaRomaneio;
	}

	public void setNumeroPlaquetaRomaneio(Integer numeroPlaquetaRomaneio) {
		NumeroPlaquetaRomaneio = numeroPlaquetaRomaneio;
	}

	public Integer getCodigoEmpresaItemRomaneio() {
		return CodigoEmpresaItemRomaneio;
	}

	public void setCodigoEmpresaItemRomaneio(Integer codigoEmpresaItemRomaneio) {
		CodigoEmpresaItemRomaneio = codigoEmpresaItemRomaneio;
	}

	public Integer getCodigoLojaItemRomaneio() {
		return CodigoLojaItemRomaneio;
	}

	public void setCodigoLojaItemRomaneio(Integer codigoLojaItemRomaneio) {
		CodigoLojaItemRomaneio = codigoLojaItemRomaneio;
	}

	public Integer getCodigoItemRomaneio() {
		return CodigoItemRomaneio;
	}

	public void setCodigoItemRomaneio(Integer codigoItemRomaneio) {
		CodigoItemRomaneio = codigoItemRomaneio;
	}

	public void setDiametroTora(BigDecimal diametroTora) {
		DiametroTora = diametroTora;
	}

	public BigDecimal getDiametroTora() {
		return DiametroTora;
	}

	public void setComprimentoTora(BigDecimal comprimentoTora) {
		ComprimentoTora = comprimentoTora;
	}

	public BigDecimal getComprimentoTora() {
		return ComprimentoTora;
	}

	public void setVolumeTora(BigDecimal volumeTora) {
		VolumeTora = volumeTora;
	}

	public BigDecimal getVolumeTora() {
		return VolumeTora;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setNomeEssencia(String nomeEssencia) {
		NomeEssencia = nomeEssencia;
	}

	public String getNomeEssencia() {
		return NomeEssencia;
	}
}