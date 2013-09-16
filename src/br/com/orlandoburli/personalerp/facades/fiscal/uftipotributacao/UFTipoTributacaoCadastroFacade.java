package br.com.orlandoburli.personalerp.facades.fiscal.uftipotributacao;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.base.EstadoDAO;
import br.com.orlandoburli.core.dao.fiscal.UFTipoTributacaoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.fiscal.UFTipoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class UFTipoTributacaoCadastroFacade extends BaseCadastroFlexFacade<UFTipoTributacaoVO, UFTipoTributacaoDAO>{

	private static final long serialVersionUID = 1L;

	@IgnoreMethodAuthentication
	public void estados() {
		try {
			EstadoDAO _dao = new EstadoDAO();
			write(Utils.voToXml(_dao.getList(null, "NomeEstado"), _dao.getListCount()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("uftipotributacao_pk") >= 0) {
			writeErrorMessage("Estado já foi inserido neste tipo de tributação!", "UFOrigemTributacao");
			return;
		} else if (e.getMessage().indexOf("uftipotributacao_tributacao_fk") >= 0) {
			writeErrorMessage("Este registro possui vínculos no Cadastro de Tributações.\nNão é possível excluir.");
			return;
		}
		super.doBeforeWriteSqlErro(e);
	}
}
