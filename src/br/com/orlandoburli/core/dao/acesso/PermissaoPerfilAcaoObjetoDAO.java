package br.com.orlandoburli.core.dao.acesso;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;

public class PermissaoPerfilAcaoObjetoDAO extends BaseCadastroDAO<PermissaoPerfilAcaoObjetoVO> {

	public List<PermissaoPerfilAcaoObjetoVO> getListByPerfil(PerfilVO perfil, ObjetoVO objeto) {
		List<PermissaoPerfilAcaoObjetoVO> list = new ArrayList<PermissaoPerfilAcaoObjetoVO>();
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT \n");
		sb.append("	" + perfil.getCodigoEmpresa() + " as codigoempresa, \n");
		sb.append("	" + perfil.getCodigoLoja()    + " as codigoloja, \n");
		sb.append("	" + perfil.getCodigoPerfil()  + " as codigoperfil, \n");
		sb.append(" a.codigoobjeto, b.codigoacaoobjeto, a.nomeobjeto, b.nomeacaoobjeto,\n");
		sb.append(" COALESCE((SELECT CAST('S' AS VARCHAR)");
		sb.append("		    FROM " + getSchemaName() + ".permissaoperfilacaoobjeto c \n");
		sb.append("		   WHERE c.codigoobjeto     = a.codigoobjeto\n");
		sb.append("		     AND c.codigoacaoobjeto = b.codigoacaoobjeto\n");
		sb.append("		     AND c.codigoempresa    = " + perfil.getCodigoEmpresa() + "\n");
		sb.append("	         AND c.codigoloja   	= " + perfil.getCodigoLoja() + "\n");
		sb.append("		     AND c.codigoperfil     = " + perfil.getCodigoPerfil() + "), 'N') AS Permitido\n");
		sb.append("   FROM " + getSchemaName() + ".objeto a\n");
		sb.append("   JOIN " + getSchemaName() + ".acaoobjeto b ON a.codigoobjeto = b.codigoobjeto\n");
		sb.append("  WHERE b.codigoobjeto = " + objeto.getCodigoObjeto());
		
		try {
			DaoUtils.rowSetToList(PermissaoPerfilAcaoObjetoVO.class, list, this.getRowSet(sb.toString()));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<PermissaoPerfilAcaoObjetoVO> getList(PerfilVO perfil) throws SQLException {
		PermissaoPerfilAcaoObjetoVO filter = new PermissaoPerfilAcaoObjetoVO();
		filter.setCodigoEmpresa(perfil.getCodigoEmpresa());
		filter.setCodigoLoja(perfil.getCodigoLoja());
		filter.setCodigoPerfil(perfil.getCodigoPerfil());
		return getList(filter);
	}
}