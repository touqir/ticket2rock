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

package de.ejb3buch.ticket2rock.session.demo;

import java.math.BigDecimal;
import java.util.Collection;

import javax.ejb.Stateless;

import de.ejb3buch.ticket2rock.entity.Ticketbestellung;
import de.ejb3buch.ticket2rock.session.demo.DiscountCalculator;

public @Stateless
class DiscountCalculatorBean implements DiscountCalculator
{
    static BigDecimal STD_PRICE = new BigDecimal("50.0");

    public BigDecimal calculateDiscountedPrice(Collection<Ticketbestellung> bestellungen)
    {
        BigDecimal result = new BigDecimal(0);

        for (Ticketbestellung a : bestellungen)
        {
            BigDecimal items = new BigDecimal(a.getAnzahl());
            
            BigDecimal thisOrder = items.multiply(STD_PRICE);
            System.out.println("thisorder " + thisOrder);
            result = result.add( thisOrder);
        }

        return result;
    }
}
