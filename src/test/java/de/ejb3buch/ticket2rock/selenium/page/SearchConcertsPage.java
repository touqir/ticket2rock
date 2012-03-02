package de.ejb3buch.ticket2rock.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchConcertsPage extends AbstractPage{

	@FindBy(how = How.ID, using = "konzertsucheForm:ort")
	@CacheLookup
	WebElement locationField;
	

	@FindBy(how = How.ID, using = "	konzertsucheForm:von")
	@CacheLookup
	WebElement startDateField;
	
	@FindBy(how = How.ID, using = "	konzertsucheForm:bis")
	@CacheLookup
	WebElement endDateField;
	
	
	@FindBy(how = How.ID, using = "konzertsucheForm:submitSearch")
	@CacheLookup
	WebElement submitButton;
	
	
	@Override
	public boolean isCurrentPage() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public SearchConcertsPage fillLocationName(String name){
		locationField.sendKeys(name);
		return this;
	}
	
	public SearchConcertsPage fillStartDate(String date){
		startDateField.sendKeys(date);
		return this;
	}
	public SearchConcertsPage fillEndDate(String date){
		endDateField.sendKeys(date);
		return this;
	}
	public void submit(){
		submitButton.click();
	}
}
