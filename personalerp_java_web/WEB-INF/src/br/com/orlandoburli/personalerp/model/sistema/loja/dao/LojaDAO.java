package br.com.orlandoburli.personalerp.model.sistema.loja.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;
import br.com.orlandoburli.personalerp.model.sistema.loja.vo.LojaVO;

public class LojaDAO extends BaseCadastroDAO<LojaVO>{
	
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
}
