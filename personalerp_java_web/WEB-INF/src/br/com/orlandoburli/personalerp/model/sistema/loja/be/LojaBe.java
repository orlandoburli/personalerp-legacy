package br.com.orlandoburli.personalerp.model.sistema.loja.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;
import br.com.orlandoburli.personalerp.model.sistema.loja.dao.LojaDAO;
import br.com.orlandoburli.personalerp.model.sistema.loja.vo.LojaVO;

public class LojaBe extends BaseBe<LojaVO, LojaDAO>{
	
	public List<LojaVO> getListLojasUsuario(UsuarioVO usuario, EmpresaVO empresa) throws ListException  {
		try {
			return getDao().getListLojasUsuario(usuario, empresa);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}
}
