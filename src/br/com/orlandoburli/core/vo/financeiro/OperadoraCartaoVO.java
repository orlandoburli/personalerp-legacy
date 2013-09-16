package br.com.orlandoburli.core.vo.financeiro;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class OperadoraCartaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoOperadoraCartao;
	private String NomeOperadoraCartao;
	private String StatusOperadoraCartao;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoContaBancaria;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;

    @Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}
	
	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	@Override
	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	@Override
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

	public void setCodigoOperadoraCartao(Integer codigoOperadoraCartao) {
		CodigoOperadoraCartao = codigoOperadoraCartao;
	}

	public Integer getCodigoOperadoraCartao() {
		return CodigoOperadoraCartao;
	}

	public void setNomeOperadoraCartao(String nomeOperadoraCartao) {
		NomeOperadoraCartao = nomeOperadoraCartao;
	}

	public String getNomeOperadoraCartao() {
		return NomeOperadoraCartao;
	}

	public void setStatusOperadoraCartao(String statusOperadoraCartao) {
		StatusOperadoraCartao = statusOperadoraCartao;
	}

	public String getStatusOperadoraCartao() {
		return StatusOperadoraCartao;
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

	public void setCodigoContaBancaria(Integer codigoContaBancaria) {
		CodigoContaBancaria = codigoContaBancaria;
	}

	public Integer getCodigoContaBancaria() {
		return CodigoContaBancaria;
	}
}