package br.com.orlandoburli.core.dao.sistema;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpresaDAO extends BaseCadastroDAO<EmpresaVO> {

    public List<EmpresaVO> getListEmpresasUsuario(UsuarioVO usuario) {
        try {

            String statement = "SELECT DISTINCT a.* FROM " + getTableName() + " a \n";
            statement += "  JOIN " + getSchemaName() + ".UsuarioLoja b ON a.CodigoEmpresa = b.CodigoEmpresaVinculada \n";
            statement += " WHERE b.CodigoUsuario = " + usuario.getCodigoUsuario() + "\n";
            statement += "   AND b.CodigoEmpresa = " + usuario.getCodigoEmpresa() + "\n";
            statement += "   AND b.CodigoLoja    = " + usuario.getCodigoLoja();
            
            List<EmpresaVO> list = new ArrayList<EmpresaVO>();
            DaoUtils.rowSetToList(EmpresaVO.class, list, getRowSet(statement));

            return list;
        } catch (InstantiationException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}