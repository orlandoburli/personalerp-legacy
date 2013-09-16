package br.com.orlandoburli.core.vo.rh.folha;

import java.math.BigDecimal;
import java.util.List;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;

public class ContratoTrabalhoFolhaPagamentoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoFolhaPagamento;
	@Key
	private Integer CodigoEmpresaContratoTrabalho;
	@Key
	private Integer CodigoLojaContratoTrabalho;
	@Key
	private Integer CodigoContratoTrabalho;
	
	private BigDecimal SalarioBase;
	private BigDecimal BaseInss;
	private BigDecimal BaseFgts;
	private BigDecimal ValorFgts;
	private BigDecimal BaseIrrf;
	private BigDecimal FaixaIrrf;
	private BigDecimal ValorInssPatronal;
	
	@Ignore
	private List<VerbaFolhaPagamentoVO> Verbas;
	
	@Formula(getFormula="(SELECT b.NomeFuncionario FROM [schema].Funcionario b \n" +
			" JOIN [schema].ContratoTrabalho c \n" +
			"	ON b.CodigoFuncionario = c.CodigoFuncionario \n" +
			"  AND b.CodigoEmpresa     = c.CodigoEmpresaFuncionario \n" +
			"  AND b.CodigoLoja        = c.CodigoLojaFuncionario \n" +
			" WHERE c.CodigoContratoTrabalho = a.CodigoContratoTrabalho \n" +
			"   AND c.CodigoEmpresa          = a.CodigoEmpresaContratoTrabalho \n" +
			"   AND c.CodigoLoja             = a.CodigoLojaContratoTrabalho) \n")
	private String NomeFuncionario;

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

	public Integer getCodigoFolhaPagamento() {
		return CodigoFolhaPagamento;
	}

	public void setCodigoFolhaPagamento(Integer codigoFolhaPagamento) {
		CodigoFolhaPagamento = codigoFolhaPagamento;
	}

	public Integer getCodigoEmpresaContratoTrabalho() {
		return CodigoEmpresaContratoTrabalho;
	}

	public void setCodigoEmpresaContratoTrabalho(
			Integer codigoEmpresaContratoTrabalho) {
		CodigoEmpresaContratoTrabalho = codigoEmpresaContratoTrabalho;
	}

	public Integer getCodigoLojaContratoTrabalho() {
		return CodigoLojaContratoTrabalho;
	}

	public void setCodigoLojaContratoTrabalho(Integer codigoLojaContratoTrabalho) {
		CodigoLojaContratoTrabalho = codigoLojaContratoTrabalho;
	}

	public Integer getCodigoContratoTrabalho() {
		return CodigoContratoTrabalho;
	}

	public void setCodigoContratoTrabalho(Integer codigoContratoTrabalho) {
		CodigoContratoTrabalho = codigoContratoTrabalho;
	}

	public BigDecimal getSalarioBase() {
		return SalarioBase;
	}

	public void setSalarioBase(BigDecimal salarioBase) {
		SalarioBase = salarioBase;
	}

	public BigDecimal getBaseInss() {
		return BaseInss;
	}

	public void setBaseInss(BigDecimal baseInss) {
		BaseInss = baseInss;
	}

	public BigDecimal getBaseFgts() {
		return BaseFgts;
	}

	public void setBaseFgts(BigDecimal baseFgts) {
		BaseFgts = baseFgts;
	}

	public BigDecimal getValorFgts() {
		return ValorFgts;
	}

	public void setValorFgts(BigDecimal valorFgts) {
		ValorFgts = valorFgts;
	}

	public BigDecimal getBaseIrrf() {
		return BaseIrrf;
	}

	public void setBaseIrrf(BigDecimal baseIrrf) {
		BaseIrrf = baseIrrf;
	}

	public BigDecimal getFaixaIrrf() {
		return FaixaIrrf;
	}

	public void setFaixaIrrf(BigDecimal faixaIrrf) {
		FaixaIrrf = faixaIrrf;
	}

	public void setVerbas(List<VerbaFolhaPagamentoVO> verbas) {
		Verbas = verbas;
	}

	public List<VerbaFolhaPagamentoVO> getVerbas() {
		return Verbas;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		NomeFuncionario = nomeFuncionario;
	}

	public String getNomeFuncionario() {
		return NomeFuncionario;
	}

	public void setValorInssPatronal(BigDecimal valorInssPatronal) {
		ValorInssPatronal = valorInssPatronal;
	}

	public BigDecimal getValorInssPatronal() {
		return ValorInssPatronal;
	}
}