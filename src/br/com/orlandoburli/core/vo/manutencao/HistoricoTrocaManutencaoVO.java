package br.com.orlandoburli.core.vo.manutencao;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class HistoricoTrocaManutencaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoTrocaManutencao;
	@Key @AutoIncrement
	private Integer CodigoHistoricoTrocaManutencao;
	
	private Timestamp DataHoraHistoricoTrocaManutencao;
	private String DescricaoHistoricoTrocaManutencao;
	private Integer CodigoSituacaoTrocaManutencao;
	
	private String FlagEmailEnviadoTrocaManutencao;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.DescricaoSituacaoTrocaManutencao " +
    		"FROM [schema].SituacaoTrocaManutencao b " +
    		"WHERE a.CodigoSituacaoTrocaManutencao = b.CodigoSituacaoTrocaManutencao)")
    private String DescricaoSituacaoTrocaManutencao;
    
    @Formula(getFormula="(SELECT b.FlagFinalSituacaoTrocaManutencao " +
    		"FROM [schema].SituacaoTrocaManutencao b " +
    		"WHERE a.CodigoSituacaoTrocaManutencao = b.CodigoSituacaoTrocaManutencao)")
    private String FlagFinalSituacaoTrocaManutencao;
    
    @Formula(getFormula="(SELECT b.FlagEnviaEmailSituacaoTrocaManutencao " +
    		"FROM [schema].SituacaoTrocaManutencao b " +
    		"WHERE a.CodigoSituacaoTrocaManutencao = b.CodigoSituacaoTrocaManutencao)")
    private String FlagEnviaEmailSituacaoTrocaManutencao;

    public Object clone() {
    	return this.clone();
    }
    
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

	public Integer getCodigoTrocaManutencao() {
		return CodigoTrocaManutencao;
	}

	public void setCodigoTrocaManutencao(Integer codigoTrocaManutencao) {
		CodigoTrocaManutencao = codigoTrocaManutencao;
	}

	public Integer getCodigoHistoricoTrocaManutencao() {
		return CodigoHistoricoTrocaManutencao;
	}

	public void setCodigoHistoricoTrocaManutencao(Integer codigoHistoricoTrocaManutencao) {
		CodigoHistoricoTrocaManutencao = codigoHistoricoTrocaManutencao;
	}

	public Timestamp getDataHoraHistoricoTrocaManutencao() {
		return DataHoraHistoricoTrocaManutencao;
	}

	public void setDataHoraHistoricoTrocaManutencao(Timestamp dataHoraHistoricoTrocaManutencao) {
		DataHoraHistoricoTrocaManutencao = dataHoraHistoricoTrocaManutencao;
	}

	public String getDescricaoHistoricoTrocaManutencao() {
		return DescricaoHistoricoTrocaManutencao;
	}

	public void setDescricaoHistoricoTrocaManutencao(String descricaoHistoricoTrocaManutencao) {
		DescricaoHistoricoTrocaManutencao = descricaoHistoricoTrocaManutencao;
	}

	public Integer getCodigoSituacaoTrocaManutencao() {
		return CodigoSituacaoTrocaManutencao;
	}

	public void setCodigoSituacaoTrocaManutencao(Integer codigoSituacaoTrocaManutencao) {
		CodigoSituacaoTrocaManutencao = codigoSituacaoTrocaManutencao;
	}

	public void setDescricaoSituacaoTrocaManutencao(String descricaoSituacaoTrocaManutencao) {
		DescricaoSituacaoTrocaManutencao = descricaoSituacaoTrocaManutencao;
	}

	public String getDescricaoSituacaoTrocaManutencao() {
		return DescricaoSituacaoTrocaManutencao;
	}

	public String getFlagEmailEnviadoTrocaManutencao() {
		return FlagEmailEnviadoTrocaManutencao;
	}

	public void setFlagEmailEnviadoTrocaManutencao(String flagEmailEnviadoTrocaManutencao) {
		FlagEmailEnviadoTrocaManutencao = flagEmailEnviadoTrocaManutencao;
	}

	public String getFlagFinalSituacaoTrocaManutencao() {
		return FlagFinalSituacaoTrocaManutencao;
	}

	public void setFlagFinalSituacaoTrocaManutencao(String flagFinalSituacaoTrocaManutencao) {
		FlagFinalSituacaoTrocaManutencao = flagFinalSituacaoTrocaManutencao;
	}

	public String getFlagEnviaEmailSituacaoTrocaManutencao() {
		return FlagEnviaEmailSituacaoTrocaManutencao;
	}

	public void setFlagEnviaEmailSituacaoTrocaManutencao(String flagEnviaEmailSituacaoTrocaManutencao) {
		FlagEnviaEmailSituacaoTrocaManutencao = flagEnviaEmailSituacaoTrocaManutencao;
	}
}