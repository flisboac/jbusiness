package flisboac.jbusiness.util.pagination.impl;

import java.math.BigInteger;

import flisboac.jbusiness.util.navigables.impl.BigIndexNavigableImpl;
import flisboac.jbusiness.util.navigables.InvalidNavigationError;
import flisboac.jbusiness.util.navigables.Navigate;
import flisboac.jbusiness.util.navigables.Navigation;
import flisboac.jbusiness.util.numbers.Numbers;
import flisboac.jbusiness.util.pagination.Page;
import flisboac.jbusiness.util.pagination.Pages;
import flisboac.jbusiness.util.pagination.Pagination;

public class SimplePageImpl implements Page<SimplePageImpl> {

    private final Pagination configuration;
    private final BigInteger totalPages;
    private BigIndexNavigableImpl navigator;

    public SimplePageImpl(Pagination configuration) {
        this.configuration = configuration;
        this.totalPages = Pages.getTotalPages(configuration);
        this.navigator = new BigIndexNavigableImpl(
            configuration.getInitialPageIndex(), this.totalPages
        );
    }

    @Override
    public Pagination getPagination() {
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
    public SimplePageImpl navigateTo(Navigation nav) throws InvalidNavigationError {
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
