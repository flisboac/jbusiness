package flisboac.jbusiness.util.pagination;

import java.math.BigInteger;
import java.math.RoundingMode;

import flisboac.jbusiness.util.numbers.Numbers;

public class Pages {

    private Pages() {
    }

    public static boolean isValid(Pagination pagination) {
        boolean valid = pagination != null
            && pagination.getInitialPageIndex() != null
            && pagination.getTotalElements() != null;
        valid = valid
            && pagination.getPageSize() >= 0
            && pagination.getTotalElements().compareTo(BigInteger.ZERO) >= 0
            && pagination.getInitialPageIndex().compareTo(BigInteger.ZERO) >= 0;
        return valid;
    }

    public static <T> boolean isValid(Page<T> page) {
        boolean valid = page != null
            && isValid(page.getPagination())
            && page.getPageSize() >= 0
            && page.getPageSize() < page.getPagination().getPageSize()
            && page.getPageIndex().compareTo(BigInteger.ZERO) >= 0
            && page.getPageIndex().compareTo(getTotalPages(page)) < 0;
        return valid;
    }

    public static BigInteger getTotalPages(Pagination pagination) {
        return !pagination.isPaginated()
            ? BigInteger.ONE
            : Numbers.from(pagination.getTotalElements())
            .bigDecimal()
            .divide(Numbers.from(pagination.getPageSize()).bigDecimal(), RoundingMode.CEILING)
            .toBigIntegerExact();
    }

    public static BigInteger getTotalPages(Page<?> page) {
        return getTotalPages(page.getPagination());
    }

    public static boolean isSinglePage(Pagination pagination) {
        return !pagination.isPaginated() || pagination.getTotalElements().compareTo(Numbers.from(pagination.getPageSize()).bigInteger()) <= 0;
    }

    public static boolean isSinglePage(Page<?> page) {
        return isSinglePage(page.getPagination());
    }

    public static boolean isUndersized(Pagination pagination) {
        return pagination.isPaginated()
            && pagination.getTotalElements().compareTo(Numbers.from(pagination.getPageSize()).bigInteger()) > 0;
    }

    public static boolean isUndersized(Page<?> page) {
        return !page.getPagination().isPaginated() || isUndersized(page.getPagination());
    }
}
