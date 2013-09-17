package br.com.orlandoburli.personalerp.web.action.estoque.produto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.core.web.framework.action.BaseCadastroAction;
import br.com.orlandoburli.personalerp.model.estoque.fabricante.be.FabricanteBe;
import br.com.orlandoburli.personalerp.model.estoque.fabricante.vo.FabricanteVO;
import br.com.orlandoburli.personalerp.model.estoque.grupo.be.GrupoBe;
import br.com.orlandoburli.personalerp.model.estoque.grupo.vo.GrupoVO;
import br.com.orlandoburli.personalerp.model.estoque.produto.be.ProdutoBe;
import br.com.orlandoburli.personalerp.model.estoque.produto.dao.ProdutoDAO;
import br.com.orlandoburli.personalerp.model.estoque.produto.vo.ProdutoVO;
import br.com.orlandoburli.personalerp.model.estoque.subgrupo.be.SubGrupoBe;
import br.com.orlandoburli.personalerp.model.estoque.subgrupo.vo.SubGrupoVO;
import br.com.orlandoburli.personalerp.model.estoque.unidade.be.UnidadeBe;
import br.com.orlandoburli.personalerp.model.estoque.unidade.vo.UnidadeVO;
import br.com.orlandoburli.personalerp.model.fiscal.tipotributacao.be.TipoTributacaoBe;
import br.com.orlandoburli.personalerp.model.fiscal.tipotributacao.vo.TipoTributacaoVO;

public class ProdutoCadastroAction extends BaseCadastroAction<ProdutoVO, ProdutoDAO, ProdutoBe>{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doBeforeVisualizar(HttpServletRequest request, HttpServletResponse response, ProdutoVO vo, ProdutoBe be) {
		
		try {
			
			List<GrupoVO> grupos = new GrupoBe().getList(null, "NomeGrupo");
			List<SubGrupoVO> subgrupos = new SubGrupoBe().getList(null, "NomeSubGrupo");
			List<FabricanteVO> fabricantes = new FabricanteBe().getList(null, "NomeFabricante");
			List<TipoTributacaoVO> tributacoes = new TipoTributacaoBe().getList(null, "IdentificacaoTipoTributacao");
			List<UnidadeVO> unidades = new UnidadeBe().getList(null, "SiglaUnidade");
			
			request.setAttribute("grupos", grupos);
			request.setAttribute("subgrupos", subgrupos);
			request.setAttribute("fabricantes", fabricantes);
			request.setAttribute("tributacoes", tributacoes);
			request.setAttribute("unidades", unidades);
			
		} catch (ListException e) {
			e.printStackTrace();
		}
		
		super.doBeforeVisualizar(request, response, vo, be);
	}
	
	@Override
	public String getJspCadastro() {
		return "web/pages/estoque/produto/produtocadastro.jsp";
	}
}