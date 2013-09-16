package br.com.orlandoburli.core.extras.sped.registros.bloco0;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class Registro0150 extends RegistroSped implements Bloco0 {

	private String codigoParticipante;
	private String nome;
	private Integer codigoPais;
	private String cnpj;
	private String cpf;
	private String ie;
	private Integer codigoMunicipio;
	private String suframa;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "0150";
	}

	@CampoSped(name = "COD_PART", order = 2)
	public String getCodigoParticipante() {
		return codigoParticipante;
	}

	public void setCodigoParticipante(String codigoParticipante) {
		this.codigoParticipante = codigoParticipante;
	}

	@CampoSped(name = "NOME", order = 3)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@CampoSped(name = "COD_PAIS", order = 4)
	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
	}

	@CampoSped(name = "CNPJ", order = 5)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@CampoSped(name = "CPF", order = 6)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@CampoSped(name = "IE", order = 7)
	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	@CampoSped(name = "COD_MUN", order = 8)
	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	@CampoSped(name = "SUFRAMA", order = 9)
	public String getSuframa() {
		return suframa;
	}

	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}

	@CampoSped(name = "END", order = 10)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@CampoSped(name = "NUM", order = 11)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@CampoSped(name = "COMPL", order = 12)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@CampoSped(name = "BAIRRO", order = 13)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

}
