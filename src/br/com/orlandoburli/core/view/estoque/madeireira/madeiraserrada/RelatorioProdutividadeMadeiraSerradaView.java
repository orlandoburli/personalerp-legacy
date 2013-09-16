package br.com.orlandoburli.core.view.estoque.madeireira.madeiraserrada;

import java.math.BigDecimal;

import br.com.orlandoburli.core.view.IView;

public class RelatorioProdutividadeMadeiraSerradaView implements IView {

	private static final long serialVersionUID = 1L;

	private Integer CodigoGerente;
	private Integer CodigoEmpresaGerente;
	private Integer CodigoLojaGerente;
	
	private String TipoRomaneio;
	private String StatusRomaneio;
	
	private Integer MesDataRomaneio;
	private Integer AnoDataRomaneio;
	private String NomeFuncionario;
	
	private Integer CodigoEmpresaContratoTrabalho;
	private Integer CodigoLojaContratoTrabalho;
	private Integer CodigoContratoTrabalho;
	
	private BigDecimal VolumeTotalM3;

	public Integer getCodigoGerente() {
		return CodigoGerente;
	}

	public void setCodigoGerente(Integer codigoGerente) {
		CodigoGerente = codigoGerente;
	}

	public Integer getCodigoEmpresaGerente() {
		return CodigoEmpresaGerente;
	}

	public void setCodigoEmpresaGerente(Integer codigoEmpresaGerente) {
		CodigoEmpresaGerente = codigoEmpresaGerente;
	}

	public Integer getCodigoLojaGerente() {
		return CodigoLojaGerente;
	}

	public void setCodigoLojaGerente(Integer codigoLojaGerente) {
		CodigoLojaGerente = codigoLojaGerente;
	}

	public String getTipoRomaneio() {
		return TipoRomaneio;
	}

	public void setTipoRomaneio(String tipoRomaneio) {
		TipoRomaneio = tipoRomaneio;
	}

	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public Integer getMesDataRomaneio() {
		return MesDataRomaneio;
	}

	public void setMesDataRomaneio(Integer mesDataRomaneio) {
		MesDataRomaneio = mesDataRomaneio;
	}

	public Integer getAnoDataRomaneio() {
		return AnoDataRomaneio;
	}

	public void setAnoDataRomaneio(Integer anoDataRomaneio) {
		AnoDataRomaneio = anoDataRomaneio;
	}

	public String getNomeFuncionario() {
		return NomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		NomeFuncionario = nomeFuncionario;
	}

	public Integer getCodigoEmpresaContratoTrabalho() {
		return CodigoEmpresaContratoTrabalho;
	}

	public void setCodigoEmpresaContratoTrabalho(
			Integer codigoEmpresaContratoTrabalho) {
		CodigoEmpresaContratoTrabalho = codigoEmpresaContratoTrabalho;
	}

	public Integer getCodigoLojaContratoTrabalho() {
		return CodigoLojaContratoTrabalho;
	}

	public void setCodigoLojaContratoTrabalho(Integer codigoLojaContratoTrabalho) {
		CodigoLojaContratoTrabalho = codigoLojaContratoTrabalho;
	}

	public Integer getCodigoContratoTrabalho() {
		return CodigoContratoTrabalho;
	}

	public void setCodigoContratoTrabalho(Integer codigoContratoTrabalho) {
		CodigoContratoTrabalho = codigoContratoTrabalho;
	}

	public BigDecimal getVolumeTotalM3() {
		return VolumeTotalM3;
	}

	public void setVolumeTotalM3(BigDecimal volumeTotalM3) {
		VolumeTotalM3 = volumeTotalM3;
	}
}