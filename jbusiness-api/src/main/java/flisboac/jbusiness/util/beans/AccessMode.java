package flisboac.jbusiness.util.beans;

public enum AccessMode {

	NORMAL,
	FORCED;
	
	public boolean isForced() {
		return this == FORCED;
	}
}
