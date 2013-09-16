package br.com.orlandoburli.core.dao.cadastro.pessoa;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;

public class EnderecoPessoaDAO extends BaseCadastroDAO<EnderecoPessoaVO> {

	
	public List<EnderecoPessoaVO> getListByCpfCnpj(String cpfCnpj) throws SQLException {
		EnderecoPessoaVO filter = new EnderecoPessoaVO();
		filter.setCpfCnpjEnderecoPessoa(cpfCnpj);
		
		return getList(filter);
	}
}