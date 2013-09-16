package br.com.orlandoburli.core.vo.rh;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class FaltaFuncionarioVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoContratoTrabalho;
	@Key
	@AutoIncrement
	private Integer SequencialFaltaFuncionario;
	
	private Date DataFaltaFuncionario;
	private Timestamp HorasFaltaFuncionario;
	private String ObservacoesFaltaFuncionario;
	
	private Integer CodigoEmpresaMotivoFaltaFuncionario;
	private Integer CodigoLojaMotivoFaltaFuncionario;
	private Integer CodigoMotivoFaltaFuncionario;
	
	@Formula(getFormula="(SELECT c.NomeCargo       FROM [schema].ContratoTrabalho b JOIN [schema].Cargo       c ON b.codigoempresacargo       = c.codigoempresa AND b.codigolojacargo       = c.codigoloja AND b.codigocargo       = c.codigocargo       WHERE a.codigoempresa = b.codigoempresa AND a.codigoloja = b.codigoloja AND a.codigocontratotrabalho = b.codigocontratotrabalho)")
	private String NomeCargo;
	
	@Formula(getFormula="(SELECT c.NomeFuncionario FROM [schema].ContratoTrabalho b JOIN [schema].Funcionario c ON b.codigoempresafuncionario = c.codigoempresa AND b.codigolojafuncionario = c.codigoloja AND b.codigofuncionario = c.codigofuncionario WHERE a.codigoempresa = b.codigoempresa AND a.codigoloja = b.codigoloja AND a.codigocontratotrabalho = b.codigocontratotrabalho)")
	private String NomeFuncionario;
	
	@Formula(getFormula="CAST (EXTRACT (MONTH FROM a.DataFaltaFuncionario) AS INTEGER)")
	private Integer MesReferenciaFaltaFuncionario;
	
	@Formula(getFormula="CAST (EXTRACT (YEAR  FROM a.DataFaltaFuncionario) AS INTEGER)")
	private Integer AnoReferenciaFaltaFuncionario;
	
	@Join(entityName="MotivoFaltaFuncionario", 
			foreignColumnName="DescricaoMotivoFaltaFuncionario", 
			localKeys={"CodigoEmpresaMotivoFaltaFuncionario", "CodigoLojaMotivoFaltaFuncionario", "CodigoMotivoFaltaFuncionario"}, 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoMotivoFaltaFuncionario"})
	private String DescricaoMotivoFaltaFuncionario;
	
	@Join(entityName="MotivoFaltaFuncionario", 
			foreignColumnName="FlagParcialMotivoFaltaFuncionario", 
			localKeys={"CodigoEmpresaMotivoFaltaFuncionario", "CodigoLojaMotivoFaltaFuncionario", "CodigoMotivoFaltaFuncionario"}, 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoMotivoFaltaFuncionario"})
	private String FlagParcialMotivoFaltaFuncionario;
	
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

	public Integer getSequencialFaltaFuncionario() {
		return SequencialFaltaFuncionario;
	}

	public void setSequencialFaltaFuncionario(Integer sequencialFaltaFuncionario) {
		SequencialFaltaFuncionario = sequencialFaltaFuncionario;
	}

	public Date getDataFaltaFuncionario() {
		return DataFaltaFuncionario;
	}

	public void setDataFaltaFuncionario(Date dataFaltaFuncionario) {
		DataFaltaFuncionario = dataFaltaFuncionario;
	}

	public Timestamp getHorasFaltaFuncionario() {
		return HorasFaltaFuncionario;
	}

	public void setHorasFaltaFuncionario(Timestamp horasFaltaFuncionario) {
		HorasFaltaFuncionario = horasFaltaFuncionario;
	}

	public String getObservacoesFaltaFuncionario() {
		return ObservacoesFaltaFuncionario;
	}

	public void setObservacoesFaltaFuncionario(String observacoesFaltaFuncionario) {
		ObservacoesFaltaFuncionario = observacoesFaltaFuncionario;
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

	public void setMesReferenciaFaltaFuncionario(
			Integer mesReferenciaFaltaFuncionario) {
		MesReferenciaFaltaFuncionario = mesReferenciaFaltaFuncionario;
	}

	public Integer getMesReferenciaFaltaFuncionario() {
		return MesReferenciaFaltaFuncionario;
	}

	public void setAnoReferenciaFaltaFuncionario(
			Integer anoReferenciaFaltaFuncionario) {
		AnoReferenciaFaltaFuncionario = anoReferenciaFaltaFuncionario;
	}

	public Integer getAnoReferenciaFaltaFuncionario() {
		return AnoReferenciaFaltaFuncionario;
	}

	public void setCodigoEmpresaMotivoFaltaFuncionario(
			Integer codigoEmpresaMotivoFaltaFuncionario) {
		CodigoEmpresaMotivoFaltaFuncionario = codigoEmpresaMotivoFaltaFuncionario;
	}

	public Integer getCodigoEmpresaMotivoFaltaFuncionario() {
		return CodigoEmpresaMotivoFaltaFuncionario;
	}

	public void setCodigoLojaMotivoFaltaFuncionario(
			Integer codigoLojaMotivoFaltaFuncionario) {
		CodigoLojaMotivoFaltaFuncionario = codigoLojaMotivoFaltaFuncionario;
	}

	public Integer getCodigoLojaMotivoFaltaFuncionario() {
		return CodigoLojaMotivoFaltaFuncionario;
	}

	public void setCodigoMotivoFaltaFuncionario(
			Integer codigoMotivoFaltaFuncionario) {
		CodigoMotivoFaltaFuncionario = codigoMotivoFaltaFuncionario;
	}

	public Integer getCodigoMotivoFaltaFuncionario() {
		return CodigoMotivoFaltaFuncionario;
	}

	public void setDescricaoMotivoFaltaFuncionario(
			String descricaoMotivoFaltaFuncionario) {
		DescricaoMotivoFaltaFuncionario = descricaoMotivoFaltaFuncionario;
	}

	public String getDescricaoMotivoFaltaFuncionario() {
		return DescricaoMotivoFaltaFuncionario;
	}

	public void setFlagParcialMotivoFaltaFuncionario(
			String flagParcialMotivoFaltaFuncionario) {
		FlagParcialMotivoFaltaFuncionario = flagParcialMotivoFaltaFuncionario;
	}

	public String getFlagParcialMotivoFaltaFuncionario() {
		return FlagParcialMotivoFaltaFuncionario;
	}
}