package br.com.orlandoburli.core.vo.rh;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class MotivoFaltaFuncionarioVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoMotivoFaltaFuncionario;
	
	private String DescricaoMotivoFaltaFuncionario;
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
	public void setCodigoUsuarioLog(Integer CodigoUsuarioLog) {
		this.CodigoUsuarioLog = CodigoUsuarioLog;
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

	public Integer getCodigoMotivoFaltaFuncionario() {
		return CodigoMotivoFaltaFuncionario;
	}

	public void setCodigoMotivoFaltaFuncionario(Integer codigoMotivoFaltaFuncionario) {
		CodigoMotivoFaltaFuncionario = codigoMotivoFaltaFuncionario;
	}

	public String getDescricaoMotivoFaltaFuncionario() {
		return DescricaoMotivoFaltaFuncionario;
	}

	public void setDescricaoMotivoFaltaFuncionario(
			String descricaoMotivoFaltaFuncionario) {
		DescricaoMotivoFaltaFuncionario = descricaoMotivoFaltaFuncionario;
	}

	public String getFlagParcialMotivoFaltaFuncionario() {
		return FlagParcialMotivoFaltaFuncionario;
	}

	public void setFlagParcialMotivoFaltaFuncionario(
			String flagParcialMotivoFaltaFuncionario) {
		FlagParcialMotivoFaltaFuncionario = flagParcialMotivoFaltaFuncionario;
	}
}