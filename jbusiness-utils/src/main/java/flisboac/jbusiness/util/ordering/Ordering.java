package flisboac.jbusiness.util.ordering;

import java.util.List;

public interface Ordering {

    boolean isNatural();
    List<? extends OrderingElement> getElements();
}
