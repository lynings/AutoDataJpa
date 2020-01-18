package pers.lyning.autodatajpa.core;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import pers.lyning.autodatajpa.core.annotaion.Equals;
import pers.lyning.autodatajpa.core.annotaion.In;
import pers.lyning.autodatajpa.core.annotaion.Like;
import pers.lyning.autodatajpa.core.page.Page;
import pers.lyning.autodatajpa.core.page.SortEnum;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * query dsl Predicate wrapper
 * <p>
 * example:
 * BooleanExpression booleanExpression = new JpaCriteriaHelper<>(Patient.class).wrapper(query);
 *
 * @author lyning
 */
public class JpaCriteriaHelper {

    public static Pageable asPageable(Page page) {
        Sort sort = asSort(page);
        return PageRequest.of(page.getPageNum() - 1, page.getPageSize(), sort);
    }

    public static Sort asSort(Object instance) {
        List<Criteria> criteriaList = new SortCriteriaResolver(instance).resolve();
        List<Sort.Order> orders = criteriaList.stream()
                .map(criteria -> {
                    if (SortEnum.DESC.getValue().equals(criteria.getValue().toString())) {
                        return Sort.Order.desc(criteria.getColumn());
                    }
                    return Sort.Order.asc(criteria.getColumn());
                })
                .collect(toList());
        return Sort.by(orders);
    }

    public static <T> Specification<T> asSpecification(Object instance) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Criteria> criteriaList = new EqualsCriteriaResolver(instance).resolve();
            criteriaList.addAll(new InCriteriaResolver(instance).resolve());
            criteriaList.addAll(new LikeCriteriaResolver(instance).resolve());
            JpaPath<T> jpaPath = new JpaPath<>(root);
            List<Predicate> predicates = criteriaList.stream()
                    .map(criteria -> {
                        List<String> columns = Arrays.asList(criteria.getColumn().split("\\."));
                        if (criteria.getAnnotation() == Like.class) {
                            return criteriaBuilder.like(jpaPath.extractPath(columns), criteria.getValue().toString());
                        }
                        if (criteria.getAnnotation() == In.class) {
                            CriteriaBuilder.In in = criteriaBuilder.in(jpaPath.extractPath(columns));
                            List<Object> values = (List<Object>) criteria.getValue();
                            for (Object value : values) {
                                in.value(value);
                            }
                            return in;
                        }
                        if (criteria.getAnnotation() == Equals.class) {
                            return criteriaBuilder.equal(jpaPath.extractPath(columns), criteria.getValue().toString());
                        }
                        throw new UnsupportedOperationException("unsupported operation exception!");
                    })
                    .collect(toList());
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
