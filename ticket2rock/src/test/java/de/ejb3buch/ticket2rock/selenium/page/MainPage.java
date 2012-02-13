package de.ejb3buch.ticket2rock.selenium.page;

import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import de.ejb3buch.ticket2rock.cucumber.misc.SharedDriver;


public class MainPage {
	private static final String ADMINISTRATION_BUTTON_ID = "menuBarItem1";

	@Inject
	SharedDriver driver;

	@PostConstruct
	public void init() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = ADMINISTRATION_BUTTON_ID)
	@CacheLookup
	WebElement verwaltungsMenuButton;

	@FindBy(how = How.ID, using = "menuItem1")
	@CacheLookup
	WebElement bandButton;

	@Inject
	public MainPage(SharedDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public MainPage open() {
		driver.get("http://localhost:8080/ticket2rock");
		return this;
	}

	public void navigateToBandPage() {
		waitForMenuBarToLoad();
		verwaltungsMenuButton.click();
		bandButton.click();
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

}
