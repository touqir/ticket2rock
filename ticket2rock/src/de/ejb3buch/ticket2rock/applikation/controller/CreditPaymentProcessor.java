package de.ejb3buch.ticket2rock.applikation.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;

@ApplicationScoped
@Credit
public class CreditPaymentProcessor implements PaymentProcessor, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String getType() {
		return "Credit";
	}
	
	

}