package br.com.orlandoburli.personalerp.facades.estoque.modeloetiqueta;

import br.com.orlandoburli.core.dao.estoque.etiqueta.ModeloEtiquetaDAO;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloEtiquetaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ModeloEtiquetaConsultaFacade extends BaseConsultaFlexFacade<ModeloEtiquetaVO, ModeloEtiquetaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeModeloEtiqueta(getFiltro() + "%");
		setOrderFields("NomeModeloEtiqueta");
	}
}