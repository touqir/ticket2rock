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

package de.ejb3buch.ticket2rock.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.ejb3buch.ticket2rock.EmbeddedContainerTestBase;
import de.ejb3buch.ticket2rock.entity.Konzert;

/**
 * @author Dierk
 * 
 */
public class AuskunftBeanTest extends EmbeddedContainerTestBase
{

    /**
     * Test method for
     * {@link de.ejb3buch.ticket2rock.session.AuskunftBean#sucheKonzerte(java.lang.String, java.util.Date, java.util.Date)}.
     */
    @Test
    public void sucheAlleKonzerte() throws Exception
    {
        AuskunftLocal alleKonzerte = (AuskunftLocal) lookup("AuskunftBean/local");

        // wir suchen alle Konzerte, daher keine Einschränkung....
        List<Konzert> konzerte = alleKonzerte.sucheKonzerte(null, null, null);

        assertTrue(konzerte.size() > 1);
    }

    /**
     * Test method for
     * {@link de.ejb3buch.ticket2rock.session.AuskunftBean#sucheKonzerte(java.lang.String, java.util.Date, java.util.Date)}.
     */
    @Test
    public void sucheHamburgerKonzerte() throws Exception
    {
        AuskunftLocal alleKonzerte = (AuskunftLocal) lookup("AuskunftBean/local");

        // wir suchen ein Konzert in der Colorline Arena....
        List<Konzert> konzerte = alleKonzerte.sucheKonzerte("Olympiastadion", null, null);

        assertEquals(konzerte.size(), 1);
    }

    /**
     * Test method for
     * {@link de.ejb3buch.ticket2rock.session.AuskunftBean#sucheKonzerte(java.lang.String, java.util.Date, java.util.Date)}.
     */
    @Test
    public void sucheFalschesKonzert() throws Exception
    {
        AuskunftLocal alleKonzerte = (AuskunftLocal) lookup("AuskunftBean/local");

        // wir suchen ein Konzert im Kuhstall....
        List<Konzert> konzerte = alleKonzerte.sucheKonzerte("Kuhstall", null, null);

        assertEquals(konzerte.size(), 0);
    }
}
