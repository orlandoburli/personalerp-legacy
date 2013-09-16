package br.com.orlandoburli.core.vo.vendas.metavendas;

import java.sql.Date;

import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

public class MetaVendaVO implements IValueObject {

	private static final long serialVersionUID = 1L;
	private boolean isNew;
	
	@Key
	private Integer CodigoEmpresa;
	@Key
	private Integer CodigoLoja;
	@Key
	@AutoIncrement
	private Integer CodigoMetaVenda;
	
	private Date DataInicialMetaVenda;
	private Date DataFinalMetaVenda;
	
	private String TipoMetaVenda;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	@Join(entityName="Vendedor", 
		  foreignColumnName="NomeVendedor", 
		  foreignKeys={"CodigoEmpresa", "CodigoLoja", "CodigoVendedor"},
		  localKeys={"CodigoEmpresaVendedor", "CodigoLojaVendedor", "CodigoVendedor"})
	private String NomeVendedor;
	
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

	public Date getDataInicialMetaVenda() {
		return DataInicialMetaVenda;
	}

	public void setDataInicialMetaVenda(Date dataInicialMetaVenda) {
		DataInicialMetaVenda = dataInicialMetaVenda;
	}

	public Date getDataFinalMetaVenda() {
		return DataFinalMetaVenda;
	}

	public void setDataFinalMetaVenda(Date dataFinalMetaVenda) {
		DataFinalMetaVenda = dataFinalMetaVenda;
	}

	public String getTipoMetaVenda() {
		return TipoMetaVenda;
	}

	public void setTipoMetaVenda(String tipoMetaVenda) {
		TipoMetaVenda = tipoMetaVenda;
	}

	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		NomeVendedor = nomeVendedor;
	}

	public String getNomeVendedor() {
		return NomeVendedor;
	}
}