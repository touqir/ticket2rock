package de.ejb3buch.ticket2rock.cucumber.step;

import javax.inject.Inject;
import static org.junit.Assert.assertTrue;

import cucumber.annotation.de.Angenommen;
import cucumber.annotation.de.Wenn;
import de.ejb3buch.ticket2rock.selenium.page.BandAdministrationPage;
import de.ejb3buch.ticket2rock.selenium.page.MainPage;

public class NavigationSteps {

	@Inject
	MainPage mainPage;
	
	@Inject
	BandAdministrationPage bandVerwaltungsPage;
	

	@Angenommen("^ich öffne als User die Ticket2Rock-Seite.$")
	public void openThePage() {
		mainPage.open();
		assertTrue(mainPage.isCurrentPage());
	}

	@Angenommen("^ich die Bandverwaltung öffne.$")
	public void openBandsPage() {
		mainPage.navigateToBandPage();
		assertTrue(bandVerwaltungsPage.isCurrentPage());

	}

	@Angenommen("^auf \"Neue Band anlegen\" klicke.$")
	public void clickOnNewBandButton() {
		bandVerwaltungsPage.clickOnNewBandButton();
		
	}
	
	@Wenn("^ich die Konzertsuche öffne.$")
	public void ich_die_Konzertsuche_öffne() {
		mainPage.navigateToSearchConcertsPage();
	}

}
