package br.com.orlandoburli.personalerp.model.acesso.usuario.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.dao.UsuarioDAO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioInvalidoException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;

public class UsuarioBe extends BaseBe<UsuarioVO, UsuarioDAO> {

	public UsuarioVO login(String login, String senha) throws UsuarioInvalidoException, ListException {
		if (login == null || login.trim().equals("")) {
			throw new UsuarioInvalidoException("Login Obrigatório!");
		}

		UsuarioVO usuarioFilter = new UsuarioVO();
		usuarioFilter.setLoginUsuario(login);
		List<UsuarioVO> listUsuarios;

		try {
			listUsuarios = new UsuarioDAO().getList(usuarioFilter);
		} catch (SQLException e) {
			throw new ListException();
		}

		if (listUsuarios.size() == 1) { // Tem que retornar somente UM usuario
			usuarioFilter = listUsuarios.get(0);
			if (!usuarioFilter.getSenhaUsuario().equals(senha) || !usuarioFilter.isAtivo()) {
				throw new UsuarioInvalidoException();
			}
		} else {
			throw new UsuarioInvalidoException();
		}
		
		return listUsuarios.get(0);
	}
}
