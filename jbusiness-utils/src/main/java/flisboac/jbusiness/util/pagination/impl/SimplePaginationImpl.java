package flisboac.jbusiness.util.pagination.impl;

import flisboac.jbusiness.util.pagination.Pages;
import flisboac.jbusiness.util.pagination.Pagination;

import java.math.BigInteger;

public class SimplePaginationImpl implements Pagination {

    private final Integer pageSize;
    private final BigInteger initialPageIndex;
    private final BigInteger totalElements;
    private final BigInteger totalPages;

    public SimplePaginationImpl() {
        super();
        this.pageSize = null;
        this.initialPageIndex = BigInteger.ZERO;
        this.totalElements = BigInteger.ZERO;
        this.totalPages = BigInteger.ZERO;
    }

    public SimplePaginationImpl(SimplePaginationImpl other) {
        super();
        this.pageSize = other.pageSize;
        this.initialPageIndex = other.initialPageIndex;
        this.totalElements = other.totalElements;
        this.totalPages = other.totalPages;
    }

    public SimplePaginationImpl(
        int pageSize,
        BigInteger initialPageIndex,
        BigInteger totalElements) {
        this.pageSize = pageSize;
        this.initialPageIndex = initialPageIndex;
        this.totalElements = totalElements;
        this.totalPages = Pages.getTotalPages(this);
        checkInvariants();
    }

    public boolean isPaginated() {
        return pageSize != null;
    }

    public int getPageSize() {
        return pageSize != null ? pageSize : 0;
    }

    public BigInteger getInitialPageIndex() {
        return initialPageIndex;
    }

    public BigInteger getTotalElements() {
        return totalElements;
    }

    public BigInteger getTotalPages() {
        return totalPages;
    }

    private void checkInvariants() {
        if (!Pages.isValid(this)) {
            // TODO Make a better exception throw
            throw new IllegalStateException("Invalid page configuration.");
        }
    }

    public static SimplePaginationImpl noPagination() {
        return new SimplePaginationImpl();
    }
}
