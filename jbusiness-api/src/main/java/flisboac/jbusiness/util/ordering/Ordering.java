package flisboac.jbusiness.util.ordering;

import java.util.List;

public interface Ordering {
	
	public static interface Element {

		public Object getProperty();
		public Sort getSort();
	}
	
	public boolean isNatural();
	public List<? extends Ordering.Element> getElements();
}
