package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.Startup;

@javax.ejb.Stateless
@Startup
public class HintergrundRechnerBean implements HintergrundRechner {

	@javax.ejb.Asynchronous
	public void rechneImHintergrund() {

	}
}
