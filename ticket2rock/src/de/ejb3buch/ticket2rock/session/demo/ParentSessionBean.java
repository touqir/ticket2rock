package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ParentSessionBean {
	public void doSomething() {
		System.out.println("Did something.");
	}
}
