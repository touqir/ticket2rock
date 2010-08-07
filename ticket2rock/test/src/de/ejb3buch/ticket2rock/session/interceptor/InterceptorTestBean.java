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

package de.ejb3buch.ticket2rock.session.interceptor;

import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

/*
 * Eine Stateless Session Bean, für die der Interzeptor
 * "BeanStatisticsInterceptor" auf Methodenebene per Annonation definiert ist.
 */

@Stateless
@Interceptors(BeanStatisticsInterceptor.class)
public class InterceptorTestBean implements InterceptorTest
{

    public void interceptedCall(String str)
    {
        doWasteSomeTime();
    }

    @ExcludeClassInterceptors
    public void nonInterceptedCall(String str)
    {
        doWasteSomeTime();

    }

    private void doWasteSomeTime()
    {
        long wait = Math.round(Math.random() * 200.0);

        long start = System.currentTimeMillis();

        while ((start + wait) > System.currentTimeMillis())
        {
        }
    }
}
