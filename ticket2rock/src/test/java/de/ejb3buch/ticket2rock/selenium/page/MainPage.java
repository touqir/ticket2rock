package de.ejb3buch.ticket2rock.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import de.ejb3buch.ticket2rock.cucumber.runner.SharedDriver;

public class MainPage {
	SharedDriver driver;

	@FindBy(how = How.ID, using = "menuBarItem1")
	@CacheLookup
	WebElement verwaltungsMenuButton;
	
	@FindBy(how = How.ID, using = "menuItem1")
	@CacheLookup
	WebElement bandButton;

	public MainPage(SharedDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public MainPage open() {
		driver.get("http://localhost:8080/ticket2rock");
		return this;
	}

	void navigateToBandPage() {
		verwaltungsMenuButton.click();
		bandButton.click();
	}

}
