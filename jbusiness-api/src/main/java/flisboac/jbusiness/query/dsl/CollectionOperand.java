package flisboac.jbusiness.query.dsl;

import java.util.Collection;

public interface CollectionOperand<T, C extends Collection<T>>
		extends MultivaloredOperand<T>,
		SizedOperand<T> {

	public Class<C> getCollectionClass();
}
