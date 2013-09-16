package br.com.orlandoburli.personalerp.facades.sistema.grupoempresaloja;

import java.sql.SQLException;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaLojaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class GrupoEmpresaLojaCadastroFacade extends BaseCadastroFlexFacade<GrupoEmpresaLojaVO, GrupoEmpresaLojaDAO>{

	private static final long serialVersionUID = 1L;

	public GrupoEmpresaLojaCadastroFacade() {
		addNewValidator(new NotEmptyValidator("CodigoLoja", "Loja"));
	}
	
	@IgnoreMethodAuthentication
	public void lojas() {
		// Lista as lojas que nao estao no grupo
		String sql = "";
		
		if (!getOperacao().equals(Constants.EXCLUIR) && !getOperacao().equals(Constants.VISUALIZAR)) {
			sql = " (CodigoEmpresa, CodigoLoja) NOT IN (SELECT x.CodigoEmpresa, x.CodigoLoja FROM " + getGenericDao().getSchemaName() + ".GrupoEmpresaLoja x WHERE x.CodigoGrupoEmpresa = " + getVo().getCodigoGrupoEmpresa() + ")";
		}
		
		LojaVO lojaFilter = new LojaVO();
		getGenericDao().setSpecialCondition(sql);

		try {
			write(Utils.voToXml(getGenericDao().getList(lojaFilter, "NomeLoja")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}