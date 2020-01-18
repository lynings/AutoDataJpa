package pers.lyning.autodatajpa.core.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pers.lyning.autodatajpa.core.LikeStrategyConstant;
import pers.lyning.autodatajpa.core.annotaion.Like;

/**
 * @author lyning
 */
@Getter
@Builder
@AllArgsConstructor
public class LikeQuery {

    @Like(strategy = LikeStrategyConstant.LEFT)
    private final String leftName;

    @Like(alias = "fullName")
    private final String name;

    @Like(strategy = LikeStrategyConstant.RIGHT)
    private final String rightName;

    @Like
    private final String title;
}
