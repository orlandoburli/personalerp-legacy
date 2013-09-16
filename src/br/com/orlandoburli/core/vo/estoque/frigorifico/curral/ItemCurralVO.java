package br.com.orlandoburli.core.vo.estoque.frigorifico.curral;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class ItemCurralVO implements IValueObject {


	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key 
	private Integer CodigoEmpresa;
	@Key 
	private Integer CodigoLoja;
	@Key 
	private Integer CodigoCurral;
	@Key @AutoIncrement
	private Integer CodigoItemCurral;
	
	private Integer CodigoEmpresaPessoa;
	private Integer CodigoLojaPessoa;
	private Integer CodigoPessoa;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	private Integer QuantidadeItemCurral;
	
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
	 * @return the codigoCurral
	 */
	public Integer getCodigoCurral() {
		return CodigoCurral;
	}

	/**
	 * @param codigoCurral the codigoCurral to set
	 */
	public void setCodigoCurral(Integer codigoCurral) {
		CodigoCurral = codigoCurral;
	}

	/**
	 * @return the codigoItemCurral
	 */
	public Integer getCodigoItemCurral() {
		return CodigoItemCurral;
	}

	/**
	 * @param codigoItemCurral the codigoItemCurral to set
	 */
	public void setCodigoItemCurral(Integer codigoItemCurral) {
		CodigoItemCurral = codigoItemCurral;
	}

	/**
	 * @return the codigoEmpresaPessoa
	 */
	public Integer getCodigoEmpresaPessoa() {
		return CodigoEmpresaPessoa;
	}

	/**
	 * @param codigoEmpresaPessoa the codigoEmpresaPessoa to set
	 */
	public void setCodigoEmpresaPessoa(Integer codigoEmpresaPessoa) {
		CodigoEmpresaPessoa = codigoEmpresaPessoa;
	}

	/**
	 * @return the codigoLojaPessoa
	 */
	public Integer getCodigoLojaPessoa() {
		return CodigoLojaPessoa;
	}

	/**
	 * @param codigoLojaPessoa the codigoLojaPessoa to set
	 */
	public void setCodigoLojaPessoa(Integer codigoLojaPessoa) {
		CodigoLojaPessoa = codigoLojaPessoa;
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
	 * @return the codigoEmpresaProduto
	 */
	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	/**
	 * @param codigoEmpresaProduto the codigoEmpresaProduto to set
	 */
	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	/**
	 * @return the codigoLojaProduto
	 */
	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	/**
	 * @param codigoLojaProduto the codigoLojaProduto to set
	 */
	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	/**
	 * @return the codigoProduto
	 */
	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	/**
	 * @param codigoProduto the codigoProduto to set
	 */
	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	/**
	 * @return the quantidadeItemCurral
	 */
	public Integer getQuantidadeItemCurral() {
		return QuantidadeItemCurral;
	}

	/**
	 * @param quantidadeItemCurral the quantidadeItemCurral to set
	 */
	public void setQuantidadeItemCurral(Integer quantidadeItemCurral) {
		QuantidadeItemCurral = quantidadeItemCurral;
	}
}