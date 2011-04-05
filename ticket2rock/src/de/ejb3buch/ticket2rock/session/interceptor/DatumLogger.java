package de.ejb3buch.ticket2rock.session.interceptor;

import java.util.Date;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

import org.slf4j.Logger;

@Decorator
public class DatumLogger /*implements Logger*/ {

	@Inject
	@Delegate
	Logger logger;

	public void debug(String message) {
		logger.debug(new Date() + " " + message);
	}

}
