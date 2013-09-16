package br.com.orlandoburli.core.dao.acesso;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;

public class PermissaoPerfilObjetoDAO extends BaseCadastroDAO<PermissaoPerfilObjetoVO> {

	public List<PermissaoPerfilObjetoVO> getList(PerfilVO perfil) throws SQLException {
		PermissaoPerfilObjetoVO filter = new PermissaoPerfilObjetoVO();
		filter.setCodigoEmpresa(perfil.getCodigoEmpresa());
		filter.setCodigoLoja(perfil.getCodigoLoja());
		filter.setCodigoPerfil(perfil.getCodigoPerfil());
		return getList(filter);
	}

	public int getListCountByPerfil(PerfilVO perfil, int pageSize, String filtro) throws SQLException {
		try {
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT COUNT(*) AS Total FROM " + getSchemaName() + ".Objeto a" + br);
			sb.append(" WHERE a.DescricaoObjeto ILIKE '" + filtro + "'" + br);
			sb.append("" + br);
			
			RowSet row = getRowSet(sb.toString());
            row.next();

            int count = row.getInt(1);
            int inteiro = count / pageSize;
            int resto = count % pageSize;
            inteiro += (resto > 0)?1:0;
            
            return inteiro;
            
            
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        }
		return 0;
	}

	public List<PermissaoPerfilObjetoVO> getListByPerfil(PerfilVO perfil, int pageSize, int pageNumber, String filtro) throws SQLException {
		try {
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT a.*, b.*, CASE WHEN b.CodigoPerfil IS NULL THEN 'N' ELSE 'S' END AS Permitido FROM " + getSchemaName() + ".Objeto a" + br);
			sb.append("LEFT JOIN " + getSchemaName() + ".PermissaoPerfilObjeto b ON a.CodigoObjeto = b.CodigoObjeto" + br);
			sb.append("                                 AND b.CodigoEmpresa = " + perfil.getCodigoEmpresa() + br);
			sb.append("                                 AND b.CodigoLoja    = " + perfil.getCodigoLoja() + br);
			sb.append("                                 AND b.CodigoPerfil  = " + perfil.getCodigoPerfil() + br);
			sb.append(" WHERE a.DescricaoObjeto ILIKE '" + filtro + "'" + br);
			sb.append("" + br);
			if (pageNumber <= 0) {
				pageNumber = 1;
			}
			sb.append(" LIMIT " + pageSize);
			int begin = ((pageSize) * (pageNumber - 1));
			sb.append(" OFFSET " + begin);
			
			List<PermissaoPerfilObjetoVO> list = new ArrayList<PermissaoPerfilObjetoVO>();
            
            DaoUtils.rowSetToList(PermissaoPerfilObjetoVO.class, list, getRowSet(sb.toString()));
            
            return list;
        } catch (InstantiationException ex) {
        	ex.printStackTrace();
        } catch (IllegalAccessException ex) {
        	ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
        	ex.printStackTrace();
        } catch (SecurityException ex) {
        	ex.printStackTrace();
        } catch (InvocationTargetException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
        	ex.printStackTrace();
        }
		return null;
	}
}