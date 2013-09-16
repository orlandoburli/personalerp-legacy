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
 * Annotation para dar precisao a um campo BigDecimal.
 * @author orlandoburli
 */
public @interface Precision {
	int value() default -1;
}