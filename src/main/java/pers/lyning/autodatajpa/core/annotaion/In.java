package pers.lyning.autodatajpa.core.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否在某个集合的匹配
 *
 * @author lyning
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface In {
    /**
     * 别名，默认是字段名称
     *
     * @return
     */
    String alias() default "";
}
