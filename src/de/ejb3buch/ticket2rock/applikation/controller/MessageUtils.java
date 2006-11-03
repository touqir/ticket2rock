package de.ejb3buch.ticket2rock.applikation.controller;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageUtils {

	  private MessageUtils() {
	  }

	  public static String getLocalizedString(FacesContext facesContext, String key) {
	    ResourceBundle bundle = ResourceBundle.getBundle(
	        facesContext.getApplication().getMessageBundle(),
	        facesContext.getViewRoot().getLocale());
	    return bundle.getString(key);
	  }


	  public static FacesMessage createErrorMessage(
	      String key, FacesContext facesContext) {
	    FacesMessage message = new FacesMessage();
	    message.setDetail(getLocalizedString(facesContext, key));
	    message.setSeverity(FacesMessage.SEVERITY_ERROR);
	    return message;
	  }

	}

