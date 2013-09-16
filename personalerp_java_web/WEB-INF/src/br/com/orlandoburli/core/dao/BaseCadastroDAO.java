package br.com.orlandoburli.core.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.vo.IValueObject;

import javax.sql.RowSet;

public abstract class BaseCadastroDAO<E extends IValueObject> extends BaseDAO {

	private String specialCondition;

	public E auxiliar;

	public BaseCadastroDAO() {
	}

	public int getListCount(E vo, int PageSize) throws SQLException {
		try {
			RowSet row = getRowSet(buildCountStatement(vo));
			row.next();
			int count = row.getInt(1);
			int inteiro = count / PageSize;
			int resto = count % PageSize;
			inteiro += (resto > 0) ? 1 : 0;

			return inteiro;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public int getListCount(E vo) throws SQLException {
		try {
			RowSet row = getRowSet(buildCountStatement(vo));
			row.next();
			int count = row.getInt(1);
			return count;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public int getListCount() throws SQLException {
		try {
			RowSet row = getRowSet(buildCountStatement((E) getVOClass()
					.newInstance()));
			row.next();
			int count = row.getInt(1);
			return count;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getPageCount(E vo, int PageSize) throws SQLException {
        try {
            RowSet row = getRowSet(buildCountStatement(vo));
            row.next();
            int count = row.getInt(1);
            int inteiro = count / PageSize;
            int resto = count % PageSize;
            inteiro += (resto > 0)?1:0;
            
            return inteiro;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public int getPageCount(E vo) throws SQLException {
        try {
            RowSet row = getRowSet(buildCountStatement(vo));
            row.next();
            int count = row.getInt(1);
            return count;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    @SuppressWarnings("unchecked")
	public int getPageCount() throws SQLException {
    	try {
            RowSet row = getRowSet(buildCountStatement((E) getVOClass().newInstance()));
            row.next();
            int count = row.getInt(1);
            return count;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return -1;
    }
    
	public E get(Object primaryKey) throws SQLException {
		return get(new Object[] { primaryKey });
	}

	@SuppressWarnings("unchecked")
	public E get(Object[] primaryKeys) throws SQLException {
		if (primaryKeys == null || primaryKeys.length == 0) {
			return null;
		}

		try {
			E filter = (E) getVOClass().newInstance();
			DaoUtils.PkToFilter(filter, primaryKeys);
			List<E> list = getList(filter);
			if (list.size() == 1) { // Deve retornar somente um registro. Se
									// retornar mais de um, retorna nulo.
				return list.get(0);
			} else {
				return null;
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public E get(E filter) throws SQLException {
		if (filter == null) {
			return null;
		}

		try {
			E newfilter = (E) getVOClass().newInstance(); // Faz uma "copia",
															// para setar
															// somente os
															// atributos chave
			DaoUtils.clonePk(filter, newfilter);
			List<E> list = getList(newfilter);
			if (list.size() == 1) { // Deve retornar somente um registro. Se
									// retornar mais de um, retorna nulo.
				return list.get(0);
			} else {
				return null;
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<E> getList() throws SQLException {
		try {
			return getList((E) getVOClass().newInstance());
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return null;
	}

	/**
	 * Retorna uma lista filtrando pelo VO, somente os atributos de PK.
	 * 
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<E> getListByPK(IValueObject vo) throws SQLException {
		E filter;
		try {
			filter = (E) getVOClass().newInstance();
			DaoUtils.clonePk(vo, filter);
			List<E> list = getList(filter);
			return list;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<E> getList(E vo) throws SQLException {
		return getList(vo, 0, 0);
	}

	public List<E> getList(E vo, String orderFields) throws SQLException {
		return getList(vo, 0, 0, orderFields);
	}

	public List<E> getList(E vo, int PageSize, int PageNumber)
			throws SQLException {
		return getList(vo, PageSize, PageNumber, null);
	}

	@SuppressWarnings("unchecked")
	public List<E> getList(E vo, int PageSize, int PageNumber,
			String orderFields) throws SQLException {
		List<E> list = new ArrayList<E>();

		try {
			if (vo == null) {
				vo = (E) getVOClass().newInstance();
			}
			DaoUtils.rowSetToList(
					vo.getClass(),
					list,
					getRowSet(buildListStatement(vo, PageSize, PageNumber,
							orderFields)));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return list;
	}

	protected String buildCountStatement(E vo) {
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) ");

		sb.append(" FROM " + getTableName() + " a ");

		if (vo != null) {
			try {
				DaoUtils.voToFilter(vo, sb);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if (getSpecialCondition() != null
				&& !getSpecialCondition().trim().equals("")) {
			sb.append(" AND ( " + getSpecialCondition() + " ) ");
		}

		return sb.toString();
	}

	protected String buildListStatement(E vo, int pageSize, int pageNumber,
			String orderFields) {
		StringBuilder sb = new StringBuilder("SELECT ");

		if (DaoUtils.hasFormula(vo)) {
			sb.append(DaoUtils.buildFieldSelect(vo));
		} else {
			sb.append(" * ");
		}

		sb.append(" FROM " + getTableName() + " a ");

		if (vo != null) {
			try {
				DaoUtils.voToFilter(vo, sb);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		if (getSpecialCondition() != null
				&& !getSpecialCondition().trim().equals("")) {
			sb.append(" AND ( " + getSpecialCondition() + " ) ");
		}

		// Ordena��o de registros
		if (orderFields != null && !orderFields.trim().equals("")) {
			sb.append(" ORDER BY " + orderFields);
		}

		// Monta paginacao
		if (pageSize > 0) {
			if (pageNumber <= 0) {
				pageNumber = 1;
			}
			sb.append(" LIMIT " + pageSize);
			// Calcula inicio do resultado
			int begin = ((pageSize) * (pageNumber - 1));
			sb.append(" OFFSET " + begin);
		}
		return sb.toString();
	}

	public boolean persist(E vo) throws SQLException {
		if (vo.IsNew()) {
			boolean retorno = doInsert(vo);
			vo.setNewRecord(false);
			return retorno;
		} else {
			return doUpdate(vo);
		}
	}

	public boolean remove(E vo) throws SQLException {
		return doDelete(vo);
	}

	// @SuppressWarnings("unchecked")
	// public boolean removeByFilterPK(IValueObject voKey) throws SQLException {
	// try {
	// E filter = (E) getVOClass().newInstance();
	// DaoUtils.clonePk(voKey, filter);
	// List<E> list = getList(filter);
	//
	// for (E item : list) {
	// this.remove(item);
	// }
	// return true;
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	//
	// return false;
	// }

	public boolean removeByFilter(E filter) throws SQLException {
		List<E> list = getList(filter);

		for (E item : list) {
			this.remove(item);
		}
		return true;
	}

	protected boolean doInsert(E vo) throws SQLException {
		StringBuilder sb = new StringBuilder();
		try {
			DaoUtils.buildInsertStatement(getTableName(), vo, sb);
			executeUpdate(sb.toString(), vo);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected boolean doUpdate(E vo) throws SQLException {
		StringBuilder sb = new StringBuilder();
		try {
			DaoUtils.buildUpdateStatement(getTableName(), vo, sb);
			executeUpdate(sb.toString(), vo);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected boolean doDelete(E vo) throws SQLException {
		StringBuilder sb = new StringBuilder();
		try {
			vo.setNewRecord(false);
			DaoUtils.buildDeleteStatement(getTableName(), vo, sb);
			executeDelete(sb.toString(), vo);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
	}

	public String getSpecialCondition() {
		return specialCondition;
	}

	protected Class<?> getVOClass() {
		return ((Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	protected String getTableName() {
		return getSchemaName() + "."
				+ this.getVOClass().getSimpleName().replaceAll("VO", "");
	}
}