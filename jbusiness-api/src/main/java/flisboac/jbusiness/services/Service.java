package flisboac.jbusiness.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Service {

    List<String> RESERVED_NAMES =
        Collections.unmodifiableList(
            Arrays.asList("~")
        );

    String getApplicationName();

    String getServiceName();
}
