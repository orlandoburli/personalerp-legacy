package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.calculos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.ItemMetaVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.MetaVendaDAO;
import br.com.orlandoburli.core.dao.view.vendas.VendasGrupoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.view.vendas.VendasGrupoView;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.core.vo.rh.folha.VerbaFolhaPagamentoVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.ItemMetaVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.MetaVendaVO;
import br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.processamento.ICalculoComissao;

public class ComissaoVendedorChilliBeansAction implements ICalculoComissao {

	private BigDecimal valorComissaoDSR;

	@Override
	public BigDecimal calcular(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha, VerbaVO verba, VerbaContratoTrabalhoVO verbaContrato, VerbaFolhaPagamentoVO verbaFolha, BigDecimal totalHorasDSR, BigDecimal totalHorasUteisMes) throws SQLException {
		BigDecimal valorComissao = BigDecimal.ZERO;
		BigDecimal cem = new BigDecimal(100);
		
		// Busca Vendedor

		VendedorVO vendedor = new VendedorDAO().getVendedorByContrato(contrato);

		if (vendedor == null) {
			return valorComissao;
		}

		// Busca vendas
		VendasGrupoView vgFilter = new VendasGrupoView();
		vgFilter.setCodigoEmpresaVendedor(vendedor.getCodigoEmpresa());
		vgFilter.setCodigoLojaVendedor(vendedor.getCodigoLoja());
		vgFilter.setCodigoVendedor(vendedor.getCodigoVendedor());
		
		VendasGrupoDAO vendasDao = new VendasGrupoDAO();
		String sql = " EXTRACT (YEAR FROM a.DataVenda) = " + folha.getAnoCompetenciaFolhaPagamento();
		sql += " AND EXTRACT (MONTH FROM a.DataVenda) = " + folha.getMesCompetenciaFolhaPagamento();

		vendasDao.setSpecialCondition(sql);
		
		List<VendasGrupoView> listVendas = vendasDao.getList(vgFilter);
		
		// Valor total
		BigDecimal valorTotal = BigDecimal.ZERO;
		
		// Valor das vendas de cada grupo
		Map<Integer, BigDecimal> pecasGrupos = new HashMap<Integer, BigDecimal>();
		
		for (VendasGrupoView v : listVendas) {
			valorTotal = valorTotal.add(v.getValorTotal());
			
			if (pecasGrupos.get(v.getCodigoGrupo()) == null) {
				pecasGrupos.put(v.getCodigoGrupo(), v.getTotalPecas());
			} else {
				pecasGrupos.put(v.getCodigoGrupo(),	pecasGrupos.get(v.getCodigoGrupo()).add(v.getTotalPecas()));
			}
		}
		
		// Carrega a meta do mes
		MetaVendaVO metaFilter = new MetaVendaVO();
		
		Calendar calDataInicial = Calendar.getInstance();
		
		calDataInicial.set(Calendar.YEAR, folha.getAnoCompetenciaFolhaPagamento());
		calDataInicial.set(Calendar.MONTH, folha.getMesCompetenciaFolhaPagamento() - 1);
		calDataInicial.set(Calendar.DAY_OF_MONTH, 1);
		
		Calendar calDataFinal = (Calendar) calDataInicial.clone();
		calDataFinal.set(Calendar.DAY_OF_MONTH, calDataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		metaFilter.setDataInicialMetaVenda(Utils.calendarToDate(calDataInicial));
		metaFilter.setDataFinalMetaVenda(Utils.calendarToDate(calDataFinal));
		metaFilter.setCodigoEmpresaVendedor(vendedor.getCodigoEmpresa());
		metaFilter.setCodigoLojaVendedor(vendedor.getCodigoLoja());
		metaFilter.setCodigoVendedor(vendedor.getCodigoVendedor());
		metaFilter.setCodigoEmpresa(contrato.getCodigoEmpresaLotacao());
		metaFilter.setCodigoLoja(contrato.getCodigoLojaLotacao());
		
		metaFilter.setTipoMetaVenda("V"); // Vendedor
		
		MetaVendaVO meta = null;
		
		List<MetaVendaVO> listMetas = new MetaVendaDAO().getList(metaFilter);
		
		if (listMetas.size() > 0) {
			meta = listMetas.get(0);
		}
		
		if (meta == null) {
			return valorComissao;
		}
		
		ItemMetaVendaVO itemMetaFilter = new ItemMetaVendaVO();
		itemMetaFilter.setCodigoEmpresa(meta.getCodigoEmpresa());
		itemMetaFilter.setCodigoLoja(meta.getCodigoLoja());
		itemMetaFilter.setCodigoMetaVenda(meta.getCodigoMetaVenda());
		itemMetaFilter.setTipoItemMetaVenda("V"); // por valor
		
		List<ItemMetaVendaVO> listItensMetaValor = new ItemMetaVendaDAO().getList(itemMetaFilter, "PesoNivelMetaVenda DESC");
		ItemMetaVendaVO metaValor = null;
		for (ItemMetaVendaVO item : listItensMetaValor) {
			metaValor = item;
			if (valorTotal.compareTo(metaValor.getValorMetaVenda()) >= 0) {
				break;
			}
		}
		
		// Valor da comissao
		BigDecimal comissaoValorVendas = valorTotal.multiply(metaValor.getPremioMetaVenda()).divide(cem, 2, BigDecimal.ROUND_CEILING);
		
		// Carrega os itens das metas por grupos
		itemMetaFilter = new ItemMetaVendaVO();
		itemMetaFilter.setCodigoEmpresa(meta.getCodigoEmpresa());
		itemMetaFilter.setCodigoLoja(meta.getCodigoLoja());
		itemMetaFilter.setCodigoMetaVenda(meta.getCodigoMetaVenda());
		itemMetaFilter.setTipoItemMetaVenda("Q");
		List<ItemMetaVendaVO> listItensMeta = new ItemMetaVendaDAO().getList(itemMetaFilter);
		
		BigDecimal premiacaoPecas = BigDecimal.ZERO;
		
		for (ItemMetaVendaVO item : listItensMeta) {
			BigDecimal valorGrupo = pecasGrupos.get(item.getReferenciaMetaVenda());
			if (valorGrupo != null && valorGrupo.compareTo(BigDecimal.ZERO) > 0) {
				if (valorGrupo.compareTo(item.getValorMetaVenda()) >= 0) {
					premiacaoPecas = premiacaoPecas.add(item.getPremioMetaVenda());
				}
			}
		}
		
		valorComissao = comissaoValorVendas.add(premiacaoPecas);
		
		if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S") && verba.getFlagFolhaVerba().equals("S")) {
			// Aqui, uma parte dessa comissao será paga como DSR
			BigDecimal fator = totalHorasDSR.divide(totalHorasDSR.add(totalHorasUteisMes), 2, BigDecimal.ROUND_HALF_EVEN);

			// Novo valor da comissão...
			BigDecimal novoValorComissao = valorComissao.multiply(BigDecimal.ONE.subtract(fator));

			// ... e o valor do DSR equivalente
			valorComissaoDSR = valorComissao.subtract(novoValorComissao);

			return novoValorComissao;
		} else {
			valorComissaoDSR = BigDecimal.ZERO;
			return valorComissao;
		}
	}

	@Override
	public BigDecimal calcularImpactoDSR(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha, BigDecimal valorComissao, BigDecimal totalHorasDSR, BigDecimal totalHorasUteisMes) {
		return this.valorComissaoDSR;
	}

}
