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
import javax.interceptor.Interceptors;


@Stateless(name = "ZielobjektBeanMitMethodeninterzeptor")
public class ZielobjektBeanMitMethodeninterzeptor extends ZielobjektBase {

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public void fangMichAb() {
		super.fangMichAb();
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public String michAuch(Object obj) {
		return super.michAuch(obj);
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public void duKriegstMichNicht() {
		super.duKriegstMichNicht();
	}

	// Hier verzichten wir auf die @Interceptors-Annotation, weil diese Methode
	// nicht abgefangen werden soll
	public void lassMichInRuhe() {
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	public void erzeugeTimer() {
		super.erzeugeTimer();
	}

	@Interceptors(de.ejb3buch.ticket2rock.session.interceptor.demo.Abfangjaeger.class)
	@Timeout
	protected void deineZeitIstAbgelaufen(Timer timer) {
		super.deineZeitIstAbgelaufen(timer);
	}
}
