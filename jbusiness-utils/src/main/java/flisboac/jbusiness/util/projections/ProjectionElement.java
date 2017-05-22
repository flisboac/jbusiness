package flisboac.jbusiness.util.projections;

import java.util.List;

public interface ProjectionElement {

    Class<?> getPropertyClass();
    Object getProperty();
}
