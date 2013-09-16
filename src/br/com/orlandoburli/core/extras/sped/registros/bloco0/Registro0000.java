package br.com.orlandoburli.core.extras.sped.registros.bloco0;

import java.sql.Date;

import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatDateSped;

public class Registro0000 extends RegistroSped implements Bloco0 {

	private String versao;
	private String finalidade;
	private Date dataInicial;
	private Date dataFinal;
	private String nomeEntidade;
	private String cnpjEntidade;
	private String cpfEntidade;
	private String uf;
	private String ie;
	private String im;
	private Integer codigoMunicipio;
	private String suframa;
	private String perfil;
	private Integer indicadorAtividade;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "0000";
	}

	@CampoSped(name = "COD_VER", order = 2)
	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	@CampoSped(name = "COD_FIN", order = 3)
	public String getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(String finalidade) {
		this.finalidade = finalidade;
	}

	@CampoSped(name = "DT_INI", order = 4)
	@FormatDateSped(ArquivoSpedFiscal.FORMATO_DATA_SPED)
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	@CampoSped(name = "DT_FIN", order = 5)
	@FormatDateSped(ArquivoSpedFiscal.FORMATO_DATA_SPED)
	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	@CampoSped(name = "NOME", order = 6)
	public String getNomeEntidade() {
		return nomeEntidade;
	}

	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}

	@CampoSped(name = "CNPJ", order = 7)
	public String getCnpjEntidade() {
		return cnpjEntidade;
	}

	public void setCnpjEntidade(String cnpjEntidade) {
		this.cnpjEntidade = cnpjEntidade;
	}

	@CampoSped(name = "CPF", order = 8)
	public String getCpfEntidade() {
		return cpfEntidade;
	}

	public void setCpfEntidade(String cpfEntidade) {
		this.cpfEntidade = cpfEntidade;
	}

	@CampoSped(name = "UF", order = 9)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@CampoSped(name = "IE", order = 10)
	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	@CampoSped(name = "IM", order = 12)
	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	@CampoSped(name = "COD_MUN", order = 11)
	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	@CampoSped(name = "SUFRAMA", order = 13)
	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}

	@CampoSped(name = "IND_PERFIL", order = 14)
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@CampoSped(name = "IND_ATIV", order = 15)
	public Integer getIndicadorAtividade() {
		return indicadorAtividade;
	}

	public void setIndicadorAtividade(Integer indicadorAtividade) {
		this.indicadorAtividade = indicadorAtividade;
	}
}