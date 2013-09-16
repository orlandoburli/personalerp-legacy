package br.com.orlandoburli.core.vo.vendas.pedido;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;

public class PedidoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoPdv;
	@Key @AutoIncrement
	private Integer CodigoPedido;
	
	private String StatusPedido;
	private Date DataPedido;
	private Integer CodigoPlanoPagamento;
	
	private Integer CodigoEmpresaEnderecoPessoa;
	private Integer CodigoLojaEnderecoPessoa;
	private Integer CodigoPessoa;
	private Integer CodigoEnderecoPessoa;
	
	private String ObservacoesPedido;
	
	private Integer QuantidadeAtendimentos;
	
	@Precision(decimals=2)
	private BigDecimal ValorTotalItensPedido;
	@Precision(decimals=2)
	private BigDecimal ValorDescontoPedido;
	@Precision(decimals=2)
	private BigDecimal ValorFretePedido;
	@Precision(decimals=2)
	private BigDecimal ValorTotalPedido;
	
	private Integer CodigoEmpresaFrete;
	private Integer CodigoLojaFrete;
	private Integer CodigoPessoaFrete;
	private Integer CodigoEnderecoPessoaFrete;
	
	private Integer CodigoEmpresaEntrega;
	private Integer CodigoLojaEntrega;
	private Integer CodigoPessoaEntrega;
	private Integer CodigoEnderecoPessoaEntrega;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private Integer CodigoEmpresaNFSaida;
	private Integer CodigoLojaNFSaida;
	private Integer CodigoNFSaida;
	private String SerieNFSaida;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="COALESCE((SELECT b.NomeUsuario FROM [schema].Usuario b" +
    		"			    WHERE b.CodigoEmpresa = a.CodigoEmpresaUsuarioLog" +
    		"                 AND b.CodigoLoja    = a.CodigoLojaUsuarioLog" +
    		"                 AND b.CodigoUsuario = a.CodigoUsuarioLog), '')")
    private String NomeUsuarioLog;
    
    @Formula(getFormula="COALESCE((SELECT b.NomeRazaoSocialPessoa FROM [schema].Pessoa b" +
    		"              WHERE b.CodigoEmpresa = a.CodigoEmpresaEnderecoPessoa" +
    		"                AND b.CodigoLoja    = a.CodigoLojaEnderecoPessoa" +
    		"                AND b.CodigoPessoa  = a.CodigoPessoa" +
    		"), '')")
    private String NomeCliente;
    
    @Formula(getFormula="COALESCE((SELECT b.NomeVendedor FROM [schema].Vendedor b" +
    		"              WHERE b.CodigoEmpresa  = a.CodigoEmpresaVendedor" +
    		"                AND b.CodigoLoja     = a.CodigoLojaVendedor" +
    		"                AND b.CodigoVendedor = a.CodigoVendedor" +
    		"), '')")
    private String NomeVendedor;
    
    @Ignore
    private PessoaVO PessoaCliente;
    @Ignore
    private EnderecoPessoaVO EnderecoCliente;
    @Ignore
    private EnderecoPessoaVO EnderecoClienteEntrega;
    @Ignore
    private PessoaVO PessoaTransportadora;
    @Ignore
    private EnderecoPessoaVO EnderecoTransportadora;
    @Ignore
    private List<ItemPedidoVO> Itens;

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
	 * @return the codigoPedido
	 */
	public Integer getCodigoPedido() {
		return CodigoPedido;
	}

	/**
	 * @param codigoPedido the codigoPedido to set
	 */
	public void setCodigoPedido(Integer codigoPedido) {
		CodigoPedido = codigoPedido;
	}

	/**
	 * @return the statusPedido
	 */
	public String getStatusPedido() {
		return StatusPedido;
	}

	/**
	 * @param statusPedido the statusPedido to set
	 */
	public void setStatusPedido(String statusPedido) {
		StatusPedido = statusPedido;
	}

	/**
	 * @return the dataPedido
	 */
	public Date getDataPedido() {
		return DataPedido;
	}

	/**
	 * @param dataPedido the dataPedido to set
	 */
	public void setDataPedido(Date dataPedido) {
		DataPedido = dataPedido;
	}

	/**
	 * @return the codigoEmpresaEnderecoPessoa
	 */
	public Integer getCodigoEmpresaEnderecoPessoa() {
		return CodigoEmpresaEnderecoPessoa;
	}

	/**
	 * @param codigoEmpresaEnderecoPessoa the codigoEmpresaEnderecoPessoa to set
	 */
	public void setCodigoEmpresaEnderecoPessoa(Integer codigoEmpresaEnderecoPessoa) {
		CodigoEmpresaEnderecoPessoa = codigoEmpresaEnderecoPessoa;
	}

	/**
	 * @return the codigoLojaEnderecoPessoa
	 */
	public Integer getCodigoLojaEnderecoPessoa() {
		return CodigoLojaEnderecoPessoa;
	}

	/**
	 * @param codigoLojaEnderecoPessoa the codigoLojaEnderecoPessoa to set
	 */
	public void setCodigoLojaEnderecoPessoa(Integer codigoLojaEnderecoPessoa) {
		CodigoLojaEnderecoPessoa = codigoLojaEnderecoPessoa;
	}

	/**
	 * @return the codigoPessoa
	 */
	public Integer getCodigoPessoa() {
		return CodigoPessoa;
	}

	/**
	 * @param codigoPessoa the codigoPessoa to set
	 */
	public void setCodigoPessoa(Integer codigoPessoa) {
		CodigoPessoa = codigoPessoa;
	}

	/**
	 * @return the codigoEnderecoPessoa
	 */
	public Integer getCodigoEnderecoPessoa() {
		return CodigoEnderecoPessoa;
	}

	/**
	 * @param codigoEnderecoPessoa the codigoEnderecoPessoa to set
	 */
	public void setCodigoEnderecoPessoa(Integer codigoEnderecoPessoa) {
		CodigoEnderecoPessoa = codigoEnderecoPessoa;
	}

	/**
	 * @return the observacoesPedido
	 */
	public String getObservacoesPedido() {
		return ObservacoesPedido;
	}

	/**
	 * @param observacoesPedido the observacoesPedido to set
	 */
	public void setObservacoesPedido(String observacoesPedido) {
		ObservacoesPedido = observacoesPedido;
	}

	/**
	 * @return the valorTotalItensPedido
	 */
	public BigDecimal getValorTotalItensPedido() {
		return ValorTotalItensPedido;
	}

	/**
	 * @param valorTotalItensPedido the valorTotalItensPedido to set
	 */
	public void setValorTotalItensPedido(BigDecimal valorTotalItensPedido) {
		ValorTotalItensPedido = valorTotalItensPedido;
	}

	/**
	 * @return the valorDescontoPedido
	 */
	public BigDecimal getValorDescontoPedido() {
		return ValorDescontoPedido;
	}

	/**
	 * @param valorDescontoPedido the valorDescontoPedido to set
	 */
	public void setValorDescontoPedido(BigDecimal valorDescontoPedido) {
		ValorDescontoPedido = valorDescontoPedido;
	}

	/**
	 * @return the valorFretePedido
	 */
	public BigDecimal getValorFretePedido() {
		return ValorFretePedido;
	}

	/**
	 * @param valorFretePedido the valorFretePedido to set
	 */
	public void setValorFretePedido(BigDecimal valorFretePedido) {
		ValorFretePedido = valorFretePedido;
	}

	/**
	 * @return the valorTotalPedido
	 */
	public BigDecimal getValorTotalPedido() {
		return ValorTotalPedido;
	}

	/**
	 * @param valorTotalPedido the valorTotalPedido to set
	 */
	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		ValorTotalPedido = valorTotalPedido;
	}

	/**
	 * @return the codigoEmpresaFrete
	 */
	public Integer getCodigoEmpresaFrete() {
		return CodigoEmpresaFrete;
	}

	/**
	 * @param codigoEmpresaFrete the codigoEmpresaFrete to set
	 */
	public void setCodigoEmpresaFrete(Integer codigoEmpresaFrete) {
		CodigoEmpresaFrete = codigoEmpresaFrete;
	}

	/**
	 * @return the codigoLojaFrete
	 */
	public Integer getCodigoLojaFrete() {
		return CodigoLojaFrete;
	}

	/**
	 * @param codigoLojaFrete the codigoLojaFrete to set
	 */
	public void setCodigoLojaFrete(Integer codigoLojaFrete) {
		CodigoLojaFrete = codigoLojaFrete;
	}

	/**
	 * @return the codigoPessoaFrete
	 */
	public Integer getCodigoPessoaFrete() {
		return CodigoPessoaFrete;
	}

	/**
	 * @param codigoPessoaFrete the codigoPessoaFrete to set
	 */
	public void setCodigoPessoaFrete(Integer codigoPessoaFrete) {
		CodigoPessoaFrete = codigoPessoaFrete;
	}

	/**
	 * @return the codigoEnderecoPessoaFrete
	 */
	public Integer getCodigoEnderecoPessoaFrete() {
		return CodigoEnderecoPessoaFrete;
	}

	/**
	 * @param codigoEnderecoPessoaFrete the codigoEnderecoPessoaFrete to set
	 */
	public void setCodigoEnderecoPessoaFrete(Integer codigoEnderecoPessoaFrete) {
		CodigoEnderecoPessoaFrete = codigoEnderecoPessoaFrete;
	}

	/**
	 * @return the codigoEmpresaEntrega
	 */
	public Integer getCodigoEmpresaEntrega() {
		return CodigoEmpresaEntrega;
	}

	/**
	 * @param codigoEmpresaEntrega the codigoEmpresaEntrega to set
	 */
	public void setCodigoEmpresaEntrega(Integer codigoEmpresaEntrega) {
		CodigoEmpresaEntrega = codigoEmpresaEntrega;
	}

	/**
	 * @return the codigoLojaEntrega
	 */
	public Integer getCodigoLojaEntrega() {
		return CodigoLojaEntrega;
	}

	/**
	 * @param codigoLojaEntrega the codigoLojaEntrega to set
	 */
	public void setCodigoLojaEntrega(Integer codigoLojaEntrega) {
		CodigoLojaEntrega = codigoLojaEntrega;
	}

	/**
	 * @return the codigoPessoaEntrega
	 */
	public Integer getCodigoPessoaEntrega() {
		return CodigoPessoaEntrega;
	}

	/**
	 * @param codigoPessoaEntrega the codigoPessoaEntrega to set
	 */
	public void setCodigoPessoaEntrega(Integer codigoPessoaEntrega) {
		CodigoPessoaEntrega = codigoPessoaEntrega;
	}

	/**
	 * @return the codigoEnderecoPessoaEntrega
	 */
	public Integer getCodigoEnderecoPessoaEntrega() {
		return CodigoEnderecoPessoaEntrega;
	}

	/**
	 * @param codigoEnderecoPessoaEntrega the codigoEnderecoPessoaEntrega to set
	 */
	public void setCodigoEnderecoPessoaEntrega(Integer codigoEnderecoPessoaEntrega) {
		CodigoEnderecoPessoaEntrega = codigoEnderecoPessoaEntrega;
	}

	/**
	 * @return the codigoEmpresaVendedor
	 */
	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	/**
	 * @param codigoEmpresaVendedor the codigoEmpresaVendedor to set
	 */
	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	/**
	 * @return the codigoLojaVendedor
	 */
	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	/**
	 * @param codigoLojaVendedor the codigoLojaVendedor to set
	 */
	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	/**
	 * @return the codigoVendedor
	 */
	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	/**
	 * @param codigoVendedor the codigoVendedor to set
	 */
	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setPessoaCliente(PessoaVO pessoaCliente) {
		PessoaCliente = pessoaCliente;
	}

	public PessoaVO getPessoaCliente() {
		return PessoaCliente;
	}

	public void setEnderecoCliente(EnderecoPessoaVO enderecoCliente) {
		EnderecoCliente = enderecoCliente;
	}

	public EnderecoPessoaVO getEnderecoCliente() {
		return EnderecoCliente;
	}

	public void setEnderecoClienteEntrega(EnderecoPessoaVO enderecoClienteEntrega) {
		EnderecoClienteEntrega = enderecoClienteEntrega;
	}

	public EnderecoPessoaVO getEnderecoClienteEntrega() {
		return EnderecoClienteEntrega;
	}

	public void setPessoaTransportadora(PessoaVO pessoaTransportadora) {
		PessoaTransportadora = pessoaTransportadora;
	}

	public PessoaVO getPessoaTransportadora() {
		return PessoaTransportadora;
	}

	public void setEnderecoTransportadora(EnderecoPessoaVO enderecoTransportadora) {
		EnderecoTransportadora = enderecoTransportadora;
	}

	public EnderecoPessoaVO getEnderecoTransportadora() {
		return EnderecoTransportadora;
	}

	public void setItens(List<ItemPedidoVO> itens) {
		Itens = itens;
	}

	public List<ItemPedidoVO> getItens() {
		return Itens;
	}

	public void setNomeVendedor(String nomeVendedor) {
		NomeVendedor = nomeVendedor;
	}

	public String getNomeVendedor() {
		return NomeVendedor;
	}

	public void setCodigoEmpresaNFSaida(Integer codigoEmpresaNFSaida) {
		CodigoEmpresaNFSaida = codigoEmpresaNFSaida;
	}

	public Integer getCodigoEmpresaNFSaida() {
		return CodigoEmpresaNFSaida;
	}

	public void setCodigoLojaNFSaida(Integer codigoLojaNFSaida) {
		CodigoLojaNFSaida = codigoLojaNFSaida;
	}

	public Integer getCodigoLojaNFSaida() {
		return CodigoLojaNFSaida;
	}

	public void setCodigoNFSaida(Integer codigoNFSaida) {
		CodigoNFSaida = codigoNFSaida;
	}

	public Integer getCodigoNFSaida() {
		return CodigoNFSaida;
	}

	public void setSerieNFSaida(String serieNFSaida) {
		SerieNFSaida = serieNFSaida;
	}

	public String getSerieNFSaida() {
		return SerieNFSaida;
	}

	public void setCodigoPlanoPagamento(Integer codigoPlanoPagamento) {
		CodigoPlanoPagamento = codigoPlanoPagamento;
	}

	public Integer getCodigoPlanoPagamento() {
		return CodigoPlanoPagamento;
	}

	public String getNomeUsuarioLog() {
		return NomeUsuarioLog;
	}

	public void setNomeUsuarioLog(String nomeUsuarioLog) {
		NomeUsuarioLog = nomeUsuarioLog;
	}

	public Integer getQuantidadeAtendimentos() {
		return QuantidadeAtendimentos;
	}

	public void setQuantidadeAtendimentos(Integer quantidadeAtendimentos) {
		QuantidadeAtendimentos = quantidadeAtendimentos;
	}

	public Integer getCodigoPdv() {
		return CodigoPdv;
	}

	public void setCodigoPdv(Integer codigoPdv) {
		CodigoPdv = codigoPdv;
	}
}