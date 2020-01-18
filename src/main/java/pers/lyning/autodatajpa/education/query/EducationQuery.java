package pers.lyning.autodatajpa.education.query;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import pers.lyning.autodatajpa.core.annotaion.*;
import pers.lyning.autodatajpa.core.page.Page;
import pers.lyning.autodatajpa.core.page.SortEnum;
import pers.lyning.autodatajpa.education.domain.Education;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lyning
 */
@Setter
@Getter
@Builder
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
public class EducationQuery extends Page {

    @Like(alias = "courses.name")
    private String courseName;

    @NotNull(message = "intro parameter is not null")
    @Like
    private String intro;

    @NotEmpty(message = "name parameter is not null")
    @Equals
    private String name;

    @Like(alias = "news.title")
    private String newsTitle;

    @Sortable(classes = SortColumn.class)
    private String sort;

    @NotNull(message = "type parameter is not null")
    @In(alias = "type")
    private List<Education.Type> types;

    @Setter
    @Getter
    public static class SortColumn {

        @OrderBy
        private SortEnum createdDate;
    }
}
