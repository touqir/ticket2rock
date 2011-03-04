package de.ejb3buch.ticket2rock.session.demo;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
@DependsOn("NotwendigBean")
public class AbhaengigBean {
	@PostConstruct
	public void init() {
		System.out.println("Abhaengig konstruiert!");
	}

	public void doSomething() {
	}
}
