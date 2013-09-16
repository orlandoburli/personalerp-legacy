package br.com.orlandoburli.core.extras.sped.registros.bloco0;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class Registro0005 extends RegistroSped implements Bloco0 {

	private String fantasia;
	private Integer cep;
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String fone;
	private String fax;
	private String email;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "0005";
	}

	@CampoSped(name = "FANTASIA", order = 2)
	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}

	@CampoSped(name = "CEP", order = 3)
	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	@CampoSped(name = "END", order = 4)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@CampoSped(name = "NUM", order = 5)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@CampoSped(name = "COMPL", order = 6)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@CampoSped(name = "BAIRRO", order = 7)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@CampoSped(name = "FONE", order = 8)
	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	@CampoSped(name = "FAX", order = 9)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@CampoSped(name = "EMAIL", order = 10)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
