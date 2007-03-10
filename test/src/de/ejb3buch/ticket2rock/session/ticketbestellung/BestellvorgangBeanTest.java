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
