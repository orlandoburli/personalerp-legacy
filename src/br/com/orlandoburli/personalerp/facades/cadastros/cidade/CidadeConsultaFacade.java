package br.com.orlandoburli.personalerp.facades.cadastros.cidade;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.dao.base.EstadoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.vo.base.EstadoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class CidadeConsultaFacade extends BaseConsultaFlexFacade<CidadeVO, CidadeDAO> {

	private static final long	serialVersionUID	= 1L;
	
	private Integer CodigoEstado;
	
	@IgnoreMethodAuthentication
	public void list() {
		List<EstadoVO> list = null;
		try {
			list = new EstadoDAO().getList(new EstadoVO(), "SiglaEstado");
		} catch (SQLException e) {
			write(e.getMessage());
			return;
		}
		EstadoVO emptyVo = new EstadoVO();
		emptyVo.setSiglaEstado("TODOS");
		list.add(0, emptyVo);
		write(Utils.voToXml(list));
	}
	
	@Override
	public String getOrderFields() {
		return "NomeCidade";
	}
	
	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeCidade(getFiltro() + "%");
		getFilter().setCodigoEstado(getCodigoEstado());
	}

	@Override
	protected Class<?> getDAOClass() {
		return CidadeDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return CidadeVO.class;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		CodigoEstado = codigoEstado;
	}

	public Integer getCodigoEstado() {
		return CodigoEstado;
	}
}