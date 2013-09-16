package br.com.orlandoburli.personalerp.model.sistema.empresa.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioSemEmpresaException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.dao.EmpresaDAO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;

public class EmpresaBe extends BaseBe<EmpresaVO, EmpresaDAO>{

	 public List<EmpresaVO> getListEmpresasUsuario(UsuarioVO usuario) throws ListException, UsuarioSemEmpresaException {
		 try {
			List<EmpresaVO> list = getDao().getListEmpresasUsuario(usuario);
			
			if (list.size() <= 0) {
				throw new UsuarioSemEmpresaException();
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	 }
}

