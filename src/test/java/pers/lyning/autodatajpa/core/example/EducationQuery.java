package pers.lyning.autodatajpa.core.example;

import lombok.*;
import pers.lyning.autodatajpa.core.annotaion.*;
import pers.lyning.autodatajpa.core.page.SortEnum;
import pers.lyning.autodatajpa.education.domain.Education;

import java.util.List;

/**
 * @author lyning
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationQuery {

    @Equals(alias = "courses.name")
    private String courseName;

    @Equals(alias = "courses.education.name")
    private String educationName;

    @Equals
    private Integer id;

    @Like
    private String name;

    @Equals(alias = "news.title")
    private String newsTitle;

    @Sortable(classes = SortField.class)
    private String sort;

    @In(alias = "type")
    private List<Education.Type> types;

    @Getter
    @Setter
    public static class SortField {
        @OrderBy
        private SortEnum age;

        @OrderBy
        private SortEnum createdDate;
    }
}