package br.com.orlandoburli.core.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.view.IView;
import br.com.orlandoburli.core.vo.AutoIncrement;
import br.com.orlandoburli.core.vo.Formula;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Ignore;
import br.com.orlandoburli.core.vo.Join;
import br.com.orlandoburli.core.vo.Key;

import java.math.BigDecimal;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class DaoUtils {
	
	public static void resultToList(Class classe, List list, ResultSet result)
			throws SQLException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, SecurityException {

		ResultSetMetaData meta = result.getMetaData();

		int count = meta.getColumnCount();

		while (result.next()) {

			Object objeto = classe.newInstance();

			for (int i = 1; i < count + 1; i++) {

				String columnName = meta.getColumnName(i);

				// Busca a coluna como propriedade no objeto
				try {
					Field field = classe.getField(columnName);
					field.set(objeto, result.getObject(i));
				} catch (NoSuchFieldException e) {
				}

				list.add(objeto);
			}
		}
		result.close();
	}

	public static void rowSetToList(Class classe, List list, CachedRowSet rowset) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, SecurityException, InvocationTargetException {
		ResultSetMetaData meta = rowset.getMetaData();
		int count = meta.getColumnCount();
		while (rowset.next()) {
			Object objeto = classe.newInstance();
			Method[] listMethods = classe.getMethods();
			for (int i = 1; i < count + 1; i++) {
				String columnName = meta.getColumnName(i);
				// Busca a coluna como propriedade no objeto
				for (Method method : listMethods) {
					if (method.getName().equalsIgnoreCase("set" + columnName)) {
						if (rowset.getObject(i) != null) {
							if (rowset.getObject(i) instanceof BigDecimal) {
								BigDecimal valor = (BigDecimal) rowset.getObject(i);
								
								try { // Tenta setar com BigDecimal
									method.invoke(objeto, valor);
								} catch (Exception ex) {
									// Se der erro, vai como double
									method.invoke(objeto, valor.doubleValue());
								}
							} else {
								try {
									method.invoke(objeto, rowset.getObject(i));
								} catch (IllegalArgumentException ex1) {
									System.out.println(method.getName());
									System.out.println(rowset.getObject(i).getClass());
									throw ex1;
								}
							}
						}
						break;
					}
				}
			}
			list.add(objeto);
		}
		rowset.close();
	}

	public static void ViewToFilter(IView vo, StringBuilder sb) throws IllegalArgumentException, IllegalAccessException { 
		StringBuilder sbAux = new StringBuilder();
		Field[] fieldsList = vo.getClass().getDeclaredFields();

		sbAux.append(" WHERE 1=1 ");

		for (Field field : fieldsList) {
			field.setAccessible(true);
			Object value = field.get(vo);
			Ignore ignore = field.getAnnotation(Ignore.class);
			Formula formula = field.getAnnotation(Formula.class);
			Join join = field.getAnnotation(Join.class);
			
			if (!Modifier.isStatic(field.getModifiers())) {
				if (value != null && !field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && ignore == null && formula == null && join == null) {
					if (value.getClass().equals(String.class)) {
						sbAux.append(" AND ");
						sbAux.append(field.getName() + " ILIKE '" + value.toString() + "'");
					} else if (value.getClass().equals(Integer.class)) {
						if (Integer.parseInt(value.toString()) > 0) {
							sbAux.append(" AND ");
							sbAux.append(field.getName() + " = '" + value.toString() + "' ");
						}
					} else if (value.getClass().equals(Boolean.class)) {
						sbAux.append(" AND ");
						sbAux.append(field.getName() + " = '" + value.toString() + "' ");
					} else {
						sbAux.append(" AND ");
						sbAux.append(field.getName() + " = '" + value.toString() + "' ");
					}
				} else if (formula != null && value != null
						&& value.getClass().equals(String.class)) {
					sbAux.append(" AND ");
					sbAux.append(formula.getFormula().replace("[schema]", SystemManager.getSchemaName()) + " ILIKE '" + value.toString() + "' ");
				} else if (formula != null && value != null && value.getClass().equals(Integer.class)) {
					sbAux.append(" AND ");
					sbAux.append(formula.getFormula().replace("[schema]", SystemManager.getSchemaName()) + " = '" + value.toString() + "' ");
				} else if (join != null) {
					// TODO Filtro por JOIN - Idem formula
					
				}
			}
		}
		sb.append(sbAux.toString());
	}
	
	public static void voToFilter(IValueObject vo, StringBuilder sb) throws IllegalArgumentException, IllegalAccessException {
		voToFilter(vo, sb, false, true);
	}

	public static void voToFilter(IValueObject vo, StringBuilder sb, boolean useOr, boolean appendWhere) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sbAux = new StringBuilder();
		
		Field[] fieldsList = vo.getClass().getDeclaredFields();
		
		if (appendWhere) {
			sbAux.append(" WHERE 1=1 ");
		}
		
		if (useOr) {
			sbAux.append("(");
		}
		
		boolean first = !appendWhere;
		
		for (Field field : fieldsList) {
			
			field.setAccessible(true);
			Object value = field.get(vo);
			Ignore ignore = field.getAnnotation(Ignore.class);
			Formula formula = field.getAnnotation(Formula.class);
			Join join = field.getAnnotation(Join.class);
			
			if (!Modifier.isStatic(field.getModifiers())) {
				if (value != null && !field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && ignore == null && formula == null && join == null) {
					if (value.getClass().equals(String.class)) {
						if (first) {
							first = false;
						} else {
							sbAux.append(useOr?" OR ":" AND ");
						}
						sbAux.append(field.getName() + " ILIKE '" + value.toString() + "'");
					} else if (value.getClass().equals(Integer.class)) {
						if (Integer.parseInt(value.toString()) > 0) {
							if (first) {
								first = false;
							} else {
								sbAux.append(useOr?" OR ":" AND ");
							}
							sbAux.append(field.getName() + " = '" + value.toString() + "' ");
						}
					} else if (value.getClass().equals(Boolean.class)) {
						if (first) {
							first = false;
						} else {
							sbAux.append(useOr?" OR ":" AND ");
						}
						sbAux.append(field.getName() + " = '" + value.toString() + "' ");
					} else {
						if (first) {
							first = false;
						} else {
							sbAux.append(useOr?" OR ":" AND ");
						}
						sbAux.append(field.getName() + " = '" + value.toString() + "' ");
					}
				} else if (formula != null && value != null && value.getClass().equals(String.class)) {
					if (first) {
						first = false;
					} else {
						sbAux.append(useOr?" OR ":" AND ");
					}
					sbAux.append(formula.getFormula().replace("[schema]", SystemManager.getSchemaName()) + " ILIKE '" + value.toString() + "' ");
				} else if (formula != null && value != null && value.getClass().equals(Integer.class)) {
					if (first) {
						first = false;
					} else {
						sbAux.append(useOr?" OR ":" AND ");
					}
					sbAux.append(formula.getFormula().replace("[schema]", SystemManager.getSchemaName()) + " = '" + value.toString() + "' ");
				} else if (join != null && value != null) {
					// Filter com JOIN
					String nomeCampo = "COALESCE((SELECT b." + join.foreignColumnName() + " FROM " + SystemManager.getSchemaName() + "." + join.entityName() + " b WHERE 1=1";
					String where = "";
					
					for (int i = 0; i < join.foreignKeys().length; i++) {
						where += " AND a." + join.localKeys()[i] + " = b." + join.foreignKeys()[i];
					}
					nomeCampo += where + "), '') ";
					
					if (value.getClass().equals(String.class)) {
						if (first) {
							first = false;
						} else {
							sbAux.append(useOr?" OR ":" AND ");
						}
						sbAux.append(" " + nomeCampo + " ILIKE '" + value.toString() + "'");
					} else {
						if (first) {
							first = false;
						} else {
							sbAux.append(useOr?" OR ":" AND ");
						}
						sbAux.append(" " + nomeCampo + " = '" + value.toString() + "'");
					}
				}
			}
		}
		
		if (useOr) {
			sbAux.append(")");
		}
		// Mapeamento das formulas
		if (DaoUtils.hasFormula(vo)) {
			String str1 = sb.toString() + sbAux.toString();
			str1 = mapValues(vo, str1);
			sb.delete(0, sb.length());
			sb.append(str1);
		} else {
			sb.append(sbAux.toString());
		}
	}

	@SuppressWarnings("unused")
	public static void buildInsertStatement(String tableName, IValueObject vo, StringBuilder sb) {
		StringBuilder sbRetorno = new StringBuilder();
		sbRetorno.append("INSERT INTO " + tableName + " (");

		Field[] fieldsList = vo.getClass().getDeclaredFields();

		String fieldAutoInc = null;

		int size = 0;
		for (Field field : fieldsList) {
			field.setAccessible(true);
			AutoIncrement auto = field.getAnnotation(AutoIncrement.class);
			Ignore ignore = field.getAnnotation(Ignore.class);
			Formula formula = field.getAnnotation(Formula.class);
			Join join = field.getAnnotation(Join.class);
			
			if (!Modifier.isStatic(field.getModifiers())) {
				if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && auto == null && ignore == null && formula == null && join == null) {
					sbRetorno.append(field.getName().toUpperCase() + ", ");
					size++;
				} else if (auto != null) {
					fieldAutoInc = field.getName();
				}
			}
		}

		StringBuilder sbAux = new StringBuilder();
		for (Field field : fieldsList) {
			AutoIncrement auto = field.getAnnotation(AutoIncrement.class);
			Ignore ignore = field.getAnnotation(Ignore.class);
			Formula formula = field.getAnnotation(Formula.class);
			Join join = field.getAnnotation(Join.class);
			
			if (!Modifier.isStatic(field.getModifiers())) {
				if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && auto == null && ignore == null && formula == null && join == null) {
					sbAux.append(auto == null ? "?, " : "nextval('" + tableName.toLowerCase() + "_" + field.getName().toLowerCase() + "_seq" + "'::regclass), ");
				}
			}
		}
		String str1 = sbRetorno.toString();
		String str2 = sbAux.toString();

		sb.append(str1.substring(0, str1.length() - 2));
		sb.append(") VALUES (");
		sb.append(str2.substring(0, str2.length() - 2));
		sb.append(")");
		
		if (fieldAutoInc != null) {
			sb.append(" RETURNING " + fieldAutoInc);
		}
	}

	@SuppressWarnings("unused")
	public static void buildUpdateStatement(String tableName, IValueObject vo, StringBuilder sb) {
		StringBuilder sbRetorno = new StringBuilder();
		sbRetorno.append(" UPDATE " + tableName + " SET ");

		Field[] fieldsList = vo.getClass().getDeclaredFields();
		int size = 0;
		for (Field field : fieldsList) {
			Ignore ignore = field.getAnnotation(Ignore.class);
			Formula formula = field.getAnnotation(Formula.class);
			Join join = field.getAnnotation(Join.class);
			
			if (!Modifier.isStatic(field.getModifiers())) {
				if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && ignore == null && formula == null && join == null) {
					field.setAccessible(true);
					sbRetorno.append(" " + field.getName().toUpperCase() + " = ?, ");
					size++;
				}
			}
		}

		String str1 = sbRetorno.toString();
		sb.append(str1.substring(0, str1.length() - 2));
		sb.append(" WHERE ");

		sbRetorno = new StringBuilder(); // Zera a String Builder

		// Monta clausula WHERE
		for (Field field : fieldsList) {
			if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID")) {
				field.setAccessible(true);
				Key key = field.getAnnotation(Key.class);
				if (key != null) {
					sbRetorno.append(field.getName() + " = ? AND ");
				}
			}
		}
		String str2 = sbRetorno.toString();
		sb.append(str2.substring(0, str2.length() - 5));
	}

	public static void buildDeleteStatement(String tableName, IValueObject vo, StringBuilder sb) {
		StringBuilder sbRetorno = new StringBuilder();
		sb.append(" DELETE FROM " + tableName);

		Field[] fieldsList = vo.getClass().getDeclaredFields();
		sb.append(" WHERE ");

		sbRetorno = new StringBuilder(); // Zera a String Builder

		// Monta clausula WHERE
		for (Field field : fieldsList) {
			if (!Modifier.isStatic(field.getModifiers())) {
				if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID")) {
					field.setAccessible(true);
					Key key = field.getAnnotation(Key.class);
					if (key != null) {
						sbRetorno.append(field.getName() + " = ? AND ");
					}
				}
			}
		}
		String str2 = sbRetorno.toString();
		sb.append(str2.substring(0, str2.length() - 5));
	}

	public static void voToPreparedStatement(IValueObject vo, CallableStatement prepared) throws IllegalArgumentException, SQLException, IllegalAccessException {
		voToPreparedStatement(vo, prepared, false);
	}

	public static void voToPreparedStatement(IValueObject vo, CallableStatement prepared, boolean isDelete) throws IllegalArgumentException, SQLException, IllegalAccessException {
		Field[] fieldsList = vo.getClass().getDeclaredFields();
		int pos = 1;

		if (!isDelete) {
			for (Field field : fieldsList) {
				field.setAccessible(true);
				AutoIncrement auto = field.getAnnotation(AutoIncrement.class);
				Ignore ignore = field.getAnnotation(Ignore.class);
				Formula formula = field.getAnnotation(Formula.class);
				Join join = field.getAnnotation(Join.class);
				
				if (!Modifier.isStatic(field.getModifiers())) {
					if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && ignore == null && formula == null && join == null) {
						if (!vo.IsNew() || auto == null) {
							if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true")) {
								if (vo.IsNew() && SystemManager.getProperty("debug.sql.insert").equalsIgnoreCase("true")) {
									System.out.println(field.getName() + " = " + field.get(vo));
								} else if (SystemManager.getProperty("debug.sql.update").equalsIgnoreCase("true")) {
									System.out.println(field.getName() + " = " + field.get(vo));
								}
							}
							prepared.setObject(pos, field.get(vo));
							pos++;
						}
					}
				}
			}
		}
		// Parametros do UPDATE (WHERE)
		if (!vo.IsNew() || isDelete) {
			for (Field field : fieldsList) {
				field.setAccessible(true);
				Ignore ignore = field.getAnnotation(Ignore.class);
				Formula formula = field.getAnnotation(Formula.class);
				Key key = field.getAnnotation(Key.class);
				Join join = field.getAnnotation(Join.class);
				
				if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && ignore == null && formula == null && key != null && join == null) {
					if (SystemManager.getProperty("debug.sql").equalsIgnoreCase("true")) {
						if (vo.IsNew() && SystemManager.getProperty("debug.sql.insert").equalsIgnoreCase("true")) {
							System.out.println(field.getName() + " = " + field.get(vo));
						} else if (SystemManager.getProperty("debug.sql.update").equalsIgnoreCase("true")) {
							System.out.println(field.getName() + " = " + field.get(vo));
						}
					}
					prepared.setObject(pos, field.get(vo));
					pos++;
				}
			}
		}
	}

	public static String buildSelectLastValue(IValueObject vo) {
		String nomeTabela = vo.getClass().getSimpleName().toLowerCase().replace("vo", "");
		nomeTabela = SystemManager.getProperty("db.schema") + "." + nomeTabela;
		String nomeCampo = null;
		Field[] fieldsList = vo.getClass().getFields();
		
		for (Field field : fieldsList) {
			AutoIncrement auto = field.getAnnotation(AutoIncrement.class);
			if (auto != null) {
				nomeCampo = field.getName().toLowerCase();
				break;
			}
		}
		return "SELECT last_value FROM " + nomeTabela + "_" + nomeCampo + "_seq";
	}

	public static String buildFieldSelect(IValueObject vo) {
		StringBuilder sb = new StringBuilder();
		String nomeCampo = null;
		Field[] fieldsList = vo.getClass().getDeclaredFields();
		
		for (Field field : fieldsList) {
			
			Ignore ignore = field.getAnnotation(Ignore.class);
			if (!field.getName().equalsIgnoreCase("isnew") && !field.getName().equalsIgnoreCase("serialVersionUID") && ignore == null) {
				field.setAccessible(true);
				Formula formula = field.getAnnotation(Formula.class);
				Join join = field.getAnnotation(Join.class);
				if (formula != null) {
					nomeCampo = formula.getFormula().replace("[schema]", SystemManager.getSchemaName()) + " AS " + field.getName().toLowerCase();
				} else if (join != null) {
					nomeCampo = "(SELECT b." + join.foreignColumnName() + " FROM " + SystemManager.getSchemaName() + "." + join.entityName() + " b WHERE 1=1";
					String where = "";
					for (int i = 0; i < join.foreignKeys().length; i++) {
						where += " AND a." + join.localKeys()[i] + " = b." + join.foreignKeys()[i];
					}
					nomeCampo += where + ") AS " + field.getName().toLowerCase();
				} else {
					nomeCampo = field.getName().toLowerCase();
				}
				sb.append(nomeCampo + ", ");
			}
		}
		String str1 = sb.toString();
		return str1.substring(0, str1.length() - 2);
	}

	public static void setIdReturned(IValueObject vo, int Id) {
		Field[] fieldsList = vo.getClass().getDeclaredFields();
		for (Field field : fieldsList) {
			field.setAccessible(true);
			AutoIncrement auto = field.getAnnotation(AutoIncrement.class);
			if (auto != null) {
				try {
					if (field.getType().equals(int.class)) {
						field.setInt(vo, Id);
					} else {
						field.set(vo, Id);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}

	public static boolean hasAutoIncrement(IValueObject vo) {
		Field[] fieldsList = vo.getClass().getDeclaredFields();
		for (Field field : fieldsList) {
			field.setAccessible(true);
			AutoIncrement auto = field.getAnnotation(AutoIncrement.class);
			if (auto != null) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasFormula(IValueObject vo) {
		Field[] fieldsList = vo.getClass().getDeclaredFields();
		for (Field field : fieldsList) {
			field.setAccessible(true);
			Formula formula = field.getAnnotation(Formula.class);
			Join join = field.getAnnotation(Join.class);
			if (formula != null || join != null) {
				return true;
			}
		}
		return false;
	}

	public static void PkToFilter(IValueObject vo, Object[] primaryKey) {
		Field[] fields = vo.getClass().getDeclaredFields();

		int i = 0;

		for (Field field : fields) {
			field.setAccessible(true);
			Key key = field.getAnnotation(Key.class);
			if (key != null) {
				try {
					field.set(vo, primaryKey[i]);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
	}
	
	/**
	 * Verifica se tem algum campo com a marcacao @Key com valor nulo
	 * @param vo VO a ser checado
	 * @return true se tiver algum campo chave com valor nulo
	 */
	public static boolean hasNullKey(IValueObject vo) {
		Field[] fields = vo.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			Key key = field.getAnnotation(Key.class);
			if (key != null) {
				try {
					if (field.get(vo) == null) {
						return true;
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static Object getproperty(Object objeto, String property) {
		if (objeto == null) {
			return null;
		}
		String fieldname = property;
		Object objetoaux = objeto;

		int index = property.indexOf('.');
		if (index >= 0) {
			fieldname = property.substring(0, index);
		}

		Object retorno = null;
		try {
			Field[] fields = objetoaux.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(fieldname)) {
					field.setAccessible(true);
					retorno = field.get(objetoaux);

					if (index >= 0) {
						retorno = getproperty(retorno, property
								.substring(index + 1));
					}

					return retorno;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static String mapValues(Object object, String property) {
		String _retorno = property;
		String fieldname_old = "";
		if (object == null) {
			// return property;
		}
		if (_retorno != null) {
			while (_retorno.indexOf('{') >= 0) {
				int inicio = _retorno.indexOf('{') + 1;
				int fim = _retorno.indexOf('}');
				String fieldname = _retorno.substring(inicio, fim);
				if (fieldname.equals(fieldname_old)) {
					_retorno = _retorno.replace("{" + fieldname + "}", "");
				} else {
					Object retorno = getproperty(object, fieldname);
					if (retorno != null) {
						String newstring = retorno.toString();
						_retorno = _retorno.replace("{" + fieldname + "}",
								newstring);
					} else {
						_retorno = _retorno.replace("{" + fieldname + "}", "");
					}
				}
				fieldname_old = fieldname;
			}
		}
		return _retorno;
	}

	public static void clonePk(IValueObject source, IValueObject destination) {
		Field[] fields = source.getClass().getDeclaredFields();

		for (Field field : fields) {
			Key key = field.getAnnotation(Key.class);
			if (key != null) {
				field.setAccessible(true);
				try {
					Field fieldDest = destination.getClass().getDeclaredField(field.getName());
					fieldDest.setAccessible(true);
					fieldDest.set(destination, field.get(source));
					//field.set(destination, field.get(source));
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
		}
	}
}