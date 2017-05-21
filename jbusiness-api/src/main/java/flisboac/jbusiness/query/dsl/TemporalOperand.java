package flisboac.jbusiness.query.dsl;

import java.util.Calendar;
import java.util.Optional;

public interface TemporalOperand<T>
		extends ComparableOperand<T>,
		SummableOperand<T> {

	public Calendar getCalendar(Optional<Calendar> calendar);
}
