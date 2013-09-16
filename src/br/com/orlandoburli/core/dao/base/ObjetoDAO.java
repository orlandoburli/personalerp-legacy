package br.com.orlandoburli.core.dao.base;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.base.ObjetoVO;

public class ObjetoDAO extends BaseCadastroDAO<ObjetoVO> {

	public List<ObjetoVO> getListByPerfil(PerfilVO perfil) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from " + getSchemaName() + ".objeto\n");
		sb.append("where codigoobjeto\n");
		sb.append("   not in (select codigoobjeto\n");
		sb.append("	     from " + getSchemaName() + ".permissaoperfilobjeto a\n");
		sb.append("	    where a.codigoempresa = " + perfil.getCodigoEmpresa() + "\n");
		sb.append("	      and a.codigoloja    = " + perfil.getCodigoLoja() + "\n");
		sb.append("	      and a.codigoperfil  = " + perfil.getCodigoPerfil() + ")\n");
		
		List<ObjetoVO> list = new ArrayList<ObjetoVO>();
		
		try {
			DaoUtils.rowSetToList(ObjetoVO.class, list, this.getRowSet(sb.toString()));
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
}