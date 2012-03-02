package de.ejb3buch.ticket2rock.selenium.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import de.ejb3buch.ticket2rock.cucumber.step.Concert;

public class ConcertSearchResultsPage extends AbstractPage {

	@Override
	public boolean isCurrentPage() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Concert> getConcerts() {
		List<WebElement> parts = driver.findElements(By
				.cssSelector("#j_idt14\\:items td"));
		ArrayList<Concert> concerts = new ArrayList<Concert>();
		Iterator<WebElement> iterator = parts.iterator();
		while (iterator.hasNext()) {
			concerts.add(new Concert(iterator.next().getText(), iterator.next()
					.getText(), iterator.next().getText(), iterator.next()
					.getText(), iterator.next().getText(), iterator.next()
					.getText(), iterator.next().getText()));
		}
		return concerts;

	}

	public void clickOnTickets() {
		driver.findElement(By.cssSelector("td input")).click();
		
	}

}
