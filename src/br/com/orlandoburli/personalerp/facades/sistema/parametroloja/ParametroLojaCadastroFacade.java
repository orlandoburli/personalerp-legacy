package br.com.orlandoburli.personalerp.facades.sistema.parametroloja;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.sistema.ParametroLojaVO;
import br.com.orlandoburli.core.vo.sistema.ParametroVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class ParametroLojaCadastroFacade extends
		BaseCadastroFlexFacade<ParametroLojaVO, ParametroLojaDAO> {

	private static final long serialVersionUID = 1L;

	public ParametroLojaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		// Verifica se e um objeto novo
		ParametroLojaVO copia = (ParametroLojaVO) getGenericDao().get(getVo());
		getVo().setNewRecord(copia == null);
		
		return super.doBeforeSave();
	}

	@Override
	public void visualizar() {
		ParametroLojaVO backup = getVo();
		try {
			this.setVo(this.dao.get(getVo()));

			if (getVo() == null) {
				setVo(backup);
				ParametroVO parametro = new ParametroVO();
				parametro.setChaveParametro(getVo().getChaveParametro());
				parametro = (ParametroVO) getGenericDao().get(parametro);
				
				if (parametro != null) {
					getVo().setDescricaoParametro(parametro.getDescricaoParametro());
					getVo().setTipoDadoParametro(parametro.getTipoDadoParametro());
					getVo().setDecimaisParametro(parametro.getDecimaisParametro());
					getVo().setValoresParametro(parametro.getValoresParametro());
				}
			}

		} catch (SQLException e) {
			doBeforeWriteSqlErro(e);
		}

		doBeforeWriteVo(getVo());
		write(Utils.voToXml(getVo()));
		dispatch();
	}

	@Override
	protected Class<?> getDAOClass() {
		return ParametroLojaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ParametroLojaVO.class;
	}
}