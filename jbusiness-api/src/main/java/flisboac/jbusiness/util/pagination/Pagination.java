package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;

import flisboac.jbusiness.util.numbers.Numbers;

public interface Pagination {

	public static final int MAX_PAGE_SIZE = Numbers.from(Math.pow(2, 30)).intValue();
	public static final int INVALID_PAGE_SIZE = Integer.MAX_VALUE;

	public boolean isPaginated();
	public int getPageSize();
	public BigInteger getInitialPageIndex();
	public BigInteger getTotalElements();
}