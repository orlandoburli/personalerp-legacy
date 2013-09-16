package br.com.orlandoburli.core.web.framework.validators;

public class CpfCnpjValidator extends AbstractValidator {

	public CpfCnpjValidator(String fieldName) {
		this.setFieldName(fieldName);
	}

	public CpfCnpjValidator(String fieldName, String fieldDescription) {
		this.setFieldName(fieldName);
		this.setFieldDescription(fieldDescription);
	}

	@Override
	public String getMessage() {
		return "CPF / CNPJ Inválido!";
	}

	@Override
	public boolean validate() {
		// ------- Rotina para CPF
		if (getValueField().toString().length() == 11) {
			int d1, d2;
			int digito1, digito2, resto;
			int digitoCPF;
			String nDigResult;
			d1 = d2 = 0;
			digito1 = digito2 = resto = 0;
			for (int n_Count = 1; n_Count < getValueField().toString().length() - 1; n_Count++) {
				digitoCPF = Integer.valueOf(
						getValueField().toString().substring(n_Count - 1, n_Count)).intValue();
				// --------- Multiplique a ultima casa por 2 a seguinte por 3 a
				// seguinte por 4 e assim por diante.
				d1 = d1 + (11 - n_Count) * digitoCPF;
				// --------- Para o segundo digito repita o procedimento
				// incluindo o primeiro digito calculado no passo anterior.
				d2 = d2 + (12 - n_Count) * digitoCPF;
			}
			;
			// --------- Primeiro resto da divis�o por 11.
			resto = (d1 % 11);
			// --------- Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o
			// digito � 11 menos o resultado anterior.
			if (resto < 2)
				digito1 = 0;
			else
				digito1 = 11 - resto;
			d2 += 2 * digito1;
			// --------- Segundo resto da divis�o por 11.
			resto = (d2 % 11);
			// --------- Se o resultado for 0 ou 1 o digito � 0 caso contr�rio o
			// digito � 11 menos o resultado anterior.
			if (resto < 2)
				digito2 = 0;
			else
				digito2 = 11 - resto;
			// --------- Digito verificador do CPF que est� sendo validado.
			String nDigVerific = getValueField().toString().substring(getValueField().toString().length() - 2, getValueField().toString()
					.length());
			// --------- Concatenando o primeiro resto com o segundo.
			nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
			// --------- Comparar o digito verificador do cpf com o primeiro
			// resto + o segundo resto.
			return nDigVerific.equals(nDigResult);
		}
		// -------- Rotina para CNPJ
		else if (getValueField().toString().length() == 14) {
			int soma = 0, dig;
			String cnpj_calc = getValueField().toString().substring(0, 12);
			char[] chr_cnpj = getValueField().toString().toCharArray();
			// --------- Primeira parte
			for (int i = 0; i < 4; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
					soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			// --------- Segunda parte
			soma = 0;
			for (int i = 0; i < 5; i++)
				if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
					soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
			for (int i = 0; i < 8; i++)
				if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
					soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
			dig = 11 - (soma % 11);
			cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
			return getValueField().toString().equals(cnpj_calc);
		} else {
			return false;
		}
	}
}