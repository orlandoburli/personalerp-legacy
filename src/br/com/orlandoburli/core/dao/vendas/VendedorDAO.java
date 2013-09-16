package br.com.orlandoburli.core.dao.vendas;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.vo.rh.FuncionarioVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;

public class VendedorDAO extends BaseCadastroDAO<VendedorVO> {

	public VendedorVO getVendedorByContrato(ContratoTrabalhoVO contrato) throws SQLException {

		VendedorVO vendedor = new VendedorVO();

		vendedor.setCodigoEmpresaFuncionario(contrato.getCodigoEmpresaFuncionario());
		vendedor.setCodigoLojaFuncionario(contrato.getCodigoLojaFuncionario());
		vendedor.setCodigoFuncionario(contrato.getCodigoFuncionario());
		
	 	List<VendedorVO> list = getList(vendedor);
		
	 	if (list.size() == 1) {
	 		return list.get(0);
	 	}
	 	
		return null;
	}
	
	public VendedorVO getVendedorByFuncionario(FuncionarioVO funcionario) throws SQLException {

		VendedorVO vendedor = new VendedorVO();

		vendedor.setCodigoEmpresaFuncionario(funcionario.getCodigoEmpresa());
		vendedor.setCodigoLojaFuncionario(funcionario.getCodigoLoja());
		vendedor.setCodigoFuncionario(funcionario.getCodigoFuncionario());
		
	 	List<VendedorVO> list = getList(vendedor);
		
	 	if (list.size() == 1) {
	 		return list.get(0);
	 	}
	 	
		return null;
	}

}
