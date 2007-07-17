/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Jo Ehm, Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg, Holger Koschek,
 *  Carsten Sahling, Roman Schloemmer
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

package de.ejb3buch.ticket2rock.session.statistics;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.Test;

public class BeanStatisticsBeanTest
{
    /**
     * Testen der reportMethodCall Methode. Wir sehen einfach mal nach, ob
     * <ul>
     * <li> ein Method Call auch nur genau einmal geloggt wird</li>
     * <li> ein anderer Method Call anders geloggt wird</li>
     * <li> ein weiterer MethodCall zurErhoehung des Counters fuehrt</li>
     * </ul>
     */
    @Test
    public void testReportMethodCall()
    {
        BeanStatisticsBean bs = new BeanStatisticsBean();

        Method dummyMethodOne = this.getClass().getMethods()[0];
        Method dummyMethodTwo = this.getClass().getMethods()[1];

        // noch nichts geloggt - muss 0 liefern
        assertEquals(0, bs.getMethodUsage().size());
        bs.reportMethodCall(dummyMethodOne);

        // nach dem ersten Aufruf muss ein Element vorhanden sein
        // und als Counter 1 liefern
        assertEquals(1, bs.getMethodUsage().size());
        assertEquals(1, bs.getMethodUsage().get(dummyMethodOne));

        bs.reportMethodCall(dummyMethodOne);
        assertEquals(2, bs.getMethodUsage().get(dummyMethodOne));

        // eine andere Methode muss immer noch 0 bzw null liefern
        assertEquals(null, bs.getMethodUsage().get(dummyMethodTwo));

    }

    @Test
    public void testReportMethodDuration()
    {
        BeanStatisticsBean bs = new BeanStatisticsBean();

        Method dummyMethodOne = this.getClass().getMethods()[0];

        // noch nichts geloggt - muss 0 liefern
        assertEquals(0, bs.getMethodTotalDuration().size());

        // einmal loggen mit Dauer = 100
        bs.reportMethodDuration(dummyMethodOne, 100L);
        assertEquals(100L, bs.getMethodTotalDuration().get(dummyMethodOne));

        // nochmal loggen mit Dauer = 400
        bs.reportMethodDuration(dummyMethodOne, 400L);
        assertEquals(500L, bs.getMethodTotalDuration().get(dummyMethodOne));

    }

}
