package br.com.orlandoburli.core.vo.replicacao;

import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ReplicacaoExecucaoVO implements IValueObject {

	private static final long serialVersionUID = 2494527780534971122L;
	private boolean isNew;
	
	@Key
	private Integer IdReplicacao;
	@Key @AutoIncrement
	private Integer IdReplicacaoExecucao;
	private Timestamp DataHoraExecucao;
	private String UsuarioExecucao;
	private Integer IdDestinoReplicacao;

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

	public void setIdReplicacaoExecucao(Integer idReplicacaoExecucao) {
		IdReplicacaoExecucao = idReplicacaoExecucao;
	}

	public Integer getIdReplicacaoExecucao() {
		return IdReplicacaoExecucao;
	}

	public void setDataHoraExecucao(Timestamp dataHoraExecucao) {
		DataHoraExecucao = dataHoraExecucao;
	}

	public Timestamp getDataHoraExecucao() {
		return DataHoraExecucao;
	}

	public void setUsuarioExecucao(String usuarioExecucao) {
		UsuarioExecucao = usuarioExecucao;
	}

	public String getUsuarioExecucao() {
		return UsuarioExecucao;
	}

	public void setIdDestinoReplicacao(Integer idDestinoReplicacao) {
		IdDestinoReplicacao = idDestinoReplicacao;
	}

	public Integer getIdDestinoReplicacao() {
		return IdDestinoReplicacao;
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
