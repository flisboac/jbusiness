package flisboac.jbusiness.persistence;

import java.util.List;

import flisboac.jbusiness.services.DomainService;
import flisboac.jbusiness.services.InfrastructureService;
import flisboac.jbusiness.util.pagination.Page;

public interface Repository extends InfrastructureService, DomainService {

	public <T> List<T> list(Page<T> page);
}
