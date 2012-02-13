package de.ejb3buch.ticket2rock.cucumber.misc;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.annotation.After;

/**
 * Dieser Driver wird von Cucumber für ein Szenario benutzt und danach
 * geschlossen. Es erbt von {@link EventFiringWebDriver}, da kein Adapter in
 * Selenium zu Verfügung steht.
 */
public class SharedDriver extends EventFiringWebDriver {
	public SharedDriver() {
		super(new FirefoxDriver());
	}

	@After
	public void close() {
		super.close();
	}
}
