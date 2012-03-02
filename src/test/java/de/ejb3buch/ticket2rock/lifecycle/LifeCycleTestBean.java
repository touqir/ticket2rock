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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;

public @Stateless
class LifeCycleTestBean implements LifeCycleTest
{
    public enum Zustand {NICHT_DA, FERTIG, SCHLAFEND, AUFGEWACHT, WEG };
    
    Zustand meinZustand = Zustand.NICHT_DA;

    @PostConstruct
    public void nachKonstruktion()
    {
        meinZustand = Zustand.FERTIG;
    }

    
    @PrePassivate
    public void vorPassivierung()
    {
        meinZustand = Zustand.SCHLAFEND;
        
    }
    
    @PostActivate
    public void nachAktivierung()
    {
        meinZustand = Zustand.AUFGEWACHT;
        
    }
    
    @PreDestroy
    public void vorZerstoerung()
    {
        meinZustand = Zustand.WEG;
    }

    public void hallo()
    {
        
    }
    
    public Zustand getZustand()
    {
        return meinZustand;
    }
}
