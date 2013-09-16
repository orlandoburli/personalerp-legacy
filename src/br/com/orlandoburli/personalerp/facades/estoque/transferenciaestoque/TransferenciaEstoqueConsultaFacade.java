package br.com.orlandoburli.personalerp.facades.estoque.transferenciaestoque;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.transferencia.ItemTransferenciaEstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.transferencia.TransferenciaEstoqueDAO;
import br.com.orlandoburli.core.utils.etiqueta.ItemImpressaoVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.transferencia.ItemTransferenciaEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.transferencia.TransferenciaEstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class TransferenciaEstoqueConsultaFacade extends BaseConsultaFlexFacade<TransferenciaEstoqueVO, TransferenciaEstoqueDAO>{

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;
	
	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataHoraSaidaTransferenciaEstoque DESC");
		
		getFilter().setNomeLojaDestino(getFiltro() + "%");
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		String sb = "";
		
		if (getDataInicial() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DATE_TRUNC('DAY', DataHoraSaidaTransferenciaEstoque) >= '" + getDataInicial() + "' ";
		}
		
		if (getDataFinal() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DATE_TRUNC('DAY', DataHoraSaidaTransferenciaEstoque) <= '" + getDataFinal() + "' ";
		}
		
		dao.setSpecialCondition(sb);
	}
	
	@IgnoreMethodAuthentication
	public void etiquetas() {
		ItemTransferenciaEstoqueVO itemFilter = new ItemTransferenciaEstoqueVO();
		itemFilter.setCodigoEmpresa(getFilter().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getFilter().getCodigoLoja());
		itemFilter.setCodigoTransferenciaEstoque(getFilter().getCodigoTransferenciaEstoque());


		ItemTransferenciaEstoqueDAO dao = new ItemTransferenciaEstoqueDAO();
		ProdutoDAO prodDao = new ProdutoDAO();
		
		try {
			List<ItemTransferenciaEstoqueVO> list = dao.getList(itemFilter);
			
			List<ItemImpressaoVO> listImpressao = new ArrayList<ItemImpressaoVO>();
			for (ItemTransferenciaEstoqueVO item : list) {
				
				ProdutoVO produto = new ProdutoVO();
				
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				
				produto = prodDao.get(produto);
				
				listImpressao.add(new ItemImpressaoVO(produto, item.getQuantidadeItemTransferenciaEstoque().intValue()));
			}
			
			request.getSession().setAttribute("itensimpressao", listImpressao);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
	}
}