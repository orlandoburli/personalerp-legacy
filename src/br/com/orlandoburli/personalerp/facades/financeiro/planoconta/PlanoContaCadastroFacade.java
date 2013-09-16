package br.com.orlandoburli.personalerp.facades.financeiro.planoconta;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.dao.financeiro.TipoPlanoContaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.TipoPlanoContaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class PlanoContaCadastroFacade extends
		BaseCadastroFlexFacade<PlanoContaVO, PlanoContaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer CodigoTipoPlanoContaPesquisa;
	private String NumeroPlanoContaPaiPesquisa;

	public PlanoContaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomePlanoConta",
				"Nome do Plano de Contas"));
	}
	
	@Override
	public void doBeforeWriteVo(PlanoContaVO vo) {
		vo.setNumeroPlanoConta(vo.getNumeroPlanoConta().replace(".", "*"));
	}

	@Override
	public boolean doBeforeInsert() throws SQLException {
		synchronized (PlanoContaVO.class) {
			if (getVo().getCodigoPlanoContaPai() != null) {
				
				PlanoContaVO planoPai = new PlanoContaVO();
				planoPai.setCodigoPlanoConta(getVo().getCodigoPlanoContaPai());
				planoPai = dao.get(planoPai);
				
				PlanoContaVO filter = new PlanoContaVO();
				filter.setCodigoPlanoContaPai(getVo().getCodigoPlanoContaPai());
				
				List<PlanoContaVO> list = dao.getList(filter, "NumeroPlanoConta");
				
				String numero = "001";
				
				if (list.size() > 0) {
					numero = list.get(list.size() -1).getNumeroPlanoConta();
					numero = Integer.toString(Integer.parseInt(numero.substring(numero.lastIndexOf(".") + 1)) + 1);
					numero = Utils.fillString(numero, "0", 3, 1);
				}
				
				getVo().setNumeroPlanoConta(planoPai.getNumeroPlanoConta() + "." + numero);
				
			} else {
				dao.setSpecialCondition("CodigoPlanoContaPai IS NULL");
				
				List<PlanoContaVO> list = dao.getList(null, "NumeroPlanoConta");
				
				String numero = "1";
				
				if (list.size() > 0) {
					numero = list.get(list.size() -1).getNumeroPlanoConta();
					numero = Integer.toString(Integer.parseInt(numero) + 1);
				}
				
				getVo().setNumeroPlanoConta(numero);
				
				dao.setSpecialCondition(null);
			}
		}
		return super.doBeforeSave();
	}

	@IgnoreMethodAuthentication
	public void tipoplanoconta() {
		TipoPlanoContaDAO _dao = new TipoPlanoContaDAO();
		TipoPlanoContaVO _vo = new TipoPlanoContaVO();

		try {
			_vo = _dao.get(getCodigoTipoPlanoContaPesquisa());
			if (_vo != null) {
				write(Utils.voToXml(_vo));
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	@IgnoreMethodAuthentication
	public void planocontapai() {
		PlanoContaVO _vo = new PlanoContaVO();
		_vo.setNumeroPlanoConta(getNumeroPlanoContaPaiPesquisa());
		try {
			List<PlanoContaVO> list = dao.getList(_vo);
			if (list.size() == 1) {
				write(Utils.voToXml(list.get(0)));
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return PlanoContaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PlanoContaVO.class;
	}

	public void setCodigoTipoPlanoContaPesquisa(
			Integer codigoTipoPlanoContaPesquisa) {
		CodigoTipoPlanoContaPesquisa = codigoTipoPlanoContaPesquisa;
	}

	public Integer getCodigoTipoPlanoContaPesquisa() {
		return CodigoTipoPlanoContaPesquisa;
	}

	public void setNumeroPlanoContaPaiPesquisa(
			String numeroPlanoContaPaiPesquisa) {
		NumeroPlanoContaPaiPesquisa = numeroPlanoContaPaiPesquisa;
	}

	public String getNumeroPlanoContaPaiPesquisa() {
		return NumeroPlanoContaPaiPesquisa;
	}
}