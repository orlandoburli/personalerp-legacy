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
 * Rotina de Calculo para calculo de comissão de gerente de madeireira 
 * @author Orlando Burli
 *
 */
public class ComissaoGerenteMadeireiraAction implements ICalculoComissao {

	@Override
	public BigDecimal calcular(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha,
			VerbaVO verba, VerbaContratoTrabalhoVO verbaContrato,
			VerbaFolhaPagamentoVO verbaFolha, BigDecimal totalHorasDSR,
			BigDecimal totalHorasUteisMes) throws SQLException {
		
		RelatorioProdutividadeMadeiraSerradaView _filter = new RelatorioProdutividadeMadeiraSerradaView();
		_filter.setAnoDataRomaneio(folha.getAnoCompetenciaFolhaPagamento());
		_filter.setMesDataRomaneio(folha.getMesCompetenciaFolhaPagamento());
		
		_filter.setCodigoEmpresaContratoTrabalho(contrato.getCodigoEmpresa());
		_filter.setCodigoLojaContratoTrabalho(contrato.getCodigoLoja());
		_filter.setCodigoContratoTrabalho(contrato.getCodigoContratoTrabalho());
		
		_filter.setTipoRomaneio(RomaneioMadeiraSerradaVO.TIPOROMANEIO_ENTRADA);
		_filter.setStatusRomaneio(RomaneioMadeiraSerradaVO.STATUS_ROMANEIO_PROCESSADO);
		
		RelatorioProdutividadeMadeiraSerradaDAO _dao = new RelatorioProdutividadeMadeiraSerradaDAO();
		
		List<RelatorioProdutividadeMadeiraSerradaView> list = _dao.getList(_filter);
		
		BigDecimal totalM3 = BigDecimal.ZERO;
		
		for (RelatorioProdutividadeMadeiraSerradaView item : list) {
			totalM3 = totalM3.add(item.getVolumeTotalM3());
		}
		
		// Arredondado...
		totalM3 = totalM3.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		// Busca parametro do valor da comissao por m3 serrado
		BigDecimal valorm3  = verbaContrato.getValorVerba(); //= new ParametroLojaDAO().getBigDecimalParametro(Constants.Parameters.Madeireira.VALOR_COMISSAO_GERENTE_MADEIRASERRADA, folha.getCodigoEmpresa(), folha.getCodigoLoja());
		
		// Seta a referencia
		verbaFolha.setReferenciaVerba(totalM3);
		
		// Calcula o valor total da verba e retorna.
		BigDecimal valorComissao = totalM3.multiply(valorm3).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		return valorComissao;
	}

	@Override
	public BigDecimal calcularImpactoDSR(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha, BigDecimal valorComissao, BigDecimal totalHorasDSR, BigDecimal totalHorasUteisMes) {
		if (contrato.getFlagCarteiraAssinadaContratoTrabalho().equalsIgnoreCase("S")) {
			return BigDecimal.ZERO;
		}
		BigDecimal valorHoraComissao = valorComissao.divide(totalHorasUteisMes, 10, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal valorTotalMes = totalHorasDSR.multiply(valorHoraComissao).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return valorTotalMes;
	}
}