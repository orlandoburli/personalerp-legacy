package br.com.orlandoburli.core.vo.vendas.caixa;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class MovimentacaoCaixaLojaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoCaixa;
	@Key
	private Integer CodigoAberturaCaixa;
	@Key @AutoIncrement
	private Integer SequencialMovimentacaoCaixaLoja;
	
	private String OperacaoMovimentacaoCaixaLoja;
	private BigDecimal ValorMovimentacaoCaixaLoja;
	private Date DataMovimentacaoCaixaLoja;
	private String HistoricoMovimentacaoCaixaLoja;
	
	private Integer CodigoEmpresaPedido;
	private Integer CodigoLojaPedido;
	private Integer CodigoPedido;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;

    @Override
	public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	@Override
	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	@Override
	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}
	
	@Override
	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	@Override
	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

	@Override
	public void setCodigoUsuarioLog(Integer codigoUsuarioLog) {
		CodigoUsuarioLog = codigoUsuarioLog;
	}

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoCaixa() {
		return CodigoCaixa;
	}

	public void setCodigoCaixa(Integer codigoCaixa) {
		CodigoCaixa = codigoCaixa;
	}

	public Integer getCodigoAberturaCaixa() {
		return CodigoAberturaCaixa;
	}

	public void setCodigoAberturaCaixa(Integer codigoAberturaCaixa) {
		CodigoAberturaCaixa = codigoAberturaCaixa;
	}

	public Integer getSequencialMovimentacaoCaixaLoja() {
		return SequencialMovimentacaoCaixaLoja;
	}

	public void setSequencialMovimentacaoCaixaLoja(
			Integer sequencialMovimentacaoCaixaLoja) {
		SequencialMovimentacaoCaixaLoja = sequencialMovimentacaoCaixaLoja;
	}

	public String getOperacaoMovimentacaoCaixaLoja() {
		return OperacaoMovimentacaoCaixaLoja;
	}

	public void setOperacaoMovimentacaoCaixaLoja(
			String operacaoMovimentacaoCaixaLoja) {
		OperacaoMovimentacaoCaixaLoja = operacaoMovimentacaoCaixaLoja;
	}

	public BigDecimal getValorMovimentacaoCaixaLoja() {
		return ValorMovimentacaoCaixaLoja;
	}

	public void setValorMovimentacaoCaixaLoja(BigDecimal valorMovimentacaoCaixaLoja) {
		ValorMovimentacaoCaixaLoja = valorMovimentacaoCaixaLoja;
	}

	public Date getDataMovimentacaoCaixaLoja() {
		return DataMovimentacaoCaixaLoja;
	}

	public void setDataMovimentacaoCaixaLoja(Date dataMovimentacaoCaixaLoja) {
		DataMovimentacaoCaixaLoja = dataMovimentacaoCaixaLoja;
	}

	public String getHistoricoMovimentacaoCaixaLoja() {
		return HistoricoMovimentacaoCaixaLoja;
	}

	public void setHistoricoMovimentacaoCaixaLoja(
			String historicoMovimentacaoCaixaLoja) {
		HistoricoMovimentacaoCaixaLoja = historicoMovimentacaoCaixaLoja;
	}

	public Integer getCodigoEmpresaPedido() {
		return CodigoEmpresaPedido;
	}

	public void setCodigoEmpresaPedido(Integer codigoEmpresaPedido) {
		CodigoEmpresaPedido = codigoEmpresaPedido;
	}

	public Integer getCodigoLojaPedido() {
		return CodigoLojaPedido;
	}

	public void setCodigoLojaPedido(Integer codigoLojaPedido) {
		CodigoLojaPedido = codigoLojaPedido;
	}

	public Integer getCodigoPedido() {
		return CodigoPedido;
	}

	public void setCodigoPedido(Integer codigoPedido) {
		CodigoPedido = codigoPedido;
	}
}