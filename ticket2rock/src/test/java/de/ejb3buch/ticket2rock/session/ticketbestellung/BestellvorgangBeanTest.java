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
package de.ejb3buch.ticket2rock.session.ticketbestellung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;

public class BestellvorgangBeanTest
{
	
	static Konzert testKonzert1, testKonzert2;
	BestellvorgangBean bv;

    @Before
    public void baueKonzerte()
    {
        testKonzert1 = new Konzert();
        testKonzert1.setId(1);
        testKonzert2 = new Konzert();
        testKonzert2.setId(2);
        bv = new BestellvorgangBean();
        JavaEEGloss.apply(Inject.class, bv,Mockito.mock(Conversation.class));
    }
    
    @Test
    public void testBestelleTickets()
    {
    	
        assertFalse(bv.hasBestellungen());
        bv.bestelleTickets(testKonzert1, 1);
        bv.bestelleTickets(testKonzert2, 2);
        assertTrue(bv.hasBestellungen());
        Collection<Ticketbestellung> liste = bv.getTicketbestellungen();
        assertEquals(2, liste.size());
    }

}
