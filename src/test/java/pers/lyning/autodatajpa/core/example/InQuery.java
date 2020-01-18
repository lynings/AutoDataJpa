package pers.lyning.autodatajpa.core.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pers.lyning.autodatajpa.core.annotaion.In;
import pers.lyning.autodatajpa.education.domain.Education;

import java.util.List;

/**
 * @author lyning
 */
@Setter
@Getter
@AllArgsConstructor
public class InQuery {

    @In(alias = "age")
    private final List<Integer> ages;

    @In(alias = "type")
    private final List<Education.Type> types;
}
