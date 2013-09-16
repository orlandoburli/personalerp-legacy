package br.com.orlandoburli.core.vo.estoque;

import java.math.BigDecimal;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ProdutoVO implements IValueObject {
	
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoProduto;
	private String DescricaoProduto;
	private String CodigoReferenciaProduto;
	private String TipoEstoqueProduto;
	private Integer CodigoGrupo;
	private Integer CodigoSubGrupo;
	private Integer CodigoFabricante;
	private Integer CodigoTipoTributacao;
	private String SiglaUnidade;
	@Precision(decimals=2)
	private BigDecimal ComprimentoProduto;
	@Precision(decimals=2)
	private BigDecimal LarguraProduto;
	@Precision(decimals=2)
	private BigDecimal AlturaProduto;
	private BigDecimal VolumeProduto;
	private String PermiteVendaFracionadaProduto;
	private BigDecimal MargemLucroVarejoProduto;
	private BigDecimal MargemLucroAtacadoProduto;
	private Integer CodigoEmpresaFornecedor;
	private Integer CodigoLojaFornecedor;
	private Integer CodigoPessoaFornecedor;
	private BigDecimal EstoqueMinimoProduto;
	private String SituacaoProduto;
	private String ObservacoesProduto;
	private Integer CodigoEssencia;
	private Integer CodigoTipoMadeiraBeneficiada;
	private String CodigoNCMProduto;
	
	@Ignore
	private BigDecimal ValorVendaAtacadoProduto;
	@Ignore
	private BigDecimal ValorVendaVarejoProduto;
	
	@Ignore
	private BigDecimal QuantidadeDisplayProduto;
	@Ignore
	private BigDecimal QuantidadeGavetaProduto;
	
	@Formula(getFormula="(SELECT CASE WHEN ( COALESCE(COUNT(1), 0)) > 0 THEN 'S' ELSE 'N' END FROM [schema].SubProduto b " +
			" WHERE a.CodigoEmpresa = b.CodigoEmpresa " +
			"   AND a.CodigoLoja    = b.CodigoLoja " +
			"   AND a.CodigoProduto = b.CodigoProduto)")
	private String FlagTemSubProduto;
	
	@Override
	public String toString() {
		return Utils.voToXml(this);
	}
	
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

	public String getDescricaoProduto() {
		return DescricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		DescricaoProduto = descricaoProduto;
	}

	public String getCodigoReferenciaProduto() {
		return CodigoReferenciaProduto;
	}

	public void setCodigoReferenciaProduto(String codigoReferenciaProduto) {
		CodigoReferenciaProduto = codigoReferenciaProduto;
	}

	public String getTipoEstoqueProduto() {
		return TipoEstoqueProduto;
	}

	public void setTipoEstoqueProduto(String tipoEstoqueProduto) {
		TipoEstoqueProduto = tipoEstoqueProduto;
	}

	public Integer getCodigoGrupo() {
		return CodigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		CodigoGrupo = codigoGrupo;
	}

	public Integer getCodigoSubGrupo() {
		return CodigoSubGrupo;
	}

	public void setCodigoSubGrupo(Integer codigoSubGrupo) {
		CodigoSubGrupo = codigoSubGrupo;
	}

	public Integer getCodigoFabricante() {
		return CodigoFabricante;
	}

	public void setCodigoFabricante(Integer codigoFabricante) {
		CodigoFabricante = codigoFabricante;
	}

	public String getSiglaUnidade() {
		return SiglaUnidade;
	}

	public void setSiglaUnidade(String siglaUnidade) {
		SiglaUnidade = siglaUnidade;
	}

	public BigDecimal getComprimentoProduto() {
		return ComprimentoProduto;
	}

	public void setComprimentoProduto(BigDecimal comprimentoProduto) {
		ComprimentoProduto = comprimentoProduto;
	}

	public BigDecimal getLarguraProduto() {
		return LarguraProduto;
	}

	public void setLarguraProduto(BigDecimal larguraProduto) {
		LarguraProduto = larguraProduto;
	}

	public String getPermiteVendaFracionadaProduto() {
		return PermiteVendaFracionadaProduto;
	}

	public void setPermiteVendaFracionadaProduto(
			String permiteVendaFracionadaProduto) {
		PermiteVendaFracionadaProduto = permiteVendaFracionadaProduto;
	}

	public BigDecimal getMargemLucroVarejoProduto() {
		return MargemLucroVarejoProduto;
	}

	public void setMargemLucroVarejoProduto(BigDecimal margemLucroVarejoProduto) {
		MargemLucroVarejoProduto = margemLucroVarejoProduto;
	}

	public BigDecimal getMargemLucroAtacadoProduto() {
		return MargemLucroAtacadoProduto;
	}

	public void setMargemLucroAtacadoProduto(BigDecimal margemLucroAtacadoProduto) {
		MargemLucroAtacadoProduto = margemLucroAtacadoProduto;
	}

	public Integer getCodigoEmpresaFornecedor() {
		return CodigoEmpresaFornecedor;
	}

	public void setCodigoEmpresaFornecedor(Integer codigoEmpresaFornecedor) {
		CodigoEmpresaFornecedor = codigoEmpresaFornecedor;
	}

	public Integer getCodigoLojaFornecedor() {
		return CodigoLojaFornecedor;
	}

	public void setCodigoLojaFornecedor(Integer codigoLojaFornecedor) {
		CodigoLojaFornecedor = codigoLojaFornecedor;
	}

	public Integer getCodigoPessoaFornecedor() {
		return CodigoPessoaFornecedor;
	}

	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaFornecedor = codigoPessoaFornecedor;
	}

	public BigDecimal getEstoqueMinimoProduto() {
		return EstoqueMinimoProduto;
	}

	public void setEstoqueMinimoProduto(BigDecimal estoqueMinimoProduto) {
		EstoqueMinimoProduto = estoqueMinimoProduto;
	}

	public String getSituacaoProduto() {
		return SituacaoProduto;
	}

	public void setSituacaoProduto(String situacaoProduto) {
		SituacaoProduto = situacaoProduto;
	}

	public String getObservacoesProduto() {
		return ObservacoesProduto;
	}

	public void setObservacoesProduto(String observacoesProduto) {
		ObservacoesProduto = observacoesProduto;
	}

	public void setValorVendaAtacadoProduto(BigDecimal valorVendaAtacadoProduto) {
		ValorVendaAtacadoProduto = valorVendaAtacadoProduto;
	}

	public BigDecimal getValorVendaAtacadoProduto() {
		return ValorVendaAtacadoProduto;
	}

	public void setValorVendaVarejoProduto(BigDecimal valorVendaVarejoProduto) {
		ValorVendaVarejoProduto = valorVendaVarejoProduto;
	}

	public BigDecimal getValorVendaVarejoProduto() {
		return ValorVendaVarejoProduto;
	}

	public void setCodigoEssencia(Integer codigoEssencia) {
		CodigoEssencia = codigoEssencia;
	}

	public Integer getCodigoEssencia() {
		return CodigoEssencia;
	}

	public void setVolumeProduto(BigDecimal volumeProduto) {
		VolumeProduto = volumeProduto;
	}

	public BigDecimal getVolumeProduto() {
		return VolumeProduto;
	}

	public void setAlturaProduto(BigDecimal alturaProduto) {
		AlturaProduto = alturaProduto;
	}

	public BigDecimal getAlturaProduto() {
		return AlturaProduto;
	}

	public void setCodigoTipoMadeiraBeneficiada(Integer codigoTipoMadeiraBeneficiada) {
		CodigoTipoMadeiraBeneficiada = codigoTipoMadeiraBeneficiada;
	}

	public Integer getCodigoTipoMadeiraBeneficiada() {
		return CodigoTipoMadeiraBeneficiada;
	}

	public void setCodigoTipoTributacao(Integer codigoTipoTributacao) {
		CodigoTipoTributacao = codigoTipoTributacao;
	}

	public Integer getCodigoTipoTributacao() {
		return CodigoTipoTributacao;
	}

	public void setCodigoNCMProduto(String codigoNCMProduto) {
		CodigoNCMProduto = codigoNCMProduto;
	}

	public String getCodigoNCMProduto() {
		return CodigoNCMProduto;
	}

	public void setFlagTemSubProduto(String flagTemSubProduto) {
		FlagTemSubProduto = flagTemSubProduto;
	}

	public String getFlagTemSubProduto() {
		return FlagTemSubProduto;
	}

	public BigDecimal getQuantidadeDisplayProduto() {
		return QuantidadeDisplayProduto;
	}

	public void setQuantidadeDisplayProduto(BigDecimal quantidadeDisplayProduto) {
		QuantidadeDisplayProduto = quantidadeDisplayProduto;
	}

	public BigDecimal getQuantidadeGavetaProduto() {
		return QuantidadeGavetaProduto;
	}

	public void setQuantidadeGavetaProduto(BigDecimal quantidadeGavetaProduto) {
		QuantidadeGavetaProduto = quantidadeGavetaProduto;
	}
}