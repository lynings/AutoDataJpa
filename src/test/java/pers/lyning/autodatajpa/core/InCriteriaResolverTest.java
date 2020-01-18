package pers.lyning.autodatajpa.core;

import lombok.var;
import org.junit.jupiter.api.Test;
import pers.lyning.autodatajpa.core.annotaion.In;
import pers.lyning.autodatajpa.core.example.EqualsQuery;
import pers.lyning.autodatajpa.core.example.InQuery;
import pers.lyning.autodatajpa.education.domain.Education;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lyning
 */
class InCriteriaResolverTest {

    @Test
    void should_return_in_criteria_when_contain_in_annotation() throws Exception {
        // given
        var inQuery = new InQuery(Arrays.asList(1, 2), Arrays.asList(Education.Type.COLLEGE, Education.Type.ENTERPRISE));
        // when
        List<Criteria> actualCriteriaList = new InCriteriaResolver(inQuery).resolve();
        // then
        List<Criteria> expectedCriteriaList = Arrays.asList(
                Criteria.builder()
                        .annotation(In.class)
                        .column("age")
                        .value(Arrays.asList(1, 2))
                        .build(),
                Criteria.builder()
                        .annotation(In.class)
                        .column("type")
                        .value(Arrays.asList(Education.Type.COLLEGE, Education.Type.ENTERPRISE))
                        .build()
        );
        assertThat(actualCriteriaList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedCriteriaList);
    }

    @Test
    void should_return_empty_when_not_contain_in_annotation() throws Exception {
        // given
        var equalsQuery = new EqualsQuery("yuning", "li");
        // when
        List<Criteria> actualCriteriaList = new InCriteriaResolver(equalsQuery).resolve();
        // then
        assertThat(actualCriteriaList).isEmpty();
    }

}