package de.ejb3buch.ticket2rock.selenium.page;

import javax.annotation.PostConstruct;

import org.openqa.selenium.support.PageFactory;

import com.google.inject.Inject;

import de.ejb3buch.ticket2rock.cucumber.misc.SharedDriver;

public abstract class AbstractPage {
	
	@Inject
	SharedDriver driver;
	
	@PostConstruct
	public void init() {
		PageFactory.initElements(driver, this);
	}
	
	public abstract boolean isCurrentPage();
}
