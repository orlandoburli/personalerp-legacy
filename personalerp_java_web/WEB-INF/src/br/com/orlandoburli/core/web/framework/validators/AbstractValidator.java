package br.com.orlandoburli.core.web.framework.validators;

import java.lang.reflect.Field;

import br.com.orlandoburli.core.vo.IValueObject;

public abstract class AbstractValidator {
	
	private IValueObject vo;
	private String fieldName;
	private String fieldDescription;
	private String fieldFocus;
	
	public abstract boolean validate();
	public abstract String getMessage();
	
	public void setVo(IValueObject vo) {
		this.vo = vo;
	}

	public IValueObject getVo() {
		return vo;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
	
	protected Field[] getFields() {
		return this.getVo().getClass().getDeclaredFields();
	}
	
	protected Object getValueField() {
		Field[] fields = getFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				try {
					field.setAccessible(true);
					return field.get(getVo());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	protected Class<?> getClassField() {
		Field[] fields = getFields();
		for (Field field : fields) {
			if (field.getName().equals(fieldName)) {
				try {
					field.setAccessible(true);
					return field.getType();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public void setFieldDescription(String fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	public String getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldFocus(String fieldFocus) {
		this.fieldFocus = fieldFocus;
	}
	public String getFieldFocus() {
		return fieldFocus==null?fieldName:fieldFocus.trim().equals("")?fieldName:fieldFocus;
	}	
}