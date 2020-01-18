package pers.lyning.autodatajpa.education.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import pers.lyning.autodatajpa.AutoDataJpaApplication;
import pers.lyning.autodatajpa.core.page.Page;
import pers.lyning.autodatajpa.core.page.PageResponse;
import pers.lyning.autodatajpa.education.domain.Course;
import pers.lyning.autodatajpa.education.domain.Education;
import pers.lyning.autodatajpa.education.domain.EducationRepository;
import pers.lyning.autodatajpa.education.domain.Logo;
import pers.lyning.autodatajpa.education.query.EducationQuery;
import pers.lyning.autodatajpa.education.vo.EducationVO;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = AutoDataJpaApplication.class)
@AutoConfigureMockMvc
@Transactional
class EducationResourceIT {

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_educations_list_when_query_education_by_keyword() throws Exception {
        // given
        Education education = Education.builder()
                .intro("极客学院是....")
                .logo(new Logo("https://ip:port/jiker-logo.png"))
                .name("极客学院")
                .type(Education.Type.COLLEGE)
                .build();
        List<Course> courses = Arrays.asList(Course.builder().education(education).name("TDD 练功房").build());
        education.setCourses(courses);
        educationRepository.save(education);

        EducationQuery query = EducationQuery.builder()
                .courseName("极客学院")
                .intro("极客")
                .name("极客学院")
                .types(Arrays.asList(Education.Type.COLLEGE))
                .build();
        query.setPageNum(1);
        query.setPageSize(1);
        query.setSort("createdDate.DESC");
        // when
        var requestBuilder = get("/educations")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param(EducationQuery.Fields.courseName, query.getCourseName())
                .param(EducationQuery.Fields.newsTitle, query.getNewsTitle())
                .param(EducationQuery.Fields.name, query.getName())
                .param(EducationQuery.Fields.intro, query.getIntro())
                .param(EducationQuery.Fields.types, query.getTypes().stream().map(Enum::toString).toArray(String[]::new))
                .param(Page.Fields.sort, query.getSort())
                .param(Page.Fields.pageNum, String.valueOf(query.getPageNum()))
                .param(Page.Fields.pageSize, String.valueOf(query.getPageSize()));
        var responseString = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        PageResponse<EducationVO> actualResponse = JSON.parseObject(responseString, new TypeReference<PageResponse<EducationVO>>() {}.getType());
        // then
        EducationVO expectedVoEducation = EducationVO.builder()
                .intro(education.getIntro())
                .logo(education.getLogo().getUrl())
                .name(education.getName())
                .type(education.getType())
                .build();
        PageResponse expectedResponse = PageResponse.builder()
                .totalSize(1)
                .content(Arrays.asList(expectedVoEducation))
                .totalPages(1)
                .last(true)
                .build();
        assertThat(actualResponse)
                .usingDefaultComparator()
                .isEqualToComparingOnlyGivenFields(expectedResponse);
    }
}