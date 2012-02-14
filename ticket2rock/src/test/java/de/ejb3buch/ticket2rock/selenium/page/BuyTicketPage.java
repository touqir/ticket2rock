package de.ejb3buch.ticket2rock.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BuyTicketPage extends AbstractPage {

	@FindBy(how = How.ID, using = "ticketbestellungForm:ticketnumber")
	@CacheLookup
	WebElement ticketNumberField;

	@FindBy(how = How.ID, using = "ticketbestellungForm:submitOrder")
	@CacheLookup
	WebElement submitButton;
	@FindBy(how = How.CSS, using = "span.errors")
	WebElement errorMessageSpan;

	@Override
	public boolean isCurrentPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public BuyTicketPage fillIntoNumberField(String number) {
		//There is a zero prefilled which has to be deleted first.
		ticketNumberField.sendKeys("\b" + number);
		return this;
	}

	public String getErrorMessage() {
		return errorMessageSpan.getText();
	}

	public BuyTicketPage submit() {
		submitButton.click();
		return this;
	}

}
