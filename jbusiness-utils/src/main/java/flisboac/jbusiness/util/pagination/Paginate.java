package flisboac.jbusiness.util.pagination;

import flisboac.jbusiness.util.FluentBuilder;
import flisboac.jbusiness.util.numbers.Numbers;
import flisboac.jbusiness.util.pagination.impl.SimplePaginationImpl;

import java.math.BigInteger;

public final class Paginate implements FluentBuilder<Pagination> {

    private int pageSize;
    private BigInteger initialPageIndex = BigInteger.ZERO;
    private BigInteger totalElements = BigInteger.ZERO;

    private Paginate() {
        super();
    }

    private Paginate(int pageSize) {
        super();
        this.pageSize = pageSize;
    }

    public static Pagination none() {
        return SimplePaginationImpl.noPagination();
    }

    public static Paginate size(int pageSize) {
        return new Paginate(pageSize);
    }

    public Paginate withInitialPageIndex(Number number) {
        this.initialPageIndex = Numbers.from(number).exactBigInteger();
        return this;
    }

    public Paginate withTotalElements(Number number) {
        this.totalElements = Numbers.from(number).exactBigInteger();
        return this;
    }

    public Pagination done() {
        return new SimplePaginationImpl(pageSize, initialPageIndex, totalElements);
    }

    public static final Pagination NONE = Paginate.none();
}
