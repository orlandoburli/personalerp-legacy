package br.com.orlandoburli.core.vo.rh;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ValeFuncionarioVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoValeFuncionario;
	
	private Integer CodigoEmpresaContratoTrabalho;
	private Integer CodigoLojaContratoTrabalho;
	private Integer CodigoContratoTrabalho;
	
	@Formula(getFormula="(SELECT c.NomeCargo       FROM [schema].ContratoTrabalho b JOIN [schema].Cargo       c ON b.codigoempresacargo       = c.codigoempresa AND b.codigolojacargo       = c.codigoloja AND b.codigocargo       = c.codigocargo       WHERE a.codigoempresacontratotrabalho = b.codigoempresa AND a.codigolojacontratotrabalho = b.codigoloja AND a.codigocontratotrabalho = b.codigocontratotrabalho)")
	private String NomeCargo;
	@Formula(getFormula="(SELECT c.NomeFuncionario FROM [schema].ContratoTrabalho b JOIN [schema].Funcionario c ON b.codigoempresafuncionario = c.codigoempresa AND b.codigolojafuncionario = c.codigoloja AND b.codigofuncionario = c.codigofuncionario WHERE a.codigoempresacontratotrabalho = b.codigoempresa AND a.codigolojacontratotrabalho = b.codigoloja AND a.codigocontratotrabalho = b.codigocontratotrabalho)")
	private String NomeFuncionario;
	
	private Date DataValeFuncionario;
	private Integer CodigoEmpresaVerba;
	private Integer CodigoLojaVerba;
	private Integer CodigoVerba;
	
	private Integer NumeroParcelaValeFuncionario;
	private Integer QuantidadeParcelasValeFuncionario;
	
	private BigDecimal ValorValeFuncionario;
	
	private String DescricaoValeFuncionario;
	
	@Formula(getFormula="CAST (EXTRACT (MONTH FROM a.DataValeFuncionario) AS INTEGER)")
	private Integer MesReferenciaValeFuncionario;
	
	@Formula(getFormula="CAST (EXTRACT (YEAR  FROM a.DataValeFuncionario) AS INTEGER)")
	private Integer AnoReferenciaValeFuncionario;
	
	@Formula(getFormula="(SELECT b.DescricaoVerba FROM [schema].Verba b" +
			" WHERE a.CodigoEmpresaVerba = b.CodigoEmpresa " +
			"   AND a.CodigoLojaverba = b.CodigoLoja" +
			"   AND a.CodigoVerba = b.CodigoVerba)")
	private String DescricaoVerba;
	
	@Formula(getFormula="(SELECT CASE WHEN b.TipoVerba = 'C' THEN 'Crédito' ELSE 'Débito' END FROM [schema].Verba b" +
			" WHERE a.CodigoEmpresaVerba = b.CodigoEmpresa " +
			"   AND a.CodigoLojaverba = b.CodigoLoja" +
			"   AND a.CodigoVerba = b.CodigoVerba)")
	private String TipoVerbaDescritivo;
	
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

	public BigDecimal getValorValeFuncionario() {
		return ValorValeFuncionario;
	}

	public void setValorValeFuncionario(BigDecimal valorValeFuncionario) {
		ValorValeFuncionario = valorValeFuncionario;
	}

	public void setCodigoValeFuncionario(Integer codigoValeFuncionario) {
		CodigoValeFuncionario = codigoValeFuncionario;
	}

	public Integer getCodigoValeFuncionario() {
		return CodigoValeFuncionario;
	}

	public void setDataValeFuncionario(Date dataValeFuncionario) {
		DataValeFuncionario = dataValeFuncionario;
	}

	public Date getDataValeFuncionario() {
		return DataValeFuncionario;
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

	public void setMesReferenciaValeFuncionario(
			Integer mesReferenciaValeFuncionario) {
		MesReferenciaValeFuncionario = mesReferenciaValeFuncionario;
	}

	public Integer getMesReferenciaValeFuncionario() {
		return MesReferenciaValeFuncionario;
	}

	public void setAnoReferenciaValeFuncionario(
			Integer anoReferenciaValeFuncionario) {
		AnoReferenciaValeFuncionario = anoReferenciaValeFuncionario;
	}

	public Integer getAnoReferenciaValeFuncionario() {
		return AnoReferenciaValeFuncionario;
	}

	public void setCodigoEmpresaVerba(Integer codigoEmpresaVerba) {
		CodigoEmpresaVerba = codigoEmpresaVerba;
	}

	public Integer getCodigoEmpresaVerba() {
		return CodigoEmpresaVerba;
	}

	public void setCodigoLojaVerba(Integer codigoLojaVerba) {
		CodigoLojaVerba = codigoLojaVerba;
	}

	public Integer getCodigoLojaVerba() {
		return CodigoLojaVerba;
	}

	public void setCodigoVerba(Integer codigoVerba) {
		CodigoVerba = codigoVerba;
	}

	public Integer getCodigoVerba() {
		return CodigoVerba;
	}

	public void setDescricaoVerba(String descricaoVerba) {
		DescricaoVerba = descricaoVerba;
	}

	public String getDescricaoVerba() {
		return DescricaoVerba;
	}

	public void setDescricaoValeFuncionario(String descricaoValeFuncionario) {
		DescricaoValeFuncionario = descricaoValeFuncionario;
	}

	public String getDescricaoValeFuncionario() {
		return DescricaoValeFuncionario;
	}

	public void setTipoVerbaDescritivo(String tipoVerbaDescritivo) {
		TipoVerbaDescritivo = tipoVerbaDescritivo;
	}

	public String getTipoVerbaDescritivo() {
		return TipoVerbaDescritivo;
	}

	public Integer getNumeroParcelaValeFuncionario() {
		return NumeroParcelaValeFuncionario;
	}

	public void setNumeroParcelaValeFuncionario(Integer numeroParcelaValeFuncionario) {
		NumeroParcelaValeFuncionario = numeroParcelaValeFuncionario;
	}

	public Integer getQuantidadeParcelasValeFuncionario() {
		return QuantidadeParcelasValeFuncionario;
	}

	public void setQuantidadeParcelasValeFuncionario(Integer quantidadeParcelasValeFuncionario) {
		QuantidadeParcelasValeFuncionario = quantidadeParcelasValeFuncionario;
	}
}