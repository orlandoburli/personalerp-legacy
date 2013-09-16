package br.com.orlandoburli.core.utils.etiqueta;

import java.io.Serializable;

import br.com.orlandoburli.core.vo.estoque.ProdutoVO;

public class ItemImpressaoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ProdutoVO Produto;
	private Integer QuantidadeCopias;
	
	public ItemImpressaoVO(ProdutoVO produto, Integer quantidadeCopias) {
		setProduto(produto);
		setQuantidadeCopias(quantidadeCopias);
	}
	
	public ProdutoVO getProduto() {
		return Produto;
	}
	public void setProduto(ProdutoVO produto) {
		Produto = produto;
	}
	public Integer getQuantidadeCopias() {
		return QuantidadeCopias;
	}
	public void setQuantidadeCopias(Integer quantidadeCopias) {
		QuantidadeCopias = quantidadeCopias;
	}
}