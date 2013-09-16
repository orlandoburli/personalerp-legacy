package br.com.orlandoburli.personalerp.model.acesso.perfil.dao;
import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO extends BaseCadastroDAO<PerfilVO> {

    public List<PerfilVO> getListPerfisUsuario(UsuarioVO usuario) throws SQLException {
        try {
            String statement = "SELECT * FROM " + getTableName() + " A" + br;
            statement += "    JOIN " + getSchemaName() + ".PERFILUSUARIO B ON A.CODIGOPERFIL  = B.CODIGOPERFIL" + br;
            statement += "                        AND A.CODIGOEMPRESA = B.CODIGOEMPRESA" + br;
            statement += "                        AND A.CODIGOLOJA    = B.CODIGOLOJA" + br;
            statement += "WHERE B.USUARIOCODIGOUSUARIO = " + usuario.getCodigoUsuario() + br;
            statement += "  AND B.USUARIOCODIGOEMPRESA = " + usuario.getCodigoEmpresa() + br;
            statement += "  AND B.USUARIOCODIGOLOJA    = " + usuario.getCodigoLoja() + br;
            statement += " ORDER BY NomePerfil";

            List<PerfilVO> list = new ArrayList<PerfilVO>();
            
            DaoUtils.rowSetToList(PerfilVO.class, list, getRowSet(statement));

            return list;
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (IllegalArgumentException ex) {
        } catch (SecurityException ex) {
        } catch (InvocationTargetException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return null;
    }

	public List<PerfilVO> getListPerfisUsuarioSelecionado(UsuarioVO vo) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT a.*, CASE WHEN b.UsuarioCodigoUsuario IS NULL THEN 'N' ELSE 'S' END AS Permitido FROM " + getTableName() + " a" + br);
		sb.append("LEFT JOIN " + getSchemaName() + ".perfilusuario b ON a.CodigoEmpresa = b.CodigoEmpresa" + br);
		sb.append("                         AND a.CodigoLoja    = b.CodigoLoja" + br);
		sb.append("                         AND a.CodigoPerfil  = b.CodigoPerfil" + br);
		sb.append("                         AND b.UsuarioCodigoUsuario = " + vo.getCodigoUsuario() + br);
		sb.append("                         AND b.UsuarioCodigoEmpresa = " + vo.getCodigoEmpresa() + br);
		sb.append("                         AND b.UsuarioCodigoLoja    = " + vo.getCodigoLoja() + br);
		try {
			List<PerfilVO> list = new ArrayList<PerfilVO>();
            
            DaoUtils.rowSetToList(PerfilVO.class, list, getRowSet(sb.toString()));
            
            return list;
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (IllegalArgumentException ex) {
        } catch (SecurityException ex) {
        } catch (InvocationTargetException ex) {
        } catch (ClassNotFoundException ex) {
        }
		return null;
	}
}