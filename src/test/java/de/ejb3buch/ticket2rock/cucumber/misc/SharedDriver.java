package de.ejb3buch.ticket2rock.cucumber.misc;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.annotation.After;
import cucumber.runtime.ScenarioResult;

/**
 * Dieser Driver wird von Cucumber für ein Szenario benutzt und danach
 * geschlossen. Es erbt von {@link EventFiringWebDriver}, da kein Adapter in
 * Selenium zu Verfügung steht.
 */
public class SharedDriver extends EventFiringWebDriver {
	public SharedDriver() {
		super(new FirefoxDriver());
	}

	@After
	public void close(ScenarioResult result) {
		mkScreenshot(result);
		super.close();
	}

	private void mkScreenshot(ScenarioResult result) {
		try {
			byte[] screenshot = this.getScreenshotAs(OutputType.BYTES);
			result.embed(new ByteArrayInputStream(screenshot), "image/png");
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err
					.println(somePlatformsDontSupportScreenshots.getMessage());
		}
	}
}
