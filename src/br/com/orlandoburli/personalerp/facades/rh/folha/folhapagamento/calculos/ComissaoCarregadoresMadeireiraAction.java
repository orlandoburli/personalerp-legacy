package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.calculos;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.view.estoque.madeireira.madeiraserrada.RelatorioProdutividadeMadeiraSerradaDAO;
import br.com.orlandoburli.core.view.estoque.madeireira.madeiraserrada.RelatorioProdutividadeMadeiraSerradaView;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomadeiraserrada.RomaneioMadeiraSerradaVO;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.core.vo.rh.folha.VerbaFolhaPagamentoVO;
import br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.processamento.ICalculoComissao;

/**
 * Rotina de calculo para comissão de carregadores
 * @author Orlando Burli
 */
public class ComissaoCarregadoresMadeireiraAction implements ICalculoComissao {

	private BigDecimal valorComissaoDSR;

	@Override
	public BigDecimal calcular(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha,
			VerbaVO verba, VerbaContratoTrabalhoVO verbaContrato,
			VerbaFolhaPagamentoVO verbaFolha, BigDecimal totalHorasDSR,
			BigDecimal totalHorasUteisMes) throws SQLException {
		
		RelatorioProdutividadeMadeiraSerradaView _filter = new RelatorioProdutividadeMadeiraSerradaView();
		_filter.setAnoDataRomaneio(folha.getAnoCompetenciaFolhaPagamento());
		_filter.setMesDataRomaneio(folha.getMesCompetenciaFolhaPagamento());
		
		_filter.setTipoRomaneio(RomaneioMadeiraSerradaVO.TIPOROMANEIO_SAIDA);
		_filter.setStatusRomaneio(RomaneioMadeiraSerradaVO.STATUS_ROMANEIO_PROCESSADO);
		
		RelatorioProdutividadeMadeiraSerradaDAO _dao = new RelatorioProdutividadeMadeiraSerradaDAO();
		
		List<RelatorioProdutividadeMadeiraSerradaView> list = _dao.getList(_filter);
		
		BigDecimal totalM3 = BigDecimal.ZERO;
		
		for (RelatorioProdutividadeMadeiraSerradaView item : list) {
			totalM3 = totalM3.add(item.getVolumeTotalM3());
		}
		
		// Arredondado...
		totalM3 = totalM3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		// Busca parametro do valor da comissao por m3 carregado
		BigDecimal valorm3 = verbaContrato.getValorVerba();
		
		// Seta a referencia
		verbaFolha.setReferenciaVerba(totalM3);
		
		// Calcula o valor total da verba
		BigDecimal valorComissao = totalM3.multiply(valorm3).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		if (!contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")) {
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
		// Retorna o valor previamente calculado
		return valorComissaoDSR;
	}
}