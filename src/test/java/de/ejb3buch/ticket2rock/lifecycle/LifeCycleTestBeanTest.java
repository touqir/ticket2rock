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
package de.ejb3buch.ticket2rock.lifecycle;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.stvconsultants.easygloss.javaee.JavaEEGloss;

public class LifeCycleTestBeanTest
{
    private JavaEEGloss gloss;
    
    @Before 
    public void umgebungAufbauen()
    {
        gloss = new JavaEEGloss();
    }
    
    @Test
    public void testNachKonstruktion()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
       
    }

    @Test
    public void testVorPassivierung()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
        gloss.beforePassivate(tb);
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.SCHLAFEND);         
    }

    @Test
    public void testNachAktivierung()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
        gloss.afterActivate(tb);
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.AUFGEWACHT);     
    }

    @Test
    public void testVorZerstoerung()
    {
        LifeCycleTestBean tb = gloss.make(LifeCycleTestBean.class);
        
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.FERTIG); 
        gloss.beforeDestroy(tb);
        assertTrue(tb.getZustand() == LifeCycleTestBean.Zustand.WEG);     }

}
