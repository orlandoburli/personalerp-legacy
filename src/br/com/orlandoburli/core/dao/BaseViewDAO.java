package br.com.orlandoburli.core.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import br.com.orlandoburli.core.view.IView;

public class BaseViewDAO<E extends IView> extends BaseDAO {

	private String specialCondition;
	
    public int getListCount(E view, int PageSize) throws SQLException {
        try {
            RowSet row = getRowSet(buildCountStatement(view));
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
    
    public int getListCount(E view) throws SQLException {
        try {
            RowSet row = getRowSet(buildCountStatement(view));
            row.next();
            int count = row.getInt(1);
            return count;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return -1;
    }
    
    public E get(Object primaryKey) throws SQLException {
    	return get(new Object[]{ primaryKey });
    }
    
    public List<E> getList(E view) throws SQLException {
        return getList(view, 0, 0);
    }
    
    public List<E> getList(E view, String orderFields) throws SQLException {
        return getList(view, 0, 0, orderFields);
    }
    
    public List<E> getList(E view, int PageSize, int PageNumber) throws SQLException {
    	return getList(view, PageSize, PageNumber, null);
    }

	public List<E> getList(E view, int PageSize, int PageNumber, String orderFields) throws SQLException {
		List<E> list = new ArrayList<E>();
		
		try {
			
			DaoUtils.rowSetToList(view.getClass(), list, getRowSet(buildListStatement(view, PageSize, PageNumber, orderFields)));
			
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

    protected String buildCountStatement(E view) {
        StringBuilder sb = new StringBuilder("SELECT COUNT(*) ");

        sb.append(" FROM " + getTableName() + " a ");

		if (view != null) {
			try {
				DaoUtils.ViewToFilter(view, sb);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		if (getSpecialCondition() != null && !getSpecialCondition().trim().equals("")) {
			sb.append(" AND ( " + getSpecialCondition() + " ) ");
		}
		
        return sb.toString();
    }

	protected String buildListStatement(E view, int pageSize, int pageNumber, String orderFields) {
		StringBuilder sb = new StringBuilder("SELECT ");

        sb.append(" * ");
        
        sb.append(" FROM " + getTableName() + " a ");

		if (view != null) {
			try {
				DaoUtils.ViewToFilter(view, sb);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		if (getSpecialCondition() != null && !getSpecialCondition().trim().equals("")) {
			sb.append(" AND ( " + getSpecialCondition() + " ) ");
		}
		
		// Ordenação de registros
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

	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
	}

	public String getSpecialCondition() {
		return specialCondition;
	}
}
