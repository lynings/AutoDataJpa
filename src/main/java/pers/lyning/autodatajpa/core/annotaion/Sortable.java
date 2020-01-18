package pers.lyning.autodatajpa.core.annotaion;

/**
 * 声明可排序字段
 *
 * @author lyning
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Sortable {

    Class<?> classes();

    String join() default "\\.";

    String separator() default ",";
}
