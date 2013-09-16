package br.com.orlandoburli.core.vo.financeiro;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;
import br.com.orlandoburli.core.vo.Precision;

public class ChequeRecebidoVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoChequeRecebido;
	
	private String NumeroChequeRecebido;
	@Precision(decimals=2)
	private BigDecimal ValorChequeRecebido;
	
	private Date DataEmissaoChequeRecebido;
	private Date BomParaChequeRecebido;
	private String CodigoBancoChequeRecebido;
	
	private String AgenciaChequeRecebido;
	private String DigitoAgenciaChequeRecebido;
	private String ContaChequeRecebido;
	private String DigitoContaChequeRecebido;
	
	private String StatusChequeRecebido;
	
	private Integer CodigoEmpresaRecebimento;
	private Integer CodigoLojaRecebimento;
	private Integer CodigoRecebimento;
	
	private Integer CodigoEmpresaEmitente;
	private Integer CodigoLojaEmitente;
	private Integer CodigoPessoaEmitente;
	private Integer CodigoEnderecoPessoaEmitente;
	
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
	 * @return the codigoChequeRecebido
	 */
	public Integer getCodigoChequeRecebido() {
		return CodigoChequeRecebido;
	}

	/**
	 * @param codigoChequeRecebido the codigoChequeRecebido to set
	 */
	public void setCodigoChequeRecebido(Integer codigoChequeRecebido) {
		CodigoChequeRecebido = codigoChequeRecebido;
	}

	/**
	 * @return the numeroChequeRecebido
	 */
	public String getNumeroChequeRecebido() {
		return NumeroChequeRecebido;
	}

	/**
	 * @param numeroChequeRecebido the numeroChequeRecebido to set
	 */
	public void setNumeroChequeRecebido(String numeroChequeRecebido) {
		NumeroChequeRecebido = numeroChequeRecebido;
	}

	/**
	 * @return the valorChequeRecebido
	 */
	public BigDecimal getValorChequeRecebido() {
		return ValorChequeRecebido;
	}

	/**
	 * @param valorChequeRecebido the valorChequeRecebido to set
	 */
	public void setValorChequeRecebido(BigDecimal valorChequeRecebido) {
		ValorChequeRecebido = valorChequeRecebido;
	}

	/**
	 * @return the dataEmissaoChequeRecebido
	 */
	public Date getDataEmissaoChequeRecebido() {
		return DataEmissaoChequeRecebido;
	}

	/**
	 * @param dataEmissaoChequeRecebido the dataEmissaoChequeRecebido to set
	 */
	public void setDataEmissaoChequeRecebido(Date dataEmissaoChequeRecebido) {
		DataEmissaoChequeRecebido = dataEmissaoChequeRecebido;
	}

	/**
	 * @return the bomParaChequeRecebido
	 */
	public Date getBomParaChequeRecebido() {
		return BomParaChequeRecebido;
	}

	/**
	 * @param bomParaChequeRecebido the bomParaChequeRecebido to set
	 */
	public void setBomParaChequeRecebido(Date bomParaChequeRecebido) {
		BomParaChequeRecebido = bomParaChequeRecebido;
	}

	/**
	 * @return the codigoBancoChequeRecebido
	 */
	public String getCodigoBancoChequeRecebido() {
		return CodigoBancoChequeRecebido;
	}

	/**
	 * @param codigoBancoChequeRecebido the codigoBancoChequeRecebido to set
	 */
	public void setCodigoBancoChequeRecebido(String codigoBancoChequeRecebido) {
		CodigoBancoChequeRecebido = codigoBancoChequeRecebido;
	}

	/**
	 * @return the codigoEmpresaRecebimento
	 */
	public Integer getCodigoEmpresaRecebimento() {
		return CodigoEmpresaRecebimento;
	}

	/**
	 * @param codigoEmpresaRecebimento the codigoEmpresaRecebimento to set
	 */
	public void setCodigoEmpresaRecebimento(Integer codigoEmpresaRecebimento) {
		CodigoEmpresaRecebimento = codigoEmpresaRecebimento;
	}

	/**
	 * @return the codigoLojaRecebimento
	 */
	public Integer getCodigoLojaRecebimento() {
		return CodigoLojaRecebimento;
	}

	/**
	 * @param codigoLojaRecebimento the codigoLojaRecebimento to set
	 */
	public void setCodigoLojaRecebimento(Integer codigoLojaRecebimento) {
		CodigoLojaRecebimento = codigoLojaRecebimento;
	}

	/**
	 * @return the codigoRecebimento
	 */
	public Integer getCodigoRecebimento() {
		return CodigoRecebimento;
	}

	/**
	 * @param codigoRecebimento the codigoRecebimento to set
	 */
	public void setCodigoRecebimento(Integer codigoRecebimento) {
		CodigoRecebimento = codigoRecebimento;
	}

	/**
	 * @return the codigoEmpresaEmitente
	 */
	public Integer getCodigoEmpresaEmitente() {
		return CodigoEmpresaEmitente;
	}

	/**
	 * @param codigoEmpresaEmitente the codigoEmpresaEmitente to set
	 */
	public void setCodigoEmpresaEmitente(Integer codigoEmpresaEmitente) {
		CodigoEmpresaEmitente = codigoEmpresaEmitente;
	}

	/**
	 * @return the codigoLojaEmitente
	 */
	public Integer getCodigoLojaEmitente() {
		return CodigoLojaEmitente;
	}

	/**
	 * @param codigoLojaEmitente the codigoLojaEmitente to set
	 */
	public void setCodigoLojaEmitente(Integer codigoLojaEmitente) {
		CodigoLojaEmitente = codigoLojaEmitente;
	}

	/**
	 * @return the codigoPessoaEmitente
	 */
	public Integer getCodigoPessoaEmitente() {
		return CodigoPessoaEmitente;
	}

	/**
	 * @param codigoPessoaEmitente the codigoPessoaEmitente to set
	 */
	public void setCodigoPessoaEmitente(Integer codigoPessoaEmitente) {
		CodigoPessoaEmitente = codigoPessoaEmitente;
	}

	/**
	 * @return the codigoEnderecoPessoaEmitente
	 */
	public Integer getCodigoEnderecoPessoaEmitente() {
		return CodigoEnderecoPessoaEmitente;
	}

	/**
	 * @param codigoEnderecoPessoaEmitente the codigoEnderecoPessoaEmitente to set
	 */
	public void setCodigoEnderecoPessoaEmitente(Integer codigoEnderecoPessoaEmitente) {
		CodigoEnderecoPessoaEmitente = codigoEnderecoPessoaEmitente;
	}

	public void setAgenciaChequeRecebido(String agenciaChequeRecebido) {
		AgenciaChequeRecebido = agenciaChequeRecebido;
	}

	public String getAgenciaChequeRecebido() {
		return AgenciaChequeRecebido;
	}

	public void setDigitoAgenciaChequeRecebido(
			String digitoAgenciaChequeRecebido) {
		DigitoAgenciaChequeRecebido = digitoAgenciaChequeRecebido;
	}

	public String getDigitoAgenciaChequeRecebido() {
		return DigitoAgenciaChequeRecebido;
	}

	public void setContaChequeRecebido(String contaChequeRecebido) {
		ContaChequeRecebido = contaChequeRecebido;
	}

	public String getContaChequeRecebido() {
		return ContaChequeRecebido;
	}

	public void setDigitoContaChequeRecebido(String digitoContaChequeRecebido) {
		DigitoContaChequeRecebido = digitoContaChequeRecebido;
	}

	public String getDigitoContaChequeRecebido() {
		return DigitoContaChequeRecebido;
	}

	public void setStatusChequeRecebido(String statusChequeRecebido) {
		StatusChequeRecebido = statusChequeRecebido;
	}

	public String getStatusChequeRecebido() {
		return StatusChequeRecebido;
	}
	
	@Override
	public String toString() {
		return Utils.voToXml(this);
	}
}