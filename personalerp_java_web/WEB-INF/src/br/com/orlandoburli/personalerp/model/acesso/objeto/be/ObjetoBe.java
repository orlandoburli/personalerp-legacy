package br.com.orlandoburli.personalerp.model.acesso.objeto.be;

import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.dao.ObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.vo.ObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;

public class ObjetoBe extends BaseBe<ObjetoVO, ObjetoDAO>{
	
	public List<ObjetoVO> getListByPerfil(PerfilVO perfil) {
		return getDao().getListByPerfil(perfil);
	}
}