package pers.lyning.autodatajpa.core.annotaion;

import pers.lyning.autodatajpa.core.LikeStrategyConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明模糊查询注解
 *
 * @author lyning
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Like {

    /**
     * 别名，默认是字段名称
     *
     * @return
     */
    String alias() default "";

    /**
     * 策略，具体参考 LikeStrategyConstant.java
     *
     * @return
     */
    String strategy() default LikeStrategyConstant.DEFAULT;
}
