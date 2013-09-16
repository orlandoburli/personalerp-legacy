package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.processamento;

import java.math.BigDecimal;
import java.sql.SQLException;

import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.core.vo.rh.folha.VerbaFolhaPagamentoVO;

import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;

public interface ICalculoVerbaFolha {

	BigDecimal calcular(ContratoTrabalhoVO contrato, FolhaPagamentoVO folha,
			VerbaVO verba, VerbaContratoTrabalhoVO verbaContrato,
			VerbaFolhaPagamentoVO verbaFolha, BigDecimal totalHorasDSR,
			BigDecimal totalHorasUteisMes) throws SQLException;
}