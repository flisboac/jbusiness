package flisboac.jbusiness.util.navigables.impl;

import java.math.BigInteger;

import flisboac.jbusiness.util.navigables.*;
import flisboac.jbusiness.util.numbers.Numbers;

public final class BigIndexNavigableImpl implements Navigable<BigIndexNavigableImpl>, Comparable<BigIndexNavigableImpl> {

    private final BigInteger first;
    private final BigInteger last;
    private final BigInteger length;
    private final BigInteger index;

    public BigIndexNavigableImpl(BigIndexNavigableImpl other) {
        super();
        this.first = other.first;
        this.last = other.last;
        this.length = other.length;
        this.index = other.index;
        checkInvariants();
    }

    public BigIndexNavigableImpl(Number first, Number last) {
        super();
        this.first = Numbers.from(first).exactBigInteger();
        this.last = Numbers.from(last).exactBigInteger();
        this.length = calculateLength(this.first, this.last);
        this.index = this.first;
        checkInvariants();
    }

    public BigIndexNavigableImpl(Number first, Number last, Number index) {
        this(first, last, index, NavigationOverflow.NATURAL);
    }

    public BigIndexNavigableImpl(Number first, Number last, Number index, NavigationOverflow overflow) {
        super();
        this.first = Numbers.from(first).exactBigInteger();
        this.last = Numbers.from(last).exactBigInteger();
        this.length = calculateLength(this.first, this.last);
        this.index = calculateIndex(index, overflow);
        checkInvariants();
    }

    private BigIndexNavigableImpl(BigIndexNavigableImpl other, BigInteger index) {
        super();
        this.first = other.first;
        this.last = other.last;
        this.length = other.length;
        this.index = index;
        checkInvariants();
    }

    private void checkInvariants() {
        if (this.length.compareTo(BigInteger.ZERO) <= 0) {
            // TODO Make a better exception throw
            throw new IllegalStateException("Last must be greater than or equal to first.");
        }
        if (this.index == null || this.index.compareTo(this.first) < 0 || this.index.compareTo(this.last) > 0) {
            // TODO Make a better exception throw
            throw new IllegalStateException("Index must be within bounds.");
        }
    }

    private BigInteger calculateLength(BigInteger first, BigInteger last) {
        return last.subtract(first).add(BigInteger.ONE);
    }

    private BigInteger calculateIndex(Number index, NavigationOverflow overflow) {
        BigInteger result = Numbers.from(index).exactBigInteger();
        switch (overflow) {
            case NATURAL:
                if (result.compareTo(this.first) < 0 || result.compareTo(this.last) > 0) {
                    result = null;
                }
                break;
            case BOUND:
                if (result.compareTo(this.first) < 0) {
                    result = this.first;
                } else if (result.compareTo(this.last) > 0) {
                    result = this.last;
                }
                break;
            case WRAPPED:
                if (result.compareTo(this.first) < 0) {
                    result = this.last.subtract(this.length.remainder(this.first.subtract(result)));
                } else if (result.compareTo(this.last) > 0) {
                    result = this.first.add(this.length.remainder(result.subtract(this.first)));
                }
                break;
        }
        return result;
    }

    private BigInteger calculateNewIndex(Navigation ref) {
        BigInteger result = null;
        Number value = ref.getIndex();
        NavigationReference reference = ref.getReference();
        NavigationOverflow overflow = ref.getOverflow();

        switch (reference) {
            case CURRENT:
                result = this.index;
                break;
            case FIRST:
                result = this.first;
                break;
            case LAST:
                result = this.last;
                break;
            case NEXT:
                result = calculateIndex(this.index.add(BigInteger.ONE), overflow);
                break;
            case PREVIOUS:
                result = calculateIndex(this.index.subtract(BigInteger.ONE), overflow);
                break;
            case ABSOLUTE:
                result = Numbers.from(value).exactBigInteger();
                result = calculateIndex(result, overflow);
                break;
            case RELATIVE:
                result = Numbers.from(value).exactBigInteger();
                result = calculateIndex(this.index.add(result), overflow);
                break;
        }
        return result;
    }

    @Override
    public boolean isNavigableTo(Navigation ref) {
        return calculateNewIndex(ref) != null;
    }

    @Override
    public BigIndexNavigableImpl navigateTo(Navigation ref) throws InvalidNavigationError {
        BigInteger index = calculateNewIndex(ref);
        if (index == null) {
            throw new InvalidNavigationError(ref);
        }
        return new BigIndexNavigableImpl(this, index);
    }

    public BigInteger getHeadLength() {
        return this.index.subtract(this.first);
    }

    public BigInteger getTailLength() {
        return this.last.subtract(this.index);
    }

    public BigInteger getFirst() {
        return first;
    }

    public BigInteger getLast() {
        return last;
    }

    public BigInteger getLength() {
        return length;
    }

    public BigInteger getIndex() {
        return index;
    }

    public boolean isAt(Navigation ptr) throws UnsupportedOperationException {
        BigInteger idx = null;
        switch (ptr.getReference()) {
            case FIRST:
                return this.index.compareTo(this.first) == 0;
            case LAST:
                return this.index.compareTo(this.last) == 0;
            case ABSOLUTE:
                idx = calculateIndex(idx, ptr.getOverflow());
                return this.index.compareTo(Numbers.from(ptr.getIndex()).exactBigInteger()) == 0;
            case NEXT:
                idx = calculateIndex(idx, ptr.getOverflow());
                return this.index.compareTo(Numbers.from(ptr.getIndex()).exactBigInteger()) > 0;
            case PREVIOUS:
                idx = calculateIndex(idx, ptr.getOverflow());
                return this.index.compareTo(Numbers.from(ptr.getIndex()).exactBigInteger()) < 0;
            case CURRENT:
            case RELATIVE:
                throw new UnsupportedOperationException();
        }
        return false;
    }

    @Override
    public int compareTo(BigIndexNavigableImpl o) {
        return this.index.compareTo(o.index);
    }
}
