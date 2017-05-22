package flisboac.jbusiness.util.numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class NumberWrapper extends Number {
    private static final long serialVersionUID = 45056085189310527L;

    private final Number value;

    private NumberWrapper(Number number) {
        this.value = number;
    }

    public static NumberWrapper from(Number number) {
        return new NumberWrapper(number);
    }

    public Number getNumber() {
        return value;
    }

    public AtomicInteger atomicInteger() {
        return new AtomicInteger(value.intValue());
    }

    public AtomicLong atomicLong() {
        return new AtomicLong(value.longValue());
    }

    public BigDecimal bigDecimal() {
        if (NumberWrapper.class.isInstance(value)) {
            return ((NumberWrapper)value).bigDecimal();
        } else if (BigDecimal.class.isInstance(value)) {
            return (BigDecimal) value;
        } else if (BigInteger.class.isInstance(value)) {
            return new BigDecimal((BigInteger) value);
        } else if (Float.class.isInstance(value)) {
            return BigDecimal.valueOf(value.floatValue());
        } else if (Double.class.isInstance(value)) {
            return BigDecimal.valueOf(value.doubleValue());
        } else {
            return BigDecimal.valueOf(value.longValue());
        }
    }

    public BigInteger bigInteger() {
        if (NumberWrapper.class.isInstance(value)) {
            return ((NumberWrapper)value).bigInteger();
        } else if (BigInteger.class.isInstance(value)) {
            return (BigInteger) value;
        } else if (BigDecimal.class.isInstance(value)) {
            return ((BigDecimal) value).toBigInteger();
        } else if (Float.class.isInstance(value)) {
            return BigDecimal.valueOf(value.floatValue()).toBigInteger();
        } else if (Double.class.isInstance(value)) {
            return BigDecimal.valueOf(value.doubleValue()).toBigInteger();
        } else {
            return BigDecimal.valueOf(value.longValue()).toBigInteger();
        }
    }

    public BigInteger exactBigInteger() {
        if (NumberWrapper.class.isInstance(value)) {
            return ((NumberWrapper)value).exactBigInteger();
        } else if (BigInteger.class.isInstance(value)) {
            return (BigInteger) value;
        } else if (BigDecimal.class.isInstance(value)) {
            return ((BigDecimal) value).toBigIntegerExact();
        } else if (Float.class.isInstance(value)) {
            return BigDecimal.valueOf(value.floatValue()).toBigIntegerExact();
        } else if (Double.class.isInstance(value)) {
            return BigDecimal.valueOf(value.doubleValue()).toBigIntegerExact();
        } else {
            return BigDecimal.valueOf(value.longValue()).toBigIntegerExact();
        }
    }

    public byte byteValue() {
        return value.byteValue();
    }

    public Byte byteObject() {
        return value.byteValue();
    }

    public short shortValue() {
        return value.shortValue();
    }

    public Short shortObject() {
        return value.shortValue();
    }

    public Integer intObject() {
        return value.intValue();
    }

    public int intValue() {
        return value.intValue();
    }

    public Long longObject() {
        return value.longValue();
    }

    public long longValue() {
        return value.longValue();
    }

    public Float floatObject() {
        return value.floatValue();
    }

    public float floatValue() {
        return value.floatValue();
    }

    public Double doubleObject() {
        return value.doubleValue();
    }

    public double doubleValue() {
        return value.doubleValue();
    }
}
