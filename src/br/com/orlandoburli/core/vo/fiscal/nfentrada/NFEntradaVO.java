package br.com.orlandoburli.core.vo.fiscal.nfentrada;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class NFEntradaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoNFEntrada;
	@Key
	private String SerieNFEntrada;
	@Key
	private Integer CodigoEmpresaEmitenteNFEntrada;
	@Key
	private Integer CodigoLojaEmitenteNFEntrada;
	@Key
	private Integer CodigoPessoaEmitenteNFEntrada;
	@Key
	private Integer CodigoEnderecoPessoaEmitenteNFEntrada;
	
	private String ChaveNFeEntrada;
	
	private Integer FormaPagamentoNFEntrada;
	private String OperacaoTributacao;
	private String ModeloNFEntrada;
	private Date DataEmissaoNFEntrada;
	private Timestamp DataHoraEntradaNFEntrada;
	
	private String StatusNFEntrada;
	
	private String ModalidadeFreteNFEntrada;
	
	private Integer CodigoEmpresaTransportadoraNFEntrada;
	private Integer CodigoLojaTransportadoraNFEntrada;
	private Integer CodigoPessoaTransportadoraNFEntrada;
	private Integer CodigoEnderecoPessoaTransportadoraNFEntrada;
	
	private Integer QuantidadeVolumesTransporteNFEntrada;
	private String EspecieVolumeTransporteNFEntrada;
	
	@Precision(decimals=3)
	private BigDecimal PesoBrutoTransporteNFEntrada;
	@Precision(decimals=3)
	private BigDecimal PesoLiquitoTransporteNFEntrada;
	
	private String PlacaVeiculoTransporteNFEntrada;
	private String UFPlacaVeiculoTransporteNFEntrada;
	
	private String InscricaoEstadualSTNFEntrada;
	private String MarcaVolumeTransporteNFEntrada;
	private String NumeracaoVolumesTransporteNFEntrada;
	
	@Precision(decimals=2)
	private BigDecimal ValorBaseCalculoIcmsNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorBaseCalculoIcmsSTNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsSTNFEntrada;
	
	@Precision(decimals=2)
	private BigDecimal ValorTotalProdutosNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorFreteNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorSeguroNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalDescontoNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalIpiNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalPisNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalCofinsNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorOutrasDespesasNFEntrada;
	@Precision(decimals=2)
	private BigDecimal ValorTotalNFEntrada;
	
	private String ObservacaoNFEntrada;
	
	@Formula(getFormula="(SELECT b.NomeRazaoSocialPessoa FROM [schema].Pessoa b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresaEmitenteNFEntrada" +
			"                AND b.CodigoLoja = a.CodigoLojaEmitenteNFEntrada" +
			"                AND b.CodigoPessoa = a.CodigoPessoaEmitenteNFEntrada)")
	private String NomeEmitente;
	
	@Ignore
	private List<ItemNFEntradaVO> ItensNFEntrada;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    public NFEntradaVO() {
    	super();
    	this.ItensNFEntrada = new ArrayList<ItemNFEntradaVO>();
    }

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

	/**
	 * @return the codigoEmpresa
	 */
	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the codigoLoja
	 */
	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	/**
	 * @param codigoLoja the codigoLoja to set
	 */
	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	/**
	 * @return the codigoNFEntrada
	 */
	public Integer getCodigoNFEntrada() {
		return CodigoNFEntrada;
	}

	/**
	 * @param codigoNFEntrada the codigoNFEntrada to set
	 */
	public void setCodigoNFEntrada(Integer codigoNFEntrada) {
		CodigoNFEntrada = codigoNFEntrada;
	}

	/**
	 * @return the serieNFEntrada
	 */
	public String getSerieNFEntrada() {
		return SerieNFEntrada;
	}

	/**
	 * @param serieNFEntrada the serieNFEntrada to set
	 */
	public void setSerieNFEntrada(String serieNFEntrada) {
		SerieNFEntrada = serieNFEntrada;
	}

	/**
	 * @return the formaPagamentoNFEntrada
	 */
	public Integer getFormaPagamentoNFEntrada() {
		return FormaPagamentoNFEntrada;
	}

	/**
	 * @param formaPagamentoNFEntrada the formaPagamentoNFEntrada to set
	 */
	public void setFormaPagamentoNFEntrada(Integer formaPagamentoNFEntrada) {
		FormaPagamentoNFEntrada = formaPagamentoNFEntrada;
	}

	/**
	 * @return the modeloNFEntrada
	 */
	public String getModeloNFEntrada() {
		return ModeloNFEntrada;
	}

	/**
	 * @param modeloNFEntrada the modeloNFEntrada to set
	 */
	public void setModeloNFEntrada(String modeloNFEntrada) {
		ModeloNFEntrada = modeloNFEntrada;
	}

	/**
	 * @return the dataEmissaoNFEntrada
	 */
	public Date getDataEmissaoNFEntrada() {
		return DataEmissaoNFEntrada;
	}

	/**
	 * @param dataEmissaoNFEntrada the dataEmissaoNFEntrada to set
	 */
	public void setDataEmissaoNFEntrada(Date dataEmissaoNFEntrada) {
		DataEmissaoNFEntrada = dataEmissaoNFEntrada;
	}

	/**
	 * @return the dataHoraEntradaNFEntrada
	 */
	public Timestamp getDataHoraEntradaNFEntrada() {
		return DataHoraEntradaNFEntrada;
	}

	/**
	 * @param dataHoraEntradaNFEntrada the dataHoraEntradaNFEntrada to set
	 */
	public void setDataHoraEntradaNFEntrada(Timestamp dataHoraEntradaNFEntrada) {
		DataHoraEntradaNFEntrada = dataHoraEntradaNFEntrada;
	}

	/**
	 * @return the codigoEmpresaEmitenteNFEntrada
	 */
	public Integer getCodigoEmpresaEmitenteNFEntrada() {
		return CodigoEmpresaEmitenteNFEntrada;
	}

	/**
	 * @param codigoEmpresaEmitenteNFEntrada the codigoEmpresaEmitenteNFEntrada to set
	 */
	public void setCodigoEmpresaEmitenteNFEntrada(
			Integer codigoEmpresaEmitenteNFEntrada) {
		CodigoEmpresaEmitenteNFEntrada = codigoEmpresaEmitenteNFEntrada;
	}

	/**
	 * @return the codigoLojaEmitenteNFEntrada
	 */
	public Integer getCodigoLojaEmitenteNFEntrada() {
		return CodigoLojaEmitenteNFEntrada;
	}

	/**
	 * @param codigoLojaEmitenteNFEntrada the codigoLojaEmitenteNFEntrada to set
	 */
	public void setCodigoLojaEmitenteNFEntrada(
			Integer codigoLojaEmitenteNFEntrada) {
		CodigoLojaEmitenteNFEntrada = codigoLojaEmitenteNFEntrada;
	}

	/**
	 * @return the codigoPessoaEmitenteNFEntrada
	 */
	public Integer getCodigoPessoaEmitenteNFEntrada() {
		return CodigoPessoaEmitenteNFEntrada;
	}

	/**
	 * @param codigoPessoaEmitenteNFEntrada the codigoPessoaEmitenteNFEntrada to set
	 */
	public void setCodigoPessoaEmitenteNFEntrada(
			Integer codigoPessoaEmitenteNFEntrada) {
		CodigoPessoaEmitenteNFEntrada = codigoPessoaEmitenteNFEntrada;
	}

	/**
	 * @return the codigoEnderecoPessoaEmitenteNFEntrada
	 */
	public Integer getCodigoEnderecoPessoaEmitenteNFEntrada() {
		return CodigoEnderecoPessoaEmitenteNFEntrada;
	}

	/**
	 * @param codigoEnderecoPessoaEmitenteNFEntrada the codigoEnderecoPessoaEmitenteNFEntrada to set
	 */
	public void setCodigoEnderecoPessoaEmitenteNFEntrada(
			Integer codigoEnderecoPessoaEmitenteNFEntrada) {
		CodigoEnderecoPessoaEmitenteNFEntrada = codigoEnderecoPessoaEmitenteNFEntrada;
	}

	/**
	 * @return the modalidadeFreteNFEntrada
	 */
	public String getModalidadeFreteNFEntrada() {
		return ModalidadeFreteNFEntrada;
	}

	/**
	 * @param modalidadeFreteNFEntrada the modalidadeFreteNFEntrada to set
	 */
	public void setModalidadeFreteNFEntrada(String modalidadeFreteNFEntrada) {
		ModalidadeFreteNFEntrada = modalidadeFreteNFEntrada;
	}

	/**
	 * @return the codigoEmpresaTransportadoraNFEntrada
	 */
	public Integer getCodigoEmpresaTransportadoraNFEntrada() {
		return CodigoEmpresaTransportadoraNFEntrada;
	}

	/**
	 * @param codigoEmpresaTransportadoraNFEntrada the codigoEmpresaTransportadoraNFEntrada to set
	 */
	public void setCodigoEmpresaTransportadoraNFEntrada(
			Integer codigoEmpresaTransportadoraNFEntrada) {
		CodigoEmpresaTransportadoraNFEntrada = codigoEmpresaTransportadoraNFEntrada;
	}

	/**
	 * @return the codigoLojaTransportadoraNFEntrada
	 */
	public Integer getCodigoLojaTransportadoraNFEntrada() {
		return CodigoLojaTransportadoraNFEntrada;
	}

	/**
	 * @param codigoLojaTransportadoraNFEntrada the codigoLojaTransportadoraNFEntrada to set
	 */
	public void setCodigoLojaTransportadoraNFEntrada(
			Integer codigoLojaTransportadoraNFEntrada) {
		CodigoLojaTransportadoraNFEntrada = codigoLojaTransportadoraNFEntrada;
	}

	/**
	 * @return the codigoPessoaTransportadoraNFEntrada
	 */
	public Integer getCodigoPessoaTransportadoraNFEntrada() {
		return CodigoPessoaTransportadoraNFEntrada;
	}

	/**
	 * @param codigoPessoaTransportadoraNFEntrada the codigoPessoaTransportadoraNFEntrada to set
	 */
	public void setCodigoPessoaTransportadoraNFEntrada(
			Integer codigoPessoaTransportadoraNFEntrada) {
		CodigoPessoaTransportadoraNFEntrada = codigoPessoaTransportadoraNFEntrada;
	}

	/**
	 * @return the codigoEnderecoPessoaTransportadoraNFEntrada
	 */
	public Integer getCodigoEnderecoPessoaTransportadoraNFEntrada() {
		return CodigoEnderecoPessoaTransportadoraNFEntrada;
	}

	/**
	 * @param codigoEnderecoPessoaTransportadoraNFEntrada the codigoEnderecoPessoaTransportadoraNFEntrada to set
	 */
	public void setCodigoEnderecoPessoaTransportadoraNFEntrada(
			Integer codigoEnderecoPessoaTransportadoraNFEntrada) {
		CodigoEnderecoPessoaTransportadoraNFEntrada = codigoEnderecoPessoaTransportadoraNFEntrada;
	}

	/**
	 * @return the pesoBrutoTransporteNFEntrada
	 */
	public BigDecimal getPesoBrutoTransporteNFEntrada() {
		return PesoBrutoTransporteNFEntrada;
	}

	/**
	 * @param pesoBrutoTransporteNFEntrada the pesoBrutoTransporteNFEntrada to set
	 */
	public void setPesoBrutoTransporteNFEntrada(BigDecimal pesoBrutoTransporteNFEntrada) {
		PesoBrutoTransporteNFEntrada = pesoBrutoTransporteNFEntrada;
	}

	/**
	 * @return the pesoLiquitoTransporteNFEntrada
	 */
	public BigDecimal getPesoLiquitoTransporteNFEntrada() {
		return PesoLiquitoTransporteNFEntrada;
	}

	/**
	 * @param pesoLiquitoTransporteNFEntrada the pesoLiquitoTransporteNFEntrada to set
	 */
	public void setPesoLiquitoTransporteNFEntrada(
			BigDecimal pesoLiquitoTransporteNFEntrada) {
		PesoLiquitoTransporteNFEntrada = pesoLiquitoTransporteNFEntrada;
	}

	/**
	 * @return the placaVeiculoTransporteNFEntrada
	 */
	public String getPlacaVeiculoTransporteNFEntrada() {
		return PlacaVeiculoTransporteNFEntrada;
	}

	/**
	 * @param placaVeiculoTransporteNFEntrada the placaVeiculoTransporteNFEntrada to set
	 */
	public void setPlacaVeiculoTransporteNFEntrada(
			String placaVeiculoTransporteNFEntrada) {
		PlacaVeiculoTransporteNFEntrada = placaVeiculoTransporteNFEntrada;
	}

	/**
	 * @return the uFPlacaVeiculoTransporteNFEntrada
	 */
	public String getUFPlacaVeiculoTransporteNFEntrada() {
		return UFPlacaVeiculoTransporteNFEntrada;
	}

	/**
	 * @param uFPlacaVeiculoTransporteNFEntrada the uFPlacaVeiculoTransporteNFEntrada to set
	 */
	public void setUFPlacaVeiculoTransporteNFEntrada(
			String uFPlacaVeiculoTransporteNFEntrada) {
		UFPlacaVeiculoTransporteNFEntrada = uFPlacaVeiculoTransporteNFEntrada;
	}

	/**
	 * @return the inscricaoEstadualSTNFEntrada
	 */
	public String getInscricaoEstadualSTNFEntrada() {
		return InscricaoEstadualSTNFEntrada;
	}

	/**
	 * @param inscricaoEstadualSTNFEntrada the inscricaoEstadualSTNFEntrada to set
	 */
	public void setInscricaoEstadualSTNFEntrada(String inscricaoEstadualSTNFEntrada) {
		InscricaoEstadualSTNFEntrada = inscricaoEstadualSTNFEntrada;
	}

	/**
	 * @return the marcaVolumeTransporteNFEntrada
	 */
	public String getMarcaVolumeTransporteNFEntrada() {
		return MarcaVolumeTransporteNFEntrada;
	}

	/**
	 * @param marcaVolumeTransporteNFEntrada the marcaVolumeTransporteNFEntrada to set
	 */
	public void setMarcaVolumeTransporteNFEntrada(String marcaVolumeTransporteNFEntrada) {
		MarcaVolumeTransporteNFEntrada = marcaVolumeTransporteNFEntrada;
	}

	/**
	 * @return the numeracaoVolumesTransporteNFEntrada
	 */
	public String getNumeracaoVolumesTransporteNFEntrada() {
		return NumeracaoVolumesTransporteNFEntrada;
	}

	/**
	 * @param numeracaoVolumesTransporteNFEntrada the numeracaoVolumesTransporteNFEntrada to set
	 */
	public void setNumeracaoVolumesTransporteNFEntrada(
			String numeracaoVolumesTransporteNFEntrada) {
		NumeracaoVolumesTransporteNFEntrada = numeracaoVolumesTransporteNFEntrada;
	}

	/**
	 * @return the valorBaseCalculoIcmsNFEntrada
	 */
	public BigDecimal getValorBaseCalculoIcmsNFEntrada() {
		return ValorBaseCalculoIcmsNFEntrada;
	}

	/**
	 * @param valorBaseCalculoIcmsNFEntrada the valorBaseCalculoIcmsNFEntrada to set
	 */
	public void setValorBaseCalculoIcmsNFEntrada(
			BigDecimal valorBaseCalculoIcmsNFEntrada) {
		ValorBaseCalculoIcmsNFEntrada = valorBaseCalculoIcmsNFEntrada;
	}

	/**
	 * @return the valorIcmsNFEntrada
	 */
	public BigDecimal getValorIcmsNFEntrada() {
		return ValorIcmsNFEntrada;
	}

	/**
	 * @param valorIcmsNFEntrada the valorIcmsNFEntrada to set
	 */
	public void setValorIcmsNFEntrada(BigDecimal valorIcmsNFEntrada) {
		ValorIcmsNFEntrada = valorIcmsNFEntrada;
	}

	/**
	 * @return the valorBaseCalculoIcmsSTNFEntrada
	 */
	public BigDecimal getValorBaseCalculoIcmsSTNFEntrada() {
		return ValorBaseCalculoIcmsSTNFEntrada;
	}

	/**
	 * @param valorBaseCalculoIcmsSTNFEntrada the valorBaseCalculoIcmsSTNFEntrada to set
	 */
	public void setValorBaseCalculoIcmsSTNFEntrada(
			BigDecimal valorBaseCalculoIcmsSTNFEntrada) {
		ValorBaseCalculoIcmsSTNFEntrada = valorBaseCalculoIcmsSTNFEntrada;
	}

	/**
	 * @return the valorIcmsSTNFEntrada
	 */
	public BigDecimal getValorIcmsSTNFEntrada() {
		return ValorIcmsSTNFEntrada;
	}

	/**
	 * @param valorIcmsSTNFEntrada the valorIcmsSTNFEntrada to set
	 */
	public void setValorIcmsSTNFEntrada(BigDecimal valorIcmsSTNFEntrada) {
		ValorIcmsSTNFEntrada = valorIcmsSTNFEntrada;
	}

	/**
	 * @return the valorTotalProdutosNFEntrada
	 */
	public BigDecimal getValorTotalProdutosNFEntrada() {
		return ValorTotalProdutosNFEntrada;
	}

	/**
	 * @param valorTotalProdutosNFEntrada the valorTotalProdutosNFEntrada to set
	 */
	public void setValorTotalProdutosNFEntrada(BigDecimal valorTotalProdutosNFEntrada) {
		ValorTotalProdutosNFEntrada = valorTotalProdutosNFEntrada;
	}

	/**
	 * @return the valorFreteNFEntrada
	 */
	public BigDecimal getValorFreteNFEntrada() {
		return ValorFreteNFEntrada;
	}

	/**
	 * @param valorFreteNFEntrada the valorFreteNFEntrada to set
	 */
	public void setValorFreteNFEntrada(BigDecimal valorFreteNFEntrada) {
		ValorFreteNFEntrada = valorFreteNFEntrada;
	}

	/**
	 * @return the valorSeguroNFEntrada
	 */
	public BigDecimal getValorSeguroNFEntrada() {
		return ValorSeguroNFEntrada;
	}

	/**
	 * @param valorSeguroNFEntrada the valorSeguroNFEntrada to set
	 */
	public void setValorSeguroNFEntrada(BigDecimal valorSeguroNFEntrada) {
		ValorSeguroNFEntrada = valorSeguroNFEntrada;
	}

	/**
	 * @return the valorTotalDescontoNFEntrada
	 */
	public BigDecimal getValorTotalDescontoNFEntrada() {
		return ValorTotalDescontoNFEntrada;
	}

	/**
	 * @param valorTotalDescontoNFEntrada the valorTotalDescontoNFEntrada to set
	 */
	public void setValorTotalDescontoNFEntrada(BigDecimal valorTotalDescontoNFEntrada) {
		ValorTotalDescontoNFEntrada = valorTotalDescontoNFEntrada;
	}

	/**
	 * @return the valorTotalIpiNFEntrada
	 */
	public BigDecimal getValorTotalIpiNFEntrada() {
		return ValorTotalIpiNFEntrada;
	}

	/**
	 * @param valorTotalIpiNFEntrada the valorTotalIpiNFEntrada to set
	 */
	public void setValorTotalIpiNFEntrada(BigDecimal valorTotalIpiNFEntrada) {
		ValorTotalIpiNFEntrada = valorTotalIpiNFEntrada;
	}

	/**
	 * @return the valorTotalPisNFEntrada
	 */
	public BigDecimal getValorTotalPisNFEntrada() {
		return ValorTotalPisNFEntrada;
	}

	/**
	 * @param valorTotalPisNFEntrada the valorTotalPisNFEntrada to set
	 */
	public void setValorTotalPisNFEntrada(BigDecimal valorTotalPisNFEntrada) {
		ValorTotalPisNFEntrada = valorTotalPisNFEntrada;
	}

	/**
	 * @return the valorTotalCofinsNFEntrada
	 */
	public BigDecimal getValorTotalCofinsNFEntrada() {
		return ValorTotalCofinsNFEntrada;
	}

	/**
	 * @param valorTotalCofinsNFEntrada the valorTotalCofinsNFEntrada to set
	 */
	public void setValorTotalCofinsNFEntrada(BigDecimal valorTotalCofinsNFEntrada) {
		ValorTotalCofinsNFEntrada = valorTotalCofinsNFEntrada;
	}

	/**
	 * @return the valorOutrasDespesasNFEntrada
	 */
	public BigDecimal getValorOutrasDespesasNFEntrada() {
		return ValorOutrasDespesasNFEntrada;
	}

	/**
	 * @param valorOutrasDespesasNFEntrada the valorOutrasDespesasNFEntrada to set
	 */
	public void setValorOutrasDespesasNFEntrada(BigDecimal valorOutrasDespesasNFEntrada) {
		ValorOutrasDespesasNFEntrada = valorOutrasDespesasNFEntrada;
	}

	/**
	 * @return the valorTotalNFEntrada
	 */
	public BigDecimal getValorTotalNFEntrada() {
		return ValorTotalNFEntrada;
	}

	/**
	 * @param valorTotalNFEntrada the valorTotalNFEntrada to set
	 */
	public void setValorTotalNFEntrada(BigDecimal valorTotalNFEntrada) {
		ValorTotalNFEntrada = valorTotalNFEntrada;
	}

	public void setQuantidadeVolumesTransporteNFEntrada(
			Integer quantidadeVolumesTransporteNFEntrada) {
		QuantidadeVolumesTransporteNFEntrada = quantidadeVolumesTransporteNFEntrada;
	}

	public Integer getQuantidadeVolumesTransporteNFEntrada() {
		return QuantidadeVolumesTransporteNFEntrada;
	}

	public void setEspecieVolumeTransporteNFEntrada(
			String especieVolumeTransporteNFEntrada) {
		EspecieVolumeTransporteNFEntrada = especieVolumeTransporteNFEntrada;
	}

	public String getEspecieVolumeTransporteNFEntrada() {
		return EspecieVolumeTransporteNFEntrada;
	}

	public void setNomeEmitente(String nomeEmitente) {
		NomeEmitente = nomeEmitente;
	}

	public String getNomeEmitente() {
		return NomeEmitente;
	}

	public void setStatusNFEntrada(String statusNFEntrada) {
		StatusNFEntrada = statusNFEntrada;
	}

	public String getStatusNFEntrada() {
		return StatusNFEntrada;
	}

	public void setObservacaoNFEntrada(String observacaoNFEntrada) {
		ObservacaoNFEntrada = observacaoNFEntrada;
	}

	public String getObservacaoNFEntrada() {
		return ObservacaoNFEntrada;
	}

	public void setOperacaoTributacao(String operacaoTributacao) {
		OperacaoTributacao = operacaoTributacao;
	}

	public String getOperacaoTributacao() {
		return OperacaoTributacao;
	}

	public void setChaveNFeEntrada(String chaveNFeEntrada) {
		ChaveNFeEntrada = chaveNFeEntrada;
	}

	public String getChaveNFeEntrada() {
		return ChaveNFeEntrada;
	}

	public List<ItemNFEntradaVO> getItensNFEntrada() {
		return ItensNFEntrada;
	}

	public void setItensNFEntrada(List<ItemNFEntradaVO> itensNFEntrada) {
		ItensNFEntrada = itensNFEntrada;
	}
}
