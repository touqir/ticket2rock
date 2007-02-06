package de.ejb3buch.ticket2rock;
import junit.framework.JUnit4TestAdapter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.ejb3buch.ticket2rock.session.interceptor.BeanStatisticsInterceptorTestSimple;
import de.ejb3buch.ticket2rock.session.statistics.BeanStatisticsBeanTest;

@RunWith(Suite.class)
@Suite.SuiteClasses( 
		{ BeanStatisticsBeanTest.class,
		  BeanStatisticsInterceptorTestSimple.class})

public class AllPOJTests {
	// Used for backward compatibility (IDEs, Ant and JUnit 3 text runner)
	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(AllTests.class);
	}
}
