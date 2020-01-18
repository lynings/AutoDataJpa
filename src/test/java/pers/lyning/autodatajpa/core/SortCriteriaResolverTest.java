package pers.lyning.autodatajpa.core;

import lombok.var;
import org.junit.jupiter.api.Test;
import pers.lyning.autodatajpa.core.annotaion.OrderBy;
import pers.lyning.autodatajpa.core.example.SortQuery;
import pers.lyning.autodatajpa.core.page.SortEnum;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lyning
 */
class SortCriteriaResolverTest {

    @Test
    void should_exclude_sort_parameter_when_not_declare_OrderBy_annotation() throws Exception {
        // given   height not declare @OrderBy annotation
        var sortQuery = SortQuery.builder()
                .sort("age.ASC,height.ASC,createdDate.DESC,score.DESC")
                .build();
        // when
        List<Criteria> actualCriteriaList = new SortCriteriaResolver(sortQuery).resolve();
        // then
        List<Criteria> expectedCriteriaList = Arrays.asList(
                Criteria.builder()
                        .annotation(OrderBy.class)
                        .column("ages")
                        .value(SortEnum.ASC)
                        .build(),
                Criteria.builder()
                        .annotation(OrderBy.class)
                        .column("score")
                        .value(SortEnum.DESC)
                        .build(),
                Criteria.builder()
                        .annotation(OrderBy.class)
                        .column("createdDate")
                        .value(SortEnum.DESC)
                        .build()
        );
        assertThat(actualCriteriaList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedCriteriaList);
    }

    @Test
    void should_return_empty_when_sort_is_null() throws Exception {
        // given
        var sortQuery = new SortQuery(null);
        // when
        List<Criteria> actualCriteriaList = new SortCriteriaResolver(sortQuery).resolve();
        // then
        assertThat(actualCriteriaList).isEmpty();
    }

    @Test
    void should_return_sort_criteria_when_contain_sort_field_and_order_by_annotation() throws Exception {
        // given
        var sortQuery = SortQuery.builder()
                .sort("age.ASC,createdDate.DESC,score.DESC")
                .build();
        // when
        List<Criteria> actualCriteriaList = new SortCriteriaResolver(sortQuery).resolve();
        // then
        List<Criteria> expectedCriteriaList = Arrays.asList(
                Criteria.builder()
                        .annotation(OrderBy.class)
                        .column("ages")
                        .value(SortEnum.ASC)
                        .build(),
                Criteria.builder()
                        .annotation(OrderBy.class)
                        .column("score")
                        .value(SortEnum.DESC)
                        .build(),
                Criteria.builder()
                        .annotation(OrderBy.class)
                        .column("createdDate")
                        .value(SortEnum.DESC)
                        .build()
        );
        assertThat(actualCriteriaList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedCriteriaList);
    }
}
