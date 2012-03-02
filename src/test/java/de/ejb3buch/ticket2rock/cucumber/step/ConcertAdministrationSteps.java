package de.ejb3buch.ticket2rock.cucumber.step;

import java.util.List;


import com.google.inject.Inject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import cucumber.annotation.de.Dann;
import cucumber.annotation.de.Wenn;
import de.ejb3buch.ticket2rock.selenium.page.ConcertSearchResultsPage;
import de.ejb3buch.ticket2rock.selenium.page.SearchConcertsPage;

public class ConcertAdministrationSteps {
	@Inject
	SearchConcertsPage page;
	
	@Inject
	ConcertSearchResultsPage resultsPage;

	@Wenn("^ich als Ort \"([^\"]*)\" eingebe.$")
	public void ich_als_Ort_eingebe(String location) {
		page.fillLocationName(location);
	}

	@Dann("^sehe ich das folgende Ergebnis:$")
	public void sehe_ich_das_folgende_Ergebnis(List<Concert> concerts) {
		page.submit();
		assertThat(concerts, equalTo(resultsPage.getConcerts()));
	}

	
}
