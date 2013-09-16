package br.com.orlandoburli.core.vo.vendas.comissoes;

import java.math.BigDecimal;
import java.sql.Date;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class ComissaoVendaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoMetaVenda;
	@Key
	private Integer CodigoItemMetaVenda;
	@Key
	private Integer CodigoEmpresaVendedor;
	@Key
	private Integer CodigoLojaVendedor;
	@Key
	private Integer CodigoVendedor;
	
	private BigDecimal ValorComissaoVenda;
	private BigDecimal ValorAtingidoMetaVenda;
	
	@Join(entityName="MetaVenda", 
			foreignColumnName="DataInicialMetaVenda", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoMetaVenda"}, 
			localKeys={"CodigoEmpresa", "CodigoLoja", "CodigoMetaVenda"})
	private Date DataInicialReferencia;
	
	@Join(entityName="MetaVenda", 
			foreignColumnName="DataFinalMetaVenda", 
			foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoMetaVenda"}, 
			localKeys={"CodigoEmpresa", "CodigoLoja", "CodigoMetaVenda"})
	private Date DataFinalReferencia;
	
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

	public Integer getCodigoMetaVenda() {
		return CodigoMetaVenda;
	}

	public void setCodigoMetaVenda(Integer codigoMetaVenda) {
		CodigoMetaVenda = codigoMetaVenda;
	}

	public Integer getCodigoItemMetaVenda() {
		return CodigoItemMetaVenda;
	}

	public void setCodigoItemMetaVenda(Integer codigoItemMetaVenda) {
		CodigoItemMetaVenda = codigoItemMetaVenda;
	}

	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public BigDecimal getValorComissaoVenda() {
		return ValorComissaoVenda;
	}

	public void setValorComissaoVenda(BigDecimal valorComissaoVenda) {
		ValorComissaoVenda = valorComissaoVenda;
	}

	public BigDecimal getValorAtingidoMetaVenda() {
		return ValorAtingidoMetaVenda;
	}

	public void setValorAtingidoMetaVenda(BigDecimal valorAtingidoMetaVenda) {
		ValorAtingidoMetaVenda = valorAtingidoMetaVenda;
	}

	public void setDataInicialReferencia(Date dataInicialReferencia) {
		DataInicialReferencia = dataInicialReferencia;
	}

	public Date getDataInicialReferencia() {
		return DataInicialReferencia;
	}

	public void setDataFinalReferencia(Date dataFinalReferencia) {
		DataFinalReferencia = dataFinalReferencia;
	}

	public Date getDataFinalReferencia() {
		return DataFinalReferencia;
	}
}