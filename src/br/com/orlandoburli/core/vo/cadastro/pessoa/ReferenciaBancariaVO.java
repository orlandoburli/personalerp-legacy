package br.com.orlandoburli.core.vo.cadastro.pessoa;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ReferenciaBancariaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoPessoa;
	@Key
	@AutoIncrement
	private Integer CodigoReferenciaBancaria;
	private Integer CodigoBancoReferenciaBancaria;
	private Integer CodigoCidadeReferenciaBancaria;
	private String AgenciaReferenciaBancaria;
	private String ContaReferenciaBancaria;
	private String Fone1ReferenciaBancaria;
	private String Fone2ReferenciaBancaria;
	private Date DataReferenciaBancaria;
	private Date DataInclusaoReferenciaBancaria;
	private String ExperienciaFinanceiraReferenciaBancaria;
	private String PossuiRestricaoReferenciaBancaria;
	private Integer ConceitoReferenciaBancaria;
	private String ObservacoesReferenciaBancaria;
	private String ContatoReferenciaBancaria;
	private String CargoReferenciaBancaria;
	
	
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

	public Integer getCodigoPessoa() {
		return CodigoPessoa;
	}

	public void setCodigoPessoa(Integer codigoPessoa) {
		CodigoPessoa = codigoPessoa;
	}

	public Integer getCodigoReferenciaBancaria() {
		return CodigoReferenciaBancaria;
	}

	public void setCodigoReferenciaBancaria(Integer codigoReferenciaBancaria) {
		CodigoReferenciaBancaria = codigoReferenciaBancaria;
	}

	public Integer getCodigoBancoReferenciaBancaria() {
		return CodigoBancoReferenciaBancaria;
	}

	public void setCodigoBancoReferenciaBancaria(
			Integer codigoBancoReferenciaBancaria) {
		CodigoBancoReferenciaBancaria = codigoBancoReferenciaBancaria;
	}

	public Integer getCodigoCidadeReferenciaBancaria() {
		return CodigoCidadeReferenciaBancaria;
	}

	public void setCodigoCidadeReferenciaBancaria(
			Integer codigoCidadeReferenciaBancaria) {
		CodigoCidadeReferenciaBancaria = codigoCidadeReferenciaBancaria;
	}

	public String getAgenciaReferenciaBancaria() {
		return AgenciaReferenciaBancaria;
	}

	public void setAgenciaReferenciaBancaria(String agenciaReferenciaBancaria) {
		AgenciaReferenciaBancaria = agenciaReferenciaBancaria;
	}

	public String getContaReferenciaBancaria() {
		return ContaReferenciaBancaria;
	}

	public void setContaReferenciaBancaria(String contaReferenciaBancaria) {
		ContaReferenciaBancaria = contaReferenciaBancaria;
	}

	public String getFone1ReferenciaBancaria() {
		return Fone1ReferenciaBancaria;
	}

	public void setFone1ReferenciaBancaria(String fone1ReferenciaBancaria) {
		Fone1ReferenciaBancaria = fone1ReferenciaBancaria;
	}

	public String getFone2ReferenciaBancaria() {
		return Fone2ReferenciaBancaria;
	}

	public void setFone2ReferenciaBancaria(String fone2ReferenciaBancaria) {
		Fone2ReferenciaBancaria = fone2ReferenciaBancaria;
	}

	public Date getDataReferenciaBancaria() {
		return DataReferenciaBancaria;
	}

	public void setDataReferenciaBancaria(Date dataReferenciaBancaria) {
		DataReferenciaBancaria = dataReferenciaBancaria;
	}

	public Date getDataInclusaoReferenciaBancaria() {
		return DataInclusaoReferenciaBancaria;
	}

	public void setDataInclusaoReferenciaBancaria(
			Date dataInclusaoReferenciaBancaria) {
		DataInclusaoReferenciaBancaria = dataInclusaoReferenciaBancaria;
	}

	public String getExperienciaFinanceiraReferenciaBancaria() {
		return ExperienciaFinanceiraReferenciaBancaria;
	}

	public void setExperienciaFinanceiraReferenciaBancaria(
			String experienciaFinanceiraReferenciaBancaria) {
		ExperienciaFinanceiraReferenciaBancaria = experienciaFinanceiraReferenciaBancaria;
	}

	public String getPossuiRestricaoReferenciaBancaria() {
		return PossuiRestricaoReferenciaBancaria;
	}

	public void setPossuiRestricaoReferenciaBancaria(
			String possuiRestricaoReferenciaBancaria) {
		PossuiRestricaoReferenciaBancaria = possuiRestricaoReferenciaBancaria;
	}

	public Integer getConceitoReferenciaBancaria() {
		return ConceitoReferenciaBancaria;
	}

	public void setConceitoReferenciaBancaria(Integer conceitoReferenciaBancaria) {
		ConceitoReferenciaBancaria = conceitoReferenciaBancaria;
	}

	public String getObservacoesReferenciaBancaria() {
		return ObservacoesReferenciaBancaria;
	}

	public void setObservacoesReferenciaBancaria(
			String observacoesReferenciaBancaria) {
		ObservacoesReferenciaBancaria = observacoesReferenciaBancaria;
	}

	public String getContatoReferenciaBancaria() {
		return ContatoReferenciaBancaria;
	}

	public void setContatoReferenciaBancaria(String contatoReferenciaBancaria) {
		ContatoReferenciaBancaria = contatoReferenciaBancaria;
	}

	public String getCargoReferenciaBancaria() {
		return CargoReferenciaBancaria;
	}

	public void setCargoReferenciaBancaria(String cargoReferenciaBancaria) {
		CargoReferenciaBancaria = cargoReferenciaBancaria;
	}
}
