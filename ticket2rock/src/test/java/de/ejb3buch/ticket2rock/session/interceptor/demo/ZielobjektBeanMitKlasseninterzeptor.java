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
package de.ejb3buch.ticket2rock.session.interceptor.demo;

import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;


@Stateless(name = "ZielobjektBeanKlasseninterzeptor")
@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
public class ZielobjektBeanMitKlasseninterzeptor extends ZielobjektBase {

	@ExcludeClassInterceptors
	public void lassMichInRuhe() {
	}

	@Timeout
	protected void deineZeitIstAbgelaufen(Timer timer) {
		super.deineZeitIstAbgelaufen(timer);
	}
}
