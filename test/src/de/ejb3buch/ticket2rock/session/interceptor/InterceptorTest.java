package de.ejb3buch.ticket2rock.session.interceptor;

import javax.ejb.Local;

@Local
public interface InterceptorTest {

	public void interceptedCall(String str);
	
	public void nonInterceptedCall(String str);
	
}