package br.com.orlandoburli.core.be;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.core.be.exceptions.persistence.AlterarBeException;
import br.com.orlandoburli.core.be.exceptions.persistence.ConfirmarBeException;
import br.com.orlandoburli.core.be.exceptions.persistence.DesfazerBeException;
import br.com.orlandoburli.core.be.exceptions.persistence.ExcluirBeException;
import br.com.orlandoburli.core.be.exceptions.persistence.InserirBeException;
import br.com.orlandoburli.core.be.exceptions.persistence.SalvarBeException;
import br.com.orlandoburli.core.be.utils.ValidatorUtils;
import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.IValueObject;

public abstract class BaseBe<E extends IValueObject, F extends BaseCadastroDAO<E>> {

	private F dao;

	@SuppressWarnings("unchecked")
	public BaseBe() {
		super();
		try {
			this.setDao((F) getDAOClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void inserir(E vo) throws InserirBeException, SalvarBeException {
		try {
			
			doBeforeInserir(vo);
			doBeforeSalvar(vo);

			vo.setNewRecord(true);
			
			getDao().persist(vo);

			doAfterInserir(vo);
			doAfterSalvar(vo);

			getDao().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InserirBeException("Erro ao inserir dados. Consulte o administrador do sistema.");
		}
	}

	public void alterar(E vo) throws AlterarBeException, SalvarBeException {
		alterar(vo, true);
	}

	public void alterar(E vo, boolean validate) throws AlterarBeException, SalvarBeException {
		try {
			
			if (validate) {
				doBeforeAlterar(vo);
				doBeforeSalvar(vo);
			}

			vo.setNewRecord(false);
			
			getDao().persist(vo);

			if (validate) {
				doAfterAlterar(vo);
				doAfterSalvar(vo);
			}

			getDao().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AlterarBeException("Erro ao alterar dados. Consulte o administrador do sistema.");
		}
	}

	public void excluir(E vo) throws ExcluirBeException {
		try {
			
			doBeforeExcluir(vo);

			getDao().remove(vo);

			doAfterExcluir(vo);

			getDao().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ExcluirBeException("Erro ao excluir dados. Consulte o administrador do sistema.");
		}
	}

	public void doBeforeInserir(E vo) throws InserirBeException {
	}

	public void doBeforeAlterar(E vo) throws AlterarBeException {
		
	}

	public void doBeforeSalvar(E vo) throws SalvarBeException {
		ValidatorUtils.validateVo(vo);
		ValidatorUtils.transformVo(vo);
	}

	public void doBeforeExcluir(E vo) throws ExcluirBeException {
	}

	public void doAfterInserir(E vo) throws InserirBeException {
	}

	public void doAfterAlterar(E vo) throws AlterarBeException {
	}

	public void doAfterSalvar(E vo) throws SalvarBeException {
	}

	public void doAfterExcluir(E vo) throws ExcluirBeException {
	}

	public E get(E vo) throws ListException {
		try {
			return getDao().get(vo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public E getById(Integer id) throws ListException {
		try {
			return dao.get(id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public List<E> getList(E vo, int PageSize, int PageNumber, String orderFields) throws ListException {
		try {
			return getDao().getList(vo, PageSize, PageNumber, orderFields);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public List<E> getList(E vo, int PageSize, int PageNumber) throws ListException {
		try {
			return getDao().getList(vo, PageSize, PageNumber);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public List<E> getList(E vo, String orderFields) throws ListException {
		try {
			return getDao().getList(vo, orderFields);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public List<E> getList(E vo) throws ListException {
		try {
			return getDao().getList(vo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public List<E> getList() throws ListException {
		try {
			return getDao().getList();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public int getPageCount() throws ListException {
		try {
			return getDao().getPageCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public int getPageCount(E vo) throws ListException {
		try {
			return getDao().getPageCount(vo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}

	public int getPageCount(E vo, int PageSize) throws ListException {
		try {
			return getDao().getPageCount(vo, PageSize);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}
	
	public void confirmar() throws ConfirmarBeException {
		try {
			getDao().commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConfirmarBeException();
		}
	}
	
	public void desfazer() throws DesfazerBeException {
		try {
			getDao().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DesfazerBeException();
		}
	}

	protected Class<?> getVOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	protected Class<?> getDAOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]);
	}

	public F getDao() {
		return dao;
	}

	public void setDao(F dao) {
		this.dao = dao;
	}
}