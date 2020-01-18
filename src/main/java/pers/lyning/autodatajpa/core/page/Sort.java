package pers.lyning.autodatajpa.core.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 排序实体
 *
 * @author lyning
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sort implements Serializable {

    private SortEnum direction;

    private String field;
}
