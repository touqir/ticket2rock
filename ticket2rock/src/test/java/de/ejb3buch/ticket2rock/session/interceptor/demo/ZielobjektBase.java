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

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;


public class ZielobjektBase implements Zielobjekt {

	@Resource
	private TimerService timerService;

	private Logger logger = Logger.getRootLogger();

	public void fangMichAb() {
	}

	public String michAuch(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	public void duKriegstMichNicht() {
		throw new RuntimeException("Mich kriegst Du nicht!");
	}

	public void lassMichInRuhe() {
	}

	public void erzeugeTimer() {
		Calendar timerCal = GregorianCalendar.getInstance();
		timerCal.add(Calendar.MILLISECOND, 1);
		String info = "Deine Zeit läuft ab um "
				+ timerCal.get(Calendar.HOUR_OF_DAY) + " Uhr "
				+ timerCal.get(Calendar.MINUTE) + " Minuten, "
				+ timerCal.get(Calendar.SECOND) + " Sekunden und "
				+ timerCal.get(Calendar.MILLISECOND) + " Millisekunden!";
		timerService.createTimer(timerCal.getTime(), info);
		logger.info("Timer created");
	}

	protected void deineZeitIstAbgelaufen(Timer timer) {
		logger.info("Bean timeout: " + (String) timer.getInfo());
	}

	@SuppressWarnings("unused")
	@AroundInvoke
	private Object umMichSelbst(InvocationContext ctx) throws Exception {
		logger.info("Vor dem Aufruf von " + ctx.getTarget() + "."
				+ ctx.getMethod().getName());
		try {
			return ctx.proceed();
		} finally {
			logger.info("Nach dem Aufruf von " + ctx.getTarget() + "."
					+ ctx.getMethod().getName());
		}
	}
}
