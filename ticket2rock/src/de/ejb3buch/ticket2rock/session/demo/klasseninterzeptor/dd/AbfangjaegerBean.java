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

package de.ejb3buch.ticket2rock.session.demo.klasseninterzeptor.dd;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.demo.AbfangjaegerLocal;

// Eine Stateless Session Bean, für die der Interzeptor "Abfangjaeger"
// als Klasseninterzeptor mit Ausnahmen im Deployment-Deskriptor ejb-jar.xml
// definiert ist.

public class AbfangjaegerBean implements AbfangjaegerLocal {

	static Logger logger = Logger.getLogger(AbfangjaegerBean.class);

	public void fangMichAb() {
	}

	public Object michAuch(Object obj) {
		return obj;
	}

	public void michNicht() {
	}

	@AroundInvoke
	public Object beanKlassenInterzeptor(InvocationContext ctx)
			throws Exception {
		try {
			logger.info("Bean-Klassen-Interzeptor [" + ctx.getMethod()
					+ "] - vor dem Methodenaufruf");
			return ctx.proceed();
		} finally {
			logger.info("Bean-Klassen-Interzeptor [" + ctx.getMethod()
					+ "] - nach dem Methodenaufruf");
		}
	}
}