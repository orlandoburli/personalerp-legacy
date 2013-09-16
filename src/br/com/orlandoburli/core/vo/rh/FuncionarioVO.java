package br.com.orlandoburli.core.vo.rh;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class FuncionarioVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoFuncionario;
	
	private String NomeFuncionario;
	private String SexoFuncionario;
	private String EnderecoFuncionario;
	private String Fone1Funcionario;
	private String Fone2Funcionario;
	private String Fone3Funcionario;
	private String BairroFuncionario;
	private String CepFuncionario;
	private Integer CodigoCidade;
	
	private String CpfFuncionario;
	private String RgFuncionario;
	private Date DataEmissaoRgFuncionario;
	private String OrgaoEmissaoRgFuncionario;
	private String UFEmissaoRgFuncionario;
	private String CtpsFuncionario;
	private String PisFuncionario;
	private String TituloEleitorFuncionario;
	private String SecaoTituloEleitorFuncionario;
	private String ZonaTituloEleitorFuncionario;
	
	private Integer NaturalidadeFuncionario;
	private Date DataNascimentoFuncionario;
	private Integer EstadoCivilFuncionario;
	private String NomePaiFuncionario;
	private String NomeMaeFuncionario;
	private String NomeConjugueFuncionario;
	
	private String ObservacoesFuncionario;
	
	@Formula(getFormula="(coalesce((select coalesce(b.statuscontratotrabalho, 'S') from personalerp.contratotrabalho b where a.codigoempresa = b.codigoempresafuncionario and a.codigoloja = b.codigolojafuncionario and a.codigofuncionario = b.codigofuncionario and b.datainiciocontratotrabalho = (select max(datainiciocontratotrabalho) from personalerp.contratotrabalho c where c.codigoempresafuncionario = b.codigoempresafuncionario and c.codigolojafuncionario = b.codigolojafuncionario and c.codigofuncionario = b.codigofuncionario) ), 'S'))")
	private String SituacaoFuncionario;

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
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

	public Integer getCodigoFuncionario() {
		return CodigoFuncionario;
	}

	public void setCodigoFuncionario(Integer codigoFuncionario) {
		CodigoFuncionario = codigoFuncionario;
	}

	public String getNomeFuncionario() {
		return NomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		NomeFuncionario = nomeFuncionario;
	}

	public String getSexoFuncionario() {
		return SexoFuncionario;
	}

	public void setSexoFuncionario(String sexoFuncionario) {
		SexoFuncionario = sexoFuncionario;
	}

	public String getEnderecoFuncionario() {
		return EnderecoFuncionario;
	}

	public void setEnderecoFuncionario(String enderecoFuncionario) {
		EnderecoFuncionario = enderecoFuncionario;
	}

	public String getFone1Funcionario() {
		return Fone1Funcionario;
	}

	public void setFone1Funcionario(String fone1Funcionario) {
		Fone1Funcionario = fone1Funcionario;
	}

	public String getFone2Funcionario() {
		return Fone2Funcionario;
	}

	public void setFone2Funcionario(String fone2Funcionario) {
		Fone2Funcionario = fone2Funcionario;
	}

	public String getFone3Funcionario() {
		return Fone3Funcionario;
	}

	public void setFone3Funcionario(String fone3Funcionario) {
		Fone3Funcionario = fone3Funcionario;
	}

	public String getBairroFuncionario() {
		return BairroFuncionario;
	}

	public void setBairroFuncionario(String bairroFuncionario) {
		BairroFuncionario = bairroFuncionario;
	}

	public Integer getCodigoCidade() {
		return CodigoCidade;
	}

	public void setCodigoCidade(Integer codigoCidade) {
		CodigoCidade = codigoCidade;
	}

	public String getCpfFuncionario() {
		return CpfFuncionario;
	}

	public void setCpfFuncionario(String cpfFuncionario) {
		CpfFuncionario = cpfFuncionario;
	}

	public String getRgFuncionario() {
		return RgFuncionario;
	}

	public void setRgFuncionario(String rgFuncionario) {
		RgFuncionario = rgFuncionario;
	}

	public Date getDataEmissaoRgFuncionario() {
		return DataEmissaoRgFuncionario;
	}

	public void setDataEmissaoRgFuncionario(Date dataEmissaoRgFuncionario) {
		DataEmissaoRgFuncionario = dataEmissaoRgFuncionario;
	}

	public String getOrgaoEmissaoRgFuncionario() {
		return OrgaoEmissaoRgFuncionario;
	}

	public void setOrgaoEmissaoRgFuncionario(String orgaoEmissaoRgFuncionario) {
		OrgaoEmissaoRgFuncionario = orgaoEmissaoRgFuncionario;
	}

	public String getUFEmissaoRgFuncionario() {
		return UFEmissaoRgFuncionario;
	}

	public void setUFEmissaoRgFuncionario(String uFEmissaoRgFuncionario) {
		UFEmissaoRgFuncionario = uFEmissaoRgFuncionario;
	}

	public String getCtpsFuncionario() {
		return CtpsFuncionario;
	}

	public void setCtpsFuncionario(String ctpsFuncionario) {
		CtpsFuncionario = ctpsFuncionario;
	}

	public String getPisFuncionario() {
		return PisFuncionario;
	}

	public void setPisFuncionario(String pisFuncionario) {
		PisFuncionario = pisFuncionario;
	}

	public String getTituloEleitorFuncionario() {
		return TituloEleitorFuncionario;
	}

	public void setTituloEleitorFuncionario(String tituloEleitorFuncionario) {
		TituloEleitorFuncionario = tituloEleitorFuncionario;
	}

	public String getSecaoTituloEleitorFuncionario() {
		return SecaoTituloEleitorFuncionario;
	}

	public void setSecaoTituloEleitorFuncionario(
			String secaoTituloEleitorFuncionario) {
		SecaoTituloEleitorFuncionario = secaoTituloEleitorFuncionario;
	}

	public String getZonaTituloEleitorFuncionario() {
		return ZonaTituloEleitorFuncionario;
	}

	public void setZonaTituloEleitorFuncionario(String zonaTituloEleitorFuncionario) {
		ZonaTituloEleitorFuncionario = zonaTituloEleitorFuncionario;
	}

	public Integer getNaturalidadeFuncionario() {
		return NaturalidadeFuncionario;
	}

	public void setNaturalidadeFuncionario(Integer naturalidadeFuncionario) {
		NaturalidadeFuncionario = naturalidadeFuncionario;
	}

	public Date getDataNascimentoFuncionario() {
		return DataNascimentoFuncionario;
	}

	public void setDataNascimentoFuncionario(Date dataNascimentoFuncionario) {
		DataNascimentoFuncionario = dataNascimentoFuncionario;
	}

	public Integer getEstadoCivilFuncionario() {
		return EstadoCivilFuncionario;
	}

	public void setEstadoCivilFuncionario(Integer estadoCivilFuncionario) {
		EstadoCivilFuncionario = estadoCivilFuncionario;
	}

	public String getNomePaiFuncionario() {
		return NomePaiFuncionario;
	}

	public void setNomePaiFuncionario(String nomePaiFuncionario) {
		NomePaiFuncionario = nomePaiFuncionario;
	}

	public String getNomeMaeFuncionario() {
		return NomeMaeFuncionario;
	}

	public void setNomeMaeFuncionario(String nomeMaeFuncionario) {
		NomeMaeFuncionario = nomeMaeFuncionario;
	}

	public String getNomeConjugueFuncionario() {
		return NomeConjugueFuncionario;
	}

	public void setNomeConjugueFuncionario(String nomeConjugueFuncionario) {
		NomeConjugueFuncionario = nomeConjugueFuncionario;
	}

	public String getObservacoesFuncionario() {
		return ObservacoesFuncionario;
	}

	public void setObservacoesFuncionario(String observacoesFuncionario) {
		ObservacoesFuncionario = observacoesFuncionario;
	}

	public void setCepFuncionario(String cepFuncionario) {
		CepFuncionario = cepFuncionario;
	}

	public String getCepFuncionario() {
		return CepFuncionario;
	}

	public void setSituacaoFuncionario(String situacaoFuncionario) {
		SituacaoFuncionario = situacaoFuncionario;
	}

	public String getSituacaoFuncionario() {
		return SituacaoFuncionario;
	}
}