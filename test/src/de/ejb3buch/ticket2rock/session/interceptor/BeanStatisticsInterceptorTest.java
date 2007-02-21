/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg und Holger Koschek
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

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.ejb3buch.ticket2rock.EmbeddedContainerTestHelper;
import de.ejb3buch.ticket2rock.session.statistics.BeanStatisticsLocal;

public class BeanStatisticsInterceptorTest
{

	static Logger logger = Logger.getLogger(BeanStatisticsInterceptorTest.class);

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        EmbeddedContainerTestHelper.startupEmbeddedContainer(null);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception
    {
        EmbeddedContainerTestHelper.shutdownEmbeddedContainer();
    }

    /**
     * Testen, ob der Interceptor gerufen wird und ordentlich funktioniert
     * 
     * @throws Exception
     */
    @Test
    public void interceptedMethodCall() throws Exception
    {
        Method interceptedMethod = InterceptorTestBean.class.getMethod("interceptedCall", new Class[] { String.class });

        InterceptorTest it = (InterceptorTest) EmbeddedContainerTestHelper.lookup("InterceptorTestBean/local");
        BeanStatisticsLocal bsl = (BeanStatisticsLocal) EmbeddedContainerTestHelper.lookup("BeanStatisticsBean/local");

        it.interceptedCall("");
        long duration = bsl.getMethodTotalDuration().get(interceptedMethod).longValue();

        it.nonInterceptedCall("");
        assertEquals(duration, bsl.getMethodTotalDuration().get(interceptedMethod));

        it.interceptedCall("");

        long secondDuration = bsl.getMethodTotalDuration().get(interceptedMethod).longValue();

        logger.info(duration + " / " + secondDuration);
        assertNotSame(duration, secondDuration);

    }

    /**
     * Testen, ob die nicht aufzurufende Methode evtl. doch gerufen wird.
     * 
     * @throws Exception
     */
    @Test
    public void nonInterceptedMethodCall() throws Exception
    {

        InterceptorTest it = (InterceptorTest) EmbeddedContainerTestHelper.lookup("InterceptorTestBean/local");
        BeanStatisticsLocal bsl = (BeanStatisticsLocal) EmbeddedContainerTestHelper.lookup("BeanStatisticsBean/local");

        int startsize = bsl.getMethodUsage().size();
        it.nonInterceptedCall("");

        assertEquals(startsize, bsl.getMethodUsage().size());
    }

}
