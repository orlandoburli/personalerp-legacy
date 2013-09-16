package br.com.orlandoburli.core.dao.replicacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.replicacao.ReplicacaoComandosVO;
import br.com.orlandoburli.core.vo.replicacao.ReplicacaoDestinoVO;

public class ReplicacaoComandosDAO extends BaseCadastroDAO<ReplicacaoComandosVO> {
	
	public static final String SQL_QUERY_PENDENTES = "select * from " + SystemManager.getSchemaName() + ".replicacaocomandos except select b.* from " + SystemManager.getSchemaName() + ".replicacaoexecucao a join " + SystemManager.getSchemaName() + ".replicacaocomandos b on a.idreplicacao = b.idreplicacao and and a.iddestinoreplicacao = ";
	
	private Connection conn;
	
	public List<ReplicacaoComandosVO> getListReplicacao(ReplicacaoDestinoVO destino) {
		List<ReplicacaoComandosVO> list = null;
		try {
			list = new ArrayList<ReplicacaoComandosVO>();
			DaoUtils.resultToList(ReplicacaoComandosVO.class, list, this.getRowSet(SQL_QUERY_PENDENTES + destino.getIdReplicacaoDestino()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean ExecuteReplicacao(ReplicacaoComandosVO vo) {
		PreparedStatement prep;
		try {
			prep = this.conn.prepareStatement(vo.getDadosReplicacao());
			return prep.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void start() {
		try {
			conn = getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void end() {
		try {
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void error() {
		try {
			this.conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
