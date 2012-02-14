package de.ejb3buch.ticket2rock.selenium.page;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class MainPage extends AbstractPage {
	private static final String ADMINISTRATION_BUTTON_ID = "menuBarItem1";

	@FindBy(how = How.ID, using = ADMINISTRATION_BUTTON_ID)
	@CacheLookup
	WebElement administrationMenuButton;
	
	@FindBy(how = How.ID, using = "menuBarItem2")
	@CacheLookup
	WebElement useCasesButton;
	
	@FindBy(how = How.ID, using = "menuItem4")
	@CacheLookup
	WebElement searchConcertsButton;

	@FindBy(how = How.ID, using = "menuItem1")
	@CacheLookup
	WebElement bandButton;
	
	

	public MainPage open() {
		driver.get("http://localhost:8080/ticket2rock");
		waitForMenuBarToLoad();
		return this;
	}

	public void navigateToBandPage() {
		administrationMenuButton.click();
		bandButton.click();
	}
	
	public void navigateToSearchConcertsPage(){
		useCasesButton.click();
		searchConcertsButton.click();
	}

	private void waitForMenuBarToLoad() {
		new WebDriverWait(driver, 1).until(new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver driver) {
				try {
					driver.findElement(By.id(ADMINISTRATION_BUTTON_ID));
				} catch (NoSuchElementException e) {
					return false;
				}
				return true;
			}
		});
	}

	@Override
	public boolean isCurrentPage() {
		return driver.findElement(By.xpath("id('content')/div/h1")).getText()
				.equals("Willkommen bei Ticket2Rock!");
	}

}



