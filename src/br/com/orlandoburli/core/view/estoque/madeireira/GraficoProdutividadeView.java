package br.com.orlandoburli.core.view.estoque.madeireira;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.view.IView;

public class GraficoProdutividadeView implements IView {

	private static final long serialVersionUID = 1L;
	
	private Date DataMovimentacao;
	private BigDecimal VolumeM3Movimentacao;
	
	public void setDataMovimentacao(Date dataMovimentacao) {
		DataMovimentacao = dataMovimentacao;
	}
	public Date getDataMovimentacao() {
		return DataMovimentacao;
	}
	public void setVolumeM3Movimentacao(BigDecimal volumeM3Movimentacao) {
		VolumeM3Movimentacao = volumeM3Movimentacao;
	}
	public BigDecimal getVolumeM3Movimentacao() {
		return VolumeM3Movimentacao;
	}
}