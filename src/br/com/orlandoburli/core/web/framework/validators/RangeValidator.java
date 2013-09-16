package br.com.orlandoburli.core.web.framework.validators;

public class RangeValidator extends AbstractValidator {

	private Double MinValue;
	private Double MaxValue;
	
	
	public RangeValidator(String fieldName, String fieldDescription, Double MinValue, Double MaxValue) {
		setFieldName(fieldName);
		setFieldDescription(fieldDescription);
		setMinValue(MinValue);
		setMaxValue(MaxValue);
	}
		
	@Override
	public boolean validate() {
		double value = Double.parseDouble(getValueField().toString());
		if (MinValue != null && value <= MinValue) {
			return false;
		}
		if (MaxValue != null && value >= MaxValue) {
			return false;
		}
		return true;
	}
	
	@Override
	public String getMessage() {
		if (MinValue != null && MaxValue != null) {
			return "O campo " + getFieldName() + " deve ser entre " + getMinValue() + " e " + getMaxValue() + "! ";
		} else if (MinValue != null && MaxValue == null) {
			return "O campo " + getFieldName() + " deve ser maior que " + getMinValue() + "!";
		} else if (MaxValue != null && MinValue == null) {
			return "O campo " + getFieldName() + " deve ser menor que " + getMaxValue() + "!";
		}
		return "Validador inválido!";
	}
	public void setMinValue(Double minValue) {
		MinValue = minValue;
	}
	public Double getMinValue() {
		return MinValue;
	}
	public void setMaxValue(Double maxValue) {
		MaxValue = maxValue;
	}
	public Double getMaxValue() {
		return MaxValue;
	}
}