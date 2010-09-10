package de.ejb3buch.ticket2rock.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.apache.commons.io.FileUtils;
import org.glassfish.api.ActionReport;
import org.glassfish.api.admin.CommandRunner;
import org.glassfish.api.admin.ParameterMap;
import org.glassfish.api.embedded.Server;

public class DataUtil {
	static void createConnectionPoolAndJDBCResource(Server server) {
		createConnectionPool(server);
		createJDBCResource(server);
	}

	private static void createConnectionPool(Server server) {
		String com = "create-jdbc-connection-pool";
		ParameterMap props = new ParameterMap();
		props.add("datasourceclassname",
				"org.apache.derby.jdbc.EmbeddedDataSource");
		props.add("restype", "javax.sql.DataSource");
		props.add("ping", "true");
		props.add("property",
				"ConnectionAttributes=create\\=true:DatabaseName=target/ticket2rock");

		props.add("DEFAULT", "ticket2rock");
		CommandRunner run = server.getHabitat().getComponent(
				CommandRunner.class);
		ActionReport rep = server.getHabitat().getComponent(ActionReport.class);
		run.getCommandInvocation(com, rep).parameters(props).execute();
	}

	static void createJDBCResource(Server server) {
		String command = "create-jdbc-resource";
		ParameterMap par = new ParameterMap();
		par.add("connectionpoolid", "ticket2rock");
		par.add("jndi_name", "jdbc/ticket2rock");
		CommandRunner runner = server.getHabitat().getComponent(
				CommandRunner.class);
		ActionReport report = server.getHabitat().getComponent(
				ActionReport.class);
		runner.getCommandInvocation(command, report).parameters(par).execute();
	}

	static void insertTestData() throws NamingException, SQLException,
			IOException, NotSupportedException, SystemException,
			SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {
		EntityManager em = Persistence
				.createEntityManagerFactory("ticket2rock")
				.createEntityManager();
		EntityTransaction utx = em.getTransaction();
		@SuppressWarnings("unchecked")
		List<String> lines = FileUtils.readLines(new File(
				"test\\conf\\import.sql.test"));
		utx.begin();
		for (String line : lines) {
			if (isNotComment(line) && isNotEmpty(line)) {
				em.createNativeQuery(line.replace(";", "")).executeUpdate();
			}
		}
		utx.commit();
		em.close();
	}
	
	static boolean isNotComment(String line) {
		return !line.trim().startsWith("--");
	}

	static boolean isNotEmpty(String line) {
		return !line.trim().equals("");
	}

}
