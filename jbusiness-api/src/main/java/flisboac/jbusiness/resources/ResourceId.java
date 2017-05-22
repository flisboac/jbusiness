package flisboac.jbusiness.resources;

import java.util.Locale;
import java.util.Optional;

public interface ResourceId {

    class Arity {
        private final boolean array;
        private final int minSize;
        private final int maxSize;

        public Arity() {
            this.array = false;
            this.minSize = -1;
            this.maxSize = -1;
        }

        public Arity(int size) {
            this.array = true;
            this.minSize = size;
            this.maxSize = size;
        }

        public Arity(int minSize, int maxSize) {
            this.array = true;
            this.minSize = minSize;
            this.maxSize = maxSize;
        }

        public boolean isArray() {
            return array;
        }

        public boolean isFixed() {
            return minSize == maxSize;
        }

        public int getMinSize() {
            return minSize;
        }

        public int getMaxSize() {
            return maxSize;
        }
    }

    String getName(); // By default, this.name() for enums, or this.getClass().getSimpleName() for other objects.
    String getGroupName(); // By default, this.getClass().getName() for enums, or this.getClass().getPackage().getName() for other objects.
    Optional<String> getBundleName(); // If not provided, uses a "default" bundle (e.g. tries to resolve the bundle's name at runtime).
    Optional<Locale> getLocale(); // If not provided, uses the JVM's (or environment's) default locale.
    Arity getArity(); // Indicates if the value is and array and how many elements it's expected to have
    Class<?> getResourceElementClass(); // The resulting loaded resource's class, WITHOUT array qualification.
    Class<?> getResourceClass(); // The resulting loaded resource's class, MAYBE WITH array qualification (depends on arity).
}
