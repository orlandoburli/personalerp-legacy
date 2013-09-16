package br.com.orlandoburli.personalerp.model.acesso.usuario.perfilusuario.be;

import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.perfil.be.PerfilBe;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioSemPerfilException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.perfilusuario.dao.PerfilUsuarioDAO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.perfilusuario.vo.PerfilUsuarioVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;

public class PerfilUsuarioBe extends BaseBe<PerfilUsuarioVO, PerfilUsuarioDAO> {

	public List<PerfilVO> getPerfilUsuario(UsuarioVO usuario) throws ListException, UsuarioSemPerfilException {
		List<PerfilVO> list = new PerfilBe().getListPerfisUsuario(usuario);

		if (list.size() <= 0) {
			throw new UsuarioSemPerfilException();
		}
		return list;
	}
}
