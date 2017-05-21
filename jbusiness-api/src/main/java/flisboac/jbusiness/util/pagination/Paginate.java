package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;
import java.util.Optional;

import flisboac.jbusiness.FluentBuilder;
import flisboac.jbusiness.util.numbers.Numbers;

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
		return ImmutablePagination.noPagination();
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
		return new ImmutablePagination(pageSize, initialPageIndex, totalElements);
	}
	
	public static final Pagination NONE = Paginate.none();
}
