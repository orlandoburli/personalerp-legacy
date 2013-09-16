package br.com.orlandoburli.personalerp.facades.replicacao.pdv;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.rowset.CachedRowSet;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.BaseDAO;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.replicacao.pdv.ColunaTabelaReplicacaoPdvDAO;
import br.com.orlandoburli.core.dao.replicacao.pdv.TabelaReplicacaoPdvDAO;
import br.com.orlandoburli.core.utils.Criptografia;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.replicacao.pdv.ColunaTabelaReplicacaoPdvVO;
import br.com.orlandoburli.core.vo.replicacao.pdv.TabelaReplicacaoPdvVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class ReplicacaoPdvFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	private String FlagFull = "S";

	public void dados() {
		try {
			GenericDAO dao = new GenericDAO();
			dao.setAutoCommit(false);

			BaseDAO baseDao = new BaseDAO() {};
			TabelaReplicacaoPdvDAO tabRepDao = new TabelaReplicacaoPdvDAO();
			ColunaTabelaReplicacaoPdvDAO colDao = new ColunaTabelaReplicacaoPdvDAO();

			tabRepDao.mergeDAO(dao);
			colDao.mergeDAO(dao);
			baseDao.mergeDAO(dao);

			StringBuilder sb = new StringBuilder();

			sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><dados>");

			List<TabelaReplicacaoPdvVO> listTabelas = tabRepDao.getList(null, "OrdemTabela ASC");

			for (TabelaReplicacaoPdvVO tabelaRep : listTabelas) {

				String sql = "SELECT ";

				ColunaTabelaReplicacaoPdvVO colunaFilter = new ColunaTabelaReplicacaoPdvVO();
				colunaFilter.setNomeTabela(tabelaRep.getNomeTabela());

				List<ColunaTabelaReplicacaoPdvVO> listColunas = colDao.getList(colunaFilter);

				String colunas = "";

				for (ColunaTabelaReplicacaoPdvVO coluna : listColunas) {
					if (coluna.getFormulaColuna() == null || coluna.getFormulaColuna().trim().equals("")) {
						colunas += coluna.getNomeColuna() + ", ";
					} else {
						colunas += "(" + coluna.getFormulaColuna() + ") AS " + coluna.getNomeColuna() + ", ";
					}
				}

				colunas = colunas.substring(0, colunas.lastIndexOf(", "));

				sql += colunas + " FROM " + dao.getSchemaName() + "." + tabelaRep.getNomeTabela() + " tabela";

				CachedRowSet rowset = baseDao.getRowSet(sql);
				rowset.beforeFirst();

				// Monta o xml
				sb.append("<Tabela nome=\"" + tabelaRep.getNomeTabela() + "\">");

				while (rowset.next()) {
					sb.append("<Registro>");

					for (ColunaTabelaReplicacaoPdvVO coluna : listColunas) {
						sb.append("<Campo nome=\"" + coluna.getNomeColuna() + "\" chave=\"" + coluna.getChaveColuna() + "\"><![CDATA[");
						sb.append(rowset.getObject(coluna.getNomeColuna()));
						sb.append("]]></Campo>");
					}
					sb.append("</Registro>");
				}
				sb.append("</Tabela>");
			}

			sb.append("</dados>");
			dao.commit();
			
			String temp64 = Utils.encode64(sb.toString().getBytes());

			Criptografia cripto = Criptografia.newInstance(SystemManager.getProperty("licence.key"));

			String dadosCripto = cripto.cripto(temp64);

			String dadosCripto64 = Utils.encode64(dadosCripto.getBytes());

			write(dadosCripto64);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

	public String getFlagFull() {
		return FlagFull;
	}

	public void setFlagFull(String flagFull) {
		FlagFull = flagFull;
	}
}
