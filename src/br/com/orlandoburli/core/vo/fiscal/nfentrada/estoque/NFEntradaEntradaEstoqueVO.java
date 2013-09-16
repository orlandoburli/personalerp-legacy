package br.com.orlandoburli.core.vo.fiscal.nfentrada.estoque;

import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class NFEntradaEntradaEstoqueVO implements IValueObject {

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
	@Key
	private Integer CodigoEmpresaEntradaEstoque;
	@Key
	private Integer CodigoLojaEntradaEstoque;
	@Key
	private Integer CodigoEntradaEstoque;
	
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

	public Integer getCodigoNFEntrada() {
		return CodigoNFEntrada;
	}

	public void setCodigoNFEntrada(Integer codigoNFEntrada) {
		CodigoNFEntrada = codigoNFEntrada;
	}

	public String getSerieNFEntrada() {
		return SerieNFEntrada;
	}

	public void setSerieNFEntrada(String serieNFEntrada) {
		SerieNFEntrada = serieNFEntrada;
	}

	public Integer getCodigoEmpresaEmitenteNFEntrada() {
		return CodigoEmpresaEmitenteNFEntrada;
	}

	public void setCodigoEmpresaEmitenteNFEntrada(
			Integer codigoEmpresaEmitenteNFEntrada) {
		CodigoEmpresaEmitenteNFEntrada = codigoEmpresaEmitenteNFEntrada;
	}

	public Integer getCodigoLojaEmitenteNFEntrada() {
		return CodigoLojaEmitenteNFEntrada;
	}

	public void setCodigoLojaEmitenteNFEntrada(Integer codigoLojaEmitenteNFEntrada) {
		CodigoLojaEmitenteNFEntrada = codigoLojaEmitenteNFEntrada;
	}

	public Integer getCodigoPessoaEmitenteNFEntrada() {
		return CodigoPessoaEmitenteNFEntrada;
	}

	public void setCodigoPessoaEmitenteNFEntrada(
			Integer codigoPessoaEmitenteNFEntrada) {
		CodigoPessoaEmitenteNFEntrada = codigoPessoaEmitenteNFEntrada;
	}

	public Integer getCodigoEnderecoPessoaEmitenteNFEntrada() {
		return CodigoEnderecoPessoaEmitenteNFEntrada;
	}

	public void setCodigoEnderecoPessoaEmitenteNFEntrada(
			Integer codigoEnderecoPessoaEmitenteNFEntrada) {
		CodigoEnderecoPessoaEmitenteNFEntrada = codigoEnderecoPessoaEmitenteNFEntrada;
	}

	public Integer getCodigoEmpresaEntradaEstoque() {
		return CodigoEmpresaEntradaEstoque;
	}

	public void setCodigoEmpresaEntradaEstoque(Integer codigoEmpresaEntradaEstoque) {
		CodigoEmpresaEntradaEstoque = codigoEmpresaEntradaEstoque;
	}

	public Integer getCodigoLojaEntradaEstoque() {
		return CodigoLojaEntradaEstoque;
	}

	public void setCodigoLojaEntradaEstoque(Integer codigoLojaEntradaEstoque) {
		CodigoLojaEntradaEstoque = codigoLojaEntradaEstoque;
	}

	public Integer getCodigoEntradaEstoque() {
		return CodigoEntradaEstoque;
	}

	public void setCodigoEntradaEstoque(Integer codigoEntradaEstoque) {
		CodigoEntradaEstoque = codigoEntradaEstoque;
	}
}
