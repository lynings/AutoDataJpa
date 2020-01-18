package pers.lyning.autodatajpa.core;

import pers.lyning.autodatajpa.core.annotaion.Equals;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author lyning
 */
class EqualsCriteriaResolver {
    private final Object instance;

    public EqualsCriteriaResolver(Object instance) {
        this.instance = instance;
    }

    public List<Criteria> resolve() {
        return Arrays.stream(instance.getClass().getDeclaredFields())
                .map(this::extractAsCriteria)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Optional<Criteria> extractAsCriteria(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == Equals.class)
                .findFirst()
                .map(annotation -> {
                    Equals equals = (Equals) annotation;
                    String fieldName = equals.alias().trim().isEmpty()
                            ? field.getName()
                            : equals.alias().trim();
                    return Criteria.builder()
                            .annotation(Equals.class)
                            .column(fieldName)
                            .value(new GetMethod(instance, field.getName()).invoke())
                            .build();
                });
    }
}

