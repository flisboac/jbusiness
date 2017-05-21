package flisboac.jbusiness.util.navigables;

import java.math.BigInteger;

import flisboac.jbusiness.BusinessError;
import flisboac.jbusiness.util.numbers.Numbers;

public final class BigIndexNavigable implements Navigable<BigIndexNavigable>, Comparable<BigIndexNavigable> {

	private final BigInteger first;
	private final BigInteger last;
	private final BigInteger length;
	private final BigInteger index;
	
	public BigIndexNavigable(BigIndexNavigable other) {
		super();
		this.first = other.first;
		this.last = other.last;
		this.length = other.length;
		this.index = other.index;
	}
	
	public BigIndexNavigable(Number first, Number last) {
		this.first = Numbers.from(first).exactBigInteger();
		this.last = Numbers.from(last).exactBigInteger();
		this.length = calculateLength();
		this.index = this.first;
		checkInvariants();
	}

	public BigIndexNavigable(Number first, Number last, Number index) {
		this(first, last, index, NavigationOverflow.NATURAL);
	}

	public BigIndexNavigable(Number first, Number last, Number index, NavigationOverflow overflow) {
		this.first = Numbers.from(first).exactBigInteger();
		this.last = Numbers.from(last).exactBigInteger();
		this.length = this.last.subtract(this.first).add(BigInteger.ONE);
		this.index = calculateIndex(index, overflow);
		checkInvariants();
	}
	
	private BigIndexNavigable(BigIndexNavigable other, BigInteger index) {
		super();
		this.first = other.first;
		this.last = other.last;
		this.length = other.length;
		this.index = index;
	}
	
	private void checkInvariants() {
		if (this.length.compareTo(BigInteger.ZERO) <= 0) {
			// TODO Make a better exception throw
			throw new BusinessError("Last must be greater than or equal to first.");
		}
		if (this.index == null || this.index.compareTo(this.first) < 0 || this.index.compareTo(this.last) > 0) {
			// TODO Make a better exception throw
			throw new BusinessError("Index must be within bounds.");
		}
	}
	
	private BigInteger calculateLength() {
		return this.last.subtract(this.first).add(BigInteger.ONE);
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
	
	private BigInteger calculateNewIndex(
			Number value,
			NavigationReference reference,
			NavigationOverflow overflow) {
		BigInteger result = null;
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
			if (value == null) {
				throw new BusinessError("Value needed for absolute navigation.");
			}
			result = Numbers.from(value).exactBigInteger();
			result = calculateIndex(result, overflow);
			break;
		case RELATIVE:
			if (value == null) {
				throw new BusinessError("Value needed for relative navigation.");
			}
			result = Numbers.from(value).exactBigInteger();
			result = calculateIndex(this.index.add(result), overflow);
			break;
		}
		return result;
	}
	
	@Override
	public boolean isNavigableTo(Navigation ref) {
		return calculateNewIndex(ref.getIndex(), ref.getReference(), ref.getOverflow()) != null;
	}

	@Override
	public BigIndexNavigable navigateTo(Navigation ref) throws InvalidNavigationError {
		BigInteger index = calculateNewIndex(ref.getIndex(), ref.getReference(), ref.getOverflow());
		if (index == null) {
			throw new InvalidNavigationError(ref);
		}
		return new BigIndexNavigable(this, index);
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
	public int compareTo(BigIndexNavigable o) {
		return this.index.compareTo(o.index);
	}
}
