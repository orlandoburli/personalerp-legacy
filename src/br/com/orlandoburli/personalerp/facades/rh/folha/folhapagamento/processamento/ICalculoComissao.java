package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.processamento;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;

public interface ICalculoComissao extends ICalculoVerbaFolha {

	BigDecimal calcularImpactoDSR(ContratoTrabalhoVO contrato,
			FolhaPagamentoVO folha, BigDecimal valorComissao,
			BigDecimal totalHorasDSR, BigDecimal totalHorasUteisMes);
}