package br.com.orlandoburli.personalerp.facades.vendas.pedidovenda;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.dao.vendas.pedido.PedidoDAO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PedidoVendasConsultaFacade extends BaseConsultaFlexFacade<PedidoVO, PedidoDAO>{

	private static final long serialVersionUID = 1L;
	
	private Date DataInicial;
	private Date DataFinal;
	
	private BigDecimal ValorInicial;
	private BigDecimal ValorFinal;
	
	@Override
	protected void doBeforeFilter() {
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		String sb = "";
		if (DataInicial != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataPedido >= '" + DataInicial.toString() + " 00:00:00' ";
		}
		
		if (DataFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataPedido <= '" + DataFinal.toString() + " 23:59:59' ";
		}
		
		if (sb.trim().length() > 0) {
			dao.setSpecialCondition(sb);
		}
		setOrderFields("DataPedido DESC, CodigoPedido DESC");
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

	public void setValorInicial(BigDecimal valorInicial) {
		ValorInicial = valorInicial;
	}

	public BigDecimal getValorInicial() {
		return ValorInicial;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		ValorFinal = valorFinal;
	}

	public BigDecimal getValorFinal() {
		return ValorFinal;
	}
}