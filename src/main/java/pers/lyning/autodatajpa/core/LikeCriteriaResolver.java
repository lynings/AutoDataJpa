package pers.lyning.autodatajpa.core;

import pers.lyning.autodatajpa.core.annotaion.Like;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author lyning
 */
class LikeCriteriaResolver {
    private final Object instance;

    public LikeCriteriaResolver(Object instance) {this.instance = instance;}


    public List<Criteria> resolve() {
        return Arrays.stream(instance.getClass().getDeclaredFields())
                .map(this::asCriteria)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Optional<Criteria> asCriteria(Field field) {
        return Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == Like.class)
                .findFirst()
                .map(annotation -> {
                    Like like = (Like) annotation;
                    String columnName = like.alias().trim().isEmpty()
                            ? field.getName()
                            : like.alias().trim();
                    Object returnValue = new GetMethod(instance, field.getName())
                            .invoke();
                    if (Objects.isNull(returnValue)) {
                        return null;
                    }
                    String value = returnValue.toString();
                    if (like.strategy().equals(LikeStrategyConstant.LEFT)) {
                        value = String.format("%s%s", "%", value);
                    } else if (like.strategy().equals(LikeStrategyConstant.RIGHT)) {
                        value = String.format("%s%s", value, "%");
                    } else {
                        value = String.format("%s%s%s", "%", value, "%");
                    }
                    return Criteria.builder()
                            .annotation(Like.class)
                            .column(columnName)
                            .value(value)
                            .build();
                });
    }
}
