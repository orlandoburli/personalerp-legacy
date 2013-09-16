package br.com.orlandoburli.core.vo.rh.folha;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class VerbaFolhaPagamentoVO implements IValueObject {

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
	@Key
	private Integer CodigoEmpresaVerba;
	@Key
	private Integer CodigoLojaVerba;
	@Key
	private Integer CodigoVerba;
	
	private BigDecimal ValorVerba;
	private BigDecimal ReferenciaVerba;
	
	@Formula(getFormula="(SELECT b.DescricaoCurtaVerba FROM [schema].Verba b" +
			" WHERE b.CodigoVerba = a.CodigoVerba" +
			"   AND b.CodigoEmpresa = a.CodigoEmpresaVerba" +
			"   AND b.CodigoLoja = a.CodigoLojaVerba)")
	private String DescricaoCurtaVerba;

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

	public Integer getCodigoEmpresaVerba() {
		return CodigoEmpresaVerba;
	}

	public void setCodigoEmpresaVerba(Integer codigoEmpresaVerba) {
		CodigoEmpresaVerba = codigoEmpresaVerba;
	}

	public Integer getCodigoLojaVerba() {
		return CodigoLojaVerba;
	}

	public void setCodigoLojaVerba(Integer codigoLojaVerba) {
		CodigoLojaVerba = codigoLojaVerba;
	}

	public Integer getCodigoVerba() {
		return CodigoVerba;
	}

	public void setCodigoVerba(Integer codigoVerba) {
		CodigoVerba = codigoVerba;
	}

	public void setValorVerba(BigDecimal valorVerba) {
		ValorVerba = valorVerba;
	}

	public BigDecimal getValorVerba() {
		return ValorVerba;
	}

	public void setReferenciaVerba(BigDecimal referenciaVerba) {
		ReferenciaVerba = referenciaVerba;
	}

	public BigDecimal getReferenciaVerba() {
		return ReferenciaVerba;
	}

	public void setDescricaoCurtaVerba(String descricaoCurtaVerba) {
		DescricaoCurtaVerba = descricaoCurtaVerba;
	}

	public String getDescricaoCurtaVerba() {
		return DescricaoCurtaVerba;
	}
}