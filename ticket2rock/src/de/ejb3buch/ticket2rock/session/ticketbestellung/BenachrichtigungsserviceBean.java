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
package de.ejb3buch.ticket2rock.session.ticketbestellung;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.entity.Konzert;
import de.ejb3buch.ticket2rock.entity.Ticketbestellung;

@Stateless
public class BenachrichtigungsserviceBean implements
		BenachrichtigungsserviceLocal {

	static Logger logger = Logger.getLogger(BenachrichtigungsserviceBean.class);

	@Resource
	private TimerService timerService;

	public void installiereKonzerterinnerungen(String email,
			List<Ticketbestellung> ticketBestellungen) {
		for (Ticketbestellung bestellung : ticketBestellungen) {
			Konzert konzert = bestellung.getKonzert();
			String[] benachrichtigungsInfo = new String[] { email,
					konzert.getInterpret().getName(),
					konzert.getOrt().getAdresse() };
			Calendar timerCalendar = GregorianCalendar.getInstance();
			timerCalendar.setTime(konzert.getDatum());
			timerCalendar.add(Calendar.DATE, -1);
			timerService.createTimer(timerCalendar.getTime(),
					benachrichtigungsInfo);
		}
	}

	@Timeout
	public void benachrichtigeKunde(Timer timer) {
		String[] infos = (String[]) timer.getInfo();
		String mailAdresse = infos[0];
		String interpret = infos[1];
		String stadt = infos[2];
		logger.info("Simuliere Mail-Versand an " + mailAdresse);
		logger.info("Sehr geehrter Kunde, bitte vergessen Sie das Konzert von "
				+ interpret + " in " + stadt + " am morgigen Tag nicht.");
	}
}
