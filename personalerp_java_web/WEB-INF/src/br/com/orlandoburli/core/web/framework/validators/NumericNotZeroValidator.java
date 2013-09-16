package br.com.orlandoburli.core.web.framework.validators;

import java.math.BigDecimal;

public class NumericNotZeroValidator extends AbstractValidator {

	public NumericNotZeroValidator(String fieldName) {
		setFieldName(fieldName);
	}
	
	public NumericNotZeroValidator(String fieldName, String fieldDescription) {
		this.setFieldName(fieldName);
		this.setFieldDescription(fieldDescription);
	}
	
	public NumericNotZeroValidator(String fieldName, String fieldDescription, String fieldFocus) {
		this.setFieldName(fieldName);
		this.setFieldDescription(fieldDescription);
		this.setFieldFocus(fieldFocus);
	}
	
	@Override
	public String getMessage() {
		if (getFieldDescription() != null && !getFieldDescription().trim().equals("")) {
			return "Campo " + getFieldDescription() + " deve ser maior que zero!";
		} else {
			return "Campo " + getFieldName() + " deve ser maior que zero!";
		}
	}

	@Override
	public boolean validate() {
		if (getValueField() == null) {
			return false;
		} else if (getValueField() instanceof Integer) {
			Integer integer = (Integer) getValueField();
			if (integer <= 0) {
				return false;
			}
		} else if (getValueField() instanceof BigDecimal) {
			BigDecimal decimal = (BigDecimal) getValueField();
			if (decimal.compareTo(BigDecimal.ZERO) <= 0) {
				return false;
			}
		}
		return true;
	}

}
