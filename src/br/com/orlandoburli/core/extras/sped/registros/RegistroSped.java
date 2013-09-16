package br.com.orlandoburli.core.extras.sped.registros;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatDateSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatNumberSped;

/**
 * Classe abstrata para todos os registros do arquivo SPED
 * 
 * @author Orlando
 */
public abstract class RegistroSped {

	/**
	 * Identificação do tipo de registro
	 * 
	 * @return Tipo de Registro
	 */
	public abstract String getRegistro();

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("|"); // Inicio da linha

		Method[] methods = this.getClass().getMethods();

		Arrays.sort(methods, new Comparator<Method>() {

			@Override
			public int compare(Method o1, Method o2) {
				CampoSped campoSped1 = o1.getAnnotation(CampoSped.class);
				CampoSped campoSped2 = o2.getAnnotation(CampoSped.class);

				if (campoSped1 != null && campoSped2 != null) {
					return campoSped1.order() - campoSped2.order();
				} else if (campoSped1 != null && campoSped2 == null) {
					return -1;
				} else if (campoSped1 == null && campoSped2 != null) {
					return 1;
				}
				return o1.getName().compareTo(o2.getName());
			}
		});

		for (Method method : methods) {
			if (!method.isAccessible()) {
				method.setAccessible(true);
			}

			CampoSped campoSped = method.getAnnotation(CampoSped.class);

			if (campoSped != null) {

				// Deve ser um metodo sem parametros, do tipo GET, e não pode
				// ser VOID
				if (!method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0) {
					try {
						Object retorno = method.invoke(this, new Object[] {});

						if (retorno != null) {

							if (retorno instanceof Date) {

								FormatDateSped formatDateSped = method.getAnnotation(FormatDateSped.class);

								if (formatDateSped != null) {
									SimpleDateFormat sdf = new SimpleDateFormat(formatDateSped.value());
									sb.append(sdf.format(retorno));
								} else {
									sb.append(retorno.toString());
								}

							} else if (retorno instanceof BigDecimal) {

								FormatNumberSped formatNumberSped = method.getAnnotation(FormatNumberSped.class);

								if (formatNumberSped != null) {

									String dec = "";
									for (int i = 0; i < formatNumberSped.value(); i++) {
										dec += "0";
									}
									if (dec.length() > 0) {
										dec = "." + dec;
									}
									DecimalFormat df = new DecimalFormat("0" + dec);

									sb.append(df.format(retorno));
								} else {
									sb.append(retorno.toString());
								}

							} else {
								sb.append(retorno.toString());
							}
						}
						sb.append("|"); // Proximo campo
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return sb.toString();
	}
}