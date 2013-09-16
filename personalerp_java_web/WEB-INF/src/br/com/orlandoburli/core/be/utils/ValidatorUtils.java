package br.com.orlandoburli.core.be.utils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.orlandoburli.core.be.exceptions.persistence.SalvarBeException;
import br.com.orlandoburli.core.validators.Cpf;
import br.com.orlandoburli.core.validators.Email;
import br.com.orlandoburli.core.validators.FullTrim;
import br.com.orlandoburli.core.validators.Lower;
import br.com.orlandoburli.core.validators.MaxSize;
import br.com.orlandoburli.core.validators.MinSize;
import br.com.orlandoburli.core.validators.NotEmpty;
import br.com.orlandoburli.core.validators.Upper;
import br.com.orlandoburli.core.vo.Description;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.web.framework.validators.CpfCnpjValidator;

public final class ValidatorUtils {

	public static void validateVo(IValueObject vo) throws SalvarBeException {

		if (vo == null) {
			return;
		}

		try {
			for (Field f : vo.getClass().getDeclaredFields()) {
				f.setAccessible(true);
				Object value = f.get(vo);

				Description description = f.getAnnotation(Description.class);

				// @FullTrim
				FullTrim fullTrim = f.getAnnotation(FullTrim.class);
				if (fullTrim != null) {
					if (value instanceof String && value != null) {
						String s = (String) value;
						s = s.trim();
						while (s.indexOf("  ") >= 0) {
							s = s.replace("  ", " ");
						}
						f.set(vo, s);
					}
				}

				// @NotEmpty
				NotEmpty notEmpty = f.getAnnotation(NotEmpty.class);
				if (notEmpty != null) {
					if (value == null) {
						if (description == null) {
							throw new SalvarBeException("Campo Obrigatório não preenchido!", f.getName());
						} else {
							throw new SalvarBeException("Campo " + description.value() + " é obrigatório!", f.getName());
						}
					} else {
						if (value instanceof String) {
							if (value.toString().trim().length() == 0) {
								if (description == null) {
									throw new SalvarBeException("Campo Obrigatório não preenchido!", f.getName());
								} else {
									throw new SalvarBeException("Campo " + description.value() + " é obrigatório!", f.getName());
								}
							}
						}
					}
				}

				// @MinSize
				MinSize minSize = f.getAnnotation(MinSize.class);
				if (minSize != null) {
					if (value == null) {
						if (description == null) {
							throw new SalvarBeException("Campo tem o tamanho mínimo de " + minSize.value() + "!", f.getName());
						} else {
							throw new SalvarBeException("Campo " + description.value() + " tem o tamanho mínimo de " + minSize.value() + "!", f.getName());
						}
					} else {
						String value2 = value.toString();
						if (value2.toString().trim().length() < minSize.value()) {
							if (description == null) {
								throw new SalvarBeException("Campo tem o tamanho mínimo de " + minSize.value() + "!", f.getName());
							} else {
								throw new SalvarBeException("Campo " + description.value() + " tem o tamanho mínimo de " + minSize.value() + "!", f.getName());
							}
						}
					}
				}

				// @Email
				Email email = f.getAnnotation(Email.class);
				if (email != null) {
					if (value != null && value instanceof String) {
						Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
						Matcher m = p.matcher((String) value);
						if (!m.find()) {
							if (description == null) {
								throw new SalvarBeException("Campo de email inválido!", f.getName());
							} else {
								throw new SalvarBeException("Campo " + description.value() + " não é um email válido!", f.getName());
							}
						}
					}
				}

				Cpf cpf = f.getAnnotation(Cpf.class);
				if (cpf != null) {
					if (value != null && value instanceof String) {
						CpfCnpjValidator val = new CpfCnpjValidator(f.getName());
						val.setVo(vo);
						if (!val.validate()) {
							if (description == null) {
								throw new SalvarBeException("Campo de Cpf inválido!", f.getName());
							} else {
								throw new SalvarBeException("Campo " + description.value() + " não é um cpf válido!", f.getName());
							}
						}
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static void transformVo(IValueObject vo) {
		try {
			for (Field f : vo.getClass().getDeclaredFields()) {
				f.setAccessible(true);

				Object value = f.get(vo);

				Upper upper = f.getAnnotation(Upper.class);
				if (upper != null) {
					if (value instanceof String && value != null) {
						String s = (String) value;
						s = s.toUpperCase();
						f.set(vo, s);
					}
				}

				Lower lower = f.getAnnotation(Lower.class);
				if (lower != null) {
					if (value instanceof String && value != null) {
						String s = (String) value;
						s = s.toLowerCase();
						f.set(vo, s);
					}
				}

				MaxSize maxSize = f.getAnnotation(MaxSize.class);
				if (maxSize != null) {
					if (value instanceof String && value != null) {
						String s = (String) value;
						if (s.length() >= maxSize.value()) {
							s = s.substring(0, maxSize.value());
						}
						f.set(vo, s);
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}