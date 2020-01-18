package pers.lyning.autodatajpa.core.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 排序别名注解
 *
 * @author lyning
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrderBy {

    /**
     * 请求参数映射名称，默认是字段名称
     * <p>
     * 例如：
     * 前端请求 ?direction=createTime.desc,integral.desc
     * \@Setter
     * \@Getter
     * class Foo {
     * \@Sortable
     * private Sort direction;
     * <p>
     * public static class Sort {
     * \@OrderBy(alias = "create_time")
     * private SortEnum createTime;
     * \@OrderBy
     * private SortEnum integral;
     * }
     * }
     * </p>
     *
     * @return
     */
    String alias() default "";
}
