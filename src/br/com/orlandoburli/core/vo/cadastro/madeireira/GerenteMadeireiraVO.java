package br.com.orlandoburli.core.vo.cadastro.madeireira;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class GerenteMadeireiraVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoGerente;
	
	@Formula(getFormula="(SELECT c.NomeFuncionario " +
			"FROM [schema].ContratoTrabalho b " +
			"JOIN [schema].Funcionario c ON b.CodigoEmpresaFuncionario = c.CodigoEmpresa " +
			"							AND b.CodigoLojaFuncionario    = c.CodigoLoja " +
			"                           AND b.CodigoFuncionario        = c.CodigoFuncionario " +
			"WHERE a.CodigoEmpresaContratoTrabalho = b.CodigoEmpresa " +
			"  AND a.CodigoLojaContratoTrabalho    = b.CodigoLoja " +
			"  AND a.CodigoContratoTrabalho        = b.CodigoContratoTrabalho) ")
	private String NomeGerente;
	
	private Integer CodigoEmpresaContratoTrabalho;
	private Integer CodigoLojaContratoTrabalho;
	private Integer CodigoContratoTrabalho;

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

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoGerente(Integer codigoGerente) {
		CodigoGerente = codigoGerente;
	}

	public Integer getCodigoGerente() {
		return CodigoGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		NomeGerente = nomeGerente;
	}

	public String getNomeGerente() {
		return NomeGerente;
	}

	public void setCodigoEmpresaContratoTrabalho(
			Integer codigoEmpresaContratoTrabalho) {
		CodigoEmpresaContratoTrabalho = codigoEmpresaContratoTrabalho;
	}

	public Integer getCodigoEmpresaContratoTrabalho() {
		return CodigoEmpresaContratoTrabalho;
	}

	public void setCodigoLojaContratoTrabalho(Integer codigoLojaContratoTrabalho) {
		CodigoLojaContratoTrabalho = codigoLojaContratoTrabalho;
	}

	public Integer getCodigoLojaContratoTrabalho() {
		return CodigoLojaContratoTrabalho;
	}

	public void setCodigoContratoTrabalho(Integer codigoContratoTrabalho) {
		CodigoContratoTrabalho = codigoContratoTrabalho;
	}

	public Integer getCodigoContratoTrabalho() {
		return CodigoContratoTrabalho;
	}

}
