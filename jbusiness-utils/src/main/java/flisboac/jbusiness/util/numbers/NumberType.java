package flisboac.jbusiness.util.numbers;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public enum NumberType {
    UNKNOWN(false, false, false, false, false, null, null, null),
    BYTE(true, false, false, false, true, "byteValue", Byte.class, Byte.class),
    SHORT(true, false, false, false, true, "shortValue", Short.class, Short.class),
    INTEGER(true, false, false, false, true, "intValue", Integer.class, Integer.class),
    LONG(true, false, false, false, true, "longValue", Long.class, Long.class),
    FLOAT(true, true, false, false, true, "floatValue", Float.class, Float.class),
    DOUBLE(true, true, false, false, true, "doubleValue", Double.class, Double.class),
    ATOMIC_INTEGER(true, false, false, true, true, "intntValue", AtomicInteger.class, Integer.class),
    ATOMIC_LONG(true, false, false, true, true, "longValue", AtomicLong.class, Long.class),
    BIG_INTEGER(true, false, true, false, false, null, BigInteger.class, null),
    BIG_DECIMAL(true, true, true, false, false, null, BigDecimal.class, null);

    private final Class<? extends Number> numberClass;
    private final Class<? extends Number> nativeNumberClass;
    private final boolean valid;
    private final boolean floatingPoint;
    private final boolean bigNumber;
    private final boolean atomic;
    private final boolean bounded;
    private final String valueGetterMethodName;
    private final BigDecimal minimumValue;
    private final BigDecimal maximumValue;

    private NumberType(boolean valid,
                       boolean floatingPoint,
                       boolean bigNumber,
                       boolean atomic,
                       boolean bounded,
                       String valueGetterMethodName,
                       Class<? extends Number> numberClass,
                       Class<? extends Number> nativeNumberClass) {
        this.valid = valid;
        this.floatingPoint = floatingPoint;
        this.bigNumber = bigNumber;
        this.atomic = atomic;
        this.bounded = bounded;
        this.numberClass = numberClass;
        this.valueGetterMethodName = valueGetterMethodName;
        this.nativeNumberClass = nativeNumberClass;
        this.minimumValue = generateMinimumValue();
        this.maximumValue = generateMaximumValue();
    }

    public static NumberType findByNumberClass(Class<?> numberClass) {
        for (NumberType elem : values()) {
            if (numberClass.equals(elem.numberClass)) {
                return elem;
            }
        }
        return UNKNOWN;
    }

    private final BigDecimal generateLimitValue(String fieldName) {
        if (!this.bounded) {
            return BigDecimal.ZERO;
        }
        if (this.nativeNumberClass == null) {
            throw new RuntimeException(fieldName + " cannot be deduced for "
                + this.name() + " (value class: "
                + this.numberClass.getName() + ").");
        }
        Field field;
        Number number;
        try {
            field = this.nativeNumberClass.getField(fieldName);
            number = (Number) field.get(null);
        } catch (NoSuchFieldException
            | SecurityException
            | IllegalArgumentException
            | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (this.floatingPoint) {
            return new BigDecimal(number.doubleValue());
        }
        return new BigDecimal(number.longValue());
    }

    private final BigDecimal generateMinimumValue() {
        return generateLimitValue("MIN_VALUE");
    }

    private final BigDecimal generateMaximumValue() {
        return generateLimitValue("MAX_VALUE");
    }

    public Class<? extends Number> getNumberClass() {
        return numberClass;
    }

    public Optional<Class<? extends Number>> getNativeNumberClass() {
        return Optional.ofNullable(nativeNumberClass);
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isFloatingPoint() {
        return floatingPoint;
    }

    public boolean isBigNumber() {
        return bigNumber;
    }

    public boolean isAtomic() {
        return atomic;
    }

    public boolean isBounded() {
        return bounded;
    }

    public String getValueGetterMethodName() {
        return valueGetterMethodName;
    }

    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    public BigDecimal getMaximumValue() {
        return maximumValue;
    }
}
