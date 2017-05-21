package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;
import java.util.Optional;

import flisboac.jbusiness.BusinessError;

public class SimplePagination implements Pagination {

	private final Integer pageSize;
	private final BigInteger initialPageIndex;
	private final BigInteger totalElements;
	private final BigInteger totalPages;
	
	public SimplePagination() {
		super();
		this.pageSize = null;
		this.initialPageIndex = BigInteger.ZERO;
		this.totalElements = BigInteger.ZERO;
		this.totalPages = BigInteger.ZERO;
	}
	
	public SimplePagination(SimplePagination other) {
		super();
		this.pageSize = other.pageSize;
		this.initialPageIndex = other.initialPageIndex;
		this.totalElements = other.totalElements;
		this.totalPages = other.totalPages;
	}
	
	public SimplePagination(
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
			throw new BusinessError("Invalid page configuration.");
		}
	}
}
