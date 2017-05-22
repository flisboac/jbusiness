package flisboac.jbusiness.util.projections;

import java.util.List;

public interface Projection<T> {

    List<ProjectionElement> getElements();
}
