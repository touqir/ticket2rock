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

package de.ejb3buch.ticket2rock.session.ticketbestellung;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;

public class BestellvorgangBeanTest
{
    
    static Konzert testKonzert1, testKonzert2;

    @BeforeClass
    public static void baueKonzerte()
    {
        testKonzert1 = new Konzert();
        testKonzert1.setId(1);
        
        testKonzert2 = new Konzert();
        testKonzert2.setId(2);
    }
    
    @Test
    public void testBestelleTickets()
    {
        BestellvorgangLocal bv = new BestellvorgangBean();
        
        assertFalse(bv.hasBestellungen());
    
        bv.bestelleTickets(testKonzert1, 1);
        bv.bestelleTickets(testKonzert2, 2);
        
        assertTrue(bv.hasBestellungen());
        
        
        Collection<Ticketbestellung> liste = bv.getTicketbestellungen();
        
        assertEquals(2, liste.size());
        
    }



    @Test
    @Ignore
    public void testBezahleTickets()
    {
        fail("Not yet implemented"); // TODO
    }

}
