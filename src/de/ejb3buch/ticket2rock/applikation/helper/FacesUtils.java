package de.ejb3buch.ticket2rock.applikation.helper;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
	
	protected static ClassLoader getCurrentClassLoader(Object defaultObject){
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		if(loader == null){
			loader = defaultObject.getClass().getClassLoader();
		}
		
		return loader;
	}

	public static String getMessageResourceString(
							String bundleName, 
							String key, 
							Object params[], 
							Locale locale){
		
		String text = null;
		
		ResourceBundle bundle = 
				ResourceBundle.getBundle(bundleName, locale, 
										getCurrentClassLoader(params));
		
		try{
			text = bundle.getString(key);
		} catch(MissingResourceException e){
			text = "?? key " + key + " not found ??";
		}
		
		if(params != null){
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		
		return text;
	}
}
