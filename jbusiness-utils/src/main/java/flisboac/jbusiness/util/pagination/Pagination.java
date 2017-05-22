package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;

import flisboac.jbusiness.util.numbers.Numbers;

public interface Pagination {

    int MAX_PAGE_SIZE = Numbers.from(Math.pow(2, 30)).intValue();
    int INVALID_PAGE_SIZE = Integer.MAX_VALUE;

    boolean isPaginated();
    int getPageSize();
    BigInteger getInitialPageIndex();
    BigInteger getTotalElements();
}
