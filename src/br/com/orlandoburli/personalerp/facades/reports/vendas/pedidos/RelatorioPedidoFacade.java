package br.com.orlandoburli.personalerp.facades.reports.vendas.pedidos;

import java.sql.SQLException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import br.com.orlandoburli.core.dao.cadastro.pessoa.EnderecoPessoaDAO;
import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.ItemPedidoDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.PedidoDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.vendas.pedido.ItemPedidoVO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioPedidoFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;
	private Integer CodigoPedido;
	
	@Override
	protected String getJasperFileName() {
		return "reports/relatorioPedido.jasper";
	}
	
	@Override
	public JRDataSource getDataSource() {
		try {
			PedidoDAO pedidoDao = new PedidoDAO();
			PessoaDAO pessoaDao = new PessoaDAO();
			EnderecoPessoaDAO enderecoDao = new EnderecoPessoaDAO();
			
			PedidoVO pedido = new PedidoVO();
			pedido.setCodigoEmpresa(getCodigoEmpresa());
			pedido.setCodigoLoja(getCodigoLoja());
			pedido.setCodigoPedido(getCodigoPedido());
			
			pedido = pedidoDao.get(pedido);
			
			// Dados do cliente

			PessoaVO cliente = new PessoaVO();
			cliente.setCodigoEmpresa(pedido.getCodigoEmpresaEnderecoPessoa());
			cliente.setCodigoLoja(pedido.getCodigoLojaEnderecoPessoa());
			cliente.setCodigoPessoa(pedido.getCodigoPessoa());
			
			cliente = pessoaDao.get(cliente);
			
			pedido.setPessoaCliente(cliente);
			
			// Endereco do Cliente
			EnderecoPessoaVO enderecoCliente = new EnderecoPessoaVO();
			enderecoCliente.setCodigoEmpresa(pedido.getCodigoEmpresaEnderecoPessoa());
			enderecoCliente.setCodigoLoja(pedido.getCodigoLojaEnderecoPessoa());
			enderecoCliente.setCodigoPessoa(pedido.getCodigoPessoa());
			enderecoCliente.setCodigoEnderecoPessoa(pedido.getCodigoEnderecoPessoa());
			
			enderecoCliente = enderecoDao.get(enderecoCliente);
			pedido.setEnderecoCliente(enderecoCliente);
			
			// Endereco de Entrega do Cliente
			EnderecoPessoaVO enderecoEntregaCliente = new EnderecoPessoaVO();
			enderecoEntregaCliente.setCodigoEmpresa(pedido.getCodigoEmpresaEntrega());
			enderecoEntregaCliente.setCodigoLoja(pedido.getCodigoLojaEntrega());
			enderecoEntregaCliente.setCodigoPessoa(pedido.getCodigoPessoaEntrega());
			enderecoEntregaCliente.setCodigoEnderecoPessoa(pedido.getCodigoEnderecoPessoaEntrega());
			
			enderecoEntregaCliente = enderecoDao.get(enderecoEntregaCliente);
			pedido.setEnderecoClienteEntrega(enderecoEntregaCliente);
			
			// Transportadora
			PessoaVO transportadora = new PessoaVO();
			transportadora.setCodigoEmpresa(pedido.getCodigoEmpresaEnderecoPessoa());
			transportadora.setCodigoLoja(pedido.getCodigoLojaEnderecoPessoa());
			transportadora.setCodigoPessoa(pedido.getCodigoPessoa());
			
			transportadora = pessoaDao.get(transportadora);
			pedido.setPessoaTransportadora(transportadora);
			
			// Endereco da Transportadora
			EnderecoPessoaVO enderecoTransportadora = new EnderecoPessoaVO();
			enderecoTransportadora.setCodigoEmpresa(pedido.getCodigoEmpresaFrete());
			enderecoTransportadora.setCodigoLoja(pedido.getCodigoLojaFrete());
			enderecoTransportadora.setCodigoPessoa(pedido.getCodigoPessoaFrete());
			enderecoTransportadora.setCodigoEnderecoPessoa(pedido.getCodigoEnderecoPessoaFrete());
			
			enderecoTransportadora = enderecoDao.get(enderecoTransportadora);
			pedido.setEnderecoTransportadora(enderecoTransportadora);
			
			// Itens do pedido
			
			ItemPedidoVO itemFilter = new ItemPedidoVO();
			itemFilter.setCodigoEmpresa(pedido.getCodigoEmpresa());
			itemFilter.setCodigoLoja(pedido.getCodigoLoja());
			itemFilter.setCodigoPedido(pedido.getCodigoPedido());
			
			ItemPedidoDAO itemDao = new ItemPedidoDAO();
			pedido.setItens(itemDao.getList(itemFilter));
			
			PedidoVO[] pedidos = new PedidoVO[1];
			pedidos[0] = pedido;
			
			JRDataSource ds = new JRBeanArrayDataSource(pedidos);
			
			return ds;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoPedido(Integer codigoPedido) {
		CodigoPedido = codigoPedido;
	}

	public Integer getCodigoPedido() {
		return CodigoPedido;
	}
}
