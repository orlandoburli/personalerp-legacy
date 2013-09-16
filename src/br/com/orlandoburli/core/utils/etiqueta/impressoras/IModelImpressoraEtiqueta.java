package br.com.orlandoburli.core.utils.etiqueta.impressoras;

import java.util.List;

import br.com.orlandoburli.core.utils.etiqueta.ItemImpressaoVO;
import br.com.orlandoburli.core.vo.estoque.etiqueta.ModeloEtiquetaVO;

public interface IModelImpressoraEtiqueta {

	public void imprimir(List<ItemImpressaoVO> itens, ModeloEtiquetaVO modelo);
	
}