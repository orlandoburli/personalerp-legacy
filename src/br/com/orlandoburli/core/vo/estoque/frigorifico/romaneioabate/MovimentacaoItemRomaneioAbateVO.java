package br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class MovimentacaoItemRomaneioAbateVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	private Integer CodigoRomaneio;
	@Key
	private Integer CodigoItemRomaneio;
	
	@Key
	private Integer CodigoEmpresaEstoque;
	@Key
	private Integer CodigoLojaEstoque;
	@Key
	private Integer CodigoMovimentacaoEstoque;
	
	@Key
	private Integer CodigoEmpresaSubProduto;
	@Key
	private Integer CodigoLojaSubProduto;
	@Key 
	private Integer CodigoSubProduto;
	
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
	 * @return the codigoItemRomaneio
	 */
	public Integer getCodigoItemRomaneio() {
		return CodigoItemRomaneio;
	}

	/**
	 * @param codigoItemRomaneio the codigoItemRomaneio to set
	 */
	public void setCodigoItemRomaneio(Integer codigoItemRomaneio) {
		CodigoItemRomaneio = codigoItemRomaneio;
	}

	/**
	 * @return the codigoEmpresaEstoque
	 */
	public Integer getCodigoEmpresaEstoque() {
		return CodigoEmpresaEstoque;
	}

	/**
	 * @param codigoEmpresaEstoque the codigoEmpresaEstoque to set
	 */
	public void setCodigoEmpresaEstoque(Integer codigoEmpresaEstoque) {
		CodigoEmpresaEstoque = codigoEmpresaEstoque;
	}

	/**
	 * @return the codigoLojaEstoque
	 */
	public Integer getCodigoLojaEstoque() {
		return CodigoLojaEstoque;
	}

	/**
	 * @param codigoLojaEstoque the codigoLojaEstoque to set
	 */
	public void setCodigoLojaEstoque(Integer codigoLojaEstoque) {
		CodigoLojaEstoque = codigoLojaEstoque;
	}

	/**
	 * @return the codigoMovimentacaoEstoque
	 */
	public Integer getCodigoMovimentacaoEstoque() {
		return CodigoMovimentacaoEstoque;
	}

	/**
	 * @param codigoMovimentacaoEstoque the codigoMovimentacaoEstoque to set
	 */
	public void setCodigoMovimentacaoEstoque(Integer codigoMovimentacaoEstoque) {
		CodigoMovimentacaoEstoque = codigoMovimentacaoEstoque;
	}

	/**
	 * @return the codigoEmpresaSubProduto
	 */
	public Integer getCodigoEmpresaSubProduto() {
		return CodigoEmpresaSubProduto;
	}

	/**
	 * @param codigoEmpresaSubProduto the codigoEmpresaSubProduto to set
	 */
	public void setCodigoEmpresaSubProduto(Integer codigoEmpresaSubProduto) {
		CodigoEmpresaSubProduto = codigoEmpresaSubProduto;
	}

	/**
	 * @return the codigoLojaSubProduto
	 */
	public Integer getCodigoLojaSubProduto() {
		return CodigoLojaSubProduto;
	}

	/**
	 * @param codigoLojaSubProduto the codigoLojaSubProduto to set
	 */
	public void setCodigoLojaSubProduto(Integer codigoLojaSubProduto) {
		CodigoLojaSubProduto = codigoLojaSubProduto;
	}

	/**
	 * @return the codigoSubProduto
	 */
	public Integer getCodigoSubProduto() {
		return CodigoSubProduto;
	}

	/**
	 * @param codigoSubProduto the codigoSubProduto to set
	 */
	public void setCodigoSubProduto(Integer codigoSubProduto) {
		CodigoSubProduto = codigoSubProduto;
	}
}