package br.com.orlandoburli.core.vo.cadastro.madeireira;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class VeiculoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoVeiculo;
	
	private String DescricaoVeiculo;
	
	private Integer CodigoEmpresaTipoVeiculo;
	private Integer CodigoLojaTipoVeiculo;
	private Integer CodigoTipoVeiculo;
	
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

	public Integer getCodigoVeiculo() {
		return CodigoVeiculo;
	}

	public void setCodigoVeiculo(Integer codigoVeiculo) {
		CodigoVeiculo = codigoVeiculo;
	}

	public String getDescricaoVeiculo() {
		return DescricaoVeiculo;
	}

	public void setDescricaoVeiculo(String descricaoVeiculo) {
		DescricaoVeiculo = descricaoVeiculo;
	}

	public Integer getCodigoEmpresaTipoVeiculo() {
		return CodigoEmpresaTipoVeiculo;
	}

	public void setCodigoEmpresaTipoVeiculo(Integer codigoEmpresaTipoVeiculo) {
		CodigoEmpresaTipoVeiculo = codigoEmpresaTipoVeiculo;
	}

	public Integer getCodigoLojaTipoVeiculo() {
		return CodigoLojaTipoVeiculo;
	}

	public void setCodigoLojaTipoVeiculo(Integer codigoLojaTipoVeiculo) {
		CodigoLojaTipoVeiculo = codigoLojaTipoVeiculo;
	}

	public Integer getCodigoTipoVeiculo() {
		return CodigoTipoVeiculo;
	}

	public void setCodigoTipoVeiculo(Integer codigoTipoVeiculo) {
		CodigoTipoVeiculo = codigoTipoVeiculo;
	}
}