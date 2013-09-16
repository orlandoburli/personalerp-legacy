package br.com.orlandoburli.core.dao;

import java.sql.SQLException;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import br.com.orlandoburli.core.vo.IValueObject;

public class GenericDAO extends BaseCadastroDAO<IValueObject> {
	
	private Class<?> classVO;
	
	@Override
	public IValueObject get(IValueObject filter) throws SQLException {
		this.classVO = filter.getClass();
		return super.get(filter);
	}
	
	@Override
	public IValueObject get(Object primaryKey) throws SQLException {
		throw new NotImplementedException();
	}
	
	@Override
	public IValueObject get(Object[] primaryKeys) throws SQLException {
		throw new NotImplementedException();
	}
	
	@Override
	public boolean executeUpdate(String statement) throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException {
		return super.executeUpdate(statement);
	}
	
	@Override
	public List<IValueObject> getList() throws SQLException {
		throw new NotImplementedException();
	}
	
	@Override
	public List<IValueObject> getList(IValueObject vo) throws SQLException {
		this.classVO = vo.getClass();
		return super.getList(vo);
	}
	
	@Override
	public List<IValueObject> getList(IValueObject vo, String orderFields)
			throws SQLException {
		this.classVO = vo.getClass();
		return super.getList(vo, orderFields);
	}
	
	@Override
	public List<IValueObject> getList(IValueObject vo, int PageSize,
			int PageNumber) throws SQLException {
		this.classVO = vo.getClass();
		return super.getList(vo, PageSize, PageNumber);
	}
	
	@Override
	public int getListCount(IValueObject vo) throws SQLException {
		this.classVO = vo.getClass();
		return super.getListCount(vo);
	}
	
	@Override
	public int getListCount() throws SQLException {
		throw new NotImplementedException();
	}
	
	@Override
	public int getListCount(IValueObject vo, int PageSize) throws SQLException {
		this.classVO = vo.getClass();
		return super.getListCount(vo, PageSize);
	}
	
	@Override
	public List<IValueObject> getList(IValueObject vo, int PageSize, int PageNumber, String orderFields) throws SQLException {
		this.classVO = vo.getClass();
		return super.getList(vo, PageSize, PageNumber, orderFields);
	}
	
	@Override
	protected Class<?> getVOClass() {
		if (classVO != null) {
			return classVO;
		}
		return super.getVOClass();
	}
	
	@Override
	public boolean remove(IValueObject vo) throws SQLException {
		this.classVO = vo.getClass();
		return super.remove(vo);
	}
	
	@Override
	public boolean persist(IValueObject vo) throws SQLException {
		this.classVO = vo.getClass();
		return super.persist(vo);
	}
}
