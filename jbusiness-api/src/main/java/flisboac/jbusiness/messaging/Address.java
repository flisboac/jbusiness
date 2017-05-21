package flisboac.jbusiness.messaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class Address {
	
	public static final String INDEPENDENT_NAME = "~";
	public static final String INTERFACE_SEPARATOR = "!";
	
	public static enum Scope {
		
		UNKNOWN(null),
		CUSTOM(null),
		GLOBAL("global"), // global:/app/service/path/subject!interface?paramname=paramvalue#fragment
		APPLICATION("app"), // app:/service/path/subject!interface?paramname=paramvalue#fragment
		SERVICE("service"); // service:/path/subject!interface?paramname=paramvalue#fragment
		
		// Globally-bound, application-dependent:   global:/app/service/path/subject!interface?paramname=paramvalue#fragment
		// Globally-bound, application-independent: global:/~/path/subject!interface?paramname=paramvalue#fragment
		// Application-bound, service-dependent:    app:/service/path/subject!interface?paramname=paramvalue#fragment
		// Application-bound, service-independent:  app:/~/subject!interface?paramname=paramvalue#fragment
		// Service-bound:                           service:/path/subject!interface?paramname=paramvalue#fragment
		
		private final String name;
		
		private Scope(String name) {
			this.name = name;
		}

		public static Scope findByName(String name) {
			for (Scope elem : values()) {
				if (elem.isKnown() && elem.name.equals(name)) {
					return elem;
				}
			}
			return UNKNOWN;
		}
		
		public String getName() {
			return name;
		}
		
		public boolean isStandardized() {
			return this.name != null;
		}
		
		public boolean isKnown() {
			return this != UNKNOWN;
		}
	}
	
	private final Scope scope;
	private final Optional<String> scopeName;
	private final Optional<String> applicationName;
	private final Optional<String> serviceName;
	private final List<String> path;
	private final String subjectName;
	private final Optional<String> interfaceName;
	private final Map<String, String> parameters;
	private final Optional<String> fragment;
	
	public Address(Address other) {
		super();
		this.scope = other.scope;
		this.scopeName = other.scopeName;
		this.applicationName = other.applicationName;
		this.serviceName = other.serviceName;
		this.path = new ArrayList<>(other.path);
		this.subjectName = other.subjectName;
		this.interfaceName = other.interfaceName;
		this.parameters = new HashMap<>(other.parameters);
		this.fragment = other.fragment;
	}
	
	private Scope inferScope(Scope initialScope) {
		Scope scope;
		switch (initialScope) {
		case UNKNOWN:
			if (this.scopeName.isPresent()) {
				scope = Scope.CUSTOM;
			} else if (this.applicationName.isPresent()) {
				scope = Scope.GLOBAL;
			} else if (this.serviceName.isPresent()) {
				scope = Scope.SERVICE;
			} else {
				scope = Scope.GLOBAL;
			}
			break;
		case CUSTOM:
		case GLOBAL:
		case APPLICATION:
		case SERVICE:
		}
		return scope;
	}
	
}
