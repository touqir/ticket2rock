/**
 *  Ticket2Rock ist die Beispielanwendung des Buchs "EJB 3 professionell" (dpunkt).
 *  Es implementiert eine einfache Webanwendung zur Onlinebuchung von Tickets f�r
 *  Rockkonzerte auf Basis von EJB 3.0 und JavaServer Faces.
 *
 *  Copyright (C) 2006
 *  Dierk Harbeck, Stefan M. Heldt, Oliver Ihns, Jochen J�rg und Holger Koschek
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

package de.ejb3buch.ticket2rock.messagedriven;

import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import de.ejb3buch.ticket2rock.session.ticketbestellung.Stornator;

@MessageDriven
public class StornierungsnachrichtVerarbeitungsBean implements MessageListener {

	static Logger logger = Logger
			.getLogger(StornierungsnachrichtVerarbeitungsBean.class);

	@EJB
	private Stornator stornator;

	public void onMessage(Message msg) {
		try {
			TextMessage tmsg = (TextMessage) msg;
			StringTokenizer st = new StringTokenizer(tmsg.getText().trim(), " ");
			if (st.countTokens() == 2) {
				String command = st.nextToken();
				if (!command.equalsIgnoreCase("STORNO")) {
					throw new EJBException("'" + tmsg.getText().trim()
							+ "' - Invalid command: '" + command + "'");
				}

				String parameter = st.nextToken();
				long bestellnr;
				try {
					bestellnr = Long.parseLong(parameter);
				} catch (NumberFormatException nfe) {
					throw new EJBException("'" + tmsg.getText().trim()
							+ "' - Invalid reservation ID: '" + parameter + "'");
				}

				logger.info("Storniere Bestellung Nr. " + bestellnr);
				stornator.storniereBestellung(bestellnr);

			} else {
				throw new EJBException("'" + tmsg.getText().trim()
						+ "' - Invalid message");
			}
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
}
