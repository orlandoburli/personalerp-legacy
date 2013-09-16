package br.com.orlandoburli.personalerp.facades.estoque.levantamento.levantamentoestoqueitem;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.levantamento.LevantamentoEstoqueItemDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.levantamento.LevantamentoEstoqueItemVO;
import br.com.orlandoburli.core.vo.estoque.levantamento.LevantamentoEstoqueVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class LevantamentoEstoqueItemCadastroFacade extends BaseCadastroFlexFacade<LevantamentoEstoqueItemVO, LevantamentoEstoqueItemDAO> {

	private static final long serialVersionUID = 1L;
	
	private String CodigoReferenciaPesquisa;

	public LevantamentoEstoqueItemCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoProduto", "Produto", "Produto"));
		addNewValidator(new NotEmptyValidator("QuantidadeDigitadaItemLevantamentoEstoque", "Quantidade"));
	}

	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("unq_levantamentoestoqueitem_produto") > 0) {
			writeErrorMessage("Este produto já foi lançado neste levantamento!");
		} else {
			super.doBeforeWriteSqlErro(e);
		}
	}

	private boolean checkAlterarLevantamento() {
		try {

			LevantamentoEstoqueVO levantamento = new LevantamentoEstoqueVO();
			levantamento.setCodigoEmpresa(getVo().getCodigoEmpresa());
			levantamento.setCodigoLoja(getVo().getCodigoLoja());
			levantamento.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());

			levantamento = (LevantamentoEstoqueVO) getGenericDao().get(levantamento);

			if (levantamento == null) {
				this.messages.add(new MessageVO("Levantamento não encontrado!"));
				return false;
			}

			if (levantamento.getStatusLevantamentoEstoque() == null || !levantamento.getStatusLevantamentoEstoque().equals("A")) {
				this.messages.add(new MessageVO("Levantamento já foi processado e não pode ser alterado!"));
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean doBeforeSave() throws SQLException {
		getVo().setQuantidadeDigitadaItemLevantamentoEstoque(getVo().getQuantidadeDisplayItemLevantamentoEstoque().add(getVo().getQuantidadeGavetaItemLevantamentoEstoque()));
		if (!checkAlterarLevantamento()) {
			return false;
		}
		return super.doBeforeSave();
	}

	@Override
	public boolean doBeforeDelete() throws SQLException {
		if (!checkAlterarLevantamento()) {
			return false;
		}
		return super.doBeforeDelete();
	}

	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			ProdutoVO produtoFilter = new ProdutoVO();
			produtoFilter.setSituacaoProduto("N");
			List<IValueObject> produtos = getGenericDao().getList(produtoFilter);
			write(Utils.voToXml(produtos, produtos.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void codigobarra() {
		
		System.out.println("Buscando produto...");
		
		// Primeiro tenta converter pra Integer
		String codigoReferencia = "";
		
		try {
			codigoReferencia = new Integer(Integer.parseInt(getCodigoReferenciaPesquisa())).toString();
		} catch (NullPointerException e) {
			codigoReferencia = "";
		} catch (NumberFormatException e){
			codigoReferencia = getCodigoReferenciaPesquisa();
		}
		
		if (codigoReferencia == null || codigoReferencia.trim().equals("")) {
			return;
		}
		
		ProdutoVO produto = new ProdutoVO();
		produto.setCodigoReferenciaProduto(codigoReferencia);
		
		try {
			List<ProdutoVO> listProd = new ProdutoDAO().getList(produto);
			if (listProd.size() > 0) {
				write(Utils.voToXml(listProd.get(0)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void adicionarcodigobarra() {

		try {
			LevantamentoEstoqueItemVO levItemFilter = new LevantamentoEstoqueItemVO();
			
			levItemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			levItemFilter.setCodigoLoja(getVo().getCodigoLoja());
			levItemFilter.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());
			
			levItemFilter.setCodigoEmpresaProduto(getVo().getCodigoEmpresaProduto());
			levItemFilter.setCodigoLojaProduto(getVo().getCodigoLojaProduto());
			levItemFilter.setCodigoProduto(getVo().getCodigoProduto());
			
			List<LevantamentoEstoqueItemVO> listFind = this.dao.getList(levItemFilter);
			
			if (listFind.size() > 0) {
				
				LevantamentoEstoqueItemVO item = listFind.get(0);
				
				item.setCodigoEmpresa(getVo().getCodigoEmpresa());
				item.setCodigoLoja(getVo().getCodigoLoja());
				item.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());
				
				item.setCodigoEmpresaProduto(getVo().getCodigoEmpresaProduto());
				item.setCodigoLojaProduto(getVo().getCodigoLojaProduto());
				item.setCodigoProduto(getVo().getCodigoProduto());
				
				item.setQuantidadeDigitadaItemLevantamentoEstoque(item.getQuantidadeDigitadaItemLevantamentoEstoque().add(getVo().getQuantidadeDigitadaItemLevantamentoEstoque()));
				item.setQuantidadeDisplayItemLevantamentoEstoque(item.getQuantidadeDisplayItemLevantamentoEstoque().add(getVo().getQuantidadeDisplayItemLevantamentoEstoque()));
				item.setQuantidadeGavetaItemLevantamentoEstoque(item.getQuantidadeGavetaItemLevantamentoEstoque().add(getVo().getQuantidadeGavetaItemLevantamentoEstoque()));
				
				setVo(item);
				
				alterar();
			} else {
				LevantamentoEstoqueItemVO item = new LevantamentoEstoqueItemVO();
				
				item.setCodigoEmpresa(getVo().getCodigoEmpresa());
				item.setCodigoLoja(getVo().getCodigoLoja());
				item.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());
				
				item.setCodigoEmpresaProduto(getVo().getCodigoEmpresaProduto());
				item.setCodigoLojaProduto(getVo().getCodigoLojaProduto());
				item.setCodigoProduto(getVo().getCodigoProduto());
				
				item.setQuantidadeDigitadaItemLevantamentoEstoque(getVo().getQuantidadeDigitadaItemLevantamentoEstoque());
				item.setQuantidadeDisplayItemLevantamentoEstoque(getVo().getQuantidadeDisplayItemLevantamentoEstoque());
				item.setQuantidadeGavetaItemLevantamentoEstoque(getVo().getQuantidadeGavetaItemLevantamentoEstoque());
				
				setVo(item);
				
				inserir();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getCodigoReferenciaPesquisa() {
		return CodigoReferenciaPesquisa;
	}

	public void setCodigoReferenciaPesquisa(String codigoReferenciaPesquisa) {
		CodigoReferenciaPesquisa = codigoReferenciaPesquisa;
	}
}