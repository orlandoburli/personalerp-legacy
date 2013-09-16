package br.com.orlandoburli.personalerp.model.sistema.loja.vo;

import java.math.BigDecimal;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.*;

public class LojaVO implements IValueObject {

	private static final long serialVersionUID = 1L;

	private boolean isNew;

	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	private String NomeLoja;
	private Boolean FlagMatrizLoja = null;
	private String CNPJLoja;
	private String InscricaoMunicipalLoja;
	private String InscricaoEstadualLoja;
	private String EnderecoLoja;
	private String BairroLoja;
	private Integer CodigoCidadeLoja;
	private String CepLoja;
	private String FoneLoja;
	private String FaxLoja;
	private BigDecimal IssLoja;
	private Integer CodigoPessoaLoja;
	
	private Integer CodigoEmpresaFuncionarioSupervisor;
	private Integer CodigoLojaFuncionarioSupervisor;
	private Integer CodigoFuncionarioSupervisor;
	
	private Integer CodigoEmpresaFuncionarioGerente;
	private Integer CodigoLojaFuncionarioGerente;
	private Integer CodigoFuncionarioGerente;
	
	@Formula(value = "(select c.siglaestado from [schema].cidade b join [schema].estado c on c.codigoestado = b.codigoestado where b.codigocidade = a.codigocidadeloja)")
	private String UFLoja;
	
	@Formula(value = "(select c.regiaoestado from [schema].cidade b join [schema].estado c on c.codigoestado = b.codigoestado where b.codigocidade = a.codigocidadeloja)")
	private String RegiaoLoja;
		
	@Ignore
	private String Permitido;
	
	@Formula(value="(SELECT e.RazaoSocialEmpresa FROM [schema].Empresa e WHERE e.CodigoEmpresa = a.CodigoEmpresa)")
	private String RazaoSocialEmpresa;
	
	@Formula(value="(SELECT e.FantasiaEmpresa FROM [schema].Empresa e WHERE e.CodigoEmpresa = a.CodigoEmpresa)")
	private String FantasiaEmpresa;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LojaVO)) {
			return false;
		}
		LojaVO compare = (LojaVO) obj;
		try {
			if (compare.getCodigoEmpresa().equals(getCodigoEmpresa()) && compare.getCodigoLoja().equals(getCodigoLoja())) {
				return true;
			}
		} catch (NullPointerException e) {
			return false;
		}
		return super.equals(obj);
	}

	public boolean IsNew() {
		return this.isNew;
	}

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

	public String getNomeLoja() {
		return NomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}

	public Boolean isFlagMatrizLoja() {
		return FlagMatrizLoja;
	}

	public void setFlagMatrizLoja(Boolean flagMatrizLoja) {
		FlagMatrizLoja = flagMatrizLoja;
	}

	public String getCNPJLoja() {
		return CNPJLoja;
	}

	public void setCNPJLoja(String loja) {
		CNPJLoja = loja;
	}

	public String getInscricaoMunicipalLoja() {
		return InscricaoMunicipalLoja;
	}

	public void setInscricaoMunicipalLoja(String inscricaoMunicipalLoja) {
		InscricaoMunicipalLoja = inscricaoMunicipalLoja;
	}

	public String getInscricaoEstadualLoja() {
		return InscricaoEstadualLoja;
	}

	public void setInscricaoEstadualLoja(String inscricaoEstadualLoja) {
		InscricaoEstadualLoja = inscricaoEstadualLoja;
	}

	public String getEnderecoLoja() {
		return EnderecoLoja;
	}

	public void setEnderecoLoja(String enderecoLoja) {
		EnderecoLoja = enderecoLoja;
	}

	public String getBairroLoja() {
		return BairroLoja;
	}

	public void setBairroLoja(String bairroLoja) {
		BairroLoja = bairroLoja;
	}

	public Integer getCodigoCidadeLoja() {
		return CodigoCidadeLoja;
	}

	public void setCodigoCidadeLoja(Integer codigoCidadeLoja) {
		CodigoCidadeLoja = codigoCidadeLoja;
	}

	public String getCepLoja() {
		return CepLoja;
	}

	public void setCepLoja(String cepLoja) {
		CepLoja = cepLoja;
	}

	public String getFoneLoja() {
		return FoneLoja;
	}

	public void setFoneLoja(String foneLoja) {
		FoneLoja = foneLoja;
	}

	public String getFaxLoja() {
		return FaxLoja;
	}

	public void setFaxLoja(String faxLoja) {
		FaxLoja = faxLoja;
	}

	// public double getIssLoja() {
	// return IssLoja;
	// }
	//
	// public void setIssLoja(double issLoja) {
	// IssLoja = issLoja;
	// }

	public Integer getCodigoPessoaLoja() {
		return CodigoPessoaLoja;
	}

	public void setCodigoPessoaLoja(Integer codigoPessoaLoja) {
		CodigoPessoaLoja = codigoPessoaLoja;
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

	public void setPermitido(String permitido) {
		Permitido = permitido;
	}

	public String getPermitido() {
		return Permitido;
	}

	public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
		RazaoSocialEmpresa = razaoSocialEmpresa;
	}

	public String getRazaoSocialEmpresa() {
		return RazaoSocialEmpresa;
	}

	public void setIssLoja(BigDecimal issLoja) {
		IssLoja = issLoja;
	}

	public BigDecimal getIssLoja() {
		return IssLoja;
	}

	public void setUFLoja(String uFLoja) {
		UFLoja = uFLoja;
	}

	public String getUFLoja() {
		return UFLoja;
	}

	public void setRegiaoLoja(String regiaoLoja) {
		RegiaoLoja = regiaoLoja;
	}

	public String getRegiaoLoja() {
		return RegiaoLoja;
	}

	@Override
	public String toString() {
		return Utils.voToXml(this);
	}

	public Integer getCodigoEmpresaFuncionarioSupervisor() {
		return CodigoEmpresaFuncionarioSupervisor;
	}

	public void setCodigoEmpresaFuncionarioSupervisor(Integer codigoEmpresaFuncionarioSupervisor) {
		CodigoEmpresaFuncionarioSupervisor = codigoEmpresaFuncionarioSupervisor;
	}

	public Integer getCodigoLojaFuncionarioSupervisor() {
		return CodigoLojaFuncionarioSupervisor;
	}

	public void setCodigoLojaFuncionarioSupervisor(Integer codigoLojaFuncionarioSupervisor) {
		CodigoLojaFuncionarioSupervisor = codigoLojaFuncionarioSupervisor;
	}

	public Integer getCodigoFuncionarioSupervisor() {
		return CodigoFuncionarioSupervisor;
	}

	public void setCodigoFuncionarioSupervisor(Integer codigoFuncionarioSupervisor) {
		CodigoFuncionarioSupervisor = codigoFuncionarioSupervisor;
	}

	public Integer getCodigoEmpresaFuncionarioGerente() {
		return CodigoEmpresaFuncionarioGerente;
	}

	public void setCodigoEmpresaFuncionarioGerente(Integer codigoEmpresaFuncionarioGerente) {
		CodigoEmpresaFuncionarioGerente = codigoEmpresaFuncionarioGerente;
	}

	public Integer getCodigoLojaFuncionarioGerente() {
		return CodigoLojaFuncionarioGerente;
	}

	public void setCodigoLojaFuncionarioGerente(Integer codigoLojaFuncionarioGerente) {
		CodigoLojaFuncionarioGerente = codigoLojaFuncionarioGerente;
	}

	public Integer getCodigoFuncionarioGerente() {
		return CodigoFuncionarioGerente;
	}

	public void setCodigoFuncionarioGerente(Integer codigoFuncionarioGerente) {
		CodigoFuncionarioGerente = codigoFuncionarioGerente;
	}

	public Boolean getFlagMatrizLoja() {
		return FlagMatrizLoja;
	}

	public String getFantasiaEmpresa() {
		return FantasiaEmpresa;
	}

	public void setFantasiaEmpresa(String fantasiaEmpresa) {
		FantasiaEmpresa = fantasiaEmpresa;
	}
}