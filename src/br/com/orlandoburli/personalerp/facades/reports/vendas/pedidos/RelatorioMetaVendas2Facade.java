package br.com.orlandoburli.personalerp.facades.reports.vendas.pedidos;

import java.sql.Date;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioMetaVendas2Facade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private Date DataInicial;
	private Date DataFinal;

	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;

	private String TipoRelatorio;
	
	private String TodasLojas;
	
	@IgnoreMethodAuthentication
	public void vendedores() {
		try {
			write(Utils.voToXml(new VendedorDAO().getList()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected String getJasperFileName() {
		return null;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
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

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public String getTodasLojas() {
		return TodasLojas;
	}

	public void setTodasLojas(String todasLojas) {
		TodasLojas = todasLojas;
	}

}
