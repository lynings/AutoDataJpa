package pers.lyning.autodatajpa.education.vo;

import lombok.*;
import pers.lyning.autodatajpa.education.domain.Education;

/**
 * @author lyning
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationVO {

    private String intro;

    private String logo;

    private String name;

    private Education.Type type;
}
