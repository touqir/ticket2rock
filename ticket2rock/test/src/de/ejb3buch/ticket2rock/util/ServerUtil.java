package de.ejb3buch.ticket2rock.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.glassfish.api.deployment.DeployCommandParameters;
import org.glassfish.api.embedded.ContainerBuilder;
import org.glassfish.api.embedded.EmbeddedFileSystem;
import org.glassfish.api.embedded.Server;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.glassfish.api.ShrinkwrapReadableArchive;
import org.jboss.shrinkwrap.glassfish.impl.ShrinkwrapReadableArchiveImpl;
import org.junit.Assert;

import de.ejb3buch.ticket2rock.KonzertDetails;
import de.ejb3buch.ticket2rock.session.crud.BandVerwaltungBean;

public class ServerUtil {
	static Server createServer() {
		Server.Builder builder = new Server.Builder("coralicglassfish");
		builder.logger(false);
		builder.verbose(false);
		EmbeddedFileSystem.Builder efsb = new EmbeddedFileSystem.Builder();
		efsb.autoDelete(true);
		EmbeddedFileSystem efs = efsb.build();
		builder.embeddedFileSystem(efs);
		Server server = builder.build();
		server.addContainer(server.createConfig(ContainerBuilder.Type.ejb));
		return server;
	}

	static void deploy(Server server) {
		server.getDeployer().deploy(createEAR(), new DeployCommandParameters());
		
	}

	private static JavaArchive createJarArchive() {
		JavaArchive archive = ShrinkWrap.create(JavaArchive.class,
				"ticket2rock.jar");
		archive.addPackages(true, KonzertDetails.class.getPackage());
		archive.addPackages(true, BandVerwaltungBean.class.getPackage());
		Assert.assertTrue(archive.delete(archive.get("/de/ejb3buch/ticket2rock/messagedriven")
				.getPath()));
		File persistenceXML = new File("test/conf/META-INF/persistence.xml");
		Assert.assertTrue(persistenceXML.exists());
		archive.addManifestResource(persistenceXML);
		return archive;
	}

	private static ShrinkwrapReadableArchive createEAR() {
		EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class,
				"ticket2rock.ear");
		ear.addModule(createJarArchive());
		Assert.assertTrue(new File("dd/ejb/ejb-jar.xml").exists());
		ear.addManifestResource(new File("dd/ejb/ejb-jar.xml"));
		return new ShrinkwrapReadableArchiveImpl(ear);
	}
	
	// Derby und glassfish hinterlassen einige Spuren im Filesystem, die hier
	// gelöscht werden.
	static void deleteFiles() {
		File wrkDir = new File((System.getProperty("user.dir")));
		File[] filesToDelete = wrkDir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return isGlassFishFile(file) || isDerbyFile(file);
			}
		});
		for (File file : filesToDelete){
			if (file.isDirectory()){
				try{
					FileUtils.deleteDirectory(file);
				}
				catch(IOException e){
					//Logger besitzt jetzt noch fileHandle, daher scheitert das Löschen für das
					//zuletzt erzeugte Filesystem.
				}
			}
			else{
				file.delete();
			}
		}
	}

	private static boolean isDerbyFile(File file) {
		return file.isFile() && file.getName().equals("derby.log");
	}

	private static boolean isGlassFishFile(File file) {
		return isTargetFolder(file) || isFileSystemFolder(file) || isNullFolder(file);
	}

	private static boolean isTargetFolder(File file) {
		return file.isDirectory() && file.getName().equals("target");
	}

	private static boolean isFileSystemFolder(File file) {
		return file.isDirectory() && file.getName().startsWith("gfembed") && file.getName().endsWith("tmp");
	}

	private static boolean isNullFolder(File file) {
		return file.isDirectory() && file.getName().equals("null");
	}

}
