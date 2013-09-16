package br.com.orlandoburli.core.vo.financeiro.contasapagar;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class BaixaContaPagarVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoPagamento;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoEmpresaContaPagar;
	@Key
	private Integer CodigoLojaContaPagar;
	@Key
	private Integer CodigoContaPagar;
	
	@Precision(decimals=2)
	private BigDecimal ValorBaixaContaPagar;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Ignore
    private PagamentoVO Pagamento;
    
    @Formula(getFormula="(SELECT b.NomeUsuario FROM [schema].Usuario b" +
    		"  WHERE a.CodigoEmpresaUsuarioLog = b.CodigoEmpresa" +
    		"    AND a.CodigoLojaUsuarioLog = b.CodigoLoja" +
    		"    AND a.CodigoUsuarioLog = b.CodigoUsuario)")
    private String NomeUsuarioPagamento;
    
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

	public Integer getCodigoEmpresaContaPagar() {
		return CodigoEmpresaContaPagar;
	}

	public void setCodigoEmpresaContaPagar(Integer codigoEmpresaContaPagar) {
		CodigoEmpresaContaPagar = codigoEmpresaContaPagar;
	}

	public Integer getCodigoLojaContaPagar() {
		return CodigoLojaContaPagar;
	}

	public void setCodigoLojaContaPagar(Integer codigoLojaContaPagar) {
		CodigoLojaContaPagar = codigoLojaContaPagar;
	}

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
	}

	public void setValorBaixaContaPagar(BigDecimal valorBaixaContaPagar) {
		ValorBaixaContaPagar = valorBaixaContaPagar;
	}

	public BigDecimal getValorBaixaContaPagar() {
		return ValorBaixaContaPagar;
	}

	public void setPagamento(PagamentoVO pagamento) {
		Pagamento = pagamento;
	}

	public PagamentoVO getPagamento() {
		return Pagamento;
	}

	public void setNomeUsuarioPagamento(String nomeUsuarioPagamento) {
		NomeUsuarioPagamento = nomeUsuarioPagamento;
	}

	public String getNomeUsuarioPagamento() {
		return NomeUsuarioPagamento;
	}
}