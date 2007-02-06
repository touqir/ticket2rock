package de.ejb3buch.ticket2rock;

import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


public class EmbeddedContainerTestBase {

	private UserTransaction utx = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EmbeddedContainerTestHelper.startupEmbeddedContainer(null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		EmbeddedContainerTestHelper.shutdownEmbeddedContainer();
	}

	public EmbeddedContainerTestBase() {
		super();
	}

	/**
	 * Startet eine UserTransaction vor jedem Test
	 */
	@Before
	public void startTransaction() throws Exception {
		utx = EmbeddedContainerTestHelper.startUserTransaction();
	}

	/**
	 * und nach jedem Test schmeissen wir die Aenderungen an der DB weg.
	 */
	@After
	public void rollbackTransaction() throws Exception {
		utx.rollback();
	}

	protected Object lookup(String name) throws Exception
	{
		return EmbeddedContainerTestHelper.lookup(name);
	}
	
	protected InitialContext getInitialContext() throws Exception
	{
		return EmbeddedContainerTestHelper.getInitialContext();
	}
}