package br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomanejotora;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ItemRomaneioManejoToraVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoRomaneio;
	@Key
	@AutoIncrement
	private Integer CodigoItemRomaneio;
	
	private Integer CodigoEssencia;
	@Precision(decimals=2)
	private BigDecimal DiametroTora;
	@Precision(decimals=2)
	private BigDecimal ComprimentoTora;
	@Precision(decimals=4)
	private BigDecimal VolumeTora;
	
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

	public Integer getCodigoItemRomaneio() {
		return CodigoItemRomaneio;
	}

	public void setCodigoItemRomaneio(Integer codigoItemRomaneio) {
		CodigoItemRomaneio = codigoItemRomaneio;
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

	public void setNomeEssencia(String nomeEssencia) {
		NomeEssencia = nomeEssencia;
	}

	public String getNomeEssencia() {
		return NomeEssencia;
	}
}