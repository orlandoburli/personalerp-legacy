package br.com.orlandoburli.core.web.framework.facades;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.base.MenuVO;

public abstract class BaseConsultaFacade< E extends IValueObject, F extends BaseCadastroDAO<E>> extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	protected F dao;
	protected E filter;
	
	protected abstract Class<?> getDAOClass();
	protected abstract Class<?> getVOClass();
	
	protected abstract String getJspConsulta();
	
	protected abstract void doBeforeFilter();
	
	private String Filtro;
	private String FiltroQuery;
	
	private int PageNumber;
	private int PageCount;
	private int PageSize;
	
	private List<E> listSource;
	
	private List<MenuVO> menuUsuarioSession;
	private String orderFields;
	
	@SuppressWarnings("unchecked")
	public BaseConsultaFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		try {
			setDao((F) getDAOClass().newInstance());
			setFilter((E) getVOClass().newInstance());
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public BaseConsultaFacade() {
		super();
		try {
			setDao((F) getDAOClass().newInstance());
			setFilter((E) getVOClass().newInstance());
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {}
	}
	
	public void execute() throws SQLException {
		if (isPost()) {
			this.setPageNumber(0);
		}
		doBeforeFilter();
		setPageCount(this.dao.getListCount(getFilter(), getPageSize()));
		setListSource(this.dao.getList(getFilter(), getPageSize(), getPageNumber(), getOrderFields()));
		forward(getJspConsulta());
	}
	
	public void setDao(F dao) {
		this.dao = dao;
	}
	public F getDao() {
		return dao;
	}
	public void setFilter(E filter) {
		this.filter = filter;
	}
	public E getFilter() {
		return filter;
	}
	public void setFiltro(String filtro) {
		Filtro = filtro;
	}
	
	public String getFiltro() {
		if (isPost()) {
			return Filtro==null?"":Filtro;
		} else {
			return FiltroQuery==null?"":FiltroQuery;
		}
	}
	public void setPageNumber(int pageNumber) {
		PageNumber = pageNumber;
	}
	public int getPageNumber() {
		return PageNumber;
	}
	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}
	public int getPageCount() {
		return PageCount;
	}
	public void setListSource(List<E> listSource) {
		this.listSource = listSource;
	}
	public List<E> getListSource() {
		return listSource;
	}
	public void setPageSize(int PageSize) {
		this.PageSize = PageSize;
	}
	public int getPageSize() {
		return this.PageSize;
	}
	
	public String getLinkVoltar() {
		if (menuUsuarioSession != null) {
			for (MenuVO item : menuUsuarioSession) {
				if (item.getNomeObjeto() != null && item.getNomeObjeto().equalsIgnoreCase(getFacadeName() + ".action")) {
					return "index.action?menu.CodigoMenu=" + item.getCodigoMenuPai();
				}
			}
		}
		return null;
	}
	
	public String getOrderFields() {
		return this.orderFields;
	}
	
	public void setOrderFields(String orderFields) {
		this.orderFields = orderFields;
	}
}