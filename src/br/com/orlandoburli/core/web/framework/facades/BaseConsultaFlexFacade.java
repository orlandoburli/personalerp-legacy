package br.com.orlandoburli.core.web.framework.facades;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public abstract class BaseConsultaFlexFacade< E extends IValueObject, F extends BaseCadastroDAO<E>> extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	protected F dao;
	protected E filter;
	protected GenericDAO _dao;
	
	protected Class<?> getDAOClass() {
		return ((Class<?>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}
	
	protected Class<?> getVOClass() {
		return ((Class<?>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}
	
	protected abstract void doBeforeFilter();
	
	private String Filtro;
	private String CampoFiltro;
	
	private int PageNumber;
	private int PageCount;
	private int PageSize;
	
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private GrupoEmpresaVO grupoempresasessao;
	
	protected List<E> listSource;
	
	private String orderFields;
	
	private UsuarioVO usuariosessao;
	
	@SuppressWarnings("unchecked")
	public BaseConsultaFlexFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		try {
			setDao((F) getDAOClass().newInstance());
			setFilter((E) getVOClass().newInstance());
			_dao = new GenericDAO();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {}
	}
	
	@SuppressWarnings("unchecked")
	public BaseConsultaFlexFacade() {
		super();
		try {
			setDao((F) getDAOClass().newInstance());
			setFilter((E) getVOClass().newInstance());
			_dao = new GenericDAO();
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {}
	}

	@IgnoreMethodAuthentication
	public void execute() {
		doBeforeFilter();
		
		try {
			setPageCount(this.dao.getListCount(getFilter(), getPageSize()));
			setListSource(this.dao.getList(getFilter(), getPageSize(), getPageNumber(), getOrderFields()));

			doBeforeWrite();
			
			String xmlList = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>";
			for (E vo : getListSource()) {
				xmlList += Utils.voToXml(vo, false);
			}
			xmlList += "<count>" + getPageCount() + "</count>";
			xmlList += "</list>";
			
			write(xmlList);
			
			dispatch();
		} catch (SQLException e1) {
			writeErrorMessage(e1.getMessage());
			dispatch();
			return;
		}
	}
	
	public void doBeforeWrite() {}
	
	
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
		return Filtro==null?"":Filtro.replace("*", "%");
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
	public String getOrderFields() {
		return this.orderFields;
	}
	public void setOrderFields(String orderFields) {
		this.orderFields = orderFields;
	}
	public String getCampoFiltro() {
		return CampoFiltro;
	}
	public void setCampoFiltro(String campoFiltro) {
		CampoFiltro = campoFiltro;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}
	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}
	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}
	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}
	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}
	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public GrupoEmpresaVO getGrupoempresasessao() {
		return grupoempresasessao;
	}

	public void setGrupoempresasessao(GrupoEmpresaVO grupoempresasessao) {
		this.grupoempresasessao = grupoempresasessao;
	}
}