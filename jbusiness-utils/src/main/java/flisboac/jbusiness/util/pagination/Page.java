package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;

import flisboac.jbusiness.util.navigables.Navigable;

public interface Page<T> extends Navigable<Page<T>> {

    // Fixed data (won't change on page navigation)
    Pagination getPagination();

    // Per page instance data
    int getPageSize();
    BigInteger getPageIndex();
}
