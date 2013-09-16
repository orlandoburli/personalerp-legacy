package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0200;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;

public class Registro0200Factory {

	private static Registro0200Factory factory;

	private Registro0200Factory() {

	}

	public static Registro0200Factory getFactory() {
		if (factory == null) {
			factory = new Registro0200Factory();
		}
		return factory;
	}

	public void buildRegistro0200(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException, SpedException {
		for (IValueObject i : arquivo.getListVo()) {

			if (i instanceof ProdutoVO) {
				ProdutoVO produto = (ProdutoVO) i;
				
				// Validacao dos dados
				if (produto.getDescricaoProduto() == null || produto.getDescricaoProduto().trim().equals("")) {
					throw new SpedException("O produto " + produto.getCodigoProduto() + " não tem descrição!");
				}

				if (produto.getSiglaUnidade() == null || produto.getSiglaUnidade().trim().equals("")) {
					throw new SpedException("O produto " + produto.getDescricaoProduto() + " não tem unidade configurada!");
				}

				if (produto.getCodigoNCMProduto() != null && !produto.getCodigoNCMProduto().trim().equals("")) {
					if (produto.getCodigoNCMProduto().length() != 8) {
						throw new SpedException("O produto " + produto.getDescricaoProduto() + " está com o NCM incorreto!");
					}
				}

				Registro0200 reg0200 = new Registro0200();

				String codigoItem = buildCodigoItem(produto);

				reg0200.setCodigoItem(codigoItem);
				reg0200.setDescricaoItem(produto.getDescricaoProduto().trim());
				reg0200.setCodigoBarra(produto.getCodigoReferenciaProduto().trim());
				reg0200.setCodigoAnteriorItem("");
				reg0200.setUnidade(produto.getSiglaUnidade());
				reg0200.setTipoItem("00"); // TODO - Parametrizar no cadastro de produtos
				reg0200.setCodigoNcm(produto.getCodigoNCMProduto());
				reg0200.setExTipi(""); // Nao tenho no cadastro
				reg0200.setCodigoGenero(""); // Nao tenho no cadastro
				reg0200.setCodigoLst(""); // Nao tenho no cadastro
				reg0200.setAliquota(null); // Nao tenho no cadastro

				arquivo.addRegistro(reg0200);
			}
		}
	}

	public String buildCodigoItem(ProdutoVO produto) {
		return Utils.fillString(produto.getCodigoEmpresa(), "0", 3, 1) + Utils.fillString(produto.getCodigoLoja(), "0", 3, 1) + Utils.fillString(produto.getCodigoProduto(), "0", 8, 1);
	}
}
