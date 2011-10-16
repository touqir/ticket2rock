package de.ejb3buch.ticket2rock.session.demo;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
@Stateless
@LocalBean
public class CallerBean {
	
	@EJB
	private  ChildSessionBean childSessionBean;
	
	@WebMethod
	public String invokeThis() {
		childSessionBean.defaultVisibility();
		return "";
	}

}
