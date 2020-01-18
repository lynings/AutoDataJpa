package pers.lyning.autodatajpa.core.example;

import lombok.*;
import pers.lyning.autodatajpa.core.annotaion.OrderBy;
import pers.lyning.autodatajpa.core.annotaion.Sortable;
import pers.lyning.autodatajpa.core.page.SortEnum;

/**
 * @author lyning
 */
@Getter
@Builder
@AllArgsConstructor
public class SortQuery {

    /**
     * 格式：api/customers?direction=age.ASC,createdDate.DESC,score.DESC
     */
    @Sortable(classes = SortField.class)
    private final String sort;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SortField {
        @OrderBy(alias = "ages")
        private SortEnum age;

        @OrderBy
        private SortEnum createdDate;

        @OrderBy
        private SortEnum score;
    }
}
