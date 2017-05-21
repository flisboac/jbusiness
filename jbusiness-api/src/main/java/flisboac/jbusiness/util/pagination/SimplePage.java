package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;

import flisboac.jbusiness.util.navigables.BigIndexNavigable;
import flisboac.jbusiness.util.navigables.InvalidNavigationError;
import flisboac.jbusiness.util.navigables.Navigate;
import flisboac.jbusiness.util.navigables.Navigation;
import flisboac.jbusiness.util.numbers.Numbers;

public class SimplePage implements Page<SimplePage> {

	private final Pagination configuration;
	private final BigInteger totalPages;
	private BigIndexNavigable navigator;

	public SimplePage(Pagination configuration) {
		this.configuration = configuration;
		this.totalPages = Pages.getTotalPages(configuration);
		this.navigator = new BigIndexNavigable(
				configuration.getInitialPageIndex(), this.totalPages
		);
	}

	@Override
	public Pagination getConfiguration() {
		return configuration;
	}

	@Override
	public int getPageSize() {
		int size = Pagination.INVALID_PAGE_SIZE;
		if (!configuration.isPaginated()) {
			BigInteger maximumLength = Numbers.from(
					configuration.getPageSize() > 0
						? configuration.getPageSize() 
						: Pagination.MAX_PAGE_SIZE)
					.bigInteger();
			if (configuration.getTotalElements().compareTo(maximumLength) <= 0
					&& configuration.getTotalElements().compareTo(BigInteger.ZERO) > 0){
				size = configuration.getTotalElements().intValue();
			}		
		} else {
			size = navigator.isAt(Navigate.LAST)
					? configuration.getTotalElements().subtract(
							this.totalPages
								.subtract(BigInteger.ONE)
								.multiply(Numbers.from(configuration.getPageSize()).bigInteger())
							).intValue()
					: configuration.getPageSize();
		}
		return size;
	}

	public boolean isOversizedInMemory() {
		return getPageSize() == Pagination.INVALID_PAGE_SIZE;
	}
	
	public BigInteger getFirstElementIndex() {
		return getPageIndex().multiply(Numbers.from(configuration.getPageSize()).bigInteger());
	}
	
	public BigInteger getLastElementIndex() {
		return configuration.isPaginated()
				? getFirstElementIndex().add(Numbers.from(getPageSize()).bigInteger()).subtract(BigInteger.ONE)
				: configuration.getTotalElements().subtract(BigInteger.ONE);
	}
	
	@Override
	public BigInteger getPageIndex() {
		return configuration.isPaginated() ? navigator.getIndex() : BigInteger.ZERO;
	}

	@Override
	public boolean isNavigableTo(Navigation nav) {
		return configuration.isPaginated() && navigator.isNavigableTo(nav);
	}

	@Override
	public SimplePage navigateTo(Navigation nav) throws InvalidNavigationError {
		if (!isNavigableTo(nav)) {
			throw new InvalidNavigationError(nav);
		}
		this.navigator = this.navigator.navigateTo(nav);
		return this;
	}

	@Override
	public boolean isAt(Navigation ref) throws UnsupportedOperationException {
		return navigator.isNavigableTo(ref);
	}
}
