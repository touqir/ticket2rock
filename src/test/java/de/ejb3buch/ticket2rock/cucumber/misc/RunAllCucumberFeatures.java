package de.ejb3buch.ticket2rock.cucumber.misc;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "src/test/resources/", glue = "de.ejb3buch.ticket2rock", 
  strict = true, format = "html:target/cucumber")
public class RunAllCucumberFeatures {

}
