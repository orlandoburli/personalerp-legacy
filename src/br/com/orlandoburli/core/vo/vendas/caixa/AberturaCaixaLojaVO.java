package br.com.orlandoburli.core.vo.vendas.caixa;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class AberturaCaixaLojaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoCaixa;
	@Key @AutoIncrement
	private Integer CodigoAberturaCaixa;
	
	private String StatusAberturaCaixaLoja;
	private Timestamp DataHoraAberturaCaixa;
	
	private Integer CodigoEmpresaVendedorAbertura;
	private Integer CodigoLojaVendedorAbertura;
	private Integer CodigoVendedorAbertura;
	
	private Timestamp DataHoraFechamentoCaixa;
	
	private Integer CodigoEmpresaVendedorFechamento;
	private Integer CodigoLojaVendedorFechamento;
	private Integer CodigoVendedorFechamento;
	
	@Precision(decimals=2)
	private BigDecimal ValorAberturaCaixaLoja;
	@Precision(decimals=2)
	private BigDecimal ValorFechamentoCaixaLoja;
	
	@Formula(getFormula="(SELECT b.NomeVendedor FROM [schema].Vendedor b WHERE b.CodigoEmpresa = a.CodigoEmpresaVendedorAbertura AND b.CodigoLoja = a.CodigoLojaVendedorAbertura AND b.CodigoVendedor = a.CodigoVendedorAbertura)")
	private String NomeVendedorAbertura;
	
	@Formula(getFormula="(SELECT b.NomeVendedor FROM [schema].Vendedor b WHERE b.CodigoEmpresa = a.CodigoEmpresaVendedorFechamento AND b.CodigoLoja = a.CodigoLojaVendedorFechamento AND b.CodigoVendedor = a.CodigoVendedorFechamento)")
	private String NomeVendedorFechamento;
	
	
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

	public String getStatusAberturaCaixaLoja() {
		return StatusAberturaCaixaLoja;
	}

	public void setStatusAberturaCaixaLoja(String statusAberturaCaixaLoja) {
		StatusAberturaCaixaLoja = statusAberturaCaixaLoja;
	}

	public Timestamp getDataHoraAberturaCaixa() {
		return DataHoraAberturaCaixa;
	}

	public void setDataHoraAberturaCaixa(Timestamp dataHoraAberturaCaixa) {
		DataHoraAberturaCaixa = dataHoraAberturaCaixa;
	}

	public Integer getCodigoEmpresaVendedorAbertura() {
		return CodigoEmpresaVendedorAbertura;
	}

	public void setCodigoEmpresaVendedorAbertura(
			Integer codigoEmpresaVendedorAbertura) {
		CodigoEmpresaVendedorAbertura = codigoEmpresaVendedorAbertura;
	}

	public Integer getCodigoLojaVendedorAbertura() {
		return CodigoLojaVendedorAbertura;
	}

	public void setCodigoLojaVendedorAbertura(Integer codigoLojaVendedorAbertura) {
		CodigoLojaVendedorAbertura = codigoLojaVendedorAbertura;
	}

	public Integer getCodigoVendedorAbertura() {
		return CodigoVendedorAbertura;
	}

	public void setCodigoVendedorAbertura(Integer codigoVendedorAbertura) {
		CodigoVendedorAbertura = codigoVendedorAbertura;
	}

	public Timestamp getDataHoraFechamentoCaixa() {
		return DataHoraFechamentoCaixa;
	}

	public void setDataHoraFechamentoCaixa(Timestamp dataHoraFechamentoCaixa) {
		DataHoraFechamentoCaixa = dataHoraFechamentoCaixa;
	}

	public Integer getCodigoEmpresaVendedorFechamento() {
		return CodigoEmpresaVendedorFechamento;
	}

	public void setCodigoEmpresaVendedorFechamento(
			Integer codigoEmpresaVendedorFechamento) {
		CodigoEmpresaVendedorFechamento = codigoEmpresaVendedorFechamento;
	}

	public Integer getCodigoLojaVendedorFechamento() {
		return CodigoLojaVendedorFechamento;
	}

	public void setCodigoLojaVendedorFechamento(Integer codigoLojaVendedorFechamento) {
		CodigoLojaVendedorFechamento = codigoLojaVendedorFechamento;
	}

	public Integer getCodigoVendedorFechamento() {
		return CodigoVendedorFechamento;
	}

	public void setCodigoVendedorFechamento(Integer codigoVendedorFechamento) {
		CodigoVendedorFechamento = codigoVendedorFechamento;
	}

	public BigDecimal getValorAberturaCaixaLoja() {
		return ValorAberturaCaixaLoja;
	}

	public void setValorAberturaCaixaLoja(BigDecimal valorAberturaCaixaLoja) {
		ValorAberturaCaixaLoja = valorAberturaCaixaLoja;
	}

	public BigDecimal getValorFechamentoCaixaLoja() {
		return ValorFechamentoCaixaLoja;
	}

	public void setValorFechamentoCaixaLoja(BigDecimal valorFechamentoCaixaLoja) {
		ValorFechamentoCaixaLoja = valorFechamentoCaixaLoja;
	}

	public void setNomeVendedorAbertura(String nomeVendedorAbertura) {
		NomeVendedorAbertura = nomeVendedorAbertura;
	}

	public String getNomeVendedorAbertura() {
		return NomeVendedorAbertura;
	}

	public void setNomeVendedorFechamento(String nomeVendedorFechamento) {
		NomeVendedorFechamento = nomeVendedorFechamento;
	}

	public String getNomeVendedorFechamento() {
		return NomeVendedorFechamento;
	}
}
