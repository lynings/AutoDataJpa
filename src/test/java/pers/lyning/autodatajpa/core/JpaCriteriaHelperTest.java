package pers.lyning.autodatajpa.core;

import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import pers.lyning.autodatajpa.core.example.EducationQuery;
import pers.lyning.autodatajpa.education.domain.Education;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JpaCriteriaHelperTest {

    @Test
    void should_return_jpa_specification_when_given_instance() throws Exception {
        // given
        var educationQuery = EducationQuery.builder()
                .id(1)
                .newsTitle("新闻")
                .courseName("TDD")
                .educationName("TDD")
                .name("赵")
                .sort("createdDate.DESC")
                .types(Arrays.asList(Education.Type.COLLEGE, Education.Type.ENTERPRISE))
                .build();
        // when
        Specification<Education> specification = JpaCriteriaHelper.asSpecification(educationQuery);
        // then
        assertThat(specification).isNotNull();
    }
}
