package flisboac.jbusiness.util.ordering;

public enum Sort {

    NATURAL(Sort.Type.NATURAL, Sort.Type.NATURAL),
    ASC(Sort.Type.ASC, Sort.Type.NATURAL),
    DESC(Sort.Type.DESC, Sort.Type.NATURAL),
    ASC_NULLS_FIRST(Sort.Type.ASC, Sort.Type.ASC),
    DESC_NULLS_FIRST(Sort.Type.DESC, Sort.Type.ASC),
    ASC_NULLS_LAST(Sort.Type.ASC, Sort.Type.DESC),
    DESC_NULLS_LAST(Sort.Type.DESC, Sort.Type.DESC);

    public enum Type {
        NATURAL, ASC, DESC
    }

    private final Type sort;
    private final Type nullSort;

    private Sort(Type sort, Type nullSort) {
        this.sort = sort;
        this.nullSort = nullSort;
    }

    public Type getSort() {
        return sort;
    }

    public Type getNullSort() {
        return nullSort;
    }

}
