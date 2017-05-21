package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;

import flisboac.jbusiness.util.navigables.Navigable;

public interface Page<T> extends Navigable<Page<T>> {

	// Fixed data (won't change on page navigation)
	public Pagination getConfiguration();
	
	// Per page instance data
	public int getPageSize();
	public BigInteger getPageIndex();
}
