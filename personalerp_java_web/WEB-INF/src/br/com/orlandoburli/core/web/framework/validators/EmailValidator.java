package br.com.orlandoburli.core.web.framework.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator extends AbstractValidator {

	public EmailValidator(String fieldName) {
		this.setFieldName(fieldName);
	}

	public EmailValidator(String fieldName, String fieldDescription) {
		this.setFieldName(fieldName);
		this.setFieldDescription(fieldDescription);
	}

	@Override
	public String getMessage() {
		return "Email inválido";
	}

	@Override
	public boolean validate() {
		if (this.getValueField() == null || this.getValueField().toString().equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(this.getValueField().toString());
		return (m.find());
	}
}