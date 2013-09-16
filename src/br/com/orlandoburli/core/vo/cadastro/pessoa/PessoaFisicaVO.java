package br.com.orlandoburli.core.vo.cadastro.pessoa;

import java.sql.Date;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class PessoaFisicaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoPessoaFisica;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	private Date DataNascimentoPessoaFisica;
	private String RgPessoaFisica;
	private String NomeMaePessoaFisica;
	private String NomePaiPessoaFisica;
	private String SexoPessoaFisica;
	private String LocalTrabalhoPessoaFisica;
	private Date DataAdmissaoPessoaFisica;
	private String CargoPessoaFisica;
	private Double RendimentosPessoaFisica;
	private String NomeConjuguePessoaFisica;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoPessoaFisica(Integer codigoPessoaFisica) {
		CodigoPessoaFisica = codigoPessoaFisica;
	}

	public Integer getCodigoPessoaFisica() {
		return CodigoPessoaFisica;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setDataNascimentoPessoaFisica(Date dataNascimentoPessoaFisica) {
		DataNascimentoPessoaFisica = dataNascimentoPessoaFisica;
	}

	public Date getDataNascimentoPessoaFisica() {
		return DataNascimentoPessoaFisica;
	}

	public void setRgPessoaFisica(String rgPessoaFisica) {
		RgPessoaFisica = rgPessoaFisica;
	}

	public String getRgPessoaFisica() {
		return RgPessoaFisica;
	}

	public void setNomeMaePessoaFisica(String nomeMaePessoaFisica) {
		NomeMaePessoaFisica = nomeMaePessoaFisica;
	}

	public String getNomeMaePessoaFisica() {
		return NomeMaePessoaFisica;
	}

	public void setNomePaiPessoaFisica(String nomePaiPessoaFisica) {
		NomePaiPessoaFisica = nomePaiPessoaFisica;
	}

	public String getNomePaiPessoaFisica() {
		return NomePaiPessoaFisica;
	}

	public void setSexoPessoaFisica(String sexoPessoaFisica) {
		SexoPessoaFisica = sexoPessoaFisica;
	}

	public String getSexoPessoaFisica() {
		return SexoPessoaFisica;
	}

	public void setLocalTrabalhoPessoaFisica(String localTrabalhoPessoaFisica) {
		LocalTrabalhoPessoaFisica = localTrabalhoPessoaFisica;
	}

	public String getLocalTrabalhoPessoaFisica() {
		return LocalTrabalhoPessoaFisica;
	}

	public void setDataAdmissaoPessoaFisica(Date dataAdmissaoPessoaFisica) {
		DataAdmissaoPessoaFisica = dataAdmissaoPessoaFisica;
	}

	public Date getDataAdmissaoPessoaFisica() {
		return DataAdmissaoPessoaFisica;
	}

	public void setCargoPessoaFisica(String cargoPessoaFisica) {
		CargoPessoaFisica = cargoPessoaFisica;
	}

	public String getCargoPessoaFisica() {
		return CargoPessoaFisica;
	}

	public void setRendimentosPessoaFisica(Double rendimentosPessoaFisica) {
		RendimentosPessoaFisica = rendimentosPessoaFisica;
	}

	public Double getRendimentosPessoaFisica() {
		return RendimentosPessoaFisica;
	}

	public void setNomeConjuguePessoaFisica(String nomeConjuguePessoaFisica) {
		NomeConjuguePessoaFisica = nomeConjuguePessoaFisica;
	}

	public String getNomeConjuguePessoaFisica() {
		return NomeConjuguePessoaFisica;
	}
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
	
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
	
}