package br.com.orlandoburli.core.extras.nfe.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
/**
 * Annotation de formata��o para numeros<br/> 
 * na gera��o do XML da NF-e.
 */
public @interface INumberFormat {

	public abstract String maskNumber();
}
