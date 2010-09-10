package de.ejb3buch.ticket2rock.util;

import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.junit.After;
import org.junit.Before;

public class UserTransactionPerTest {
	UserTransaction utx;
	@Before
	public void startTransaction(){
		utx = EmbeddedServerTestRunner.getStartedTransaction();
	}
	
	@After
	public void rollBack() throws IllegalStateException, SecurityException, SystemException{
		utx.rollback();
	}

}
