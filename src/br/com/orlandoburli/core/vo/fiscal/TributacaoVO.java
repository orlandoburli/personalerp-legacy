package br.com.orlandoburli.core.vo.fiscal;

import java.math.BigDecimal;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class TributacaoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoTipoTributacao;
	@Key
	private String UFOrigemTributacao;
	@Key
	private String OperacaoTributacao;
	@Key
	private String UFDestinoTributacao;
	
	private String ComplementoTributacao;
	private String ClassificacaoTributariaIcmsTributacao;
	private String CfopTributacao;
	private String FormaCalculoIcmsTributacao;
	private BigDecimal AliquotaIcmsTributacao;
	private BigDecimal AliquotaIcmsStTributacao;
	private BigDecimal ReducaoBaseCalculoIcmsTributacao;
	private BigDecimal MargemValorAgregadoTributacao;
	
	private String IpiBaseIcmsTributacao;
	private String OutrasDespesasBaseIcmsTributacao;
	
	private String CstPisTributacao;
	private BigDecimal AliquotaPisTributacao;
	private String CstCofinsTributacao;
	private BigDecimal AliquotaCofinsTributacao;
	private String CstIpiTributacao;
	private BigDecimal AliquotaIpiTributacao;
	
	@Formula(getFormula="COALESCE((SELECT b.NomeEstado FROM personalerp.Estado b WHERE b.SiglaEstado = a.UFDestinoTributacao), 'TODOS')")
	private String NomeUFDestinoTributacao;
	
	@Formula(getFormula="(SELECT c.DescricaoOperacaoTributacao FROM [schema].OperacaoTributacao c WHERE c.OperacaoTributacao = a.OperacaoTributacao)")
	private String DescricaoOperacaoTributacao;
	
	@Formula(getFormula="(SELECT b.IdentificacaoTipoTributacao FROM [schema].TipoTributacao b WHERE a.CodigoTipoTributacao = b.CodigoTipoTributacao)")
	private String IdentificacaoTipoTributacao;
	
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

	public Integer getCodigoTipoTributacao() {
		return CodigoTipoTributacao;
	}

	public void setCodigoTipoTributacao(Integer codigoTipoTributacao) {
		CodigoTipoTributacao = codigoTipoTributacao;
	}

	public String getUFOrigemTributacao() {
		return UFOrigemTributacao;
	}

	public void setUFOrigemTributacao(String uFOrigemTributacao) {
		UFOrigemTributacao = uFOrigemTributacao;
	}

	public String getOperacaoTributacao() {
		return OperacaoTributacao;
	}

	public void setOperacaoTributacao(String operacaoTributacao) {
		OperacaoTributacao = operacaoTributacao;
	}

	public String getUFDestinoTributacao() {
		return UFDestinoTributacao;
	}

	public void setUFDestinoTributacao(String uFDestinoTributacao) {
		UFDestinoTributacao = uFDestinoTributacao;
	}

	public String getComplementoTributacao() {
		return ComplementoTributacao;
	}

	public void setComplementoTributacao(String complementoTributacao) {
		ComplementoTributacao = complementoTributacao;
	}

	public String getClassificacaoTributariaIcmsTributacao() {
		return ClassificacaoTributariaIcmsTributacao;
	}

	public void setClassificacaoTributariaIcmsTributacao(
			String classificacaoTributariaIcmsTributacao) {
		ClassificacaoTributariaIcmsTributacao = classificacaoTributariaIcmsTributacao;
	}

	public String getCfopTributacao() {
		return CfopTributacao;
	}

	public void setCfopTributacao(String cfopTributacao) {
		CfopTributacao = cfopTributacao;
	}

	public String getFormaCalculoIcmsTributacao() {
		return FormaCalculoIcmsTributacao;
	}

	public void setFormaCalculoIcmsTributacao(String formaCalculoIcmsTributacao) {
		FormaCalculoIcmsTributacao = formaCalculoIcmsTributacao;
	}

	public BigDecimal getAliquotaIcmsTributacao() {
		return AliquotaIcmsTributacao;
	}

	public void setAliquotaIcmsTributacao(BigDecimal aliquotaIcmsTributacao) {
		AliquotaIcmsTributacao = aliquotaIcmsTributacao;
	}

	public BigDecimal getReducaoBaseCalculoIcmsTributacao() {
		return ReducaoBaseCalculoIcmsTributacao;
	}

	public void setReducaoBaseCalculoIcmsTributacao(
			BigDecimal reducaoBaseCalculoIcmsTributacao) {
		ReducaoBaseCalculoIcmsTributacao = reducaoBaseCalculoIcmsTributacao;
	}

	public BigDecimal getMargemValorAgregadoTributacao() {
		return MargemValorAgregadoTributacao;
	}

	public void setMargemValorAgregadoTributacao(
			BigDecimal margemValorAgregadoTributacao) {
		MargemValorAgregadoTributacao = margemValorAgregadoTributacao;
	}

	public String getIpiBaseIcmsTributacao() {
		return IpiBaseIcmsTributacao;
	}

	public void setIpiBaseIcmsTributacao(String ipiBaseIcmsTributacao) {
		IpiBaseIcmsTributacao = ipiBaseIcmsTributacao;
	}

	public String getOutrasDespesasBaseIcmsTributacao() {
		return OutrasDespesasBaseIcmsTributacao;
	}

	public void setOutrasDespesasBaseIcmsTributacao(
			String outrasDespesasBaseIcmsTributacao) {
		OutrasDespesasBaseIcmsTributacao = outrasDespesasBaseIcmsTributacao;
	}

	public void setNomeUFDestinoTributacao(String nomeUFDestinoTributacao) {
		NomeUFDestinoTributacao = nomeUFDestinoTributacao;
	}

	public String getNomeUFDestinoTributacao() {
		return NomeUFDestinoTributacao;
	}

	public void setDescricaoOperacaoTributacao(
			String descricaoOperacaoTributacao) {
		DescricaoOperacaoTributacao = descricaoOperacaoTributacao;
	}

	public String getDescricaoOperacaoTributacao() {
		return DescricaoOperacaoTributacao;
	}

	public void setCstPisTributacao(String cstPisTributacao) {
		CstPisTributacao = cstPisTributacao;
	}

	public String getCstPisTributacao() {
		return CstPisTributacao;
	}

	public void setAliquotaPisTributacao(BigDecimal aliquotaPisTributacao) {
		AliquotaPisTributacao = aliquotaPisTributacao;
	}

	public BigDecimal getAliquotaPisTributacao() {
		return AliquotaPisTributacao;
	}

	public void setCstCofinsTributacao(String cstCofinsTributacao) {
		CstCofinsTributacao = cstCofinsTributacao;
	}

	public String getCstCofinsTributacao() {
		return CstCofinsTributacao;
	}

	public void setAliquotaCofinsTributacao(BigDecimal aliquotaCofinsTributacao) {
		AliquotaCofinsTributacao = aliquotaCofinsTributacao;
	}

	public BigDecimal getAliquotaCofinsTributacao() {
		return AliquotaCofinsTributacao;
	}

	public void setCstIpiTributacao(String cstIpiTributacao) {
		CstIpiTributacao = cstIpiTributacao;
	}

	public String getCstIpiTributacao() {
		return CstIpiTributacao;
	}

	public void setAliquotaIpiTributacao(BigDecimal aliquotaIpiTributacao) {
		AliquotaIpiTributacao = aliquotaIpiTributacao;
	}

	public BigDecimal getAliquotaIpiTributacao() {
		return AliquotaIpiTributacao;
	}

	public void setIdentificacaoTipoTributacao(
			String identificacaoTipoTributacao) {
		IdentificacaoTipoTributacao = identificacaoTipoTributacao;
	}

	public String getIdentificacaoTipoTributacao() {
		return IdentificacaoTipoTributacao;
	}

	public void setAliquotaIcmsStTributacao(BigDecimal aliquotaIcmsStTributacao) {
		AliquotaIcmsStTributacao = aliquotaIcmsStTributacao;
	}

	public BigDecimal getAliquotaIcmsStTributacao() {
		return AliquotaIcmsStTributacao;
	}
}