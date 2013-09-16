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
			writeErrorMessage("Estado j� foi inserido neste tipo de tributa��o!", "UFOrigemTributacao");
			return;
		} else if (e.getMessage().indexOf("uftipotributacao_tributacao_fk") >= 0) {
			writeErrorMessage("Este registro possui v�nculos no Cadastro de Tributa��es.\nN�o � poss�vel excluir.");
			return;
		}
		super.doBeforeWriteSqlErro(e);
	}
}
