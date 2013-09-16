package br.com.orlandoburli.core.vo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
/**
 * Annotation para fazer um sub-select ou mesmo um cálculo dinamico em SQL. 
 * @author orlandoburli
 *
 */
public @interface Formula {
	String value();
}