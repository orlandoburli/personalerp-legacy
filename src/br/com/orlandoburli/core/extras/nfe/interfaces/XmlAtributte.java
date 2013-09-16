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
 * Annotation para sobrescrever o nome do atributo a ser gerado.
 */
public @interface XmlAtributte {
	String value();
}
