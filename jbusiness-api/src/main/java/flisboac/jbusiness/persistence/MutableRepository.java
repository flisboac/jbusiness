package flisboac.jbusiness.persistence;

public interface MutableRepository extends Repository {

	public <T> T save(T entity);
	public <T> void delete(T entity);
	public <ID> void deleteById(ID id);
}
