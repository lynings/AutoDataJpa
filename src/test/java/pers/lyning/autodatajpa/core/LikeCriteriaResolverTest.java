package pers.lyning.autodatajpa.core;

import lombok.var;
import org.junit.jupiter.api.Test;
import pers.lyning.autodatajpa.core.annotaion.Like;
import pers.lyning.autodatajpa.core.example.EqualsQuery;
import pers.lyning.autodatajpa.core.example.LikeQuery;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author lyning
 */
class LikeCriteriaResolverTest {

    @Test
    void should_return_empty_when_not_contain_like_annotation() throws Exception {
        // given
        var equalsQuery = new EqualsQuery("yuning", "li");
        // when
        List<Criteria> actualCriteriaList = new LikeCriteriaResolver(equalsQuery).resolve();
        // then
        assertThat(actualCriteriaList).isEmpty();
    }

    @Test
    void should_return_like_criteria_when_contain_like_annotation() throws Exception {
        // given
        var likeQuery = LikeQuery.builder()
                .leftName("left")
                .name("李")
                .rightName("right")
                .title("极客学院")
                .build();
        // when
        List<Criteria> actualCriteriaList = new LikeCriteriaResolver(likeQuery).resolve();
        // then
        List<Criteria> expectedCriteriaList = Arrays.asList(
                Criteria.builder()
                        .annotation(Like.class)
                        .column("leftName")
                        .value("%left")
                        .build(),
                Criteria.builder()
                        .annotation(Like.class)
                        .column("fullName")
                        .value("%李%")
                        .build(),
                Criteria.builder()
                        .annotation(Like.class)
                        .column("rightName")
                        .value("right%")
                        .build(),
                Criteria.builder()
                        .annotation(Like.class)
                        .column("title")
                        .value("%极客学院%")
                        .build()
        );
        assertThat(actualCriteriaList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedCriteriaList);
    }
}