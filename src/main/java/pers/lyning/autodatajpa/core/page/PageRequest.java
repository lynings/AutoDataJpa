package pers.lyning.autodatajpa.core.page;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求对象
 *
 * @author lyning
 */
@Setter
@Getter
public class PageRequest extends Page {

    public PageRequest() {
        this.pageNum = 1;
        this.pageSize = 20;
    }

    public PageRequest(final int page, final int size) {
        super(page, size);
    }

    public PageRequest(final Page page) {
        super(page.getPageNum(), page.getPageSize(), page.getSort());
    }
}
