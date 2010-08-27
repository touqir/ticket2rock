package de.ejb3buch.ticket2rock.session.interceptor.demo;

import javax.ejb.EJB;
import javax.ejb.Timer;
import javax.interceptor.AroundInvoke;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.demo.AufrufstatistikBeanLocal;

public class Abfangjaeger {

	@EJB
	private AufrufstatistikBeanLocal aufrufstatistik;

	private Logger logger = Logger.getRootLogger();

//	@AroundInvoke
	public Object onInvocation(InvocationContext ctx) {
		Object result = null;
		if (aufrufstatistik != null) {
			aufrufstatistik.notiereMethodenaufruf(ctx.getMethod().getClass()
					.getName(), ctx.getMethod().getName(),
					System.currentTimeMillis());
		}
		try {
			result = ctx.proceed();
		} catch (Exception e) {
			if (aufrufstatistik != null) {
				aufrufstatistik.notiereAusnahme(ctx.getMethod().getClass()
						.getName(), ctx.getMethod().getName(),
						System.currentTimeMillis(), e);
			}
			logger.error(e);
		}
		return result;
	}

	// @AroundTimeout interception for timeout method need to be implemented.
	// It's currently missing from AS 6.0.0.M4.
	// https://jira.jboss.org/browse/EJBTHREE-2142
	@AroundTimeout
	public Object onTimeout(InvocationContext ctx) throws Exception {
		Timer timer = (Timer) ctx.getTimer();
		if (aufrufstatistik != null) {
			aufrufstatistik.notiereTimeout(timer);
		}
		logger.info("Interceptor timeout: " + (String) timer.getInfo());
		return ctx.proceed();
	}
}
