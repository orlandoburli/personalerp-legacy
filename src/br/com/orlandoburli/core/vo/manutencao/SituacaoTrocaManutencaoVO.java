package br.com.orlandoburli.core.vo.manutencao;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class SituacaoTrocaManutencaoVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;

	@Key @AutoIncrement
	private Integer CodigoSituacaoTrocaManutencao;
	
	private String DescricaoSituacaoTrocaManutencao;
	private String FlagFinalSituacaoTrocaManutencao;
	private String FlagEnviaEmailSituacaoTrocaManutencao;
	
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

	public void setCodigoSituacaoTrocaManutencao(Integer codigoSituacaoTrocaManutencao) {
		CodigoSituacaoTrocaManutencao = codigoSituacaoTrocaManutencao;
	}

	public Integer getCodigoSituacaoTrocaManutencao() {
		return CodigoSituacaoTrocaManutencao;
	}

	public void setDescricaoSituacaoTrocaManutencao(String descricaoSituacaoTrocaManutencao) {
		DescricaoSituacaoTrocaManutencao = descricaoSituacaoTrocaManutencao;
	}

	public String getDescricaoSituacaoTrocaManutencao() {
		return DescricaoSituacaoTrocaManutencao;
	}

	public void setFlagFinalSituacaoTrocaManutencao(String flagFinalSituacaoTrocaManutencao) {
		FlagFinalSituacaoTrocaManutencao = flagFinalSituacaoTrocaManutencao;
	}

	public String getFlagFinalSituacaoTrocaManutencao() {
		return FlagFinalSituacaoTrocaManutencao;
	}

	public void setFlagEnviaEmailSituacaoTrocaManutencao(String flagEnviaEmailSituacaoTrocaManutencao) {
		FlagEnviaEmailSituacaoTrocaManutencao = flagEnviaEmailSituacaoTrocaManutencao;
	}

	public String getFlagEnviaEmailSituacaoTrocaManutencao() {
		return FlagEnviaEmailSituacaoTrocaManutencao;
	}
}