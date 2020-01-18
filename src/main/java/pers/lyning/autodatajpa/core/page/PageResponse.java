package pers.lyning.autodatajpa.core.page;

import lombok.*;
import org.springframework.data.domain.PageImpl;

import java.util.List;

/**
 * 分页返回值对象
 *
 * @author lyning
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> extends Page {

    /**
     * 总条数
     */
    private long totalSize;

    /**
     * 数据列表
     */
    private List<T> content;

    /**
     * 总页数
     */
    private int totalPages;

    /**
     * 是否是最后一页
     * true or false
     */
    private boolean last;

    private PageResponse(final PageImpl<T> page) {
        this.setTotalSize(page.getTotalElements());
        this.setContent(page.getContent());
        this.setTotalPages(page.getTotalPages());
        this.setLast(page.isLast());
        this.setPageNum(page.getPageable().getPageNumber() + 1);
        this.setPageSize(page.getPageable().getPageSize());
    }

    public static <T> PageResponse<T> of(final org.springframework.data.domain.Page<T> page) {
        return new PageResponse<>((PageImpl<T>) page);
    }
}

