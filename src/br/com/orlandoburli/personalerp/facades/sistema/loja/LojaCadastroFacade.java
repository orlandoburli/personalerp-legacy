package br.com.orlandoburli.personalerp.facades.sistema.loja;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.rh.FuncionarioDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class LojaCadastroFacade extends BaseCadastroFlexFacade<LojaVO, LojaDAO>{

	private static final long serialVersionUID = 1L;
	private Integer CodigoEmpresaPesquisa;
	private Integer CodigoCidadePesquisa;

	public LojaCadastroFacade() {
		super();
	}
	
	@IgnoreMethodAuthentication
	public void empresa() {
		EmpresaVO filter = new EmpresaVO();
		filter.setCodigoEmpresa(getCodigoEmpresaPesquisa());
		try {
			write(Utils.voToXml(getGenericDao().get(filter)));
		} catch (SQLException e) {
			e.printStackTrace();
			write(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void cidade() {
		CidadeVO filter = new CidadeVO();
		filter.setCodigoCidade(getCodigoCidadePesquisa());
		try {
			write(Utils.voToXml(getGenericDao().get(filter)));
		} catch (SQLException e) {
			e.printStackTrace();
			write(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void funcionarios() {
		try {
			write(Utils.voToXml(new FuncionarioDAO().getList(null, "NomeFuncionario")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCodigoCidadePesquisa(Integer codigoCidadePesquisa) {
		CodigoCidadePesquisa = codigoCidadePesquisa;
	}

	public Integer getCodigoCidadePesquisa() {
		return CodigoCidadePesquisa;
	}

	public void setCodigoEmpresaPesquisa(Integer codigoEmpresaPesquisa) {
		CodigoEmpresaPesquisa = codigoEmpresaPesquisa;
	}

	public Integer getCodigoEmpresaPesquisa() {
		return CodigoEmpresaPesquisa;
	}
}