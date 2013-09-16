package br.com.orlandoburli.core.vo.replicacao;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ReplicacaoComandosVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer IdReplicacao;
	private String OperacaoReplicacao;
	private Timestamp DataHoraReplicacao;
	private String UsuarioReplicacao;
	private String TabelaReplicacao;
	private String DadosReplicacao;
	private String DadosRollback;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setIdReplicacao(Integer idReplicacao) {
		IdReplicacao = idReplicacao;
	}

	public Integer getIdReplicacao() {
		return IdReplicacao;
	}

	public void setOperacaoReplicacao(String operacaoReplicacao) {
		OperacaoReplicacao = operacaoReplicacao;
	}

	public String getOperacaoReplicacao() {
		return OperacaoReplicacao;
	}

	public void setDataHoraReplicacao(Timestamp DataHoraReplicacao) {
		this.DataHoraReplicacao = DataHoraReplicacao;
	}

	public Timestamp getDataHoraReplicacao() {
		return DataHoraReplicacao;
	}

	public void setUsuarioReplicacao(String usuarioReplicacao) {
		UsuarioReplicacao = usuarioReplicacao;
	}

	public String getUsuarioReplicacao() {
		return UsuarioReplicacao;
	}

	public void setTabelaReplicacao(String tabelaReplicacao) {
		TabelaReplicacao = tabelaReplicacao;
	}

	public String getTabelaReplicacao() {
		return TabelaReplicacao;
	}

	public void setDadosReplicacao(String dadosReplicacao) {
		DadosReplicacao = dadosReplicacao;
	}

	public String getDadosReplicacao() {
		return DadosReplicacao;
	}

	public void setDadosRollback(String dadosRollback) {
		DadosRollback = dadosRollback;
	}

	public String getDadosRollback() {
		return DadosRollback;
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
}