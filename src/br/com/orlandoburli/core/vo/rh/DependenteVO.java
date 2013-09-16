package br.com.orlandoburli.core.vo.rh;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class DependenteVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoFuncionario;
	@Key
	@AutoIncrement
	private Integer CodigoDependente;
	
	private String NomeDependente;
	private String TipoDependente;
	
	private Date DataInicialDependente;
	private Date DataFinalDependente;

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

	public Integer getCodigoDependente() {
		return CodigoDependente;
	}

	public void setCodigoDependente(Integer codigoDependente) {
		CodigoDependente = codigoDependente;
	}

	public String getNomeDependente() {
		return NomeDependente;
	}

	public void setNomeDependente(String nomeDependente) {
		NomeDependente = nomeDependente;
	}

	public String getTipoDependente() {
		return TipoDependente;
	}

	public void setTipoDependente(String tipoDependente) {
		TipoDependente = tipoDependente;
	}

	public Date getDataInicialDependente() {
		return DataInicialDependente;
	}

	public void setDataInicialDependente(Date dataInicialDependente) {
		DataInicialDependente = dataInicialDependente;
	}

	public Date getDataFinalDependente() {
		return DataFinalDependente;
	}

	public void setDataFinalDependente(Date dataFinalDependente) {
		DataFinalDependente = dataFinalDependente;
	}
}