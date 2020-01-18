package pers.lyning.autodatajpa.core.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pers.lyning.autodatajpa.core.annotaion.Equals;

/**
 * @author lyning
 */
@Setter
@Getter
@AllArgsConstructor
public class EqualsQuery {

    @Equals(alias = "secondName")
    private final String lastName;

    @Equals
    private final String firstName;
}
