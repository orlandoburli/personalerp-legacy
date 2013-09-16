package br.com.orlandoburli.personalerp.facades.fiscal.tributacao;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.base.EstadoDAO;
import br.com.orlandoburli.core.dao.fiscal.OperacaoTributacaoDAO;
import br.com.orlandoburli.core.dao.fiscal.TributacaoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.EstadoVO;
import br.com.orlandoburli.core.vo.fiscal.OperacaoTributacaoVO;
import br.com.orlandoburli.core.vo.fiscal.TributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class TributacaoCadastroFacade extends BaseCadastroFlexFacade<TributacaoVO, TributacaoDAO>{

	private String EmptyEstado;
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doBeforeWriteSqlErro(Exception e) {
		if (e.getMessage().indexOf("tributacao_pk") >= 0) {
			writeErrorMessage("Tributação já cadastrada!");
		} else {
			super.doBeforeWriteSqlErro(e);
		}
	}
	
	@IgnoreMethodAuthentication
	public void estado() {
		try {
			List<EstadoVO> list = new EstadoDAO().getList(new EstadoVO(), "NomeEstado");
			
			if (getEmptyEstado() != null && getEmptyEstado().equalsIgnoreCase("S")) {
				EstadoVO empty = new EstadoVO();
				empty.setSiglaEstado("XX");
				empty.setNomeEstado("TODOS");
				list.add(0, empty);
			}
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void operacao() {
		try {
			List<OperacaoTributacaoVO> list = new OperacaoTributacaoDAO().getList();
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setEmptyEstado(String emptyEstado) {
		EmptyEstado = emptyEstado;
	}

	public String getEmptyEstado() {
		return EmptyEstado;
	}
}