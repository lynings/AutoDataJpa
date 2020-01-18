package pers.lyning.autodatajpa.core.page;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 分页请求对象
 *
 * @author lyning
 */
@Setter
@Getter
@FieldNameConstants
public class Page {

    /**
     * 页码
     * 例如: 1
     */
    protected int pageNum = 1;

    /**
     * 每页记录条数
     * 例如: 20
     */
    protected int pageSize = 20;

    /**
     * 排序参数
     * 例如 /users?direction=createTime.desc,integral.desc
     */
    protected String sort;

    public Page() {
    }

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Page(int pageNum, int pageSize, String sort) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public Page addSort(String column, SortEnum sort) {
        if (Objects.nonNull(this.getSort())) {
            this.setSort(this.getSort() + ",");
        } else {
            this.setSort("");
        }
        this.setSort(this.getSort() + column + "." + sort.getValue());
        return this;
    }

    public List<Sort> toSortList() {
        List<Sort> sorts = null;
        if (Objects.isNull(this.getSort())) {
            return new ArrayList<>();
        } else {
            sorts = Arrays.stream(this.getSort().split(","))
                    .filter(Objects::nonNull)
                    .map(sort -> {
                        String[] sortSplit = sort.split("\\.");
                        String field = sortSplit[0];
                        String direction = sortSplit[1];
                        return new Sort(SortEnum.valueOf(direction.toUpperCase()), field);
                    })
                    .collect(Collectors.toList());
            return sorts;
        }
    }
}
