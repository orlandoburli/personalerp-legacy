package br.com.orlandoburli.core.vo.estoque.frigorifico.romaneiogado;

import java.sql.Date;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class RomaneioGadoVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoRomaneio;
	
	private Date DataRomaneio;
	private Timestamp DataHoraLancamentoRomaneio;
	
	private Integer CodigoEmpresaEnderecoFornecedor;
	private Integer CodigoLojaEnderecoFornecedor;
	private Integer CodigoPessoaFornecedor;
	private Integer CodigoPessoaEnderecoFornecedor;
	
	private String StatusRomaneio;
	
	private String ObservacaoRomaneio;
	
	private Integer CodigoEmpresaUsuarioLog;
    private Integer CodigoLojaUsuarioLog;
    private Integer CodigoUsuarioLog;
    
    @Formula(getFormula="(SELECT b.NomeUsuario FROM [schema].Usuario b WHERE a.CodigoEmpresaUsuarioLog = b.CodigoEmpresa AND a.CodigoLojaUsuarioLog = b.CodigoLoja AND a.CodigoUsuarioLog = b.CodigoUsuario)")
    private String NomeUsuarioRomaneio;

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
	 * @return the codigoRomaneio
	 */
	public Integer getCodigoRomaneio() {
		return CodigoRomaneio;
	}

	/**
	 * @param codigoRomaneio the codigoRomaneio to set
	 */
	public void setCodigoRomaneio(Integer codigoRomaneio) {
		CodigoRomaneio = codigoRomaneio;
	}

	/**
	 * @return the dataRomaneio
	 */
	public Date getDataRomaneio() {
		return DataRomaneio;
	}

	/**
	 * @param dataRomaneio the dataRomaneio to set
	 */
	public void setDataRomaneio(Date dataRomaneio) {
		DataRomaneio = dataRomaneio;
	}

	/**
	 * @return the dataHoraLancamentoRomaneio
	 */
	public Timestamp getDataHoraLancamentoRomaneio() {
		return DataHoraLancamentoRomaneio;
	}

	/**
	 * @param dataHoraLancamentoRomaneio the dataHoraLancamentoRomaneio to set
	 */
	public void setDataHoraLancamentoRomaneio(Timestamp dataHoraLancamentoRomaneio) {
		DataHoraLancamentoRomaneio = dataHoraLancamentoRomaneio;
	}

	/**
	 * @return the codigoEmpresaEnderecoFornecedor
	 */
	public Integer getCodigoEmpresaEnderecoFornecedor() {
		return CodigoEmpresaEnderecoFornecedor;
	}

	/**
	 * @param codigoEmpresaEnderecoFornecedor the codigoEmpresaEnderecoFornecedor to set
	 */
	public void setCodigoEmpresaEnderecoFornecedor(
			Integer codigoEmpresaEnderecoFornecedor) {
		CodigoEmpresaEnderecoFornecedor = codigoEmpresaEnderecoFornecedor;
	}

	/**
	 * @return the codigoLojaEnderecoFornecedor
	 */
	public Integer getCodigoLojaEnderecoFornecedor() {
		return CodigoLojaEnderecoFornecedor;
	}

	/**
	 * @param codigoLojaEnderecoFornecedor the codigoLojaEnderecoFornecedor to set
	 */
	public void setCodigoLojaEnderecoFornecedor(Integer codigoLojaEnderecoFornecedor) {
		CodigoLojaEnderecoFornecedor = codigoLojaEnderecoFornecedor;
	}

	/**
	 * @return the codigoPessoaFornecedor
	 */
	public Integer getCodigoPessoaFornecedor() {
		return CodigoPessoaFornecedor;
	}

	/**
	 * @param codigoPessoaFornecedor the codigoPessoaFornecedor to set
	 */
	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaFornecedor = codigoPessoaFornecedor;
	}

	/**
	 * @return the codigoPessoaEnderecoFornecedor
	 */
	public Integer getCodigoPessoaEnderecoFornecedor() {
		return CodigoPessoaEnderecoFornecedor;
	}

	/**
	 * @param codigoPessoaEnderecoFornecedor the codigoPessoaEnderecoFornecedor to set
	 */
	public void setCodigoPessoaEnderecoFornecedor(
			Integer codigoPessoaEnderecoFornecedor) {
		CodigoPessoaEnderecoFornecedor = codigoPessoaEnderecoFornecedor;
	}

	/**
	 * @return the statusRomaneio
	 */
	public String getStatusRomaneio() {
		return StatusRomaneio;
	}

	/**
	 * @param statusRomaneio the statusRomaneio to set
	 */
	public void setStatusRomaneio(String statusRomaneio) {
		StatusRomaneio = statusRomaneio;
	}

	public void setObservacaoRomaneio(String observacaoRomaneio) {
		ObservacaoRomaneio = observacaoRomaneio;
	}

	public String getObservacaoRomaneio() {
		return ObservacaoRomaneio;
	}

	public void setNomeUsuarioRomaneio(String nomeUsuarioRomaneio) {
		NomeUsuarioRomaneio = nomeUsuarioRomaneio;
	}

	public String getNomeUsuarioRomaneio() {
		return NomeUsuarioRomaneio;
	}
}