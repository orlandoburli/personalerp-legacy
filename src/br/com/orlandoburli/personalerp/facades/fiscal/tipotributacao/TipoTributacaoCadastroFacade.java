package br.com.orlandoburli.personalerp.facades.fiscal.tipotributacao;

import br.com.orlandoburli.core.dao.fiscal.TipoTributacaoDAO;
import br.com.orlandoburli.core.vo.fiscal.TipoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoTributacaoCadastroFacade extends BaseCadastroFlexFacade<TipoTributacaoVO, TipoTributacaoDAO>{

	private static final long serialVersionUID = 1L;
	
	public TipoTributacaoCadastroFacade() {
		addNewValidator(new NotEmptyValidator("IdentificacaoTipoTributacao", "Identificação"));
	}
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("tipotributacao_uftipotributacao_fk") >= 0) {
			writeErrorMessage("Este Tipo de Tributação possui UF's de Origem Cadastradas.\nNão é possível excluir.");
			return;
		}
		super.doBeforeWriteSqlErro(e);
	}
}