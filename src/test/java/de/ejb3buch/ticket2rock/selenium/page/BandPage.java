package de.ejb3buch.ticket2rock.selenium.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BandPage extends AbstractPage {

	@FindBy(how = How.ID, using = "bandForm:bandname")
	@CacheLookup
	private WebElement bandNameField;

	@FindBy(how = How.ID, using = "bandForm:bandMusikerList")
	private WebElement bandMemberSelectBox;

	@FindBy(how = How.ID, using = "bandForm:musikerList")
	private WebElement artistsSelectBox;

	@FindBy(how = How.ID, using = "bandForm:deselectButton")
	@CacheLookup
	private WebElement deselectArtistButton;

	@FindBy(how = How.ID, using = "bandForm:selectButton")
	@CacheLookup
	private WebElement selectArtistButton;

	@FindBy(how = How.XPATH, using = "//span[@class='errors']")
	private WebElement errorMessage;

	@FindBy(how = How.ID, using = "bandForm:submitAdd")
	@CacheLookup
	private WebElement submitButton;

	@FindBy(how = How.ID, using = "bandForm:submitCancel")
	@CacheLookup
	private WebElement cancelButton;

	public BandPage fillInBandName(String bandName) {
		bandNameField.sendKeys(bandName);
		return this;
	}

	public String readErrorMessage() {
		return errorMessage.getText();
	}

	public void submit() {
		submitButton.click();
	}

	public BandPage addArtistToBand(String name) {
		WebElement artistOption = artistsSelectBox.findElement(By
				.xpath("//option[text()=\"" + name + "\"]"));
		artistOption.click();
		selectArtistButton.click();
		return this;
	}

	public BandPage removeArtistFromBand(String name) {
		WebElement artistOption = bandMemberSelectBox.findElement(By
				.xpath("//option[text()=\"" + name + "\"]"));
		artistOption.click();
		deselectArtistButton.click();
		return this;
	}

	public List<String> getAllArtistsNotOnTheBand() {
		return getAllOptions(artistsSelectBox);
	}
	

	private static List<String> getAllOptions(WebElement selectBox) {
		ArrayList<String> list = new ArrayList<String>();
		for (WebElement option : selectBox.findElements(By.xpath("//option"))) {
			list.add(option.getText());
		}
		return list;
	}

	public List<String> getAllArtistsOnTheBand() {
		return getAllOptions(bandMemberSelectBox);
	}

	public void cancel() {
		cancelButton.click();
	}

	@Override
	public boolean isCurrentPage() {
		return driver.findElements(By.id("bandForm")).size()==1;
	}

}
