package de.ejb3buch.ticket2rock.cucumber.step;

import com.google.inject.Inject;

import cucumber.annotation.de.Angenommen;
import cucumber.annotation.de.Wenn;
import de.ejb3buch.ticket2rock.selenium.page.BuyTicketPage;
import de.ejb3buch.ticket2rock.selenium.page.ConcertSearchResultsPage;
import de.ejb3buch.ticket2rock.selenium.page.MainPage;
import de.ejb3buch.ticket2rock.selenium.page.SearchConcertsPage;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TicketSteps {
	@Inject
	MainPage mainPage;
	
	@Inject
	SearchConcertsPage searchConcertsPage;
	
	@Inject
	ConcertSearchResultsPage searchResultsPage;
	
	@Inject
	BuyTicketPage buyTicketPage;
	
	 
	@Angenommen("^ich habe ein Konzert gefunden für das noch Tickets verfügbar sind und auf \"Tickets\" geklickt.$")
	public void ich_habe_ein_Konzert_gefunden_und_auf_geklickt() {
		mainPage.open();
		mainPage.navigateToSearchConcertsPage();
		searchConcertsPage.fillLocationName("Olympiastadion").submit();
		searchResultsPage.clickOnTickets();
	}

	@Wenn("^ich \"([^\"]*)\" als Ticketanzahl angebe und auf Bestellen drücke sehe ich diese Meldung: \"([^\"]*)\".$")
	public void ich_Tickets_angebe_und_auf_Bestellen_drücke_sehe_ich_diese_Meldung_(String ticketCount, String errorMessage) {
		assertThat((buyTicketPage.fillIntoNumberField(ticketCount).submit().getErrorMessage()),is(errorMessage));
	}

}
