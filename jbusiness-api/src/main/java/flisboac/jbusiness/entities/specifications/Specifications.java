package flisboac.jbusiness.entities.specifications;

import flisboac.jbusiness.entities.specifications.composition.*;
import flisboac.jbusiness.entities.specifications.composition.*;

import java.util.*;

public final class Specifications {

    private Specifications() {}

    public static <T> boolean isCompositeSpecification(Specification<T> specification) {
        return getCompositeOperation(specification).isPresent();
    }

    public static Optional<SpecificationOperation> getCompositeOperation(Specification<?> specification) {
        SpecificationOperation operation = null;
        Objects.requireNonNull(specification);
        if (specification instanceof AndSpecification) {
            operation = SpecificationOperation.AND;
        } else if (specification instanceof OrSpecification) {
            operation = SpecificationOperation.OR;
        } else if (specification instanceof NotSpecification) {
            operation = SpecificationOperation.NOT;
        } else if (specification instanceof IdentitySpecification) {
            operation = SpecificationOperation.ID;
        }
        return Optional.ofNullable(operation);
    }

    public static boolean implementsClass(Class<?> protocol, Specification<?>... specification) {
        boolean result = true;
        Objects.requireNonNull(specification);
        Deque<Specification<?>> allSpecifications = new ArrayDeque<>();
        allSpecifications.addAll(Arrays.asList(specification));

        while (result && !allSpecifications.isEmpty()) {
            Specification<?> currentSpecification = allSpecifications.removeFirst();

            if (currentSpecification instanceof TrueSpecification) {
                return false;
            }

            Specification<?> innerSpecifications[] = {
                // Left-hand side, if any
                AndSpecification.class.isInstance(currentSpecification)
                    ? AndSpecification.class.cast(currentSpecification).getLhs()
                    : OrSpecification.class.isInstance(currentSpecification)
                        ? OrSpecification.class.cast(currentSpecification).getLhs()
                        : null,
                // right-hand side, if any
                AndSpecification.class.isInstance(currentSpecification)
                    ? AndSpecification.class.cast(currentSpecification).getRhs()
                    : OrSpecification.class.isInstance(currentSpecification)
                        ? OrSpecification.class.cast(currentSpecification).getRhs()
                        : null,
                // single id, if any
                NotSpecification.class.isInstance(currentSpecification)
                    ? NotSpecification.class.cast(currentSpecification).getSpecification()
                    : IdentitySpecification.class.isInstance(currentSpecification)
                        ? IdentitySpecification.class.cast(currentSpecification).getSpecification()
                        : null
            };

            if (innerSpecifications[0] != null || innerSpecifications[1] != null || innerSpecifications[2] != null) {
                for (Specification<?> innerSpecification : innerSpecifications) {

                    if (innerSpecification != null) {
                        if (isCompositeSpecification(innerSpecification)) {
                            allSpecifications.addLast(innerSpecification);

                        } else {
                            result = protocol.isInstance(innerSpecification);
                        }
                    }
                }

            } else {
                result = protocol.isInstance(currentSpecification);
            }
        }

        return result;
    }

    public static boolean isAnything(Specification<?> specification) {
        return specification instanceof TrueSpecification;
    }
}
