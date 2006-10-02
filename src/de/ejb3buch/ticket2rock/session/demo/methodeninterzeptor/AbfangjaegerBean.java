/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets für
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen Jörg und Holger Koschek
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

package de.ejb3buch.ticket2rock.session.demo.methodeninterzeptor;

import javax.ejb.Stateless;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;

import de.ejb3buch.ticket2rock.session.demo.Abfangjaeger;
import de.ejb3buch.ticket2rock.session.demo.AbfangjaegerLocal;

// Eine Stateless Session Bean, für die der Interzeptor "Abfangjaeger"
// auf Methodenebene per Annonation definiert ist.

@Stateless
public class AbfangjaegerBean implements AbfangjaegerLocal {
	@Interceptors(Abfangjaeger.class)
	public void fangMichAb() {
	}

	@Interceptors(Abfangjaeger.class)
	public Object michAuch(Object obj) {
		return obj;
	}

	public void michNicht() {
	}

	@AroundInvoke
	public Object beanKlassenInterzeptor(InvocationContext ctx)
			throws Exception {
		try {
			System.out.println("Bean-Klassen-Interzeptor [" + ctx.getMethod()
					+ "] - vor dem Methodenaufruf");
			return ctx.proceed();
		} finally {
			System.out.println("Bean-Klassen-Interzeptor [" + ctx.getMethod()
					+ "] - nach dem Methodenaufruf");
		}
	}
}
