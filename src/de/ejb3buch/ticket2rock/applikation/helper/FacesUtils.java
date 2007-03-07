package de.ejb3buch.ticket2rock.applikation.helper;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
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
	
	
	/**
	 * Hilfmethode zur Generierung einer Message. Message im FacesContext gesetzt
	 * @param clientId id der Client Komponente, der diese Message zuzuornden ist
	 * @param messageId property name des zu generierenden Message String
	 */
	public static void addMessage(String clientId, String messageId) {
		FacesContext context = FacesContext.getCurrentInstance();
		String messageString = FacesUtils.getMessageResourceString(
				context.getApplication().getMessageBundle(),
				messageId, null, context
						.getViewRoot().getLocale());
		FacesMessage message = new FacesMessage(messageString);
		context.addMessage(clientId,message);
	}
}
