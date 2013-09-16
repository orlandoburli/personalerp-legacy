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
 * Annotation para identificar o campo, usado para as validações automáticas.
 * @author orlandoburli
 *
 */
public @interface Description {

	public String value();
}