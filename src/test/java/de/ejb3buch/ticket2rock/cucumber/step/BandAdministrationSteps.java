package de.ejb3buch.ticket2rock.cucumber.step;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;

import com.google.inject.Inject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import cucumber.annotation.de.Dann;
import cucumber.annotation.de.Wenn;
import de.ejb3buch.ticket2rock.selenium.page.BandAdministrationPage;
import de.ejb3buch.ticket2rock.selenium.page.BandPage;
import de.ejb3buch.ticket2rock.selenium.page.Utils;

public class BandAdministrationSteps {
	@Inject
	BandPage bandPage;

	@Inject
	BandAdministrationPage administrationPage;

	private String nameOfDeletedBand;
	
	private String nameOfCreatedBand;

	@Wenn("^ich einer Band keinen Namen gebe.$")
	public void ich_einer_Band_keinen_Namen_gebe() {
		//nothing to do.
	}

	@Wenn("^auf Speichern klicke.$")
	public void auf_Speichern_klicke() {
		bandPage.submit();
	}

	@Dann("^erscheint die Fehlermeldung \"([^\"]*)\".$")
	public void erscheint_die_Fehlermeldung_(String expectedErrorMessage) {
		String actualErrorMessage = bandPage.readErrorMessage();
		assertThat(expectedErrorMessage,is(actualErrorMessage));
	}

	@Wenn("^der Band einen Namen gebe.$")
	public void ich_einer_Band_den_Namen_gebe() {
		nameOfCreatedBand = RandomStringUtils.random(10);
		bandPage.fillInBandName(nameOfCreatedBand);
	}

	@Wenn("^einen Künstler hinzufüge.$")
	public void einen_Künstler_hinzufüge() {
		List<String> artistNames = bandPage.getAllArtistsNotOnTheBand();
		String choosenArtist =  artistNames.get(Utils.getRandomNumber(artistNames.size()-1));
		bandPage.addArtistToBand(choosenArtist);
	}

	@Dann("^sehe ich auf der Bandverwaltungsseite eine Band mit diesem Namen.$")
	public void sehe_ich_auf_der_Bandverwaltungsseite_die_Band_() {
		assertThat(administrationPage.getAllBandNames(),
				(hasItem(nameOfCreatedBand)));
	}

	@Wenn("^ich bei einer Band auf \"Löschen\" klicke.$")
	public void deleteBand() {
		nameOfDeletedBand = administrationPage.deleteRandomBand();
	}

	@Dann("^ist die Band nicht mehr in der Liste.$")
	public void ist_die_Band_nicht_mehr_in_der_Liste() {
		assertThat(administrationPage.getAllBandNames(),
				not(hasItem(nameOfDeletedBand)));
	}

}
