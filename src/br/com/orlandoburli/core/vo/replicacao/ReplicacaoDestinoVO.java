package br.com.orlandoburli.core.vo.replicacao;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ReplicacaoDestinoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key @AutoIncrement
	private Integer IdReplicacaoDestino;
	private String HostReplicacaoDestino;
	private int PortaReplicaocaDestino;
	private boolean Ativo;
	private Integer UltimoIdReplicacao;
	
	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public void setIdReplicacaoDestino(Integer idReplicacaoDestino) {
		IdReplicacaoDestino = idReplicacaoDestino;
	}

	public Integer getIdReplicacaoDestino() {
		return IdReplicacaoDestino;
	}

	public void setHostReplicacaoDestino(String hostReplicacaoDestino) {
		HostReplicacaoDestino = hostReplicacaoDestino;
	}

	public String getHostReplicacaoDestino() {
		return HostReplicacaoDestino;
	}

	public void setPortaReplicaocaDestino(int portaReplicaocaDestino) {
		PortaReplicaocaDestino = portaReplicaocaDestino;
	}

	public int getPortaReplicaocaDestino() {
		return PortaReplicaocaDestino;
	}

	public void setAtivo(boolean ativo) {
		Ativo = ativo;
	}

	public boolean isAtivo() {
		return Ativo;
	}

	public void setUltimoIdReplicacao(Integer ultimoIdReplicacao) {
		UltimoIdReplicacao = ultimoIdReplicacao;
	}

	public Integer getUltimoIdReplicacao() {
		return UltimoIdReplicacao;
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
