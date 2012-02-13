package de.ejb3buch.ticket2rock.selenium.page;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import de.ejb3buch.ticket2rock.cucumber.misc.SharedDriver;

public class BandVerwaltungsPage {

	@FindBy(how = How.CSS, using = "input[value=\"Neue Band\"]")
	@CacheLookup
	private WebElement verwaltungsMenuButton;

	@Inject
	SharedDriver driver;

	@PostConstruct
	public void init() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnNewBandButton() {
		verwaltungsMenuButton.click();
	}

}
