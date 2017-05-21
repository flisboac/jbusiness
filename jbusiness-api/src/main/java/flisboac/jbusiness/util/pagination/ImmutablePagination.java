package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;
import java.util.Optional;

import flisboac.jbusiness.Immutable;

public final class ImmutablePagination extends SimplePagination implements Immutable {

	public ImmutablePagination() {
		super();
	}

	public ImmutablePagination(ImmutablePagination other) {
		super(other);
	}
	
	public ImmutablePagination(
	        int pageSize,
            BigInteger initialPageIndex,
            BigInteger totalElements
    ) {
		super(pageSize, initialPageIndex, totalElements);
	}
	
	public static SimplePagination noPagination() {
		return new ImmutablePagination();
	}

}
