package br.com.orlandoburli.core.vo.cadastro.pessoa;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class EnderecoPessoaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoPessoa;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoEnderecoPessoa;
	
	private String EmailEnderecoPessoa;
	private String LogradouroEnderecoPessoa;
	private String BairroEnderecoPessoa;
	private Integer CodigoCidadeEnderecoPessoa;
	private String CepEnderecoPessoa;
	private String Fone1EnderecoPessoa;
	private String Fone2EnderecoPessoa;
	private String TipoEnderecoPessoa;
	private String CpfCnpjEnderecoPessoa;
	private String FantasiaApelidoEnderecoPessoa;
	private String InscricaoEstadualEnderecoPessoa;
	private String InscricaoMunicipalEnderecoPessoa;
	
	@Formula(getFormula="(SELECT b.nomecidade FROM [schema].cidade b WHERE a.codigocidadeenderecopessoa = b.codigocidade)")
	private String NomeCidadeEnderecoPessoa;
	@Formula(getFormula="(SELECT c.siglaestado FROM [schema].cidade b JOIN [schema].estado c ON b.codigoestado = c.codigoestado WHERE a.codigocidadeenderecopessoa = b.codigocidade)")
	private String SiglaUFEnderecoPessoa;
	@Formula(getFormula="(SELECT " +
			"d.NomeRazaoSocialPessoa || ' - ' || a.LogradouroEnderecoPessoa || ' ' || e.NomeCidade || '/' || f.SiglaEstado \n" +
			"FROM [schema].Pessoa d \n" +
			"LEFT JOIN [schema].Cidade e ON a.CodigoCidadeEnderecoPessoa = e.CodigoCidade \n" +
			"LEFT JOIN [schema].Estado f ON e.CodigoEstado = f.CodigoEstado " +
			"WHERE d.CodigoPessoa = a.CodigoPessoa AND d.CodigoEmpresa = a.CodigoEmpresa AND d.CodigoLoja = a.CodigoLoja)\n")
	private String NomeEnderecoPessoa;

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getCodigoPessoa() {
		return CodigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		CodigoPessoa = codigoPessoa;
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

	public Integer getCodigoEnderecoPessoa() {
		return CodigoEnderecoPessoa;
	}

	public void setCodigoEnderecoPessoa(Integer codigoEnderecoPessoa) {
		CodigoEnderecoPessoa = codigoEnderecoPessoa;
	}


	public String getEmailEnderecoPessoa() {
		return EmailEnderecoPessoa;
	}

	public void setEmailEnderecoPessoa(String emailEnderecoPessoa) {
		EmailEnderecoPessoa = emailEnderecoPessoa;
	}

	public String getLogradouroEnderecoPessoa() {
		return LogradouroEnderecoPessoa;
	}

	public void setLogradouroEnderecoPessoa(String logradouroEnderecoPessoa) {
		LogradouroEnderecoPessoa = logradouroEnderecoPessoa;
	}

	public String getBairroEnderecoPessoa() {
		return BairroEnderecoPessoa;
	}

	public void setBairroEnderecoPessoa(String bairroEnderecoPessoa) {
		BairroEnderecoPessoa = bairroEnderecoPessoa;
	}

	public Integer getCodigoCidadeEnderecoPessoa() {
		return CodigoCidadeEnderecoPessoa;
	}

	public void setCodigoCidadeEnderecoPessoa(Integer codigoCidadeEnderecoPessoa) {
		CodigoCidadeEnderecoPessoa = codigoCidadeEnderecoPessoa;
	}

	public String getCepEnderecoPessoa() {
		return CepEnderecoPessoa;
	}

	public void setCepEnderecoPessoa(String cepEnderecoPessoa) {
		CepEnderecoPessoa = cepEnderecoPessoa;
	}

	public String getFone1EnderecoPessoa() {
		return Fone1EnderecoPessoa;
	}

	public void setFone1EnderecoPessoa(String fone1EnderecoPessoa) {
		Fone1EnderecoPessoa = fone1EnderecoPessoa;
	}

	public String getFone2EnderecoPessoa() {
		return Fone2EnderecoPessoa;
	}

	public void setFone2EnderecoPessoa(String fone2EnderecoPessoa) {
		Fone2EnderecoPessoa = fone2EnderecoPessoa;
	}

	public String getTipoEnderecoPessoa() {
		return TipoEnderecoPessoa;
	}

	public void setTipoEnderecoPessoa(String tipoEnderecoPessoa) {
		TipoEnderecoPessoa = tipoEnderecoPessoa;
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

	public void setNomeCidadeEnderecoPessoa(String nomeCidadeEnderecoPessoa) {
		NomeCidadeEnderecoPessoa = nomeCidadeEnderecoPessoa;
	}

	public String getNomeCidadeEnderecoPessoa() {
		return NomeCidadeEnderecoPessoa;
	}

	public void setSiglaUFEnderecoPessoa(String siglaUFEnderecoPessoa) {
		SiglaUFEnderecoPessoa = siglaUFEnderecoPessoa;
	}

	public String getSiglaUFEnderecoPessoa() {
		return SiglaUFEnderecoPessoa;
	}

	public void setCpfCnpjEnderecoPessoa(String cpfCnpjEnderecoPessoa) {
		CpfCnpjEnderecoPessoa = cpfCnpjEnderecoPessoa;
	}

	public String getCpfCnpjEnderecoPessoa() {
		return CpfCnpjEnderecoPessoa;
	}

	public void setFantasiaApelidoEnderecoPessoa(
			String fantasiaApelidoEnderecoPessoa) {
		FantasiaApelidoEnderecoPessoa = fantasiaApelidoEnderecoPessoa;
	}

	public String getFantasiaApelidoEnderecoPessoa() {
		return FantasiaApelidoEnderecoPessoa;
	}

	public void setInscricaoEstadualEnderecoPessoa(
			String inscricaoEstadualEnderecoPessoa) {
		InscricaoEstadualEnderecoPessoa = inscricaoEstadualEnderecoPessoa;
	}

	public String getInscricaoEstadualEnderecoPessoa() {
		return InscricaoEstadualEnderecoPessoa;
	}

	public void setInscricaoMunicipalEnderecoPessoa(
			String inscricaoMunicipalEnderecoPessoa) {
		InscricaoMunicipalEnderecoPessoa = inscricaoMunicipalEnderecoPessoa;
	}

	public String getInscricaoMunicipalEnderecoPessoa() {
		return InscricaoMunicipalEnderecoPessoa;
	}

	public void setNomeEnderecoPessoa(String nomeEnderecoPessoa) {
		NomeEnderecoPessoa = nomeEnderecoPessoa;
	}

	public String getNomeEnderecoPessoa() {
		return NomeEnderecoPessoa;
	}
}