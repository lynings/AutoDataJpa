package pers.lyning.autodatajpa.core;

import lombok.var;
import org.junit.jupiter.api.Test;
import pers.lyning.autodatajpa.core.annotaion.Equals;
import pers.lyning.autodatajpa.core.example.EqualsQuery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lyning
 */
class EqualsCriteriaResolverTest {


    @Test
    void should_return_equals_criteria_when_contain_equals_annotation() throws Exception {
        // given
        var equalsQuery = new EqualsQuery("yuning", "li");
        // when
        List<Criteria> actualCriteriaList = new EqualsCriteriaResolver(equalsQuery).resolve();
        // then
        List<Criteria> expectedCriteriaList = Arrays.asList(
                Criteria.builder()
                        .annotation(Equals.class)
                        .column("firstName")
                        .value("li")
                        .build(),
                Criteria.builder()
                        .annotation(Equals.class)
                        .column("secondName")
                        .value("yuning")
                        .build()
        );
        assertThat(actualCriteriaList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedCriteriaList);
    }

    @Test
    void should_return_empty_when_not_contain_equals_annotation() throws Exception {
        // given
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", "li");
        map.put("lastName", "yuning");
        // when
        List<Criteria> actualCriteriaList = new EqualsCriteriaResolver(map).resolve();
        // then
        assertThat(actualCriteriaList).isEmpty();
    }

}