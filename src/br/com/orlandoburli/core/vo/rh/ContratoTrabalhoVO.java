package br.com.orlandoburli.core.vo.rh;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class ContratoTrabalhoVO implements IValueObject {

	@Ignore
	public static final String STATUS_ATIVO = "A";
	@Ignore
	public static final String STATUS_INATIVO = "I";
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoContratoTrabalho;
	
	private Date DataInicioContratoTrabalho;
	private Date DataFimContratoTrabalho;
	
	private Integer DiasExperiencia1ContratoTrabalho;
	private Integer DiasExperiencia2ContratoTrabalho;
	
	private String StatusContratoTrabalho;
	
	private Integer CodigoEmpresaCargo;
	private Integer CodigoLojaCargo;
	private Integer CodigoCargo;
	
	private Integer CodigoEmpresaMotivoDesligamento;
	private Integer CodigoLojaMotivoDesligamento;
	private Integer CodigoMotivoDesligamento;
	
	private Integer CodigoEmpresaFuncionario;
	private Integer CodigoLojaFuncionario;
	private Integer CodigoFuncionario;
	
	private Integer CodigoEmpresaHorarioTrabalho;
	private Integer CodigoLojaHorarioTrabalho;
	private Integer CodigoHorarioTrabalho;
	
	private String ObservacoesContratoTrabalho;
	
	private String FlagCarteiraAssinadaContratoTrabalho;
	
	private Integer CodigoEmpresaLotacao;
	private Integer CodigoLojaLotacao;
	
	@Formula(getFormula="(SELECT b.NomeCargo FROM [schema].Cargo b WHERE a.CodigoEmpresaCargo = b.CodigoEmpresa AND a.CodigoLojaCargo = b.CodigoLoja AND a.CodigoCargo = b.CodigoCargo)")
	private String NomeCargo;
	
	@Formula(getFormula="(SELECT b.NomeFuncionario FROM [schema].Funcionario b WHERE a.CodigoEmpresaFuncionario = b.CodigoEmpresa AND a.CodigoLojaFuncionario = b.CodigoLoja AND a.CodigoFuncionario = b.CodigoFuncionario)")
	private String NomeFuncionario;

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

	public Integer getCodigoContratoTrabalho() {
		return CodigoContratoTrabalho;
	}

	public void setCodigoContratoTrabalho(Integer codigoContratoTrabalho) {
		CodigoContratoTrabalho = codigoContratoTrabalho;
	}

	public Date getDataInicioContratoTrabalho() {
		return DataInicioContratoTrabalho;
	}

	public void setDataInicioContratoTrabalho(Date dataInicioContratoTrabalho) {
		DataInicioContratoTrabalho = dataInicioContratoTrabalho;
	}

	public Integer getDiasExperiencia1ContratoTrabalho() {
		return DiasExperiencia1ContratoTrabalho;
	}

	public void setDiasExperiencia1ContratoTrabalho(
			Integer diasExperiencia1ContratoTrabalho) {
		DiasExperiencia1ContratoTrabalho = diasExperiencia1ContratoTrabalho;
	}

	public Integer getDiasExperiencia2ContratoTrabalho() {
		return DiasExperiencia2ContratoTrabalho;
	}

	public void setDiasExperiencia2ContratoTrabalho(
			Integer diasExperiencia2ContratoTrabalho) {
		DiasExperiencia2ContratoTrabalho = diasExperiencia2ContratoTrabalho;
	}

	public String getStatusContratoTrabalho() {
		return StatusContratoTrabalho;
	}

	public void setStatusContratoTrabalho(String statusContratoTrabalho) {
		StatusContratoTrabalho = statusContratoTrabalho;
	}

	public Integer getCodigoEmpresaMotivoDesligamento() {
		return CodigoEmpresaMotivoDesligamento;
	}

	public void setCodigoEmpresaMotivoDesligamento(
			Integer codigoEmpresaMotivoDesligamento) {
		CodigoEmpresaMotivoDesligamento = codigoEmpresaMotivoDesligamento;
	}

	public Integer getCodigoLojaMotivoDesligamento() {
		return CodigoLojaMotivoDesligamento;
	}

	public void setCodigoLojaMotivoDesligamento(Integer codigoLojaMotivoDesligamento) {
		CodigoLojaMotivoDesligamento = codigoLojaMotivoDesligamento;
	}

	public Integer getCodigoMotivoDesligamento() {
		return CodigoMotivoDesligamento;
	}

	public void setCodigoMotivoDesligamento(Integer codigoMotivoDesligamento) {
		CodigoMotivoDesligamento = codigoMotivoDesligamento;
	}

	public Integer getCodigoEmpresaFuncionario() {
		return CodigoEmpresaFuncionario;
	}

	public void setCodigoEmpresaFuncionario(Integer codigoEmpresaFuncionario) {
		CodigoEmpresaFuncionario = codigoEmpresaFuncionario;
	}

	public Integer getCodigoLojaFuncionario() {
		return CodigoLojaFuncionario;
	}

	public void setCodigoLojaFuncionario(Integer codigoLojaFuncionario) {
		CodigoLojaFuncionario = codigoLojaFuncionario;
	}

	public Integer getCodigoFuncionario() {
		return CodigoFuncionario;
	}

	public void setCodigoFuncionario(Integer codigoFuncionario) {
		CodigoFuncionario = codigoFuncionario;
	}

	public Integer getCodigoEmpresaHorarioTrabalho() {
		return CodigoEmpresaHorarioTrabalho;
	}

	public void setCodigoEmpresaHorarioTrabalho(Integer codigoEmpresaHorarioTrabalho) {
		CodigoEmpresaHorarioTrabalho = codigoEmpresaHorarioTrabalho;
	}

	public Integer getCodigoLojaHorarioTrabalho() {
		return CodigoLojaHorarioTrabalho;
	}

	public void setCodigoLojaHorarioTrabalho(Integer codigoLojaHorarioTrabalho) {
		CodigoLojaHorarioTrabalho = codigoLojaHorarioTrabalho;
	}

	public Integer getCodigoHorarioTrabalho() {
		return CodigoHorarioTrabalho;
	}

	public void setCodigoHorarioTrabalho(Integer codigoHorarioTrabalho) {
		CodigoHorarioTrabalho = codigoHorarioTrabalho;
	}

	public String getObservacoesContratoTrabalho() {
		return ObservacoesContratoTrabalho;
	}

	public void setObservacoesContratoTrabalho(String observacoesContratoTrabalho) {
		ObservacoesContratoTrabalho = observacoesContratoTrabalho;
	}

	public void setDataFimContratoTrabalho(Date dataFimContratoTrabalho) {
		DataFimContratoTrabalho = dataFimContratoTrabalho;
	}

	public Date getDataFimContratoTrabalho() {
		return DataFimContratoTrabalho;
	}

	public void setCodigoEmpresaCargo(Integer codigoEmpresaCargo) {
		CodigoEmpresaCargo = codigoEmpresaCargo;
	}

	public Integer getCodigoEmpresaCargo() {
		return CodigoEmpresaCargo;
	}

	public void setCodigoLojaCargo(Integer codigoLojaCargo) {
		CodigoLojaCargo = codigoLojaCargo;
	}

	public Integer getCodigoLojaCargo() {
		return CodigoLojaCargo;
	}

	public void setCodigoCargo(Integer codigoCargo) {
		CodigoCargo = codigoCargo;
	}

	public Integer getCodigoCargo() {
		return CodigoCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		NomeCargo = nomeCargo;
	}

	public String getNomeCargo() {
		return NomeCargo;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		NomeFuncionario = nomeFuncionario;
	}

	public String getNomeFuncionario() {
		return NomeFuncionario;
	}

	public void setFlagCarteiraAssinadaContratoTrabalho(
			String flagCarteiraAssinadaContratoTrabalho) {
		FlagCarteiraAssinadaContratoTrabalho = flagCarteiraAssinadaContratoTrabalho;
	}

	public String getFlagCarteiraAssinadaContratoTrabalho() {
		return FlagCarteiraAssinadaContratoTrabalho;
	}

	public Integer getCodigoEmpresaLotacao() {
		return CodigoEmpresaLotacao;
	}

	public void setCodigoEmpresaLotacao(Integer codigoEmpresaLotacao) {
		CodigoEmpresaLotacao = codigoEmpresaLotacao;
	}

	public Integer getCodigoLojaLotacao() {
		return CodigoLojaLotacao;
	}

	public void setCodigoLojaLotacao(Integer codigoLojaLotacao) {
		CodigoLojaLotacao = codigoLojaLotacao;
	}
}