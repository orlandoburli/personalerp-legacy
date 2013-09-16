package br.com.orlandoburli.core.vo.financeiro.contasapagar;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;

public class PagamentoVO implements  IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoPagamento;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	private Timestamp DataHoraBaixaPagamento;
	private BigDecimal ValorPagamento;
	private Integer CodigoFormaPagamento;
	private String ObservacaoPagamento;
	
	@Formula(getFormula="(SELECT b.NomeFormaPagamento FROM [schema].FormaPagamento b WHERE a.CodigoFormaPagamento = b.CodigoFormaPagamento)")
	private String NomeFormaPagamento;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    /* Auxiliares da tela de pagamento **/
    @Ignore
    private MovimentacaoContaBancariaVO movimentacao;
    @Ignore
    private ChequeEmitidoVO cheque;
    
    public Integer getCodigoEmpresaUsuarioLog() {
		return CodigoEmpresaUsuarioLog;
	}

	public void setCodigoEmpresaUsuarioLog(Integer codigoEmpresaUsuarioLog) {
		CodigoEmpresaUsuarioLog = codigoEmpresaUsuarioLog;
	}

	public Integer getCodigoLojaUsuarioLog() {
		return CodigoLojaUsuarioLog;
	}

	public void setCodigoLojaUsuarioLog(Integer codigoLojaUsuarioLog) {
		CodigoLojaUsuarioLog = codigoLojaUsuarioLog;
	}

	public Integer getCodigoUsuarioLog() {
		return CodigoUsuarioLog;
	}

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

	public Integer getCodigoPagamento() {
		return CodigoPagamento;
	}

	public void setCodigoPagamento(Integer codigoPagamento) {
		CodigoPagamento = codigoPagamento;
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

	public Timestamp getDataHoraBaixaPagamento() {
		return DataHoraBaixaPagamento;
	}

	public void setDataHoraBaixaPagamento(Timestamp dataHoraBaixaPagamento) {
		DataHoraBaixaPagamento = dataHoraBaixaPagamento;
	}

	public Integer getCodigoFormaPagamento() {
		return CodigoFormaPagamento;
	}

	public void setCodigoFormaPagamento(Integer codigoFormaPagamento) {
		CodigoFormaPagamento = codigoFormaPagamento;
	}

	public String getObservacaoPagamento() {
		return ObservacaoPagamento;
	}

	public void setObservacaoPagamento(String observacaoPagamento) {
		ObservacaoPagamento = observacaoPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		ValorPagamento = valorPagamento;
	}

	public BigDecimal getValorPagamento() {
		return ValorPagamento;
	}

	public void setNomeFormaPagamento(String nomeFormaPagamento) {
		NomeFormaPagamento = nomeFormaPagamento;
	}

	public String getNomeFormaPagamento() {
		return NomeFormaPagamento;
	}

	public void setMovimentacao(MovimentacaoContaBancariaVO movimentacao) {
		this.movimentacao = movimentacao;
	}

	public MovimentacaoContaBancariaVO getMovimentacao() {
		return movimentacao;
	}

	public void setCheque(ChequeEmitidoVO cheque) {
		this.cheque = cheque;
	}

	public ChequeEmitidoVO getCheque() {
		return cheque;
	}
}