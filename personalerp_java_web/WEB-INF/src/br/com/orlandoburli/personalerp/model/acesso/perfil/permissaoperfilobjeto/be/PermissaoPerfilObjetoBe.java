package br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.dao.PermissaoPerfilObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.vo.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;

public class PermissaoPerfilObjetoBe extends BaseBe<PermissaoPerfilObjetoVO, PermissaoPerfilObjetoDAO>{

	public List<PermissaoPerfilObjetoVO> getList(PerfilVO perfil) throws ListException {
		try {
			return getDao().getList(perfil);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}
}