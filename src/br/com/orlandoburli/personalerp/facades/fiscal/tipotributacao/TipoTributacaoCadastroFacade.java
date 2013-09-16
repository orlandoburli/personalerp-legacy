package br.com.orlandoburli.personalerp.facades.fiscal.tipotributacao;

import br.com.orlandoburli.core.dao.fiscal.TipoTributacaoDAO;
import br.com.orlandoburli.core.vo.fiscal.TipoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoTributacaoCadastroFacade extends BaseCadastroFlexFacade<TipoTributacaoVO, TipoTributacaoDAO>{

	private static final long serialVersionUID = 1L;
	
	public TipoTributacaoCadastroFacade() {
		addNewValidator(new NotEmptyValidator("IdentificacaoTipoTributacao", "Identifica��o"));
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("tipotributacao_uftipotributacao_fk") >= 0) {
			writeErrorMessage("Este Tipo de Tributa��o possui UF's de Origem Cadastradas.\nN�o � poss�vel excluir.");
			return;
		}
		super.doBeforeWriteSqlErro(e);
	}
}