package br.com.orlandoburli.core.vo.fiscal.nfsaida;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class NFSaidaVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoNFSaida;
	@Key
	private String SerieNFSaida;
	
	private Integer CodigoNfe;
	
	private Integer FormaPagamentoNFSaida;
	private String OperacaoTributacao;
	private String ModeloNFSaida;
	private Date DataEmissaoNFSaida;
	private Timestamp DataHoraSaidaNFSaida;
	
	private String StatusNFSaida;
	
	private Integer CodigoEmpresaDestinatarioNFSaida;
	private Integer CodigoLojaDestinatarioNFSaida;
	private Integer CodigoPessoaDestinatarioNFSaida;
	private Integer CodigoEnderecoPessoaDestinatarioNFSaida;
	
	private String ModalidadeFreteNFSaida;
	
	private Integer CodigoEmpresaTransportadoraNFSaida;
	private Integer CodigoLojaTransportadoraNFSaida;
	private Integer CodigoPessoaTransportadoraNFSaida;
	private Integer CodigoEnderecoPessoaTransportadoraNFSaida;
	
	private Integer QuantidadeVolumesTransporteNFSaida;
	private String EspecieVolumeTransporteNFSaida;
	
	@Precision(decimals=3)
	private BigDecimal PesoBrutoTransporteNFSaida;
	@Precision(decimals=3)
	private BigDecimal PesoLiquitoTransporteNFSaida;
	
	private String PlacaVeiculoTransporteNFSaida;
	private String UFPlacaVeiculoTransporteNFSaida;
	
	private String InscricaoEstadualSTNFSaida;
	private String MarcaVolumeTransporteNFSaida;
	private String NumeracaoVolumesTransporteNFSaida;
	
	@Precision(decimals=2)
	private BigDecimal ValorBaseCalculoIcmsNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorBaseCalculoIcmsSTNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorIcmsSTNFSaida;
	
	@Precision(decimals=2)
	private BigDecimal ValorTotalProdutosNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorFreteNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorSeguroNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalDescontoNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalIpiNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalPisNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalCofinsNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorOutrasDespesasNFSaida;
	@Precision(decimals=2)
	private BigDecimal ValorTotalNFSaida;
	
	private String ObservacaoNFSaida;
	
	@Formula(getFormula="(SELECT b.NomeRazaoSocialPessoa FROM [schema].Pessoa b" +
			"              WHERE b.CodigoEmpresa = a.CodigoEmpresaDestinatarioNFSaida" +
			"                AND b.CodigoLoja = a.CodigoLojaDestinatarioNFSaida" +
			"                AND b.CodigoPessoa = a.CodigoPessoaDestinatarioNFSaida)")
	private String NomeDestinatario;
	
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
	 * @return the codigoNFSaida
	 */
	public Integer getCodigoNFSaida() {
		return CodigoNFSaida;
	}

	/**
	 * @param codigoNFSaida the codigoNFSaida to set
	 */
	public void setCodigoNFSaida(Integer codigoNFSaida) {
		CodigoNFSaida = codigoNFSaida;
	}

	/**
	 * @return the serieNFSaida
	 */
	public String getSerieNFSaida() {
		return SerieNFSaida;
	}

	/**
	 * @param serieNFSaida the serieNFSaida to set
	 */
	public void setSerieNFSaida(String serieNFSaida) {
		SerieNFSaida = serieNFSaida;
	}

	/**
	 * @return the formaPagamentoNFSaida
	 */
	public Integer getFormaPagamentoNFSaida() {
		return FormaPagamentoNFSaida;
	}

	/**
	 * @param formaPagamentoNFSaida the formaPagamentoNFSaida to set
	 */
	public void setFormaPagamentoNFSaida(Integer formaPagamentoNFSaida) {
		FormaPagamentoNFSaida = formaPagamentoNFSaida;
	}

	/**
	 * @return the modeloNFSaida
	 */
	public String getModeloNFSaida() {
		return ModeloNFSaida;
	}

	/**
	 * @param modeloNFSaida the modeloNFSaida to set
	 */
	public void setModeloNFSaida(String modeloNFSaida) {
		ModeloNFSaida = modeloNFSaida;
	}

	/**
	 * @return the dataEmissaoNFSaida
	 */
	public Date getDataEmissaoNFSaida() {
		return DataEmissaoNFSaida;
	}

	/**
	 * @param dataEmissaoNFSaida the dataEmissaoNFSaida to set
	 */
	public void setDataEmissaoNFSaida(Date dataEmissaoNFSaida) {
		DataEmissaoNFSaida = dataEmissaoNFSaida;
	}

	/**
	 * @return the dataHoraSaidaNFSaida
	 */
	public Timestamp getDataHoraSaidaNFSaida() {
		return DataHoraSaidaNFSaida;
	}

	/**
	 * @param dataHoraSaidaNFSaida the dataHoraSaidaNFSaida to set
	 */
	public void setDataHoraSaidaNFSaida(Timestamp dataHoraSaidaNFSaida) {
		DataHoraSaidaNFSaida = dataHoraSaidaNFSaida;
	}

	/**
	 * @return the codigoEmpresaDestinatarioNFSaida
	 */
	public Integer getCodigoEmpresaDestinatarioNFSaida() {
		return CodigoEmpresaDestinatarioNFSaida;
	}

	/**
	 * @param codigoEmpresaDestinatarioNFSaida the codigoEmpresaDestinatarioNFSaida to set
	 */
	public void setCodigoEmpresaDestinatarioNFSaida(
			Integer codigoEmpresaDestinatarioNFSaida) {
		CodigoEmpresaDestinatarioNFSaida = codigoEmpresaDestinatarioNFSaida;
	}

	/**
	 * @return the codigoLojaDestinatarioNFSaida
	 */
	public Integer getCodigoLojaDestinatarioNFSaida() {
		return CodigoLojaDestinatarioNFSaida;
	}

	/**
	 * @param codigoLojaDestinatarioNFSaida the codigoLojaDestinatarioNFSaida to set
	 */
	public void setCodigoLojaDestinatarioNFSaida(
			Integer codigoLojaDestinatarioNFSaida) {
		CodigoLojaDestinatarioNFSaida = codigoLojaDestinatarioNFSaida;
	}

	/**
	 * @return the codigoPessoaDestinatarioNFSaida
	 */
	public Integer getCodigoPessoaDestinatarioNFSaida() {
		return CodigoPessoaDestinatarioNFSaida;
	}

	/**
	 * @param codigoPessoaDestinatarioNFSaida the codigoPessoaDestinatarioNFSaida to set
	 */
	public void setCodigoPessoaDestinatarioNFSaida(
			Integer codigoPessoaDestinatarioNFSaida) {
		CodigoPessoaDestinatarioNFSaida = codigoPessoaDestinatarioNFSaida;
	}

	/**
	 * @return the codigoEnderecoPessoaDestinatarioNFSaida
	 */
	public Integer getCodigoEnderecoPessoaDestinatarioNFSaida() {
		return CodigoEnderecoPessoaDestinatarioNFSaida;
	}

	/**
	 * @param codigoEnderecoPessoaDestinatarioNFSaida the codigoEnderecoPessoaDestinatarioNFSaida to set
	 */
	public void setCodigoEnderecoPessoaDestinatarioNFSaida(
			Integer codigoEnderecoPessoaDestinatarioNFSaida) {
		CodigoEnderecoPessoaDestinatarioNFSaida = codigoEnderecoPessoaDestinatarioNFSaida;
	}

	/**
	 * @return the modalidadeFreteNFSaida
	 */
	public String getModalidadeFreteNFSaida() {
		return ModalidadeFreteNFSaida;
	}

	/**
	 * @param modalidadeFreteNFSaida the modalidadeFreteNFSaida to set
	 */
	public void setModalidadeFreteNFSaida(String modalidadeFreteNFSaida) {
		ModalidadeFreteNFSaida = modalidadeFreteNFSaida;
	}

	/**
	 * @return the codigoEmpresaTransportadoraNFSaida
	 */
	public Integer getCodigoEmpresaTransportadoraNFSaida() {
		return CodigoEmpresaTransportadoraNFSaida;
	}

	/**
	 * @param codigoEmpresaTransportadoraNFSaida the codigoEmpresaTransportadoraNFSaida to set
	 */
	public void setCodigoEmpresaTransportadoraNFSaida(
			Integer codigoEmpresaTransportadoraNFSaida) {
		CodigoEmpresaTransportadoraNFSaida = codigoEmpresaTransportadoraNFSaida;
	}

	/**
	 * @return the codigoLojaTransportadoraNFSaida
	 */
	public Integer getCodigoLojaTransportadoraNFSaida() {
		return CodigoLojaTransportadoraNFSaida;
	}

	/**
	 * @param codigoLojaTransportadoraNFSaida the codigoLojaTransportadoraNFSaida to set
	 */
	public void setCodigoLojaTransportadoraNFSaida(
			Integer codigoLojaTransportadoraNFSaida) {
		CodigoLojaTransportadoraNFSaida = codigoLojaTransportadoraNFSaida;
	}

	/**
	 * @return the codigoPessoaTransportadoraNFSaida
	 */
	public Integer getCodigoPessoaTransportadoraNFSaida() {
		return CodigoPessoaTransportadoraNFSaida;
	}

	/**
	 * @param codigoPessoaTransportadoraNFSaida the codigoPessoaTransportadoraNFSaida to set
	 */
	public void setCodigoPessoaTransportadoraNFSaida(
			Integer codigoPessoaTransportadoraNFSaida) {
		CodigoPessoaTransportadoraNFSaida = codigoPessoaTransportadoraNFSaida;
	}

	/**
	 * @return the codigoEnderecoPessoaTransportadoraNFSaida
	 */
	public Integer getCodigoEnderecoPessoaTransportadoraNFSaida() {
		return CodigoEnderecoPessoaTransportadoraNFSaida;
	}

	/**
	 * @param codigoEnderecoPessoaTransportadoraNFSaida the codigoEnderecoPessoaTransportadoraNFSaida to set
	 */
	public void setCodigoEnderecoPessoaTransportadoraNFSaida(
			Integer codigoEnderecoPessoaTransportadoraNFSaida) {
		CodigoEnderecoPessoaTransportadoraNFSaida = codigoEnderecoPessoaTransportadoraNFSaida;
	}

	/**
	 * @return the pesoBrutoTransporteNFSaida
	 */
	public BigDecimal getPesoBrutoTransporteNFSaida() {
		return PesoBrutoTransporteNFSaida;
	}

	/**
	 * @param pesoBrutoTransporteNFSaida the pesoBrutoTransporteNFSaida to set
	 */
	public void setPesoBrutoTransporteNFSaida(BigDecimal pesoBrutoTransporteNFSaida) {
		PesoBrutoTransporteNFSaida = pesoBrutoTransporteNFSaida;
	}

	/**
	 * @return the pesoLiquitoTransporteNFSaida
	 */
	public BigDecimal getPesoLiquitoTransporteNFSaida() {
		return PesoLiquitoTransporteNFSaida;
	}

	/**
	 * @param pesoLiquitoTransporteNFSaida the pesoLiquitoTransporteNFSaida to set
	 */
	public void setPesoLiquitoTransporteNFSaida(
			BigDecimal pesoLiquitoTransporteNFSaida) {
		PesoLiquitoTransporteNFSaida = pesoLiquitoTransporteNFSaida;
	}

	/**
	 * @return the placaVeiculoTransporteNFSaida
	 */
	public String getPlacaVeiculoTransporteNFSaida() {
		return PlacaVeiculoTransporteNFSaida;
	}

	/**
	 * @param placaVeiculoTransporteNFSaida the placaVeiculoTransporteNFSaida to set
	 */
	public void setPlacaVeiculoTransporteNFSaida(
			String placaVeiculoTransporteNFSaida) {
		PlacaVeiculoTransporteNFSaida = placaVeiculoTransporteNFSaida;
	}

	/**
	 * @return the uFPlacaVeiculoTransporteNFSaida
	 */
	public String getUFPlacaVeiculoTransporteNFSaida() {
		return UFPlacaVeiculoTransporteNFSaida;
	}

	/**
	 * @param uFPlacaVeiculoTransporteNFSaida the uFPlacaVeiculoTransporteNFSaida to set
	 */
	public void setUFPlacaVeiculoTransporteNFSaida(
			String uFPlacaVeiculoTransporteNFSaida) {
		UFPlacaVeiculoTransporteNFSaida = uFPlacaVeiculoTransporteNFSaida;
	}

	/**
	 * @return the inscricaoEstadualSTNFSaida
	 */
	public String getInscricaoEstadualSTNFSaida() {
		return InscricaoEstadualSTNFSaida;
	}

	/**
	 * @param inscricaoEstadualSTNFSaida the inscricaoEstadualSTNFSaida to set
	 */
	public void setInscricaoEstadualSTNFSaida(String inscricaoEstadualSTNFSaida) {
		InscricaoEstadualSTNFSaida = inscricaoEstadualSTNFSaida;
	}

	/**
	 * @return the marcaVolumeTransporteNFSaida
	 */
	public String getMarcaVolumeTransporteNFSaida() {
		return MarcaVolumeTransporteNFSaida;
	}

	/**
	 * @param marcaVolumeTransporteNFSaida the marcaVolumeTransporteNFSaida to set
	 */
	public void setMarcaVolumeTransporteNFSaida(String marcaVolumeTransporteNFSaida) {
		MarcaVolumeTransporteNFSaida = marcaVolumeTransporteNFSaida;
	}

	/**
	 * @return the numeracaoVolumesTransporteNFSaida
	 */
	public String getNumeracaoVolumesTransporteNFSaida() {
		return NumeracaoVolumesTransporteNFSaida;
	}

	/**
	 * @param numeracaoVolumesTransporteNFSaida the numeracaoVolumesTransporteNFSaida to set
	 */
	public void setNumeracaoVolumesTransporteNFSaida(
			String numeracaoVolumesTransporteNFSaida) {
		NumeracaoVolumesTransporteNFSaida = numeracaoVolumesTransporteNFSaida;
	}

	/**
	 * @return the valorBaseCalculoIcmsNFSaida
	 */
	public BigDecimal getValorBaseCalculoIcmsNFSaida() {
		return ValorBaseCalculoIcmsNFSaida;
	}

	/**
	 * @param valorBaseCalculoIcmsNFSaida the valorBaseCalculoIcmsNFSaida to set
	 */
	public void setValorBaseCalculoIcmsNFSaida(
			BigDecimal valorBaseCalculoIcmsNFSaida) {
		ValorBaseCalculoIcmsNFSaida = valorBaseCalculoIcmsNFSaida;
	}

	/**
	 * @return the valorIcmsNFSaida
	 */
	public BigDecimal getValorIcmsNFSaida() {
		return ValorIcmsNFSaida;
	}

	/**
	 * @param valorIcmsNFSaida the valorIcmsNFSaida to set
	 */
	public void setValorIcmsNFSaida(BigDecimal valorIcmsNFSaida) {
		ValorIcmsNFSaida = valorIcmsNFSaida;
	}

	/**
	 * @return the valorBaseCalculoIcmsSTNFSaida
	 */
	public BigDecimal getValorBaseCalculoIcmsSTNFSaida() {
		return ValorBaseCalculoIcmsSTNFSaida;
	}

	/**
	 * @param valorBaseCalculoIcmsSTNFSaida the valorBaseCalculoIcmsSTNFSaida to set
	 */
	public void setValorBaseCalculoIcmsSTNFSaida(
			BigDecimal valorBaseCalculoIcmsSTNFSaida) {
		ValorBaseCalculoIcmsSTNFSaida = valorBaseCalculoIcmsSTNFSaida;
	}

	/**
	 * @return the valorIcmsSTNFSaida
	 */
	public BigDecimal getValorIcmsSTNFSaida() {
		return ValorIcmsSTNFSaida;
	}

	/**
	 * @param valorIcmsSTNFSaida the valorIcmsSTNFSaida to set
	 */
	public void setValorIcmsSTNFSaida(BigDecimal valorIcmsSTNFSaida) {
		ValorIcmsSTNFSaida = valorIcmsSTNFSaida;
	}

	/**
	 * @return the valorTotalProdutosNFSaida
	 */
	public BigDecimal getValorTotalProdutosNFSaida() {
		return ValorTotalProdutosNFSaida;
	}

	/**
	 * @param valorTotalProdutosNFSaida the valorTotalProdutosNFSaida to set
	 */
	public void setValorTotalProdutosNFSaida(BigDecimal valorTotalProdutosNFSaida) {
		ValorTotalProdutosNFSaida = valorTotalProdutosNFSaida;
	}

	/**
	 * @return the valorFreteNFSaida
	 */
	public BigDecimal getValorFreteNFSaida() {
		return ValorFreteNFSaida;
	}

	/**
	 * @param valorFreteNFSaida the valorFreteNFSaida to set
	 */
	public void setValorFreteNFSaida(BigDecimal valorFreteNFSaida) {
		ValorFreteNFSaida = valorFreteNFSaida;
	}

	/**
	 * @return the valorSeguroNFSaida
	 */
	public BigDecimal getValorSeguroNFSaida() {
		return ValorSeguroNFSaida;
	}

	/**
	 * @param valorSeguroNFSaida the valorSeguroNFSaida to set
	 */
	public void setValorSeguroNFSaida(BigDecimal valorSeguroNFSaida) {
		ValorSeguroNFSaida = valorSeguroNFSaida;
	}

	/**
	 * @return the valorTotalDescontoNFSaida
	 */
	public BigDecimal getValorTotalDescontoNFSaida() {
		return ValorTotalDescontoNFSaida;
	}

	/**
	 * @param valorTotalDescontoNFSaida the valorTotalDescontoNFSaida to set
	 */
	public void setValorTotalDescontoNFSaida(BigDecimal valorTotalDescontoNFSaida) {
		ValorTotalDescontoNFSaida = valorTotalDescontoNFSaida;
	}

	/**
	 * @return the valorTotalIpiNFSaida
	 */
	public BigDecimal getValorTotalIpiNFSaida() {
		return ValorTotalIpiNFSaida;
	}

	/**
	 * @param valorTotalIpiNFSaida the valorTotalIpiNFSaida to set
	 */
	public void setValorTotalIpiNFSaida(BigDecimal valorTotalIpiNFSaida) {
		ValorTotalIpiNFSaida = valorTotalIpiNFSaida;
	}

	/**
	 * @return the valorTotalPisNFSaida
	 */
	public BigDecimal getValorTotalPisNFSaida() {
		return ValorTotalPisNFSaida;
	}

	/**
	 * @param valorTotalPisNFSaida the valorTotalPisNFSaida to set
	 */
	public void setValorTotalPisNFSaida(BigDecimal valorTotalPisNFSaida) {
		ValorTotalPisNFSaida = valorTotalPisNFSaida;
	}

	/**
	 * @return the valorTotalCofinsNFSaida
	 */
	public BigDecimal getValorTotalCofinsNFSaida() {
		return ValorTotalCofinsNFSaida;
	}

	/**
	 * @param valorTotalCofinsNFSaida the valorTotalCofinsNFSaida to set
	 */
	public void setValorTotalCofinsNFSaida(BigDecimal valorTotalCofinsNFSaida) {
		ValorTotalCofinsNFSaida = valorTotalCofinsNFSaida;
	}

	/**
	 * @return the valorOutrasDespesasNFSaida
	 */
	public BigDecimal getValorOutrasDespesasNFSaida() {
		return ValorOutrasDespesasNFSaida;
	}

	/**
	 * @param valorOutrasDespesasNFSaida the valorOutrasDespesasNFSaida to set
	 */
	public void setValorOutrasDespesasNFSaida(BigDecimal valorOutrasDespesasNFSaida) {
		ValorOutrasDespesasNFSaida = valorOutrasDespesasNFSaida;
	}

	/**
	 * @return the valorTotalNFSaida
	 */
	public BigDecimal getValorTotalNFSaida() {
		return ValorTotalNFSaida;
	}

	/**
	 * @param valorTotalNFSaida the valorTotalNFSaida to set
	 */
	public void setValorTotalNFSaida(BigDecimal valorTotalNFSaida) {
		ValorTotalNFSaida = valorTotalNFSaida;
	}

	public void setQuantidadeVolumesTransporteNFSaida(
			Integer quantidadeVolumesTransporteNFSaida) {
		QuantidadeVolumesTransporteNFSaida = quantidadeVolumesTransporteNFSaida;
	}

	public Integer getQuantidadeVolumesTransporteNFSaida() {
		return QuantidadeVolumesTransporteNFSaida;
	}

	public void setEspecieVolumeTransporteNFSaida(
			String especieVolumeTransporteNFSaida) {
		EspecieVolumeTransporteNFSaida = especieVolumeTransporteNFSaida;
	}

	public String getEspecieVolumeTransporteNFSaida() {
		return EspecieVolumeTransporteNFSaida;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		NomeDestinatario = nomeDestinatario;
	}

	public String getNomeDestinatario() {
		return NomeDestinatario;
	}

	public void setStatusNFSaida(String statusNFSaida) {
		StatusNFSaida = statusNFSaida;
	}

	public String getStatusNFSaida() {
		return StatusNFSaida;
	}

	public void setCodigoNfe(Integer codigoNfe) {
		CodigoNfe = codigoNfe;
	}

	public Integer getCodigoNfe() {
		return CodigoNfe;
	}

	public void setObservacaoNFSaida(String observacaoNFSaida) {
		ObservacaoNFSaida = observacaoNFSaida;
	}

	public String getObservacaoNFSaida() {
		return ObservacaoNFSaida;
	}

	public void setOperacaoTributacao(String operacaoTributacao) {
		OperacaoTributacao = operacaoTributacao;
	}

	public String getOperacaoTributacao() {
		return OperacaoTributacao;
	}
}