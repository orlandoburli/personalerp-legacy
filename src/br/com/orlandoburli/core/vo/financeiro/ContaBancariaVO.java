package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ContaBancariaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoContaBancaria;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	
	private String DescricaoContaBancaria;
	private String CodigoBancoContaBancaria;
	private Integer NumeroContaBancaria;
	private String DigitoContaBancaria;
	private Integer NumeroAgenciaContaBancaria;
	private String DigitoAgenciaContaBancaria;
	
	private String FlagContaCorrenteContaBancaria;
	private String FlagContaPoupancaContaBancaria;
	
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

	public Integer getCodigoContaBancaria() {
		return CodigoContaBancaria;
	}

	public void setCodigoContaBancaria(Integer codigoContaBancaria) {
		CodigoContaBancaria = codigoContaBancaria;
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

	public String getDescricaoContaBancaria() {
		return DescricaoContaBancaria;
	}

	public void setDescricaoContaBancaria(String descricaoContaBancaria) {
		DescricaoContaBancaria = descricaoContaBancaria;
	}

	public String getCodigoBancoContaBancaria() {
		return CodigoBancoContaBancaria;
	}

	public void setCodigoBancoContaBancaria(String codigoBancoContaBancaria) {
		CodigoBancoContaBancaria = codigoBancoContaBancaria;
	}

	public Integer getNumeroContaBancaria() {
		return NumeroContaBancaria;
	}

	public void setNumeroContaBancaria(Integer numeroContaBancaria) {
		NumeroContaBancaria = numeroContaBancaria;
	}

	public String getDigitoAgenciaContaBancaria() {
		return DigitoAgenciaContaBancaria;
	}

	public void setDigitoAgenciaContaBancaria(String digitoAgenciaContaBancaria) {
		DigitoAgenciaContaBancaria = digitoAgenciaContaBancaria;
	}

	public String getFlagContaCorrenteContaBancaria() {
		return FlagContaCorrenteContaBancaria;
	}

	public void setFlagContaCorrenteContaBancaria(
			String flagContaCorrenteContaBancaria) {
		FlagContaCorrenteContaBancaria = flagContaCorrenteContaBancaria;
	}

	public String getFlagContaPoupancaContaBancaria() {
		return FlagContaPoupancaContaBancaria;
	}

	public void setFlagContaPoupancaContaBancaria(
			String flagContaPoupancaContaBancaria) {
		FlagContaPoupancaContaBancaria = flagContaPoupancaContaBancaria;
	}

	public void setDigitoContaBancaria(String digitoContaBancaria) {
		DigitoContaBancaria = digitoContaBancaria;
	}

	public String getDigitoContaBancaria() {
		return DigitoContaBancaria;
	}

	public void setNumeroAgenciaContaBancaria(Integer numeroAgenciaContaBancaria) {
		NumeroAgenciaContaBancaria = numeroAgenciaContaBancaria;
	}

	public Integer getNumeroAgenciaContaBancaria() {
		return NumeroAgenciaContaBancaria;
	}
}
