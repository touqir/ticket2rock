package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ChildSessionBean extends ParentSessionBean {
	public void doSomethingElse() {
		System.out.println("Did something else.");
	}
}
