package br.com.orlandoburli.core.vo.estoque;

import java.math.BigDecimal;
import java.sql.Timestamp;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class MovimentacaoEstoqueVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	public static final String OPERACAO_ADICIONAR = "+";
	public static final String OPERACAO_DIMINUIR = "-";
	public static final String OPERACAO_INVENTARIO = "I";
	
	@Key
	@AutoIncrement
	private Integer CodigoMovimentacaoEstoque;
	@Key
	private Integer CodigoEmpresaEstoque;
	@Key
	private Integer CodigoLojaEstoque;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoProduto;
	
	private Timestamp DataHoraMovimentacaoEstoque;
	private String OperacaoMovimentacaoEstoque;
	private BigDecimal QuantidadeFisicoMovimentacaoEstoque;
	private BigDecimal QuantidadeFiscalMovimentacaoEstoque;
	private BigDecimal QuantidadeFisicoAnteriorMovimentacaoEstoque;
	private BigDecimal QuantidadeFiscalAnteriorMovimentacaoEstoque;
	private BigDecimal PrecoMedioMovimentacaoEstoque;
	private BigDecimal PrecoMedioAnteriorMovimentacaoEstoque;
	private String ObservacaoMovimentacaoEstoque;
	private String DocumentoMovimentacaoEstoque;
	
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

	public Integer getCodigoMovimentacaoEstoque() {
		return CodigoMovimentacaoEstoque;
	}

	public void setCodigoMovimentacaoEstoque(Integer codigoMovimentacaoEstoque) {
		CodigoMovimentacaoEstoque = codigoMovimentacaoEstoque;
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

	public Timestamp getDataHoraMovimentacaoEstoque() {
		return DataHoraMovimentacaoEstoque;
	}

	public void setDataHoraMovimentacaoEstoque(Timestamp dataHoraMovimentacaoEstoque) {
		DataHoraMovimentacaoEstoque = dataHoraMovimentacaoEstoque;
	}

	public String getOperacaoMovimentacaoEstoque() {
		return OperacaoMovimentacaoEstoque;
	}

	public void setOperacaoMovimentacaoEstoque(String operacaoMovimentacaoEstoque) {
		OperacaoMovimentacaoEstoque = operacaoMovimentacaoEstoque;
	}

	public BigDecimal getQuantidadeFisicoMovimentacaoEstoque() {
		return QuantidadeFisicoMovimentacaoEstoque;
	}

	public void setQuantidadeFisicoMovimentacaoEstoque(
			BigDecimal quantidadeFisicoMovimentacaoEstoque) {
		QuantidadeFisicoMovimentacaoEstoque = quantidadeFisicoMovimentacaoEstoque;
	}

	public BigDecimal getQuantidadeFiscalMovimentacaoEstoque() {
		return QuantidadeFiscalMovimentacaoEstoque;
	}

	public void setQuantidadeFiscalMovimentacaoEstoque(
			BigDecimal quantidadeFiscalMovimentacaoEstoque) {
		QuantidadeFiscalMovimentacaoEstoque = quantidadeFiscalMovimentacaoEstoque;
	}

	public BigDecimal getQuantidadeFisicoAnteriorMovimentacaoEstoque() {
		return QuantidadeFisicoAnteriorMovimentacaoEstoque;
	}

	public void setQuantidadeFisicoAnteriorMovimentacaoEstoque(
			BigDecimal quantidadeFisicoAnteriorMovimentacaoEstoque) {
		QuantidadeFisicoAnteriorMovimentacaoEstoque = quantidadeFisicoAnteriorMovimentacaoEstoque;
	}

	public BigDecimal getQuantidadeFiscalAnteriorMovimentacaoEstoque() {
		return QuantidadeFiscalAnteriorMovimentacaoEstoque;
	}

	public void setQuantidadeFiscalAnteriorMovimentacaoEstoque(
			BigDecimal quantidadeFiscalAnteriorMovimentacaoEstoque) {
		QuantidadeFiscalAnteriorMovimentacaoEstoque = quantidadeFiscalAnteriorMovimentacaoEstoque;
	}

	public BigDecimal getPrecoMedioMovimentacaoEstoque() {
		return PrecoMedioMovimentacaoEstoque;
	}

	public void setPrecoMedioMovimentacaoEstoque(
			BigDecimal precoMedioMovimentacaoEstoque) {
		PrecoMedioMovimentacaoEstoque = precoMedioMovimentacaoEstoque;
	}

	public BigDecimal getPrecoMedioAnteriorMovimentacaoEstoque() {
		return PrecoMedioAnteriorMovimentacaoEstoque;
	}

	public void setPrecoMedioAnteriorMovimentacaoEstoque(
			BigDecimal precoMedioAnteriorMovimentacaoEstoque) {
		PrecoMedioAnteriorMovimentacaoEstoque = precoMedioAnteriorMovimentacaoEstoque;
	}

	public String getObservacaoMovimentacaoEstoque() {
		return ObservacaoMovimentacaoEstoque;
	}

	public void setObservacaoMovimentacaoEstoque(
			String observacaoMovimentacaoEstoque) {
		ObservacaoMovimentacaoEstoque = observacaoMovimentacaoEstoque;
	}

	public String getDocumentoMovimentacaoEstoque() {
		return DocumentoMovimentacaoEstoque;
	}

	public void setDocumentoMovimentacaoEstoque(String documentoMovimentacaoEstoque) {
		DocumentoMovimentacaoEstoque = documentoMovimentacaoEstoque;
	}

}
