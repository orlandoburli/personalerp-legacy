package br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.dao.PermissaoPerfilAcaoObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.vo.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;

public class PermissaoPerfilAcaoObjetoBe extends BaseBe<PermissaoPerfilAcaoObjetoVO, PermissaoPerfilAcaoObjetoDAO>{
	
	public List<PermissaoPerfilAcaoObjetoVO> getList(PerfilVO perfil) throws ListException {
		try {
			return getDao().getList(perfil);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}
}
