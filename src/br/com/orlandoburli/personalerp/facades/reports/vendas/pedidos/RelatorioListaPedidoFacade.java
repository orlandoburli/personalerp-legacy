package br.com.orlandoburli.personalerp.facades.reports.vendas.pedidos;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RelatorioListaPedidoFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	private Date DataInicial;
	private Date DataFinal;
	
	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;
	
	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioListagensPedido2.jasper";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		super.doParameter(parameters);
		
		parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
		parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		
		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());
		
		parameters.put("CodigoEmpresaVendedor", getCodigoEmpresaVendedor());
		parameters.put("CodigoLojaVendedor", getCodigoLojaVendedor());
		parameters.put("CodigoVendedor", getCodigoVendedor());
		
		parameters.put("CodigoEmpresaProduto", getCodigoEmpresaProduto());
		parameters.put("CodigoLojaProduto", getCodigoLojaProduto());
		parameters.put("CodigoProduto", getCodigoProduto());
	}
	
	@IgnoreMethodAuthentication
	public void dados() {
		try {
			List<IValueObject> list = new ArrayList<IValueObject>();
			
			list.addAll(new ProdutoDAO().getList(null, "DescricaoProduto"));
			list.addAll(new VendedorDAO().getList());
			
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
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

	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}
}