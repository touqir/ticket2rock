package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.Local;
import javax.ejb.Timer;

@Local
public interface AufrufstatistikBeanLocal {
	public void notiereMethodenaufruf(final String className,
			final String methodName, final long callTime);

	public void notiereAusnahme(final String className,
			final String methodName, final long callTime, final Exception e);

	public void notiereTimeout(final Timer timer);
}
