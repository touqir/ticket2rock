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

import java.lang.reflect.Method;

import javax.interceptor.InvocationContext;

import static org.mockito.Mockito.*;
import org.junit.Test;
import org.mockito.Mockito;

import de.ejb3buch.ticket2rock.session.statistics.BeanStatisticsLocal;

public class BeanStatisticsInterceptorTestSimple
{

    @Test
    public void testOnMethodCall() throws Exception
    {
        Method interceptedMethod = InterceptorTestBean.class.getMethod("interceptedCall", new Class[] { String.class });

        BeanStatisticsInterceptor classUnderTest = new BeanStatisticsInterceptor();

        // Mockobjekt fuer den InvocationContext zusammenbauen
        InvocationContext icMock = mock(InvocationContext.class);
        Mockito.when(icMock.getMethod()).thenReturn(interceptedMethod);
        Mockito.when(icMock.proceed()).thenReturn(null);

        // Mockobject fuer die Beanstatistics zusammenbauen
        BeanStatisticsLocal bslMock = mock(BeanStatisticsLocal.class);

        // injizieren der BeanStatistic in den Interzeptor
        classUnderTest.setBeanStatisticsBean(bslMock);

        classUnderTest.onMethodCall(icMock);

        // pruefen, ob das Ergebnis dem Erwarteten entspricht
        verify(bslMock).reportMethodCall(interceptedMethod);
        verify(bslMock).reportMethodDuration(interceptedMethod, 0);
    }

}
