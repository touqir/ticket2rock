package de.ejb3buch.ticket2rock.session.ticketbestellung;

import java.util.List;

import javax.ejb.Local;

import de.ejb3buch.ticket2rock.entity.Ticketbestellung;

@Local
public interface BenachrichtigungsserviceLocal {

	public void installiereKonzerterinnerungen(String email,
			List<Ticketbestellung> ticketBestellungen);
}
