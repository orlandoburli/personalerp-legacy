package br.com.orlandoburli.core.web.framework.validators;

public class NotEmptyValidator extends AbstractValidator {

	public NotEmptyValidator(String fieldName) {
		this.setFieldName(fieldName);
	}
	
	public NotEmptyValidator(String fieldName, String fieldDescription) {
		this.setFieldName(fieldName);
		this.setFieldDescription(fieldDescription);
	}
	
	public NotEmptyValidator(String fieldName, String fieldDescription, String fieldFocus) {
		this.setFieldName(fieldName);
		this.setFieldDescription(fieldDescription);
		this.setFieldFocus(fieldFocus);
	}

	@Override
	public boolean validate() {
		if (getValueField() == null) {
			return false;
		}
		if (getClassField().equals(String.class) && getValueField().toString().trim().equals("")) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getMessage() {
		if (getFieldDescription() != null && !getFieldDescription().trim().equals("")) {
			return "Campo " + getFieldDescription() + " é obrigatório!";
		} else {
			return "Campo " + getFieldName() + " é obrigatório!";
		}
	}
}