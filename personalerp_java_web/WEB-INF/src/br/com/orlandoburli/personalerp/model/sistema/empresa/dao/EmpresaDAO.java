package br.com.orlandoburli.personalerp.model.sistema.empresa.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;

public class EmpresaDAO extends BaseCadastroDAO<EmpresaVO>{
	
	 public List<EmpresaVO> getListEmpresasUsuario(UsuarioVO usuario) throws SQLException {
	        try {

	            String statement = "SELECT DISTINCT a.* FROM " + getTableName() + " a \n";
	            statement += "  JOIN " + getSchemaName() + ".UsuarioLoja b ON a.CodigoEmpresa = b.CodigoEmpresaVinculada \n";
	            statement += " WHERE b.CodigoUsuario = " + usuario.getCodigoUsuario() + "\n";
	            statement += "   AND b.CodigoEmpresa = " + usuario.getCodigoEmpresa() + "\n";
	            statement += "   AND b.CodigoLoja    = " + usuario.getCodigoLoja();
	            statement += "  ORDER BY a.RazaoSocialEmpresa";
	            
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
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(EmpresaDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return null;
	    }
}
