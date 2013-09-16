package br.com.orlandoburli.personalerp.model.acesso.perfil.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.perfil.dao.PerfilDAO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;

public class PerfilBe extends BaseBe<PerfilVO, PerfilDAO> {

	public List<PerfilVO> getListPerfisUsuario(UsuarioVO usuario) throws ListException {
		try {
			return getDao().getListPerfisUsuario(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}
}