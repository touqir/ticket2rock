package de.ejb3buch.ticket2rock.applikation.helper;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public class FacesUtils {

	public static ServletContext getServletContext() {		
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	public static Map getRequestMap() {		
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}	
	
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}
}
