/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3.1 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerten. 
 *
 *  Copyright (C) 2006-2011
 *  Holisticon AG
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
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

	@AroundInvoke
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
	// It's missing from JBOSS AS 6 until version 6.1.
	// https://jira.jboss.org/browse/EJBTHREE-2142 & https://issues.jboss.org/browse/JBAS-8306
	@AroundTimeout
	public Object onTimeout(InvocationContext ctx) throws Exception {
		Timer timer = (Timer) ctx.getTimer();
		aufrufstatistik.notiereTimeout(timer);
		logger.info("Interceptor timeout: " + (String) timer.getInfo());
		return ctx.proceed();
	}
}
