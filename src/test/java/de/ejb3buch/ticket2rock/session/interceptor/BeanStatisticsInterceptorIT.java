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
package de.ejb3buch.ticket2rock.session.interceptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.lang.reflect.Method;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.ejb3buch.ticket2rock.session.statistics.BeanStatistics;
import de.ejb3buch.ticket2rock.session.statistics.BeanStatisticsBean;
import de.ejb3buch.ticket2rock.session.statistics.BeanStatisticsLocal;

@RunWith(Arquillian.class)
public class BeanStatisticsInterceptorIT {
	static Logger logger = Logger
			.getLogger(BeanStatisticsInterceptorIT.class);

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addClasses(
				BeanStatisticsInterceptor.class, BeanStatistics.class,
				BeanStatisticsLocal.class, BeanStatisticsBean.class,
				InterceptorTest.class, InterceptorTestBean.class);

	}

	@EJB
	InterceptorTest it;

	@EJB
	BeanStatistics bsl;

	/**
	 * Testen, ob der Interceptor gerufen wird und ordentlich
	 * funktioniert
	 * 
	 * @throws Exception
	 */
	@Test
	public void interceptedMethodCall() throws Exception {
		Method interceptedMethod = InterceptorTestBean.class.getMethod(
				"interceptedCall", new Class[] { String.class });

		it.interceptedCall("");
		long duration = bsl.getMethodTotalDuration().get(interceptedMethod)
				.longValue();

		it.nonInterceptedCall("");
		assertEquals(new Long(duration),
				bsl.getMethodTotalDuration().get(interceptedMethod));

		it.interceptedCall("");

		long secondDuration = bsl.getMethodTotalDuration()
				.get(interceptedMethod).longValue();

		logger.info(duration + " / " + secondDuration);
		assertNotSame(duration, secondDuration);

	}

	/**
	 * Testen, ob die nicht aufzurufende Methode evtl. doch gerufen wird.
	 * 
	 * @throws Exception
	 */
	@Test
	public void nonInterceptedMethodCall() throws Exception {

		int startsize = bsl.getMethodUsage().size();
		it.nonInterceptedCall("");
		assertEquals(startsize, bsl.getMethodUsage().size());
	}

}
