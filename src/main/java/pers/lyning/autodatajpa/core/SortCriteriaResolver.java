package pers.lyning.autodatajpa.core;

import pers.lyning.autodatajpa.core.annotaion.OrderBy;
import pers.lyning.autodatajpa.core.annotaion.Sortable;
import pers.lyning.autodatajpa.core.page.SortEnum;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author lyning
 */
class SortCriteriaResolver {
    private final Object instance;

    public SortCriteriaResolver(Object instance) {this.instance = instance;}

    public List<Criteria> resolve() {
        return Arrays.stream(instance.getClass().getDeclaredFields())
                .map(this::asCriteria)
                .flatMap(Collection::stream)
                .collect(toList());
    }

    private List<Criteria> asCriteria(Field field) {
        Optional<Annotation> optionalAnnotation = Arrays.stream(field.getDeclaredAnnotations())
                .filter(annotation -> annotation.annotationType() == Sortable.class)
                .findFirst();

        return optionalAnnotation
                .map(annotation -> {
                    Sortable sortable = (Sortable) annotation;
                    Object returnValue = new GetMethod(instance, field.getName())
                            .invoke();
                    if (Objects.isNull(returnValue)) {
                        return null;
                    }
                    String sortString = (String) returnValue;
                    Map<String, String> fieldNameToAliasMap = asFieldNameToAliasMap(sortable.classes());
                    return Arrays.stream(sortString.split(sortable.separator()))
                            .filter(sort -> {
                                // 忽略掉 后端 没有开放 的 排序字段
                                String fieldName = sort.split(sortable.join())[0];
                                return fieldNameToAliasMap.containsKey(fieldName);
                            })
                            .map(sort -> {
                                String[] sortSplit = sort.split(sortable.join());
                                return Criteria.builder()
                                        .annotation(OrderBy.class)
                                        .column(fieldNameToAliasMap.get(sortSplit[0]))
                                        .value(SortEnum.valueOf(sortSplit[1].toUpperCase()))
                                        .build();
                            })
                            .collect(toList());
                })
                .orElse(new ArrayList<>());
    }

    private Map<String, String> asFieldNameToAliasMap(Class<?> classes) {
        Map<String, String> fieldNameToAliasMap = new HashMap<>();
        for (Field field : classes.getDeclaredFields()) {
            Optional<Annotation> annotationOptional = Arrays.stream(field.getDeclaredAnnotations())
                    .filter(annotation -> annotation.annotationType() == OrderBy.class)
                    .findFirst();
            if (!annotationOptional.isPresent()) {
                continue;
            }
            OrderBy orderBy = (OrderBy) annotationOptional.get();
            String alias = orderBy.alias().trim();
            fieldNameToAliasMap.put(field.getName(), alias.isEmpty() ? field.getName() : alias);
        }
        return fieldNameToAliasMap;
    }
}
