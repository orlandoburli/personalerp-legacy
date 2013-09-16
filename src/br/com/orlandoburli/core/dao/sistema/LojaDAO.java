package br.com.orlandoburli.core.dao.sistema;


import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LojaDAO extends BaseCadastroDAO<LojaVO> {

    public List<LojaVO> getListLojasUsuario(UsuarioVO usuario, EmpresaVO empresa) throws SQLException {
        try {

            String statement = "SELECT a.*, \n";
            statement += "(select c.siglaestado from " + getSchemaName() + ".cidade b join " + getSchemaName() + ".estado c on c.codigoestado = b.codigoestado where b.codigocidade = a.codigocidadeloja) as UFLoja, \n";
            statement += "(select c.regiaoestado from " + getSchemaName() + ".cidade b join " + getSchemaName() + ".estado c on c.codigoestado = b.codigoestado where b.codigocidade = a.codigocidadeloja) as RegiaoLoja \n";            
            statement += " FROM " + getTableName() + " a \n";            
            statement += "  JOIN " + getSchemaName() + ".UsuarioLoja b ON a.CodigoEmpresa = b.CodigoEmpresaVinculada AND a.CodigoLoja = b.CodigoLojaVinculada \n";
            statement += " WHERE b.CodigoUsuario = " + usuario.getCodigoUsuario() + "\n";
            statement += "   AND b.CodigoEmpresa = " + usuario.getCodigoEmpresa() + "\n";
            statement += "   AND b.CodigoLoja    = " + usuario.getCodigoLoja() + "\n";
            
            if (empresa != null) {
                statement += "   AND b.CodigoEmpresaVinculada = " + empresa.getCodigoEmpresa();
            }
            
            statement += " ORDER BY a.NomeLoja";

            List<LojaVO> list = new ArrayList<LojaVO>();

            DaoUtils.rowSetToList(LojaVO.class, list, getRowSet(statement));

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

	public List<LojaVO> getListLojasUsuarioSelecionadas(UsuarioVO vo) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT a.*, e.RazaoSocialEmpresa, CASE WHEN b.CodigoUsuario IS NULL THEN 'N' ELSE 'S' END AS Permitido FROM " + getSchemaName() + ".Loja a" + br);
		sb.append("     JOIN " + getSchemaName() + ".Empresa e ON e.CodigoEmpresa = a.CodigoEmpresa" + br);
		sb.append("LEFT JOIN " + getSchemaName() + ".UsuarioLoja b ON a.CodigoEmpresa = b.CodigoEmpresaVinculada" + br);
		sb.append("                       AND a.CodigoLoja    = b.CodigoLojaVinculada" + br);
		sb.append("                       AND b.CodigoUsuario = " + vo.getCodigoUsuario() + br);
		sb.append("                       AND b.CodigoEmpresa = " + vo.getCodigoEmpresa() + br);
		sb.append("                       AND b.CodigoLoja    = " + vo.getCodigoLoja() + br);
		
		List<LojaVO> list = new ArrayList<LojaVO>();

        try {
			DaoUtils.rowSetToList(LojaVO.class, list, getRowSet(sb.toString()));
			return list;
		} catch (IllegalArgumentException e) {
		} catch (SecurityException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (ClassNotFoundException e) {
		}
		return null;
	}
}