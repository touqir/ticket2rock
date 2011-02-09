package de.ejb3buch.ticket2rock.session.demo;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class NotwendigBean {
	@PostConstruct
	public void init() {
		System.out.println("Notwendig konstruiert!");
	}

	public void doSomething() {
	}
}
