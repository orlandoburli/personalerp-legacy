package br.com.orlandoburli.core.vo.manutencao;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TipoFlagTrocaManutencaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer CodigoTipoFlagTrocaManutencao;
	private String NomeTipoFlagTrocaManutencao;
	
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

	public void setCodigoTipoFlagTrocaManutencao(Integer codigoTipoFlagTrocaManutencao) {
		CodigoTipoFlagTrocaManutencao = codigoTipoFlagTrocaManutencao;
	}

	public Integer getCodigoTipoFlagTrocaManutencao() {
		return CodigoTipoFlagTrocaManutencao;
	}

	public void setNomeTipoFlagTrocaManutencao(String nomeTipoFlagTrocaManutencao) {
		NomeTipoFlagTrocaManutencao = nomeTipoFlagTrocaManutencao;
	}

	public String getNomeTipoFlagTrocaManutencao() {
		return NomeTipoFlagTrocaManutencao;
	}
}