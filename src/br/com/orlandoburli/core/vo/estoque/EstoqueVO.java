package br.com.orlandoburli.core.vo.estoque;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class EstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoProduto;
	
	@Key
	private Integer CodigoEmpresaEstoque;
	@Key
	private Integer CodigoLojaEstoque;
	
	private BigDecimal EstoqueFisico;
	private BigDecimal EstoqueFiscal;
	private BigDecimal PrecoMedioEstoque;
	private Date DataPrecoMedioEstoque;
	private Date DataUltimaEntradaEstoque;
	private String LocacaoEstoque;
	private BigDecimal DemandaEstoque;
	private BigDecimal FrequenciaEstoque;
	
	@Formula(getFormula="(SELECT b.RazaoSocialEmpresa FROM [schema].Empresa b WHERE a.CodigoEmpresaEstoque = b.CodigoEmpresa)")
	private String NomeEmpresa;
	@Formula(getFormula="(SELECT c.NomeLoja FROM [schema].Loja c WHERE a.CodigoEmpresaEstoque = c.CodigoEmpresa AND a.CodigoLojaEstoque = c.CodigoLoja)")
	private String NomeLoja;
	
	private BigDecimal QuantidadeDisplayEstoque;
	private BigDecimal QuantidadeGavetaEstoque;

	@Override
	public boolean IsNew() {
		return this.isNew;
	}

	@Override
	public void setNewRecord(boolean isNew) {
		this.isNew = isNew;
	}

	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
	
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

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public Integer getCodigoEmpresaEstoque() {
		return CodigoEmpresaEstoque;
	}

	public void setCodigoEmpresaEstoque(Integer codigoEmpresaEstoque) {
		CodigoEmpresaEstoque = codigoEmpresaEstoque;
	}

	public Integer getCodigoLojaEstoque() {
		return CodigoLojaEstoque;
	}

	public void setCodigoLojaEstoque(Integer codigoLojaEstoque) {
		CodigoLojaEstoque = codigoLojaEstoque;
	}

	public BigDecimal getEstoqueFisico() {
		return EstoqueFisico;
	}

	public void setEstoqueFisico(BigDecimal estoqueFisico) {
		EstoqueFisico = estoqueFisico;
	}

	public BigDecimal getEstoqueFiscal() {
		return EstoqueFiscal;
	}

	public void setEstoqueFiscal(BigDecimal estoqueFiscal) {
		EstoqueFiscal = estoqueFiscal;
	}

	public BigDecimal getPrecoMedioEstoque() {
		return PrecoMedioEstoque;
	}

	public void setPrecoMedioEstoque(BigDecimal precoMedioEstoque) {
		PrecoMedioEstoque = precoMedioEstoque;
	}

	public Date getDataPrecoMedioEstoque() {
		return DataPrecoMedioEstoque;
	}

	public void setDataPrecoMedioEstoque(Date dataPrecoMedioEstoque) {
		DataPrecoMedioEstoque = dataPrecoMedioEstoque;
	}

	public Date getDataUltimaEntradaEstoque() {
		return DataUltimaEntradaEstoque;
	}

	public void setDataUltimaEntradaEstoque(Date dataUltimaEntradaEstoque) {
		DataUltimaEntradaEstoque = dataUltimaEntradaEstoque;
	}

	public String getLocacaoEstoque() {
		return LocacaoEstoque;
	}

	public void setLocacaoEstoque(String locacaoEstoque) {
		LocacaoEstoque = locacaoEstoque;
	}

	public BigDecimal getDemandaEstoque() {
		return DemandaEstoque;
	}

	public void setDemandaEstoque(BigDecimal demandaEstoque) {
		DemandaEstoque = demandaEstoque;
	}

	public BigDecimal getFrequenciaEstoque() {
		return FrequenciaEstoque;
	}

	public void setFrequenciaEstoque(BigDecimal frequenciaEstoque) {
		FrequenciaEstoque = frequenciaEstoque;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		NomeEmpresa = nomeEmpresa;
	}

	public String getNomeEmpresa() {
		return NomeEmpresa;
	}

	public void setNomeLoja(String nomeLoja) {
		NomeLoja = nomeLoja;
	}

	public String getNomeLoja() {
		return NomeLoja;
	}

	public void setQuantidadeDisplayEstoque(BigDecimal quantidadeDisplayEstoque) {
		QuantidadeDisplayEstoque = quantidadeDisplayEstoque;
	}

	public BigDecimal getQuantidadeDisplayEstoque() {
		return QuantidadeDisplayEstoque;
	}

	public void setQuantidadeGavetaEstoque(BigDecimal quantidadeGavetaEstoque) {
		QuantidadeGavetaEstoque = quantidadeGavetaEstoque;
	}

	public BigDecimal getQuantidadeGavetaEstoque() {
		return QuantidadeGavetaEstoque;
	}
}
