package br.com.orlandoburli.core.vo.vendas;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Key;

public class VendedorVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key @AutoIncrement
	private Integer CodigoVendedor;
	private String NomeVendedor;
	private String EmailVendedor;
	private String TipoVendedor;
	private String CategoriaVendedor;
	private Double PercComissaoClienteVendedor;
	private Double PercComissaoClienteEmpresa;
	private String SituacaoVendedor;
	
	private Integer CodigoEmpresaFuncionario;
	private Integer CodigoLojaFuncionario;
	private Integer CodigoFuncionario;

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

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public String getNomeVendedor() {
		return NomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		NomeVendedor = nomeVendedor;
	}

	public String getEmailVendedor() {
		return EmailVendedor;
	}

	public void setEmailVendedor(String emailVendedor) {
		EmailVendedor = emailVendedor;
	}

	public String getTipoVendedor() {
		return TipoVendedor;
	}

	public void setTipoVendedor(String tipoVendedor) {
		TipoVendedor = tipoVendedor;
	}

	public String getCategoriaVendedor() {
		return CategoriaVendedor;
	}

	public void setCategoriaVendedor(String categoriaVendedor) {
		CategoriaVendedor = categoriaVendedor;
	}

	public Double getPercComissaoClienteVendedor() {
		return PercComissaoClienteVendedor;
	}

	public void setPercComissaoClienteVendedor(Double percComissaoClienteVendedor) {
		PercComissaoClienteVendedor = percComissaoClienteVendedor;
	}

	public Double getPercComissaoClienteEmpresa() {
		return PercComissaoClienteEmpresa;
	}

	public void setPercComissaoClienteEmpresa(Double percComissaoClienteEmpresa) {
		PercComissaoClienteEmpresa = percComissaoClienteEmpresa;
	}

	public String getSituacaoVendedor() {
		return SituacaoVendedor;
	}

	public void setSituacaoVendedor(String situacaoVendedor) {
		SituacaoVendedor = situacaoVendedor;
	}

	public void setCodigoEmpresaFuncionario(Integer codigoEmpresaFuncionario) {
		CodigoEmpresaFuncionario = codigoEmpresaFuncionario;
	}

	public Integer getCodigoEmpresaFuncionario() {
		return CodigoEmpresaFuncionario;
	}

	public void setCodigoLojaFuncionario(Integer codigoLojaFuncionario) {
		CodigoLojaFuncionario = codigoLojaFuncionario;
	}

	public Integer getCodigoLojaFuncionario() {
		return CodigoLojaFuncionario;
	}

	public void setCodigoFuncionario(Integer codigoFuncionario) {
		CodigoFuncionario = codigoFuncionario;
	}

	public Integer getCodigoFuncionario() {
		return CodigoFuncionario;
	}
}
