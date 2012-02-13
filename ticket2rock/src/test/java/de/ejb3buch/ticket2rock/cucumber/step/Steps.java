package de.ejb3buch.ticket2rock.cucumber.step;

import javax.inject.Inject;

import cucumber.annotation.de.Angenommen;
import de.ejb3buch.ticket2rock.selenium.page.BandVerwaltungsPage;
import de.ejb3buch.ticket2rock.selenium.page.MainPage;

public class Steps {

	@Inject
	MainPage mainPage;
	@Inject
	BandVerwaltungsPage bandVerwaltungsPage;

	@Angenommen("^ich öffne als User die Ticket2Rock-Seite.$")
	public void openThePage() {
		mainPage.open();
	}

	@Angenommen("^ich die Bandverwaltung öffne.$")
	public void openBandsPage() {
		mainPage.navigateToBandPage();

	}

	@Angenommen("^auf \"Neue Band anlegen\" klicke.$")
	public void clickOnNewBandButton() {
		bandVerwaltungsPage.clickOnNewBandButton();
	}

}
