package de.ejb3buch.ticket2rock.selenium.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BandAdministrationPage extends AbstractPage {

	private static final String FORM_ID = "j_idt14";

	@FindBy(how = How.CSS, using = "input[value=\"Neue Band\"]")
	@CacheLookup
	private WebElement verwaltungsMenuButton;

	@FindBy(how = How.CSS, using = "td a")
	private List<WebElement> bandLinks;

	@FindBy(how = How.CSS, using = "td input.button")
	@CacheLookup
	private List<WebElement> deleteBandButtons;

	public void clickOnNewBandButton() {
		verwaltungsMenuButton.click();
	}

	public boolean isCurrentPage() {
		return driver.findElements(By.id(FORM_ID)).size() == 1;
	}

	/**
	 * Chooses one of the bands at random, and returns it's name.
	 * 
	 * @return the name of the deleted band.
	 */
	public String deleteRandomBand() {
		int index = Utils.getRandomNumber(bandLinks.size()-1);
		String bandName = bandLinks.get(index).getText();
		deleteBandButtons.get(index).click();
		return bandName;
	}

	public List<String> getAllBandNames() {
		ArrayList<String> list = new ArrayList<String>();
		for (WebElement bandLink : bandLinks){
			list.add(bandLink.getText());
		}
		return list;
	}
}
