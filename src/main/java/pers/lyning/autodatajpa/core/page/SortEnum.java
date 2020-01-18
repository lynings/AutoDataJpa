package pers.lyning.autodatajpa.core.page;

/**
 * 排序
 *
 * @author lyning
 */
public enum SortEnum {

    /**
     * 降序
     */
    DESC("DESC"),

    /**
     * 升序
     */
    ASC("ASC"),
    ;

    private final String value;

    SortEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
