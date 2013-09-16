package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiomanejotora;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiomanejotora.RomaneioManejoToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.madeireira.EsplanadaVO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.ManejoToraVO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.MotoristaVO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.VeiculoVO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiomanejotora.RomaneioManejoToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RomaneioManejoToraCadastroFacade extends BaseCadastroFlexFacade<RomaneioManejoToraVO, RomaneioManejoToraDAO> {
	
	private static final long serialVersionUID = 1L;
	
	private String ClienteQuery;
	
	private Integer CodigoEmpresaEnderecoCliente;
	private Integer CodigoLojaEnderecoCliente;
	private Integer CodigoPessoaCliente;
	private Integer CodigoEnderecoCliente;

	public RomaneioManejoToraCadastroFacade() {
		super();
		this.setWriteVoOnInsert(true);
		this.setWriteVoOnUpdate(true);
		//addNewValidator(validator)
	}
	
	@IgnoreMethodAuthentication
	public void clientes() {
		EnderecoPessoaVO filter = new EnderecoPessoaVO();
		
		if (getClienteQuery() != null) {
			filter.setNomeEnderecoPessoa(getClienteQuery() + "%");
		}
		
		filter.setCodigoEnderecoPessoa(getCodigoEnderecoCliente());
		filter.setCodigoEmpresa(getCodigoEmpresaEnderecoCliente());
		filter.setCodigoLoja(getCodigoLojaEnderecoCliente());
		filter.setCodigoPessoa(getCodigoPessoaCliente());
		
		try {
			write(Utils.voToXml(getGenericDao().getList(filter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void veiculos() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new VeiculoVO()), getGenericDao().getListCount(new VeiculoVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void esplanadas() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new EsplanadaVO()), getGenericDao().getListCount(new EsplanadaVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void motoristas() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new MotoristaVO()), getGenericDao().getListCount(new MotoristaVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void manejotora() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new ManejoToraVO()), getGenericDao().getListCount(new ManejoToraVO())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getClienteQuery() {
		return ClienteQuery;
	}

	public void setClienteQuery(String clienteQuery) {
		ClienteQuery = clienteQuery;
	}

	public Integer getCodigoPessoaCliente() {
		return CodigoPessoaCliente;
	}

	public void setCodigoPessoaCliente(Integer codigoPessoaCliente) {
		CodigoPessoaCliente = codigoPessoaCliente;
	}
	
	public void setCodigoLojaEnderecoCliente(Integer codigoLojaEnderecoCliente) {
		CodigoLojaEnderecoCliente = codigoLojaEnderecoCliente;
	}

	public Integer getCodigoLojaEnderecoCliente() {
		return CodigoLojaEnderecoCliente;
	}

	public void setCodigoEnderecoCliente(Integer codigoEnderecoCliente) {
		CodigoEnderecoCliente = codigoEnderecoCliente;
	}

	public Integer getCodigoEnderecoCliente() {
		return CodigoEnderecoCliente;
	}

	public void setCodigoEmpresaEnderecoCliente(
			Integer codigoEmpresaEnderecoCliente) {
		CodigoEmpresaEnderecoCliente = codigoEmpresaEnderecoCliente;
	}

	public Integer getCodigoEmpresaEnderecoCliente() {
		return CodigoEmpresaEnderecoCliente;
	}
}