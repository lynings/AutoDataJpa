package pers.lyning.autodatajpa.core;

import pers.lyning.autodatajpa.core.annotaion.In;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author lyning
 */
class InCriteriaResolver {
    private final Object instance;

    public InCriteriaResolver(Object instance) {
        this.instance = instance;
    }

    public List<Criteria> resolve() {
        return Arrays.stream(instance.getClass().getDeclaredFields())
                .map(this::asCriteria)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Optional<Criteria> asCriteria(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == In.class)
                .findFirst()
                .map(annotation -> {
                    In in = (In) annotation;
                    String columnName = in.alias().trim().isEmpty()
                            ? field.getName()
                            : in.alias().trim();
                    return Criteria.builder()
                            .annotation(In.class)
                            .column(columnName)
                            .value(new GetMethod(instance, field.getName()).invoke())
                            .build();
                });
    }
}
