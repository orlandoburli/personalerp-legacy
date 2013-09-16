package br.com.orlandoburli.core.vo.financeiro;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class DistribuicaoCustoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	@AutoIncrement
	private Integer CodigoDistribuicaoCusto;
	
	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	
	@Precision(decimals=2)
	private BigDecimal PercentualCusto;
	
	private Integer CodigoPlanoConta;
	
	private Integer CodigoEmpresaReferencia;
	private Integer CodigoLojaReferencia;
	
	private Date DataInicialDistribuicaoCusto;
	private Date DataFinalDistribuicaoCusto;
	
    @Formula(getFormula="(SELECT d.NumeroPlanoConta from [schema].planoconta d WHERE a.CodigoPlanoConta = d.CodigoPlanoConta)")
    private String NumeroPlanoConta;
    
    @Formula(getFormula="(SELECT b.NomeLoja FROM [schema].Loja b WHERE a.CodigoEmpresa = b.CodigoEmpresa AND a.CodigoLoja = b.CodigoLoja)")
    private String NomeLoja;
    
    @Formula(getFormula="(SELECT b.NomeLoja FROM [schema].Loja b WHERE a.CodigoEmpresaReferencia = b.CodigoEmpresa AND a.CodigoLojaReferencia = b.CodigoLoja)")    
    private String NomeLojaReferencia;
	
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

	public Integer getCodigoDistribuicaoCusto() {
		return CodigoDistribuicaoCusto;
	}

	public void setCodigoDistribuicaoCusto(Integer codigoDistribuicaoCusto) {
		CodigoDistribuicaoCusto = codigoDistribuicaoCusto;
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

	public BigDecimal getPercentualCusto() {
		return PercentualCusto;
	}

	public void setPercentualCusto(BigDecimal percentualCusto) {
		PercentualCusto = percentualCusto;
	}

	public Integer getCodigoPlanoConta() {
		return CodigoPlanoConta;
	}

	public void setCodigoPlanoConta(Integer codigoPlanoConta) {
		CodigoPlanoConta = codigoPlanoConta;
	}

	public Integer getCodigoEmpresaReferencia() {
		return CodigoEmpresaReferencia;
	}

	public void setCodigoEmpresaReferencia(Integer codigoEmpresaReferencia) {
		CodigoEmpresaReferencia = codigoEmpresaReferencia;
	}

	public Integer getCodigoLojaReferencia() {
		return CodigoLojaReferencia;
	}

	public void setCodigoLojaReferencia(Integer codigoLojaReferencia) {
		CodigoLojaReferencia = codigoLojaReferencia;
	}

	public Date getDataInicialDistribuicaoCusto() {
		return DataInicialDistribuicaoCusto;
	}

	public void setDataInicialDistribuicaoCusto(Date dataInicialDistribuicaoCusto) {
		DataInicialDistribuicaoCusto = dataInicialDistribuicaoCusto;
	}

	public Date getDataFinalDistribuicaoCusto() {
		return DataFinalDistribuicaoCusto;
	}

	public void setDataFinalDistribuicaoCusto(Date dataFinalDistribuicaoCusto) {
		DataFinalDistribuicaoCusto = dataFinalDistribuicaoCusto;
	}

	public String getNumeroPlanoConta() {
		return NumeroPlanoConta;
	}

	public void setNumeroPlanoConta(String numeroPlanoConta) {
		NumeroPlanoConta = numeroPlanoConta;
	}

	public String getNomeLoja() {
		return NomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}

	public String getNomeLojaReferencia() {
		return NomeLojaReferencia;
	}

	public void setNomeLojaReferencia(String nomeLojaReferencia) {
		NomeLojaReferencia = nomeLojaReferencia;
	}
}
