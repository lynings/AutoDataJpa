package pers.lyning.autodatajpa.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.annotation.Annotation;

/**
 * @author lyning
 */
@Getter
@Builder
@AllArgsConstructor
public class Criteria {
    private Class<?> annotation;

    private String column;

    private Object value;
}
